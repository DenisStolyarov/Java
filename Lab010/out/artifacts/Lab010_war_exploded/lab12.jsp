<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 17.05.2020
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="/tld/TMILabledTextField.tld" prefix="tag"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${30 < 50}">
        <p>30<50</p><br>
    </c:if>

    <c:set var="number" value="50"/>
    <c:choose>
        <c:when test="${ number > 60 }" >
            <c:out value="число ${ number } больше 60"/>
        </c:when>
        <c:when test="${ number > 40 }" >
            <c:out value="число ${ number } больше 40"/>
        </c:when>
        <c:when test="${ number > 10 }" >
            <c:out value="число ${ number } больше 10"/>
        </c:when>
        <c:otherwise>
            <c:out value="число ${ number } меньше 10"/>
        </c:otherwise>
    </c:choose>

    <br>
    <br>

    <jsp:useBean id="now" class="java.util.Date" />
    <fmt:setLocale value="en-EN"/>
    Вывод даты в формате English<br/>
    Сегодня: <fmt:formatDate value="${now}" /><br/>
    <fmt:setLocale value="ru-RU"/>
    Вывод даты в формате Russian<br/>
    Сегодня: <fmt:formatDate value="${now}" /><br/>
    Стиль времени:
    (short): <fmt:formatDate value="${now}"
                             type="time" timeStyle="short" /><br/>
    (medium):<fmt:formatDate value="${now}"
                             type="time" timeStyle="medium" /><br/>
    (long): <fmt:formatDate value="${now}"
                            type="time" timeStyle="long" /><br/>


    <sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/Servlets?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                       user="root"  password="admin"
                       scope="session" />


    <sql:query var="qryProvider" dataSource="${dataSource}">
        Select * From users;
    </sql:query>

    <table>
        <c:forEach var="row" items="${qryProvider.rows}">
            <tr>
                <td>${row.display_name}</td>
            </tr>
        </c:forEach>
    </table>

<p><tag:TMI1 label="input"></tag:TMI1></p>

<br><br>

    <form action="TableServlet" method="get">
        <input type="text" name="row" placeholder="row"/>
        <br>
        <input type="text" name="column" placeholder="col"/>
        <br/>
        <input type="submit" value="Generate"/>
    </form>


</body>
</html>
