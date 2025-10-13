package Modelo;

public class Cita {
    private int id;
    private int idPaciente;
    private int idPsicologo;
    private String horario;
    private String descripcion;

    public Cita(int id, int idPaciente, int idPsicologo, String horario, String descripcion) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idPsicologo = idPsicologo;
        this.horario = horario;
        this.descripcion = descripcion;
    }

    public Cita(int idPaciente, int idPsicologo, String horario, String descripcion) {
        this(0, idPaciente, idPsicologo, horario, descripcion);
    }

    public int getId() { return id; }
    public int getIdPaciente() { return idPaciente; }
    public int getIdPsicologo() { return idPsicologo; }
    public String getHorario() { return horario; }
    public String getDescripcion() { return descripcion; }
}
