package Controlador;

import Modelo.Historial;
import Vista.AlumnoVista;
import Vista.LoginVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlumnoControlador {
    private final AlumnoVista vista;

    public AlumnoControlador(AlumnoVista vista) {
        this.vista = vista;

        // Mostrar historial actual al abrir la vista del alumno
        String texto = Historial.getOperacionesTexto();
        // Se asume que AlumnoVista tiene un método mostrarHistorial(String)
        this.vista.mostrarHistorial(texto);

        // Listener Volver
        this.vista.agregarVolverListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverLogin();
            }
        });

        // Listener Salir
        this.vista.agregarSalirListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void volverLogin() {
        vista.dispose();
        LoginVista login = new LoginVista();
        new LoginControlador(login);
        login.setVisible(true);
    }
}
