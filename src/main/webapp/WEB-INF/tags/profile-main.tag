<%--@elvariable id="profile" type="com.resume.entity.Profile"--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<div class="panel panel-primary">
	<a href="/edit"><img class="img-responsive photo" src="/media/avatar/3f95a777-2194-45bd-b1bd-a9ee2b339802.jpg" alt="photo"></a>
	<h1 class="text-center">
		<a style="color: black;" href="/edit">${profile.firstName}</a>
	</h1>
	<h6 class="text-center">
		<strong>Krasnodar, Russia</strong>
	</h6>
	<h6 class="text-center">
		<strong>Age:</strong> 27, <strong>Birthday: </strong> Feb 26, 1989
	</h6>
	<div class="list-group contacts">
		<a class="list-group-item" href="tel:+79530845389"><i class="fa fa-phone"></i> +79530845389</a>
		<a class="list-group-item" href="mailto:bertram-gilfoyle@gmail.com"><i class="fa fa-envelope"></i> bertram-gilfoyle@gmail.com</a>
		<a class="list-group-item" href="javascript:void(0);"><i class="fa fa-skype"></i>bertram-gilfoyle</a>
		<a target="_blank" class="list-group-item" href="https://vk.com/bertram-gilfoyle"><i class="fa fa-vk"></i> https://vk.com/bertram-gilfoyle</a>
		<a target="_blank" class="list-group-item" href="https://facebook.com/bertram-gilfoyle"><i class="fa fa-facebook"></i> https://facebook.com/bertram-gilfoyle</a>
		<a target="_blank" class="list-group-item" href="https://linkedin.com/bertram-gilfoyle"><i class="fa fa-linkedin"></i> https://linkedin.com/bertram-gilfoyle</a>
		<a target="_blank" class="list-group-item" href="https://github.com/bertram-gilfoyle"><i class="fa fa-github"></i> https://github.com/bertram-gilfoyle</a>
		<a target="_blank" class="list-group-item" href="https://stackoverflow.com/bertram-gilfoyle"><i class="fa fa-stack-overflow"></i> https://stackoverflow.com/bertram-gilfoyle</a>
	</div>
</div>