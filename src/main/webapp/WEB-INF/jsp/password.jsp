<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume" 	tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %> 

<div class="panel panel-info small-center-block">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-unlock-alt"></i> Новый пароль для аккаунта
		</h3>
	</div>
	<div class="panel-body edit-password">
		<%--@elvariable id="passwordForm" type="com.resume.form.PasswordForm"--%>
		<form:form action="/edit/password" modelAttribute="passwordForm" method="post">
			<div class="row skill-delim">
				<div class="col-xs-offset-5 col-sm-offset-4 col-md-offset-2 col-xs-7 col-sm-8 col-md-10" style="padding-left:0px;">
					<form:errors cssClass="alert alert-danger" element="div" />
				</div>
			</div>
			<div class="help-block">Введите Ваш новый пароль и подтвердите его </div>
			<div class="form-group">
				<label class="control-label" for="password">Новый пароль</label> 
				<form:password path="password" id="password" cssClass="form-control" required="required"/>
			</div>

			<div class="form-group">
				<label class="control-label" for="confirmPassword">Подтверждение пароля</label> 
				<form:password path="confirmPassword" id="confirmPassword" cssClass="form-control" required="required"/>
			</div>
			<button type="submit" class="btn btn-primary">Обновить пароль</button>
		</form:form>
	</div>
</div>