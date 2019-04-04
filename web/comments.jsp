<%@ page import="AppLayer.Comment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="AppLayer.Post" %><%--
  Created by IntelliJ IDEA.
  User: Tayfun
  Date: 3.04.2019
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comments</title>
</head>
<body>

<br/><%
    Post post = (Post)request.getAttribute("post");%>

    <h1> <%=post.getHeader()%> </h1>
    <p style="font-size: 12px;color: blue"> Posted by: <%=post.getPosted_by()%> </p>
    <p style="font-size: 25px"><%=post.getBody()%></p> <br><br> <hr>


<br/><%
    ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");

    for(int i=0;i<comments.size();i++) {
        %>
        <p style="font-size: 11px;color: blue"> Posted by: <%=comments.get(i).getPosted_by()%> </p>
        <%=comments.get(i).getComment_body()%> <br><br>
        <hr>
    <%} %>

<form action="/WebSecProject_war_exploded/MakeAComment" method="post">

    <br> <br>
    <h1>Create a new comment:</h1>

    <textarea name="comment" style="width: 700px;height: 80px" maxlength="2000"></textarea> <br>

    <input type="submit" value="Make a Comment"/>

</form>


</body>
</html>
