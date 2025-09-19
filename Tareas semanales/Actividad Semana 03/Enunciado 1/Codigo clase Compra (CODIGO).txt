package Modelo;

public class Compra {
    private double precioDocena;
    private int cantidadDocenas;

    // Constructor
    public Compra(double precioDocena, int cantidadDocenas) {
        this.precioDocena = precioDocena;
        this.cantidadDocenas = cantidadDocenas;
    }

    public double calcularImporteCompra() {
        return precioDocena * cantidadDocenas;
    }

    public double calcularDescuento() {
        double importe = calcularImporteCompra();
        if (cantidadDocenas >= 10) {
            return importe * 0.20;
        } else {
            return importe * 0.10;
        }
    }

    public double calcularImportePagar() {
        return calcularImporteCompra() - calcularDescuento();
    }

    public int calcularLapiceros() {
        double importePagar = calcularImportePagar();
        if (importePagar >= 200) {
            return cantidadDocenas * 2;
        } else {
            return 0;
        }
    }
}
