package es.colegio.modelo;

public class Alumno {
	 private int id;
	    private String nombre;
	    private String apellido1;
	    private String apellido2;
	    private float nota;

	    public Alumno() {}

	    public Alumno(int id, String nombre, String apellido1, String apellido2, float nota) {
	        this.id = id;
	        this.nombre = nombre;
	        this.apellido1 = apellido1;
	        this.apellido2 = apellido2;
	        this.nota = nota;
	    }

	    // Getters y Setters
	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }
	    public String getNombre() { return nombre; }
	    public void setNombre(String nombre) { this.nombre = nombre; }
	    public String getApellido1() { return apellido1; }
	    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }
	    public String getApellido2() { return apellido2; }
	    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }
	    public float getNota() { return nota; }
	    public void setNota(float nota) { this.nota = nota; }

}
