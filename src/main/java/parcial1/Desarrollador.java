package parcial1;

import java.time.LocalDate;

public class Desarrollador {
	private String codigoDesarrollador;
	private String equipoTrabajo;
	private String nivel; // Junior, Semisenior, Senior
	private int maxProyectosSimultaneos;
	private double tarifaPorDia;
	private String estado; // Disponible, Asignado, Ocupado, En capacitacion

	public Desarrollador(String codigo, String equipoTrabajo, String nivel,
			int maxProyectosSimultaneos, double tarifaPorDia, String estado) {
		this.codigoDesarrollador = codigo;
		this.equipoTrabajo = equipoTrabajo;
		this.nivel = nivel;
		this.maxProyectosSimultaneos = maxProyectosSimultaneos;
		this.tarifaPorDia = tarifaPorDia;
		this.estado = estado;
	}

	public String getCodigoDesarrollador() {
		return codigoDesarrollador;
	}

	public void setCodigoDesarrollador(String codigo) {
		this.codigoDesarrollador = codigo;
	}

	public String getEquipoTrabajo() {
		return equipoTrabajo;
	}

	public void setEquipoTrabajo(String equipoTrabajo) {
		this.equipoTrabajo = equipoTrabajo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getMaxProyectosSimultaneos() {
		return maxProyectosSimultaneos;
	}

	public void setMaxProyectosSimultaneos(int maxProyectosSimultaneos) {
		this.maxProyectosSimultaneos = maxProyectosSimultaneos;
	}

	public double getTarifaPorDia() {
		return tarifaPorDia;
	}

	public void setTarifaPorDia(double tarifaPorDia) {
		this.tarifaPorDia = tarifaPorDia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// Valida si el desarrollador está disponible en un rango de fechas específico
	// Verifica que el estado sea "Disponible" y que las fechas sean válidas
	public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaEntrega) {
		// Validación 1: Verificar que el estado sea "Disponible"
		if (!this.estado.equalsIgnoreCase("Disponible")) {
			return false;
		}

		// Validación 2: Verificar que las fechas no sean nulas
		if (fechaInicio == null || fechaEntrega == null) {
			return false;
		}

		// Validación 3: Verificar que la fecha de entrega sea posterior a la de inicio
		if (fechaEntrega.isBefore(fechaInicio)) {
			return false;
		}

		// Si pasa todas las validaciones, el desarrollador está disponible
		return true;
	}

	@Override
	public String toString() {
		return "Cod: " + codigoDesarrollador + " | Equipo: " + equipoTrabajo + " | Nivel: " + nivel +
				" | Tarifa/dia: $" + String.format("%.2f", tarifaPorDia) + " | Estado: " + estado;
	}
}