<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="config.ActionConfig" %>
<div align="center">
	<a href="view?action=${ActionConfig.REDIRECT}:index.jsp"><img src="../images/BookstoreAdminLogo.png"/></a>
	<div>
		Welcome, <c:out value="${sessionScope.userEmail}"></c:out> | <a href="view?action=${ActionConfig.LOGOUT}">Logout</a>
		<br/><br/>
	</div>

	<div id="header-menu">
		<div>
			<a href="view?action=${ActionConfig.GET_ALL_USERS}&page=1">
				<img src="../images/users.png"/>
				<br /> Users
			</a>
		</div>
		<div>
			<a href="view?action=${ActionConfig.GET_ALL_CATEGORIES}&page=1">
				<img src="../images/category.png"/>
				<br /> Categories
			</a>
		</div>
		<div>
			<a href="view?action=${ActionConfig.SEARCH_BOOKS}&page=1">
				<img src="../images/bookstack.png"/>
				<br /> Books
			</a>
		</div>
		<div>
			<a href="">
				<img src="../images/customer.png"/>
				<br /> Customers
			</a>
		</div>
		<div>
			<a href="">
				<img src="../images/review.png"/>
				<br /> Reviews
			</a>
		</div>
		<div>
			<a href="">
				<img src="../images/order.png"/>
				<br /> Orders
			</a>
		</div>

	</div>
</div>
