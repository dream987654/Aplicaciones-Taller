package Controladores;

import Modelos.Usuario;
import Modelos.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Obtener parámetros del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // 2. Intentar autenticar al usuario
        Usuario usuario = usuarioDAO.verificarLogin(username, password, role);

        if (usuario != null) {
            // 3. Autenticación exitosa
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogeado", usuario); // Almacenar el objeto Usuario
            
            // 4. Redirigir según el rol
            switch (usuario.getRol()) {
                case "Estudiante":
                    response.sendRedirect("EstudianteController?accion=verTesis");
                    break;
                case "Profesor":
                    response.sendRedirect("ProfesorController?accion=verAsignadas");
                    break;
                case "Administrador":
                    response.sendRedirect("AdminController?accion=dashboard");
                    break;
                default:
                    response.sendRedirect("login.jsp"); // Fallback
                    break;
            }
        } else {
            // 5. Autenticación fallida
            request.setAttribute("errorMessage", "Usuario, contraseña o rol inválido.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}