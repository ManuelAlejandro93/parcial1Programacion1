package parcial1;

public class GestorServicios {
	public static final int MAX_SERVICIOS = 50;

	private ServicioAdicional[] servicios;
	private int cantidad;

	public GestorServicios() {
		servicios = new ServicioAdicional[MAX_SERVICIOS];
		cantidad = 0;
	}

	// CREATE - crear
	public boolean crear(ServicioAdicional nuevoServicio) {
		if (cantidad >= MAX_SERVICIOS)
			return false;
		servicios[cantidad] = nuevoServicio;
		cantidad++;
		return true;
	}

	// READ (listar todos como texto)
	public String mostrarServicios() {
		if (cantidad == 0)
			return "No hay servicios registrados.";
		String resultado = "";
		for (int i = 0; i < cantidad; i++) {
			resultado = resultado + servicios[i].toString() + "\n";
		}
		return resultado;
	}

	// READ (buscar por código)
	public ServicioAdicional buscarServicioPorCodigo(String codigo) {
		for (int i = 0; i < cantidad; i++) {
			if (servicios[i].getCodigo().equalsIgnoreCase(codigo)) {
				return servicios[i];
			}
		}
		return null;
	}

	// UPDATE
	public boolean actualizarServicio(String codigo, String nuevoNombre,
			String nuevaDescripcion, double nuevoPrecio, boolean nuevoEstado) {
		ServicioAdicional servicio = buscarServicioPorCodigo(codigo);
		if (servicio == null)
			return false;
		servicio.setNombre(nuevoNombre);
		servicio.setDescripcion(nuevaDescripcion);
		servicio.setPrecio(nuevoPrecio);
		servicio.setDisponible(nuevoEstado);
		return true;
	}

	// DELETE
	public boolean eliminarServicio(String codigo) {
		int indice = -1;
		for (int i = 0; i < cantidad; i++) {
			if (servicios[i].getCodigo().equalsIgnoreCase(codigo)) {
				indice = i;
				break;
			}
		}
		if (indice == -1)
			return false;

		for (int i = indice; i < cantidad - 1; i++) {
			servicios[i] = servicios[i + 1];
		}
		servicios[cantidad - 1] = null;
		cantidad--;
		return true;
	}

	public ServicioAdicional[] getServicios() {
		return servicios;
	}

	public int getCantidad() {
		return cantidad;
	}
}