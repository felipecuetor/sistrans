package edu.uniandes.backend;

import java.io.File;

import edu.uniandes.data.Cuenta;
import edu.uniandes.data.Date;
import edu.uniandes.data.Oficina;
import edu.uniandes.data.PuntoDeAtencion;
import edu.uniandes.data.Transaccion;
import edu.uniandes.domain.Administrador;
import edu.uniandes.domain.Usuario;
import jxl.*;

public class CargarDatos {
	private Workbook workbook;
	private Sheet sheet;
	private int numSheet;
	private Administrador admin;

	// Constructor
	public CargarDatos(Administrador administrador) {
		admin = administrador;

		System.out.println(admin.getCedula()+admin.getNombre());
		
		sheet = null;

		numSheet = 0;

		conectarExcel();

		cargarUsuarios();
		cargarOficinas();
		cargarPuntos();
		cargarCuentas();
		cargarPrestamos();
	}

	// Abre el archivo Excel
	private void conectarExcel() {
		try {
			File f1 = new File("./docs/datos.xls");
			workbook = Workbook.getWorkbook(f1);
			sheet = workbook.getSheet(numSheet);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Sube los datos iniciales de usuarios
	public void cargarUsuarios() {
		int i = 1;
		int j = 0;

		String usuario = "";
		String nombre = "";
		String cedula = "";
		String tipoCedula = "";
		String cargo = "";
		String nacionalidad = "";
		String direccionFisica = "";
		String email = "";
		String telefono = "";
		String ciudad = "";
		String departamento = "";
		String codigoPostal = "";
		while (i < sheet.getRows()) {
			usuario = sheet.getCell(j, i).getContents();
			j++;
			nombre = sheet.getCell(j, i).getContents();
			j++;
			cedula = sheet.getCell(j, i).getContents();
			j++;
			tipoCedula = sheet.getCell(j, i).getContents();
			j++;
			cargo = sheet.getCell(j, i).getContents();
			j++;
			nacionalidad = sheet.getCell(j, i).getContents();
			j++;
			direccionFisica = sheet.getCell(j, i).getContents();
			j++;
			email = sheet.getCell(j, i).getContents();
			j++;
			telefono = sheet.getCell(j, i).getContents();
			j++;
			ciudad = sheet.getCell(j, i).getContents();
			j++;
			departamento = sheet.getCell(j, i).getContents();
			j++;
			codigoPostal = sheet.getCell(j, i).getContents();
			j = 0;

			String tipoPersona = "Natural";
			
			Usuario u = new Usuario(usuario, nombre, cedula, tipoCedula,
					Integer.parseInt(cargo), nacionalidad, direccionFisica,
					email, telefono, ciudad, departamento, codigoPostal, tipoPersona);

			try {
				admin.registrarUsuario(u, admin.getCargo());
			} catch (Exception e) {
				e.printStackTrace();
			}

			i++;
		}
	}

	public void cargarOficinas() {
		int i = 1;
		int j = 0;

		numSheet++;
		sheet = workbook.getSheet(numSheet);

		String nombre = "";
		String direccion = "";
		String telefono = "";
		String gerente = "";

		while (i < sheet.getRows()) {
			nombre = sheet.getCell(j, i).getContents();
			j++;
			direccion = sheet.getCell(j, i).getContents();
			j++;
			telefono = sheet.getCell(j, i).getContents();
			j++;
			gerente = sheet.getCell(j, i).getContents();
			j = 0;

			try {
				Oficina o = new Oficina(nombre, direccion, telefono,
						admin.obtenerGerente(gerente));
				admin.registrarOficina(o);
			} catch (Exception e) {
				e.printStackTrace();
			}

			i++;
		}
	}

	public void cargarPuntos() {
		int i = 1;
		int j = 0;

		numSheet++;
		sheet = workbook.getSheet(numSheet);

		String tipo = "";
		String localizacion = "";
		String oficina = "";

		while (i < sheet.getRows()) {
			tipo = sheet.getCell(j, i).getContents();
			j++;
			localizacion = sheet.getCell(j, i).getContents();
			j++;
			oficina = sheet.getCell(j, i).getContents();
			j = 0;

			try {
				PuntoDeAtencion p = new PuntoDeAtencion(tipo, localizacion,
						admin.obtenerOficina(oficina));
				admin.registrarPuntoDeAtencion(p);
			} catch (Exception e) {
				e.printStackTrace();
			}

			i++;
		}

	}

	public void cargarCuentas() {
		int i = 1;
		int j = 0;

		numSheet++;
		sheet = workbook.getSheet(numSheet);

		String id = "";
		String saldo = "";
		String tipoCuenta = "";
		String idMoneda = "";
		String estaCerrada = "";
		String oficina = "";

		while (i < sheet.getRows()) {
			id = sheet.getCell(j, i).getContents();
			j++;
			saldo = sheet.getCell(j, i).getContents();
			j++;
			tipoCuenta = sheet.getCell(j, i).getContents();
			j++;
			idMoneda = sheet.getCell(j, i).getContents();
			j++;
			estaCerrada = sheet.getCell(j, i).getContents();
			j++;
			estaCerrada = sheet.getCell(j, i).getContents();
			j=0;

			try {
				Cuenta c = new Cuenta(id, Integer.parseInt(saldo), tipoCuenta,
						idMoneda, Boolean.parseBoolean(estaCerrada), admin.obtenerOficina(oficina));
				admin.registrarCuenta(c);
			} catch (Exception e) {
				e.printStackTrace();
			}

			i++;
		}
	}

	public void cargarPrestamos() {
		int i = 1;
		int j = 0;

		numSheet++;
		sheet = workbook.getSheet(numSheet);

		String id = "";
		String cantidad = "";
		String fechaInicio = "";
		String done = "";
		String tipo = "";
		String cuenta_Destino = "";
		String cambio = "";
		String interes = "";
		String numeroCuotas = "";
		String fechaPago = "";
		String valorCuota = "";
		String saldoPendiente = "";

		while (i < sheet.getRows()) {
			id = sheet.getCell(j, i).getContents();
			j++;
			cantidad = sheet.getCell(j, i).getContents();
			j++;
			fechaInicio = sheet.getCell(j, i).getContents();
			j++;
			done = sheet.getCell(j, i).getContents();
			j++;
			tipo = sheet.getCell(j, i).getContents();
			j++;
			j++;
			cuenta_Destino = sheet.getCell(j, i).getContents();
			j++;
			cambio = sheet.getCell(j, i).getContents();
			j++;
			interes = sheet.getCell(j, i).getContents();
			j++;
			numeroCuotas = sheet.getCell(j, i).getContents();
			j++;
			fechaPago = sheet.getCell(j, i).getContents();
			j++;
			valorCuota = sheet.getCell(j, i).getContents();
			j++;
			saldoPendiente = sheet.getCell(j, i).getContents();
			j = 0;

			try {
				String[] sfecha1 = fechaInicio.split("/");
				Date fecha1 = new Date(sfecha1[2], sfecha1[1], sfecha1[0]);

				String[] sfecha2 = fechaPago.split("/");
				Date fecha2 = new Date(sfecha2[2], sfecha2[1], sfecha2[0]);

				Cuenta cuenta_DestinoC = admin.obtenerCuenta(cuenta_Destino);
				Cuenta cuenta_OrigenC = admin
						.obtenerCuenta("AAAHuDAIgAAABIJAAB");

				Transaccion t = new Transaccion(id, cantidad, fecha1, done,
						tipo, cuenta_OrigenC, cuenta_DestinoC, cambio, interes,
						Integer.parseInt(numeroCuotas), fecha2,
						Integer.parseInt(valorCuota),
						Integer.parseInt(saldoPendiente));
				admin.registrarTransaccionPrestamo(t);
			} catch (Exception e) {
				e.printStackTrace();
			}

			i++;
		}

	}

}
