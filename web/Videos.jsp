<%@ page import="beens.VideoBean" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>
<%
    //allow access only if session exists
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
    }
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) userName = cookie.getValue();
        }
    }
%>
<h3>Hi <%=userName %>, Here are all the videos</h3>
<form action="LogoutServlet" method="post">
    <input type="submit" value="see more">
</form>
<br>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
