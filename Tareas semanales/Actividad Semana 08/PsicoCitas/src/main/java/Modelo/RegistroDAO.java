package Modelo;

import java.sql.*;

public class RegistroDAO {

    public boolean registrar(Registro reg) {
        String sql = "INSERT INTO usuarios (rol, nombre, celular, usuario, clave) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reg.getRol());
            ps.setString(2, reg.getNombre());
            ps.setString(3, reg.getCelular());
            ps.setString(4, reg.getUsuario());
            ps.setString(5, reg.getClave());

            ps.executeUpdate();
            System.out.println("✅ Usuario registrado correctamente.");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}
