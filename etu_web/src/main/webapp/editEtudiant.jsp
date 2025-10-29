<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Modifier l'étudiant #${etudiant.id_etudiant}</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        input, button { padding: 8px; margin: 5px 0; width: 100%; }
        button { background: #28a745; color: white; border: none; cursor: pointer; }
        a { color: #007bff; text-decoration: none; }
    </style>
</head>
<body>
<h1>Modifier l'étudiant #${etudiant.id_etudiant}</h1>

<form action="etudiants" method="post">
    <input type="hidden" name="id_etudiant" value="${etudiant.id_etudiant}">

    <label>Nom :</label>
    <input type="text" name="nom" value="${etudiant.nom}" required>

    <label>Niveau :</label>
    <input type="text" name="niveau" value="${etudiant.niveau}" required>

    <button type="submit">Enregistrer</button>
</form>

<br>
<a href="etudiants">← Retour à la liste</a>
</body>
</html>