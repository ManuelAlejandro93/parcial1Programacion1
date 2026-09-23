package parcial1;

public class GestorDesarrolladores {
	public static final int MAX_DESARROLLADORES = 50;

	private Desarrollador[] desarrolladores;
	private int cantidad;

	public GestorDesarrolladores() {
		desarrolladores = new Desarrollador[MAX_DESARROLLADORES];
		cantidad = 0;
	}

	// CREATE - crear
	public boolean crear(Desarrollador nuevoDesarrollador) {
		if (cantidad >= MAX_DESARROLLADORES)
			return false;
		desarrolladores[cantidad] = nuevoDesarrollador;
		cantidad++;
		return true;
	}

	// READ (listar todos como texto)
	public String mostrarDesarrolladores() {
		if (cantidad == 0)
			return "No hay desarrolladores registrados.";
		String resultado = "";
		for (int i = 0; i < cantidad; i++) {
			resultado = resultado + desarrolladores[i].toString() + "\n";
		}
		return resultado;
	}

	// READ (buscar por código)
	public Desarrollador buscarDesarrolladorPorCodigo(String codigo) {
		for (int i = 0; i < cantidad; i++) {
			if (desarrolladores[i].getCodigoDesarrollador().equalsIgnoreCase(codigo)) {
				return desarrolladores[i];
			}
		}
		return null;
	}

	// UPDATE
	public boolean actualizarEstadoDesarrollador(String codigo, String nuevoEstado) {
		Desarrollador desarrollador = buscarDesarrolladorPorCodigo(codigo);
		if (desarrollador == null)
			return false;
		desarrollador.setEstado(nuevoEstado);
		return true;
	}

	// DELETE
	public boolean eliminarDesarrollador(String codigo) {
		int indice = -1;
		for (int i = 0; i < cantidad; i++) {
			if (desarrolladores[i].getCodigoDesarrollador().equalsIgnoreCase(codigo)) {
				indice = i;
				break;
			}
		}
		if (indice == -1)
			return false;

		for (int i = indice; i < cantidad - 1; i++) {
			desarrolladores[i] = desarrolladores[i + 1];
		}
		desarrolladores[cantidad - 1] = null;
		cantidad--;
		return true;
	}

	public Desarrollador[] getDesarrolladores() {
		return desarrolladores;
	}

	public int getCantidad() {
		return cantidad;
	}
}