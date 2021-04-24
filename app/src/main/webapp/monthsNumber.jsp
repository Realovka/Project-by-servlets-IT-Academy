<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Number of months</title>
</head>
<body>
<h2 style="color: mediumblue">Enter the months of the trainer's work for which you want to calculate the average salary</h2>
${enterWrongMonths}<br>
<form action="/calculationAverageSalary/${trainerId}" method="post">
    <input type="text" name="finishMonthsNumber" placeholder="Enter months"/><br>
    <button>Submit</button>
</form>
<a href="/getAdminMainPage">Main page</a><br>
<a href="/logout">Logout</a>
</body>
</html>
