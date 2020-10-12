<%--
  Created by IntelliJ IDEA.
  User: barrrssss
  Date: 08.10.2020
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save Meal</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="">Home</a> </h2>
    <h3>Save meal</h3>
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>

    <form method="post" action="meals">
        <input type="hidden" name="mealId" value="${meal.id}"/>
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"/> </dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" name="description" value="${meal.description}"/> </dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" name="calories" value="${meal.calories}"/></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>

</body>
</html>
