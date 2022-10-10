<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<table  border="1" cellspacing="14" cellpadding="10" style="border-collapse:collapse  ">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
                <th></th>
                <th></th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="mealTos" items="${mealTos}">
        <tr>
            <td style="color:${mealTos.excess ? 'green' : 'red'}">${f:formatLocalDateTime(mealTos.dateTime, 'dd-MM-yyyy HH:mm:ss')}</td>
            <td style="color:${mealTos.excess ? 'green' : 'red'}">${mealTos.description}</td>
            <td style="color:${mealTos.excess ? 'green' : 'red'}">${mealTos.calories}</td>
            <td style="color:blue"><a href="users.jsp">Update</a></td>
            <td style="color:blue"><a href="users.jsp">Delete</a></td>
        </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
