<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 05.05.2020
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

    <jsp:include page="Header.jsp"/>

    <form method="post" action="Registration">
        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br>
        <input type="submit" value="Зарегистрироваться">
    </form>
    <br><br>
    <form action="index.jsp">
        <input type="submit" value="Авторизация">
    </form>

    <jsp:include page="Footer.jsp"/>

</body>
</html>
