<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Modifier le module #${module.id_module}</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        input, button { padding: 8px; margin: 5px 0; width: 100%; }
        button { background: #28a745; color: white; border: none; cursor: pointer; }
        a { color: #007bff; text-decoration: none; }
    </style>
</head>
<body>
<h1>Modifier le module #${module.id_module}</h1>

<form action="modules" method="post">
    <input type="hidden" name="id_module" value="${module.id_module}">

    <label>Nom du module :</label>
    <input type="text" name="nomModule" value="${module.nomModule}" required>

    <button type="submit">Enregistrer</button>
</form>

<br>
<a href="modules">← Retour à la liste</a>
</body>
</html>