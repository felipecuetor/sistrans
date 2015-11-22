package edu.uniandes.data;

import java.util.ArrayList;

public class Cuenta {

	private String id;
	private int saldo;
	private String tipoCuenta;
	private String moneda_Id;
	private boolean estaCerrado;
	private Oficina oficina;
	private ArrayList<Transaccion> transacciones;

	public Cuenta(String id, int saldo, String tipoCuenta, String moneda_Id,
			boolean estaCerrado, Oficina oficina) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.tipoCuenta = tipoCuenta;
		this.moneda_Id = moneda_Id;
		this.estaCerrado = estaCerrado;
		this.oficina = oficina;
		this.transacciones = new ArrayList<Transaccion>();
	}

	public String getId() {
		return id;
	}

	public int getSaldo() {
		return saldo;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public String getMonedaId() {
		return moneda_Id;
	}

	public boolean isEstaCerrado() {
		return estaCerrado;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public ArrayList<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(ArrayList<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

}
