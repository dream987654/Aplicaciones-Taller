<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel del Profesor - Sistema de Tesis UPLA</title>

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
            width: 500px;
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

        ul {
            margin-top: 10px;
            padding-left: 20px;
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
        <h1>Panel del Profesor</h1>

        <div class="info-box">
            <p><strong>Tesis asignadas para revisión:</strong></p>

            <ul>
                <li>Tesis 1 — Estudiante: Carlos López — Estado: Enviado</li>
                <li>Tesis 2 — Estudiante: Mónica Ríos — Estado: En Revisión</li>
                <li>Tesis 3 — Estudiante: Alberto Huamán — Estado: Pendiente</li>
            </ul>

            <p><strong>Acciones:</strong></p>
            <ul>
                <li>Ver tesis asignadas</li>
                <li>Subir observaciones</li>
                <li>Enviar calificación final</li>
            </ul>
        </div>

        <button class="btn" onclick="location.href='login.jsp'">Cerrar Sesión</button>
    </div>

</body>
</html>
