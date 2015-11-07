package edu.uniandes.servletsadministrador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uniandes.domain.Administrador;
import edu.uniandes.domain.Usuario;

/**
 * Servlet implementation class Registro
 */
public class RegistroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistroUsuario() {
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
		String usuario = request.getParameter("usuario");
		String nombre = request.getParameter("nombre");
		String cedula = request.getParameter("cedula");
		String tipoCedula = request.getParameter("tipoCedula");
		String cargoString = request.getParameter("cargo");
		int cargo = 0;
		if (cargoString.equals("cliente")) {
			cargo = 2;
		} else if (cargoString.equals("gerenteGeneral")) {
			cargo = 1;
		} else if (cargoString.equals("gerenteDeOficina")) {
			cargo = 3;
		} else if (cargoString.equals("cajero")) {
			cargo = 4;
		}
		String nacionalidad = request.getParameter("nacionalidad");
		String direccionFisica = request.getParameter("tipoDirrecion")
				+ request.getParameter("numero") + "#"
				+ request.getParameter("numero1") + "-"
				+ request.getParameter("numero2");
		String direccionElectronica = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		String ciudad = request.getParameter("ciudad");
		String departamento = request.getParameter("departamento");
		String codigoPostal = request.getParameter("codigoPostal");
		String tipoPersona = request.getParameter("tipopersona");
		Usuario nuevoUsuario = new Usuario(usuario, nombre, cedula, tipoCedula,
				cargo, nacionalidad, direccionFisica, direccionElectronica,
				telefono, ciudad, departamento, codigoPostal, tipoPersona);

		HttpSession session = request.getSession(true);
		Administrador administrador = (Administrador) session
				.getAttribute("administrador");
		PrintWriter out = response.getWriter();

		try {
			int miCargo = administrador.getCargo();
			administrador.registrarUsuario(nuevoUsuario, miCargo);

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
			int cargoDos = administrador.getCargo();
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
			out.println("<h2>" + "Se ha registrado al Usuario: "
					+ nuevoUsuario.getUsuario() + "</h2>");
			out.println("<br>");
			out.println("<p> Nombre: " + nuevoUsuario.getNombre() + "</p>");
			out.println("<p> Tipo de Cedula: " + nuevoUsuario.getTipoCedula()
					+ " Cedula: " + nuevoUsuario.getCedula() + "</p>");
			out.println("<p> Cargo: " + nuevoUsuario.getCargo() + "</p>");
			out.println("<p> Nacionalidad: " + nuevoUsuario.getNacionalidad()
					+ "</p>");
			out.println("<p> Direccion: " + nuevoUsuario.getDireccionFisica()
					+ "</p>");
			out.println("<p> Email: " + nuevoUsuario.getEmail() + "</p>");
			out.println("<p> Telefono: " + nuevoUsuario.getTelefono() + "</p>");
			out.println("<p> Ciudad: " + nuevoUsuario.getCiudad() + "</p>");
			out.println("<p> Departamento: " + nuevoUsuario.getDepartamento()
					+ "</p>");
			out.println("<p> Codigo Postal: " + nuevoUsuario.getCodigoPostal()
					+ "</p>");
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
			int cargoDos = administrador.getCargo();
			if (cargoDos == 0) {
				out.println("<li><a href=\"RegistroInicio.html\"><button type = \"button\">Registro</button></a></li>");
				out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
			} else if (cargoDos == 1) {
				out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li><a href=\"#\"><button type = \"button\">#</button></a></li>");
				out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
			} else if (cargoDos == 3) {
				out.println("<li><a href=\"RegistroInicioGerenteOficina.html\"><button type = \"button\">Registro</button></a></li>");
				out.println("<li class=\"acerca\"><a href=\"CerrarInicio.html\" ><button type = \"button\">Finalizar Tramites</button></a></li>");
				out.println("<li class=\"horario\"><a href=\"ConsultarInicioAdministrador.html\"><button type = \"button\">Consultas</button></a></li>");
			} else if (cargoDos == 4) {
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
			out.println("<h2> No se pudo registrar el usuario</h2>");
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
