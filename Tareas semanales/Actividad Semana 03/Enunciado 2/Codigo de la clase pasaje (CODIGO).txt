package Modelo;

public class Pasaje {
    private String turno;
    private int cantidad;

    public Pasaje(String turno, int cantidad) {
        this.turno = turno;
        this.cantidad = cantidad;
    }

    // 1. Precio unitario (ambos turnos cuestan igual, pero dejamos la lógica lista)
    public double precioUnitario() {
        if (turno.equalsIgnoreCase("Mañana")) {
            return 37.5;
        } else {
            return 37.5;
        }
    }

    // 2. Importe de la compra
    public double calcularImporteCompra() {
        return cantidad * precioUnitario();
    }

    // 3. Descuento según la cantidad de pasajes
    public double calcularDescuento() {
        double importe = calcularImporteCompra();
        if (cantidad >= 15) {
            return importe * 0.08;
        } else {
            return importe * 0.05;
        }
    }

    // 4. Importe a pagar
    public double calcularImportePagar() {
        return calcularImporteCompra() - calcularDescuento();
    }

    // 5. Caramelos de obsequio
    public int calcularCaramelos() {
        if (calcularImportePagar() > 200) {
            return cantidad * 2;
        } else {
            return 0;
        }
    }
}
