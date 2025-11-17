package Modelos;

public class Usuario {
    private int id;
    private String username;
    private String nombreCompleto;
    private String rol; // Estudiante, Profesor, Administrador

    // Constructor
    public Usuario(int id, String username, String nombreCompleto, String rol) {
        this.id = id;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}