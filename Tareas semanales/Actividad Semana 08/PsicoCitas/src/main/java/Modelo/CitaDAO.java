package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public boolean registrarCita(int idPaciente, int idPsicologo, String fecha, String descripcion) {
        String sql = "INSERT INTO citas (id_paciente, id_psicologo, fecha, descripcion) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPaciente);
            ps.setInt(2, idPsicologo);
            ps.setString(3, fecha);
            ps.setString(4, descripcion);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar cita: " + e.getMessage());
            return false;
        }
    }

    public List<String> obtenerCitas() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT p.nombre AS paciente, s.nombre AS psicologo, c.fecha, c.descripcion "
                   + "FROM citas c "
                   + "JOIN pacientes p ON c.id_paciente = p.id_paciente "
                   + "JOIN psicologos s ON c.id_psicologo = s.id_psicologo";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add("📅 " + rs.getString("fecha") + 
                          " | 👤 " + rs.getString("paciente") + 
                          " | 👨‍⚕️ " + rs.getString("psicologo") + 
                          " | 📝 " + rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener citas: " + e.getMessage());
        }
        return lista;
    }
}
