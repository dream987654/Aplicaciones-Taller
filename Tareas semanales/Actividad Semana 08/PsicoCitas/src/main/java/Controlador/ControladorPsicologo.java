package Controlador;

import Modelo.Conexion;
import Vista.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorPsicologo {

    private final PsicologoVista psicologoVista;
    private final LoginVista loginVista;
    private final Connection conn;
    private final int idPsicologo; // Identificador del psicólogo logueado

    public ControladorPsicologo(PsicologoVista p, LoginVista l, int idPsicologo) {
        this.psicologoVista = p;
        this.loginVista = l;
        this.idPsicologo = idPsicologo;
        this.conn = Conexion.getConexion();

        // --- Botón "Volver al login" ---
        psicologoVista.agregarVolverListener(e -> {
            psicologoVista.setVisible(false);
            loginVista.setVisible(true);
        });

        // --- Botón "Salir" ---
        psicologoVista.agregarSalirListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Seguro que deseas salir de la aplicación?",
                    "Confirmar salida", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Hasta luego.");
                System.exit(0);
            }
        });

        // --- Botón "Ver Agenda" ---
        psicologoVista.agregarAgendaListener(e -> cargarCitas());

        // --- Mensaje del día ---
        mostrarMensajeDelDia();
    }

    // ---------- MÉTODOS PRINCIPALES ----------

    private void cargarCitas() {
        try {
            String sql = """
                SELECT c.fecha, c.hora, c.descripcion, u.nombre AS paciente
                FROM citas c
                JOIN usuarios u ON c.id_paciente = u.id_usuario
                WHERE c.id_psicologo = ?
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPsicologo);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Fecha");
            modelo.addColumn("Hora");
            modelo.addColumn("Paciente");
            modelo.addColumn("Motivo");

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getString("paciente"),
                    rs.getString("descripcion")
                });
            }

            psicologoVista.getTablaCitas().setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar citas: " + e.getMessage());
        }
    }

    private void mostrarMensajeDelDia() {
        try {
            String[] mensajes = {
                "🧠 Cada sesión es una oportunidad para generar cambio.",
                "🌼 Escuchar también sana.",
                "✨ Tu trabajo marca una diferencia real en las personas.",
                "💬 Cada palabra puede ser el inicio de una transformación."
            };

            int aleatorio = (int) (Math.random() * mensajes.length);
            String mensaje = mensajes[aleatorio];

            psicologoVista.setMensaje(mensaje); // ✅ Usamos el nuevo método de la vista

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar mensaje del día: " + e.getMessage());
        }
    }
}
