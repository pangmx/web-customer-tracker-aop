<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>

<html>
	<head>
		<title>
			Customers list
		</title>
		
		<!-- Link to css -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
	</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<input type="button" value="Add Customer" 
				onclick="window.location.href='showFormForAddCustomer'; return false;" 
				class="add-button"/>
			
			<form:form action="searchCustomer" method="GET">
				Search Customer: <input type="text" name="theSearchName">
				<input type="submit" value="Search" class="add-button"/>
			</form:form>
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
				
				<!-- For loop to print out customers list -->
				<c:forEach var="customer" items="${customers}">
				
					<!-- Create a url for update link -->
					<c:url var="updateUrl" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${customer.id}"></c:param>
					</c:url>
					
					<!-- Create a url for delete link -->
					<c:url var="deleteUrl" value="/customer/deleteCustomer">
						<c:param name="customerId" value="${customer.id}"></c:param>
					</c:url>
					
					<tr>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.email}</td>
						<td>
							<a href="${updateUrl}">Update</a>
							|
							<a href="${deleteUrl}" 
								onclick="if (!(confirm('Are you sure to delete this customer?'))) return false;">
								Delete
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>