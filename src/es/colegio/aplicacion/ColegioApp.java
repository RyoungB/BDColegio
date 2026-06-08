package es.colegio.aplicacion;

import es.colegio.controlador.AlumnoControlador;
import es.colegio.modelo.AlumnoDAO;
import es.colegio.vista.AlumnoVista;

public class ColegioApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Creamos un objeto de la clase principal para sacar la lógica del contexto estatico
		ColegioApp app = new ColegioApp();
		app.ejecutar();

	}
	
	public void ejecutar() {
		AlumnoVista vista = new AlumnoVista();
		AlumnoDAO dao = new AlumnoDAO();
		
		
		AlumnoControlador controlador = new AlumnoControlador(vista, dao);
		controlador.iniciar();
		
	}

}
