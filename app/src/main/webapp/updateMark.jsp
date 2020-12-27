<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Or Delete Mark</title>
</head>
<body>
<h2 style="color: mediumblue">Update Or Delete Mark</h2>
<label>
    If you want to update mark, enter new mark in this field
</label>

${massageFormatOfMarkIsWrong}

<form action="/updateMark?student=${request.getParameter("student")}&theme=${request.getParameter("theme")}" method="get">
    <input type="text" name="mark" placeholder="New mark"/>
    <input type="hidden" name="student" value="${requestScope.get("student")}">
    <input type="hidden" name="theme" value="${requestScope.get("theme")}">
    <button type="submit">Submit</button>
</form>
<a href="mainTrainer.jsp">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
