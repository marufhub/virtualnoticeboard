<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-6 col-md-offset-3">
	<h2>Authorized uses only</h2>
	<table class="table table-striped">

		<tr>

			<th>User Name</th>
			<th>Email</th>
			<th>Role</th>
			<th>Enabled</th>
		</tr>

		<c:forEach var="users" items="${users}">
			<tr>
				<td><c:out value="${users.username}" /></td>
				<td><c:out value="${users.email}" /></td>
				<td><c:out value="${users.authority}" /></td>
				<td><c:out value="${users.enabled}" /></td>
			</tr>

		</c:forEach>
	</table>
</div>
