<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume" 	tagdir="/WEB-INF/tags"%>

<resume:edit-tab-header selected="certificates" />

<div class="panel panel-default certificates">
	<div class="panel-body">
		<h4 class="data-header">Сертификаты</h4>
		<hr/>
		<%--@elvariable id="certificateForm" type="com.resume.form.CertificateForm"--%>
		<form:form action="/edit/certificates" method="post" modelAttribute="certificateForm">
			<div id="ui-block-container">
				<c:forEach var="certificate" items="${certificateForm.certificates}" varStatus="status">
					<resume:edit-certificate-block index="${status.index}"
								largeUrl="${certificate.largeUrl}" smallUrl="${certificate.smallUrl}" name="${certificate.name}" certificate="${certificate}"/>
				</c:forEach>
			</div>
			<hr/>
			<div class="row">
				<div class="col-xs-12">
					<a href="javascript:resume.certificates.showUploadDialog();">+ Добавить сертификат</a>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-xs-12 text-center">
					<input type="submit" class="btn btn-primary" value="Сохранить">
				</div>
			</div>
		</form:form>
	</div>
</div>
<script id="ui-block-template" type="text/x-handlebars-template">
	<resume:edit-certificate-block index="{{blockIndex}}" largeUrl="{{largeUrl}}" smallUrl="{{smallUrl}}" name="{{name}}"/>
</script>
<resume:certificate-uploader />
