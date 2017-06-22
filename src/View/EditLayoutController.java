package View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import Controller.Controller;
import Model.Licencia;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditLayoutController {

	/* Datos generales solicitud */
	@FXML
	private TextField numRegistro;
	@FXML
	private TextField descripcionAct;
	@FXML
	private TextField refCatastral;
	@FXML
	private TextField cuota;
	@FXML
	private TextField tipoAct;
	@FXML
	private TextField emplazamiento;
	@FXML
	private ComboBox<String> tipoSuelo;
	@FXML
	private DatePicker fecha;
	@FXML
	private DatePicker fechaInicio;
	@FXML
	private ComboBox<String> estado;

	
	/* Documentación aportada */

	/* Documentación actividad */
	@FXML
	private CheckBox fotocopiaDNI;
	@FXML
	private CheckBox fotocopiaImpuestoAct;
	@FXML
	private CheckBox fotografias;
	@FXML
	private CheckBox fotocopiaEscritura;
	@FXML
	private CheckBox justificantePago;

	/* Documentación técnica */

	@FXML
	private CheckBox memoriaAct;
	@FXML
	private CheckBox planosAct;
	@FXML
	private CheckBox fotocopiaLicenciaObra;
	@FXML
	private CheckBox certificadoMod1;
	@FXML
	private CheckBox certificadoMod2;
	@FXML
	private CheckBox cd;
	@FXML
	private CheckBox otrasAutorizaciones;
	@FXML
	private CheckBox certificadoColegioOfi;

	/* Botones */

	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;

	public void initialize() {
		tipoSuelo.getItems().addAll("Urbano", "Rústico");
		estado.getItems().addAll("En proceso", "Aceptada", "Rechazada");
		Controller.metodos.setControllerEdit(this);
		btnAceptar.setOnAction(e -> aceptar());
	}

	public void cargarDatos(Licencia licencia) {

		/* Licencia */
		numRegistro.setText(licencia.getNRegistro());
		descripcionAct.setText(licencia.getDescripcionAct());
		refCatastral.setText(licencia.getRefCatastral());
		cuota.setText(licencia.getCuota());
		tipoAct.setText(licencia.getTipo());
		emplazamiento.setText(licencia.getEmplazamiento());
		tipoSuelo.setValue(licencia.getTipoSuelo());
		estado.setValue(licencia.getEstado());

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = format.parse(licencia.getFechaSolicitud());
			LocalDate dt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			fecha.setValue(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* Documentación */
		fotocopiaDNI.setSelected(licencia.getFotocopiaDNI().equals("0") ? false : true);
		fotocopiaImpuestoAct.setSelected(licencia.getFotocopiaImpuesto().equals("0") ? false : true);
		fotografias.setSelected(licencia.getFotografias().equals("0") ? false : true);
		fotocopiaEscritura.setSelected(licencia.getFotocopiaEscritura().equals("0") ? false : true);
		justificantePago.setSelected(licencia.getJustificantePago().equals("0") ? false : true);
		memoriaAct.setSelected(licencia.getMemoriaActividad().equals("0") ? false : true);
		planosAct.setSelected(licencia.getPlanosActividad().equals("0") ? false : true);
		fotocopiaLicenciaObra.setSelected(licencia.getFotocopiaLicenciaObra().equals("0") ? false : true);
		certificadoMod1.setSelected(licencia.getCertificadoModelo1().equals("0") ? false : true);
		certificadoMod2.setSelected(licencia.getCertificadoModelo2().equals("0") ? false : true);
		cd.setSelected(licencia.getCd().equals("0") ? false : true);
		otrasAutorizaciones.setSelected(licencia.getOtrasAutorizaciones().equals("0") ? false : true);
		certificadoColegioOfi.setSelected(licencia.getCertificadoColegioOficial().equals("0") ? false : true);

	}

	public void cerrar() {
		Controller.metodos.cerrarEditar();
	}

	public void aceptar() {
		Controller.metodos.updateLicencia();
	}	
	
	public String getEstado(){
		return estado.getValue();
	}

	public String getnumRegistro() {
		return numRegistro.getText();
	}

	public String getdescripcionAct() {
		return descripcionAct.getText();
	}

	public String getrefCatastral() {
		return refCatastral.getText();
	}

	public String getcuota() {
		return cuota.getText();
	}

	public String gettipoAct() {
		return tipoAct.getText();
	}

	public String getemplazamiento() {
		return emplazamiento.getText();
	}

	public String gettipoSuelo() {
		return tipoSuelo.getValue();
	}

	public String getfecha() {
		return fecha.getValue().toString();
	}

	public boolean getfotocopiaDNI() {
		return fotocopiaDNI.isSelected();
	}

	public boolean getfotocopiaImpuestoAct() {
		return fotocopiaImpuestoAct.isSelected();
	}

	public boolean getfotografias() {
		return fotografias.isSelected();
	}

	public boolean getfotocopiaEscritura() {
		return fotocopiaEscritura.isSelected();
	}

	public boolean getjustificantePago() {
		return justificantePago.isSelected();
	}

	/* Documentación técnica */

	public boolean getmemoriaAct() {
		return memoriaAct.isSelected();
	}

	public boolean getplanosAct() {
		return planosAct.isSelected();
	}

	public boolean getfotocopiaLicenciaObra() {
		return fotocopiaLicenciaObra.isSelected();
	}

	public boolean getcertificadoMod1() {
		return certificadoMod1.isSelected();
	}

	public boolean getcertificadoMod2() {
		return certificadoMod2.isSelected();
	}

	public boolean getcd() {
		return cd.isSelected();
	}

	public boolean getotrasAutorizaciones() {
		return otrasAutorizaciones.isSelected();
	}

	public boolean getcertificadoColegioOfi() {
		return certificadoColegioOfi.isSelected();
	}
}
