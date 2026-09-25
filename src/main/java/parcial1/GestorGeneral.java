package parcial1;

import java.time.LocalDate;

public class GestorGeneral {
	private DevPlus empresa;
	private GestorClientes gestorClientes;
	private GestorDesarrolladores gestorDesarrolladores;
	private GestorProyectos gestorProyectos;
	private GestorServicios gestorServicios;

	public GestorGeneral(DevPlus empresa) {
		// Validar que la empresa no sea nula
		if (empresa == null) {
			throw new IllegalArgumentException("La empresa no puede ser nula");
		}

		this.empresa = empresa;
		this.gestorClientes = new GestorClientes();
		this.gestorDesarrolladores = new GestorDesarrolladores();
		this.gestorProyectos = new GestorProyectos();
		this.gestorServicios = new GestorServicios();
	}

	// Getters para acceder a los gestores
	public DevPlus getEmpresa() {
		return empresa;
	}

	public GestorClientes getGestorClientes() {
		return gestorClientes;
	}

	public GestorDesarrolladores getGestorDesarrolladores() {
		return gestorDesarrolladores;
	}

	public GestorProyectos getGestorProyectos() {
		return gestorProyectos;
	}

	public GestorServicios getGestorServicios() {
		return gestorServicios;
	}

	// ==================== LÓGICA DE NÚMEROS PERFECTOS ====================

	// Método que determina si un número es perfecto
	// Un número perfecto es igual a la suma de sus divisores propios
	// Ejemplo: 6 = 1 + 2 + 3
	public boolean esNumeroPerfecto(long numero) {
		if (numero <= 1) {
			return false;
		}

		long sumaDivisores = 0; // ✓ Cambio de int a long para evitar desbordamiento

		// ✓ Cambio de int a long en el bucle para evitar desbordamiento
		for (long i = 1; i < numero; i++) {
			if (numero % i == 0) {
				sumaDivisores += i;
			}
		}

		return sumaDivisores == numero;
	}

	// Extrae solo los dígitos de una cadena de teléfono
	private String extraerDigitos(String telefono) {
		// Validar que el teléfono no sea nulo
		if (telefono == null || telefono.trim().isEmpty()) {
			return "";
		}

		return telefono.replaceAll("[^0-9]", "");
	}

	// Busca un cliente por teléfono y valida si el número es perfecto
	public String consultarClientePorTelefonoYValidarPerfecto(String telefono) {
		// Validar que el teléfono no sea nulo
		if (telefono == null || telefono.trim().isEmpty()) {
			return "Teléfono inválido.";
		}

		Cliente cliente = gestorClientes.buscarClientePorTelefono(telefono);

		if (cliente == null) {
			return "Cliente no encontrado con ese teléfono.";
		}

		// Extrae los dígitos del teléfono
		String soloDigitos = extraerDigitos(telefono);

		// Validar que se hayan extraído dígitos
		if (soloDigitos.isEmpty()) {
			return "El teléfono no contiene dígitos válidos.";
		}

		// Convierte a número largo
		long numeroTelefono = Long.parseLong(soloDigitos);

		// Valida si es número perfecto
		boolean esPerfecto = esNumeroPerfecto(numeroTelefono);

		// Construye la respuesta
		String resultado = "=== CONSULTA DE CLIENTE ===\n\n";
		resultado += cliente.toString() + "\n\n";
		resultado += "Número de teléfono: " + numeroTelefono + "\n";
		resultado += "¿Es número perfecto?: " + (esPerfecto ? "SÍ ✓" : "NO ✗");

		return resultado;
	}

	// ==================== CONSULTA DE INGRESOS POR FECHA ====================

	// Delega al gestor de proyectos la consulta de ingresos
	public double consultarIngresosPorFecha(LocalDate fecha) {
		// Validar que la fecha no sea nula
		if (fecha == null) {
			return 0.0;
		}

		return gestorProyectos.consultarIngresosPorFecha(fecha);
	}

	// ==================== MÉTODOS INFORMATIVOS ====================

	// Retorna información general del sistema
	public String obtenerInformacionEmpresa() {
		String info = "=== INFORMACIÓN DE LA EMPRESA ===\n\n";
		info += "Nombre: " + empresa.getNombreLegal() + "\n";
		info += "NIT: " + empresa.getIdLegal() + "\n";
		info += "Dirección: " + empresa.getDireccion() + "\n";
		info += "Teléfono: " + empresa.getTelefono() + "\n";
		info += "Página Web: " + empresa.getPaginaWeb() + "\n";
		info += "\n--- ESTADÍSTICAS DEL SISTEMA ---\n";
		info += "Total Clientes: " + gestorClientes.getCantidad() + "\n";
		info += "Total Desarrolladores: " + gestorDesarrolladores.getCantidad() + "\n";
		info += "Total Proyectos: " + gestorProyectos.getCantidad() + "\n";
		info += "Total Servicios: " + gestorServicios.getCantidad();

		return info;
	}

	// Retorna el total de ingresos de todos los proyectos
	public double obtenerTotalIngresosGlobal() {
		double totalIngresos = 0.0;
		Proyecto[] proyectos = gestorProyectos.getProyectos();

		for (int i = 0; i < gestorProyectos.getCantidad(); i++) {
			if (proyectos[i] != null) {
				totalIngresos += proyectos[i].getValorTotal();
			}
		}

		return totalIngresos;
	}

	// Retorna información detallada del sistema
	public String obtenerReporteSistema() {
		String reporte = obtenerInformacionEmpresa() + "\n\n";
		reporte += "--- INGRESOS TOTALES ---\n";
		reporte += "Total Ingresos Globales: $" + String.format("%.2f", obtenerTotalIngresosGlobal());

		return reporte;
	}
}