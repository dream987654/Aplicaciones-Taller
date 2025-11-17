package Modelos;

public class Tesis {
    private int idTesis;
    private String titulo;
    private int idEstudiante;
    private int idProfesorAsesor;
    private int idProfesorJurado1;
    private int idProfesorJurado2;
    private String estado; // Pendiente, En Revisión, Aceptado, Rechazado
    private String rutaArchivo; // Ruta o nombre del archivo de la tesis

    // Constructor
    public Tesis(int idTesis, String titulo, int idEstudiante, int idProfesorAsesor, int idProfesorJurado1, int idProfesorJurado2, String estado, String rutaArchivo) {
        this.idTesis = idTesis;
        this.titulo = titulo;
        this.idEstudiante = idEstudiante;
        this.idProfesorAsesor = idProfesorAsesor;
        this.idProfesorJurado1 = idProfesorJurado1;
        this.idProfesorJurado2 = idProfesorJurado2;
        this.estado = estado;
        this.rutaArchivo = rutaArchivo;
    }

    // Getters y Setters
    public int getIdTesis() { return idTesis; }
    public void setIdTesis(int idTesis) { this.idTesis = idTesis; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }

    public int getIdProfesorAsesor() { return idProfesorAsesor; }
    public void setIdProfesorAsesor(int idProfesorAsesor) { this.idProfesorAsesor = idProfesorAsesor; }

    public int getIdProfesorJurado1() { return idProfesorJurado1; }
    public void setIdProfesorJurado1(int idProfesorJurado1) { this.idProfesorJurado1 = idProfesorJurado1; }

    public int getIdProfesorJurado2() { return idProfesorJurado2; }
    public void setIdProfesorJurado2(int idProfesorJurado2) { this.idProfesorJurado2 = idProfesorJurado2; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getRutaArchivo() { return rutaArchivo; }
    public void setRutaArchivo(String rutaArchivo) { this.rutaArchivo = rutaArchivo; }
}