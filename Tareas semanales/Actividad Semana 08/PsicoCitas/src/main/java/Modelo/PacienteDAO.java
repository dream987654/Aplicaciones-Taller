package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public List<String> obtenerHistorialCitas(int idPaciente) {
        List<String> historial = new ArrayList<>();
        String sql = "SELECT c.fecha, p.nombre AS psicologo, c.descripcion "
                   + "FROM citas c "
                   + "JOIN psicologos p ON c.id_psicologo = p.id_psicologo "
                   + "WHERE c.id_paciente = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idPaciente);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                historial.add("📅 " + rs.getString("fecha") + 
                              " | 👨‍⚕️ " + rs.getString("psicologo") + 
                              " | 📝 " + rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener historial: " + e.getMessage());
        }
        return historial;
    }
}
