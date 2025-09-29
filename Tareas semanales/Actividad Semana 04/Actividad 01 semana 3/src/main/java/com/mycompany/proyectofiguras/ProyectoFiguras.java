
package com.mycompany.proyectofiguras;

import Controlador.FiguraControlador;
import Vista.FiguraVista;

public class ProyectoFiguras {

    public static void main(String[] args) {
        FiguraVista vista = new FiguraVista();
        
        // Crear el controlador y vincularlo con la vista
        FiguraControlador controlador = new FiguraControlador(vista);
        
        // Mostrar la ventana
        vista.setVisible(true);
    }
}
