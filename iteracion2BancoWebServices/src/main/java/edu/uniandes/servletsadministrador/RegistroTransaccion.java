package edu.uniandes.servletsadministrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uniandes.data.Cuenta;
import edu.uniandes.data.Date;
import edu.uniandes.data.Transaccion;
import edu.uniandes.domain.Administrador;

/**
 * Servlet implementation class RegistroTransaccion
 */
public class RegistroTransaccion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroTransaccion() {
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
		String cantidad = request.getParameter("cantidad");
		Date fechaInicio = new Date(request.getParameter("diaInicio"), request.getParameter("mesInicio"), request.getParameter("yearInicio"));
		String done = request.getParameter("done");
		String tipo = request.getParameter("tipo");
		String stringCuenta_Origen = request.getParameter("cuenta_Origen");
		String stringCuenta_Destino = request.getParameter("cuenta_Destino");
		String cambio = request.getParameter("cambio");
		String interes = request.getParameter("interes");
		int numeroCuotas = tryParser(request.getParameter("numeroCuotas"));
		Date fechaPago = new Date(request.getParameter("diaFinal"), request.getParameter("mesFinal"), request.getParameter("yearFinal"));
		int valorCuota = tryParser(request.getParameter("valorCuota"));
		int saldoPendiente = tryParser(request.getParameter("saldoPendiente"));
		
		HttpSession session = request.getSession(true);
		Administrador administrador = (Administrador) session.getAttribute("administrador");
		
		PrintWriter out = response.getWriter();
		System.out.println("Inicio");
		
		try {
			Transaccion transaccion = null;
			if (tipo.equals("Prestamo")) {
				Cuenta cuenta_Origen = administrador.obtenerCuenta(stringCuenta_Origen);
				Cuenta cuenta_Destino= administrador.obtenerCuenta(stringCuenta_Destino);
				transaccion = new Transaccion(id, cantidad, fechaInicio, done, tipo, cuenta_Origen, cuenta_Destino, cambio, interes, numeroCuotas, fechaPago, valorCuota, saldoPendiente);
				administrador.registrarTransaccionPrestamo(transaccion);
			}else if (tipo.equals("Retiro")) {
				Cuenta cuenta_Origen = administrador.obtenerCuenta(stringCuenta_Origen);
				transaccion = new Transaccion(id, cantidad, fechaInicio, done, tipo, cuenta_Origen, null, cambio, interes, numeroCuotas, fechaPago, valorCuota, saldoPendiente);
				administrador.registrarTransaccionRetiro(transaccion);
			}else if (tipo.equals("Deposito")) {
				Cuenta cuenta_Destino = administrador.obtenerCuenta(stringCuenta_Destino);
				transaccion = new Transaccion(id, cantidad, fechaInicio, done, tipo, null, cuenta_Destino, cambio, interes, numeroCuotas, fechaPago, valorCuota, saldoPendiente);
				administrador.registrarTransaccionDeposito(transaccion);
			}else if (tipo.equals("Transferencia")) {
				Cuenta cuenta_Origen = administrador.obtenerCuenta(stringCuenta_Origen);
				Cuenta cuenta_Destino= administrador.obtenerCuenta(stringCuenta_Destino);
				transaccion = new Transaccion(id, cantidad, fechaInicio, done, tipo, cuenta_Origen, cuenta_Destino, cambio, interes, numeroCuotas, fechaPago, valorCuota, saldoPendiente);
				administrador.registrarTransaccionTransferencia(transaccion);
			}
			
			
			HttpSession session1 = request.getSession();
			session1.setAttribute("administrador", administrador);		
			
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
			out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</header>");
			out.println("<section class=\"main\">");
			out.println("<section Class=\"articles\">");
			out.println("<article>");
			out.println("<h2> Se ha registrado la Transacci&oacuten </h2>");
			out.println("<br>");
			out.println("<p> Id : "+ transaccion.getId() + "</p>");
			out.println("<p> Cantidad : "+ transaccion.getCantidad() + "</p>");
			out.println("<p> Fecha De Inicio : "+ transaccion.getFechaInicio().toString() + "</p>");
			out.println("<p> Tipo : "+ transaccion.getTipo() + "</p>");
			out.println("<p> Cuenta Destino : "+ transaccion.getCuenta_Destino().getId() + "</p>");
			out.println("<p> Fecha De Pago : "+  transaccion.getFechaPago() + "</p>");
			out.println("<p> Numero De Cuotas : "+ transaccion.getNumeroCuotas() + "</p>");
			out.println("<p> Valor De Cuota : "+ transaccion.getValorCuota() + "</p>");
			out.println("<p> Saldo Pendiente : "+ transaccion.getSaldoPendiente() + "</p>");
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
		} catch (SQLException e) {
			
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
			out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</header>");
			out.println("<section class=\"main\">");
			out.println("<section Class=\"articles\">");
			out.println("<article>");
			out.println("<h2> No se pudo registrar la Transacci&oacuten</h2>");
			out.println("<br>");
			out.println("<p> El error es: " + e.getMessage() + "</p>");
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
			out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</header>");
			out.println("<section class=\"main\">");
			out.println("<section Class=\"articles\">");
			out.println("<article>");
			out.println("<h2> No se pudo registrar la Transacci&oacuten</h2>");
			out.println("<br>");
			out.println("<p> El error es: " + e.getMessage() + "</p>");
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
		}
	}

	private int tryParser(String parameter) {
		try {
			System.out.println(parameter);
			int numero = Integer.parseInt(parameter);
			System.out.println(numero + "");
			return numero;
		} catch (Exception e) {
			return -10;
		}
	}

}
