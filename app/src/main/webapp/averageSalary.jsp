<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Trainers</title>
</head>
<h2 style="color: mediumblue">All Trainers</h2>
<label>If you want to know average salary of trainer, click on his name</label><br>
<c:if test="${applicationScope.listTrainers.get(0)!=null}">
    <table border="3">
        <thead>
        <th align="center">Trainer name</th>
        </thead>
        <form action="/choiceTrainerGetAverageSalary" method="get">
            <c:forEach items="${applicationScope.listTrainers}" var="trainer">
                <tr>
                    <td>
                        <a href="<c:url value="/choiceTrainerGetAverageSalary"/>?trainerId=${trainer.id}">${trainer.name}</a><br>
                    </td>
                </tr>
            </c:forEach>
        </form>
    </table>
</c:if>
<a href="mainAdmin.jsp">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
