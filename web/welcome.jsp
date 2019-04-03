<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Tayfun
  Date: 29.03.2019
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<br/><%ArrayList<String> headers=(ArrayList<String>) request.getAttribute("headers");
    ArrayList<String> bodies=(ArrayList<String>) request.getAttribute("bodies");

    for(int i=0;i<headers.size();i++) {
        %>
        <h1> <%=headers.get(i)%> </h1>
        <%=bodies.get(i)%> <br> <br> <hr>
    <%} %>

<form action="/WebSecProject_war_exploded/MakeAPost" method="post">

    <br> <br>
    <h1>Create a new post:</h1>

    Header: <br>
    <input type="text" name="header" style="width: 700px;height: 35px" maxlength="100" minlength="10"> <br>
    Body: <br>
    <textarea name="body" style="width: 700px;height: 80px" maxlength="2000"> </textarea> <br>

    <input type="submit" value="Make a post"/>

</form>

</body>
</html>
