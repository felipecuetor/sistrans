package iteracion2BancoWebServices;

import edu.uniandes.backend.CargarDatos;
import edu.uniandes.domain.Administrador;
import edu.uniandes.domain.Usuario;
import junit.framework.TestCase;

public class AdministradorTestCase extends TestCase
{
	private Administrador admin;
	
	protected void setScenario1()
	{
		admin = new Administrador("diego", "Diego Riveros", "95120607449", "Cedula", 1, "Colombia", "Calle 138#75-75", "diego@hotmail.com", "4662325", "Bogotá", "Cundinamarca", "11");
	}

	public void testCargarDatos()
	{
		setScenario1();
		
		CargarDatos datos = new CargarDatos(admin);
	}
}
