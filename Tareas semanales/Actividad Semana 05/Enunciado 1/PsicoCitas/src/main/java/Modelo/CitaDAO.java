package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    public boolean registrarCita(Cita c) {
        String sql = "INSERT INTO citas(id_paciente, id_psicologo, horario, descripcion) VALUES(?, ?, ?, ?)";
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, c.getIdPaciente());
            pst.setInt(2, c.getIdPsicologo());
            pst.setString(3, c.getHorario());
            pst.setString(4, c.getDescripcion());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar cita: " + e.getMessage());
            return false;
        }
    }

    public List<Cita> obtenerCitasPorPaciente(int idPaciente) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas WHERE id_paciente=?";
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idPaciente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                citas.add(new Cita(
                    rs.getInt("id"),
                    rs.getInt("id_paciente"),
                    rs.getInt("id_psicologo"),
                    rs.getString("horario"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener citas: " + e.getMessage());
        }
        return citas;
    }
}
