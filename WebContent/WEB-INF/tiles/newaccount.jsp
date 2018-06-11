<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div class="col-md-6 col-md-offset-3">
		<sf:form class="form-horizontal" method="post"
			action="${pageContext.request.contextPath}/createaccount"
			commandName="user">
			<fieldset>

				<!-- Form Name -->
				<legend>Create Account</legend>

				<!-- Text input-->
				<div class="control-group">
					<label class="control-label" for="username">User Name</label>
					<div class="controls">
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="enter name" class="input-xlarge" />
						<sf:errors path="username" cssClass="alart-danger" />
					</div>
				</div>

				<!-- Text input-->
				<div class="control-group">
					<label class="control-label" for="name">Full Name</label>
					<div class="controls">
						<sf:input id="name" path="name" name="name"
							type="text" placeholder="enter full name" class="input-xlarge" />
						<sf:errors path="name" cssClass="alart-danger" />
					</div>
				</div>
	
				<!-- Text input-->
				<div class="control-group">
					<label class="control-label" for="email">Email</label>
					<div class="controls">
						<sf:input id="email" path="email" name="email" type="text"
							placeholder="enter email" class="input-xlarge" />
						<sf:errors path="email" cssClass="alart-danger" />
					</div>
				</div>

				<!-- Password input-->
				<div class="control-group">
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<sf:input id="password" path="password" name="password"
							type="password" class="input-xlarge" />
						<sf:errors path="password" cssClass="alart-danger" />
					</div>
				</div>

				<!-- Confirm Password input-->
				<div class="control-group">
					<label class="control-label" for="confirmpassword">Confirm
						Password</label>
					<div class="controls">
						<input id="confirmpassword" name="confirmpassword" type="password"
							class="input-xlarge" />
					</div>
				</div>
				<!-- Button -->
				<div class="control-group">
					<label class="control-label" for="submit"></label>
					<div class="controls">
						<button id="submit" name="Create User" class="btn btn-primary">Create
							Notice</button>
					</div>
				</div>


			</fieldset>
		</sf:form>
	</div>

	<script>
		var password1 = document.getElementById('password');
		var password2 = document.getElementById('confirmpassword');

		var checkPasswordValidity = function() {
			if (password1.value != password2.value) {
				password1.setCustomValidity("<fmt:message key='UnmatchPassword.user.password'/>");
			} else {
				password1.setCustomValidity('');
			}
		};

		password1.addEventListener('change', checkPasswordValidity, false);
		password2.addEventListener('change', checkPasswordValidity, false);

		var form = document.getElementById('user');
		form.addEventListener('submit', function() {
			checkPasswordValidity();
			if (!this.checkValidity()) {
				event.preventDefault();
				//Implement you own means of displaying error messages to the user here.
				password1.focus();
			}
		}, false);
	</script>
