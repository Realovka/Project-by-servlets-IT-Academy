<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<h2 style="color: mediumblue">Authorization</h2>
<form action="/auth" method="post">
    <input type="text" name="loginAuthorization" placeholder="Login"/>
    <input type="password" name="passwordAuthorization" placeholder="Password"/>
    <button type="submit"> Authorization</button>
    <a href="/registration.jsp">Registration</a>
</form>
</body>
</html>
