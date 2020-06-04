<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-bullseye"></i> Objective <a class="edit-block" href="${pageContext.request.contextPath}/edit#inputObjective">Edit</a>
		</h3>
	</div>
	<div class="panel-body">
		<h4><c:out value="${profile.objective}"/></h4>
		<p>
			<strong>Summary of Qualifications:</strong> <br>
			<c:out value="${profile.summary}"/>
		</p>
	</div>
</div>