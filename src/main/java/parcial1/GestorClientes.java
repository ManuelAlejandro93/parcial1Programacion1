package parcial1;

// Clase encargada del CRUD de clientes usando un ARREGLO de tamano fijo
public class GestorClientes {
	public static final int MAX_CLIENTES = 100;

	private Cliente[] clientes;
	private int cantidad;

	public GestorClientes() {
		clientes = new Cliente[MAX_CLIENTES];
		cantidad = 0;
	}

	// CREATE - crear
	public boolean crear(Cliente nuevoCliente) {
		if (cantidad >= MAX_CLIENTES)
			return false;
		clientes[cantidad] = nuevoCliente;
		cantidad++;
		return true;
	}

	// READ (listar todos como texto, para mostrar en JOptionPane)
	// Leer
	public String mostrarClientes() {
		if (cantidad == 0)
			return "No hay clientes registrados.";
		String resultado = "";
		for (int i = 0; i < cantidad; i++) {
			resultado = resultado + clientes[i].toString() + "\n";
		}
		return resultado;
	}

	// READ (buscar por documento o NIT)
	public Cliente buscarClientePorDocumentoLegal(String documentoLegal) {
		for (int i = 0; i < cantidad; i++) {
			if (clientes[i].getDocumentoLegal().equalsIgnoreCase(documentoLegal)) {
				return clientes[i];
			}
		}
		return null;
	}

	// READ (buscar por telefono, usado en la consulta de "numero perfecto")
	public Cliente buscarClientePorTelefono(String telefono) {
		for (int i = 0; i < cantidad; i++) {
			if (clientes[i].getTelefono().equals(telefono)) {
				return clientes[i];
			}
		}
		return null;
	}

	// UPDATE
	public boolean actualizarInformacionCliente(String nuevoDocumentoLegal, String nuevoTelefono, String nuevoCorreo) {
		Cliente cliente = buscarClientePorDocumentoLegal(nuevoDocumentoLegal);
		if (cliente == null)
			return false;
		cliente.setTelefono(nuevoTelefono);
		cliente.setCorreoElectronico(nuevoCorreo);
		return true;
	}

	// DELETE (recorre el arreglo y recorre los elementos hacia la izquierda)
	public boolean eliminarCliente(String documento) {
		int indice = -1;
		for (int i = 0; i < cantidad; i++) {
			if (clientes[i].getDocumentoLegal().equalsIgnoreCase(documento)) {
				indice = i;
				break;
			}
		}
		if (indice == -1)
			return false;

		for (int i = indice; i < cantidad - 1; i++) {
			clientes[i] = clientes[i + 1];
		}
		// Se limpia la ultima posicion porque el for de arriba deja una referencia
		// duplicada del ultimo cliente (no se "mueve", se "copia"). Si no se limpia,
		// el objeto queda ocupando memoria de forma innecesaria.
		clientes[cantidad - 1] = null;
		cantidad--;
		return true;
	}

	public Cliente[] getClientes() {
		return clientes;
	}

	public int getCantidad() {
		return cantidad;
	}
}