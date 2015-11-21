package edu.uniandes.data;


public class Transaccion {
	
	private String id;
	private String cantidad;
	private Date fechaInicio;
	private String done;
	private String tipo;
	private Cuenta cuenta_Origen;
	private Cuenta cuenta_Destino;
	private String cambio;
	private String interes;
	private int numeroCuotas;
	private Date fechaPago;
	private int valorCuota;
	private int saldoPendiente;
	
	public Transaccion(String id, String cantidad, Date fechaInicio, String done, String tipo, Cuenta cuenta_Origen, Cuenta cuenta_Destino, String cambio, String interes, int numeroCuotas,
			Date fechaPago, int valorCuota, int saldoPendiente) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.fechaInicio = fechaInicio;
		this.done = done;
		this.tipo = tipo;
		this.cuenta_Origen = cuenta_Origen;
		this.cuenta_Destino = cuenta_Destino;
		this.cambio = cambio;
		this.interes = interes;
		this.numeroCuotas = numeroCuotas;
		this.fechaPago = fechaPago;
		this.valorCuota = valorCuota;
		this.saldoPendiente = saldoPendiente;
	}

	public String getId() {
		return id;
	}

	public String getCantidad() {
		return cantidad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public String getDone() {
		return done;
	}

	public String getTipo() {
		return tipo;
	}

	public Cuenta getCuenta_Origen() {
		return cuenta_Origen;
	}

	public Cuenta getCuenta_Destino() {
		return cuenta_Destino;
	}

	public String getCambio() {
		return cambio;
	}

	public String getInteres() {
		return interes;
	}

	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public int getValorCuota() {
		return valorCuota;
	}

	public int getSaldoPendiente() {
		return saldoPendiente;
	}
	
	
	
}
