<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="false"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	      
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	
	<h3 style="color: red;">Hello Admin</h3>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_STUDENT')">
	
	<h3 style="color: red;">Hello Student</h3>
	</sec:authorize>
	
	
</body>
</html>