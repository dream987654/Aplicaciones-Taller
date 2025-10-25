package Controlador;

import Modelo.Conexion;
import Vista.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ControladorLogin {
    private final LoginVista loginVista;
    private Connection conn;

    public ControladorLogin(LoginVista loginVista) {
        this.loginVista = loginVista;
        this.conn = Conexion.getConexion();

        // Listener botón Ingresar
        this.loginVista.agregarLoginListener(e -> autenticarUsuario());

        // Listener botón Registrar
        this.loginVista.agregarRegistroListener(e -> abrirRegistro());
    }

    private void autenticarUsuario() {
        String usuario = loginVista.getUsuario();
        String clave = loginVista.getClave();
        String rol = loginVista.getRol();

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe completar usuario y contraseña");
            return;
        }

        try {
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ? AND rol = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ps.setString(3, rol);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("id");
                JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario);
                loginVista.setVisible(false);

                // Abrir vista según el rol
                if (rol.equalsIgnoreCase("Paciente")) {
                    int idPaciente = obtenerIdPaciente(idUsuario);

                    if (idPaciente == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontró registro del paciente.");
                        loginVista.setVisible(true);
                        return;
                    }

                    // Crear vistas
                    PacienteVista pv = new PacienteVista();
                    RegistrarCitaVista rcv = new RegistrarCitaVista();

                    // Controlador Paciente (con ID)
                    new ControladorPaciente(pv, rcv, loginVista, idPaciente);
                    pv.setVisible(true);

                } else if (rol.equalsIgnoreCase("Psicólogo")) {
                    int idPsicologo = obtenerIdPsicologo(idUsuario);

                    if (idPsicologo == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontró registro del psicólogo.");
                        loginVista.setVisible(true);
                        return;
                    }

                    // Crear vista
                    PsicologoVista psicoVista = new PsicologoVista();

                    // Controlador Psicólogo (con ID)
                    new ControladorPsicologo(psicoVista, loginVista, idPsicologo);
                    psicoVista.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Rol no reconocido");
                    loginVista.setVisible(true);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al autenticar: " + e.getMessage());
        }
    }

    // 🔹 Buscar ID del paciente por id_usuario
    private int obtenerIdPaciente(int idUsuario) {
        try {
            String sql = "SELECT id FROM pacientes WHERE id_usuario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener paciente: " + e.getMessage());
        }
        return -1;
    }

    // 🔹 Buscar ID del psicólogo por id_usuario
    private int obtenerIdPsicologo(int idUsuario) {
        try {
            String sql = "SELECT id FROM psicologos WHERE id_usuario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener psicólogo: " + e.getMessage());
        }
        return -1;
    }

    private void abrirRegistro() {
        loginVista.dispose();
        RegistroVista rv = new RegistroVista();
        new ControladorRegistro(rv);
        rv.setVisible(true);
    }
}
