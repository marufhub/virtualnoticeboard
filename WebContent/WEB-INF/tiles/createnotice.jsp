<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-6 col-md-offset-3">
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="notice">
		<fieldset>

			<!-- Form Name -->
			<legend>Create Notice</legend>

			<sf:input name="id" type="hidden" path="id" />

			<!-- Textarea -->
			<div class="control-group">
				<label class="control-label" for="text">Notice</label>
				<div class="controls">
					<sf:textarea id="text" path="text" name="text" />
					<sf:errors path="text" cssClass="alart-danger" />
				</div>
			</div>

			<!-- Button -->
			<div class="control-group">
				<label class="control-label" for="submit"></label>
				<div class="controls">
					<button id="submit" name="submit" class="btn btn-primary">Save</button>
				</div>
			</div>
			<c:if test="${notice.id != 0}">
				<!-- Button -->
				<div class="control-group">
					<label class="control-label" for="delete"></label>
					<div class="controls">
						<button id="delete" name="delete" class="btn btn-primary">Delete</button>
					</div>
				</div>
			</c:if>
		</fieldset>
	</sf:form>
</div>
