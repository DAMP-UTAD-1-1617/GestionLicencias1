package Launcher;

import Model.Model;
import View.VistaPrincipal;
import Controller.Controller;

public class Main {
	public static void main(String[] args){
		VistaPrincipal vista = new VistaPrincipal();
		Model modelo = new Model();
		Controller.metodos.setModel(modelo);
		Controller.metodos.setVistaPrincipal(vista);
		vista.launchView(args);
	}
}
