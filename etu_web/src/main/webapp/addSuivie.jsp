<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Ajouter une note</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        select, input, button { padding: 8px; margin: 5px 0; width: 100%; }
        button { background: #007bff; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
<h1>Ajouter une note</h1>

<form action="suivies" method="post">
    <label>Étudiant :</label>
    <select name="idEtudiant" required>
        <c:forEach items="${etudiants}" var="e">
            <option value="${e.id_etudiant}">${e.nom}</option>
        </c:forEach>
    </select>

    <label>Module :</label>
    <select name="idModule" required>
        <c:forEach items="${modules}" var="m">
            <option value="${m.id_module}">${m.nomModule}</option>
        </c:forEach>
    </select>

    <label>Note (0-20) :</label>
    <input type="number" step="0.1" min="0" max="20" name="note" required>

    <button type="submit">Enregistrer</button>
</form>

<br>
<a href="suivies">Retour</a>
</body>
</html>
