<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Addition Salary</title>
</head>
<body>
<h2 style="color: mediumblue">Add Next Salary</h2>
<form action="/addSalary" method="post">
    <input type="text" name="salary" placeholder="Salary">
    <button>Submit</button>
</form>
<a href="mainAdmin.jsp">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
