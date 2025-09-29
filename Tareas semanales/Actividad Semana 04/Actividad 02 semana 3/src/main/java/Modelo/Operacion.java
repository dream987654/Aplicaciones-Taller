package Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

/**
 * Representa una operación de conversión realizada por el profesor.
 */
public class Operacion implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tipoConversion;     // p.ej. "Kilómetros a Metros"
    private double valorOriginal;
    private double valorConvertido;
    private LocalDateTime fechaHora;

    public Operacion(String tipoConversion, double valorOriginal, double valorConvertido) {
        this.tipoConversion = tipoConversion;
        this.valorOriginal = valorOriginal;
        this.valorConvertido = valorConvertido;
        this.fechaHora = LocalDateTime.now();
    }

    // getters
    public String getTipoConversion() { return tipoConversion; }
    public double getValorOriginal() { return valorOriginal; }
    public double getValorConvertido() { return valorConvertido; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    // devuelve una línea legible para mostrar en el historial
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s | %s -> %s | %s",
                fechaHora.format(f),
                formateaNumero(valorOriginal),
                formateaNumero(valorConvertido),
                tipoConversion);
    }

    // formateador: quita decimales innecesarios
    private String formateaNumero(double d) {
        if (d == (long) d) return String.format("%d", (long) d);
        DecimalFormat df = new DecimalFormat("#.######"); // hasta 6 decimales
        return df.format(d);
    }
}
