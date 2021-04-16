<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hello Trainer</title>
</head>
<body>
<h2 style="color: mediumblue">Hello ${sessionScope.userAuth.userName}</h2>
<c:choose>
    <c:when test="${sessionScope.userAuth.group!= null}">
        <a href="/allStudents">List All Students</a><br>
    </c:when>
    <c:otherwise>
        <a href="/addGroup">Create group with students</a><br>
    </c:otherwise>
</c:choose>

<a href="/addTheme.jsp">Add new theme</a><br>

<c:if test="${massageFormatOfMarkIsWrong!=null}">
    ${massageFormatOfMarkIsWrong}
    ${massageFormatOfMarkIsWrong = null}
</c:if>

<c:if test="${listStudents.size()>0}">

    <table border="3">
        <thead>
        <th align="center">Student name</th>

        <c:forEach items="${listStudents.get(0).themes}" var="theme">

            <th align="center">${theme.name}</th>

        </c:forEach>
        </thead>

        <c:forEach items="${listStudents}" var="student">
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
                                    <a href=/deleteMark/${theme.id}>Delete</a>
                                    <f:form action="/addOrUpdateMark/${theme.id}" method="post" modelAttribute="mark" >
                                        <input type="text" name="mark" placeholder="Update mark here"/>
                                    </f:form>
                                </c:when>
                                <c:otherwise>
                                    <f:form action="/addOrUpdateMark/${theme.id}" method="post" modelAttribute="mark">
                                        <input type="text" name="mark" placeholder="Enter mark here"/>
                                    </f:form>
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
