package Modelo;

public class Tesis {
    private int id;
    private String titulo;
    private String nombreAlumno; // Guardaremos el nombre, no solo el ID, para mostrarlo fácil
    private int nota;
    private String estado;

    public Tesis() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getNombreAlumno() { return nombreAlumno; }
    public void setNombreAlumno(String nombreAlumno) { this.nombreAlumno = nombreAlumno; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}