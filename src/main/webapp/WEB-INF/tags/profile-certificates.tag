<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<div class="panel panel-primary certificates">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-certificate"></i> Certificates <a class="edit-block" href="${pageContext.request.contextPath}/edit/certificates">Edit</a>
		</h3>
	</div>
	<div class="panel-body">
		<c:forEach var="certificate" items="${profile.certificates}">
			<a data-url="${certificate.largeUrl}" data-title="${certificate.name}" href="#" class="thumbnail certificate-link">
				<img alt="${certificate.name}" src="${certificate.smallUrl}" class="img-responsive"> <span><c:out value="${certificate.name}"/></span>
			</a>
		</c:forEach>
	</div>
</div>