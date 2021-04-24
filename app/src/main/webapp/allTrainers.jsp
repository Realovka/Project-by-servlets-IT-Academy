<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>All Trainers</title>
</head>
<h2 style="color: mediumblue">All Trainers</h2>
<label>If you want to know average salary of trainer, click on his name</label><br>
<c:if test="${listTrainers.get(0)!=null}">
    <table border="3">
        <thead>
        <th align="center">Trainer name</th>
        </thead>

            <c:forEach items="${listTrainers}" var="trainer">
                <tr>
                    <td>
                        <a href="/choiceTrainerGetAverageSalary/${trainer.id}">${trainer.name}</a><br>
                    </td>
                </tr>
            </c:forEach>

    </table>
</c:if>
<a href="/getAdminMainPage">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
