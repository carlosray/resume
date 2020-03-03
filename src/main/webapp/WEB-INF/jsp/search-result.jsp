<%@ page import="org.apache.log4j.Logger"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
Logger.getLogger("search-result.jsp").debug("Display search-result.jsp");
%>

<html>
<body>
    <h2>Your name: ${name}</h2>
    <br/>
    <a href="/search">Try again</a>
</body>
</html>