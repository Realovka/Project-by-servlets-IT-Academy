<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Number of months</title>
</head>
<body>
<h2 style="color: mediumblue">Enter the number of months for which you want to receive the average trainer's salary</h2>
<form action="/calculationAverageSalary" method="post">
    <input type="text" name="monthsNumber" placeholder="Number of months">
    <button>Submit</button>
</form>
<a href="mainAdmin.jsp">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
