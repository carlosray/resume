<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav>
    <sec:authorize access="isAuthenticated()">
    <div class="container-fluid bg-info">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/welcome">My Resume</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><sec:authentication property="principal.fullName"/><span class="caret"></span></a>
                    <ul class="dropdown-menu profile-menu">
                        <li><a href="/my-profile"><i class="fa fa-eye"></i>Мой профиль</a></li>
                        <li><a href="/edit"><i class="fa fa-pencil"></i>Изменить</a></li>
                        <li><a href="/edit/password"><i class="fa fa-unlock-alt"></i>Пароль</a></li>
                        <li><a href="/remove"><i class="fa fa-trash-o"></i>Удалить</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="javascript:resume.logout('${_csrf.token}');"><i
                                class="fa fa-sign-out"></i>Выйти</a></li>
                    </ul>
                </li>
            </ul>
            <form action="/search" method="get" class="navbar-form navbar-right" role="search">
                <div class="form-group">
                    <input class="form-control" placeholder="Search" name="query" value="${query}">
                </div>
                <button type="submit" class="btn btn-primary">Найти</button>
            </form>
        </div>
    </div>
    </sec:authorize>
</nav>