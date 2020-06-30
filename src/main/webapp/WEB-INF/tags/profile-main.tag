<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-primary">
    <a href="${pageContext.request.contextPath}/edit"><img class="img-responsive photo"
                                                           src="<c:out value="${profile.largePhoto}"/>" alt="photo"></a>
    <h1 class="text-center">
        <a style="color: black;" href="${pageContext.request.contextPath}/edit"><c:out
                value="${profile.firstName} ${profile.lastName}"/></a>
    </h1>
    <h6 class="text-center">
        <strong><c:out value="${profile.city}"/>, <c:out value="${profile.country}"/></strong>
    </h6>
    <h6 class="text-center">
        <strong>Age: </strong>${profile.age}, <strong>Birthday: </strong> <fmt:formatDate value="${profile.birthDay}"
                                                                                          pattern="MMM dd, yyyy"/>
    </h6>
    <div class="list-group contacts">
        <c:if test="${not empty profile.contactsProfile.phone}"><a class="list-group-item"
                                                                   href="tel:<c:out value="${profile.contactsProfile.phone}"/>"><i
                class="fa fa-phone"></i> <c:out value="${profile.contactsProfile.phone}" default="-"/></a></c:if>
        <c:if test="${not empty profile.contactsProfile.email}"><a class="list-group-item"
                                                                   href="mailto:<c:out value="${profile.contactsProfile.email}"/>"><i
                class="fa fa-envelope"></i> <c:out value="${profile.contactsProfile.email}" default="-"/></a></c:if>
        <c:if test="${not empty profile.contactsProfile.skype}"><a class="list-group-item" href="javascript:void(0);"><i
                class="fa fa-skype"></i><c:out value="${profile.contactsProfile.skype}" default="-"/></a></c:if>
        <c:if test="${not empty profile.contactsProfile.vkontakte}"><a target="_blank" class="list-group-item" href=
                <c:out value="${profile.contactsProfile.vkontakte}"/>><i class="fa fa-vk"></i> <c:out
                value="${profile.contactsProfile.vkontakte}" default="-"/></a></c:if>
        <c:if test="${not empty profile.contactsProfile.facebook}"><a target="_blank" class="list-group-item" href=
                <c:out value="${profile.contactsProfile.facebook}"/>><i class="fa fa-facebook"></i> <c:out
                value="${profile.contactsProfile.facebook}" default="-"/></a></c:if>
        <c:if test="${not empty profile.contactsProfile.linkedin}"><a target="_blank" class="list-group-item" href=
                <c:out value="${profile.contactsProfile.linkedin}"/>><i class="fa fa-linkedin"></i> <c:out
                value="${profile.contactsProfile.linkedin}" default="-"/></a></c:if>
        <c:if test="${not empty profile.contactsProfile.github}"><a target="_blank" class="list-group-item" href=<c:out
                value="${profile.contactsProfile.github}"/>><i class="fa fa-github"></i> <c:out
                value="${profile.contactsProfile.github}" default="-"/></a></c:if>
        <c:if test="${not empty profile.contactsProfile.stackoverflow}"><a target="_blank" class="list-group-item" href=
                <c:out value="${profile.contactsProfile.stackoverflow}"/>><i class="fa fa-stack-overflow"></i> <c:out
                value="${profile.contactsProfile.stackoverflow}" default="-"/></a></c:if>
    </div>
</div>