<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
    <div class="row">
        <div class="col-md-4 col-sm-6">
            <resume:profile-main/>
            <div class="hidden-xs">
                <c:if test="${not empty profile.languages}"><resume:profile-languages/></c:if>
                <c:if test="${not empty profile.hobbies}"><resume:profile-hobby/></c:if>
                <c:if test="${not empty profile.info}"><resume:profile-info/></c:if>
            </div>
        </div>
        <div class="col-md-8 col-sm-6">
            <c:if test="${not empty profile.objective}"><resume:profile-objective/></c:if>
            <c:if test="${not empty profile.skills}"><resume:profile-skills/></c:if>
            <c:if test="${not empty profile.practics}"><resume:profile-practics/></c:if>
            <c:if test="${not empty profile.certificates}"><resume:profile-certificates/></c:if>
            <c:if test="${not empty profile.courses}"><resume:profile-courses/></c:if>
            <c:if test="${not empty profile.educations}"><resume:profile-education/></c:if>
        </div>
        <div class="col-xs-12 visible-xs-block">
            <c:if test="${not empty profile.languages}"><resume:profile-languages/></c:if>
            <c:if test="${not empty profile.hobbies}"><resume:profile-hobby/></c:if>
            <c:if test="${not empty profile.info}"><resume:profile-info/></c:if>
        </div>
    </div>
</div>