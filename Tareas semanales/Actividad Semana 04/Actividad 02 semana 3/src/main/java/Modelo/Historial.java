package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Historial {
    private static final List<String> operaciones = new ArrayList<>();

    // Agrega una operación (ej: "Km->m: 1 -> 1000")
    public static synchronized void addOperacion(String operacion) {
        operaciones.add(operacion);
    }

    // Devuelve todo el historial como texto (línea por línea)
    public static synchronized String getOperacionesTexto() {
        if (operaciones.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (String op : operaciones) {
            sb.append(op).append("\n");
        }
        return sb.toString();
    }

    // Devuelve una lista inmodificable de operaciones (si la necesitas)
    public static synchronized List<String> getOperacionesList() {
        return Collections.unmodifiableList(new ArrayList<>(operaciones));
    }

    // Limpia historial (opcional)
    public static synchronized void clear() {
        operaciones.clear();
    }
}
