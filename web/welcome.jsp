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
        <%=headers.get(i)%>
        <%=bodies.get(i)%>
    <%} %>

</body>
</html>
