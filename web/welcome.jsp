<%@ page import="java.util.ArrayList" %>
<%@ page import="AppLayer.Post" %><%--
  Created by IntelliJ IDEA.
  User: Tayfun
  Date: 29.03.2019
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<br/><%ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");

    for(int i=0;i<posts.size();i++) {
        %>
        <h2> <%=posts.get(i).getHeader()%> </h2>
        <p style="font-size: 11px;color: blue"> Posted by: <%=posts.get(i).getPosted_by()%> </p>
        <%=posts.get(i).getBody()%> <br><br>

        <form action="/WebSecProject_war_exploded/Comments" method="post">           <!-- TODO change the url -->
            <input type="submit" value="See comments">
            <input type="hidden" name="i" value="<%=i%>">
        </form>

        <hr>
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
