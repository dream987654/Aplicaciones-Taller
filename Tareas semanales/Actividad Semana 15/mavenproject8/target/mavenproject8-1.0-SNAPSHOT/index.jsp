<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acceso al Sistema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body, html { height: 100%; margin: 0; }
        
        /* Imagen de la Izquierda */
        .bg-login {
            background-image: url('https://images.unsplash.com/photo-1497633762265-9d179a990aa6?auto=format&fit=crop&q=80');
            background-size: cover;
            background-position: center;
        }
        
        /* Panel del Formulario Derecha */
        .login-panel {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #ffffff;
        }
        
        .form-container {
            width: 100%;
            max-width: 800px;
            padding: 80px;
        }
    </style>
</head>
<body>

    <div class="container-fluid h-100">
        <div class="row h-100">
            
            <div class="col-md-7 d-none d-md-block bg-login">
                </div>
            
            <div class="col-md-5 login-panel">
                <div class="form-container">
                    <div class="text-center mb-4">
                        <img src="https://www.dehuancayo.com//index.php?view=image&format=raw&type=img&id=265&Itemid=117&option=com_joomgallery" width="300" class="mb-3">
                        <h3 class="fw-bold">Bienvenidos a la Universidad Peruana los andes </h3>
                        <p class="text-muted small">Sistema de Grados y Títulos</p>
                    </div>

                    <form action="LoginServlet" method="POST">
                        <div class="mb-3">
                            <label class="form-label fw-bold small">Correo Institucional</label>
                            <input type="email" name="email" class="form-control" placeholder="nombre@upla.edu.pe" required>
                        </div>
                        
                        <div class="mb-4">
                            <label class="form-label fw-bold small">Contraseña</label>
                            <input type="password" name="password" class="form-control" placeholder="••••••••" required>
                        </div>

                        <% if (request.getParameter("error") != null) { %>
                            <div class="alert alert-danger text-center p-2 mb-3 small">
                                Usuario o contraseña incorrectos
                            </div>
                        <% } %>

                        <button type="submit" class="btn btn-primary w-100 py-2 fw-bold">INGRESAR</button>
                    </form>

                    <div class="mt-4 pt-3 border-top text-center">
                        <p class="small text-muted mb-2">Accesos Directos (Modo Pruebas)</p>
                        <div class="btn-group w-100">
                            <button type="button" class="btn btn-outline-secondary btn-sm" onclick="fill('admin@test.com')">Admin</button>
                            <button type="button" class="btn btn-outline-secondary btn-sm" onclick="fill('profe@test.com')">Profe</button>
                            <button type="button" class="btn btn-outline-secondary btn-sm" onclick="fill('alumno@test.com')">Alumno</button>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>

    <script>
        function fill(email) {
            document.querySelector('input[name="email"]').value = email;
            document.querySelector('input[name="password"]').value = '123'; // Contraseña genérica
        }
    </script>
</body>
</html>