<%--
  Created by IntelliJ IDEA.
  User: imk
  Date: 29/10/2025
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Liste des Étudiants</title>
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

<h1>Gestion des Étudiants</h1>

<a href="etudiants?action=add" class="btn">Ajouter un étudiant</a>
<a href="modules" class="btn">Gestion des Modules</a>
<a href="suivies" class="btn">Gestion des Notes</a>

<br><br>

<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Niveau</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${etudiants}" var="e">
        <tr>
            <td>${e.id_etudiant}</td>
            <td>${e.nom}</td>
            <td>${e.niveau}</td>
            <td>
                <a href="etudiants?action=edit&id_etudiant=${e.id_etudiant}">Modifier</a> |
                <a href="etudiants?action=delete&id_etudiant=${e.id_etudiant}"
                   onclick="return confirm('Supprimer cet étudiant ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>