<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-info-circle"></i> Additional info <a class="edit-block" href="${pageContext.request.contextPath}/edit/info">Edit</a>
		</h3>
	</div>
	<div class="panel-body text-justify"><c:out value="${profile.info}"/></div>
</div>