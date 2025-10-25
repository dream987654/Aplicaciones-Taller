package Modelo;
import java.sql.*;

public class UsuarioDAO {
    
    public boolean validarLogin(String usuario, String clave, String rol) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ? AND rol = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ps.setString(3, rol);
            
            ResultSet rs = ps.executeQuery();
            return rs.next(); // si encuentra el usuario
        
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarUsuario(String nombre, String celular, String usuario, String clave, String rol) {
        String sql = "INSERT INTO usuarios (nombre, celular, usuario, clave, rol) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, celular);
            ps.setString(3, usuario);
            ps.setString(4, clave);
            ps.setString(5, rol);

            ps.executeUpdate();
            return true;
        
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}
