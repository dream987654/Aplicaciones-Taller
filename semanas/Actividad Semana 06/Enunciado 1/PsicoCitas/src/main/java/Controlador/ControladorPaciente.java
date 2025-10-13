package Controlador;

import Vista.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class ControladorPaciente {
    private PacienteVista pacienteVista;
    private RegistrarCitaVista registrarCitaVista;
    private LoginVista loginVista;

    public ControladorPaciente(PacienteVista p, RegistrarCitaVista r, LoginVista l) {
        this.pacienteVista = p;
        this.registrarCitaVista = r;
        this.loginVista = l;

        // Volver al login
        pacienteVista.agregarVolverListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pacienteVista.setVisible(false);
                loginVista.setVisible(true);
            }
        });

        // Registrar nueva cita
        pacienteVista.agregarRegistrarCitaListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pacienteVista.setVisible(false);
                registrarCitaVista.setVisible(true);
            }
        });

        // Salir totalmente
        pacienteVista.agregarSalirListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hasta luego.");
                System.exit(0);
            }
        });
    }
}
