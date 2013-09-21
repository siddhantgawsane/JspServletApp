<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
 language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ page import="javax.servlet.ServletRequest"%>
<%@ page import="javax.servlet.ServletResponse"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.FilterChain"%>
<html>
<head>
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"> 
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
  <body>
<%
HttpServletResponse hsr = (HttpServletResponse) response;
hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
hsr.setHeader("Expires","0"); 
hsr.setDateHeader("Expires", -1); // Proxies.

if(session.getAttribute("username")!=null)
{
	response.sendRedirect("/HomePage.jsp");
}
%>
	<form action="myPage" method="post">
	Username: <input type="text" name="user"><br>
	Password: <input type="password" name="password">
	<br/>
	<input type="submit" value="Sign In">
	<br/>
	<input type="button" value="Sign Up" onclick="location.href='/SignUp.jsp';">
	<br/>
	<input type="button" value="Login Using Google" onclick="location.href='/GLogin.jsp';">
	</form> 
  </body>
</html> 