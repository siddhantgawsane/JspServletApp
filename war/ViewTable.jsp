<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.List" %>
	<%@ page import="gaeProject.UserDB" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Tables</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>User Name</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email Id</th>
			<th>Logins</th>
		</tr>

<%
List<UserDB> l = (List<UserDB>)request.getAttribute("UsersList");
for(UserDB u : l) 
{
%>		<tr>
			<td><%= u.getUserName() %></td>
	 		<td><%= u.getFirstName() %></td>
			<td><%= u.getLastName() %></td>
			<td><%= u.getEmailId() %></td>
			<td><%= u.getLogins() %></td>
		</tr>
<% 
} 
%>
	</table>
</body>
</html>