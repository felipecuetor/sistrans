package edu.uniandes.data;

public class Requerimiento3 {
	private String tipo;
	private String oficina_Id;
	private String promedio;
	private String numeroVeces;
	
	public Requerimiento3(String tipo, String oficina_Id, String promedio, String numeroVeces) {
		super();
		this.tipo = tipo;
		this.oficina_Id = oficina_Id;
		this.promedio = promedio;
		this.numeroVeces = numeroVeces;
	}
	
	public String getTipo() {
		return tipo;
	}
	public String getOficina_Id() {
		return oficina_Id;
	}
	public String getPromedio() {
		return promedio;
	}
	public String getNumeroVeces() {
		return numeroVeces;
	}
	
}
