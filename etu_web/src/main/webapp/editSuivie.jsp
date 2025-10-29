<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Modifier la note #${suivie.id}</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        select, input, button { padding: 8px; margin: 5px 0; width: 100%; }
        button { background: #28a745; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
<h1>Modifier la note #${suivie.id}</h1>

<form action="suivies" method="post">
    <input type="hidden" name="id" value="${suivie.id}">

    <label>Étudiant :</label>
    <select name="idEtudiant" required>
        <c:forEach items="${etudiants}" var="e">
            <option value="${e.id}" ${suivie.etudiant.id == e.id ? 'selected' : ''}>${e.nom}</option>
        </c:forEach>
    </select>

    <select name="idModule" required>
        <c:forEach items="${modules}" var="m">
            <option value="${m.id}" ${suivie.module.id == m.id ? 'selected' : ''}>${m.nomModule}</option>
        </c:forEach>
    </select>

    <label>Note :</label>
    <input type="number" step="0.1" min="0" max="20" name="note" value="${suivie.note}" required>

    <button type="submit">Enregistrer</button>
</form>

<br>
<a href="suivies">Retour</a>
</body>
</html>