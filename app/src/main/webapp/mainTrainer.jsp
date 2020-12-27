<%@ page import="java.util.List" %>
<%@ page import="by.realovka.web.dao.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello Trainer</title>
</head>
<body>
<h2 style="color: mediumblue">Hello ${sessionScope.userAuth.userName}</h2>
<a href="/allStudents">List All Students</a><br>

<a href="/addTheme.jsp">Add new theme</a><br>

<c:if test="${sessionScope.listStudentsForTrainer!=null}">


    ${applicationScope.massageFormatOfMarkIsWrong}


    <table border="3">
        <thead>
        <th align="center">Student name</th>
        <c:forEach items="${sessionScope.listStudentsForTrainer.get(0).themes}" var="theme">
            <th align="center">${theme.name}</th>
        </c:forEach>
        </thead>
        <c:forEach items="${sessionScope.listStudentsForTrainer}" var="student">
            <tr>
                <td>
                        ${student.userName}
                </td>
                <c:forEach items="${student.themes}" var="theme">
                    <td>
                        <c:choose>
                            <c:when test="${theme.mark!=0}">
                                ${theme.mark}
                                <a href="/deleteMark?student=${student.login}&theme=${theme.name}">Delete</a>
                                <form action="/updateMark" method="post">
                                    <input type="text" name="mark" placeholder="Update mark here"/>
                                    <input type="hidden" name="student" value="${student.login}"/>
                                    <input type="hidden" name="theme" value="${theme.name}"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="addMark" method="post">
                                    <input type="text" name="mark" placeholder="Enter mark here"/>
                                    <input type="hidden" name="student" value="${student.login}"/>
                                    <input type="hidden" name="theme" value="${theme.name}"/>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="/logout">Logout</a>
</body>
</html>
