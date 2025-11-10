<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Sistema de Tesis UPLA</title>

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: url('fondo_upla.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            height: 100vh;
        }

        .left-panel {
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            color: white;
            text-align: center;
        }

        .left-panel img {
            width: 220px;
            margin-bottom: 20px;
        }

        .right-panel {
            width: 420px;
            background-color: rgba(255, 255, 255, 0.92);
            padding: 40px;
            border-radius: 20px;
            margin: auto 60px auto 0;
        }

        h2 {
            text-align: center;
            color: #1e1348;
            margin-bottom: 30px;
        }

        input {
            width: 100%;
            padding: 14px;
            margin-bottom: 18px;
            border: 1px solid #ccc;
            border-radius: 30px;
            font-size: 16px;
        }

        .role-buttons {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .role-buttons button {
            flex: 1;
            margin: 0 5px;
            padding: 12px;
            border: none;
            border-radius: 25px;
            cursor: pointer;
            background-color: #ddd;
            font-size: 14px;
        }

        .btn-login {
            width: 100%;
            padding: 14px;
            background-color: #2b1b55;
            color: white;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            font-size: 18px;
        }
    </style>

    <script>
        let rolSeleccionado = "";

        function seleccionarRol(rol) {
            rolSeleccionado = rol;
            document.getElementById("rolInput").value = rol;
        }

        function validarLogin() {
            var usuario = document.getElementById("usuario").value;
            var clave = document.getElementById("password").value;

            if (rolSeleccionado === "") {
                alert("Por favor selecciona un rol.");
                return false;
            }

            if (usuario.trim() === "" || clave.trim() === "") {
                alert("Ingresa usuario y contraseña.");
                return false;
            }

            // Redirección a la pantalla del rol
            if (rolSeleccionado === "estudiante") {
                window.location.href = "estudiante_home.jsp";
            } else if (rolSeleccionado === "profesor") {
                window.location.href = "profesor_home.jsp";
            } else if (rolSeleccionado === "admin") {
                window.location.href = "admin_home.jsp";
            }

            return false;
        }
    </script>

</head>
<body>

    <div class="left-panel">
        <img src="logo_upla.png" alt="UPLA">
        <h1>UPLA</h1>
        <h3>Universidad Peruana Los Andes</h3>
    </div>

    <div class="right-panel">
        <h2>Inicio de Sesión</h2>

        <div class="role-buttons">
            <button onclick="seleccionarRol('estudiante')">Estudiante</button>
            <button onclick="seleccionarRol('profesor')">Profesor</button>
            <button onclick="seleccionarRol('admin')">Administrador</button>
        </div>

        <form onsubmit="return validarLogin()">
            <input id="usuario" type="text" placeholder="Nombre de Usuario">
            <input id="password" type="password" placeholder="Contraseña">

            <input type="hidden" id="rolInput">

            <button type="submit" class="btn-login">Acceder</button>
        </form>
    </div>

</body>
</html>
