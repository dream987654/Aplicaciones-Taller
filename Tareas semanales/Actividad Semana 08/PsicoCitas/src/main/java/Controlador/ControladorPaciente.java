package Controlador;

import Modelo.Conexion;
import Vista.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorPaciente {

    private final PacienteVista pacienteVista;
    private final RegistrarCitaVista registrarCitaVista;
    private final LoginVista loginVista;
    private final Connection conn;
    private final int idPaciente;

    public ControladorPaciente(PacienteVista p, RegistrarCitaVista r, LoginVista l, int idPaciente) {
        this.pacienteVista = p;
        this.registrarCitaVista = r;
        this.loginVista = l;
        this.conn = Conexion.getConexion();
        this.idPaciente = idPaciente;

        // --- Botones ---
        pacienteVista.agregarVolverListener(e -> volverAlLogin());
        pacienteVista.agregarRegistrarCitaListener(e -> abrirRegistrarCita());
        pacienteVista.agregarSalirListener(e -> salirAplicacion());

        // --- Mostrar datos iniciales ---
        mostrarHistorial();
        mostrarMensajeDelDia();
    }

    // 🔹 Volver al login
    private void volverAlLogin() {
        pacienteVista.setVisible(false);
        loginVista.setVisible(true);
    }

    // 🔹 Abrir vista para registrar nueva cita
    private void abrirRegistrarCita() {
        try {
            registrarCitaVista.setVisible(true);
            pacienteVista.setVisible(false);
            new ControladorRegistrarCita(registrarCitaVista, idPaciente);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al abrir el registro de cita: " + ex.getMessage());
        }
    }

    // 🔹 Salir de la aplicación
    private void salirAplicacion() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Seguro que deseas salir de la aplicación?",
                "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Hasta luego.");
            System.exit(0);
        }
    }

    // 🔹 Mostrar historial de citas del paciente
    private void mostrarHistorial() {
        try {
            String sql = """
                SELECT c.fecha, c.hora, c.descripcion, u.nombre AS psicologo
                FROM citas c
                JOIN usuarios u ON c.id_psicologo = u.id
                WHERE c.id_paciente = ?
                ORDER BY c.fecha DESC, c.hora DESC
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Fecha");
            modelo.addColumn("Hora");
            modelo.addColumn("Psicólogo");
            modelo.addColumn("Motivo");

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getString("psicologo"),
                    rs.getString("descripcion")
                });
            }

            pacienteVista.getTablaHistorial().setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar historial: " + e.getMessage());
        }
    }

    // 🔹 Mostrar mensaje motivacional del día
    private void mostrarMensajeDelDia() {
        try {
            String[] mensajes = {
                "🌞 ¡Hoy es un gran día para cuidar de tu salud mental!",
                "💭 Recuerda: tus emociones también merecen atención.",
                "🌱 Un paso pequeño hoy puede significar mucho mañana.",
                "💬 No estás solo, hablar es el primer paso para sanar."
            };

            int aleatorio = (int) (Math.random() * mensajes.length);
            pacienteVista.setMensajeDia(mensajes[aleatorio]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar mensaje del día: " + e.getMessage());
        }
    }
}
