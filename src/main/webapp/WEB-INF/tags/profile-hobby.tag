<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-heart"></i> Hobby <a class="edit-block" href="${pageContext.request.contextPath}/edit/hobby">Edit</a>
		</h3>
	</div>
	<div class="panel-body">
		<div class="hobbies">
			<table class="table table-bordered">
				<tbody>
				<c:forEach var="hobby" items="${profile.hobbies}">
					<tr>
						<td><i class="fa fa-heart"></i></td>
						<td><c:out value="${hobby.name}"/></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>