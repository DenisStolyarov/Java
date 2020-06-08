<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.04.2020
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" uri="/WEB-INF/myTags.tld" %>
<%--// Теги для работы с XML-документами--%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%--// Теги для работы с базами данных--%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>

<%--// Теги для форматирования и интернационализации информации (i10n и i18n)--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>


<form action = "Welcome" method = "get">
    <c:out value="URL:" />
    <input type="text" size="5" name="image"><br>
    <c:out value="Text:" />
    <input type="text" size="5" name="text"><br>
    <input type="submit" value="Add">
    <input type="submit" value="Delete" name="del"><br>
    <c:out value="Input index there:" />
    <input type="text" name="index"><br>
    <c:out value="New image there:" />
    <input type="text" name="correctimage"><br>
    <c:out value="New text there:" />
    <input type="text" name="correcttext"><br>
    <input type="submit" value="Accept" name="Button">
</form>

<form action = "Welcome" method = "post">
    <input type="submit" value="View">
</form>
<form action="Servlet" method="get">
    <input type="text" name="row" placeholder="row"/>
    <br>
    <input type="text" name="column" placeholder="col"/>
    <br/>
    <input type="submit" value="Generate"/>
</form>
<my:TextTag name = "NAME"/>
<c:set var="Balance" value="12345.6789"/>
<f:formatNumber value="${Balance}" type="currency"/>
<s:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://localhost/test"
                   user="root"  password="1234"/><br/>
Length of the String-1 is: ${fn:length(Balance)}<br/>

<c:set var="vegetable">
    <vegetables>
        <vegetable>
            <name>onion</name>
            <price>40/kg</price>
        </vegetable>
        <vegetable>
            <name>Potato</name>
            <price>30/kg</price>
        </vegetable>
        <vegetable>
            <name>Tomato</name>
            <price>90/kg</price>
        </vegetable>
    </vegetables>
</c:set>
<x:parse xml="${vegetable}" var="output"/>
<b>Name of the vegetable is</b>:
<x:out select="$output" /><br>
<b>Price of the Potato is</b>:
<x:out select="$output" />
</body>
</html>
