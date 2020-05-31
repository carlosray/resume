<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<h1>Skill category</h1>
<select name="category" class="form-control">
    <c:forEach var="category" items="${skillCategories}">
        <option value="${category.id}">${category.category}</option>
    </c:forEach>
</select>