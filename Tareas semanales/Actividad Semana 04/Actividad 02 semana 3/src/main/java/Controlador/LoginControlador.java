package Controlador;

import Vista.LoginVista;
import Vista.ProfesorVista;
import Vista.AlumnoVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginControlador {

    private final LoginVista vista;

    public LoginControlador(LoginVista vista) {
        this.vista = vista;

        // Registramos el listener del botón "Ingresar" (la vista debe tener agregarIngresarListener)
        this.vista.agregarIngresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresar();
            }
        });
    }

    private void ingresar() {
        String rol = null;
        try {
            rol = vista.obtenerRolSeleccionado(); // debe devolver "Profesor" o "Alumno"
        } catch (Exception ex) {
            // defensa: si algo falla obteniendo la selección
        }

        if (rol == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione un rol válido.");
            return;
        }

        // Normalizamos para comparar sin problemas de mayúsculas/minúsculas
        rol = rol.trim();

        if ("Profesor".equalsIgnoreCase(rol)) {
            abrirProfesor();
        } else if ("Alumno".equalsIgnoreCase(rol)) {
            abrirAlumno();
        } else {
            JOptionPane.showMessageDialog(vista, "Rol no reconocido: " + rol);
        }
    }

    private void abrirProfesor() {
        // Cerramos la ventana de login y abrimos la del profesor
        vista.dispose();
        ProfesorVista profesorVista = new ProfesorVista();
        // El constructor del controlador del profesor debe registrar sus listeners
        new ProfesorControlador(profesorVista);
        profesorVista.setVisible(true);
    }

    private void abrirAlumno() {
        // Cerramos la ventana de login y abrimos la del alumno
        vista.dispose();
        AlumnoVista alumnoVista = new AlumnoVista();
        // El constructor del controlador del alumno debe registrar sus listeners
        new AlumnoControlador(alumnoVista);
        alumnoVista.setVisible(true);
    }
}
