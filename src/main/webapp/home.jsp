<%@ page import="java.util.List" %>
<%@ page import="model.Staff" %><%--
  Created by IntelliJ IDEA.
  User: johntoan98gmail.com
  Date: 01/07/2022
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Grayscale - Start Bootstrap Theme</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
<!-- Navigation-->

<!-- Masthead-->
<h2>Table Staff</h2>  <button type="button" class="btn btn-success"><a href="/staff?action=create">Create</a></button>


<form  action="/staff">
    <input name="action" value="search" hidden>
    <br>
    <input name="search"><button type="submit">Search</button>
</form>


<table class="container table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>DateOfBirth</th>
        <th>address</th>
        <th>email</th>
        <th>phoneNumber</th>
        <th>Department</th>
        <th>EDIT</th>
        <th>DELETE</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${staff}">
        <tr>
            <td>${p.getId()}</td>
            <td>${p.name}</td>
            <td>${p.dateOfBirth}</td>
            <td>${p.address}</td>
            <td>${p.email}</td>
            <td>${p.phoneNumber}</td>
            <td>${p.getDepartment().nameD}</td>
            <td><button type="button" class="btn btn-warning"><a href="/staff?id=${p.id}&action=edit">edit</a></button></td>
            <td><button type="button" class="btn btn-danger"><a href="/staff?id=${p.id}&action=delete">Delete</a></button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<!-- Footer-->
<footer class="footer bg-black small text-center text-white-50"><div class="container px-4 px-lg-5">HEHE</div></footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
