package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import Controller.Controller;
import com.mysql.jdbc.CallableStatement;

import View.ConexionLayoutController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	private String servidor;
	private String bbdd;
	private String usuario;
	private String clave;
	private ConexionLayoutController controllerAjustesConexion;
	private ObservableList<Licencia> licencias;

	public Model() {
		cargarConfigDb();
	}

	private void cargarConfigDb() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File config = new File("config/db.ini");
			if (config.exists()) {
				entrada = new FileInputStream(config);
				propiedades.load(entrada);
				servidor = propiedades.getProperty("Servidor");
				usuario = propiedades.getProperty("Usuario");
				bbdd = propiedades.getProperty("BBDD");
				clave = propiedades.getProperty("Clave");
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void cargarConfigDbVista() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File config = new File("config/db.ini");
			if (config.exists()) {
				entrada = new FileInputStream(config);
				propiedades.load(entrada);
				controllerAjustesConexion.cargarDatos(propiedades.getProperty("Servidor"),
						propiedades.getProperty("BBDD"), propiedades.getProperty("Usuario"),
						propiedades.getProperty("Clave"));
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void guardarConfigDb(String servidor, String baseDatos, String usuario, String clave) {
		Properties propiedades = new Properties();
		OutputStream salida = null;
		OutputStream salidaLog = null;

		try {
			File config = new File("config/db.ini");
			File log = new File("config/db.log");
			if (config.exists() && log.exists()) {
				salida = new FileOutputStream(config);
				salidaLog = new FileOutputStream(log, true);

				propiedades.setProperty("Servidor", servidor);
				propiedades.setProperty("BBDD", baseDatos);
				propiedades.setProperty("Usuario", usuario);
				propiedades.setProperty("Clave", clave);

				propiedades.store(salida, "Registro actual:");
				propiedades.store(salidaLog, "Nuevo registro");
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (salida != null) {
				try {
					salida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Connection getConnection() {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + this.servidor + "/" + this.bbdd, this.usuario,
					this.clave);
			return con;
		} catch (Exception e) {
			return null;
		}
	}

	public Connection testConnection(String server, String BBDD, String user, String pass) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + BBDD, user, pass);
			return con;
		} catch (Exception e) {
			return null;
		}
	}

	public void cargarTablaLicencias() {
		try {
			licencias = FXCollections.observableArrayList();
			Connection con = getConnection();

			ResultSet rs = con.createStatement().executeQuery("CALL getLicencias()");
			while (rs.next()) {
				licencias.add(new Licencia(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ObservableList<Usuario> getUsuarios() {
		ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
		try {
			Connection con = getConnection();
			ResultSet rs = con.createStatement().executeQuery("CALL getUsuarios()");
			while (rs.next()) {
				usuarios.add(new Usuario(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public String login(String dni) {
		String res = "";
		String procedimiento = "{call login(?)}";
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setString(1, dni);
			ctmt.execute();
			ResultSet rset = ctmt.getResultSet();
			rset.next();
			res = rset.getString(1);
			rset.close();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public String numRegistro() {
		String res = "";
		String procedimiento = "{call ultimoNumRegistro()}";
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.execute();
			ResultSet rset = ctmt.getResultSet();
			rset.next();
			res = rset.getString(1);
			rset.close();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public void setControllerAjustesConexion(ConexionLayoutController controllerAjustesConexion) {
		this.controllerAjustesConexion = controllerAjustesConexion;
	}

	public ObservableList<Licencia> getLicencias() {
		return licencias;
	}

	public Representante getRepresentante(String idTitular) {
		Representante repre = null;
		String procedimiento = "{call getRepresentante(?)}";
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setInt(1, Integer.parseInt(idTitular));
			ctmt.execute();
			ResultSet rset = ctmt.getResultSet();
			rset.next();
			repre = new Representante(rset.getString("id_representante"), rset.getString("nombre"),
					rset.getString("nif_nie"), rset.getString("direccion"), rset.getString("municipio"),
					rset.getString("cp"), rset.getString("tlf_fijo"), rset.getString("tlf_movil"),
					rset.getString("fax"), rset.getString("email"));
			rset.close();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return repre;
	}

	public ArrayList<Titular> getTitulares(String idSolicitud) {
		ArrayList<Titular> titulares = new ArrayList<Titular>();
		String procedimiento = "{call getTitulares(?)}";
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setInt(1, Integer.parseInt(idSolicitud));
			ctmt.execute();
			ResultSet rset = ctmt.getResultSet();
			while (rset.next()) {
				titulares.add(new Titular(rset.getString("id_persona"), rset.getString("nombre"), rset.getString("cif"),
						rset.getString("direccion"), rset.getString("municipio"), rset.getString("cp"),
						rset.getString("tlf_fijo"), rset.getString("tlf_movil"), rset.getString("fax"),
						rset.getString("email"), rset.getString("tipo"), rset.getString("estructura_propiedad"),
						rset.getString("planos_distribucion"), rset.getString("licencia_anterior"), rset.getString("numero_expediente"),
						rset.getString("primero"), rset.getString("fecha_fin")));
			}
			rset.close();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return titulares;
	}

	public void insertarLicencia(Licencia licencia) {
		String procedimiento = "";
		if (licencia.getTitularActual().getTipo() == "Jurídica") {
			procedimiento = "{call insertarRepresentante(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			try {
				Connection con = getConnection();
				CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
				ctmt.setString(1, licencia.getTitularActual().getRepresentante().getNombre());
				ctmt.setString(2, licencia.getTitularActual().getRepresentante().getCif());
				ctmt.setString(3, licencia.getTitularActual().getRepresentante().getDireccion());
				ctmt.setString(4, licencia.getTitularActual().getRepresentante().getMunicipio());
				ctmt.setString(5, licencia.getTitularActual().getRepresentante().getCp());
				ctmt.setInt(6, Integer.parseInt(licencia.getTitularActual().getRepresentante().getTlFijo()));
				ctmt.setInt(7, Integer.parseInt(licencia.getTitularActual().getRepresentante().getTlMovil()));
				ctmt.setInt(8, Integer.parseInt(licencia.getTitularActual().getRepresentante().getFax()));
				ctmt.setString(9, licencia.getTitularActual().getRepresentante().getEmail());
				ctmt.execute();
				ctmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			procedimiento = "{call insertarTitularConRep(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		} else {
			procedimiento = "{call insertarTitularSinRep(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		}
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setString(1, licencia.getTitularActual().getNombre());
			ctmt.setString(2, licencia.getTitularActual().getCif());
			ctmt.setString(3, licencia.getTitularActual().getDireccion());
			ctmt.setString(4, licencia.getTitularActual().getMunicipio());
			ctmt.setString(5, licencia.getTitularActual().getCp());
			ctmt.setInt(6, Integer.parseInt(licencia.getTitularActual().getTlFijo()));
			ctmt.setInt(7, Integer.parseInt(licencia.getTitularActual().getTlMovil()));
			ctmt.setInt(8, Integer.parseInt(licencia.getTitularActual().getFax()));
			ctmt.setString(9, licencia.getTitularActual().getEmail());
			ctmt.setString(10, licencia.getTitularActual().getTipo());
			ctmt.setInt(11, 0);
			ctmt.setInt(12, 0);
			ctmt.setInt(13, 0);
			ctmt.setInt(14, 0);
			ctmt.setInt(15, 1);
			ctmt.execute();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			procedimiento = "{call insertarSolicitud(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setInt(1, Integer.parseInt(licencia.getNRegistro()));
			ctmt.setString(2, licencia.getFechaSolicitud());
			ctmt.setString(3, licencia.getTipo());
			ctmt.setString(4, licencia.getDescripcionAct());
			ctmt.setString(5, licencia.getEmplazamiento());
			ctmt.setString(6, licencia.getRefCatastral());
			ctmt.setString(7, licencia.getTipoSuelo());
			ctmt.setFloat(8, Float.parseFloat(licencia.getCuota()));
			ctmt.setInt(9, Integer.parseInt(licencia.getFotocopiaDNI()));
			ctmt.setInt(10, Integer.parseInt(licencia.getFotocopiaImpuesto()));
			ctmt.setInt(11, Integer.parseInt(licencia.getFotografias()));
			ctmt.setInt(12, Integer.parseInt(licencia.getFotocopiaEscritura()));
			ctmt.setInt(13, Integer.parseInt(licencia.getJustificantePago()));
			ctmt.setInt(14, Integer.parseInt(licencia.getMemoriaActividad()));
			ctmt.setInt(15, Integer.parseInt(licencia.getPlanosActividad()));
			ctmt.setInt(16, Integer.parseInt(licencia.getFotocopiaLicenciaObra()));
			ctmt.setInt(17, Integer.parseInt(licencia.getCertificadoModelo1()));
			ctmt.setInt(18, Integer.parseInt(licencia.getCertificadoModelo2()));
			ctmt.setInt(19, Integer.parseInt(licencia.getCd()));
			ctmt.setInt(20, Integer.parseInt(licencia.getOtrasAutorizaciones()));
			ctmt.setInt(21, Integer.parseInt(licencia.getCertificadoColegioOficial()));
			ctmt.execute();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Controller.metodos.info("Insert Info", "La solicitud se ha insertado correctamente");
	}

	public void nuevoTitular(Titular titular, String idLicencia) {
		String procedimiento = "";
		if (titular.getTipo() == "Jurídica") {
			procedimiento = "{call insertarRepresentante(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			try {
				Connection con = getConnection();
				CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
				ctmt.setString(1, titular.getRepresentante().getNombre());
				ctmt.setString(2, titular.getRepresentante().getCif());
				ctmt.setString(3, titular.getRepresentante().getDireccion());
				ctmt.setString(4, titular.getRepresentante().getMunicipio());
				ctmt.setString(5, titular.getRepresentante().getCp());
				ctmt.setInt(6, Integer.parseInt(titular.getRepresentante().getTlFijo()));
				ctmt.setInt(7, Integer.parseInt(titular.getRepresentante().getTlMovil()));
				ctmt.setInt(8, Integer.parseInt(titular.getRepresentante().getFax()));
				ctmt.setString(9, titular.getRepresentante().getEmail());
				ctmt.execute();
				ctmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			procedimiento = "{call insertarTitularConRep(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		} else {
			procedimiento = "{call insertarTitularSinRep(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		}
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setString(1, titular.getNombre());
			ctmt.setString(2, titular.getCif());
			ctmt.setString(3, titular.getDireccion());
			ctmt.setString(4, titular.getMunicipio());
			ctmt.setString(5, titular.getCp());
			ctmt.setInt(6, Integer.parseInt(titular.getTlFijo()));
			ctmt.setInt(7, Integer.parseInt(titular.getTlMovil()));
			ctmt.setInt(8, Integer.parseInt(titular.getFax()));
			ctmt.setString(9, titular.getEmail());
			ctmt.setString(10, titular.getTipo());
			ctmt.setInt(11, Integer.parseInt(titular.getEstructuraPropiedad()));
			ctmt.setInt(12, Integer.parseInt(titular.getPlanosDistribucion()));
			ctmt.setInt(13, Integer.parseInt(titular.getLicenciaAnterior()));
			ctmt.setInt(14, Integer.parseInt(titular.getNumeroExpediente()));
			ctmt.setInt(15, 0);
			ctmt.execute();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			procedimiento = "{call cambioTitular(?, ?)}";
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setString(1, titular.getFechaFin());
			ctmt.setInt(2, Integer.parseInt(idLicencia));
			ctmt.execute();
			ctmt.close();
			con.close();
			Controller.metodos.info("Insert Info", "El cambio de titular se ha insertado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateLicencia(Licencia licencia) {
		String procedimiento = "";
		try {
			procedimiento = "{call updateSolicitud(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setInt(1, Integer.parseInt(licencia.getNRegistro()));
			ctmt.setString(2, licencia.getFechaSolicitud());
			ctmt.setString(3, licencia.getTipo());
			ctmt.setString(4, licencia.getDescripcionAct());
			ctmt.setString(5, licencia.getEmplazamiento());
			ctmt.setString(6, licencia.getRefCatastral());
			ctmt.setString(7, licencia.getTipoSuelo());
			ctmt.setFloat(8, Float.parseFloat(licencia.getCuota()));
			ctmt.setString(9, licencia.getEstado());
			ctmt.setInt(10, Integer.parseInt(licencia.getFotocopiaDNI()));
			ctmt.setInt(11, Integer.parseInt(licencia.getFotocopiaImpuesto()));
			ctmt.setInt(12, Integer.parseInt(licencia.getFotografias()));
			ctmt.setInt(13, Integer.parseInt(licencia.getFotocopiaEscritura()));
			ctmt.setInt(14, Integer.parseInt(licencia.getJustificantePago()));
			ctmt.setInt(15, Integer.parseInt(licencia.getMemoriaActividad()));
			ctmt.setInt(16, Integer.parseInt(licencia.getPlanosActividad()));
			ctmt.setInt(17, Integer.parseInt(licencia.getFotocopiaLicenciaObra()));
			ctmt.setInt(18, Integer.parseInt(licencia.getCertificadoModelo1()));
			ctmt.setInt(19, Integer.parseInt(licencia.getCertificadoModelo2()));
			ctmt.setInt(20, Integer.parseInt(licencia.getCd()));
			ctmt.setInt(21, Integer.parseInt(licencia.getOtrasAutorizaciones()));
			ctmt.setInt(22, Integer.parseInt(licencia.getCertificadoColegioOficial()));
			ctmt.execute();
			ctmt.close();
			con.close();
			Controller.metodos.info("Update Info", "La solicitud se ha actualizado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTitular(Titular titular) {
		String procedimiento = "";
		try {
			procedimiento = "{call updatePersona(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setInt(1, Integer.parseInt(titular.getIdPersona()));
			ctmt.setString(2, titular.getNombre());
			ctmt.setString(3, titular.getCif());
			ctmt.setString(4, titular.getDireccion());
			ctmt.setString(5, titular.getMunicipio());
			ctmt.setString(6, titular.getCp());
			ctmt.setInt(7, Integer.parseInt(titular.getTlFijo()));
			ctmt.setInt(8, Integer.parseInt(titular.getTlMovil()));
			ctmt.setInt(9, Integer.parseInt(titular.getFax()));
			ctmt.setString(10, titular.getEmail());
			ctmt.setString(11, titular.getTipo());
			ctmt.setInt(12, Integer.parseInt(titular.getEstructuraPropiedad()));
			ctmt.setInt(13, Integer.parseInt(titular.getPlanosDistribucion()));
			ctmt.setInt(14, Integer.parseInt(titular.getLicenciaAnterior()));
			ctmt.setInt(15, Integer.parseInt(titular.getNumeroExpediente()));
			ctmt.execute();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (titular.getRepresentante() != null) {
			if (titular.getRepresentante().getIdRepresentante().equals("")) {
				procedimiento = "{call insertarRepresentante(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
				try {
					Connection con = getConnection();
					CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
					ctmt.setString(1, titular.getRepresentante().getNombre());
					ctmt.setString(2, titular.getRepresentante().getCif());
					ctmt.setString(3, titular.getRepresentante().getDireccion());
					ctmt.setString(4, titular.getRepresentante().getMunicipio());
					ctmt.setString(5, titular.getRepresentante().getCp());
					ctmt.setInt(6, Integer.parseInt(titular.getRepresentante().getTlFijo()));
					ctmt.setInt(7, Integer.parseInt(titular.getRepresentante().getTlMovil()));
					ctmt.setInt(8, Integer.parseInt(titular.getRepresentante().getFax()));
					ctmt.setString(9, titular.getRepresentante().getEmail());
					ctmt.execute();
					ctmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				procedimiento = "{call updateRepPersona(?)}";
				try {
					Connection con = getConnection();
					CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
					ctmt.setInt(1, Integer.parseInt(titular.getIdPersona()));
					ctmt.execute();
					ctmt.close();
					con.close();
					Controller.metodos.info("Update Info", "El titular se ha actualizado correctamente");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					procedimiento = "{call updateRepresentante(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
					Connection con = getConnection();
					CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
					ctmt.setInt(1, Integer.parseInt(titular.getRepresentante().getIdRepresentante()));
					ctmt.setString(2, titular.getRepresentante().getNombre());
					ctmt.setString(3, titular.getRepresentante().getCif());
					ctmt.setString(4, titular.getRepresentante().getDireccion());
					ctmt.setString(5, titular.getRepresentante().getMunicipio());
					ctmt.setString(6, titular.getRepresentante().getCp());
					ctmt.setInt(7, Integer.parseInt(titular.getRepresentante().getTlFijo()));
					ctmt.setInt(8, Integer.parseInt(titular.getRepresentante().getTlMovil()));
					ctmt.setInt(9, Integer.parseInt(titular.getRepresentante().getFax()));
					ctmt.setString(10, titular.getRepresentante().getEmail());
					ctmt.execute();
					ctmt.close();
					con.close();
					Controller.metodos.info("Update Info", "El titular se ha actualizado correctamente");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void crearUsuario(String dni, String nombre) {
		String procedimiento = "{call crearUsuario(?, ?)}";
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setString(1, dni);
			ctmt.setString(2, nombre);
			ctmt.execute();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarUsuario(String id, String dni, String nombre) {
		String procedimiento = "{call editarUsuario(?, ?, ?)}";
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setString(1, id);
			ctmt.setString(2, dni);
			ctmt.setString(3, nombre);
			ctmt.execute();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarUsuario(String id) {
		String procedimiento = "{call eliminarUsuario(?)}";
		try {
			Connection con = getConnection();
			CallableStatement ctmt = (CallableStatement) con.prepareCall(procedimiento);
			ctmt.setString(1, id);
			ctmt.execute();
			ctmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
