package Controlador;

import Vista.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class ControladorPsicologo {
    private PsicologoVista psicologoVista;
    private AgendaVista agendaVista;
    private LoginVista loginVista;

    public ControladorPsicologo(PsicologoVista ps, AgendaVista a, LoginVista l) {
        this.psicologoVista = ps;
        this.agendaVista = a;
        this.loginVista = l;

        // Volver al login
        psicologoVista.agregarVolverListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                psicologoVista.setVisible(false);
                loginVista.setVisible(true);
            }
        });

        // Abrir agenda
        psicologoVista.agregarAgendaListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                psicologoVista.setVisible(false);
                agendaVista.setVisible(true);
            }
        });

        // Salir totalmente
        psicologoVista.agregarSalirListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cerrando sesión.");
                System.exit(0);
            }
        });
    }
}
