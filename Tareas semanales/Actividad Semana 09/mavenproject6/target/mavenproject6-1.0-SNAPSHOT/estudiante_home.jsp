<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel del Estudiante - Sistema de Tesis UPLA</title>

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: url('fondo_upla.jpg') no-repeat center center fixed;
            background-size: cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            width: 480px;
            background-color: rgba(255, 255, 255, 0.92);
            padding: 40px;
            border-radius: 20px;
            text-align: center;
        }

        h1 {
            color: #1e1348;
            font-size: 28px;
            margin-bottom: 20px;
        }

        .info-box {
            text-align: left;
            margin-top: 20px;
            background-color: #eee;
            padding: 20px;
            border-radius: 15px;
        }

        .btn {
            display: block;
            width: 100%;
            margin-top: 20px;
            padding: 14px;
            font-size: 17px;
            background-color: #2b1b55;
            color: white;
            border: none;
            border-radius: 30px;
            cursor: pointer;
        }
    </style>
</head>

<body>

    <div class="container">
        <h1>Panel del Estudiante</h1>

        <div class="info-box">
            <p><strong>Estado de tu tesis:</strong></p>
            <p>En proceso de revisión.</p>
            <p><strong>Docente asignado:</strong> Juan Pérez</p>
            <p><strong>Última actualización:</strong> 02/11/2025</p>
        </div>

        <button class="btn" onclick="location.href='login.jsp'">Cerrar Sesión</button>
    </div>

</body>
</html>
