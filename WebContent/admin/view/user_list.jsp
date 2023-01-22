<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tavi903.config.ActionConfig" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Users - Evergreen BookStore Administration</title>
	<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h1 class="pageheading">Users Management</h1>
		<a href="view?action=${ActionConfig.REDIRECT}:user_form.jsp">Create New User</a>
	</div>
	
	<c:if test = "${not empty message}">
		<div align="center">
			<h4><i>${message}</i></h4>
		</div>
	</c:if>
	
	<div align="center" style="margin-top: 10px;">
		<table> <!-- border="1" cellpadding="5" -->
			<tr>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listUsers}">
				<tr>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
						<a href="view?action=${ActionConfig.GET_USER}&id=${user.userId}">Edit</a>
						<a href="javascript:confirmDelete(${user.userId})">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- Pagination -->
		<div align="center" style="margin-top: 10px;">
			  <ul>
			  	<c:forEach begin="1" end="${totalPages}" varStatus="loop">
			  		<c:choose>
			  			<c:when test="${currentPage == loop.index}">
			  				<li style="display: inline;"><a style="color: blue; font-size: x-large;" href="view?action=${ActionConfig.GET_ALL_USERS}&page=${loop.index}">${loop.index}</a></li>
			  			</c:when>
			  			<c:otherwise>
			  				<li style="display: inline;"><a href="view?action=${ActionConfig.GET_ALL_USERS}&page=${loop.index}">${loop.index}</a></li>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			  </ul>
		</div>
	</div>
	
	<jsp:directive.include file="footer.jsp" />

</body>
<script type="text/javascript">
	function confirmDelete(userId) {
		if(confirm("Do you want to delete user with ID "+userId+"?")) {
			window.location = 'view?action=${ActionConfig.DELETE_USER}&id='+userId;
		}
	}
</script>
</html>