<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WELCOME</title>
<style>
body {
	background: url("BG/tttbg.jfif");
	background-size: 100% 200%;
	background-repeat: no-repeat;
}
</style>

</head>
<body background="BG/tttbg.jfif">
<security:authorize access="hasRole('ROLE_USER')">

	<h3><img src="BG/wc1.gif" width="200" height="200" alt="smile" /><font color="white" size="8"> ${uname}</font></h3>
	<p align="center">
		<a href="ttt/j_spring_security_logout">LogOut</a>
	</p>
	<p><font color="red" size="4"><a href="gamehistory.html">${uname}'s Games History</a></font></p>
	<table border=1 width="75%">
		<tr>
			<th>1-Player Game</th>
			<th>2-Player Game</th>
		</tr>
		<tr>
			<td align="center"><ul>
					<li><a href="ttt.html">Play a Game Against AI</a></li>
				
					<li><a href="resumegame.html">Resume a Saved Game</a></li>
				</ul></td>
			<td align="center"><ul>
					<li><a href="hostgame.html">Host a 2 player Game</a></li>
					<li><a href="joingame.html">Join a 2 player Game</a></li>
				</ul></td>
		</tr>
	</table>
</security:authorize>
</body>
</html>