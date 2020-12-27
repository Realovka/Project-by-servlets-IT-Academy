<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Enter Trainers</title>
</head>
<body>
<h2 style="color: mediumblue">Enter new trainer</h2>
<h2 style="color: red">${applicationScope.message}</h2>
<form action="/addTrainer" method="post">
        <input type="text" name="trainerName" placeholder="Trainer name"/><br>
        <input type="text" name="trainerAge" placeholder="Trainer age"/><br>
        <input type="text" name="salary" placeholder="Salary for month"/><br>

    <button type="submit"> Submit </button>
</form>
<c:if test="${applicationScope.trainers!=null}">
    <table border="3">
        <thead>
        <th align="center">Trainer name</th>
        <th align="center">Trainer age</th>
        <th align="center">Trainer salary</th>
        </thead>

        <c:forEach items="${applicationScope.trainers}" var="trainer">
            <tr>
                <td>${trainer.value.userName}</td>
                <td>${trainer.value.age}</td>
                <td>${trainer.value.salary}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="mainAdmin.jsp">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
