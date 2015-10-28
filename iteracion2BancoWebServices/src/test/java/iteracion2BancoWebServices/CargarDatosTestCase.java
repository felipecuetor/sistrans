package iteracion2BancoWebServices;

import edu.uniandes.dao.CargarDatos;
import edu.uniandes.fachada.FachadaAdministrador;
import edu.uniandes.fachada.FachadaUsuario;
import junit.framework.TestCase;

public class CargarDatosTestCase extends TestCase
{
	private FachadaAdministrador admin;
	
	protected void setScenario1()
	{
		
	}

	public void testCargarDatos()
	{		
		admin = new FachadaAdministrador("diego", "Diego Riveros", "95120607449", "Cedula", 0, "Colombia", "Calle 138#75-75", "diego@hotmail.com", "4662325", "Bogotá", "Cundinamarca", "11", "Natural");
		
		CargarDatos datos = new CargarDatos(admin);
		
		System.out.println("Usuario----------------------------------------------------------------");
		datos.cargarUsuarios();
		
		admin = new FachadaAdministrador("diego", "Diego Riveros", "95120607449", "Cedula", 1, "Colombia", "Calle 138#75-75", "diego@hotmail.com", "4662325", "Bogotá", "Cundinamarca", "11", "Natural");
		
		System.out.println("Oficinas----------------------------------------------------------------");
		datos.cargarOficinas();
		System.out.println("Puntos----------------------------------------------------------------");
		datos.cargarPuntos();
		System.out.println("Cuentas----------------------------------------------------------------");
		datos.cargarCuentas();
		System.out.println("Prestamos----------------------------------------------------------------");
		datos.cargarPrestamos();
	}
}
