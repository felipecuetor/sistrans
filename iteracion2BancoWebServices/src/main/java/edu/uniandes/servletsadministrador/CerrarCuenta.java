package edu.uniandes.servletsadministrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uniandes.fachada.FachadaAdministrador;

/**
 * Servlet implementation class CerrarCuenta
 */
public class CerrarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerrarCuenta() {
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
		String idCuenta = request.getParameter("id");
		String idNueva = request.getParameter("idNueva");



		HttpSession session = request.getSession(true);
		FachadaAdministrador administrador = (FachadaAdministrador) session.getAttribute("administrador");

		
		
		PrintWriter out = response.getWriter();
		System.out.println("Inicio");

		try {
			
			administrador.cerrarCuenta(idCuenta, administrador.getUsuario(), idNueva);
			
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
				out.println("<li><a href=\"CuentaXCuenta.html\"><button type = \"button\">Vincular Cuentas</button></a></li>");
			} else if (cargo == 1) {
				out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
				out.println("<li><a href=\"CuentaXCuenta.html\"><button type = \"button\">Vincular Cuentas</button></a></li>");
			} else if (cargo == 3) {
				out.println("<li><a href=\"RegistroInicioGerenteOficina.html\"><button type = \"button\">Registro</button></a></li>");
				out.println("<li class=\"acerca\"><a href=\"CerrarInicio.html\" ><button type = \"button\">Finalizar Tramites</button></a></li>");
				out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
				out.println("<li><a href=\"CuentaXCuenta.html\"><button type = \"button\">Vincular Cuentas</button></a></li>");
			} else if (cargo == 4) {
				out.println("<li><a href=\"RegistroInicioCajero.html\"><button type = \"button\">Registro</button></a></li>");
				out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li><a href=\"CuentaXCuenta.html\"><button type = \"button\">Vincular Cuentas</button></a></li>");
			}
			out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</header>");
			out.println("<section class=\"main\">");
			out.println("<section Class=\"articles\">");
			out.println("<article>");
			out.println("<h2>" + "Se ha Cerrado la Cuenta con el ID: </h2>");
			out.println("<br>");
			out.println("<p>" + idCuenta + "</p>");
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
			out.println("<h2> No se pudo Actualizar</h2>");
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
}
