<%@page contentType="text/html;charset=UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
</
<body>
<div role="navigation">
    <div class="navbar navbar-expand-sm bg-primary navbar-dark">
        <a href="/main" class="navbar-brand">M$n$ger</a>
            <ul class="navbar-nav lolkekcheburek">
                <li class="nav-item"><a href="show-add-income">Add Income</a> </li>
                <li class="nav-item"><a href="show-add-expenses">Add Expenses</a> </li>
                <li class="nav-item"><a href="#">Log out</a> </li>
            </ul>
    </div>
</div>
<c:if test="${mode=='MODE_HOME'}">
    <div class="container text-center" id="tasksDiv">
	    <h3>All transaction</h3>
	    <hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Date</th>
						<th>Description</th>
						<th>Capital</th>
						<th>In</th>
						<th>Out</th>
						<th>Balance</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="transaction" items="${transactions}">
						<tr>
							<td><fmt:formatDate type="date" pattern="dd-MM-yyyy" value="${transaction.date}"/></td>
							<td>${transaction.description}</td>
							<td>${transaction.capital}</td>
							<td>
							    <c:if test="${transaction.type == '+'}">
							        ${transaction.sum}
							    </c:if>
							</td>
							<td>
							    <c:if test="${transaction.type == '-'}">
                                    ${transaction.sum}
                                </c:if>
                            </td>
							<td>
							    <c:if test="${transaction.type == '+'}">
                               		<c:out value="${transaction.capital + transaction.sum}"/>
                               	</c:if>
                               	<c:if test="${transaction.type == '-'}">
                                    <c:out value="${transaction.capital - transaction.sum}"/>
                                </c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</c:if>
<c:if test="${mode=='MODE_ADD_INCOME'}">
    <div class="container text-center" id="tasksDiv">
	    <h3>Add Income</h3>
	    <hr>
		<form class="form-horizontal" method="post" action="add-income">
			<input type="hidden" name="type" value="+">
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
					<c:out value="${error}"/>
				</div>
			</c:if>
			<div class="form-group">
				<label class="col-md-3">Capital</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="capital" value="${capital}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3">Sum</label>
				<div class="col-md-7">
					<input type="number" class="form-control" name="sum" step="0.01">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3">Description</label>
				<div class="col-md-7">
					<textarea class="form-control" name="description"></textarea>
				</div>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Add">
			</div>
		</form>
	</div>
</c:if>
<c:if test="${mode=='MODE_ADD_EXPENSES'}">
    <div class="container text-center" id="tasksDiv">
	    <h3>Add Expenses</h3>
	    <hr>
		<form class="form-horizontal" method="post" action="add-expenses">
			<input type="hidden" name="type" value="-">
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
					<c:out value="${error}"/>
				</div>
			</c:if>
			<div class="form-group">
				<label class="col-md-3">Capital</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="capital" value="${capital}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3">Sum</label>
				<div class="col-md-7">
					<input type="number" class="form-control" name="sum" step="0.01">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3">Description</label>
				<div class="col-md-7">
					<textarea class="form-control" name="description"></textarea>
				</div>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Add">
			</div>
		</form>
	</div>
</c:if>
<script src="static/js/bootstrap.bundle.js"/>
<script src="static/js/bootstrap.js"/>
</body>
</html>
