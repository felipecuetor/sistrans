package edu.uniandes.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import edu.uniandes.dao.ConectionMYSQLAdministradorDAO;
import edu.uniandes.data.Cuenta;
import edu.uniandes.data.Date;
import edu.uniandes.data.Oficina;
import edu.uniandes.data.PuntoDeAtencion;
import edu.uniandes.data.Requerimiento3;
import edu.uniandes.data.Transaccion;
import edu.uniandes.data.Vinculo;

public class FachadaAdministrador extends FachadaUsuario {

	public FachadaAdministrador(String usuario, String nombre, String cedula,
			String tipoCedula, int cargo, String nacionalidad,
			String dirrecionFisica, String email, String telefono,
			String ciudad, String departamento, String codigoPostal,
			String tipoPersona) {
		super(usuario, nombre, cedula, tipoCedula, cargo, nacionalidad,
				dirrecionFisica, email, telefono, ciudad, departamento,
				codigoPostal, tipoPersona);
	}

	public void registrarUsuario(FachadaUsuario usuario, int miCargo)
			throws SQLException {
		int cargoUsuario = usuario.getCargo();
		if (miCargo == 0) {
			if (cargoUsuario == 1 || cargoUsuario == 3 | cargoUsuario == 4) {
				ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
				String query = "INSERT INTO USUARIO (USUARIO, NOMBRE, CEDULA, TIPOCEDULA, CARGO, NACIONALIDAD, DIRECCIONFISICA, EMAIL, TELEFONO, CIUDAD, DEPARTAMENTO, CODIGOPOSTAL) VALUES ("
						+ agregarValoresUsuario(usuario) + ")";
				conection.ejecutarQueryRegistrarUsuario(query);
			} else {
				throw new SQLException("No se puede registrar clientes");
			}
		} else if (miCargo == 3) {
			if (cargoUsuario == 2) {
				ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
				String query = "INSERT INTO USUARIO (USUARIO, NOMBRE, CEDULA, TIPOCEDULA, CARGO, NACIONALIDAD, DIRECCIONFISICA, EMAIL, TELEFONO, CIUDAD, DEPARTAMENTO, CODIGOPOSTAL) VALUES ("
						+ agregarValoresUsuario(usuario) + ")";
				conection.ejecutarQueryRegistrarUsuario(query);
			} else {
				throw new SQLException("Solo se puede registrar clientes");
			}
		}
	}

	private String agregarValoresUsuario(FachadaUsuario usuario) {
		String entrega = "'" + usuario.getUsuario() + "', '"
				+ usuario.getNombre() + "', '" + usuario.getCedula() + "', '"
				+ usuario.getTipoCedula() + "', " + usuario.getCargo() + ", '"
				+ usuario.getNacionalidad() + "', '"
				+ usuario.getDireccionFisica() + "', '" + usuario.getEmail()
				+ "', '" + usuario.getTelefono() + "', '" + usuario.getCiudad()
				+ "', '" + usuario.getDepartamento() + "', '"
				+ usuario.getCodigoPostal() + "'";
		return entrega;
	}

