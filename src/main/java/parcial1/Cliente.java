package parcial1;

public class Cliente {
	private String nombreLegal;
	private String documentoLegal;
	private String telefono;
	private String correoElectronico;
	private String paisProcedencia;
	private int cantidadProyectos; // contador de proyectos para identificar si es cliente regular

	public Cliente(String nombreCompleto, String documentoONit, String telefono,
			String correoElectronico, String paisProcedencia) {
		this.nombreLegal = nombreCompleto;
		this.documentoLegal = documentoONit;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.paisProcedencia = paisProcedencia;
		this.cantidadProyectos = 0;
	}

	public String getNombreLegal() {
		return nombreLegal;
	}

	public void setNombreLegal(String nombreCompleto) {
		this.nombreLegal = nombreCompleto;
	}

	public String getDocumentoLegal() {
		return documentoLegal;
	}

	public void setDocumentoLegal(String documentoONit) {
		this.documentoLegal = documentoONit;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPaisProcedencia() {
		return paisProcedencia;
	}

	public void setPaisProcedencia(String paisProcedencia) {
		this.paisProcedencia = paisProcedencia;
	}

	// Se llama cada vez que a este cliente se le crea un proyecto nuevo
	public void incrementarProyectos() {
		cantidadProyectos++;
	}

	public int getCantidadProyectos() {
		return cantidadProyectos;
	}

	// Regla para determinar si el cliente es frecuente y aplicar descuentos
	public boolean esClienteFrecuente() {
		return cantidadProyectos > 3;
	}

	@Override
	public String toString() {
		return "Cliente: " + nombreLegal + " | Doc/NIT: " + documentoLegal +
				" | Tel: " + telefono + " | Pais: " + paisProcedencia +
				" | Proyectos: " + cantidadProyectos;
	}
}