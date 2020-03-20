<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Resume Web
        </title>
        <meta content="width=device-width, initial-scale=1" name="viewport"/>
        <jsp:include page="/WEB-INF/section/css.jsp"></jsp:include>
    </head>
    <body class="resume">
        <jsp:include page="/WEB-INF/section/header.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/section/nav.jsp"></jsp:include>
        <section class="main">
            <sitemesh:write property="body"></sitemesh:write>
        </section>
        <jsp:include page="/WEB-INF/section/footer.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/section/js.jsp"></jsp:include>
    </body>
</html>