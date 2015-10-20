package edu.uniandes.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniandes.data.Cuenta;
import edu.uniandes.data.CuentaAhorro;
import edu.uniandes.data.CuentaCorriente;
import edu.uniandes.data.Oficina;
import edu.uniandes.data.Transaccion;
import edu.uniandes.domain.Usuario;

public class ConectionMYSQLUsuarioDAO {
	private String usuario = "ISIS2304091520";
	private String password = "cAeZtjShc2jP";
	private String url = "jdbc:oracle:thin:@fn3.oracle.virtual.uniandes.edu.co:1521:prod";

	public void ejecutarQuery(String query) throws SQLException {
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				ResultSet rs = prep.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString(1));
				}
			}
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, usuario, password);
	}

	public ArrayList<Cuenta> ejecutarQueryObtenerTipoCuenta(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					ArrayList<Cuenta> aCuentas = new ArrayList<Cuenta>();
					while (rs.next()) {
						System.out.println("Precesando elemento");
						String id = rs.getString("id");
						int saldo = rs.getInt("saldo");
						String tipoCuenta = rs.getString("tipocuenta");
						String monedaId = rs.getString("moneda_ID");
						String stringEstaCerrda = rs.getString("estacerrada");
						boolean estaCerrado = false;
						String stringOficina = rs.getString("oficina_Id");
						ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
						Oficina oficina = conection
								.ejecutarQueryObtenerOficina("SELECT * FROM OFICINA WHERE OFICINA.GERENTE_ID = '"
										+ stringOficina + "'");
						if (stringEstaCerrda.equals("SI")) {
							estaCerrado = true;
						}
						if (tipoCuenta.equals("Ahorros")) {
							CuentaAhorro cuenta = new CuentaAhorro(id, saldo,
									tipoCuenta, monedaId, estaCerrado, oficina);
							aCuentas.add(cuenta);
						} else {
							CuentaCorriente cuenta = new CuentaCorriente(id,
									saldo, tipoCuenta, monedaId, estaCerrado,
									oficina);
							aCuentas.add(cuenta);
						}

					}
					return aCuentas;
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public ArrayList<Usuario> ejecutarQueryObtenerCliente(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					System.out.println("Coneccion sec");
					ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
					ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
					if (rs.next()) {

						String usuario = rs.getString("usuario");
						String nombre = rs.getString("nombre");
						String cedula = rs.getString("cedula");
						String tipoCedula = rs.getString("tipoCedula");
						String cargo = rs.getString("cargo");
						String nacionalidad = rs.getString("nacionalidad");
						String direccionFisica = rs
								.getString("direccionFisica");
						String email = rs.getString("email");
						String telefono = rs.getString("telefono");
						String ciudad = rs.getString("ciudad");
						String departamento = rs.getString("departamento");
						String codigoPostal = rs.getString("codigoPostal");
						String tipoPersona = rs.getString("tipopersona");

						Usuario usuarioNuevo = new Usuario(usuario, nombre,
								cedula, tipoCedula, tryParser(cargo),
								nacionalidad, direccionFisica, email, telefono,
								ciudad, departamento, codigoPostal, tipoPersona);
						
						System.out.println("Usuario:  "+usuarioNuevo.getUsuario());

						ArrayList<Cuenta> cuentasArray = new ArrayList<Cuenta>();
						
						String id = rs.getString("id");
						String saldo = rs.getString("saldo");
						String tipoCuenta = rs.getString("tipoCuenta");
						String moneda_Id = rs.getString("moneda_id");
						String estaCerrada = rs.getString("estaCerrada");
						String oficina_id = rs.getString("oficina_id");

						Oficina oficina = null;
						boolean bEstaCerrado = false;
						if (oficina_id != null) {
								oficina = conection
										.ejecutarQueryObtenerOficina("SELECT * FROM OFICINA WHERE OFICINA.GERENTE_ID = '"
												+ oficina_id + "'");

						}

						if (estaCerrada != null && estaCerrada.equals("SI"))
							bEstaCerrado = true;

						Cuenta cuentaNueva = new Cuenta(id,
								tryParser(saldo), tipoCuenta, moneda_Id,
								bEstaCerrado, oficina);
						System.out.println("Cuenta: "+cuentaNueva.getId());
						
						
						ArrayList<Transaccion> transaccionesArray = new ArrayList<Transaccion>();

						String idT = rs.getString("idt");
						String cantidad = rs.getString("cantidad");
						java.sql.Date fechainicio = rs.getDate("fechainicio");
						String done = rs.getString("done");
						String tipo = rs.getString("tipo");
						String cuentaDestino = rs.getString("cuenta_destino");
						String cambio = rs.getString("cambio");
						String interes = rs.getString("interes");
						String numeroCuotas = rs.getString("numeroCuotas");
						java.sql.Date fechaPago = rs.getDate("fechaPago");
						String valorCuota = rs.getString("valorcuota");
						String saldoPendiente = rs.getString("saldoPendiente");

						Cuenta cuentaDestinoO = null;
						if (cuentaDestino != null) {
								cuentaDestinoO = conection
										.ejecutarQueryObtenerCuenta("SELECT * FROM CUENTA WHERE CUENTA.ID = '"
												+ cuentaDestino + "'");
						}

						edu.uniandes.data.Date fi = new edu.uniandes.data.Date(
								fechainicio.getDay() + "",
								fechainicio.getMonth() + "",
								fechainicio.getYear() + "");
						edu.uniandes.data.Date fp = null;

						if(fechaPago != null){
							fp = new edu.uniandes.data.Date(fechaPago.getDay()
									+ "", fechaPago.getMonth() + "",
									fechaPago.getYear() + "");
						}

						Transaccion transaccionNueva = new Transaccion(idT,
								cantidad, fi, done, tipo, cuentaNueva,
								cuentaDestinoO, cambio, interes,
								tryParser(numeroCuotas), fp,
								tryParser(valorCuota),
								tryParser(saldoPendiente));
						transaccionesArray.add(transaccionNueva);
						
						System.out.println("Transaccion: "+transaccionNueva.getId());

						while (rs.next()) {
							
							System.out.println("Entro while 1");

							while (rs.getString("id").equals(id)) {
								System.out.println("Entro while 2");
								idT = rs.getString("idt");
								cantidad = rs.getString("cantidad");
								fechainicio = rs.getDate("fechainicio");
								done = rs.getString("done");
								tipo = rs.getString("tipo");
								cuentaDestino = rs.getString("cuenta_destino");
								cambio = rs.getString("cambio");
								interes = rs.getString("interes");
								numeroCuotas = rs.getString("numeroCuotas");
								fechaPago = rs.getDate("fechaPago");
								valorCuota = rs.getString("valorcuota");
								saldoPendiente = rs.getString("saldoPendiente");

								if (cuentaDestino != null) {
										cuentaDestinoO = conection
												.ejecutarQueryObtenerCuenta("SELECT * FROM CUENTA WHERE CUENTA.ID = '"
														+ cuentaDestino + "'");
								}

								fi = null;
								fp = null;
								
								if(fechainicio!=null&&fechaPago!=null){
									fi = new edu.uniandes.data.Date(
											fechainicio.getDay() + "",
											fechainicio.getMonth() + "",
											fechainicio.getYear() + "");
									fp = new edu.uniandes.data.Date(
											fechaPago.getDay() + "",
											fechaPago.getMonth() + "",
											fechaPago.getYear() + "");
								}
								transaccionNueva = new Transaccion(idT,
										cantidad, fi, done, tipo, cuentaNueva,
										cuentaDestinoO, cambio, interes,
										tryParser(numeroCuotas), fp,
										tryParser(valorCuota),
										tryParser(saldoPendiente));
								
								System.out.println("Transaccion: "+transaccionNueva.getId());
								
								transaccionesArray.add(transaccionNueva);

								rs.next();
							}

							cuentaNueva.setTransacciones(transaccionesArray);
							cuentasArray.add(cuentaNueva);

							System.out.println("cuenta");
							transaccionesArray = new ArrayList<Transaccion>();
							id = rs.getString("id");
							saldo = rs.getString("saldo");
							tipoCuenta = rs.getString("tipoCuenta");
							moneda_Id = rs.getString("moneda_id");
							estaCerrada = rs.getString("estaCerrada");
							oficina_id = rs.getString("oficina_id");

							bEstaCerrado = false;
							if (oficina_id != null) {
									oficina = conection
											.ejecutarQueryObtenerOficina("SELECT * FROM OFICINA WHERE OFICINA.GERENTE_ID = '"
													+ oficina_id + "'");
							}

							if (estaCerrada != null && estaCerrada.equals("SI")) {
								bEstaCerrado = true;
							}

							cuentaNueva = new Cuenta(id,
									tryParser(saldo), tipoCuenta, moneda_Id,
									bEstaCerrado, oficina);
							System.out.println("Cuenta: "+cuentaNueva.getId());
							
							
							
							idT = rs.getString("idt");
							cantidad = rs.getString("cantidad");
							fechainicio = rs.getDate("fechainicio");
							done = rs.getString("done");
							tipo = rs.getString("tipo");
							cuentaDestino = rs.getString("cuenta_destino");
							cambio = rs.getString("cambio");
							interes = rs.getString("interes");
							numeroCuotas = rs.getString("numeroCuotas");
							fechaPago = rs.getDate("fechaPago");
							valorCuota = rs.getString("valorcuota");
							saldoPendiente = rs.getString("saldoPendiente");

							if (cuentaDestino != null) {
									cuentaDestinoO = conection
											.ejecutarQueryObtenerCuenta("SELECT * FROM CUENTA WHERE CUENTA.ID = '"
													+ cuentaDestino + "'");
							}

							fi = null;
							fp = null;
							
							if(fechainicio!=null&&fechaPago!=null){
								fi = new edu.uniandes.data.Date(
										fechainicio.getDay() + "",
										fechainicio.getMonth() + "",
										fechainicio.getYear() + "");
								fp = new edu.uniandes.data.Date(
										fechaPago.getDay() + "",
										fechaPago.getMonth() + "",
										fechaPago.getYear() + "");
							}
							transaccionNueva = new Transaccion(idT,
									cantidad, fi, done, tipo, cuentaNueva,
									cuentaDestinoO, cambio, interes,
									tryParser(numeroCuotas), fp,
									tryParser(valorCuota),
									tryParser(saldoPendiente));
							
							System.out.println("Transaccion: "+transaccionNueva.getId());
							
							transaccionesArray.add(transaccionNueva);
						}
						cuentasArray.add(cuentaNueva);
						usuarioNuevo.setCuentas(cuentasArray);
						ArrayList<Usuario> resp = new ArrayList<Usuario>();
						resp.add(usuarioNuevo);
						System.out.println("respondiendo");
						return resp;
						

					}
					return usuarios;
				}
			}
		}
	}

	private int tryParser(String parameter) {
		try {
			int numero = Integer.parseInt(parameter);
			return numero;
		} catch (Exception e) {
			return -10;
		}
	}

}
