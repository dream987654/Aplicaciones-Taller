<%@page import="Modelo.Tesis"%>
<%@page import="Datos.TesisDAO"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Mi Portal Estudiantil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

<style>
    /* Aplicar a todo el sitio */
    body {
        font-family: 'Poppins', sans-serif;
        background-color: #f0f2f5; /* Un gris más moderno que el blanco */
    }
</style>
<body class="bg-light">

    <nav class="navbar navbar-dark bg-primary px-4 mb-5">
        <span class="navbar-brand">Portal del Estudiante</span>
        <a href="index.jsp" class="btn btn-outline-light btn-sm">Salir</a>
    </nav>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                
                <%
                    // 1. Obtener al alumno logueado
                    Usuario alumno = (Usuario) session.getAttribute("usuario");
                    
                    if(alumno != null) {
                        // 2. Buscar su tesis en la BD
                        TesisDAO dao = new TesisDAO();
                        Tesis miTesis = dao.obtenerTesisPorAlumno(alumno.getId());
                %>

                <h2 class="text-center mb-4">Hola, <%= alumno.getNombre() %> 👋</h2>

                <% if (miTesis != null) { %>
                
                    <div class="card shadow text-center">
                        <div class="card-header fw-bold">
                            ESTADO DE TU TRÁMITE DE TITULACIÓN
                        </div>
                        <div class="card-body p-5">
                            <h3 class="card-title text-primary mb-3"><%= miTesis.getTitulo() %></h3>
                            
                            <hr class="my-4">
                            
                            <div class="row">
                                <div class="col-6 border-end">
                                    <p class="text-muted mb-1">Estado Actual</p>
                                    <% if(miTesis.getEstado().equals("Calificado")) { %>
                                        <span class="badge bg-success fs-5">CALIFICADO</span>
                                    <% } else { %>
                                        <span class="badge bg-warning text-dark fs-5">EN REVISIÓN</span>
                                    <% } %>
                                </div>
                                
                                <div class="col-6">
                                    <p class="text-muted mb-1">Tu Calificación</p>
                                    <% if(miTesis.getNota() > 10) { %>
                                        <h1 class="text-success fw-bold display-4"><%= miTesis.getNota() %></h1>
                                    <% } else if(miTesis.getNota() > 0) { %>
                                        <h1 class="text-danger fw-bold display-4"><%= miTesis.getNota() %></h1>
                                    <% } else { %>
                                        <h1 class="text-muted display-4">--</h1>
                                    <% } %>
                                </div>
                            </div>

                        </div>
                        <div class="card-footer text-muted">
                            Sistema de Grados UPLA 2025
                        </div>
                    </div>

                <% } else { %>
                    <div class="alert alert-warning text-center">
                        <h4>⚠️ No tienes un tema de tesis registrado</h4>
                        <p>Por favor, comunícate con la dirección de escuela.</p>
                    </div>
                <% } %>

                <% } else { %>
                    <script>window.location.href='index.jsp';</script>
                <% } %>
                
            </div>
        </div>
    </div>

</body>
</html>