<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="config.ActionConfig" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Categories - Evergreen BookStore Administration</title>
	<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h1 class="pageheading">Categories Management</h1>
		<a href="view?action=${ActionConfig.REDIRECT}:category_form.jsp">Create New Category</a>
	</div>
	
	<c:if test = "${not empty message}">
		<div align="center">
			<h4><i>${message}</i></h4>
		</div>
	</c:if>
	
	<div align="center" style="margin-top: 10px;">
		<table>
			<tr>
				<th>Name</th>
				<th><div title="It will not show books that belong to that category to the customers.">Deleted<span style="color: red;">*</span></div></th>
				<th>Actions</th>
			</tr>
			<c:forEach var="category" items="${categories}">
				<tr>
					<td>${category.name}</td>
					<td align="center"><input type="checkbox" ${category.deleted == true ? 'checked' : ''} disabled/></td>
					<td align="center">
						<a href="view?action=${ActionConfig.GET_CATEGORY}&id=${category.categoryId}">Edit</a>
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
			  				<li style="display: inline;"><a style="color: blue; font-size: x-large;" href="view?action=${ActionConfig.GET_ALL_CATEGORIES}&page=${loop.index}">${loop.index}</a></li>
			  			</c:when>
			  			<c:otherwise>
			  				<li style="display: inline;"><a href="view?action=${ActionConfig.GET_ALL_CATEGORIES}&page=${loop.index}">${loop.index}</a></li>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			  </ul>
		</div>
	</div>
	
	<jsp:directive.include file="footer.jsp" />

</body>
</html>