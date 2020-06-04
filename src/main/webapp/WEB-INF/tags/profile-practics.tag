<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">
            <i class="fa fa-briefcase"></i> Practice Experience <a class="edit-block"
                                                                   href="${pageContext.request.contextPath}/edit/practics">Edit</a>
        </h3>
    </div>
    <div class="panel-body">
        <ul class="timeline">
            <c:forEach var="practic" items="${profile.practics}">
                <li>
                    <div class="timeline-badge danger">
                        <i class="fa fa-briefcase"></i>
                    </div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <h4 class="timeline-title"><c:out value="${practic.position} at ${practic.company}"/></h4>
                            <p>
                                <small class="dates"><i class="fa fa-calendar"></i> <fmt:formatDate
                                        value="${practic.beginDate}" type="date" pattern="MMM yyyy"/> -
                                    <c:choose>
                                        <c:when test="${empty practic.finishDate}">
                                            <strong class="label label-danger"> Current</strong>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatDate value="${practic.finishDate}" type="date" pattern="MMM yyyy"/>
                                        </c:otherwise>
                                    </c:choose>
                                </small>
                            </p>
                        </div>
                        <div class="timeline-body">
                            <p>
                                <strong>Responsibilities included:</strong> <c:out value="${practic.responsibilities}"/>
                            </p>
                            <p>
                                <strong>Demo: </strong><a href="<c:out value="${practic.demo}"/>"><c:out
                                    value="${practic.demo}"/></a>
                            </p>
                            <p>
                                <strong>Source code: </strong><a href="<c:out value="${practic.src}"/>"><c:out
                                    value="${practic.src}"/></a>
                            </p>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>