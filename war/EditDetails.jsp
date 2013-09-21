<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Details</title>
</head>
<body>
	<form action="<%= blobstoreService.createUploadUrl("/update")%>" method="post" enctype="multipart/form-data"><br>
	Username: </br><input readonly="readonly" type="text" name="user" value="<%= (String)session.getAttribute("username") %>"></br>
	Password: </br><input type="text" name="password"></br>
	First Name: </br><input type="text" name="fname"></br>
	Last Name: </br><input type="text" name="lname"></br>
	Email Id: </br><input type="text" name="email"></br>
	Profile Pic:</br><input type="file" name="myPic">
	</br>
	<input type="submit" value="Update">
	<input type="button" value="Cancel" onclick="location.href='/HomePage.jsp';">
</form>
</body>
</html>