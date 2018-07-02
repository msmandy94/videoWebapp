<!DOCTYPE html>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Login Page</title>
</head>
<body>
<%
    //redirect to home if valid sesson exists
    String user = null;
    if (session.getAttribute("userId") != null) {
        response.sendRedirect("Home.jsp");
    }
%>

<form action="LoginServlet" method="post">

    Username: <input type="text" name="userId" required>
    <br>
    Password: <input type="password" name="pwd" required>
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>
