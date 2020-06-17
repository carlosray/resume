<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>

<%@ attribute name="index" required="true" type="java.lang.Object" %>

<div id="ui-item-${index}" class="row ui-item skill-item">
    <div class="col-xs-5 col-sm-4 col-md-2 form-group">
        <form:hidden path="skills[${index}].id"/>
        <form:hidden path="skills[${index}].profile.id"/>
        <form:select class="form-control" path="skills[${index}].category">
            <%--@elvariable id="skillCategories" type="java.util.List<com.resume.entity.SkillCategory>"--%>
            <form:options items="${skillCategories}" itemValue="category" itemLabel="category"/>
        </form:select>
    </div>
    <div class="col-xs-7 col-sm-8 col-md-10 value-container">
        <button type="button" class="close" onclick="$('#ui-item-${index}').remove();">
            <span aria-hidden="true">&times;</span>
        </button>
        <form:textarea class="form-control pull-right" required="required" rows="2" path="skills[${index}].value"/>
    </div>
</div>
<div class="row skill-delim">
</div>