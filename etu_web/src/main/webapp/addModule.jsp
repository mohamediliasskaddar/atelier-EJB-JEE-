<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un module</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        input, button { padding: 8px; margin: 5px 0; width: 100%; }
        button { background: #007bff; color: white; border: none; cursor: pointer; }
        a { color: #007bff; text-decoration: none; }
    </style>
</head>
<body>
<h1>Ajouter un module</h1>

<form action="modules" method="post">
    <label>Nom du module :</label>
    <input type="text" name="nomModule" required>

    <button type="submit">Enregistrer</button>
</form>

<br>
<a href="modules">← Retour à la liste</a>
</body>
</html>