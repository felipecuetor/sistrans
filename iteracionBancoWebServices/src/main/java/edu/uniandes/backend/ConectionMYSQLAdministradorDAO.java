package edu.uniandes.backend;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniandes.data.Cuenta;
import edu.uniandes.data.CuentaAhorro;
import edu.uniandes.data.CuentaCorriente;
import edu.uniandes.data.Oficina;
import edu.uniandes.data.Requerimiento3;
import edu.uniandes.data.Transaccion;
import edu.uniandes.data.Vinculo;
import edu.uniandes.domain.Administrador;
import edu.uniandes.domain.Usuario;

public class ConectionMYSQLAdministradorDAO {
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

	public void ejecutarQueryRegistrarOficina(String query) throws SQLException {
		System.out.println("fuera del query");
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				System.out.println(query);
				prep.execute(query);
				System.out.println("listo");
			}
		}
	}

	public void ejecutarQueryRegistrarUsuario(String query) throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}
	}

	public Usuario ejecutarQueryObtenerUsuario(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					if (rs.next()) {
						Usuario usuario = new Usuario(rs.getString("usuario"),
								rs.getString("nombre"), rs.getString("cedula"),
								rs.getString("tipocedula"), rs.getInt("cargo"),
								rs.getString("nacionalidad"),
								rs.getString("direccionFisica"),
								rs.getString("email"),
								rs.getString("telefono"),
								rs.getString("ciudad"),
								rs.getString("departamento"),
								rs.getString("codigopostal"),
								rs.getString("tipopersona"));
						return usuario;
					}
					return null;
				}
			}
		}
	}

	public Administrador ejecutarQueryObtenerAdministrador(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					if (rs.next()) {
						Administrador gerente = new Administrador(
								rs.getString("usuario"),
								rs.getString("nombre"), rs.getString("cedula"),
								rs.getString("tipocedula"), rs.getInt("cargo"),
								rs.getString("nacionalidad"),
								rs.getString("direccionFisica"),
								rs.getString("email"),
								rs.getString("telefono"),
								rs.getString("ciudad"),
								rs.getString("departamento"),
								rs.getString("codigopostal"),
								rs.getString("tipopersona"));
						return gerente;
					}
					return null;
				}
			}
		}
	}

	public void ejecutarQueryRegistrarPuntoDeAtencion(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}
	}

	public Oficina ejecutarQueryObtenerOficina(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					if (rs.next()) {
						String nombreGerente = rs.getString("gerente_ID");
						System.out.println("voy a pedir el gerente");
						Administrador gerente = this
								.ejecutarQueryObtenerAdministrador("SELECT * FROM USUARIO WHERE USUARIO.USUARIO = '"
										+ nombreGerente + "'");
						System.out.println("tengo el gerente");
						Oficina oficina = new Oficina(rs.getString("nombre"),
								rs.getString("direccion"),
								rs.getString("telefono"), gerente);
						return oficina;
					}
					return null;
				}
			}
		}
	}

	public void ejecutarQueryRegistrarCuenta(String query) throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}

	}

	public void ejecutarQueryRegistrarUsuarioXCuenta(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}
	}

	public boolean ejecutarQueryCerrarCuenta(String query) throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				System.out.println("listo");
				return prep.execute(query);
			}
		}
	}

	public void ejecutarQueryRegistrarTransaccion(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}
	}

	public Cuenta ejecutarQueryObtenerCuenta(String query) throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					if (rs.next()) {
						System.out.println("Estoy dentro del query");
						String id = rs.getString("id");
						int saldo = rs.getInt("saldo");
						String tipoCuenta = rs.getString("tipocuenta");
						String monedaId = rs.getString("moneda_ID");
						String stringEstaCerrda = rs.getString("estacerrada");
						boolean estaCerrado = false;
						String stringOficina = rs.getString("oficina_Id");
						Oficina oficina = this
								.ejecutarQueryObtenerOficina("SELECT * FROM OFICINA WHERE OFICINA.GERENTE_ID = '"
										+ stringOficina + "'");
						if (stringEstaCerrda.equals("SI")) {
							estaCerrado = true;
						}
						if (tipoCuenta.equals("Ahorros")) {
							CuentaAhorro cuenta = new CuentaAhorro(id, saldo,
									tipoCuenta, monedaId, estaCerrado, oficina);
							return cuenta;
						}
						CuentaCorriente cuenta = new CuentaCorriente(id, saldo,
								tipoCuenta, monedaId, estaCerrado, oficina);
						return cuenta;
					}
					return null;
				}
			}
		}
	}

	public void ejecutarQueryActualizarPrestamo(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}

	}

	public boolean ejecutarQueryCerrarPrestamo(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				System.out.println("listo");
				return prep.execute(query);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public Transaccion ejecutarQueryObtenerTransaccion(String query)
			throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					if (rs.next()) {
						String id = rs.getString("id");
						String cantidad = rs.getString("cantidad");
						Date sqlFechaInicio = rs.getDate("fechaInicio");
						edu.uniandes.data.Date fechaInicio = new edu.uniandes.data.Date(
								sqlFechaInicio.getDay() + "",
								sqlFechaInicio.getMonth() + "",
								sqlFechaInicio.getYear() + "");
						String done = rs.getString("done");
						String tipo = rs.getString("tipo");
						Cuenta cuenta_Origen = obtenerCuenta(rs
								.getString("cuenta_Origen"));
						Cuenta cuenta_Destino = obtenerCuenta(rs
								.getString("cuenta_Destino"));
						String cambio = rs.getString("cambio");
						String interes = rs.getString("interes");
						int numeroCuotas = rs.getInt("numeroCuotas");
						Date sqlFechaPago = rs.getDate("fechaInicio");
						edu.uniandes.data.Date fechaPago = new edu.uniandes.data.Date(
								sqlFechaPago.getDay() + "",
								sqlFechaPago.getMonth() + "",
								sqlFechaPago.getYear() + "");

						int valorCuota = rs.getInt("numeroCuotas");
						int saldoPendiente = rs.getInt("numeroCuotas");
						Transaccion transaccion = new Transaccion(id, cantidad,
								fechaInicio, done, tipo, cuenta_Origen,
								cuenta_Destino, cambio, interes, numeroCuotas,
								fechaPago, valorCuota, saldoPendiente);
						return transaccion;
					}
					return null;
				}
			}
		}
	}

	private Cuenta obtenerCuenta(String query) {
		try {
			return this
					.ejecutarQueryObtenerCuenta("SELECT * FROM CUENTA WHERE CUENTA.ID = '"
							+ query + "'");
		} catch (Exception e) {
			return null;
		}
	}

	public void ejecutarQueryCuentaSaldo(String query) throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}

	}

	public ArrayList<Requerimiento3> ejecutarQueryConsultarOperacionMayorMovimiento(
			String query) throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					ArrayList<Requerimiento3> requerimientos3 = new ArrayList<Requerimiento3>();
					while (rs.next()) {
						String tipo = rs.getString("tipo");
						String oficina_Id = rs.getString("oficina_Id");
						String promedio = rs.getString("promedio");
						String numeroVeces = rs.getString("numero_de_veces");

						Requerimiento3 req = new Requerimiento3(tipo,
								oficina_Id, promedio, numeroVeces);
						requerimientos3.add(req);
					}
					return requerimientos3;
				}
			}
		}
	}

	public ArrayList<Usuario> ejecutarQueryConsultarUsuarioMasActivo(
			String query) throws SQLException {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
					while (rs.next()) {
						Usuario usuario = new Usuario(rs.getString("usuario"),
								rs.getString("nombre"), rs.getString("cedula"),
								rs.getString("tipocedula"), rs.getInt("cargo"),
								rs.getString("nacionalidad"),
								rs.getString("direccionFisica"),
								rs.getString("email"),
								rs.getString("telefono"),
								rs.getString("ciudad"),
								rs.getString("departamento"),
								rs.getString("codigopostal"),
								rs.getString("tipopersona"));
						usuarios.add(usuario);
					}
					return usuarios;
				}
			}
		}
	}

	public void ejecutarQueryRegistrarCuentaXCuenta(String query)
			throws Exception {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}
	}

	public void ejecutarQueryGeneral(String query) throws Exception {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				prep.execute(query);
				System.out.println("listo");
			}
		}
	}

	public ArrayList<Vinculo> ejecutarQueryObtenerCuentasVinculadas(String query)
			throws Exception {
		System.out.println(query);
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					ArrayList<Vinculo> aVinculos = new ArrayList<Vinculo>();
					while (rs.next()) {
						System.out.println("Precesando elemento");
						String idCuentaJefe = rs.getString("cuentajefe");
						String idCuentaEmpleado = rs.getString("cuentaempleado");
						int idValor = rs.getInt("valor");
						String frecuencia = rs.getString("frecuencia");
						
						Vinculo e = new Vinculo(idCuentaJefe, idCuentaEmpleado, idValor, frecuencia);
						
						aVinculos.add(e);
					}
					return aVinculos;
				}
			}
		}
	}
	
	public ArrayList<String> consultarOperacionesV2(String fInicio, String fFinal, String val2 )throws SQLException
	{
		String res = "";
		String query = "";
		if(val2.contains("tipo"))
		{
			val2.replaceAll("tipo", "");
			query = "SELECT * FROM TRANSACCION WHERE FECHAINICIO BETWEEN '" + fInicio + "' AND '" + fFinal + "' AND TIPO = '" + val2+"'";
		}
		else if(val2.contains("monto"))
		{
			val2.replaceAll("monto", "");
			query = "SELECT * FROM TRANSACCION WHERE FECHAINICIO BETWEEN '" + fInicio + "' AND '" + fFinal + "' AND CANTIDAD = '" + val2+"'";
		}
		else if(val2.contains("cuotas"))
		{
			val2.replaceAll("cuotas", "");
			query = "SELECT * FROM TRANSACCION WHERE FECHAINICIO BETWEEN '" + fInicio + "' AND '" + fFinal + "' AND NUMEROCUOTAS = '" + val2+"'";
		}
		else if(val2.contains("interes"))
		{
			val2.replaceAll("interes", "");
			query = "SELECT * FROM TRANSACCION WHERE FECHAINICIO BETWEEN '" + fInicio + "' AND '" + fFinal + "' AND INTERES = '" + val2+"'";
		}
		
		System.out.println(query);
		
		ArrayList<String> result = new ArrayList<String>();
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					while (rs.next()) {
						String tipo = rs.getString("tipo");
						String id = rs.getString("id");
						System.out.println(id);
						String fecha = rs.getString("fechainicio");
						int monto = rs.getInt("cantidad");
						int cuotas = rs.getInt("numeroCuotas");
						String interes = rs.getString("interes");
						if(query.contains(tipo))
							res = "Las operaciones en el tiempo " + fecha + " de tipo " + tipo + "son:" + id;
						else if(query.contains("monto"))
							res = "Las operaciones en el tiempo " + fecha + " con monto  " + monto + "son:" + id;
						else if(query.contains("cuotas"))
							res = "Las operaciones en el tiempo " + fecha + " con # de cuotas " + cuotas + "son:" + id;
						else if(query.contains("interes"))
							res = "Las operaciones en el tiempo " + fecha + " con interes " + interes + "son:" + id;
						else{
							res = "No es un parametro disponible";
						}
						result.add(res);
					}
					return result;
				}
			}
		}
	}
	
	public ArrayList<String> consultarOperacionesV3(String fInicio, String fFinal, String val2 )throws SQLException
	{
		String res = "";
		String query = "";
		if(val2.contains("tipo"))
		{
			val2="1";
			val2.replaceAll("tipo", "");
			query = "SELECT * FROM TRANSACCION WHERE FECHAINICIO BETWEEN '" + fInicio + "' AND '" + fFinal + "' AND TIPO != '" + val2+"'";
		}
		else if(val2.contains("monto"))
		{
			val2.replaceAll("monto", "");
			query = "SELECT * FROM TRANSACCION WHERE FECHAINICIO BETWEEN '" + fInicio + "' AND '" + fFinal + "' AND CANTIDAD != '" + val2+"'";
		}
		else if(val2.contains("cuotas"))
		{
			val2.replaceAll("cuotas", "");
			query = "SELECT * FROM TRANSACCION WHERE FECHAINICIO BETWEEN '" + fInicio + "' AND '" + fFinal + "' AND NUMEROCUOTAS != '" + val2+"'";
		}
		else if(val2.contains("interes"))
		{
			val2.replaceAll("interes", "");
			query = "SELECT * FROM TRANSACCION WHERE FECHAINICIO BETWEEN '" + fInicio + "' AND '" + fFinal + "' AND INTERES != '" + val2+"'";
		}
		
		System.out.println(query);
		
		ArrayList<String> result = new ArrayList<String>();
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					while (rs.next()) {
						String tipo = rs.getString("tipo");
						String id = rs.getString("id");
						System.out.println(id);
						String fecha = rs.getString("fechainicio");
						int monto = rs.getInt("cantidad");
						int cuotas = rs.getInt("numeroCuotas");
						String interes = rs.getString("interes");
						if(query.contains(tipo))
							res = "Las operaciones en el tiempo " + fecha + " de tipo " + tipo + "son:" + id;
						else if(query.contains("monto"))
							res = "Las operaciones en el tiempo " + fecha + " con monto  " + monto + "son:" + id;
						else if(query.contains("cuotas"))
							res = "Las operaciones en el tiempo " + fecha + " con # de cuotas " + cuotas + "son:" + id;
						else if(query.contains("interes"))
							res = "Las operaciones en el tiempo " + fecha + " con interes " + interes + "son:" + id;
						else{
							res = "No es un parametro disponible";
						}
						result.add(res);
					}
					return result;
				}
			}
		}
	}

	public void consultarConsignaciones(int monto, String tipo)
			throws SQLException {
		String query = "SELECT * FROM PRESTAMO WHERE TIPO = '" + tipo
				+ "' AND MONTO = '"+monto+"'";
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				ResultSet rs = prep.executeQuery();
				System.out.println(query);
				prep.execute(query);
				System.out.println("listo");
			}
		}
	}
	
	
	public ArrayList<String> consultarConsignaciones(String cantidad)throws SQLException
	{
		String res = "";
		String query = "Select Transaccion.* FROM Transaccion JOIN Prestamos ON Transaccion.CUENTA_DESTINO = PRESTAMOS.CORREO WHERE Transaccion.Tipo = 'consignacion' AND PRESTAMOS.MONTO < '"+cantidad+"'";
		
		
		System.out.println(query);
		
		ArrayList<String> result = new ArrayList<String>();
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query)) {
				try (ResultSet rs = prep.executeQuery()) {
					while (rs.next()) {
						String tipo = rs.getString("tipo");
						String id = rs.getString("id");
						System.out.println(id);
						String fecha = rs.getString("fechainicio");
						int monto = rs.getInt("cantidad");
						int cuotas = rs.getInt("numeroCuotas");
						String interes = rs.getString("interes");
						res = "ID:"+id+"-Tipo"+tipo+"-Fecha:"+fecha+"-Monto:"+monto+"-Interes:"+interes+"-Cuotas:"+cuotas;
						result.add(res);
					}
					return result;
				}
			}
		}
	}
	
	
	public ArrayList<String> consultarPunto(String punto1, String punto2) throws Exception
	{
		String res = "";
		String query1 = "SELECT ID_PUNTO, NOMBRE_PUNTO, CUENTA_ORIGEN FROM REQ10 r JOIN TRANSACCION t ON r.ID = t.ID WHERE ID_PUNTO = " + punto1;
		String query2 = "SELECT ID_PUNTO, NOMBRE_PUNTO, CUENTA_ORIGEN FROM REQ10 r JOIN TRANSACCION t ON r.ID = t.ID WHERE ID_PUNTO = " + punto2;
		
		ArrayList<String> result = new ArrayList<String>();
		try (Connection con = getConnection()) {
			try (PreparedStatement prep = con.prepareStatement(query1)) {
				try (ResultSet rs = prep.executeQuery()) {
					while (rs.next()) {
						String idPunto = rs.getString("id_punto");
						String nombrePunto = rs.getString("inombre_punto");
						String cuenta = rs.getString("cuenta_origen");
						
						res = "Las operaciones en los puntos " + punto1 + " y " + punto2 + " son: " + idPunto + nombrePunto + cuenta + "/br";
								
						result.add(res);
					}
					return result;
				}
			}
		}
	}


}
