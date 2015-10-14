package edu.uniandes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uniandes.domain.Usuario;

/**
 * Servlet implementation class Recuperacion
 */
public class Recuperacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recuperacion() {
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
		String emailUsuario =  request.getParameter("email");
		PrintWriter out = response.getWriter();
		
		try {
			Usuario usuarioCreado = new Usuario("diego", "Diego Riveros", "95120607449", "Cedula", 1, "Colombia", "Calle 138#75-75", "diego@hotmail.com", "4662325", "Bogotá", "Cundinamarca", "11");
			Usuario usuario = usuarioCreado.darUsuarioPorEmail(emailUsuario);
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			
			if (emailUsuario.equals(usuario.getEmail())) {
				
				//falta mandar el email
				out.println("<html>");
				out.println("<head>");
				out.println("<title>BancAndes</title>");
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/EstiloUsuarioNoValido.css\">");
				out.println("<link href='http://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>");
				out.println("</head>");
				out.println("<body>");
				out.println("<form name=\"Clave\" action=\"Clave\" method=\"post\">");
				out.println("<h1>BancAndes</h1>");
				out.println("<a href=\"Inicio.html\"><button type = \"button\">El email se ha enviado a su correo</button></a>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}else {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>BancAndes</title>");
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/EstiloUsuarioNoValido.css\">");
				out.println("<link href='http://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>");
				out.println("</head>");
				out.println("<body>");
				out.println("<form name=\"Clave\" action=\"Clave\" method=\"post\">");
				out.println("<h1>BancAndes</h1>");
				out.println("<a href=\"Inicio.html\"><button type = \"button\">El email no corresponde ningun usuario</button></a>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}
			
		} catch (Exception e) {
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>BancAndes</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/EstiloUsuarioNoValido.css\">");
			out.println("<link href='http://fonts.googleapis.com/css?family=Pinyon+Script' rel='stylesheet' type='text/css'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form name=\"Clave\" action=\"Clave\" method=\"post\">");
			out.println("<h1>BancAndes</h1>");
			out.println("<a href=\"Inicio.html\"><button type = \"button\">El email no corresponde ningun usuarioo</button></a>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
		
		
	}

}
