package Modelo;

public class Usuario {
    protected int id;
    protected String nombre;
    protected String usuario;
    protected String clave;
    protected String tipo; // "paciente" o "psicologo"

    public Usuario(int id, String nombre, String usuario, String clave, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.tipo = tipo;
    }

    public Usuario(String nombre, String usuario, String clave, String tipo) {
        this(0, nombre, usuario, clave, tipo);
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUsuario() { return usuario; }
    public String getClave() { return clave; }
    public String getTipo() { return tipo; }

    public void setId(int id) { this.id = id; }
}
