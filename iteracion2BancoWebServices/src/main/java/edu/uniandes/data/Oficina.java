package edu.uniandes.data;

import java.util.ArrayList;

import edu.uniandes.domain.Administrador;

public class Oficina {
	
	private String nombre;
	private String direccion;
	private String telefono;
	private Administrador gerente;
	ArrayList<Cuenta> cuentas;
	
	public Oficina(String nombre, String direccion, String telefono, Administrador gerente) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = gerente;
		this.cuentas = new ArrayList<Cuenta>();
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public Administrador getGerente() {
		return gerente;
	}

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	
}
