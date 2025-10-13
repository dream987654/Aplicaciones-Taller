package Modelo;

import java.sql.*;

public class UsuarioDAO {
    public boolean registrarUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios(nombre, usuario, clave, tipo) VALUES(?, ?, ?, ?)";
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, u.getNombre());
            pst.setString(2, u.getUsuario());
            pst.setString(3, u.getClave());
            pst.setString(4, u.getTipo());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario validarLogin(String usuario, String clave, String tipo) {
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND clave=? AND tipo=?";
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, usuario);
            pst.setString(2, clave);
            pst.setString(3, tipo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("usuario"),
                    rs.getString("clave"),
                    rs.getString("tipo")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en validarLogin: " + e.getMessage());
        }
        return null;
    }
}
