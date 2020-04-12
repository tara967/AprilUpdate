<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">


<table border="1">

<tr>
<td>
	<a href="${pageContext.request.contextPath}/welcome">Home</a>

</td>

<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
<td>
 <a href="${pageContext.request.contextPath}/addNewEmployee">Add
        Employee</a>
        </td>
       
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<td>
     <a
        href="${pageContext.request.contextPath}/getEmployees">Show
        Employees</a> 
</td>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')">
<td>
     <a
        href="${pageContext.request.contextPath}/timeTable">Time
        Table</a>
</td>
</sec:authorize>


<td>
<a href="${contextPath}/logout">Logout</a>
</td>
</tr>
</table>

    <form id="logoutForm" method="POST" action="${contextPath}/portal/logout">
    </form>

</div>
