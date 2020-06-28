<%--@elvariable id="profileId" type="java.lang.Long"--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="index" required="true" type="java.lang.Object" %>
<%@ attribute name="course" required="false" type="com.resume.entity.Course"%>
<jsp:useBean id="dateFormatSymbols" class="java.text.DateFormatSymbols"/>
<%--months[0] : January--%>
<c:set value="${dateFormatSymbols.months}" var="months"/>
<div id="ui-item-${index}" class="panel panel-default">
    <input id="courses${index}.profile.id" name="courses[${index}].profile.id" type="hidden" value="${profileId}"/>
    <input id="courses${index}.id" name="courses[${index}].id" type="hidden" value="${course.id}"/>
    <div class="panel-body ui-item">
        <div class="row">
            <div class="col-xs-12">
                <button type="button" class="close" onclick="$('#ui-item-${index}').remove();">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6 col-md-4 form-group">
                <label class="control-label" for="courses${index}.name">Название курса*</label>
                <input class="form-control" name="courses[${index}].name" id="courses${index}.name"
                       placeholder="Example: Java Advanced" value="${course.name}" required="required">
            </div>
            <div class="col-xs-6 col-md-4 form-group">
                <label class="control-label" for="courses${index}.school">Название школы / площадки*</label>
                <input class="form-control" name="courses[${index}].school" id="courses${index}.school"
                       placeholder="Example: SourceIt" value="${course.school}" required="required">
            </div>
            <div class="col-xs-12 col-md-4 form-group">
                <label for="courses${index}.finishDate">Дата окончания</label>
                <div class="row">
                    <div class="col-xs-6">
                        <select id="courses${index}.finishDate" name="courses[${index}].finishDateMonth"
                                class="form-control">
                            <option value="">Not finished yet</option>
                            <c:forEach var="month" items="${months}" varStatus="status">
                                <option value="${status.index+1}" ${status.index+1 == course.finishDateMonth ? 'selected="selected"' : '' }>${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-xs-6">
                        <select id="courses${index}.finishDate" name="courses[${index}].finishDateYear"
                                class="form-control">
                            <option value="">Not finished yet</option>
                            <%--@elvariable id="years" type="java.util.List"--%>
                            <c:forEach var="year" items="${years}">
                                <option value="${year}" ${year == course.finishDateYear ? 'selected="selected"' : '' }>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>