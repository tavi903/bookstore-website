<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="config.ActionConfig" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Books - Evergreen BookStore Administration</title>
	<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
	<link rel="stylesheet" href="../css/style.css" />
 	<link rel="stylesheet" href="./../js/jquery-ui-themes-1.12.1/jquery-ui.css">
	<script src="./../js/jquery-1.12.4.js"></script>
 	<script src="./../js/jquery-ui-1.12.1/jquery-ui.js"></script>
 	<style>
 		.slider-background {
			background: cornflowerblue;
		}
 	</style>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<datalist id="list-title">
		<c:forEach var="bookTitle" items="${titles}">
			<option value="${bookTitle}">
		</c:forEach>
	</datalist>
	
	<datalist id="list-author">
		<c:forEach var="bookAuthor" items="${authors}">
			<option value="${bookAuthor}">
		</c:forEach>
	</datalist>
	
	<div align="center">
		<h1 class="pageheading">Books Management</h1>
		<a href="view?action=create_book">Create New Book</a>
	</div>
	
	<c:if test = "${not empty message}">
		<div align="center">
			<h4><i>${message}</i></h4>
		</div>
	</c:if>
	
	<div align="center">
		
		
		<form action="view?action=${ActionConfig.SEARCH_BOOKS}&page=1" autocomplete="off" method="post" style="width: 700px; margin: 12px auto; border-style: outset; border-radius: 12px;">
			<input type="hidden" readonly id="lowerPrice" name="lowerPrice" value="${params.get('lowerPrice')}"/>
			<input type="hidden" readonly id="upperPrice" name="upperPrice" value="${params.get('upperPrice')}"/>
			<table>
				<tr>
					<td colspan="2">
						<input style="width: 100%;" placeholder="Title" type="search" id="title" name="title" value="${params.get('title')}" list="list-title"/>
					</td>
					<td colspan="2">
						<input style="width: 100%;" placeholder="Author" type="search" id="author" name="author" value="${params.get('author')}" list="list-author"/>
					</td>
				</tr>
				<tr>
					<td colspan="1">Category:</td>
					<td colspan="1">
						<select style="width: 100%;" name="category" id="category">
						  <option value="" ${params.get('category') == null ? 'selected' : ''}></option>
						  <c:forEach var="category" items="${applicationScope.cache.get('categories')}">
						  	<option value="${category.categoryId}" ${params.get('category') == category.categoryId ? 'selected' : ''}>${category.name}</option>
						  </c:forEach>
						</select>
					</td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="4">
					Price Range:
					<span id="lowerRange"></span>
					-
					<span id="upperRange"></span>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="slider" style="width: 100%;"></div>
					</td>
				</tr>
				<tr>
					<td align="left">From Publish Date:</td>
					<td align="left"><input type="date" id="lowerPublishDate" name="lowerPublishDate" value="${params.get('lowerPublishDate')}"/></td>
					<td align="left">To Publish Date:</td>
					<td align="left"><input type="date" id="upperPublishDate" name="upperPublishDate" value="${params.get('upperPublishDate')}"/></td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: right;">
						<button type="submit" style="width: 66px;">Search</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div align="center" style="margin-top: 10px;">
		<table>
			<tr>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Publish Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${books}">
				<tr>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.price}</td>
					<td>${book.publishDate}</td>
					<td>
						<a href="view?action=edit_book?id=${book.bookId}">Edit</a>
						<a href="javascript:confirmDelete(${book.bookId})">Delete</a>
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
			  				<li style="display: inline;"><a style="color: blue; font-size: x-large;" href="list_books?page=${loop.index}">${loop.index}</a></li>
			  			</c:when>
			  			<c:otherwise>
			  				<li style="display: inline;"><a href="view?action=${ActionConfig.SEARCH_BOOKS}&page=${loop.index}">${loop.index}</a></li>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			  </ul>
		</div>
		<!-- Pagination -->
	</div>
	
	<jsp:directive.include file="footer.jsp" />

</body>
<script type="text/javascript">
	$("#lowerRange").get(0).innerHTML=$("#lowerPrice").get(0).value==""?${minPrice}-0.1:$("#lowerPrice").get(0).value;
	$("#upperRange").get(0).innerHTML=$("#upperPrice").get(0).value==""?${maxPrice}+0.1:$("#upperPrice").get(0).value;
	$( "#slider" ).slider({
		min: ${minPrice}-0.1,
		max: ${maxPrice}+0.1,
		step: 0.05,
		range: true,
		values: [$("#lowerPrice").get(0).value==""?${minPrice}-0.1:$("#lowerPrice").get(0).value, $("#upperPrice").get(0).value==""?${maxPrice}+0.1:$("#upperPrice").get(0).value],
		classes: {
			"ui-slider-range": "ui-corner-all slider-background"
		},
		slide: function( event, ui ) { 
			$("#lowerPrice").get(0).value = ui.values[0];
			$("#lowerRange").get(0).innerHTML = ui.values[0];
			$("#upperPrice").get(0).value = ui.values[1];
			$("#upperRange").get(0).innerHTML = ui.values[1];
		}
	});

	function confirmDelete(bookId) {
		if(confirm("Do you want to delete book with ID "+bookId+"?")) {
			window.location = 'delete_book?id='+bookId;
		}
	}
</script>
</html>