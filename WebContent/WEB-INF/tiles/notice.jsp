<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="col-md-6 col-md-offset-3">
		<h1>This is Notice Page</h1>
		<table class="table table-striped">

			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Notice</th>
			</tr>

			<c:forEach var="notice" items="${notices}">
				<tr>
					<td><c:out value="${notice.id}" /></td>
					<td><c:out value="${notice.user.name}" /></td>
					<td><c:out value="${notice.user.email}" /></td>
					<td><c:out value="${notice.text}" /></td>
				</tr>

			</c:forEach>
		</table>
	</div>
