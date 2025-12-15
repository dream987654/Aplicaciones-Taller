package Datos;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        System.out.println("probando conexión...");
        
        Connection c = Conexion.getConexion();
        
        if(c != null) {
            System.out.println("✅ ¡CONEXIÓN EXITOSA!");
        } else {
            System.out.println("⛔ FALLÓ. Revisa la consola arriba.");
        }
    }
}