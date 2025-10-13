package Modelo;

public class Psicologo extends Usuario {
    private String especialidad;

    public Psicologo(int id, String nombre, String usuario, String clave, String especialidad) {
        super(id, nombre, usuario, clave, "psicologo");
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
