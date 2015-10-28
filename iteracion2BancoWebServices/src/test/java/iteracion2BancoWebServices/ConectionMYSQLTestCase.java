package iteracion2BancoWebServices;

import java.sql.SQLException;

import edu.uniandes.dao.ConectionMYSQLAdministradorDAO;
import junit.framework.TestCase;

public class ConectionMYSQLTestCase extends TestCase {
 

	public void testProbarConecion() throws SQLException{
		ConectionMYSQLAdministradorDAO conecion = new ConectionMYSQLAdministradorDAO();
		conecion.ejecutarQuery("SELECT * FROM USUARIO");
	}
	
}
