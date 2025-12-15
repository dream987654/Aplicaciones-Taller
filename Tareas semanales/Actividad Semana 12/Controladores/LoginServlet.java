package Controladores;

import Datos.UsuarioDAO;
import Modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.validar(email, pass);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            
            // --- AQUÍ ESTABA EL ERROR, AHORA ESTÁ CORREGIDO ---
            String rol = usuario.getRol();
            
            // Si el rol vino vacío por error de base de datos, lo tratamos como error
            if (rol == null) {
                System.out.println("ERROR: El usuario existe pero su ROL es nulo.");
                response.sendRedirect("index.jsp?error=rol_vacio");
                return;
            }

            // Usamos "equalsIgnoreCase" de forma segura
            if (rol.equalsIgnoreCase("admin")) {
                response.sendRedirect("admin.jsp");
            } 
            else if (rol.equalsIgnoreCase("docente") || rol.equalsIgnoreCase("profe")) {
                response.sendRedirect("docente.jsp");
            } 
            else if (rol.equalsIgnoreCase("alumno") || rol.equalsIgnoreCase("estudiante")) {
                response.sendRedirect("alumno.jsp");
            } 
            else {
                response.sendRedirect("index.jsp");
            }
            
        } else {
            response.sendRedirect("index.jsp?error=true");
        }
    }
}