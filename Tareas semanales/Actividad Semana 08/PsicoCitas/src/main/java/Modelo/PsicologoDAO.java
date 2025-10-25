package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsicologoDAO {

    public List<String> obtenerPacientes(int idPsicologo) {
        List<String> pacientes = new ArrayList<>();
        String sql = "SELECT DISTINCT pa.nombre FROM citas c "
                   + "JOIN pacientes pa ON c.id_paciente = pa.id_paciente "
                   + "WHERE c.id_psicologo = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idPsicologo);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                pacientes.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    public List<String> obtenerCitas(int idPsicologo) {
        List<String> citas = new ArrayList<>();
        String sql = "SELECT pa.nombre AS paciente, c.fecha, c.descripcion "
                   + "FROM citas c "
                   + "JOIN pacientes pa ON c.id_paciente = pa.id_paciente "
                   + "WHERE c.id_psicologo = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idPsicologo);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                citas.add("📅 " + rs.getString("fecha") + 
                          " | 👤 " + rs.getString("paciente") + 
                          " | 📝 " + rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener citas: " + e.getMessage());
        }
        return citas;
    }
}
