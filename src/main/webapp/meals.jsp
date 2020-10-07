<%--
  Created by IntelliJ IDEA.
  User: barrrssss
  Date: 07.10.2020
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <table>
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="mealTo" items="${mealTos}">
            <%--@elvariable id="mealTo" type="ru.javawebinar.topjava.model.MealTo"--%>
            <tr style="color:${ mealTo.excess ? 'red' : 'green'};">
                <td>${mealTo.dateTime}</td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
