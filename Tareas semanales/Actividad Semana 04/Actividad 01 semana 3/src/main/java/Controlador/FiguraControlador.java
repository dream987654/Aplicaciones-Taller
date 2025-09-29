package Controlador;

import Modelo.Cuadrado;
import Modelo.Figura;
import Modelo.Rectangulo;
import Modelo.Triangulo;
import Vista.FiguraVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiguraControlador {
    private FiguraVista vista;

    public FiguraControlador(FiguraVista vista) {
        this.vista = vista;

        // Botón Calcular
        this.vista.agregarCalcularListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });

        // Botón Nuevo
        this.vista.agregarNuevoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.limpiarCampos();
            }
        });

        // Botón Salir
        this.vista.agregarSalirListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void calcular() {
        String figuraSeleccionada = vista.getFiguraSeleccionada();
        Figura figura = null;
        switch (figuraSeleccionada) {
            case "Cuadrado":
             double lado = vista.getLado();
             figura = new Cuadrado(lado);
             vista.mostrarImagen("src/imagenes/cuadrado.jpg");
             break;
            case "Rectangulo":
             double base = vista.getBase();
             double altura = vista.getAltura();
             figura = new Rectangulo(base, altura);
             vista.mostrarImagen("src/imagenes/rectangulo.jpg");
             break;
            case "Triangulo":
             double lado1 = vista.getLado1();
             double lado2 = vista.getLado2();
             double lado3 = vista.getLado3();
             figura = new Triangulo(lado1, lado2, lado3);
             break;
        }
        if (figura != null) {
          double area = figura.calcularArea();
          double perimetro = figura.calcularPerimetro();
          vista.mostrarResultados(area, perimetro);
        }
    }
    
}
