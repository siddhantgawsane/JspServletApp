<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
 language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<html>
<head>
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"> 
<title>logout</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<% 
//session.setAttribute("username",null);
 session.invalidate();
 %> 
 <div id="msg" style="text-align: center">
 You have successfully signed out. Click <a href = "Login.jsp">here</a> to sign in again 
</div>
</body>
</html>