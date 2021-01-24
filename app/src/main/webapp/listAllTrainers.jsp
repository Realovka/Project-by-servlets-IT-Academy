<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Enter Trainers</title>
</head>
<body>
<h2 style="color: mediumblue">All Trainer</h2>
<label>If you want to add salary to trainer, click on his name</label>
<c:if test="${applicationScope.listTrainers.get(0)!=null}">
    <table border="3">
        <thead>
        <th align="center">Trainer name</th>
        </thead>
        <form action="/choiceTrainer" method="get">
           <c:forEach items="${applicationScope.listTrainers}" var="trainer">
               <tr>
                   <td>
                <a href="<c:url value="/choiceTrainer"/>?trainerId=${trainer.id}&trainerName=${trainer.name}">${trainer.name}</a><br>
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
