package Controladores;

import Datos.TesisDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CalificarServlet", urlPatterns = {"/CalificarServlet"})
public class CalificarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Recibir los datos del formulario (JSP)
        int idTesis = Integer.parseInt(request.getParameter("id_tesis"));
        int nota = Integer.parseInt(request.getParameter("nota"));
        
        // 2. Llamar al DAO para guardar en MySQL
        TesisDAO dao = new TesisDAO();
        boolean exito = dao.calificarTesis(idTesis, nota);
        
        // 3. Volver a la página del docente
        if(exito) {
            response.sendRedirect("docente.jsp?mensaje=exito");
        } else {
            response.sendRedirect("docente.jsp?mensaje=error");
        }
    }
}