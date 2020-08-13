<%--@elvariable id="profilePage" type="org.springframework.data.domain.Page"--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags"%>

<div class="well">
    <p>Найдено <strong>${profilePage.totalElements}</strong> профилей</p>
</div>
<jsp:include page="profiles.jsp"/>