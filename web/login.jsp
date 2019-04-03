<%--
  Created by IntelliJ IDEA.
  User: Tayfun
  Date: 28.03.2019
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
</head>
<body>

    <form action="/WebSecProject_war_exploded/Login" method="post">

        Username: <input type="text" name="un" width="30" maxlength="20" minlength="8"/><br>
        Password:               <input type="password" name="pw" width="15" maxlength="20" minlength="8"/> <br>
        <input type="submit" value="Register"/>

    </form>

    <p style="color:red;">${UnPwLength}</p>
</body>
</html>
