package Controlador;

import Vista.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.*;

public class ControladorLogin {
    private LoginVista loginVista;
    private PacienteVista pacienteVista;
    private PsicologoVista psicologoVista;
    private Connection conn;

    public ControladorLogin(LoginVista l, PacienteVista p, PsicologoVista ps, Connection c) {
        this.loginVista = l;
        this.pacienteVista = p;
        this.psicologoVista = ps;
        this.conn = c;

        // Listener de botón ingresar
        loginVista.agregarLoginListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });

        // Listener de botón registrar
        loginVista.agregarRegistroListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginVista.setVisible(false);
                new RegistroVista().setVisible(true);
            }
        });
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
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM usuarios WHERE usuario = ? AND clave = ? AND rol = ?"
            );
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ps.setString(3, rol);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario);
                loginVista.setVisible(false);

                if (rol.equals("Paciente")) {
                    pacienteVista.setVisible(true);
                } else {
                    psicologoVista.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al autenticar: " + e.getMessage());
        }
    }
}
