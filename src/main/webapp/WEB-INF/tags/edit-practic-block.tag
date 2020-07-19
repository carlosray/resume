<%--@elvariable id="profileId" type="java.lang.Long"--%>
<%--@elvariable id="practic" type="com.resume.entity.Practic"--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>

<%@ attribute name="index" required="true" type="java.lang.Object" %>
<%@ attribute name="practic" required="false" type="com.resume.entity.Practic"%>
<jsp:useBean id="dateFormatSymbols" class="java.text.DateFormatSymbols"/>
<%--months[0] : January--%>
<c:set value="${dateFormatSymbols.months}" var="months"/>

<div id="ui-item-${index}" class="panel panel-default">
    <div class="panel-body ui-item">
        <input id="practics${index}.profile.id" name="practics[${index}].profile.id" type="hidden"
               value="${profileId}"/>
        <input id="practics${index}.id" name="practics[${index}].id" type="hidden" value="${practic.id}"/>
        <div class="row">
            <div class="col-xs-12">
                <button type="button" class="close" onclick="$('#ui-item-${index}').remove();">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-6 form-group">
                <label class="control-label" for="practics${index}.position">Должность</label>
                <input id="practics${index}.position" class="form-control" name="practics[${index}].position" value="${practic.position}"
                       placeholder="Example: Java trainee"/>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <label class="control-label"
                       for="practics${index}.company">Компания или организация</label>
                <input id="practics${index}.company" class="form-control" name="practics[${index}].company" value="${practic.company}"
                       placeholder="Example: Example.net" required="required"/>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <label class="control-label" for="practics${index}.beginDate">Дата начала</label>
                <div class="row">
                    <div class="col-xs-6">
                        <select id="practics${index}.beginDate" name="practics[${index}].beginDateMonth"
                                class="form-control">
                            <c:forEach var="month" items="${months}" varStatus="status">
                                <option value="${status.index+1}" ${status.index+1 == practic.beginDateMonth ? 'selected="selected"' : '' }>${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-xs-6">
                        <select id="practics${index}.beginDate" name="practics[${index}].beginDateYear"
                                class="form-control">
                            <%--@elvariable id="years" type="java.util.List"--%>
                            <c:forEach var="year" items="${years}">
                                <option value="${year}" ${year == practic.beginDateYear ? 'selected="selected"' : '' }>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <label class="control-label" for="practics${index}.finishDate">Дата завершения</label>
                <div class="row">
                    <div class="col-xs-6">
                        <select id="practics${index}.finishDate" name="practics[${index}].finishDateMonth"
                                class="form-control">
                            <option value="">Not finished yet</option>
                            <c:forEach var="month" items="${months}" varStatus="status">
                                <option value="${status.index+1}" ${status.index+1 == practic.finishDateMonth ? 'selected="selected"' : '' }>${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-xs-6">
                        <select id="practics${index}.finishDate" name="courses[${index}].finishDateYear"
                                class="form-control">
                            <option value="">Not finished yet</option>
                            <%--@elvariable id="years" type="java.util.List"--%>
                            <c:forEach var="year" items="${years}">
                                <option value="${year}" ${year == practic.finishDateYear ? 'selected="selected"' : '' }>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 col-md-12">
                <label class="control-label"
                            for="practics${index}.responsibilities">Обязанности/Достижения</label>
                <textarea class="form-control" id="practics${index}.responsibilities" name="practics[${index}].responsibilities"
                               style="margin-bottom: 10px;"
                          required="required" rows="2">${practic.responsibilities}</textarea>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <label class="control-label" for="practics${index}.demo">Ссылка на demo</label>
                <input id="practics${index}.demo" class="form-control" name="practics[${index}].demo" value="${practic.demo}"
                            placeholder="Example: http://resume.net"/>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <label class="control-label" for="practics${index}.src">Исходный код</label>
                <input id="practics${index}.src" class="form-control" name="practics[${index}].src" value="${practic.src}"
                            placeholder="Example: https://github.com/carlosray/resume"/>
            </div>
        </div>
        <div class="row skill-delim">
            <div class="col-xs-offset-5 col-sm-offset-4 col-md-offset-2 col-xs-7 col-sm-8 col-md-10" style="padding-left:0px;">
                <form:errors path="practics[${index}]*" cssClass="alert alert-danger" element="div" />
            </div>
        </div>
    </div>
</div>