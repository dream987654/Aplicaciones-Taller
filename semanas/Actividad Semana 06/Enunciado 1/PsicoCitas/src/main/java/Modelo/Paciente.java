package Modelo;

public class Paciente extends Usuario {
    private String celular;

    public Paciente(int id, String nombre, String usuario, String clave, String celular) {
        super(id, nombre, usuario, clave, "paciente");
        this.celular = celular;
    }

    public String getCelular() {
        return celular;
    }
}
