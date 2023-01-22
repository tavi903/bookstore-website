<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tavi903.config.ActionConfig" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Evergreen BookStore Administration</title>
	<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h1 class="pageheading">Administrative Dashboard</h1>	
	</div>
	
	<div align="center">
	<hr width="60%" />
		<h2>Quick actions:</h2>
		<b>
		<a href="view?action=${ActionConfig.REDIRECT}:book_form.jsp">New Book</a> &nbsp;
		<a href="create_user">New User</a> &nbsp;
		<a href="create_category">New Category</a> &nbsp;
		<a href="create_customer">New Customer</a>
		</b>
	</div>
	
	<div align="center">
		<hr width="60%" />
		<h2>Recent sales:</h2>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<h2>Recent reviews:</h2>
	</div>
	
	<div align="center">
		<hr width="60%" />
		<h2>Statistics:</h2>
		<hr width="60%" />
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>