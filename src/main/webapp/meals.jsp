<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: barrrssss
  Date: 07.10.2020
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>

    <title>Meal list</title>
</head>
<body>
<div>
    <h1>Meals</h1>
</div>
<div>
    <h3>
        <a href="meals?action=create">Add Meal</a>
    </h3>
</div>

<div>
    <table border="1" cellpadding="8" cellspacing="0">
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
        <c:forEach var="mealTo" items="${mealTos}">
            <jsp:useBean id="mealTo" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr style="color:${ mealTo.excess ? 'red' : 'green'};">
                <td>
                    <fmt:parseDate value="${mealTo.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>
                    <fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm"/>
                </td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td><a href="meals?action=edit&mealId=<c:out value="${mealTo.id}"/>">EDIT</a> </td>
                <td><a href="meals?action=delete&mealId=<c:out value="${mealTo.id}"/>">DELETE</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
