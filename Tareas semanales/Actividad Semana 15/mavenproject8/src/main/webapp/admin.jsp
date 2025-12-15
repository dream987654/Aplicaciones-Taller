<%@page import="Modelo.Tesis"%>
<%@page import="java.util.List"%>
<%@page import="Datos.TesisDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Panel Directivo</title>
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
<body class="bg-light">
    
    <nav class="navbar navbar-dark bg-dark px-16">
        <span class="navbar-brand">Administración UPLA</span>
        <a href="index.jsp" class="btn btn-outline-light btn-sm">Salir</a>
    </nav>
    
    <div class="container mt-4">
        
        <div class="card shadow-sm mb-4">
            <div class="card-header bg-primary text-white fw-bold">
                + Nueva Asignación de Tesis
            </div>
            <div class="card-body bg-white">
                <form action="AdminServlet" method="POST" class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label small text-muted">Título del Proyecto</label>
                        <input type="text" name="titulo" class="form-control" placeholder="Ej: Sistema de Ventas Web" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label small text-muted">ID Alumno</label>
                        <input type="number" name="id_alumno" class="form-control" placeholder="Ej: 3" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label small text-muted">ID Jurado (Docente)</label>
                        <input type="number" name="id_jurado" class="form-control" placeholder="Ej: 2" required>
                    </div>
                    <div class="col-12 text-end">
                        <button type="submit" class="btn btn-success">Registrar Tesis</button>
                    </div>
                </form>
            </div>
        </div>

        <h4 class="mb-3">Reporte General de Tesis</h4>
        <div class="card shadow">
            <div class="card-body p-0">
                <table class="table table-hover table-bordered mb-0">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Título</th>
                            <th>Alumno (Autor)</th>
                            <th>Estado</th>
                            <th>Nota Final</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            TesisDAO dao = new TesisDAO();
                            List<Tesis> lista = dao.listarTodas();
                            
                            for(Tesis t : lista) {
                        %>
                        <tr>
                            <td><%= t.getId() %></td>
                            <td><%= t.getTitulo() %></td>
                            <td><%= t.getNombreAlumno() %></td>
                            
                            <td>
                                <% if(t.getEstado().equals("Calificado")) { %>
                                    <span class="badge bg-success">Completado</span>
                                <% } else { %>
                                    <span class="badge bg-warning text-dark">Pendiente</span>
                                <% } %>
                            </td>
                            
                            <td class="fw-bold"><%= t.getNota() %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="alert alert-info mt-3 small">
            <strong>Nota para pruebas:</strong> Revisa en tu Workbench los IDs de tus usuarios. 
            <br>Por ejemplo: Si creas otro usuario alumno, mira qué ID le dio MySQL (ej: 4) y úsalo aquí.
        </div>
        
    </div>
</body>
</html>