<%--@elvariable id="profileId" type="java.lang.Long"--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume" 	tagdir="/WEB-INF/tags"%>

<%@ attribute name="index"     required="true" type="java.lang.Object"%>
<%@ attribute name="education" required="false" type="com.resume.entity.Education"%>

<div id="ui-item-${index}" class="panel panel-default">
	<input id="educations${index}.profile.id" name="educations[${index}].profile.id" type="hidden" value="${profileId}"/>
	<input id="educations${index}.id" name="educations[${index}].id" type="hidden" value="${education.id}"/>
	<div class="panel-body ui-item">
		<div class="row">
			<div class="col-xs-12">
				<button type="button" class="close" onclick="$('#ui-item-${index}').remove();">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-md-12}">
				<label class="control-label" for="educations${index}.summary">Развернутая специализация</label>
				<textarea class="form-control" name="educations[${index}].summary" id="educations${index}.summary" style="margin-bottom: 10px;"
						required="required" rows="2">${education.summary}</textarea>
			</div>
			<div class="col-xs-6 form-group">
				<label for="educations${index}.beginYear">Год поступления</label>
				<select id="educations${index}.beginYear" name="educations[${index}].beginYear" class="form-control" >
					<%--@elvariable id="years" type="java.util.List"--%>
					<c:forEach var="year" items="${years}">
					<option value="${year}" ${year == education.beginYear ? 'selected="selected"' : '' }>${year}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-xs-6 form-group">
				<label for="educations${index}.finishYear">Год окончания</label>
				<select id="educations${index}.finishYear" name="educations[${index}].finishYear" class="form-control">
					<option value="">Not finished yet</option>
					<c:forEach var="year" items="${years}">
					<option value="${year}" ${year == education.finishYear ? 'selected="selected"' : '' }>${year}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-xs-12 col-md-6 form-group">
				<label class="control-label" for="educations${index}.university">Университет</label>
				<input class="form-control" name="educations[${index}].university" id="educations${index}.university"
										value="${education.university}" required="required">
			</div>
			<div class="col-xs-12 col-md-6 form-group">
				<label class="control-label" for="educations${index}.faculty">Факультет</label>
				<input class="form-control" name="educations[${index}].faculty" id="educations${index}.faculty"
										value="${education.faculty}" required="required">
			</div>
		</div>
	</div>
</div>