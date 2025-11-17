package Modelos;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    // Simulación de datos en memoria (Reemplazar con lógica de conexión a MySQL)
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Tesis> tesisList = new ArrayList<>();
    
    static {
        // Usuarios Simulados: ID, Usuario, Nombre, Rol
        usuarios.add(new Usuario(101, "E1234567", "Juan Perez", "Estudiante"));
        usuarios.add(new Usuario(201, "P9876", "Dra. Ana Lopez", "Profesor"));
        usuarios.add(new Usuario(301, "admin", "Admin System", "Administrador"));
        
        // Tesis Simuladas: ID, Título, ID_Estudiante, ID_Asesor, ID_Jurado1, ID_Jurado2, Estado, Ruta_Archivo
        tesisList.add(new Tesis(1, "Metodología MVC en entornos web", 
                101, 201, 0, 0, "En Revisión", "tesis_juan.pdf"));
        tesisList.add(new Tesis(2, "Análisis de Big Data en MySQL", 
                102, 202, 201, 203, "Aceptado", "tesis_maria.pdf"));
    }

    // Simula la verificación de login y devuelve el objeto Usuario
    // NOTA: En un caso real, la contraseña se verificaría aquí.
    public Usuario verificarLogin(String username, String password, String rol) {
        for (Usuario u : usuarios) {
            // Lógica simple: coincide usuario Y rol
            if (u.getUsername().equals(username) && u.getRol().equals(rol)) {
                // Simulación de verificación de contraseña (En un proyecto real, se compara el hash de la contraseña)
                if (username.equals("E1234567") && password.equals("pass") || 
                    username.equals("P9876") && password.equals("pass") || 
                    username.equals("admin") && password.equals("pass")) {
                    return u;
                }
            }
        }
        return null; // Credenciales inválidas
    }
    
    // Métodos específicos para cada rol
    
    // Estudiante: Obtiene la tesis del estudiante
    public Tesis getTesisByEstudianteId(int idEstudiante) {
        for (Tesis t : tesisList) {
            if (t.getIdEstudiante() == idEstudiante) {
                return t;
            }
        }
        return null;
    }

    // Profesor: Obtiene las tesis asignadas para revisión (Asesor, Jurado 1 o Jurado 2)
    public List<Tesis> getTesisAsignadasToProfesor(int idProfesor) {
        List<Tesis> asignadas = new ArrayList<>();
        for (Tesis t : tesisList) {
            if (t.getIdProfesorAsesor() == idProfesor || t.getIdProfesorJurado1() == idProfesor || t.getIdProfesorJurado2() == idProfesor) {
                asignadas.add(t);
            }
        }
        return asignadas;
    }
    
    // Profesor: Cambia el estado de una tesis a "En Revisión"
    public boolean marcarTesisEnRevision(int idTesis) {
        for (Tesis t : tesisList) {
            if (t.getIdTesis() == idTesis) {
                t.setEstado("En Revisión");
                return true;
            }
        }
        return false;
    }
    
    // Admin: Métodos CRUD (Simulados)
    public List<Usuario> getAllUsuarios() {
        return usuarios; 
    }
    
    public boolean crearUsuario(Usuario nuevoUsuario) {
        // En un entorno real se generaría el ID de la BD
        int newId = usuarios.size() * 100 + 101; 
        nuevoUsuario.setId(newId);
        return usuarios.add(nuevoUsuario);
    }
    
    // Implementar los demás métodos CRUD para Admin...
}