<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.05.2020
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="my" uri="/WEB-INF/myTags.tld" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="Account" method="post">
    <c:out value="Login:" /><br>
    <input type="text" size="5" name="login"><br>
    <c:out value="Password:" /><br>
    <input type="text" size="5" name="password"><br>
    <my:LoginTag value="login"/>
<%--    <input type="submit" value="Login">--%>

</form>
<form action="Register.jsp" method="post">
    <my:LoginTag value="registration"/>
</form>
<br>
</body>
</html>
