<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello Trainer</title>
</head>
<body>
<h2 style="color: mediumblue">Hello ${userName}</h2>
<c:choose>
    <c:when test="${userAuth.group!= null}">
        <a href="/allStudents">List All Students</a><br>
    </c:when>
    <c:otherwise>
        <a href="/addGroup">Create group with students</a><br>
    </c:otherwise>
</c:choose>

<a href="/addTheme.jsp">Add new theme</a><br>

<c:if test="${sessionScope.massageFormatOfMarkIsWrong!=null}">
    ${sessionScope.massageFormatOfMarkIsWrong}
    ${sessionScope.massageFormatOfMarkIsWrong = null}
</c:if>

<c:if test="${sessionScope.listStudents.size()>0}">

    <table border="3">
        <thead>
        <th align="center">Student name</th>

        <c:forEach items="${sessionScope.listStudents.get(0).themes}" var="theme">

            <th align="center">${theme.name}</th>

        </c:forEach>
        </thead>

        <c:forEach items="${sessionScope.listStudents}" var="student">
            <tr>
                <td>
                        ${student.userName}
                </td>
                <c:if test="${student.themes.size()>0}">
                    <c:forEach items="${student.themes}" var="theme">
                        <td>
                            <c:choose>
                                <c:when test="${theme.mark!=0}">
                                    ${theme.mark}
                                    <a href="<c:url value="/deleteMark"/>?themeId=${theme.id}">Delete</a>
                                    <form action="/addOrUpdateMark" method="post">
                                        <input type="text" name="mark" placeholder="Update mark here"/>
                                        <input type="hidden" name="themeId" value="${theme.id}"/>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="/addOrUpdateMark" method="post">
                                        <input type="text" name="mark" placeholder="Enter mark here"/>
                                        <input type="hidden" name="themeId" value="${theme.id}"/>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>

                    </c:forEach>
                </c:if>
            </tr>

        </c:forEach>
    </table>
</c:if>
<a href="/logout">Logout</a>
</body>
</html>
