<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Details about pet ${foundPet.name}</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Species</th>
					<th>Weight</th>
					<th>Date Of Birth</th>
				</tr>
			</thead>
				<tr>
					<td>${foundPet.name}</td>
					<td>${foundPet.species}</td>
					<td>${foundPet.weight}</td>
					<td>${foundPet.dateOfBirth}</td>
				</tr>
		</table>

		<table class="table table-inverse">
			<thead>
				<tr>
					<th>Date of visit</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${visits}" var="visit">
					<tr>
						<td>${visit.dateOfVisit}</td>
						<td>${visit.description}</td>
					</tr>
				</c:forEach>
			</tbody>	
		</table>


	</div>
	<script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="/webjars/jquery/2.2.3/dist/jquery.min.js"></script>
</body>
</html>