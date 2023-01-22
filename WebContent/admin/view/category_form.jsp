<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tavi903.config.ActionConfig" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Category Form</title>
	<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<c:if test="${ category == null }">
			<h1 class="pageheading">Create New Category</h1>
		</c:if>
		<c:if test="${ category != null }">
			<h1 class="pageheading">Edit Category</h1>
		</c:if>
	</div>
	
	<div align="center">
		<c:if test="${ category == null }">
			<form action="view?action=${ActionConfig.CREATE_CATEGORY}" autocomplete="off" method="post">
				<table>
					<tr>
						<td align="right">Name:</td>
						<td align="left"><input type="text" id="name" name="name" size="30"/></td>
					</tr>
					<tr>
						<td align="right">Deleted:</td>
						<td align="left"><input type="checkbox" id="isDeleted" name="isDeleted" value="true"/></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" style="margin-right: 20px; width: 66px;">Save</button>
							<button type="button" onclick="javascript:history.go(-1)">Cancel</button>
						</td>
						<td></td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${ category != null }">
			<form action="view?action=${ActionConfig.EDIT_CATEGORY}" method="post">
				<input hidden="true" name="categoryId" value="${category.categoryId}"/>
				<input hidden="true" name="lastUpdate" value="${category.lastUpdate}"/>
				<table>
					<tr>
						<td align="right">Name:</td>
						<td align="left"><input type="text" id="name" name="name" value="${category.name}" size="30"/></td>
					</tr>
					<tr>
						<td align="right">Deleted:</td>
						<td align="left"><input type="checkbox" id="isDeleted" name="isDeleted" value="true" ${category.deleted == true ? 'checked' : ''}/></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" style="margin-right: 20px; width: 66px;">Save</button>
							<button type="button" onclick="javascript:history.go(-1)">Cancel</button>
						</td>
						<td></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>