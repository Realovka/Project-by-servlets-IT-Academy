<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add New Theme</title>
</head>
<body>
<h2 style="color: mediumblue">Add New Theme</h2>
<f:form action="/addTheme" method="post">
    <input type="text" name="themeName" placeholder="Theme name"/>
    <button type="submit">Submit</button>
</f:form>
<a href="/trainerAndHisStudents">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
