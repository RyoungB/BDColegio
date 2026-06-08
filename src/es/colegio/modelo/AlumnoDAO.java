package es.colegio.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.colegio.config.Conexion;

public class AlumnoDAO {
	// Insertar alumnos a la base de datos
    public boolean insertar(Alumno alumno) {
        String sql = "INSERT INTO alumnos (id, nombre, apellido1, apellido2, nota) VALUES (?, ?, ?, ?, ?)";
     
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            sentencia.setInt(1, alumno.getId());
            sentencia.setString(2, alumno.getNombre());
            sentencia.setString(3, alumno.getApellido1());
            sentencia.setString(4, alumno.getApellido2());
            sentencia.setFloat(5, alumno.getNota());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    // obtener un listado de todos los alumnos
    public List<Alumno> obtenerListado() {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql); 
        		
            ResultSet resultado = sentencia.executeQuery()) {
            
            while (resultado.next()) {
                lista.add(new Alumno(
                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido1"),
                    resultado.getString("apellido2"),
                    resultado.getFloat("nota")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
    
 // Modificar alumnos
    public boolean modificar(Alumno alumno) {
        // Buscamos al alumno por su ID y actualizamos sus otros campos
        String sql = "UPDATE alumnos SET nombre = ?, apellido1 = ?, apellido2 = ?, nota = ? WHERE id = ?";
        
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            // El orden de los parámetros debe coincidir con el establecido en sql de arriba:
            sentencia.setString(1, alumno.getNombre());
            sentencia.setString(2, alumno.getApellido1());
            sentencia.setString(3, alumno.getApellido2());
            sentencia.setFloat(4, alumno.getNota());
            sentencia.setInt(5, alumno.getId()); // El ID va al final por el WHERE
            
            return sentencia.executeUpdate() > 0; // Devuelve true si modificó la fila
        } catch (SQLException e) {
            System.err.println("Error al modificar alumno: " + e.getMessage());
            return false;
        }
    }

    // Eoiminar alumnos
    public boolean eliminar(int id) {
        // Para eliminar solo necesitamos el ID del alumno
        String sql = "DELETE FROM alumnos WHERE id = ?";
        
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            sentencia.setInt(1, id);
            
            return sentencia.executeUpdate() > 0; // Devuelve true si eliminó la fila
        } catch (SQLException e) {
            System.err.println("Error al eliminar alumno: " + e.getMessage());
            return false;
        }
    }
    
    
    // Comprobar si un código de alumno existe en la base de datos
    public boolean existeId(int id) {
        String sql = "SELECT COUNT(*) FROM alumnos WHERE id = ?";
        
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            sentencia.setInt(1, id);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    // Si el conteo es mayor que 0, es que el alumno existe
                    return resultado.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia del ID: " + e.getMessage());
        }
        return false;
    }


}
