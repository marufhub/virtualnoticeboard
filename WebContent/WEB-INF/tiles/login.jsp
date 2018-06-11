<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		document.f.j_username.focus();
	});
</script>
<div class="col-md-6 col-md-offset-3">
	<form class="form-horizontal"
		action='${pageContext.request.contextPath}/j_spring_security_check'
		method='POST'>
		<fieldset>

			<!-- Form Name -->
			<legend>Login</legend>

			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="j_username">User Name</label>
				<div class="controls">
					<input id="j_username" name="j_username" type="text"
						class="input-xlarge">

				</div>
			</div>


			<!-- Password input-->
			<div class="control-group">
				<label class="control-label" for="j_password">Password</label>
				<div class="controls">
					<input id="j_password" name="j_password" type="password"
						class="input-xlarge">

				</div>
			</div>
			<div class="alart-danger" style="color: red;">

				<c:if test="${param.error!=null }">
						Incorrect UserName or password provided.
					</c:if>

			</div>

			<!-- Remember me checkBox-->
			<div class="control-group">
				<label class="control-label" for="j_password">Remember Me
					&nbsp; </label> <input id="_spring_security_remember_me"
					name="_spring_security_remember_me" type="checkbox"
					checked="checked">


			</div>
			<!-- Button -->
			<div class="control-group">
				<label class="control-label" for="submit"></label>
				<div class="controls">
					<button id="submit" name="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>

		</fieldset>
	</form>
	<p>
		<a href="<c:url value='/newaccount' />">Create New Account</a>
	</p>
</div>


