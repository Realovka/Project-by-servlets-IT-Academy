<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Addition Salary</title>
</head>
<body>
<h2 style="color: mediumblue">Add Next Salary</h2>
<f:form action="/addSalary/${trainerId}" method="post">
    <input type="text" name="salary" placeholder="Salary">
    <button>Submit</button>
</f:form>
<a href="/getAdminMainPage">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
