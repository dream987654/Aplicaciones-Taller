package Controlador;

import Modelo.Conexion;
import Vista.LoginVista;
import Vista.RegistrarCitaVista;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class ControladorRegistrarCita {
    private final RegistrarCitaVista vista;
    private final Connection conn;

    public ControladorRegistrarCita(RegistrarCitaVista vista, int idPaciente) {
        this.vista = vista;
        this.conn = Conexion.getConexion();

        // Cargar psicólogos en el combo
        cargarPsicologos();

        // Asignar listeners
        this.vista.agregarGuardarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCita();
            }
        });

        this.vista.agregarCancelarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenu();
            }
        });
    }

    // ---------- MÉTODOS PRINCIPALES ----------

    private void cargarPsicologos() {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre FROM usuarios WHERE rol = 'Psicologo'")) {

            java.util.List<String> lista = new java.util.ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
            vista.setPsicologos(lista.toArray(new String[0]));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar psicólogos: " + e.getMessage());
        }
    }

    private void guardarCita() {
        String nombrePsicologo = vista.getPsicologo();
        String fecha = vista.getFecha();
        String hora = vista.getHora();
        String motivo = vista.getMotivo();

        if (nombrePsicologo.isEmpty() || fecha.isEmpty() || hora.isEmpty() || motivo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
            return;
        }

        try {
            // ✅ Buscar el ID real del psicólogo por su nombre
            int idPsicologo = -1;
            String sqlId = "SELECT id_usuario FROM usuarios WHERE nombre = ? AND rol = 'Psicologo'";
            try (PreparedStatement psId = conn.prepareStatement(sqlId)) {
                psId.setString(1, nombrePsicologo);
                try (ResultSet rs = psId.executeQuery()) {
                    if (rs.next()) {
                        idPsicologo = rs.getInt("id_usuario");
                    }
                }
            }

            if (idPsicologo == -1) {
                JOptionPane.showMessageDialog(null, "No se encontró el psicólogo seleccionado.");
                return;
            }

            // ⚠️ De momento usamos un paciente fijo (id = 1)
            // Puedes reemplazar esto luego con el paciente que haya iniciado sesión
            int idPaciente = 1;

            // ✅ Insertar cita con todos los datos
            String sql = "INSERT INTO citas (id_paciente, id_psicologo, fecha, hora, descripcion) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idPaciente);
                ps.setInt(2, idPsicologo);
                ps.setDate(3, java.sql.Date.valueOf(fecha)); // formato yyyy-MM-dd
                ps.setTime(4, java.sql.Time.valueOf(hora));  // formato HH:mm:ss
                ps.setString(5, motivo);

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "✅ Cita registrada correctamente");
            vista.dispose();

            // Regresar al login o menú principal
            LoginVista menu = new LoginVista();
            new ControladorLogin(menu);
            menu.setVisible(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la cita: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido. Usa fecha: YYYY-MM-DD y hora: HH:MM:SS");
        }
    }

    private void volverMenu() {
        vista.dispose();
        LoginVista menu = new LoginVista();
        new ControladorLogin(menu);
        menu.setVisible(true);
    }
}
