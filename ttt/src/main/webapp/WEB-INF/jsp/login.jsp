<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
body {
	background: url("BG/tttlogin.jpg");
	background-size: 100% 600px;
	background-repeat: no-repeat;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>
	<h3>
		<font face="script" color="red" size="10">Login</font>
	</h3>
<c:if test="${not empty error}">
		<div class="errorblock">
			${error}
		</div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
		<table>
			<tr>
				<td><font face="modern" color="red">Username:</font></td>
				<td><input type='text' name='j_username' value=''><br /></td>
			</tr>
			<tr>
				<td><font face="modern" color="red">Password:</font></td>
				<td><input type='password' name='j_password' /> <br /></td>
			</tr>
			<tr>
				<td><input name="login" type="submit" value="Login" /></td>
				<td><input name="reset" type="reset" /></td>
			</tr>
		</table>

	</form>

	<p>
		<font face="modern" color="red">Click here to <a href="register.html">Register</a></font>
	</p>
	<p><a href="http://localhost:8080/ttt/">BACK to Home Page</a></p>
</body>
</html>