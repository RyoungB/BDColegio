package es.colegio.controlador;

import java.util.List;

import es.colegio.modelo.Alumno;
import es.colegio.modelo.AlumnoDAO;
import es.colegio.vista.AlumnoVista;

public class AlumnoControlador {
	   private final AlumnoDAO dao;
	    private final AlumnoVista vista;

	    public AlumnoControlador(AlumnoVista vista, AlumnoDAO dao) {
	        this.vista = vista;
	        this.dao = dao;
	    }

	    public void iniciar() {
	        int opcion;
	        do {
	            opcion = vista.mostrarMenu();
	            switch (opcion) {
	                case 1 -> registrarAlumnos();
	                case 2 -> listarAlumnos();
	                case 3 -> modificarAlumnos();
	                case 4 -> eliminarAlumnos();
	                case 0 -> vista.mostrarMensaje("Aplicación finalizada.");
	                default -> vista.mostrarMensaje("Opción no válida.");
	            }
	        } while (opcion != 0);
	    }

	    
	    /*
	     * Añade alumnos hasta que el usuario confirme que desea finalizar
	     */
	    private void registrarAlumnos() {
	    	    do {
		        // Pedimos los datos a través de la vista
		        Alumno nuevoAlumno = vista.pedirDatosAlumno();
		        
		        // Enviamos los datos al modelo
		        boolean exito = dao.insertar(nuevoAlumno);
		        
		        // Le decimos a la vista que informe del resultado
		        if (exito) {
		            vista.mostrarMensaje("¡Alumno guardado con éxito!");
		        } else {
		            vista.mostrarMensaje("Error: No se pudo guardar el alumno.");
		        }
	    	    }while (vista.confirmarContinuar());
	    }

	    /*
	     * Obtenemos el listado de alumnos de la base de datos y se los enviamos a la vista
	     * para que los muestre en pantalla
	     */
	    private void listarAlumnos() {
	        // Obtenemos los datos del modelo
	        List<Alumno> listado = dao.obtenerListado();
	        
	        // Le pasamos los datos a la vista para que los muestre en pantalla
	        vista.mostrarAlumnos(listado);
	    }
	    
	    
	    // Modificar alumnos
	    private void modificarAlumnos() {
	        int id = vista.pedirId("modificar");
	        
	        // comprobamos si existe el código de alumno que queremos modificar
	        if (!dao.existeId(id)) {
	        	   vista.mostrarMensaje("Error: el alumno no existe");
	        	   return;
	        }
	        Alumno alumno = vista.pedirDatosModificar(id);
	        if (dao.modificar(alumno)) 
	        	   vista.mostrarMensaje("Alumno modificado correctamente");
	        	   else
	        		   vista.mostrarMensaje("Error al modificar alumno");    		   
	     }
	  
	    
	     // Eliminar alumnos
	    private void eliminarAlumnos() {
	    		int id = vista.pedirId("eliminar");
	    		
	    		// comprobamos si existe el código de alumno que queremos eliminar
	            if (!dao.existeId(id)) {
	            	   vista.mostrarMensaje("Error: el alumno no existe");
	            	   return;
	            }
	    		if (vista.confirmarEliminacion(id)) {
	    			if (dao.eliminar(id))
	    				vista.mostrarMensaje("El alumno ha sido borrado");
	    			else
	    				vista.mostrarMensaje("No se ha encontrado el alumno con el id indicado");
	        }else 
				vista.mostrarMensaje("Operación de borrado cancelada por el usuario");
		
	    }	
}
