package edu.uniandes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uniandes.backend.ConectionMYSQLAdministradorDAO;
import edu.uniandes.domain.Administrador;
import edu.uniandes.domain.Usuario;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombreUsuario = request.getParameter("usuario");
		ConectionMYSQLAdministradorDAO conection = new ConectionMYSQLAdministradorDAO();
		PrintWriter out = response.getWriter();

		try {
			Usuario usuario = conection
					.ejecutarQueryObtenerUsuario("SELECT * FROM USUARIO WHERE USUARIO.NOMBRE = "
							+ "'" + nombreUsuario + "'");

			if (usuario != null) {
				int cargo = usuario.getCargo();
				HttpSession session = request.getSession();

				if (cargo == 0 || cargo == 1 || cargo == 3 || cargo == 4) {
					Administrador administrador = new Administrador(
							usuario.getUsuario(), usuario.getNombre(),
							usuario.getCedula(), usuario.getTipoCedula(),
							usuario.getCargo(), usuario.getNacionalidad(),
							usuario.getDireccionFisica(), usuario.getEmail(),
							usuario.getTelefono(), usuario.getCiudad(),
							usuario.getDepartamento(),
							usuario.getCodigoPostal(), usuario.getTipoPersona());
					session.setAttribute("administrador", administrador);
					session.setAttribute("usuario", null);
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
					if (cargo == 0) {
						out.println("<li><a href=\"RegistroInicio.html\"><button type = \"button\">Registro</button></a></li>");
						out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
						out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
					} else if (cargo == 1) {
						out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
						out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
						out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
					} else if (cargo == 3) {
						out.println("<li><a href=\"RegistroInicioGerenteOficina.html\"><button type = \"button\">Registro</button></a></li>");
						out.println("<li class=\"acerca\"><a href=\"CerrarInicio.html\" ><button type = \"button\">Finalizar Tramites</button></a></li>");
						out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
					} else if (cargo == 4) {
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
					out.println("<h2>Titulo del Articulo</h2>");
					out.println("<br>");
					out.println("<p>Contenido</p>");
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
				} else if (cargo == 2) {
					session.setAttribute("usuario", usuario);
					session.setAttribute("administrador", null);
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
					out.println("<li><a href=\"ConsultarInicioUsuario.html\"><button type = \"button\">Consulta</button></a></li>");
					out.println("<li class=\"acerca\"><a href=\"#\" ><button type = \"button\">#</button></a></li>");
					out.println("<li class=\"horario\"><a href=\"#\"><button type = \"button\">#</button></a></li>");
					out.println("<li class=\"Salir\"><a href=\"Inicio.html\"><button type = \"button\">Salir</button></a></li>");
					out.println("</ul>");
					out.println("</nav>");
					out.println("</header>");
					out.println("<section class=\"main\">");
					out.println("<section Class=\"articles\">");
					out.println("<article>");
					out.println("<h2>Titulo del Articulo</h2>");
					out.println("<br>");
					out.println("<p>Contenido</p>");
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
		} catch (Exception e) {

			e.printStackTrace();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>BancAndes</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/EstiloUsuarioNoValido.css\">");
			out.println("<link href='http://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form name=\"Clave\" action=\"Clave\" method=\"post\">");
			out.println("<h1>BancAndes</h1>");
			out.println("<a href=\"Inicio.html\"><button type = \"button\">El Usuario no existe</button></a>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}

	}

}
