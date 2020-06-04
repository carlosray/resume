<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">
            <i class="fa fa-book"></i> Courses <a class="edit-block"
                                                  href="${pageContext.request.contextPath}/edit/courses">Edit</a>
        </h3>
    </div>
    <div class="panel-body">
        <ul class="timeline">
            <c:forEach var="course" items="${profile.courses}">
                <li>
                    <div class="timeline-badge success">
                        <i class="fa fa-book"></i>
                    </div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <h4 class="timeline-title"><c:out value="${course.name} at ${course.school}"/></h4>
                            <p>
                                <small class="dates"><i class="fa fa-calendar"></i> <strong>Finish Date:</strong>

                                    <c:choose>
                                        <c:when test="${empty course.finishDate}">
                                            <strong class="label label-danger"> Not finished yet</strong>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatDate value="${course.finishDate}" type="date"
                                                            pattern="MMM yyyy"/>
                                        </c:otherwise>
                                    </c:choose>

                                </small>
                            </p>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>