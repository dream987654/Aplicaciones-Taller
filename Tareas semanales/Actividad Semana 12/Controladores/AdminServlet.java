package Controladores;

import Datos.TesisDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Recibimos datos del formulario
        String titulo = request.getParameter("titulo");
        int idAlumno = Integer.parseInt(request.getParameter("id_alumno"));
        int idJurado = Integer.parseInt(request.getParameter("id_jurado"));
        
        // Guardamos en BD
        TesisDAO dao = new TesisDAO();
        dao.crearTesis(titulo, idAlumno, idJurado);
        
        // Volvemos al panel
        response.sendRedirect("admin.jsp");
    }
}