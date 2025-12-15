package Datos;

import Modelo.Tesis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TesisDAO {
    
    // Método para listar las tesis asignadas a un docente específico
    public List<Tesis> listarPorDocente(int idDocente) {
        List<Tesis> lista = new ArrayList<>();
        // Unimos la tabla tesis con usuarios para saber el nombre del alumno
        String sql = "SELECT t.id_tesis, t.titulo, t.nota, t.estado, u.nombre as alumno " +
                     "FROM tesis t " +
                     "INNER JOIN usuarios u ON t.id_alumno = u.id " +
                     "WHERE t.id_jurado = ?";
        
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idDocente); // Solo traemos las de ESTE profe
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tesis t = new Tesis();
                t.setId(rs.getInt("id_tesis"));
                t.setTitulo(rs.getString("titulo"));
                t.setNota(rs.getInt("nota"));
                t.setEstado(rs.getString("estado"));
                t.setNombreAlumno(rs.getString("alumno"));
                lista.add(t);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // Método para poner nota y cerrar la tesis
    public boolean calificarTesis(int idTesis, int nota) {
        boolean r = false;
        String sql = "UPDATE tesis SET nota=?, estado='Calificado' WHERE id_tesis=?";
        
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setInt(2, idTesis);
            
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas > 0) {
                r = true;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
    // Método para que el alumno vea SU tesis
    public Tesis obtenerTesisPorAlumno(int idAlumno) {
        Tesis t = null;
        String sql = "SELECT * FROM tesis WHERE id_alumno = ?";
        
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = new Tesis();
                t.setId(rs.getInt("id_tesis"));
                t.setTitulo(rs.getString("titulo"));
                t.setNota(rs.getInt("nota"));
                t.setEstado(rs.getString("estado"));
                // Aquí no necesitamos el nombre del alumno porque ya sabemos quién es
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    
    // 1. PODER DE VER TODO (Para el Admin)
    public List<Tesis> listarTodas() {
        List<Tesis> lista = new ArrayList<>();
        String sql = "SELECT t.id_tesis, t.titulo, t.nota, t.estado, u.nombre as alumno " +
                     "FROM tesis t " +
                     "INNER JOIN usuarios u ON t.id_alumno = u.id";
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tesis t = new Tesis();
                t.setId(rs.getInt("id_tesis"));
                t.setTitulo(rs.getString("titulo"));
                t.setNota(rs.getInt("nota"));
                t.setEstado(rs.getString("estado"));
                t.setNombreAlumno(rs.getString("alumno"));
                lista.add(t);
            }
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    // 2. PODER DE CREAR (Asignar tema a alumno y jurado)
    public boolean crearTesis(String titulo, int idAlumno, int idJurado) {
        boolean r = false;
        String sql = "INSERT INTO tesis (titulo, id_alumno, id_jurado, estado) VALUES (?, ?, ?, 'Pendiente')";
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idJurado);
            if(ps.executeUpdate() > 0) r = true;
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
        return r;
    }
}