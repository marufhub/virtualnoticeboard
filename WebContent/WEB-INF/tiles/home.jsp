<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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



	<c:choose>
		<c:when test="${hasNotice}">
			<a href="<c:url value='/createnotice'/>"> Edit or delete your
				Notice</a>

		</c:when>
		<c:otherwise>
			<a href="<c:url value='/createnotice'/>">Add a new Notice</a>
		</c:otherwise>
	</c:choose>

	
	<sec:authorize access="!isAuthenticated()">
		<p>
			<a href="<c:url value='/login' />">Login</a>
		</p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<p>
			<a href="<c:url value='j_spring_security_logout' />">Log Out</a>
		</p>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<p>
			<a href="<c:url value='admin' />">Admin Page</a>
		</p>
	</sec:authorize>
</div>

