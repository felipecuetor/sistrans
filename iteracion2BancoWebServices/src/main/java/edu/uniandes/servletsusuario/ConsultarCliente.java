package edu.uniandes.servletsusuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uniandes.data.Cuenta;
import edu.uniandes.data.Date;
import edu.uniandes.data.Transaccion;
import edu.uniandes.domain.Administrador;
import edu.uniandes.domain.Usuario;

/**
 * Servlet implementation class ConsultarCliente
 */
public class ConsultarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String tipoOrdenamiento = request.getParameter("tipoOrdenamiento");
		Date fechaInicio = new Date(request.getParameter("diaInicio"), request.getParameter("mesInicio"), request.getParameter("yearInicio"));
		Date fechaFinal = new Date(request.getParameter("diaFinal"), request.getParameter("mesFinal"), request.getParameter("yearFinal"));
		String saldo = request.getParameter("saldo");
		String valorInicial = request.getParameter("valorInicial");
		String valorFinal = request.getParameter("valorFinal");
		String ordenamiento = request.getParameter("ordenamiento");

		HttpSession session = request.getSession(true);
		Administrador administrador = (Administrador) session.getAttribute("administrador");
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Usuario usuarioPrincipal = null;
		boolean isUsuario = false;
		if (administrador == null) {
			usuarioPrincipal = usuario;
			isUsuario = true;
		}else {
			usuarioPrincipal = administrador;
		}
		
		PrintWriter out = response.getWriter();
		
		try {
			System.out.println("ID: " + usuarioPrincipal.getUsuario() + " Cargo: " + usuarioPrincipal.getCargo());
			
			ArrayList<Usuario> clientes = usuarioPrincipal.consultarCliente(id, tipoOrdenamiento, fechaInicio, fechaFinal, saldo, valorInicial, valorFinal, ordenamiento, usuarioPrincipal);
			System.out.println(clientes.size());
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Inicio</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/EstiloBarra.css\">");
			out.println("<link href='http://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form name=\"Buscar\" action=\"Buscar\" method=\"post\">");
			out.println("<header>");
			out.println("<nav>");
			out.println("<ul>");
			if (isUsuario) {
				out.println("<li class=\"imagen\"><div><img src=\"css/dazzle.jpg\"></div></li>");
				out.println("<li><input  name = \"buscar\" type=\"search\" placeholder=\"Search...\"></li>");
				out.println("<li><a href=\"ConsultarInicioUsuario.html\"><button type = \"button\">Consulta</button></a></li>");
				out.println("<li class=\"acerca\"><a href=\"#\" ><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"horario\"><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			}else {
				out.println("<li class=\"imagen\"><div><img src=\"css/dazzle.jpg\"></div></li>");
				out.println("<li><input  name = \"buscar\" type=\"search\" placeholder=\"Search...\"></li>");
				System.out.println(administrador.getCargo());
				int cargo = administrador.getCargo();
				if (cargo == 0) {
					out.println("<li><a href=\"RegistroInicio.html\"><button type = \"button\">Registro</button></a></li>");
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
				}else if (cargo == 1) {
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
				}else if (cargo == 3) {
					out.println("<li><a href=\"RegistroInicioGerenteOficina.html\"><button type = \"button\">Registro</button></a></li>");
					out.println("<li class=\"acerca\"><a href=\"CerrarInicio.html\" ><button type = \"button\">Finalizar Tramites</button></a></li>");
					out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
				}else if (cargo == 4) {
					out.println("<li><a href=\"RegistroInicioCajero.html\"><button type = \"button\">Registro</button></a></li>");
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				}
			}
			out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</header>");
			out.println("<section class=\"main\">");
			out.println("<section Class=\"articles\">");
			out.println("<article>");
			out.println("<h2>" + "El resultado De la Busqueda Del Cliente es: </h2>");
			out.println("<br>");
			if (clientes.isEmpty()) {
				out.println("<p> No se encontro Ninguna cliente<p>");
			}else {
				for (int i = 0; i < clientes.size(); i++) {
					Usuario cliente = clientes.get(i);
					out.println("<p> Usuario: "+ cliente.getUsuario() + " Nombre: "+ cliente.getNombre() +  " Tipo De Cedula: "+ cliente.getTipoCedula() + " Cedula: "+ cliente.getCedula() + " Cargo: " + cliente.getCargo() + " Nacionalidad: "+ cliente.getNacionalidad() + " Dirrecion Fisica: "+ cliente.getDireccionFisica()	+ " Email: "+ cliente.getEmail() + " Telefono: "+ cliente.getTelefono()	+ " Ciudad: "+ cliente.getCiudad() 	+ " Departamento: "+ cliente.getDepartamento()	+ " Codigo Postal: "+ cliente.getCodigoPostal() + "</p>");
					ArrayList<Cuenta> cuentas = cliente.getCuentas();
					System.out.println("tamaño de la cuenta" + cuentas.size());
					if (!cuentas.isEmpty()) {
						for (int j = 0; j < cuentas.size(); j++) {
							Cuenta cuenta = cuentas.get(j);
							out.println("<p> ID: "+ cuenta.getId() + " Saldo: "+ cuenta.getSaldo() +  " Tipo De Cuenta: "+ cuenta.getTipoCuenta() + " Moneda: "+ cuenta.getMonedaId() + " Esta Cerrada: "+ cuenta.getId() + "</p>");
							ArrayList<Transaccion> transacciones = cuenta.getTransacciones();
							System.out.println("tamaño de las" + transacciones.size());
							if (!transacciones.isEmpty()) {
								for (int k = 0; k < transacciones.size(); k++) {
									Transaccion transaccion = transacciones.get(k);
									String imprimir = "<p> ID: "+ transaccion.getId()	+ " Cantidad: "+ transaccion.getCantidad()	+  " Fecha De Inicio: "+ transaccion.getFechaInicio() + " Done: "+ transaccion.getDone() + " Tipo: "+ transaccion.getTipo();
									if (transaccion.getCuenta_Origen() != null) {
										imprimir += " Cuenta De Origen: "+ transaccion.getCuenta_Origen().getId();
									}
									if (transaccion.getCuenta_Destino() != null) {
										imprimir += " Cuenta De Destino: "+ transaccion.getCuenta_Destino().getId();
									}
									if (transaccion.getCambio() != null) {
										imprimir += " Cuenta De Cambio: "+ transaccion.getCambio();
									}
									if (transaccion.getInteres() != null) {
										imprimir += " Interes: "+ transaccion.getInteres();
									}
									if (!(transaccion.getNumeroCuotas() == -10)) {
										imprimir += " Numero De Cuotas: "+ transaccion.getNumeroCuotas();
									}
									if (transaccion.getFechaPago() != null) {
										imprimir += " Fecha De Pago: "+ transaccion.getFechaPago();
									}
									if (!(transaccion.getValorCuota() == -10)) {
										imprimir +=  " Valor De Cuota: "+ transaccion.getValorCuota();
									}
									if (!(transaccion.getSaldoPendiente() == -10)) {
										imprimir +=  " Saldo Pendiente: "+ transaccion.getSaldoPendiente();
									}
									imprimir += "</p>";
									out.println(imprimir);
								}
							}
						}
					}
				}
			}
			out.println("<br>");
			out.println("</article>");
			out.println("</section>");
			out.println("</section>");
			out.println("<footer>");
			out.println("<p>Diego Riveros Y Felipe Cueto - Derechos Reservados</p>");
			out.println("</footer>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");				
		} catch (Exception e) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Inicio</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/EstiloBarra.css\">");
			out.println("<link href='http://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form name=\"Buscar\" action=\"Buscar\" method=\"post\">");
			out.println("<header>");
			out.println("<nav>");
			out.println("<ul>");
			out.println("<li class=\"imagen\"><div><img src=\"css/dazzle.jpg\"></div></li>");
			out.println("<li><input  name = \"buscar\" type=\"search\" placeholder=\"Search...\"></li>");
			if (isUsuario) {
				out.println("<li class=\"imagen\"><div><img src=\"css/dazzle.jpg\"></div></li>");
				out.println("<li><input  name = \"buscar\" type=\"search\" placeholder=\"Search...\"></li>");
				out.println("<li><a href=\"ConsultarInicioUsuario.html\"><button type = \"button\">Consulta</button></a></li>");
				out.println("<li class=\"acerca\"><a href=\"#\" ><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"horario\"><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			}else {
				out.println("<li class=\"imagen\"><div><img src=\"css/dazzle.jpg\"></div></li>");
				out.println("<li><input  name = \"buscar\" type=\"search\" placeholder=\"Search...\"></li>");
				int cargo = administrador.getCargo();
				if (cargo == 0) {
					out.println("<li><a href=\"RegistroInicio.html\"><button type = \"button\">Registro</button></a></li>");
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
				}else if (cargo == 1) {
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
				}else if (cargo == 3) {
					out.println("<li><a href=\"RegistroInicioGerenteOficina.html\"><button type = \"button\">Registro</button></a></li>");
					out.println("<li class=\"acerca\"><a href=\"CerrarInicio.html\" ><button type = \"button\">Finalizar Tramites</button></a></li>");
					out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
				}else if (cargo == 4) {
					out.println("<li><a href=\"RegistroInicioCajero.html\"><button type = \"button\">Registro</button></a></li>");
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				}
			}
			out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</header>");
			out.println("<section class=\"main\">");
			out.println("<section Class=\"articles\">");
			out.println("<article>");
			out.println("<h2>" + "No Se Pudo Realizar La Busqueda En Cuenta </h2>");
			out.println("<br>");
			out.println("<p> Error: "+ e.getMessage() + "</p>");
			out.println("<br>");
			out.println("</article>");
			out.println("</section>");
			out.println("</section>");
			out.println("<footer>");
			out.println("<p>Diego Riveros Y Felipe Cueto - Derechos Reservados</p>");
			out.println("</footer>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			e.printStackTrace();
		}
	}
}
