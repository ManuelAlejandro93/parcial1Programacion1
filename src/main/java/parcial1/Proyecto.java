package parcial1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Proyecto contratado por un cliente
public class Proyecto {
	// Los desarrolladores se guardan en ARREGLOS de tamano fijo
	public static final int MAX_DESARROLLADORES = 10;
	// Los servicios se guardan en ARREGLOS de tamano fijo
	public static final int MAX_SERVICIOS = 10;

	private String codigoProyecto;
	private LocalDate fechaSolicitud;
	private LocalDate fechaInicio;
	private LocalDate fechaEntrega;
	private String estado; // Pendiente, Confirmado, En curso, Finalizado, Cancelado
	private String metodoPago; // Tarjeta de credito, Transferencia bancaria, Efectivo
	private double valorTotal;
	private Cliente cliente;

	private Desarrollador[] desarrolladoresAsignados; // asigna una cantidad de desarrolladores y ayuda en la lógica
	private ServicioAdicional[] serviciosUtilizados;

	private int cantidadDesarrolladores;
	private int cantidadServicios; // asigna una cantidad de servicios y ayuda en la lógica de asignación

	private double descuentoAplicado; // porcentaje, ej: 0.1 = 10%

	public Proyecto(String codigoProyecto, LocalDate fechaSolicitud, LocalDate fechaInicio,
			LocalDate fechaEntrega, String metodoPago, Cliente cliente) {
		this.codigoProyecto = codigoProyecto;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaInicio = fechaInicio;
		this.fechaEntrega = fechaEntrega;
		this.estado = "Pendiente";
		this.metodoPago = metodoPago;
		this.cliente = cliente;
		this.desarrolladoresAsignados = new Desarrollador[MAX_DESARROLLADORES];
		this.cantidadDesarrolladores = 0;
		this.serviciosUtilizados = new ServicioAdicional[MAX_SERVICIOS];
		this.cantidadServicios = 0;
		this.descuentoAplicado = 0.0;
		this.valorTotal = 0.0;
	}

	public String getCodigoProyecto() {
		return codigoProyecto;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public String getEstado() {
		return estado;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Desarrollador[] getDesarrolladoresAsignados() {
		return desarrolladoresAsignados;
	}

	public int getCantidadDesarrolladores() {
		return cantidadDesarrolladores;
	}

	public ServicioAdicional[] getServiciosUtilizados() {
		return serviciosUtilizados;
	}

	public int getCantidadServicios() {
		return cantidadServicios;
	}

	// Calcula la cantidad de dias de desarrollo (entre fecha inicio y fecha
	// entrega)
	public long calcularDiasDesarrollo() {
		return ChronoUnit.DAYS.between(fechaInicio, fechaEntrega);
	}

	// Calcula el valor total: tarifas de desarrolladores * dias + servicios -
	// descuento
	public double calcularValorTotal() {
		double costoDesarrolladores = 0.0;
		long dias = calcularDiasDesarrollo();

		for (int i = 0; i < cantidadDesarrolladores; i++) {
			costoDesarrolladores += desarrolladoresAsignados[i].getTarifaPorDia() * dias;
		}

		double costoServicios = 0.0;
		for (int i = 0; i < cantidadServicios; i++) {
			costoServicios += serviciosUtilizados[i].getPrecio();
		}

		// Descuento automatico del 10% si el cliente es frecuente
		if (cliente != null && cliente.esClienteFrecuente()) {
			descuentoAplicado = 0.1;
		}

		double subtotal = costoDesarrolladores + costoServicios;
		this.valorTotal = subtotal - (subtotal * descuentoAplicado);
		return this.valorTotal;
	}

	// Asigna un desarrollador al proyecto (validando disponibilidad y espacio en el
	// arreglo)
	public boolean asignarDesarrollador(Desarrollador nuevoDesarrollador) {
		if (cantidadDesarrolladores >= MAX_DESARROLLADORES) {
			return false;
		}
		if (!nuevoDesarrollador.estaDisponible(fechaInicio, fechaEntrega)) {
			return false;
		}
		desarrolladoresAsignados[cantidadDesarrolladores] = nuevoDesarrollador;
		cantidadDesarrolladores++;
		return true;
	}

	// Agrega un servicio adicional utilizado durante el proyecto
	public boolean agregarServicio(ServicioAdicional nuevoServicioAdicional) {
		if (cantidadServicios >= MAX_SERVICIOS)
			return false;
		serviciosUtilizados[cantidadServicios] = nuevoServicioAdicional;
		cantidadServicios++;
		return true;
	}

	// Cambia el estado del proyecto durante su ciclo de vida
	public void cambiarEstadoDeProyecto(String nuevoEstado) {
		this.estado = nuevoEstado;
	}

	@Override
	public String toString() {
		return "Proyecto " + codigoProyecto + " | Estado: " + estado +
				" | Cliente: " + (cliente != null ? cliente.getNombreLegal() : "N/A") +
				" | Valor total: " + valorTotal;
	}
}