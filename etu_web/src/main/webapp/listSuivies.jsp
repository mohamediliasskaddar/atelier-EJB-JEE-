<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Liste des Suivies</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; background: #007bff; color: white; text-decoration: none; border-radius: 4px; margin: 5px 0; display: inline-block; }
        select, button { padding: 8px; margin: 5px; }
        .filter { margin-bottom: 20px; }
    </style>
</head>
<body>
<h1>Gestion des Notes (Suivie)</h1>
<a href="suivies?action=add" class="btn">Ajouter une note</a>

<div class="filter">
    <form method="get">
        <label>Filtrer par étudiant :</label>
        <select name="filterEtudiant" onchange="this.form.submit()">
            <option value="">-- Tous --</option>
            <c:forEach items="${etudiants}" var="e">
                <option value="${e.id_etudiant}" ${param.filterEtudiant == e.id ? 'selected' : ''}>${e.nom}</option>
            </c:forEach>
        </select>

        <label>Filtrer par module :</label>
        <select name="filterModule" onchange="this.form.submit()">
            <option value="">-- Tous --</option>
            <c:forEach items="${modules}" var="m">
                <option value="${m.id_module}" ${param.filterModule == m.id ? 'selected' : ''}>${m.nomModule}</option>
            </c:forEach>
        </select>
    </form>
</div>

<table>
    <tr>
        <th>ID</th>
        <th>Étudiant</th>
        <th>Module</th>
        <th>Note</th>
        <th>Date</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${suivies}" var="s">
        <tr>
            <td>${s.id_suivie}</td>
            <td>${s.etudiant.nom}</td>
            <td>${s.module.nomModule}</td>
            <td>${s.note}</td>
            <td>${s.date}</td>
            <td>
                <a href="suivies?action=edit&id_suivie=${s.id_suivie}">Modifier</a>
                <a href="suivies?action=delete&id_suivie=${s.id_suivie}" onclick="return confirm('Supprimer ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="etudiants">Étudiants</a> |
<a href="modules">Modules</a>
</body>
</html>