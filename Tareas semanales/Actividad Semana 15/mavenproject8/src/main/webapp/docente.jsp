<%@page import="java.util.List"%>
<%@page import="Modelo.Tesis"%>
<%@page import="Datos.TesisDAO"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panel Docente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

<style>
    /* Aplicar a todo el sitio */
    body {
        font-family: 'Poppins', sans-serif;
        background-color: #f0f2f5; /* Un gris más moderno que el blanco */
    }
</style>
</head>
<body style="background-color: #e9ecef;">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary px-4">
        <span class="navbar-brand">Portal Docente</span>
        <div class="ms-auto text-white">
            Bienvenido, Prof. ${sessionScope.usuario.nombre} | 
            <a href="index.jsp" class="text-white fw-bold">Salir</a>
        </div>
    </nav>

    <div class="container mt-5">
    <h3 class="mb-4">Mis Asignaciones</h3>

    <%
        // 1. Recuperamos al usuario que inició sesión
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        
        // 2. Si hay usuario, buscamos sus tesis en la BD
        if (usuarioLogueado != null) {
            TesisDAO dao = new TesisDAO();
            // OJO: Aquí asumimos que tenemos el ID del usuario. 
            // Si tu Login solo guardó nombre y rol, necesitaremos ajustar el Usuario.java para guardar ID también.
            // Por ahora, para probar, pondremos el ID 2 fijo (que es el Profe Juan en la BD).
            List<Tesis> misTesis = dao.listarPorDocente(2); 
    %>

    <div class="row">
        <% for (Tesis t : misTesis) { %>
            <div class="col-md-6">
                <div class="card shadow-sm mb-3">
                    <div class="card-body">
                        <h5 class="card-title text-primary"><%= t.getTitulo() %></h5>
                        <h6 class="card-subtitle mb-2 text-muted">Alumno: <%= t.getNombreAlumno() %></h6>
                        <p class="card-text">
                            Estado: <strong><%= t.getEstado() %></strong><br>
                            Nota actual: <span class="badge bg-secondary"><%= t.getNota() %></span>
                        </p>
                        <hr>
                        <% if(t.getEstado().equals("Calificado")) { %>
                            <div class="alert alert-success p-2 small text-center mb-0">
                                ✅ Calificada con nota: <%= t.getNota() %>
                            </div>
                        <% } else { %>
                            <form action="CalificarServlet" method="POST" class="d-flex gap-2">
                                <input type="hidden" name="id_tesis" value="<%= t.getId() %>">
                                
                                <input type="number" name="nota" class="form-control form-control-sm" 
                                       placeholder="0-20" min="0" max="20" required style="width: 80px;">
                                
                                <button type="submit" class="btn btn-primary btn-sm">Guardar</button>
                            </form>
                        <% } %>
                    </div>
                </div>
            </div>
        <% } %>
        
        <% if (misTesis.isEmpty()) { %>
            <p class="text-muted">No tienes tesis asignadas actualmente.</p>
        <% } %>
    </div>

    <% } else { %>
        <div class="alert alert-danger">Sesión no válida. Por favor ingresa de nuevo.</div>
    <% } %>
</div>
    </div>
</body>
</html>