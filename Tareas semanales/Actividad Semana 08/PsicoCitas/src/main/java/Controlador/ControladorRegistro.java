package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class ControladorRegistro {
    private RegistroVista vista;
    private RegistroDAO dao;

    public ControladorRegistro(RegistroVista vista) {
        this.vista = vista;
        this.dao = new RegistroDAO();

        // Listener botón Registrar
        this.vista.agregarRegistrarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        // Listener botón Volver
        this.vista.agregarVolverListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAlLogin();
            }
        });
    }

    private void registrarUsuario() {
        Registro r = new Registro(
            vista.getRol(),
            vista.getNombre(),
            vista.getCelular(),
            vista.getUsuario(),
            vista.getClave()
        );

        if (dao.registrar(r)) {
            JOptionPane.showMessageDialog(null, "Registro exitoso. Ahora puede iniciar sesión.");
            vista.dispose();
            LoginVista login = new LoginVista();
            new ControladorLogin(login);
            login.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar. Intente nuevamente.");
        }
    }

    private void volverAlLogin() {
        vista.dispose();
        LoginVista login = new LoginVista();
        new ControladorLogin(login);
        login.setVisible(true);
    }
}
