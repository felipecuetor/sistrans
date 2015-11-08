package edu.uniandes.domain;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniandes.backend.ConectionMYSQLAdministradorDAO;
import edu.uniandes.backend.ConectionMYSQLUsuarioDAO;
import edu.uniandes.data.Cuenta;
import edu.uniandes.data.Date;

public class Usuario {

	private String usuario;
	private String nombre;
	private String cedula;
	private String tipoCedula;
	private int cargo;
	private String nacionalidad;
	private String direccionFisica;
	private String email;
	private String telefono;
	private String ciudad;
	private String departamento;
	private String codigoPostal;
	private String tipoPersona;
	private ArrayList<Cuenta> cuentas;
	private ArrayList<Cuenta> cuentasEmpleados;
	private static int ADMINISTRADOR = 0;
	private static int GERENTE_GENERAL = 1;
	private static int CLIENTE = 2;
	private static int GERENTE_OFICINA = 3;
	private static int CAJERO = 4;

	public Usuario(String usuario, String nombre, String cedula,
			String tipoCedula, int cargo, String nacionalidad,
			String direccionFisica, String email, String telefono,
			String ciudad, String departamento, String codigoPostal, String tipoPersona) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.cedula = cedula;
		this.tipoCedula = tipoCedula;
		this.cargo = cargo;
		this.nacionalidad = nacionalidad;
		this.direccionFisica = direccionFisica;
		this.email = email;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.codigoPostal = codigoPostal;
		this.cuentas = new ArrayList<Cuenta>();
	}

	public Usuario darUsuarioPorEmail(String email) throws Exception {
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		Usuario usuario = conection
				.ejecutarQueryObtenerUsuario("SELECT * FROM USUARIO WHERE USUARIO.EMAIL = "
						+ "'" + email + "'");

		System.out.println(usuario.getUsuario());
		return usuario;
	}

	public void hacerBusqueda() {
		// TODO Auto-generated method stub

	}

	// tipo de cuenta, rango de saldos, fecha de creación, fecha del último
	// movimiento
	// ordenamiento
	public ArrayList<Cuenta> consultarCuentas(String tipoBusqueda,
			String tipoCuenta, String saldo, String ordenamiento, Date fecha,
			Usuario usuarioPrincipal, String idUsuario) throws SQLException {
		ConectionMYSQLUsuarioDAO conection = new ConectionMYSQLUsuarioDAO();
		String query = "SELECT ID,SALDO,TIPOCUENTA,MONEDA_ID,ESTACERRADA,OFICINA_ID FROM USUARIOXCUENTA INNER JOIN ";

		if (tipoBusqueda.equals("tipoCuenta")) {
			query = query
					+ "CUENTA ON CUENTA.ID = USUARIOXCUENTA.CUENTA_ID WHERE CUENTA.TIPOCUENTA = '"
					+ tipoCuenta + "' ";
		} else if (tipoBusqueda.equals("rangoSaldo")) {
			String[] num = saldo.split("-");
			int min = Integer.parseInt(num[0]);
			int max = Integer.parseInt(num[1]);
			query += "CUENTA ON CUENTA.ID = USUARIOXCUENTA.CUENTA_ID WHERE CUENTA.SALDO < '"
					+ max + "' AND CUENTA.SALDO > '" + min + "' ";
		} else if (tipoBusqueda.equals("fechaCreacion")) {
			query += "(SELECT CUENTA.*, MIN(TRANSACCION.FECHAINICIO) AS FECHAMINIMA FROM CUENTA INNER JOIN TRANSACCION ON CUENTA.ID= TRANSACCION.CUENTA_ORIGEN "
					+ "OR CUENTA.ID= TRANSACCION.CUENTA_DESTINO WHERE "
					+ "FECHAMINIMA = '" + fecha.toString() + "') A";
			query += " ON USUARIOXCUENTA.CUENTA_ID = A.ID";
		} else if (tipoBusqueda.equals("fechaUltimoMovimiento")) {
			query += "(SELECT CUENTA.* , MAX(TRANSACCION.FECHAINICIO) AS FECHAMAXIMA FROM Cuenta INNER JOIN Transaccion ON Cuenta.ID= Transaccion.Cuenta_Origen "
					+ "OR Cuenta.ID= Transaccion.Cuenta_Destino WHERE "
					+ "FECHAMAXIMA = '"
					+ fecha.toString()
					+ "' OR Transaccion.FechaPago = '"
					+ fecha.toString()
					+ "') A";
			query += " ON USUARIOXCUENTA.CUENTA_ID = A.ID";
		} else if (tipoBusqueda.equals("cliente")) {
			query += " CUENTA ON CUENTA.ID = USUARIOXCUENTA.CUENTA_ID WHERE USUARIOXCUENTA.USUARIO_ID = '"
					+ usuarioPrincipal + "' ";
		}

		if (usuarioPrincipal.getCargo() == CLIENTE) { // Query desde cliente
			query += "AND USUARIO.USUARIO = '" + usuarioPrincipal.getUsuario()
					+ "'";
		} else if (usuarioPrincipal.getCargo() == GERENTE_OFICINA) { // Query
																		// desde
																		// Gerente
																		// Oficina
			query += "AND OFICINA_ID = '" + usuarioPrincipal.getUsuario() + "'";
		}

		if (ordenamiento != null && ordenamiento != "" && ordenamiento != " ") {
			query = query + "ORDER BY " + ordenamiento;
		}

		ArrayList<Cuenta> cuentas = conection
				.ejecutarQueryObtenerTipoCuenta(query);
		return cuentas;
	}

	public ArrayList<Usuario> consultarCliente(String id, String tipoOrdenamiento, Date fechaInicio, Date fechaFinal, String saldo, String valorInicial, String valorFinal, String ordenamiento, Usuario usuarioPrincipal) throws SQLException {
		int cargo = usuarioPrincipal.getCargo();
		System.out.println("Incio busqueda");
		if(cargo == 2){
			if(!id.equals(usuarioPrincipal.getUsuario())){
				throw new SQLException("Solo puedes consultar tu propio usuario");
			}
		}
		
		ConectionMYSQLUsuarioDAO conection = new ConectionMYSQLUsuarioDAO();
		
		String query = "SELECT CUENTA.*,USUARIO.*,TRANSACCION.*, TRANSACCION.ID AS idt FROM (((USUARIO INNER JOIN USUARIOXCUENTA ON USUARIO.USUARIO = USUARIOXCUENTA.USUARIO_ID) INNER JOIN CUENTA ON USUARIOXCUENTA.CUENTA_ID = CUENTA.ID) INNER JOIN TRANSACCION ON CUENTA.ID = TRANSACCION.CUENTA_ORIGEN) WHERE USUARIO.USUARIO = '" + id +"'";
		
		if(cargo == 3){
			query+="AND OFICINA_ID = '"+usuarioPrincipal.getUsuario()+"'";
		}
		
		query+=" ORDER BY CUENTA.ID";
		
		System.out.println("Query");
		ArrayList<Usuario> usuarios = conection.ejecutarQueryObtenerCliente(query);
		return usuarios;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public String getTipoCedula() {
		return tipoCedula;
	}

	public int getCargo() {
		return cargo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getDireccionFisica() {
		return direccionFisica;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	public static int getADMINISTRADOR() {
		return ADMINISTRADOR;
	}

	public static int getGERENTE_GENERAL() {
		return GERENTE_GENERAL;
	}

	public static int getCLIENTE() {
		return CLIENTE;
	}

	public static int getGERENTE_OFICINA() {
		return GERENTE_OFICINA;
	}

	public static int getCAJERO() {
		return CAJERO;
	}

	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	public void agregarCuentaEmpleado(Cuenta empleado)
	{
		
	}

}
