<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 11.05.2020
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Item</title>
</head>
<body>


<jsp:include page="Header.jsp"/>

<h3>Edit item</h3>
<form method="post">
    <input type="hidden" value="${item.id}" name="id" />
    <label>Country</label><br>
    <input name="name" value="${item.name}" /><br><br>
    <label>Price</label><br>
    <input name="price" value="${item.price}" /><br><br>
    <input type="submit" value="Send" />
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>
