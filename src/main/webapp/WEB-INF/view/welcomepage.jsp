<%@ page language="java" contentType="text/html"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>Home</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/bootstrap-grid.min.css" rel="stylesheet">
    <link href="static/css/bootstrap-reboot.min.css" rel="stylesheet">
</head>
<body>
<div role="navigation">
    <div class="navbar navbar-expand-sm bg-primary navbar-dark">
        <a href="/login" class="navbar-brand">M$n$ger</a>
        <ul class="navbar-nav">
            <li class="nav-item"><a href="/show-login">Log in</a></li>
            <li class="nav-item"><a href="/">Register</a></li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="jumbotron text-center">
        <c:choose>
            <c:when test="${mode=='MODE_REGISTER'}">
                <h1>Welcome to Money M$n$ger</h1>
                <h3>Enter information about you</h3>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                        <c:out value="${error}"/>
                    </div>
                </c:if>
                <form class="form-horizontal" method="post" action="save-user">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="form-group">
                        <label class="control-label col-md-3">Surname</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="surname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Name</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Login</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="login">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Password</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Log in">
                    </div>
                </form>
            </c:when>
            <c:when test="${mode=='MODE_LOGIN'}">
                <h1>Welcome to Money M$n$ger</h1>
                <h3>Enter login and password</h3>
                <form class="form-horizontal" method="post" action="/main">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            <c:out value="${error}"/>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-md-3">Login</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="login">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Password</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Log in">
                    </div>
                </form>
            </c:when>
        </c:choose>
    </div>
</div>
<script src="static/js/bootstrap.bundle.js"/>
<script src="static/js/bootstrap.js"/>
</body>
</html>