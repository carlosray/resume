<%--@elvariable id="profileId" type="java.lang.Long"--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"      	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume" 	tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>

<%@ attribute name="index" 		required="true"  type="java.lang.Object"%>
<%@ attribute name="language" 	required="false" type="com.resume.entity.Language"%>

<div id="ui-item-${index}" class="panel panel-default">
	<input id="languages${index}.profile.id" name="languages[${index}].profile.id" type="hidden" value="${profileId}"/>
	<input id="languages${index}.id" name="languages[${index}].id" type="hidden" value="${language.id}"/>
	<div class="panel-body ui-item">
		<div class="row">
			<div class="col-xs-12">
				<button type="button" class="close" onclick="$('#ui-item-${index}').remove();"><span aria-hidden="true">&times;</span></button>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-md-3 form-group">
				<div class="row">
					<div class="col-xs-6 form-group" style="padding-right:0px;">
						<label for="languages${index}.type">Тип</label>
						<select id="languages${index}.type" name="languages[${index}].type" class="form-control" >
						<%--@elvariable id="languageTypes" type="java.util.Set<com.resume.model.LanguageType>"--%>
						<c:forEach var="languageType" items="${languageTypes}">
						<option value="${languageType.dbValue}" ${languageType == language.type ? 'selected="selected"' : '' }>
							<spring:message code="LanguageType.${languageType}" />
						</option>
						</c:forEach>
						</select>
					</div>
					<div class="col-xs-6 form-group" style="padding-right:8px;">
						<label class="control-label" for="languages${index}.name">Язык</label>
						<input id="languages${index}.type" class="form-control" name="languages[${index}].name" placeholder="Example: English" value="${language.name}" required="required">
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-9">
				<label for="languages${index}.level">Уровень</label>
				<div style="padding: 0 30px;">
					<resume:form-input-slider index="${index}" value="${language != null ? language.level.sliderIntValue : 4}" />
				</div>
			</div>
		</div>
	</div>
</div>