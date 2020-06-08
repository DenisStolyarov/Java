<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 04.05.2020
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/tld/TMILabledTextField.tld" prefix="tag"%>
<html>
  <head>
    <title>JOVE 10 LAB</title>
  </head>
  <body>
    
    <jsp:include page="Header.jsp"/>
    <form action="LogIn">
      <input type="text" required placeholder="login" name="login"><br>
      <input type="password" required placeholder="password" name="password"><br>
      <tag:LoginTag value="Войти"></tag:LoginTag>
    </form>
      <br><br>
    <form action="Register.jsp">
      <tag:LoginTag value="Зарегистрироваться"></tag:LoginTag>
    </form>

    <jsp:include page="Footer.jsp"/>

  </body>
</html>
