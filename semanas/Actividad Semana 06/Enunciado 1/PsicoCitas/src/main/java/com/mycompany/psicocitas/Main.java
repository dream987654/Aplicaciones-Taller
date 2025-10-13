
package com.mycompany.psicocitas;

import Controlador.ControladorLogin;
import Controlador.ControladorPaciente;
import Controlador.ControladorPsicologo;
import Modelo.ConexionSQLite;
import Vista.AgendaVista;
import Vista.LoginVista;
import Vista.PacienteVista;
import Vista.PsicologoVista;
import Vista.RegistrarCitaVista;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Conexión a la base de datos
        Connection conn = ConexionSQLite.conectar();
        if (conn == null) {
            System.out.println("❌ Error al conectar con la base de datos.");
            return;
        } else {
            System.out.println("✅ Conexión exitosa con la base de datos.");
        }

        // Crear vistas
        LoginVista loginVista = new LoginVista();
        PacienteVista pacienteVista = new PacienteVista();
        PsicologoVista psicologoVista = new PsicologoVista();
        RegistrarCitaVista registrarCitaVista = new RegistrarCitaVista();
        AgendaVista agendaVista = new AgendaVista();

        // Crear controladores
        ControladorLogin controladorLogin = new ControladorLogin(loginVista, pacienteVista, psicologoVista, conn);
        ControladorPaciente controladorPaciente = new ControladorPaciente(pacienteVista, registrarCitaVista, loginVista);
        ControladorPsicologo controladorPsicologo = new ControladorPsicologo(psicologoVista, agendaVista, loginVista);

        // Mostrar pantalla principal (login)
        loginVista.setVisible(true);
    }
}