	public void registrarOficina(Oficina oficina) throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String query = "INSERT INTO OFICINA (NOMBRE, DIRECCION, TELEFONO, GERENTE_ID) VALUES ("
				+ agregarValoresOficina(oficina) + ")";
		System.out.println("tengo el query");
		conection.ejecutarQueryRegistrarOficina(query);

	}

	private String agregarValoresOficina(Oficina oficina) {
		String entrega = "'" + oficina.getNombre() + "', '"
				+ oficina.getDireccion() + "', '" + oficina.getTelefono()
				+ "', '" + oficina.getGerente().getUsuario() + "'";
		return entrega;
	}

	public void registrarPuntoDeAtencion(PuntoDeAtencion puntoDeAtencion)
			throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String query = "INSERT INTO PUNTO_DE_ATENCION (TIPO, LOCALIZACION, OFICINA_ID) VALUES ("
				+ agregarValoresPuntoDeAtencion(puntoDeAtencion) + ")";
		;
		conection.ejecutarQueryRegistrarPuntoDeAtencion(query);
	}

	private String agregarValoresPuntoDeAtencion(PuntoDeAtencion puntoDeAtencion) {
		String entrega = "'" + puntoDeAtencion.getTipo() + "', '"
				+ puntoDeAtencion.getLocalizacion() + "', '"
				+ puntoDeAtencion.getOficina().getGerente().getUsuario() + "'";
		return entrega;
	}

	public FachadaAdministrador obtenerGerente(String gerente) throws SQLException {
		System.out.println("algo");
		ConectionMYSQLAdministradorDAO conecion = new ConectionMYSQLAdministradorDAO();
		FachadaAdministrador administrador = conecion
				.ejecutarQueryObtenerAdministrador("SELECT * FROM USUARIO WHERE USUARIO.USUARIO = '"
						+ gerente + "' AND USUARIO.CARGO = 1");
		return administrador;
	}

	public Oficina obtenerOficina(String stringOficina) throws SQLException {
		ConectionMYSQLAdministradorDAO conecion = new ConectionMYSQLAdministradorDAO();
		Oficina oficina = conecion
				.ejecutarQueryObtenerOficina("SELECT * FROM OFICINA WHERE OFICINA.GERENTE_ID = '"
						+ stringOficina + "'");
		return oficina;
	}

	public void registrarCuenta(Cuenta cuenta) throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String query = "INSERT INTO CUENTA (ID, SALDO, TIPOCUENTA, MONEDA_ID, ESTACERRADA, OFICINA_ID) VALUES ("
				+ agregarValoresCuenta(cuenta) + ")";
		conection.ejecutarQueryRegistrarCuenta(query);
	}

	private String agregarValoresCuenta(Cuenta cuenta) {
		String entrega = "'" + cuenta.getId() + "', " + cuenta.getSaldo()
				+ ", '" + cuenta.getTipoCuenta() + "', '"
				+ cuenta.getMonedaId() + "', '" + cuenta.isEstaCerrado()
				+ "', '" + cuenta.getOficina().getGerente().getUsuario() + "'";
		return entrega;
	}

	public void cerrarCuenta(String idCuenta, String idOficina, String idNueva)
			throws Exception {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		Cuenta cuenta = conection.ejecutarQueryObtenerCuenta(idCuenta);
		
		
		String queryVinculadas = "SELECT * FROM CuentaXCuenta WHERE CuentaXCuenta.cuentaJefe = '"+ idCuenta + "'";
		ArrayList<Vinculo> vinculos = conection.ejecutarQueryObtenerCuentasVinculadas(queryVinculadas);
		Cuenta cuentaCerrar = obtenerCuenta(idCuenta);
		Cuenta cuentaNueva = obtenerCuenta(idNueva);
		
		if(!vinculos.isEmpty()&&idNueva!=null&&!idNueva.equals(""))
		{
			queryVinculadas = "UPDATE CuentaXCuenta SET CUENTAXCUENTA.CUENTAJEFE = '"+ idNueva + "' WHERE CUENTA.CUENTAJEFE = '" + idCuenta + "'";
		}
		if(!vinculos.isEmpty()&&(idNueva==null|idNueva.equals("")))
		{
			throw new Exception("Debe ingresar una cuenta de pago nueva");
		}
		
		if (cuenta.getOficina().equals(idOficina)) {
			String query = "UPDATE CUENTA SET CUENTA.ESTACERRADA = 'SI' WHERE CUENTA.ID = '"
					+ idCuenta + "' AND CUENTA.SALDO = " + 0;
			boolean cambio = conection.ejecutarQueryCerrarCuenta(query);
			if (!cambio) {
				throw new SQLException("EL saldo no es cero");
			}
		} else {
			throw new SQLException("La Cuenta no pertenece a esta oficina");
		}
	}

	public void registrarTransaccionPrestamo(Transaccion transaccion)
			throws Exception {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String stringCuentaOrigen = transaccion.getCuenta_Origen().getId();
		String stringCuentaDestino = transaccion.getCuenta_Destino().getId();
		boolean registrar = true;
		System.out.println("Busco la cuenta origen");
		Cuenta cuentaOrigen = this.obtenerCuenta(stringCuentaOrigen);
		if (cuentaOrigen.isEstaCerrado()) {
			registrar = false;
		}
		System.out.println("Busco la cuenta destino");
		Cuenta cuentaDestino = this.obtenerCuenta(stringCuentaDestino);
		if (cuentaDestino.isEstaCerrado()) {
			registrar = false;
		}
		if (registrar) {
			String query = "INSERT INTO TRANSACCION (ID, CANTIDAD, FECHAINICIO, DONE, TIPO, CUENTA_ORIGEN, CUENTA_DESTINO, CAMBIO, INTERES, NUMEROCUOTAS, FECHAPAGO, VALORCUOTA, SALDOPENDIENTE) VALUES ("
					+ agregarValoresTransaccion(transaccion) + ")";
			conection.ejecutarQueryRegistrarTransaccion(query);
			this.actualizarCuentaSaldoAumentar(stringCuentaDestino,
					transaccion.getValorCuota());
			this.actualizarCuentaSaldoDisminuir(stringCuentaOrigen,
					transaccion.getValorCuota());
		}
	}

	public Cuenta obtenerCuenta(String idCuenta) throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String query = "SELECT FOR UPDATE * FROM CUENTA WHERE CUENTA.ID = '" + idCuenta
				+ "'";
		Cuenta cuenta = conection.ejecutarQueryObtenerCuenta(query);
		return cuenta;
	}

	private String agregarValoresTransaccion(Transaccion transaccion) {
		String entrega = "'" + transaccion.getId() + "', '"
				+ transaccion.getCantidad() + "', '"
				+ transaccion.getFechaInicio().toString() + "', '"
				+ transaccion.getDone() + "', '" + transaccion.getTipo()
				+ "', '" + transaccion.getCuenta_Origen().getId() + "', '"
				+ transaccion.getCuenta_Destino().getId() + "', '"
				+ transaccion.getCambio() + "', '" + transaccion.getInteres()
				+ "', " + transaccion.getNumeroCuotas() + ", '"
				+ transaccion.getFechaPago().toString() + "', "
				+ transaccion.getValorCuota() + ", "
				+ transaccion.getSaldoPendiente() + "";
		return entrega;
	}

	public void actualizarCuentaSaldoAumentar(String idCuenta, int montoPagado)
			throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		Cuenta cuenta = this.obtenerCuenta(idCuenta);
		int nuevoSaldo = cuenta.getSaldo() + montoPagado;
		String query = "UPDATE CUENTA SET CUENTA.SALDO = " + nuevoSaldo
				+ " WHERE CUENTA.ID = '" + idCuenta
				+ "' AND  CUENTA.ESTACERRADA = 'NO'";
		conection.ejecutarQueryCuentaSaldo(query);
	}

	public void actualizarCuentaSaldoDisminuir(String idCuenta, int montoPagado)
			throws Exception {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		Cuenta cuenta = this.obtenerCuenta(idCuenta);
		int nuevoSaldo = cuenta.getSaldo() - montoPagado;
		if (nuevoSaldo < 0) {
			throw new Exception("El saldo no puede ser negativo");
		}
		String query = "UPDATE CUENTA SET CUENTA.SALDO = " + nuevoSaldo
				+ " WHERE CUENTA.ID = '" + idCuenta
				+ "' AND  CUENTA.ESTACERRADA = 'NO'";
		conection.ejecutarQueryCuentaSaldo(query);
	}

	public void actualizarTransaccionPrestamo(String idTransaccion,
			int montoPagado) throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		Transaccion transaccion = this.obtenerTransaccion(idTransaccion);
		System.out.println("Tengo la transaccion");
		int nuevoSaldo = transaccion.getSaldoPendiente() - montoPagado;
		if (nuevoSaldo > 0) {
			String query = "UPDATE TRANSACCION SET TRANSACCION.SALDOPENDIENTE = "
					+ nuevoSaldo
					+ " WHERE TRANSACCION.ID = '"
					+ idTransaccion
					+ "' AND  TRANSACCION.TIPO = 'Prestamo'";
			conection.ejecutarQueryActualizarPrestamo(query);
		}
	}

	public Transaccion obtenerTransaccion(String idTransaccion)
			throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String query = "SELECT * FROM TRANSACCION WHERE TRANSACCION.ID = '"
				+ idTransaccion + "'";
		Transaccion transaccion = conection
				.ejecutarQueryObtenerTransaccion(query);
		return transaccion;
	}

	public void CerrarPrestamo(String idTransaccion) throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String checkQuery = "Select TRANSACCION.DONE";
		String query = "UPDATE TRANSACCION SET TRANSACCION.DONE = 'SI' WHERE TRANSACCION.ID = '"
				+ idTransaccion
				+ "' AND  TRANSACCION.TIPO = 'Prestamo' AND TRANSACCION.SALDOPENDIENTE = "
				+ 0;
		boolean cambio = conection.ejecutarQueryCerrarPrestamo(query);
		if (!cambio) {
			throw new SQLException("El Saldo Pendiente No Es Cero");
		}
	}

	public void registrarUsuarioXCuenta(String usuario_id, String id)
			throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String query = "INSERT INTO USUARIOXCUENTA (USUARIO_ID, CUENTA_ID) VALUES ('"
				+ usuario_id + "', '" + id + "')";
		conection.ejecutarQueryRegistrarUsuarioXCuenta(query);
	}

	public void registrarTransaccionRetiro(Transaccion transaccion) throws Exception {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String stringCuentaOrigen = transaccion.getCuenta_Origen().getId();
		boolean registrarNoCerrada = true;
		boolean registrarSaldoSuficiente = true;
		System.out.println("Busco la cuenta origen");
		Cuenta cuentaOrigen = this.obtenerCuenta(stringCuentaOrigen);
		if (cuentaOrigen.isEstaCerrado()) {
			registrarNoCerrada = false;
		}
		if(cuentaOrigen.getSaldo()<Integer.parseInt(transaccion.getCantidad())) {
			registrarSaldoSuficiente = false;
		}
		if (registrarNoCerrada&&registrarSaldoSuficiente) {
			String query = "INSERT INTO TRANSACCION (ID, CANTIDAD, FECHAINICIO, DONE, TIPO, CUENTA_ORIGEN, CUENTA_DESTINO, CAMBIO, INTERES, NUMEROCUOTAS, FECHAPAGO, VALORCUOTA, SALDOPENDIENTE) VALUES ("
					+ agregarValoresTransaccion(transaccion) + ")";
			conection.ejecutarQueryRegistrarTransaccion(query);
			this.actualizarCuentaSaldoDisminuir(stringCuentaOrigen, Integer.parseInt(transaccion.getCantidad()));
		}
		else
		{
			String error ="";
			
			if(!registrarNoCerrada) error = "Esta cuenta se encuentra cerrada";
			
			if(!registrarSaldoSuficiente) error = "Saldo insuficiente";
			
			throw new Exception(error);
		}
	}

	public void registrarTransaccionDeposito(Transaccion transaccion)
			throws SQLException {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String stringCuentaDestino = transaccion.getCuenta_Destino().getId();
		boolean registrar = true;
		System.out.println("Busco la cuenta destino");
		Cuenta cuentaDestino = this.obtenerCuenta(stringCuentaDestino);
		if (cuentaDestino.isEstaCerrado()) {
			registrar = false;
		}
		if (registrar) {
			String query = "INSERT INTO TRANSACCION (ID, CANTIDAD, FECHAINICIO, DONE, TIPO, CUENTA_ORIGEN, CUENTA_DESTINO, CAMBIO, INTERES, NUMEROCUOTAS, FECHAPAGO, VALORCUOTA, SALDOPENDIENTE) VALUES ("
					+ agregarValoresTransaccion(transaccion) + ")";
			conection.ejecutarQueryRegistrarTransaccion(query);
			this.actualizarCuentaSaldoAumentar(stringCuentaDestino,
					Integer.parseInt(transaccion.getCantidad()));
		}

	}

	public void registrarTransaccionTransferencia(Transaccion transaccion)
			throws Exception {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		String stringCuentaOrigen = transaccion.getCuenta_Origen().getId();
		String stringCuentaDestino = transaccion.getCuenta_Destino().getId();
		boolean registrar = true;
		System.out.println("Busco la cuenta origen");
		Cuenta cuentaOrigen = this.obtenerCuenta(stringCuentaOrigen);
		if (cuentaOrigen.isEstaCerrado()) {
			registrar = false;
		}
		System.out.println("Busco la cuenta destino");
		Cuenta cuentaDestino = this.obtenerCuenta(stringCuentaDestino);
		if (cuentaDestino.isEstaCerrado()) {
			registrar = false;
		}
		if (registrar) {
			String query = "INSERT INTO TRANSACCION (ID, CANTIDAD, FECHAINICIO, DONE, TIPO, CUENTA_ORIGEN, CUENTA_DESTINO, CAMBIO, INTERES, NUMEROCUOTAS, FECHAPAGO, VALORCUOTA, SALDOPENDIENTE) VALUES ("
					+ agregarValoresTransaccion(transaccion) + ")";
			conection.ejecutarQueryRegistrarTransaccion(query);
			this.actualizarCuentaSaldoAumentar(stringCuentaDestino,
					Integer.parseInt(transaccion.getCantidad()));
			this.actualizarCuentaSaldoDisminuir(stringCuentaOrigen,
					Integer.parseInt(transaccion.getCantidad()));
		}

	}

	// Solo el gerente general y el gerente oficina puede hacer esto
	// ordenado por el numero de veces realizada
	public ArrayList<Requerimiento3> consultarOperacionMayorMovimiento(
			Date fechaInicio, Date fechaFinal, FachadaUsuario usuarioPrincipal)
			throws SQLException {
		// TODO Auto-generated method stub
		if (usuarioPrincipal.getCargo() == 1
				|| usuarioPrincipal.getCargo() == 3) {
			ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
			ArrayList<Requerimiento3> requerimiento3 = new ArrayList<Requerimiento3>();

			String query = "SELECT * "
					+ "FROM (SELECT TRANSACCION.TIPO,"
					+ " PUNTO_DE_ATENCION.OFICINA_ID,"
					+ " AVG(TRANSACCION.CANTIDAD) AS PROMEDIO,"
					+ " COUNT(TRANSACCION.CANTIDAD) AS NUMERO_DE_VECES"
					+ " FROM ( ( ( TRANSACCION LEFT JOIN "
					+ "CUENTA ON TRANSACCION.CUENTA_ORIGEN = CUENTA.ID ) "
					+ "LEFT JOIN CUENTA ON TRANSACCION.CUENTA_DESTINO = CUENTA.ID )"
					+ " INNER JOIN PUNTO_DE_ATENCION "
					+ "ON CUENTA.OFICINA_ID = PUNTO_DE_ATENCION.OFICINA_ID ) "
					+ "WHERE TRANSACCION.FECHAINICIO > '" + fechaInicio + "'"
					+ "AND TRANSACCION.FECHAINICIO   < '" + fechaFinal + "'"
					+ "GROUP BY TRANSACCION.TIPO ,"
					+ "PUNTO_DE_ATENCION.OFICINA_ID "
					+ "ORDER BY PROMEDIO DESC) " + "WHERE ROWNUM < 10";
			if (usuarioPrincipal.getCargo() == 3) {
				query += " AND OFICINA_ID = '" + usuarioPrincipal.getUsuario()
						+ "'";
			}
			query += " ORDER BY NUMERO_DE_VECES DESC";
			requerimiento3 = conection
					.ejecutarQueryConsultarOperacionMayorMovimiento(query);
			return requerimiento3;
		} else {
			throw new SQLException(
					"No se puede hacer esta consulta si no eres gerente general o gerente de oficina");
		}
	}

	public ArrayList<FachadaUsuario> consultarUsuarioMasActivo(String tipoOperacion,
			String tipoBusqueda, int monto, FachadaUsuario usuarioPrincipal)
			throws SQLException {
		// TODO Auto-generated method stub
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		ArrayList<FachadaUsuario> usuarios = new ArrayList<FachadaUsuario>();
		String query = "SELECT USUARIO.*"
				+ " FROM (((USUARIO INNER JOIN USUARIOXCUENTA ON USUARIO.USUARIO = USUARIOXCUENTA.USUARIO_ID)"
				+ " INNER JOIN CUENTA ON USUARIOxCUENTA.CUENTA_ID = CUENTA.ID)"
				+ "INNER JOIN TRANSACCION ON CUENTA.ID = TRANSACCION.CUENTA_ORIGEN)";
		if (tipoBusqueda.equals("NO")) {
			query += " WHERE TRANSACCION.TIPO = '" + tipoOperacion + "'";
		} else if (tipoBusqueda.equals("Mayor")) {
			query += " WHERE TRANSACCION.CANTIDAD > '" + monto + "'";
		} else if (tipoBusqueda.equals("igual")) {
			query += " WHERE TRANSACCION.CANTIDAD = '" + monto + "'";
		}
		if (usuarioPrincipal.getCargo() == 3) {
			query += " AND CUENTA.OFICINA_ID = '"
					+ usuarioPrincipal.getUsuario() + "'";
		}
		usuarios = conection.ejecutarQueryConsultarUsuarioMasActivo(query);
		return usuarios;
	}
	
	public void asociarCuentaXCuenta(String idCuentaJefe, String idCuentaEmpleado, String valor, String frecuencia) throws Exception
	{
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		
		
		Cuenta cuentaJefe = obtenerCuenta(idCuentaJefe);
		Cuenta cuentaEmpleado = obtenerCuenta(idCuentaEmpleado);
		
		String query = "INSERT INTO CUENTAXCUENTA (CUENTAJEFE, CUENTAEMPLEADO, VALOR, FRECUENCIA) VALUES ('"
				+ idCuentaJefe + "', '" + idCuentaEmpleado + "', '" + valor + "', '" + frecuencia + "')";
		
		conection.ejecutarQueryRegistrarCuentaXCuenta(query);
	}
	
	private FachadaUsuario obtenerUsuario(String stringUsuario) throws Exception {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		
		String query = "SELECT USUARIO.* FROM USUARIO WHERE USUARIO.USUARIO = '"+ stringUsuario + "'";
		
		FachadaUsuario usuario = conection.ejecutarQueryObtenerUsuario(query);
		
		return usuario;
	}

	public void pagarNomina(String cuentaOrigen)
	{
		
	}

}
