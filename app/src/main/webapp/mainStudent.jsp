<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello Student</title>
</head>
<body>
<h2 style="color: mediumblue">Hello ${sessionScope.userAuth.userName}</h2>
<c:if test="${sessionScope.userAuth.themes.size()>0}">
    <table border="3">
        <thead>
        <th>Theme name</th>
        <th>Mark</th>
        </thead>
        <c:forEach items="${sessionScope.userAuth.themes}" var="theme">
            <tr>
                <td>
                        ${theme.name}
                </td>
                <td>
                        ${theme.mark}
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="/logout">Logout</a>
</body>
</html>
