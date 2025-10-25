package com.mycompany.psicocitas;

import Modelo.Conexion;
import Vista.LoginVista;
import Controlador.ControladorLogin;

public class Main {
    public static void main(String[] args) {
        // Aseguramos que la conexión a BD funcione antes de abrir la app
        if (Conexion.getConexion() == null) {
            System.err.println("❌ No se pudo establecer conexión con la base de datos.");
            return;
        }

        // Crear la vista principal (Login)
        LoginVista loginVista = new LoginVista();

        // Crear el controlador correspondiente
        ControladorLogin controladorLogin = new ControladorLogin(loginVista);

        // Mostrar la ventana de login
        loginVista.setVisible(true);

        // (Opcional) puedes ajustar el tema o apariencia aquí
        // javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    }
}
