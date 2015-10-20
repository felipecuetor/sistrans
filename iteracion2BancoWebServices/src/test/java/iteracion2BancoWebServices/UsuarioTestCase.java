package iteracion2BancoWebServices;

import java.util.ArrayList;

import edu.uniandes.data.Cuenta;
import edu.uniandes.domain.Usuario;
import junit.framework.TestCase;

public class UsuarioTestCase extends TestCase{

	private Usuario user;
	
	protected void setScenario()
	{
		user = new Usuario("fName", "fName", "fName", "fName", 0, "fName", "fName", "fName", "fName", "fName", "fName", "fName", "fName");
	}
	
	public void testConsultaCuenta()
	{
		setScenario();
		
		//ArrayList array = user.consultarCuentas("tipoCuenta", "Ahorros", "", "");
		
//		Cuenta actual = (Cuenta) array.get(1);
//		
//		assertNotNull(actual);
//		actual = (Cuenta) array.get(2);
//		assertNotNull(actual);
	}
	
}
