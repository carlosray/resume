<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>

<%@ attribute name="index" required="true" type="java.lang.Object" %>

<jsp:useBean id="dateFormatSymbols" class="java.text.DateFormatSymbols"/>
<%--months[0] : January--%>
<c:set value="${dateFormatSymbols.months}" var="months"/>

<div id="ui-item-${index}" class="panel panel-default">
    <div class="panel-body ui-item">
        <form:hidden path="practics[${index}].id"/>
        <form:hidden path="practics[${index}].profile.id"/>
        <div class="row">
            <div class="col-xs-12">
                <button type="button" class="close" onclick="$('#ui-item-${index}').remove();">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-6 form-group">
                <form:label class="control-label" path="practics[${index}].position">Должность</form:label>
                <form:input class="form-control" path="practics[${index}].position"
                            placeholder="Example: Java trainee"/>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <form:label class="control-label"
                            path="practics[${index}].company">Компания или организация</form:label>
                <form:input class="form-control" path="practics[${index}].company"
                            placeholder="Example: Example.net" required="required"/>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <form:label class="control-label" path="practics[${index}].beginDate">Дата начала</form:label>
                <div class="row">
                    <div class="col-xs-6">
                        <form:select path="practics[${index}].beginDateMonth" class="form-control">
                            <c:forEach var="month" items="${months}" varStatus="status">
                                <form:option value="${status.index+1}" label="${month}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col-xs-6">
                        <%--@elvariable id="years" type="java.util.List<Integer>"--%>
                        <form:select path="practics[${index}].beginDateYear" class="form-control" items="${years}">

                        </form:select>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <label class="control-label" for="items${index }finishDate">Дата завершения</label>
                <div class="row">
                    <div class="col-xs-6">
                        <form:select path="practics[${index}].finishDateMonth" class="form-control">
                            <form:option value="">Not finished yet</form:option>
                            <c:forEach var="month" items="${months}" varStatus="status">
                                <form:option value="${status.index+1}" label="${month}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col-xs-6">
                        <form:select path="practics[${index}].finishDateYear" class="form-control">
                            <form:option value="">Not finished yet</form:option>
                            <c:forEach var="year" items="${years}">
                                <form:option value="${year}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 col-md-12">
                <form:label class="control-label"
                            path="practics[${index}]responsibilities">Обязанности/Достижения</form:label>
                <form:textarea class="form-control" path="practics[${index}].responsibilities"
                               style="margin-bottom: 10px;"
                               required="required" rows="2"/>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <form:label class="control-label" path="practics[${index}]demo">Ссылка на demo</form:label>
                <form:input class="form-control" path="practics[${index}].demo"
                            placeholder="Example: http://resume.net"/>
            </div>
            <div class="col-xs-12 col-md-6 form-group">
                <form:label class="control-label" path="practics[${index}].src">Исходный код</form:label>
                <form:input class="form-control" path="practics[${index}].src"
                            placeholder="Example: https://github.com/carlosray/resume"/>
            </div>
        </div>
    </div>
</div>