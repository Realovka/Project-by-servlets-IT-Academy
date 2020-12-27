<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Average salary</title>
</head>
<h2 style="color: mediumblue">Average salaries of trainers</h2>
<label>Enter name of trainer and the number of months for which you want to calculate the average salary</label><br>
<c:if test="${sessionScope.trainerDTO!=null}">
    <label> ${sessionScope.trainerDTO.trainerName} has average salary for ${sessionScope.trainerDTO.months} months ${sessionScope.trainerDTO.averageSalary}</label>
</c:if>
${applicationScope.message}
<form action="/averageSalary" method="post">
    <input type="text" name="trainerName" placeholder="Trainer name"/>
    <input type="text" name="numberOfMonths" placeholder="Number of months"/>
    <input type="submit" name="Submit"/>
</form>
<c:if test="${applicationScope.trainers!=null}">
    <table border="3">
        <thead>
        <th align="center">Trainer name</th>
        </thead>
        <c:forEach items="${applicationScope.trainers}" var="trainer">
            <tr>
                <td>${trainer.key}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="mainAdmin.jsp">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
