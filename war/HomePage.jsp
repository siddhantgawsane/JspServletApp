<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<% 
HttpServletResponse hsr = (HttpServletResponse) response;
hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
hsr.setHeader("Expires","0"); 
hsr.setDateHeader("Expires", -1); // Proxies.

if(session.getAttribute("username")==null)
{
	response.sendRedirect("/Login.jsp");
}
%>
	<br>
	</br>Welcome
	<%=session.getAttribute("username")%>

	</br>Profile Pic :

	<table style="margin: 0px; margin-top: 15px;">
		<tr>
			<td>
				<img width="200" height="150" src='/serve?blob-key=<%= session.getAttribute("blob-key") %>'>
			</td>
		</tr>
	</table>

			</br>Name :
			<%=session.getAttribute("fname")%>
			<%=session.getAttribute("lname")%>

			</br>Email Id :
			<%=session.getAttribute("emailid")%>

			</br>Number of Logins:
			<%=session.getAttribute("logins")%>

			</br>
			</br>
			<input type="button" value="View Images"
				onclick="location.href='/ViewImages.jsp'">
			</br>
			</br>
			<input type="button" value="View Videos"
				onclick="location.href='/ViewVideos.jsp'">
			</br>
			</br>
			<input type="button" value="View Table"
				onclick="location.href='usertable'">

			</br>
			</br>
			<input type="button" value="Edit Details"
				onclick="location.href='/EditDetails.jsp'">
			</br>
			</br>
			<input type="button" value="Sign Out"
				onclick="location.href='/SignOut.jsp'">
</body>
</html>