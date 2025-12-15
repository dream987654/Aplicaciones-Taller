package Modelo;

public class Usuario {
    private int id; // <--- NUEVO
    private String nombre;
    private String rol;

    public Usuario() {}

    public int getId() { return id; } // <--- NUEVO
    public void setId(int id) { this.id = id; } // <--- NUEVO

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}