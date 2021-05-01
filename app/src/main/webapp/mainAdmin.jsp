<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello Admin</title>
</head>
<body>
<h2 style="color: mediumblue">Hello ${sessionScope.userAuth.name}</h2>
<a href="addTrainer.jsp">Enter new trainer</a><br>
<a href="/allTrainers">Enter next salary to trainer</a><br>
<a href="/allTrainersForAverageSalary">Average salaries of trainers</a><br>
<a href="/logout">Logout</a>
</body>
</html>
