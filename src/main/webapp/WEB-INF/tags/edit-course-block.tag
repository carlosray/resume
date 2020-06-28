<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="index" required="true" type="java.lang.Object" %>
<%@ attribute name="course" required="false" type="com.resume.entity.Course" %>
<jsp:useBean id="dateFormatSymbols" class="java.text.DateFormatSymbols"/>
<%--months[0] : January--%>
<c:set value="${dateFormatSymbols.months}" var="months"/>
<div id="ui-item-${index}" class="panel panel-default">
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
                <input class="form-control" name="courses[${index}].name" id="items${index}.name"
                       placeholder="Example: Java Advanced" value="${course.name}" required="required">
            </div>
            <div class="col-xs-6 col-md-4 form-group">
                <label class="control-label" for="courses${index}.school">Название школы / площадки*</label>
                <input class="form-control" name="courses[${index}].school" id="items${index}.school"
                       placeholder="Example: SourceIt" value="${course.school}" required="required">
            </div>
            <div class="col-xs-12 col-md-4 form-group">
                <label for="courses${index}.finishDate">Дата окончания</label>
                <div class="row">
                    <div class="col-xs-6">
                        <form:select id="courses${index}.finishDateMonth" path="courses[${index}].finishDateMonth"
                                class="form-control">
                            <form:option value="">Not finished yet</form:option>
                            <c:forEach var="month" items="${months}" varStatus="status">
                                <form:option value="${status.index+1}" label="${month}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col-xs-6">
                        <form:select id="courses${index}.finishDateYear" path="courses[${index}].finishDateYear"
                                class="form-control">
                            <form:option value="">Not finished yet</form:option>
                            <%--@elvariable id="years" type="java.util.List"--%>
                            <c:forEach var="year" items="${years}">
                                <form:option value="${year}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>