package es.colegio.vista;

import java.util.List;
import java.util.Scanner;

import es.colegio.modelo.Alumno;

public class AlumnoVista {
	 private final Scanner teclado;

	    public AlumnoVista() {
	        this.teclado = new Scanner(System.in);
	    }

	    public int mostrarMenu() {
	        System.out.println("\n--- GESTIÓN DE ALUMNOS ---");
	        System.out.println("1. Registrar Alumno");
	        System.out.println("2. Listar Alumnos");
	        System.out.println("3. Modificar Alumnos");
	        System.out.println("4. Eliminar Alumnos");
	        System.out.println("0. Salir");
	        System.out.print("Selecciona una opción: ");
	        return teclado.nextInt();
	    }

	    public Alumno pedirDatosAlumno() {
	        System.out.println("\n--- REGISTRAR NUEVO ALUMNO ---");
	        System.out.print("ID (Número entero): ");
	        int id = teclado.nextInt();
	        teclado.nextLine(); // Limpiar el buffer de entrada
	        System.out.print("Nombre: "); 
	        String nombre = teclado.nextLine();
	        System.out.print("Primer Apellido: ");
	        String ap1 = teclado.nextLine();
	        System.out.print("Segundo Apellido (Enter para saltar): "); 
	        String ap2 = teclado.nextLine();
	        if (ap2.trim().isEmpty()) ap2 = null;
	        System.out.print("Nota: "); 
	        float nota = teclado.nextFloat();
	        teclado.nextLine(); //limpiar buffer

	        return new Alumno(id, nombre, ap1, ap2, nota);
	    }

	    public void mostrarAlumnos(List<Alumno> alumnos) {
	        System.out.println("\n--- LISTADO DE ALUMNOS ---");
	        if (alumnos.isEmpty()) {
	            System.out.println("No hay alumnos registrados.");
	        } else {
	            for (Alumno a : alumnos) {
	                System.out.printf("ID: %d | Nota: %5.2f | %s %s %s %n", 
	                    a.getId(), a.getNota(), a.getNombre(), a.getApellido1(), 
	                    (a.getApellido2() != null ? a.getApellido2() : "") );
	            }
	        }
	    }

	    public void mostrarMensaje(String mensaje) {
	        System.out.println(" >> " + mensaje);
	    }
	    
	    public int pedirId(String operacion) {
	    	    System.out.print("Introduce el ID del alumno que deseas "+ operacion);
	    	    int id = teclado.nextInt();
	        teclado.nextLine(); //limpiar buffer
	        return id;
	    }
	    
	    public Alumno pedirDatosModificar(int id) {
	        teclado.nextLine(); // Limpiar buffer
	        System.out.println("\n--- NUEVOS DATOS DEL ALUMNO ---");
	        System.out.print("Nuevo nombre: ");
	        String nombre = teclado.nextLine();
	        System.out.print("Nuevo primer apellido: ");
	        String ape1 = teclado.nextLine();
	        System.out.print("Nuevo segundo apellido: ");
	        String ape2 = teclado.nextLine();
	        System.out.print("Nueva nota: ");
	        float nota = teclado.nextFloat();
	        teclado.nextLine(); // limpiar buffer
	        
	        return new Alumno(id, nombre, ape1, ape2, nota);
	    }
	    
	    public boolean confirmarEliminacion(int id) {
	        System.out.print("¿Estás seguro de eliminar al alumno con ID " + id + "? (S/N): ");
	        char respuesta = teclado.next().toUpperCase().charAt(0);
	        return respuesta == 'S';
	    }
	    
	    public boolean confirmarContinuar() {
	        System.out.print("¿Desea continuar? (S/N): ");
	        char respuesta = teclado.next().toUpperCase().charAt(0);
	        return respuesta == 'S';
	    }

}
