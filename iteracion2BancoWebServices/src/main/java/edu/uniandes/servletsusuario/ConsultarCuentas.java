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
import edu.uniandes.domain.Administrador;
import edu.uniandes.domain.Usuario;

/**
 * Servlet implementation class ConsultarCuentas
 */
public class ConsultarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarCuentas() {
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
		String tipoBusqueda = request.getParameter("tipoBusqueda");
		String idUsuario = request.getParameter("usuario");
		String tipoCuenta = request.getParameter("tipoCuenta");
		String saldoInicial = request.getParameter("ordenamiento");
		String saldoFinal = request.getParameter("ordenamiento");
		Date fecha = new Date(request.getParameter("diaInicio"), request.getParameter("mesInicio"), request.getParameter("yearInicio"));
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
		System.out.println("ID: " + usuarioPrincipal.getUsuario() + " Cargo: " + usuarioPrincipal.getCargo());
		PrintWriter out = response.getWriter();
		
		try {
			
			ArrayList<Cuenta> cuentas = usuarioPrincipal.consultarCuentas(tipoBusqueda, tipoCuenta, saldoInicial + "-" + saldoFinal, ordenamiento, fecha, usuarioPrincipal, idUsuario);
			
			HttpSession session1 = request.getSession();
			
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
				session1.setAttribute("usuario", usuario);
				out.println("<li class=\"imagen\"><div><img src=\"css/dazzle.jpg\"></div></li>");
				out.println("<li><input  name = \"buscar\" type=\"search\" placeholder=\"Search...\"></li>");
				out.println("<li><a href=\"ConsultarInicioUsuario.html\"><button type = \"button\">Consulta</button></a></li>");
				out.println("<li class=\"acerca\"><a href=\"#\" ><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"horario\"><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			}else {
				session1.setAttribute("administrador", administrador);
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
			}
			out.println("</ul>");
			out.println("</nav>");
			out.println("</header>");
			out.println("<section class=\"main\">");
			out.println("<section Class=\"articles\">");
			out.println("<article>");
			out.println("<h2>" + "El resultado De la Busqueda De Cuenta es: </h2>");
			out.println("<br>");
			for (int i = 0; i < cuentas.size(); i++) {
				Cuenta cuenta = cuentas.get(i);
				out.println("<p> ID: "+ cuenta.getId() 
						+ " Saldo: "+ cuenta.getSaldo() 
						+  " Tipo De Cuenta: "+ cuenta.getTipoCuenta() 
						+ " Moneda: "+ cuenta.getMonedaId() 
						+ " Esta Cerrada: "+ cuenta.getId() 
						+ " Oficina: " + cuenta.getOficina().getGerente()
						+ "</p>");
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
				out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			}
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
		}
	}
}
