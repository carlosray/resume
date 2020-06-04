<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-code"></i> Technical Skills <a class="edit-block" href="${pageContext.request.contextPath}/edit/skills">Edit</a>
		</h3>
	</div>
	<div class="panel-body">
		<table class="table table-striped table-bordered">
			<tbody>
			<tr>
				<th style="width: 140px;">Category</th>
				<th>Frameworks and technologies</th>
			</tr>
			<c:forEach var="skill" items="${profile.skills}">
				<tr>
					<td><c:out value="${skill.category}"/></td>
					<td><c:out value="${skill.value}"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>