package parcial1;

import java.time.LocalDate;

public class GestorGeneral {
	private DevPlus empresa;
	private GestorClientes gestorClientes;
	private GestorDesarrolladores gestorDesarrolladores;
	private GestorProyectos gestorProyectos;
	private GestorServicios gestorServicios;

	public GestorGeneral(DevPlus empresa) {
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

		long sumaDivisores = 0;

		// Encuentra los divisores propios (sin incluir el número mismo)
		for (long i = 1; i < numero; i++) {
			if (numero % i == 0) {
				sumaDivisores += i;
			}
		}

		// Compara si la suma de divisores es igual al número
		return sumaDivisores == numero;
	}

	// Extrae solo los dígitos de una cadena de teléfono
	// Ejemplo: "+57-310-1234567" → "573101234567"
	private String extraerDigitos(String telefono) {
		return telefono.replaceAll("[^0-9]", "");
	}

	// Busca un cliente por teléfono y valida si el número es perfecto
	public String consultarClientePorTelefonoYValidarPerfecto(String telefono) {
		Cliente cliente = gestorClientes.buscarClientePorTelefono(telefono);

		if (cliente == null) {
			return "Cliente no encontrado con ese teléfono.";
		}

		// Extrae los dígitos del teléfono
		String soloDigitos = extraerDigitos(telefono);

		// Convierte a número largo
		long numeroTelefono = Long.parseLong(soloDigitos);

		// Valida si es número perfecto
		boolean esPerfecto = esNumeroPerfecto(numeroTelefono);

		// Construye la respuesta
		String resultado = "Cliente encontrado:\n";
		resultado += cliente.toString() + "\n";
		resultado += "Número de teléfono: " + numeroTelefono + "\n";
		resultado += "¿Es número perfecto?: " + (esPerfecto ? "SÍ" : "NO");

		return resultado;
	}

	// ==================== CONSULTA DE INGRESOS POR FECHA ====================

	// Delega al gestor de proyectos la consulta de ingresos
	public double consultarIngresosPorFecha(LocalDate fecha) {
		return gestorProyectos.consultarIngresosPorFecha(fecha);
	}
}