package parcial1;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Menu {
	private GestorGeneral gestorGeneral;

	public Menu(GestorGeneral gestorGeneral) {
		this.gestorGeneral = gestorGeneral;
	}

	// ==================== MENÚ PRINCIPAL ====================
	public void mostrarMenuPrincipal() {
		boolean continuar = true;
		while (continuar) {
			String opciones = "=== SISTEMA DEVPLUS ===\n\n"
					+ "1. Gestionar Clientes\n"
					+ "2. Gestionar Desarrolladores\n"
					+ "3. Gestionar Servicios\n"
					+ "4. Gestionar Proyectos\n"
					+ "5. Consultar Cliente por Teléfono (Número Perfecto)\n"
					+ "6. Consultar Ingresos por Fecha\n"
					+ "7. Salir\n\n"
					+ "Seleccione una opción:";

			String opcion = JOptionPane.showInputDialog(null, opciones);

			if (opcion == null) {
				continuar = false;
				break;
			}

			switch (opcion) {
				case "1":
					menuClientes();
					break;
				case "2":
					menuDesarrolladores();
					break;
				case "3":
					menuServicios();
					break;
				case "4":
					menuProyectos();
					break;
				case "5":
					menuConsultarClientePerfecto();
					break;
				case "6":
					menuConsultarIngresosPorFecha();
					break;
				case "7":
					JOptionPane.showMessageDialog(null, "¡Gracias por usar el sistema!");
					continuar = false;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
			}
		}
	}

	// ==================== MENÚ CLIENTES ====================
	private void menuClientes() {
		boolean continuar = true;
		while (continuar) {
			String opciones = "=== GESTIÓN DE CLIENTES ===\n\n"
					+ "1. Crear Cliente\n"
					+ "2. Listar Clientes\n"
					+ "3. Buscar Cliente por Documento\n"
					+ "4. Actualizar Cliente\n"
					+ "5. Eliminar Cliente\n"
					+ "6. Volver al Menú Principal\n\n"
					+ "Seleccione una opción:";

			String opcion = JOptionPane.showInputDialog(null, opciones);

			if (opcion == null) {
				continuar = false;
				break;
			}

			switch (opcion) {
				case "1":
					crearCliente();
					break;
				case "2":
					listarClientes();
					break;
				case "3":
					buscarClientePorDocumento();
					break;
				case "4":
					actualizarCliente();
					break;
				case "5":
					eliminarCliente();
					break;
				case "6":
					continuar = false;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
			}
		}
	}

	private void crearCliente() {
		String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre completo o razón social:");
		if (nombre == null)
			return;

		String documento = JOptionPane.showInputDialog(null, "Ingrese documento o NIT:");
		if (documento == null)
			return;

		String telefono = JOptionPane.showInputDialog(null, "Ingrese teléfono:");
		if (telefono == null)
			return;

		String correo = JOptionPane.showInputDialog(null, "Ingrese correo electrónico:");
		if (correo == null)
			return;

		String pais = JOptionPane.showInputDialog(null, "Ingrese país de procedencia:");
		if (pais == null)
			return;

		Cliente nuevoCliente = new Cliente(nombre, documento, telefono, correo, pais);
		boolean resultado = gestorGeneral.getGestorClientes().crear(nuevoCliente);

		if (resultado) {
			JOptionPane.showMessageDialog(null, "Cliente creado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo crear el cliente. Límite alcanzado.");
		}
	}

	private void listarClientes() {
		String clientes = gestorGeneral.getGestorClientes().mostrarClientes();
		JOptionPane.showMessageDialog(null, clientes);
	}

	private void buscarClientePorDocumento() {
		String documento = JOptionPane.showInputDialog(null, "Ingrese documento o NIT a buscar:");
		if (documento == null)
			return;

		Cliente cliente = gestorGeneral.getGestorClientes().buscarClientePorDocumentoLegal(documento);
		if (cliente != null) {
			JOptionPane.showMessageDialog(null, cliente.toString());
		} else {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
		}
	}

	private void actualizarCliente() {
		String documento = JOptionPane.showInputDialog(null, "Ingrese documento del cliente a actualizar:");
		if (documento == null)
			return;

		String nuevoTelefono = JOptionPane.showInputDialog(null, "Ingrese nuevo teléfono:");
		if (nuevoTelefono == null)
			return;

		String nuevoCorreo = JOptionPane.showInputDialog(null, "Ingrese nuevo correo:");
		if (nuevoCorreo == null)
			return;

		boolean resultado = gestorGeneral.getGestorClientes().actualizarInformacionCliente(documento,
				nuevoTelefono, nuevoCorreo);

		if (resultado) {
			JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
		}
	}

	private void eliminarCliente() {
		String documento = JOptionPane.showInputDialog(null, "Ingrese documento del cliente a eliminar:");
		if (documento == null)
			return;

		boolean resultado = gestorGeneral.getGestorClientes().eliminarCliente(documento);

		if (resultado) {
			JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
		}
	}

	// ==================== MENÚ DESARROLLADORES ====================
	private void menuDesarrolladores() {
		boolean continuar = true;
		while (continuar) {
			String opciones = "=== GESTIÓN DE DESARROLLADORES ===\n\n"
					+ "1. Crear Desarrollador\n"
					+ "2. Listar Desarrolladores\n"
					+ "3. Buscar Desarrollador por Código\n"
					+ "4. Actualizar Estado Desarrollador\n"
					+ "5. Eliminar Desarrollador\n"
					+ "6. Volver al Menú Principal\n\n"
					+ "Seleccione una opción:";

			String opcion = JOptionPane.showInputDialog(null, opciones);

			if (opcion == null) {
				continuar = false;
				break;
			}

			switch (opcion) {
				case "1":
					crearDesarrollador();
					break;
				case "2":
					listarDesarrolladores();
					break;
				case "3":
					buscarDesarrolladorPorCodigo();
					break;
				case "4":
					actualizarEstadoDesarrollador();
					break;
				case "5":
					eliminarDesarrollador();
					break;
				case "6":
					continuar = false;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
			}
		}
	}

	private void crearDesarrollador() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código del desarrollador:");
		if (codigo == null)
			return;

		String equipo = JOptionPane.showInputDialog(null, "Ingrese equipo de trabajo:");
		if (equipo == null)
			return;

		String nivel = JOptionPane.showInputDialog(null, "Ingrese nivel (Junior/Semisenior/Senior):");
		if (nivel == null)
			return;

		String maxProyectos = JOptionPane.showInputDialog(null, "Ingrese cantidad máxima de proyectos simultáneos:");
		if (maxProyectos == null)
			return;

		String tarifa = JOptionPane.showInputDialog(null, "Ingrese tarifa por día:");
		if (tarifa == null)
			return;

		String estado = JOptionPane.showInputDialog(null, "Ingrese estado (Disponible/Asignado/Ocupado/En capacitación):");
		if (estado == null)
			return;

		try {
			int max = Integer.parseInt(maxProyectos);
			double tarifaPorDia = Double.parseDouble(tarifa);

			Desarrollador nuevoDesarrollador = new Desarrollador(codigo, equipo, nivel, max, tarifaPorDia, estado);
			boolean resultado = gestorGeneral.getGestorDesarrolladores().crear(nuevoDesarrollador);

			if (resultado) {
				JOptionPane.showMessageDialog(null, "Desarrollador creado exitosamente.");
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo crear el desarrollador. Límite alcanzado.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Ingrese números válidos.");
		}
	}

	private void listarDesarrolladores() {
		String desarrolladores = gestorGeneral.getGestorDesarrolladores().mostrarDesarrolladores();
		JOptionPane.showMessageDialog(null, desarrolladores);
	}

	private void buscarDesarrolladorPorCodigo() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código a buscar:");
		if (codigo == null)
			return;

		Desarrollador desarrollador = gestorGeneral.getGestorDesarrolladores().buscarDesarrolladorPorCodigo(codigo);
		if (desarrollador != null) {
			JOptionPane.showMessageDialog(null, desarrollador.toString());
		} else {
			JOptionPane.showMessageDialog(null, "Desarrollador no encontrado.");
		}
	}

	private void actualizarEstadoDesarrollador() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código del desarrollador:");
		if (codigo == null)
			return;

		String nuevoEstado = JOptionPane.showInputDialog(null,
				"Ingrese nuevo estado (Disponible/Asignado/Ocupado/En capacitación):");
		if (nuevoEstado == null)
			return;

		boolean resultado = gestorGeneral.getGestorDesarrolladores().actualizarEstadoDesarrollador(codigo, nuevoEstado);

		if (resultado) {
			JOptionPane.showMessageDialog(null, "Desarrollador actualizado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Desarrollador no encontrado.");
		}
	}

	private void eliminarDesarrollador() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código del desarrollador a eliminar:");
		if (codigo == null)
			return;

		boolean resultado = gestorGeneral.getGestorDesarrolladores().eliminarDesarrollador(codigo);

		if (resultado) {
			JOptionPane.showMessageDialog(null, "Desarrollador eliminado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Desarrollador no encontrado.");
		}
	}

	// ==================== MENÚ SERVICIOS ====================
	private void menuServicios() {
		boolean continuar = true;
		while (continuar) {
			String opciones = "=== GESTIÓN DE SERVICIOS ===\n\n"
					+ "1. Crear Servicio\n"
					+ "2. Listar Servicios\n"
					+ "3. Buscar Servicio por Código\n"
					+ "4. Actualizar Servicio\n"
					+ "5. Eliminar Servicio\n"
					+ "6. Volver al Menú Principal\n\n"
					+ "Seleccione una opción:";

			String opcion = JOptionPane.showInputDialog(null, opciones);

			if (opcion == null) {
				continuar = false;
				break;
			}

			switch (opcion) {
				case "1":
					crearServicio();
					break;
				case "2":
					listarServicios();
					break;
				case "3":
					buscarServicioPorCodigo();
					break;
				case "4":
					actualizarServicio();
					break;
				case "5":
					eliminarServicio();
					break;
				case "6":
					continuar = false;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
			}
		}
	}

	private void crearServicio() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código del servicio:");
		if (codigo == null)
			return;

		String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del servicio:");
		if (nombre == null)
			return;

		String descripcion = JOptionPane.showInputDialog(null, "Ingrese descripción:");
		if (descripcion == null)
			return;

		String precio = JOptionPane.showInputDialog(null, "Ingrese precio:");
		if (precio == null)
			return;

		String disponible = JOptionPane.showInputDialog(null, "¿Disponible? (true/false):");
		if (disponible == null)
			return;

		try {
			double precioServicio = Double.parseDouble(precio);
			boolean estaDisponible = Boolean.parseBoolean(disponible);

			ServicioAdicional nuevoServicio = new ServicioAdicional(codigo, nombre, descripcion, precioServicio,
					estaDisponible);
			boolean resultado = gestorGeneral.getGestorServicios().crear(nuevoServicio);

			if (resultado) {
				JOptionPane.showMessageDialog(null, "Servicio creado exitosamente.");
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo crear el servicio. Límite alcanzado.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Ingrese números válidos.");
		}
	}

	private void listarServicios() {
		String servicios = gestorGeneral.getGestorServicios().mostrarServicios();
		JOptionPane.showMessageDialog(null, servicios);
	}

	private void buscarServicioPorCodigo() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código a buscar:");
		if (codigo == null)
			return;

		ServicioAdicional servicio = gestorGeneral.getGestorServicios().buscarServicioPorCodigo(codigo);
		if (servicio != null) {
			JOptionPane.showMessageDialog(null, servicio.toString());
		} else {
			JOptionPane.showMessageDialog(null, "Servicio no encontrado.");
		}
	}

	private void actualizarServicio() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código del servicio:");
		if (codigo == null)
			return;

		String nombre = JOptionPane.showInputDialog(null, "Ingrese nuevo nombre:");
		if (nombre == null)
			return;

		String descripcion = JOptionPane.showInputDialog(null, "Ingrese nueva descripción:");
		if (descripcion == null)
			return;

		String precio = JOptionPane.showInputDialog(null, "Ingrese nuevo precio:");
		if (precio == null)
			return;

		String disponible = JOptionPane.showInputDialog(null, "¿Disponible? (true/false):");
		if (disponible == null)
			return;

		try {
			double precioServicio = Double.parseDouble(precio);
			boolean estaDisponible = Boolean.parseBoolean(disponible);

			boolean resultado = gestorGeneral.getGestorServicios().actualizarServicio(codigo, nombre, descripcion,
					precioServicio, estaDisponible);

			if (resultado) {
				JOptionPane.showMessageDialog(null, "Servicio actualizado exitosamente.");
			} else {
				JOptionPane.showMessageDialog(null, "Servicio no encontrado.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Ingrese números válidos.");
		}
	}

	private void eliminarServicio() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código del servicio a eliminar:");
		if (codigo == null)
			return;

		boolean resultado = gestorGeneral.getGestorServicios().eliminarServicio(codigo);

		if (resultado) {
			JOptionPane.showMessageDialog(null, "Servicio eliminado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Servicio no encontrado.");
		}
	}

	// ==================== MENÚ PROYECTOS ====================
	private void menuProyectos() {
		boolean continuar = true;
		while (continuar) {
			String opciones = "=== GESTIÓN DE PROYECTOS ===\n\n"
					+ "1. Crear Proyecto\n"
					+ "2. Listar Proyectos\n"
					+ "3. Buscar Proyecto por Código\n"
					+ "4. Asignar Desarrollador a Proyecto\n"
					+ "5. Agregar Servicio a Proyecto\n"
					+ "6. Calcular Valor Total del Proyecto\n"
					+ "7. Cambiar Estado del Proyecto\n"
					+ "8. Eliminar Proyecto\n"
					+ "9. Volver al Menú Principal\n\n"
					+ "Seleccione una opción:";

			String opcion = JOptionPane.showInputDialog(null, opciones);

			if (opcion == null) {
				continuar = false;
				break;
			}

			switch (opcion) {
				case "1":
					crearProyecto();
					break;
				case "2":
					listarProyectos();
					break;
				case "3":
					buscarProyectoPorCodigo();
					break;
				case "4":
					asignarDesarrolladorAProyecto();
					break;
				case "5":
					agregarServicioAProyecto();
					break;
				case "6":
					calcularValorTotalProyecto();
					break;
				case "7":
					cambiarEstadoProyecto();
					break;
				case "8":
					eliminarProyecto();
					break;
				case "9":
					continuar = false;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
			}
		}
	}

	private void crearProyecto() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código del proyecto:");
		if (codigo == null)
			return;

		String documentoCliente = JOptionPane.showInputDialog(null, "Ingrese documento del cliente:");
		if (documentoCliente == null)
			return;

		Cliente cliente = gestorGeneral.getGestorClientes().buscarClientePorDocumentoLegal(documentoCliente);
		if (cliente == null) {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
			return;
		}

		String fechaSolicitud = JOptionPane.showInputDialog(null, "Ingrese fecha de solicitud (yyyy-MM-dd):");
		if (fechaSolicitud == null)
			return;

		String fechaInicio = JOptionPane.showInputDialog(null, "Ingrese fecha de inicio (yyyy-MM-dd):");
		if (fechaInicio == null)
			return;

		String fechaEntrega = JOptionPane.showInputDialog(null, "Ingrese fecha de entrega (yyyy-MM-dd):");
		if (fechaEntrega == null)
			return;

		String metodoPago = JOptionPane.showInputDialog(null,
				"Ingrese método de pago (Tarjeta de crédito/Transferencia bancaria/Efectivo):");
		if (metodoPago == null)
			return;

		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fSolicitud = LocalDate.parse(fechaSolicitud, formato);
			LocalDate fInicio = LocalDate.parse(fechaInicio, formato);
			LocalDate fEntrega = LocalDate.parse(fechaEntrega, formato);

			Proyecto nuevoProyecto = new Proyecto(codigo, fSolicitud, fInicio, fEntrega, metodoPago, cliente);
			boolean resultado = gestorGeneral.getGestorProyectos().crear(nuevoProyecto);

			if (resultado) {
				JOptionPane.showMessageDialog(null, "Proyecto creado exitosamente.");
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo crear el proyecto. Límite alcanzado.");
			}
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Formato de fecha inválido. Use yyyy-MM-dd");
		}
	}

	private void listarProyectos() {
		String proyectos = gestorGeneral.getGestorProyectos().mostrarProyectos();
		JOptionPane.showMessageDialog(null, proyectos);
	}

	private void buscarProyectoPorCodigo() {
		String codigo = JOptionPane.showInputDialog(null, "Ingrese código a buscar:");
		if (codigo == null)
			return;

		Proyecto proyecto = gestorGeneral.getGestorProyectos().buscarProyectoPorCodigo(codigo);
		if (proyecto != null) {
			JOptionPane.showMessageDialog(null, proyecto.toString());
		} else {
			JOptionPane.showMessageDialog(null, "Proyecto no encontrado.");
		}
	}

	private void asignarDesarrolladorAProyecto() {
		String codigoProyecto = JOptionPane.showInputDialog(null, "Ingrese código del proyecto:");
		if (codigoProyecto == null)
			return;

		Proyecto proyecto = gestorGeneral.getGestorProyectos().buscarProyectoPorCodigo(codigoProyecto);
		if (proyecto == null) {
			JOptionPane.showMessageDialog(null, "Proyecto no encontrado.");
			return;
		}

		String codigoDesarrollador = JOptionPane.showInputDialog(null, "Ingrese código del desarrollador:");
		if (codigoDesarrollador == null)
			return;

		Desarrollador desarrollador = gestorGeneral.getGestorDesarrolladores()
				.buscarDesarrolladorPorCodigo(codigoDesarrollador);
		if (desarrollador == null) {
			JOptionPane.showMessageDialog(null, "Desarrollador no encontrado.");
			return;
		}

		boolean resultado = proyecto.asignarDesarrollador(desarrollador);
		if (resultado) {
			JOptionPane.showMessageDialog(null, "Desarrollador asignado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo asignar el desarrollador (no disponible o límite alcanzado).");
		}
	}

	private void agregarServicioAProyecto() {
		String codigoProyecto = JOptionPane.showInputDialog(null, "Ingrese código del proyecto:");
		if (codigoProyecto == null)
			return;

		Proyecto proyecto = gestorGeneral.getGestorProyectos().buscarProyectoPorCodigo(codigoProyecto);
		if (proyecto == null) {
			JOptionPane.showMessageDialog(null, "Proyecto no encontrado.");
			return;
		}

		String codigoServicio = JOptionPane.showInputDialog(null, "Ingrese código del servicio:");
		if (codigoServicio == null)
			return;

		ServicioAdicional servicio = gestorGeneral.getGestorServicios().buscarServicioPorCodigo(codigoServicio);
		if (servicio == null) {
			JOptionPane.showMessageDialog(null, "Servicio no encontrado.");
			return;
		}

		boolean resultado = proyecto.agregarServicio(servicio);
		if (resultado) {
			JOptionPane.showMessageDialog(null, "Servicio agregado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo agregar el servicio (límite alcanzado).");
		}
	}

	private void calcularValorTotalProyecto() {
		String codigoProyecto = JOptionPane.showInputDialog(null, "Ingrese código del proyecto:");
		if (codigoProyecto == null)
			return;

		Proyecto proyecto = gestorGeneral.getGestorProyectos().buscarProyectoPorCodigo(codigoProyecto);
		if (proyecto == null) {
			JOptionPane.showMessageDialog(null, "Proyecto no encontrado.");
			return;
		}

		double valorTotal = proyecto.calcularValorTotal();
		JOptionPane.showMessageDialog(null, "Valor total del proyecto: $" + valorTotal);
	}

	private void cambiarEstadoProyecto() {
		String codigoProyecto = JOptionPane.showInputDialog(null, "Ingrese código del proyecto:");
		if (codigoProyecto == null)
			return;

		String nuevoEstado = JOptionPane.showInputDialog(null,
				"Ingrese nuevo estado (Pendiente/Confirmado/En curso/Finalizado/Cancelado):");
		if (nuevoEstado == null)
			return;

		boolean resultado = gestorGeneral.getGestorProyectos().cambiarEstadoProyecto(codigoProyecto, nuevoEstado);

		if (resultado) {
			JOptionPane.showMessageDialog(null, "Estado del proyecto actualizado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Proyecto no encontrado.");
		}
	}

	private void eliminarProyecto() {
		String codigoProyecto = JOptionPane.showInputDialog(null, "Ingrese código del proyecto a eliminar:");
		if (codigoProyecto == null)
			return;

		boolean resultado = gestorGeneral.getGestorProyectos().eliminarProyecto(codigoProyecto);

		if (resultado) {
			JOptionPane.showMessageDialog(null, "Proyecto eliminado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Proyecto no encontrado.");
		}
	}

	// ==================== MENÚ CONSULTA NÚMERO PERFECTO ====================
	private void menuConsultarClientePerfecto() {
		String telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono del cliente:");
		if (telefono == null)
			return;

		String resultado = gestorGeneral.consultarClientePorTelefonoYValidarPerfecto(telefono);
		JOptionPane.showMessageDialog(null, resultado);
	}

	// ==================== MENÚ INGRESOS POR FECHA ====================
	private void menuConsultarIngresosPorFecha() {
		String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha (yyyy-MM-dd):");
		if (fecha == null)
			return;

		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaConsultada = LocalDate.parse(fecha, formato);
			double ingresos = gestorGeneral.consultarIngresosPorFecha(fechaConsultada);

			JOptionPane.showMessageDialog(null, "Ingresos en la fecha " + fecha + ": $" + ingresos);
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Formato de fecha inválido. Use yyyy-MM-dd");
		}
	}
}