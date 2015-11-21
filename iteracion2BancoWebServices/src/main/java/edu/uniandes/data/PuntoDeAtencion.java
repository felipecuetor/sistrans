package edu.uniandes.data;

public class PuntoDeAtencion {
	
	private String tipo;
	private String localizacion;
	private Oficina oficina;
	
	public PuntoDeAtencion(String tipo, String localizacion, Oficina oficina) {
		super();
		this.tipo = tipo;
		this.localizacion = localizacion;
		this.oficina = oficina;
	}
	
	public String getTipo() {
		return tipo;
	}
	public String getLocalizacion() {
		return localizacion;
	}
	public Oficina getOficina() {
		return oficina;
	}
}
