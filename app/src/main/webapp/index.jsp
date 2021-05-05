<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
<h2 style="color: mediumblue">Authentication</h2>
<form action="/auth" method="post">
    ${applicationScope.authenticationFail}<br>
    <input type="text" name="loginAuthentication" placeholder="Login"/>
    <input type="password" name="passwordAuthentication" placeholder="Password"/>
    <button type="submit"> Authorization</button>
    <a href="/registration.jsp">Registration</a>
</form>
</body>
</html>
