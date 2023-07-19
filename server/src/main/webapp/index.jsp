<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <h2>Welcome</h2>
    <h3>Register</h3>
    <p>Create a new account for history recording</p>
    <form action="RegisterServlet" method="post">
        <p>Username: <input type="text" name="username" value=""></p>
        <p>Password: <input type="text" name="password" value=""></p>
        <input type="submit" value="register">
    </form>

    <h3>Login</h3>
    <p>Hint: You can use default guest account to skip registering</p>
    <form action="LoginServlet" method="post">
        <p>Username: <input type="text" name="username" value="guest"></p>
        <p>Password: <input type="text" name="password" value="guest"></p>
        <input type="submit" value="login">
    </form>
    </body>
</html>
