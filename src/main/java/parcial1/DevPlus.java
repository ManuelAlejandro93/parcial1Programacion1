package parcial1;

public class DevPlus {
	private String nombreLegal;
	private String idLegal;
	private String direccion;
	private String telefono;
	private String paginaWeb;

	public DevPlus(String nombreComercial, String idLegal, String direccion,
			String telefono, String paginaWeb) {
		this.nombreLegal = nombreComercial;
		this.idLegal = idLegal;
		this.direccion = direccion;
		this.telefono = telefono;
		this.paginaWeb = paginaWeb;
	}

	public String getNombreLegal() {
		return nombreLegal;
	}

	public void setNombreLegal(String nombreComercial) {
		this.nombreLegal = nombreComercial;
	}

	public String getIdLegal() {
		return idLegal;
	}

	public void setIdLegal(String nit) {
		this.idLegal = nit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
}