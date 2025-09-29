package Controlador;

import Modelo.Historial;
import Vista.ProfesorVista;
import Vista.LoginVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ProfesorControlador {
    private final ProfesorVista vista;

    public ProfesorControlador(ProfesorVista vista) {
        this.vista = vista;

        // Inicializar mostrando el historial existente (si ya hay operaciones)
        for (String op : Historial.getOperacionesList()) {
            this.vista.agregarResultado(op);
        }

        // Listener Convertir
        this.vista.agregarConvertirListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertir();
            }
        });

        // Listener Nuevo
        this.vista.agregarNuevoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.limpiarCampos();
            }
        });

        // Listener Volver (vuelve al login)
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

    private void convertir() {
        try {
            double valor = vista.obtenerValor();
            String opcion = vista.obtenerConversion();
            double resultado;

            switch (opcion) {
                case "Kilómetros a Metros":
                    resultado = valor * 1000.0;
                    break;
                case "Metros a Centímetros":
                    resultado = valor * 100.0;
                    break;
                case "Centímetros a Milímetros":
                    resultado = valor * 10.0;
                    break;
                case "Milímetros a Micrómetros":
                    resultado = valor * 1000.0;
                    break;
                default:
                    JOptionPane.showMessageDialog(vista, "Conversión no reconocida.");
                    return;
            }

            // Formatea la operación y la guarda en el historial compartido
            String operacion = String.format("%s : %s -> %s",
                    opcion,
                    formateaNumero(valor),
                    formateaNumero(resultado));

            Historial.addOperacion(operacion);

            // Añade la nueva operación al área de historial de la vista del profesor
            vista.agregarResultado(operacion);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingrese un número válido.");
        }
    }

    // Volver al Login (cierra vista actual y abre Login)
    private void volverLogin() {
        vista.dispose();
        LoginVista login = new LoginVista();
        new LoginControlador(login);
        login.setVisible(true);
    }

    // Formatea números: si entero muestra sin decimales, sino muestra 6 decimales
    private String formateaNumero(double d) {
        if (d == (long) d) return String.format("%d", (long) d);
        return String.format("%.6f", d);
    }
}
