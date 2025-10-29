<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Liste des Modules</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        a { margin-right: 10px; color: blue; text-decoration: none; }
        .btn { padding: 8px 12px; background: #007bff; color: white; text-decoration: none; border-radius: 4px; }
        .btn-danger { background: #dc3545; }
    </style>
</head>
<body>
<h1>Gestion des Modules</h1>
<a href="modules?action=add" class="btn">Ajouter un module</a>

<table>
    <tr>
        <th>ID</th>
        <th>Nom du Module</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${modules}" var="m">
        <tr>
            <td>${m.id_module}</td>
            <td>${m.nomModule}</td>
            <td>
                <a href="modules?action=edit&id_module=${m.id_module}">Modifier</a>
                <a href="modules?action=delete&id_module=${m.id_module}"
                   onclick="return confirm('Supprimer ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="etudiants">← Gestion Étudiants</a>
</body>
</html>