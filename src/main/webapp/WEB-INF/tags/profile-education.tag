<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-graduation-cap"></i> Education <a class="edit-block" href="${pageContext.request.contextPath}/edit/education">Edit</a>
		</h3>
	</div>
	<div class="panel-body">
		<ul class="timeline">
			<c:forEach var="education" items="${profile.educations}">
			<li>
				<div class="timeline-badge warning">
					<i class="fa fa-graduation-cap"></i>
				</div>
				<div class="timeline-panel">
					<div class="timeline-heading">
						<h4 class="timeline-title"><c:out value="${education.summary}"/></h4>
						<p>
							<small class="dates"><i class="fa fa-calendar"></i>
								<c:out value="${education.beginYear}"/> - <c:out value="${education.finishYear}" default="now"/></small>
						</p>
					</div>
					<div class="timeline-body">
						<p><c:out value="${education.faculty}, ${education.university}"/></p>
					</div>
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>