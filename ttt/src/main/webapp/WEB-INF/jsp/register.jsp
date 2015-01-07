<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<style>
body {
	background: url("BG/regg.gif");
	background-size: 100% 200%;
	background-repeat: no-repeat;
}
</style>
</head>
<body background="BG/regg.gif">
<p align="center"><img src="BG/reg.gif" width="300" height="300" alt="smile" /></p>

	<form:form modelAttribute="Users" >

		<table align="center">
			<tr>
				<td><font face="modern" color="white">Username: <form:input path="username" />  <b> <form:errors path="username" /></b><br /></font></td>
			</tr>
			<tr>
				<td><font face="modern" color="white">Password: <form:input path="password" /> <b>  <form:errors path="password" /></b> <br /></font></td>
			</tr>
			<tr>
				<td><font face="modern" color="white">E-mail: <form:input path="email" /><b> <form:errors path="email" /></b> <br /></font></td>
			</tr>

			<tr>
				<td colspan='2'><input name="register" type="submit" value="Register"/></td>
			</tr>
		</table>

	</form:form>
<br><p align="center">
<a href="login.html">Back to Login Page</a><br><br>
	<a href="http://localhost:8080/ttt/">BACK to Home Page</a></p>
</body>
</html>