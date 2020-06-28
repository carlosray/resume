<%--@elvariable id="profileId" type="java.lang.Long"--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="index"    required="true"  type="java.lang.Object"%>
<%@ attribute name="smallUrl" required="false" type="java.lang.String"%>
<%@ attribute name="largeUrl" required="false" type="java.lang.String"%>
<%@ attribute name="name"     required="false" type="java.lang.String"%>
<%@ attribute name="certificate"     required="false" type="com.resume.entity.Certificate"%>

<div id="ui-item-${index}" class="thumbnail ui-item">
	<input id="certificates${index}.smallUrl" name="certificates[${index}].smallUrl" type="hidden" value="${smallUrl}"/>
	<input id="certificates${index}.largeUrl" name="certificates[${index}].largeUrl" type="hidden" value="${largeUrl}"/>
	<input id="certificates${index}.name" name="certificates[${index}].name" type="hidden" value="${name}"/>
	<input id="certificates${index}.profile.id" name="certificates[${index}].profile.id" type="hidden" value="${profileId}"/>
	<input id="certificates${index}.id" name="certificates[${index}].id" type="hidden" value="${certificate.id}"/>

	<button type="button" class="close" onclick="$('#ui-item-${index}').remove();">
		<span aria-hidden="true">&times;</span>
	</button>
	<a class="certificate-link" href="${largeUrl}" data-title="${name}" data-url="${largeUrl}">
		<img class="img-responsive" src="${largeUrl}" alt="${name}">
		<span>${name}</span>
	</a>
</div>