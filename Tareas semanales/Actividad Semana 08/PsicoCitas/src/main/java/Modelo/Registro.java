package Modelo;

public class Registro {
    private int id;
    private String rol;
    private String nombre;
    private String celular;
    private String usuario;
    private String clave;

    public Registro() {}

    public Registro(String rol, String nombre, String celular, String usuario, String clave) {
        this.rol = rol;
        this.nombre = nombre;
        this.celular = celular;
        this.usuario = usuario;
        this.clave = clave;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}
