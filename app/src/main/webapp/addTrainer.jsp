<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Addition Trainer</title>
</head>
<body>
<h2 style="color: mediumblue">Enter new trainer</h2>
<h2 style="color: #ff0000">${suchTrainerAlreadyExist}</h2>
<form action="/addTrainer" method="post">
    <input type="text" name="trainerName" placeholder="Trainer name"/><br>
    <button type="submit"> Submit </button>
</form>
<a href="/getAdminMainPage">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
