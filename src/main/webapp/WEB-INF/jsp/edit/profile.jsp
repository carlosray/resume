<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"      	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"     	uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form"   	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume" 	tagdir="/WEB-INF/tags"%>

<resume:edit-tab-header selected="profile" />

<div class="panel panel-default edit-profile">
	<div class="panel-body">
		<h1 class="text-center">${profileForm.fullName}</h1>
		<c:if test="${!profileForm.completed}">
		<hr />
		<h4 class="data-header">Следующие поля должны быть заполнены, чтобы завершить регистрацию!</h4>
		</c:if>
		<%--@elvariable id="profileForm" type="com.resume.entity.Profile"--%>
		<form:form modelAttribute="profileForm" action="/edit" method="post" cssClass="form-horizontal data-form" enctype="multipart/form-data">
			<form:hidden path="firstName"/>
			<form:hidden path="lastName"/>
			<form:hidden path="largePhoto"/>
			<div class="form-group">
				<label for="profilePhoto" class="col-sm-2 control-label">Фото*</label>
				<div class="col-sm-5">
					<img id="currentPhoto" src="${profileForm.profilePhoto}" class="edit-photo" /><br />
					<input type="file" name="profilePhoto" id="profilePhoto" />
				</div>
				<div class="col-sm-5 help-block">
					<blockquote>
						1. Фотография может многое рассказать о кандидате: начиная от его эстетических качеств и заканчивая его отношением к поиску серьезной работы<br />
						2. Фотография как на паспорт или в костюме необязательна, главное адекватность и ухоженный, акууратный внешний вид<br /> 
						3. В качестве примеров Вы можете посмотреть на фотографии демонстрационных резюме в данном приложении<br /> 
						4. Размер фотографии должен быть не меньше чем 400x400 <br /> 
						5. Файл должен быть в формате jpg <br />
					</blockquote>
				</div>
			</div>
			<div class="form-group">
				<label for="inputBirthDay" class="col-sm-2 control-label">Дата рождения*</label>
				<div class="col-sm-5">
					<form:input path="birthDay" class="form-control datepicker" data-date-format="yyyy-mm-dd" id="inputBirthDay" placeholder="Example: 1990-12-31" required="required" />
					<div class="row skill-delim">
						<div class="col-xs-offset-5 col-sm-offset-4 col-md-offset-2 col-xs-7 col-sm-8 col-md-10" style="padding-left:0px;">
							<form:errors path="age" cssClass="alert alert-danger" element="div" />
						</div>
					</div>
				</div>
				<div class="col-sm-5 help-block">
					<blockquote>Формат даты: yyyy-mm-dd (четыре цифры года - код месяца - день рождения)</blockquote>
				</div>
			</div>
			<div class="form-group">
				<label for="inputCountry" class="col-sm-2 control-label">Страна*</label>
				<div class="col-sm-5">
					<form:input path="country" class="form-control" id="inputCountry" placeholder="Example: Ukraine" required="required" />
				</div>
				<div class="col-sm-5 help-block"></div>
			</div>
			<div class="form-group">
				<label for="inputCity" class="col-sm-2 control-label">Город*</label>
				<div class="col-sm-5">
					<form:input path="city" class="form-control" id="inputCity" placeholder="Example: Kharkiv" required="required" />
				</div>
				<div class="col-sm-5 help-block"></div>
			</div>
			<div class="form-group">
				<label for="inputEmail" class="col-sm-2 control-label">Email*</label>
				<div class="col-sm-5">
					<form:input path="contactsProfile.email" class="form-control" id="inputEmail" placeholder="Example: richard.hendricks@gmail.com" required="required" />
				</div>
				<div class="col-sm-5 help-block">
					<blockquote>
						1. Желательно, чтобы email содержал Ваше имя и фамилию как указано в загран паспорте. Если указанное имя уже занято, возможны сокращения.<br /> 
						2. Не рекомендуется использовать креативные email, например TheBestDeveloper@gmail.com, lackomka@gmail.com, etc.<br /> 
						3. Не рекомендуется использовать доменное имя работодателя там где Вы сейчас работаете.<br /> 
						4. Для программистов рекомендуется использовать домен @gmail.com
					</blockquote>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPhone" class="col-sm-2 control-label">Телефон*</label>
				<div class="col-sm-5">
					<form:input path="contactsProfile.phone" class="form-control" id="inputPhone" placeholder="Example: +380501234567" required="required" />
				</div>
				<div class="col-sm-5 help-block">
					<blockquote>Номер телефона должен быть рабочим и тем номером с которого Вы будете отвечать на звонки с неизвестных Вам номеров. 
					Номер телефона нужно предоставлять в формате <a href="https://ru.wikipedia.org/wiki/E.164" target="_blank">E.164</a>, например: +380501234567</blockquote>
				</div>
			</div>
			<div class="form-group">
				<label for="inputObjective" class="col-sm-2 control-label">Желаемая должность*</label>
				<div class="col-sm-5">
					<form:input path="objective" class="form-control" id="inputObjective" placeholder="Example: Junior java developer position" required="required" />
				</div>
				<div class="col-sm-5 help-block">
					<blockquote>В данном разделе нужно указать желаемую должность, максимально кратно и понятно</blockquote>
				</div>
			</div>
			<div class="form-group">
				<label for="inputSummary" class="col-sm-2 control-label">Ваша квалификация*</label>
				<div class="col-sm-5">
					<form:textarea path="summary" class="form-control" id="inputSummary" required="required" rows="7"
						placeholder="Example: Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)" />
				</div>
				<div class="col-sm-5 help-block">
					<blockquote>
						1. В данном разделе необходимо описать Ваш опыт (практический или теоретический) по тому направлению в котором Вы ищите работу.<br /> 
						2. Если Вы работали фотографом и хотите изменить профиль своей деятельности не нужно описывать Ваши достижения в фотографии при поиске работы программистом<br /> 
						3. Если во желаемой должности у Вас нет достижений, это повод пройти какие-либо курсы в данном направлении или самостоятельно изучить основы будущей профессии
					</blockquote>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Сохранить</button>
				</div>
			</div>
		</form:form>
	</div>
</div>