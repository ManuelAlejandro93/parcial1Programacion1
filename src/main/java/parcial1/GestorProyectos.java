package parcial1;

import java.time.LocalDate;

public class GestorProyectos {
	public static final int MAX_PROYECTOS = 100;

	private Proyecto[] proyectos;
	private int cantidad;

	public GestorProyectos() {
		proyectos = new Proyecto[MAX_PROYECTOS];
		cantidad = 0;
	}

	// CREATE - crear
	public boolean crear(Proyecto nuevoProyecto) {
		if (cantidad >= MAX_PROYECTOS)
			return false;
		proyectos[cantidad] = nuevoProyecto;
		cantidad++;
		// Incrementar el contador de proyectos del cliente
		if (nuevoProyecto.getCliente() != null) {
			nuevoProyecto.getCliente().incrementarProyectos();
		}
		return true;
	}

	// READ (listar todos como texto)
	public String mostrarProyectos() {
		if (cantidad == 0)
			return "No hay proyectos registrados.";
		String resultado = "";
		for (int i = 0; i < cantidad; i++) {
			resultado = resultado + proyectos[i].toString() + "\n";
		}
		return resultado;
	}

	// READ (buscar por código)
	public Proyecto buscarProyectoPorCodigo(String codigo) {
		for (int i = 0; i < cantidad; i++) {
			if (proyectos[i].getCodigoProyecto().equalsIgnoreCase(codigo)) {
				return proyectos[i];
			}
		}
		return null;
	}

	// UPDATE (cambiar estado)
	public boolean cambiarEstadoProyecto(String codigoProyecto, String nuevoEstado) {
		Proyecto proyecto = buscarProyectoPorCodigo(codigoProyecto);
		if (proyecto == null)
			return false;
		proyecto.cambiarEstadoDeProyecto(nuevoEstado);
		return true;
	}

	// DELETE
	public boolean eliminarProyecto(String codigo) {
		int indice = -1;
		for (int i = 0; i < cantidad; i++) {
			if (proyectos[i].getCodigoProyecto().equalsIgnoreCase(codigo)) {
				indice = i;
				break;
			}
		}
		if (indice == -1)
			return false;

		for (int i = indice; i < cantidad - 1; i++) {
			proyectos[i] = proyectos[i + 1];
		}
		proyectos[cantidad - 1] = null;
		cantidad--;
		return true;
	}

	// Consulta de ingresos por fecha específica
	// Recorre los proyectos y suma el valor total de aquellos cuya fecha de
	// solicitud coincida con la fecha consultada
	public double consultarIngresosPorFecha(LocalDate fechaConsultada) {
		double totalIngresos = 0.0;
		for (int i = 0; i < cantidad; i++) {
			if (proyectos[i].getFechaSolicitud().equals(fechaConsultada)) {
				totalIngresos += proyectos[i].getValorTotal();
			}
		}
		return totalIngresos;
	}

	public Proyecto[] getProyectos() {
		return proyectos;
	}

	public int getCantidad() {
		return cantidad;
	}
}