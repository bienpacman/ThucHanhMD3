<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new staff</title>
    <style>
        .message {
            color: green;
        }
    </style>
</head>
<body>
<h1>Create new staff</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/staff">Back to staff list</a>
</p>

<form action="/staff?action=create" method="post">
    <fieldset>
        <legend>Studen information</legend>
        <table>
            <%--            <tr>--%>
            <%--                <td>Id:</td>--%>
            <%--                <td><input type="text" name="id" id="id"></td>--%>
            <%--            </tr>--%>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Birth:</td>
                <td><input type="date" name="birth" id="Birth"></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address" id="address"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="phone" id="phone"></td>
            </tr>
            <tr>s
                <td>Class:</td>
                <td><select name="department" id="idD">
                    <c:forEach var="c" items="${department}">
                        <option value="${c.id}">${c.nameD}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create staff"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>