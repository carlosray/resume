<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="profiles" type="java.util.List<com.resume.entity.Profile>"--%>
<c:forEach var="profile" items="${profiles}">
    <div class="panel panel-default profile-item">
        <div class="media panel-body">
            <div class="media-left media-top">
                <a href="/<c:out value="${profile.uid}"/>"><img alt="<c:out value="${profile.fullName}"/>"
                                                                src="<c:out value="${profile.smallPhoto}"/>"
                                                                class="photo"></a>
                <a href="/<c:out value="${profile.uid}"/>" class="btn btn-primary visible-xs-block"
                   style="margin-top: 15px;">Детали</a>
            </div>
            <div class="media-body search-result-item">
                <a href="/<c:out value="${profile.uid}"/>" class="btn btn-primary pull-right hidden-xs">Детали</a>
                <h4 class="media-heading">
                    <a href="/<c:out value="${profile.uid}"/>"><c:out value="${profile.fullName}"/>, ${profile.age}</a>
                </h4>
                <strong><c:out value="${profile.objective}"/></strong>
                <p><c:out value="${profile.city}"/>,<c:out value="${profile.country}"/></p>
                <blockquote>
                    <small><c:out value="${profile.summary}"/></small>
                </blockquote>
            </div>
        </div>
    </div>
</c:forEach>