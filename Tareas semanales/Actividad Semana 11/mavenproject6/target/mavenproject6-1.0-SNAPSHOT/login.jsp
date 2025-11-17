<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acceso al Sistema - Seguimiento de Tesis</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        /* Definición de colores principales */
        :root {
            --primary-color: #503370; /* Púrpura oscuro (simulando color institucional) */
            --secondary-color: #f0f4f8; /* Gris claro para fondo y detalles */
        }
        
        /* Estilo para el fondo abstracto y limpio */
        body {
            background-color: var(--secondary-color);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Inter', sans-serif;
            position: relative;
        }

        /* Estilo para los botones de rol seleccionados */
        .role-button.selected {
            background-color: var(--primary-color);
            color: white;
            box-shadow: 0 4px 10px rgba(80, 51, 112, 0.4);
            font-weight: 600;
        }
        
        /* Animación y color del botón principal de Acceso */
        .btn-acceder {
            background-color: var(--primary-color);
            transition: all 0.2s ease-in-out;
        }
        .btn-acceder:hover {
            background-color: #6a4490; /* Tono más claro en hover */
            transform: translateY(-2px);
            box-shadow: 0 8px 15px rgba(80, 51, 112, 0.4);
        }

        /* Estilo para el campo de entrada en foco */
        input:focus {
            border-color: var(--primary-color) !important;
            box-shadow: 0 0 0 3px rgba(80, 51, 112, 0.2) !important;
        }

        /* Diseño de la Card para Desktop (Centrada y amplia) */
        .login-card-container {
            display: flex;
            max-width: 900px;
            width: 90%;
            border-radius: 1.5rem;
            overflow: hidden; /* Importante para el logo */
            box-shadow: 0 20px 50px rgba(0, 0, 0, 0.1);
        }

        /* Sección Izquierda: Logotipo y Marca (la mitad derecha visualmente) */
        .logo-section {
            background: linear-gradient(135deg, var(--primary-color), #3f275a); /* Degradado atractivo */
            color: white;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 2rem;
            text-align: center;
            width: 40%; /* Ocupa el 40% del contenedor */
        }
        
        /* Sección Derecha: Formulario */
        .form-section {
            background-color: white;
            padding: 3rem;
            width: 60%; /* Ocupa el 60% del contenedor (La mitad izquierda visualmente) */
        }

        /* Ajustes para Móviles: Pila y centrado */
        @media (max-width: 768px) {
            .login-card-container {
                flex-direction: column;
                width: 95%;
            }
            .logo-section, .form-section {
                width: 100%;
                padding: 2rem;
            }
            .logo-section {
                /* Ordenar la sección del logo debajo en móvil */
                order: 2; 
            }
        }
    </style>
</head>
<body>
    
    <!-- Contenedor Principal (Login Card) -->
    <div class="login-card-container">
        
        <!-- Sección del Formulario (Visualmente a la izquierda en desktop) -->
        <div class="form-section">
            <h2 class="text-3xl font-bold mb-8 text-gray-800 text-left border-b pb-2">
                Acceso al Sistema
            </h2>

            <!-- Mensaje de Error (si existe) -->
            <% if (request.getAttribute("errorMessage") != null) { %>
                <div id="error-box" class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 rounded-lg mb-6" role="alert">
                    <p class="font-bold">Error de Acceso</p>
                    <p class="text-sm"><%= request.getAttribute("errorMessage") %></p>
                </div>
            <% } %>

            <form action="LoginServlet" method="POST" id="loginForm">
                
                <!-- Campo oculto para el rol -->
                <input type="hidden" name="role" id="roleInput" value="Estudiante"> 
                
                <!-- Botones de Selección de Rol (Estilo más tipo pestaña/segmentado) -->
                <div class="flex justify-between space-x-1 mb-8 p-1 bg-gray-100 rounded-xl border border-gray-200">
                    <button type="button" class="role-button flex-1 py-3 text-sm rounded-xl transition duration-200 selected" data-role="Estudiante">
                        Estudiante
                    </button>
                    <button type="button" class="role-button flex-1 py-3 text-sm rounded-xl transition duration-200" data-role="Profesor">
                        Profesor
                    </button>
                    <button type="button" class="role-button flex-1 py-3 text-sm rounded-xl transition duration-200" data-role="Administrador">
                        Administrador
                    </button>
                </div>

                <!-- Campo Nombre de Usuario -->
                <div class="mb-5">
                    <label for="username" class="block text-sm font-medium text-gray-600 mb-2">Nombre de Usuario</label>
                    <input type="text" id="username" name="username" placeholder="Ingrese su código o usuario" required 
                           class="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm outline-none transition duration-150">
                </div>

                <!-- Campo Contraseña -->
                <div class="mb-8">
                    <label for="password" class="block text-sm font-medium text-gray-600 mb-2">Contraseña</label>
                    <input type="password" id="password" name="password" placeholder="Contraseña" required
                           class="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm outline-none transition duration-150">
                </div>

                <!-- Botón Acceder -->
                <div>
                    <button type="submit" 
                            class="btn-acceder w-full py-3 px-4 rounded-xl shadow-lg text-lg font-medium text-white tracking-wide">
                        Acceder al Sistema
                    </button>
                </div>
            </form>
        </div>
        
        <!-- Sección del Logo/Marca (Visualmente a la derecha en desktop) -->
        <div class="logo-section">
             <!-- Icono simulado de la universidad (puede ser reemplazado por SVG del logo) -->
            <svg class="h-16 w-16 mb-4 text-white opacity-90" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" d="M4.26 10.147a60.438 60.438 0 0 0-.491 6.347A48.62 48.62 0 0 1 12 20.912a48.62 48.62 0 0 1 8.232-4.418 60.44 60.44 0 0 0-.491-6.347m-15.482 0a58.91 58.91 0 0 0 2.218-3.754 48.255 48.255 0 0 1 10.368 0c.805.183 1.593.394 2.375.632M12 2.276A48.33 48.33 0 0 0 3 15.259V17.5a2.25 2.25 0 0 0 2.25 2.25s.689-.251 1.637-.525C8.423 18.234 9.613 17.5 12 17.5c2.387 0 3.577.734 5.113 1.734.948.274 1.637.525 1.637.525A2.25 2.25 0 0 0 21 17.5v-2.241c0-2.327-1.428-4.303-3.41-5.188-1.077-.47-2.193-.761-3.327-.881" />
            </svg>
            
            <h1 class="text-4xl font-extrabold mb-2 tracking-wider">PLATAFORMA TESIS</h1>
            <p class="text-sm opacity-80 font-light">
                Universidad Peruana Los Andes
            </p>
            <div class="mt-8 text-xs opacity-70 border-t border-white/50 pt-4">
                 Sistema de Control y Seguimiento de Trabajos de Investigación
            </div>
        </div>
        
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const roleButtons = document.querySelectorAll('.role-button');
            const roleInput = document.getElementById('roleInput');
            
            // Función para manejar la selección de roles
            roleButtons.forEach(button => {
                button.addEventListener('click', function() {
                    // 1. Quitar la clase 'selected' de todos los botones
                    roleButtons.forEach(btn => btn.classList.remove('selected'));
                    
                    // 2. Añadir la clase 'selected' al botón clickeado
                    this.classList.add('selected');
                    
                    // 3. Actualizar el campo oculto con el rol
                    const selectedRole = this.getAttribute('data-role');
                    roleInput.value = selectedRole;
                });
            });

            // Establecer la selección inicial basada en el valor del input oculto (por defecto es Estudiante)
            const initialRole = roleInput.value;
            const initialButton = document.querySelector(`.role-button[data-role="${initialRole}"]`);
            if (initialButton) {
                initialButton.classList.add('selected');
            }
        });
    </script>
</body>
</html>