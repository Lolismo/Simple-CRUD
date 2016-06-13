<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All pets</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
	<div class="container"></div>
			<table class="table table-striped">
				
				<thead>
				<tr>
					<th>Name</th>
					<th>Species</th>
					<th>Weight</th>
					<th>Date Of Birth</th>
					
				</tr>
			</thead>
				<tbody>
				<c:forEach items="${allPets}" var="pet">
					<tr>
						<td>${pet.name}</td>
						<td>${pet.species}</td>
						<td>${pet.weight}</td>
						<td>${pet.dateOfBirth}</td>
						<td><a class ="btn btn-success" href="/pet/findById/${pet.id}">Show more</a></td>
					</tr>
				</c:forEach>
			</tbody>
	

			</table>	
	<script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="/webjars/jquery/2.2.3/dist/jquery.min.js"></script>

</body>
</html>