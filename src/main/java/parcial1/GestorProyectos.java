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

		// Validar que el proyecto no sea nulo
		if (nuevoProyecto == null)
			return false;

		// Validar que el cliente del proyecto no sea nulo
		if (nuevoProyecto.getCliente() == null)
			return false;

		proyectos[cantidad] = nuevoProyecto;
		cantidad++;

		// Incrementar el contador de proyectos del cliente
		nuevoProyecto.getCliente().incrementarProyectos();

		return true;
	}

	// READ (listar todos como texto)
	public String mostrarProyectos() {
		if (cantidad == 0)
			return "No hay proyectos registrados.";
		String resultado = "";
		for (int i = 0; i < cantidad; i++) {
			resultado = resultado + (i + 1) + ". " + proyectos[i].toString() + "\n";
		}
		return resultado;
	}

	// READ (buscar por código)
	public Proyecto buscarProyectoPorCodigo(String codigo) {
		// Validar que el código no sea nulo o vacío
		if (codigo == null || codigo.trim().isEmpty())
			return null;

		for (int i = 0; i < cantidad; i++) {
			if (proyectos[i].getCodigoProyecto().equalsIgnoreCase(codigo)) {
				return proyectos[i];
			}
		}
		return null;
	}

	// UPDATE (cambiar estado)
	public boolean cambiarEstadoProyecto(String codigoProyecto, String nuevoEstado) {
		// Validar que los parámetros no sean nulos
		if (codigoProyecto == null || nuevoEstado == null)
			return false;

		Proyecto proyecto = buscarProyectoPorCodigo(codigoProyecto);
		if (proyecto == null)
			return false;

		// Validar que el nuevo estado sea válido
		if (!esEstadoValido(nuevoEstado))
			return false;

		proyecto.cambiarEstadoDeProyecto(nuevoEstado);
		return true;
	}

	// DELETE
	public boolean eliminarProyecto(String codigo) {
		// Validar que el código no sea nulo
		if (codigo == null || codigo.trim().isEmpty())
			return false;

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
		// Validar que la fecha no sea nula
		if (fechaConsultada == null)
			return 0.0;

		double totalIngresos = 0.0;
		for (int i = 0; i < cantidad; i++) {
			// Verificar que el proyecto no sea nulo y que su fecha coincida
			if (proyectos[i] != null && proyectos[i].getFechaSolicitud().equals(fechaConsultada)) {
				totalIngresos += proyectos[i].getValorTotal();
			}
		}
		return totalIngresos;
	}

	// Método auxiliar para validar que el estado sea válido
	private boolean esEstadoValido(String estado) {
		if (estado == null || estado.trim().isEmpty())
			return false;

		String estadoNormalizado = estado.toLowerCase().trim();
		return estadoNormalizado.equals("pendiente") ||
				estadoNormalizado.equals("confirmado") ||
				estadoNormalizado.equals("en curso") ||
				estadoNormalizado.equals("finalizado") ||
				estadoNormalizado.equals("cancelado");
	}

	// Método para obtener proyectos por estado
	public String mostrarProyectosPorEstado(String estado) {
		if (estado == null || estado.trim().isEmpty())
			return "Estado inválido.";

		String resultado = "";
		int contador = 0;

		for (int i = 0; i < cantidad; i++) {
			if (proyectos[i].getEstado().equalsIgnoreCase(estado)) {
				resultado = resultado + (contador + 1) + ". " + proyectos[i].toString() + "\n";
				contador++;
			}
		}

		if (contador == 0)
			return "No hay proyectos con estado: " + estado;

		return resultado;
	}

	// Método para obtener proyectos de un cliente específico
	public String mostrarProyectosPorCliente(String documentoCliente) {
		if (documentoCliente == null || documentoCliente.trim().isEmpty())
			return "Documento inválido.";

		String resultado = "";
		int contador = 0;

		for (int i = 0; i < cantidad; i++) {
			if (proyectos[i].getCliente() != null &&
					proyectos[i].getCliente().getDocumentoLegal().equalsIgnoreCase(documentoCliente)) {
				resultado = resultado + (contador + 1) + ". " + proyectos[i].toString() + "\n";
				contador++;
			}
		}

		if (contador == 0)
			return "No hay proyectos para el cliente con documento: " + documentoCliente;

		return resultado;
	}

	public Proyecto[] getProyectos() {
		return proyectos;
	}

	public int getCantidad() {
		return cantidad;
	}
}