package edu.uniandes.data;

public class Vinculo {


	private String idCuentaJefe;
	private String idCuentaEmpleado;
	private int idValor;
	private String frecuencia;
	
	public Vinculo(String idCuentaJefe, String idCuentaEmpleado,
			int idValor, String frecuencia) {
		super();
		this.idCuentaJefe = idCuentaJefe;
		this.idCuentaEmpleado = idCuentaEmpleado;
		this.idValor = idValor;
		this.frecuencia = frecuencia;
	}
	
	public String getIdCuentaJefe() {
		return idCuentaJefe;
	}

	public void setIdCuentaJefe(String idCuentaJefe) {
		this.idCuentaJefe = idCuentaJefe;
	}

	public String getIdCuentaEmpleado() {
		return idCuentaEmpleado;
	}

	public void setIdCuentaEmpleado(String idCuentaEmpleado) {
		this.idCuentaEmpleado = idCuentaEmpleado;
	}

	public int getIdValor() {
		return idValor;
	}

	public void setIdValor(int idValor) {
		this.idValor = idValor;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}


}
