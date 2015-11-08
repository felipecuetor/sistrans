package iteracion2BancoWebServices;

import edu.uniandes.backend.CargarDatos;
import edu.uniandes.domain.Administrador;
import edu.uniandes.domain.Usuario;
import junit.framework.TestCase;

public class CargarDatosTestCase extends TestCase
{
	private Administrador admin;
	
	protected void setScenario1()
	{
		
	}

	public void testCargarDatos()
	{		
		admin = new Administrador("Felipe", "Felipe Cueto", "95120607449", "Cedula", 0, "Colombia", "Calle 129b#55-20", "f.cueto@hotmail.com", "4662325", "Bogotá", "Cundinamarca", "11", "Natural");
		
		CargarDatos datos = new CargarDatos(admin);
		
		System.out.println("Usuario----------------------------------------------------------------");
		datos.cargarUsuarios();
		
		admin = new Administrador("Felipe", "Felipe Cueto", "95120607449", "Cedula", 1, "Colombia", "Calle 129b#55-20", "f.cueto@hotmail.com", "4662325", "Bogotá", "Cundinamarca", "11", "Natural");
		
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
