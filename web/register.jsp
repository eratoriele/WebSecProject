<%--
  Created by IntelliJ IDEA.
  User: Tayfun
  Date: 28.03.2019
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

    <form action="/WebSecProject_war_exploded/Register" method="post">

        Username: <input type="text" name="un" width="30" maxlength="20" minlength="8"/><br>
        Password:  <input type="password" name="pw" width="15" maxlength="20" minlength="8"/> <br><br><br>

        <h1>How do you view an object?</h1>
        <input type="radio" name="q1" value="0"> Tree rather than a forest <br>
        <input type="radio" name="q1" value="1"> Forest rather than a tree <br>
        <h1>Other question i dont remember rn 1</h1>
        <input type="radio" name="q2" value="0"> Other question i dont remember rn 1 <br>
        <input type="radio" name="q2" value="1"> Other question i dont remember rn 1 <br>
        <h1>Other question i dont remember rn 2</h1>
        <input type="radio" name="q3" value="0"> Other question i dont remember rn 2 <br>
        <input type="radio" name="q3" value="1"> Other question i dont remember rn 2 <br>
        <h1>Other question i dont remember rn 3</h1>
        <input type="radio" name="q4" value="0"> Other question i dont remember rn 3 <br>
        <input type="radio" name="q4" value="1"> Other question i dont remember rn 3 <br>

        <input type="submit" value="Register"/>
    </form>


    <p style="color:red;">${UnPwLength}</p>

</body>
</html>
