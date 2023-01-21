<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="config.ActionConfig" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Book Form</title>
	<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<c:if test="${ book == null }">
			<h1 class="pageheading">Create New Book</h1>
		</c:if>
		<c:if test="${ book != null }">
			<h1 class="pageheading">Edit Book</h1>
		</c:if>
	</div>
	
	<div align="center">
	
		<c:if test="${book==null}">
			<form action="multipart?action=${ActionConfig.CREATE_BOOK}" autocomplete="off" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td align="left">ISBN-10:</td>
						<td align="left"><input type="text" id="isbn" name="isbn" size="10"/></td>
					</tr>
					<tr>
						<td align="left">Title:</td>
						<td align="left"><input type="text" id="title" name="title" size="40"/></td>
					</tr>
					<tr>
						<td align="left">Author:</td>
						<td align="left"><input type="text" id="author" name="author" size="40"/></td>
					</tr>
					<tr>
						<td align="left">Category:</td>
						<td align="left">
							<select name="category" id="category">
							  <c:forEach var="category" items="${applicationScope.cache.get('categories')}">
							  	<option value="${category.categoryId}">${category.name}</option>
							  </c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="left">Publish Date:</td>
						<td align="left"><input type="date" id="publishDate" name="publishDate"/></td>
					</tr>
					<tr>
						<td align="left">Price:</td>
						<td align="left"><input type="number" step="0.01" id="price" name="price"/></td>
					</tr>
					<tr>
						<td align="left">Description:</td>
						<td align="left"><textarea id="description" name="description" cols="50" rows="10"></textarea></td>
					</tr>
					<tr>
						<td colspan="2">
							Upload Image: <input type="file" name="bookImage" id="bookImage" accept="image/jpeg" />
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" style="margin-right: 20px; width: 66px;">Save</button>
							<button type="button" onclick="javascript:history.go(-1)">Cancel</button>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		
		<c:if test="${book != null}">
			<form action="multipart?action=${ActionConfig.EDIT_BOOK}" autocomplete="off" method="post" enctype="multipart/form-data">
				<input hidden="true" name="bookId" value="${book.bookId}"/>
				<input hidden="true" name="lastUpdate" value="${book.lastUpdate}"/>
				<table>
					<tr>
						<td align="left">ISBN-10:</td>
						<td align="left"><input type="text" id="isbn" name="isbn" value="${book.isbn}" size="10"/></td>
					</tr>
					<tr>
						<td align="left">Title:</td>
						<td align="left"><input type="text" id="title" name="title" value="${book.title}" size="40"/></td>
					</tr>
					<tr>
						<td align="left">Author:</td>
						<td align="left"><input type="text" id="author" name="author" value="${book.author}" size="40"/></td>
					</tr>
					<tr>
						<td align="left">Category:</td>
						<td align="left">
							<select name="category" id="category">
							  <c:forEach var="category" items="${applicationScope.cache.get('categories')}">
							  	<c:if test="${book.category.categoryId == category.categoryId}">
							  		<option selected value="${category.categoryId}">${category.name}</option>
							  	</c:if>
							  	<option value="${category.categoryId}">${category.name}</option>
							  </c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="left">Publish Date:</td>
						<td align="left"><input type="date" id="publishDate" value="${book.publishDate}" name="publishDate"/></td>
					</tr>
					<tr>
						<td align="left">Price:</td>
						<td align="left"><input type="number" step="0.01" id="price" value="${book.price}" name="price"/></td>
					</tr>
					<tr>
						<td align="left">Description:</td>
						<td align="left"><textarea id="description" name="description" cols="50" rows="10">${book.description}</textarea></td>
					</tr>
					<tr>
						<td colspan="2">
							Upload Image: <input type="file" name="bookImage" id="bookImage" accept="image/jpeg" />
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" style="margin-right: 20px; width: 66px;">Save</button>
							<button type="button" onclick="javascript:history.go(-1)">Cancel</button>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		

	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>