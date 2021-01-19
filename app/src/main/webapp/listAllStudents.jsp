<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Students</title>
</head>
<body>
<h2 style="color: mediumblue">List All Students in the University</h2>
<label>Click on student name if you want to add his to your list</label>
<form action="/addStudent" method="post">
    <c:forEach items="${sessionScope.students}" var="student">
        <a href="<c:url value="/addStudent"/>?student=${student.id}">${student.userName}</a><br>
    </c:forEach>
</form>

<c:if test="${sessionScope.listStudentsOfTrainer!=null}">
    <table border="3">
        <thead>
        <th align="center">Students name in Your Group</th>
        </thead>
        <c:forEach items="${sessionScope.listStudentsOfTrainer}" var="student">
            <tr>
                <td>${student.userName}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="mainTrainer.jsp">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
