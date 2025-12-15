package Datos;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    
    public Usuario validar(String email, String password) {
        Usuario usu = null;
        String sql = "SELECT * FROM usuarios WHERE email=? AND password=?";
        
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                usu = new Usuario();
                usu.setId(rs.getInt("id"));
                // OJO: Asegúrate que en tu base de datos la columna se llame 'nombres' o 'nombre_completo'
                // Si te da error, cambia "nombres" por el nombre exacto de tu columna en MySQL
                usu.setNombre(rs.getString("nombre")); 
                usu.setRol(rs.getString("rol"));
            }
            // Cerramos conexiones para no saturar
            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            System.out.println("Error en Login: " + e.getMessage());
        }
        return usu;
    }
}