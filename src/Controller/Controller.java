package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Licencia;
import Model.Model;
import Model.Representante;
import Model.Titular;
import Model.Usuario;
import View.AdminUsuariosLayoutController;
import View.CambioTitularLayoutController;
import View.ConexionLayoutController;
import View.DetallesLayoutController;
import View.EditLayoutController;
import View.EditarTitularLayoutController;
import View.InscripcionLayoutController;
import View.LoginLayoutController;
import View.SplitsLayoutController;
import View.Vista;
import View.VistaPrincipal;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {
	public static final Controller metodos = new Controller();
	private Model modelo;
	private VistaPrincipal vistaPrincipal;
	private Vista vistaAjustes;
	private Vista vistaInscripcion;
	private Vista vistaEditar;
	private Vista vistaDetalles;
	private Vista vistaEditarTitular;
	private Vista vistaCambioTitular;
	private LoginLayoutController controllerLogin;
	private SplitsLayoutController controllerSplit;
	private ConexionLayoutController controllerAjustesConexion;
	private AdminUsuariosLayoutController controllerAdminUsuarios;
	private InscripcionLayoutController controllerInscripcion;
	private EditLayoutController controllerEdit;
	private DetallesLayoutController controllerDetalles;
	private EditarTitularLayoutController controllerEditarTitular;
	private CambioTitularLayoutController controllerCambioTitular;

	private String dni;
	private String usuario;

	private String menuAjustes;

	public ObservableList<Licencia> getDatosTablaLicencias() {
		return modelo.getLicencias();
	}

	public ObservableList<Usuario> getDatosTablaUsuarios() {
		return modelo.getUsuarios();
	}

	public Representante getRepresentante(String idTitular) {
		return modelo.getRepresentante(idTitular);
	}

	public ArrayList<Titular> getTitulares(String idSolicitud) {
		return modelo.getTitulares(idSolicitud);
	}

	public void cargarNumRegistro() {
		controllerInscripcion.setnumRegistro(modelo.numRegistro());
	}

	public void setModel(Model modelo) {
		this.modelo = modelo;
	}

	public void setVistaPrincipal(VistaPrincipal vista) {
		this.vistaPrincipal = vista;
	}

	public void login(String DNI) {
		controllerLogin.setEstado("Verificando DNI / NIE ...");
		String user = modelo.login(DNI);
		if (!user.equals("-1")) {
			controllerLogin.loginOK();
			this.dni = DNI;
			this.usuario = user;
			controllerLogin.setEstado("DNI/NIE Verificado - Cargando solicitudes...");
			modelo.cargarTablaLicencias();
			this.vistaPrincipal.loadPrincipal();
		} else {
			controllerLogin.loginFail();
		}
	}

	public void setControllerLogin(LoginLayoutController controller) {
		this.controllerLogin = controller;
	}

	public void setControllerAjustesConexion(ConexionLayoutController controllerAjustesConexion) {
		this.controllerAjustesConexion = controllerAjustesConexion;
		modelo.setControllerAjustesConexion(controllerAjustesConexion);
	}

	public void setControllerAdminUsuarios(AdminUsuariosLayoutController controllerAdminUsuarios) {
		this.controllerAdminUsuarios = controllerAdminUsuarios;
	}

	public void setControllerInscripcion(InscripcionLayoutController controllerInscripcion) {
		this.controllerInscripcion = controllerInscripcion;
	}

	public void setControllerSplit(SplitsLayoutController controllerSplit) {
		this.controllerSplit = controllerSplit;
	}

	public void setControllerEdit(EditLayoutController controllerEdit) {
		this.controllerEdit = controllerEdit;
	}

	public void setControllerDetalles(DetallesLayoutController controllerDetalles) {
		this.controllerDetalles = controllerDetalles;
	}

	public void setControllerEditarTitular(EditarTitularLayoutController controllerEditarTitular) {
		this.controllerEditarTitular = controllerEditarTitular;
	}

	public void setControllerCambioTitular(CambioTitularLayoutController controllerCambioTitular) {
		this.controllerCambioTitular = controllerCambioTitular;
	}

	public String getDni() {
		return dni;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getMenuAjustes() {
		return menuAjustes;
	}

	public void cerrar_sesion() {
		this.usuario = "";
		this.dni = "";
		vistaPrincipal.loadLogin();
	}

	public void abrirAjustes(String ajustes) {
		menuAjustes = ajustes;
		if (vistaAjustes == null) {
			vistaAjustes = new Vista("SettingsLayout.fxml", "Ajustes");
		} else if (!vistaAjustes.getStage().isShowing()) {
			vistaAjustes = new Vista("SettingsLayout.fxml", "Ajustes");
		}
	}

	public void abrirInscripcion() {
		if (vistaInscripcion == null) {
			vistaInscripcion = new Vista("InscripcionLayout.fxml", "Inscripción");
		} else if (!vistaInscripcion.getStage().isShowing()) {
			vistaInscripcion = new Vista("InscripcionLayout.fxml", "Inscripción");
		}
	}

	public void abrirEditar() {
		if (vistaEditar == null) {
			vistaEditar = new Vista("EditLayout.fxml", "Editar licencia");
		} else if (!vistaEditar.getStage().isShowing()) {
			vistaEditar = new Vista("EditLayout.fxml", "Editar licencia");
		}
		controllerEdit.cargarDatos(controllerSplit.getLicencia());
	}

	public void abrirEditarTitular() {
		if (vistaEditar == null) {
			vistaEditar = new Vista("EditarTitularLayout.fxml", "Editar titular");
		} else if (!vistaEditar.getStage().isShowing()) {
			vistaEditar = new Vista("EditarTitularLayout.fxml", "Editar titular");
		}
		controllerEditarTitular.cargarDatos(controllerSplit.getLicencia());
	}

	public void abrirCambioTitular() {
		if (vistaCambioTitular == null) {
			vistaCambioTitular = new Vista("CambioTitularLayout.fxml", "Cambio titular");
		} else if (!vistaEditar.getStage().isShowing()) {
			vistaCambioTitular = new Vista("CambioTitularLayout.fxml", "Cambio titular");
		}
		controllerCambioTitular.setIdLicencia(controllerSplit.getLicencia().getNRegistro());
	}

	public void abrirDetalles() {
		if (vistaDetalles == null) {
			vistaDetalles = new Vista("DetallesLayout.fxml", "Detalles");
		} else if (!vistaDetalles.getStage().isShowing()) {
			vistaDetalles = new Vista("DetallesLayout.fxml", "Detalles");
		}
		controllerDetalles.cargarDatos(controllerSplit.getLicencia());
	}

	public void cerrarAjustes() {
		vistaAjustes.getStage().close();
	}

	public void cerrarInscripcion() {
		vistaInscripcion.getStage().close();
	}

	public void cerrarEditar() {
		vistaEditar.getStage().close();
	}

	public void cerrarEditarTitular() {
		vistaEditarTitular.getStage().close();
	}

	public void cerrarCambioTitular() {
		vistaCambioTitular.getStage().close();
	}

	public void cerrarDetalles() {
		vistaDetalles.getStage().close();
	}

	public void crearUsuario() {
		modelo.crearUsuario(controllerAdminUsuarios.getDni(), controllerAdminUsuarios.getNombre());
	}

	public void editarUsuario(Usuario user) {
		modelo.editarUsuario(user.getId(), controllerAdminUsuarios.getDni(), controllerAdminUsuarios.getNombre());
	}

	public void eliminarUsuario(Usuario user) {
		modelo.eliminarUsuario(user.getId());
	}

	public void cargarConfigDb() {
		modelo.cargarConfigDbVista();
	}

	public void guardarConfigDb() {
		modelo.guardarConfigDb(controllerAjustesConexion.getServer(), controllerAjustesConexion.getBBDD(),
				controllerAjustesConexion.getUser(), controllerAjustesConexion.getPass());
	}

	public void insertLicencia() {
		Representante repre = new Representante("", controllerInscripcion.getnombreRep(),
				controllerInscripcion.getdniNieRep(), controllerInscripcion.getdireccionRep(),
				controllerInscripcion.getmunicipioRep(), controllerInscripcion.getcpRep(),
				controllerInscripcion.gettlfRep(), controllerInscripcion.getmovilRep(),
				controllerInscripcion.getfaxRep(), controllerInscripcion.getmailRep());
		Titular titular = new Titular("", controllerInscripcion.getnombreSol(), controllerInscripcion.getdniCifSol(),
				controllerInscripcion.getdireccionSol(), controllerInscripcion.getmunicipioSol(),
				controllerInscripcion.getcpSol(), controllerInscripcion.gettlfSol(),
				controllerInscripcion.getmovilSol(), controllerInscripcion.getfaxSol(),
				controllerInscripcion.getmailSol(), controllerInscripcion.gettipoSol(), repre, "0", "0", "0", "0", "1");
		Licencia licencia = new Licencia(controllerInscripcion.getnumRegistro(), controllerInscripcion.getfecha(),
				"En proceso", controllerInscripcion.gettipoAct(), controllerInscripcion.getdescripcionAct(),
				controllerInscripcion.getemplazamiento(), controllerInscripcion.getrefCatastral(),
				controllerInscripcion.gettipoSuelo(), controllerInscripcion.getcuota(),
				controllerInscripcion.getfotocopiaDNI() ? "1" : "0",
				controllerInscripcion.getfotocopiaImpuestoAct() ? "1" : "0",
				controllerInscripcion.getfotografias() ? "1" : "0",
				controllerInscripcion.getfotocopiaEscritura() ? "1" : "0",
				controllerInscripcion.getjustificantePago() ? "1" : "0",
				controllerInscripcion.getmemoriaAct() ? "1" : "0", controllerInscripcion.getplanosAct() ? "1" : "0",
				controllerInscripcion.getfotocopiaLicenciaObra() ? "1" : "0",
				controllerInscripcion.getcertificadoMod1() ? "1" : "0",
				controllerInscripcion.getcertificadoMod2() ? "1" : "0", controllerInscripcion.getcd() ? "1" : "0",
				controllerInscripcion.getotrasAutorizaciones() ? "1" : "0",
				controllerInscripcion.getcertificadoColegioOfi() ? "1" : "0", titular);
		modelo.insertarLicencia(licencia);
		modelo.cargarTablaLicencias();
		controllerSplit.initialize();
		cerrarInscripcion();
	}

	public void updateLicencia() {
		Licencia licencia = new Licencia(controllerEdit.getnumRegistro(), controllerEdit.getfecha(),
				controllerEdit.getEstado(), controllerEdit.gettipoAct(), controllerEdit.getdescripcionAct(),
				controllerEdit.getemplazamiento(), controllerEdit.getrefCatastral(), controllerEdit.gettipoSuelo(),
				controllerEdit.getcuota(), controllerEdit.getfotocopiaDNI() ? "1" : "0",
				controllerEdit.getfotocopiaImpuestoAct() ? "1" : "0", controllerEdit.getfotografias() ? "1" : "0",
				controllerEdit.getfotocopiaEscritura() ? "1" : "0", controllerEdit.getjustificantePago() ? "1" : "0",
				controllerEdit.getmemoriaAct() ? "1" : "0", controllerEdit.getplanosAct() ? "1" : "0",
				controllerEdit.getfotocopiaLicenciaObra() ? "1" : "0", controllerEdit.getcertificadoMod1() ? "1" : "0",
				controllerEdit.getcertificadoMod2() ? "1" : "0", controllerEdit.getcd() ? "1" : "0",
				controllerEdit.getotrasAutorizaciones() ? "1" : "0",
				controllerEdit.getcertificadoColegioOfi() ? "1" : "0");
		modelo.updateLicencia(licencia);
		modelo.cargarTablaLicencias();
		controllerSplit.initialize();
		cerrarEditar();
	}

	public void updateTitular() {
		Titular titular;
		if (controllerEditarTitular.gettipoSol().equals("Jurídica")) {
			Representante repre = new Representante(controllerEditarTitular.getidRep(),
					controllerEditarTitular.getnombreRep(), controllerEditarTitular.getdniNieRep(),
					controllerEditarTitular.getdireccionRep(), controllerEditarTitular.getmunicipioRep(),
					controllerEditarTitular.getcpRep(), controllerEditarTitular.gettlfRep(),
					controllerEditarTitular.getmovilRep(), controllerEditarTitular.getfaxRep(),
					controllerEditarTitular.getmailRep());
			titular = new Titular(controllerEditarTitular.getidPersona(), controllerEditarTitular.getnombreSol(),
					controllerEditarTitular.getdniCifSol(), controllerEditarTitular.getdireccionSol(),
					controllerEditarTitular.getmunicipioSol(), controllerEditarTitular.getcpSol(),
					controllerEditarTitular.gettlfSol(), controllerEditarTitular.getmovilSol(),
					controllerEditarTitular.getfaxSol(), controllerEditarTitular.getmailSol(),
					controllerEditarTitular.gettipoSol(), repre,
					controllerEditarTitular.getescrituraPropiedad() ? "1" : "0",
					controllerEditarTitular.getplanosDistribPropiedad() ? "1" : "0",
					controllerEditarTitular.getlicenciaAnteriorAportada() ? "1" : "0",
					controllerEditarTitular.getnumExpediente() ? "1" : "0", "0");
		} else {
			titular = new Titular(controllerEditarTitular.getidPersona(), controllerEditarTitular.getnombreSol(),
					controllerEditarTitular.getdniCifSol(), controllerEditarTitular.getdireccionSol(),
					controllerEditarTitular.getmunicipioSol(), controllerEditarTitular.getcpSol(),
					controllerEditarTitular.gettlfSol(), controllerEditarTitular.getmovilSol(),
					controllerEditarTitular.getfaxSol(), controllerEditarTitular.getmailSol(),
					controllerEditarTitular.gettipoSol(), controllerEditarTitular.getescrituraPropiedad() ? "1" : "0",
					controllerEditarTitular.getplanosDistribPropiedad() ? "1" : "0",
					controllerEditarTitular.getlicenciaAnteriorAportada() ? "1" : "0",
					controllerEditarTitular.getnumExpediente() ? "1" : "0", "0", "");
		}
		modelo.updateTitular(titular);
		modelo.cargarTablaLicencias();
		controllerSplit.initialize();
		cerrarEditarTitular();
	}

	public void nuevoTitular() {
		Titular titular;
		if (controllerCambioTitular.gettipoSol().equals("Jurídica")) {
			Representante repre = new Representante(controllerCambioTitular.getidRep(),
					controllerCambioTitular.getnombreRep(), controllerCambioTitular.getdniNieRep(),
					controllerCambioTitular.getdireccionRep(), controllerCambioTitular.getmunicipioRep(),
					controllerCambioTitular.getcpRep(), controllerCambioTitular.gettlfRep(),
					controllerCambioTitular.getmovilRep(), controllerCambioTitular.getfaxRep(),
					controllerCambioTitular.getmailRep());
			titular = new Titular(controllerCambioTitular.getidPersona(), controllerCambioTitular.getnombreSol(),
					controllerCambioTitular.getdniCifSol(), controllerCambioTitular.getdireccionSol(),
					controllerCambioTitular.getmunicipioSol(), controllerCambioTitular.getcpSol(),
					controllerCambioTitular.gettlfSol(), controllerCambioTitular.getmovilSol(),
					controllerCambioTitular.getfaxSol(), controllerCambioTitular.getmailSol(),
					controllerCambioTitular.gettipoSol(), repre,
					controllerCambioTitular.getescrituraPropiedad() ? "1" : "0",
					controllerCambioTitular.getplanosDistribPropiedad() ? "1" : "0",
					controllerCambioTitular.getlicenciaAnteriorAportada() ? "1" : "0",
					controllerCambioTitular.getnumExpediente() ? "1" : "0", "0");
		} else {
			titular = new Titular(controllerCambioTitular.getidPersona(), controllerCambioTitular.getnombreSol(),
					controllerCambioTitular.getdniCifSol(), controllerCambioTitular.getdireccionSol(),
					controllerCambioTitular.getmunicipioSol(), controllerCambioTitular.getcpSol(),
					controllerCambioTitular.gettlfSol(), controllerCambioTitular.getmovilSol(),
					controllerCambioTitular.getfaxSol(), controllerCambioTitular.getmailSol(),
					controllerCambioTitular.gettipoSol(), controllerCambioTitular.getescrituraPropiedad() ? "1" : "0",
					controllerCambioTitular.getplanosDistribPropiedad() ? "1" : "0",
					controllerCambioTitular.getlicenciaAnteriorAportada() ? "1" : "0",
					controllerCambioTitular.getnumExpediente() ? "1" : "0", "0", controllerCambioTitular.getfecha());
		}
		modelo.nuevoTitular(titular, controllerCambioTitular.getIdLicencia());
		modelo.cargarTablaLicencias();
		controllerSplit.initialize();
		cerrarCambioTitular();
	}

	public boolean comparadorFechas(Licencia licencia) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaSolicitud = null;
		Date fechaDesdeDt = null;
		Date fechaHastaDt = null;
		try {
			fechaDesdeDt = sdf.parse("00/00/0000");
			fechaHastaDt = sdf.parse("31/12/9999");
			fechaSolicitud = sdf.parse(licencia.getFechaSolicitud());
			fechaDesdeDt = java.sql.Date.valueOf(controllerSplit.getBuscador_fecha_desde());
			fechaHastaDt = java.sql.Date.valueOf(controllerSplit.getBuscador_fecha_hasta());

			if (fechaSolicitud.after(fechaDesdeDt) && fechaSolicitud.before(fechaHastaDt))
				return true;

		} catch (NullPointerException | ParseException ex) {
			if (fechaSolicitud.after(fechaDesdeDt) && fechaSolicitud.before(fechaHastaDt)) {
				return true;
			}
			return false;
		}
		return false;

	}

	public void testConexion() {
		if (modelo.testConnection(controllerAjustesConexion.getServer(), controllerAjustesConexion.getBBDD(),
				controllerAjustesConexion.getUser(), controllerAjustesConexion.getPass()) == null) {
			controllerAjustesConexion.testFail();
		} else {
			controllerAjustesConexion.testOK();
		}
	}

	public boolean testConexiónIni() {
		if (modelo.getConnection() == null) {
			Controller.metodos.error("Conexión a Base de Datos",
					"No se ha podido establecer conexión con la Base de Datos.");
			return false;
		}
		return true;
	}

	public void info(String title, String body) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(body);

		alert.showAndWait();
	}

	public void error(String title, String body) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(body);

		alert.showAndWait();
	}
}