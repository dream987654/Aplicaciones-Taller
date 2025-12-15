package Datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    // 1. La URL: ¿Dónde vive la base de datos?
    // "localhost" = mi compu, "3306" = puerto mysql, "sistema_grados_db" = nombre de tu base
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_final_db";
    
    // 2. El Usuario: Por defecto siempre es root
    private static final String USER = "root"; 
    
    // 3. La Contraseña: LA QUE TÚ PUSISTE EN EL INSTALADOR
    // Si no pusiste nada, déjalo vacío "". Si pusiste 1234, pon "1234".
    private static final String PASS = "1234"; 

    public static Connection getConexion() {
        Connection con = null;
        try {
            // Cargamos el driver en memoria
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Intentamos abrir la puerta
            con = DriverManager.getConnection(URL, USER, PASS);
            
        } catch (Exception e) {
            // Si falla, nos dirá por qué (clave mal, base no existe, etc.)
            System.out.println("❌ Error de Conexión: " + e.getMessage());
        }
        return con;
    }
}