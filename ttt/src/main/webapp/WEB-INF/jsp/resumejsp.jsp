<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resume Game</title>
</head>
<body background="BG/resume.jpg">
<security:authorize access="hasRole('ROLE_USER')">
<h4>Select a game to resume from below list</h4>
<p><a href="welcomepage.html">Back to Home Page</a>
<p align="right"><a href="ttt/j_spring_security_logout">LOGOUT</a>
<table border=2>
<tr>
			<th>No.</th>
			<th>Start Time</th>
			<th>Saved On</th>
			<th>Click to resume</th>
					</tr>
		<c:forEach items="${savedgames}" var="sg" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${sg.starttime}</td>
				<td>${sg.savetime}</td>
				<td><a href="resumegame2.html?idx=${sg.id}">Resume Game</a></td>

			</tr>

		</c:forEach>
</table>
</security:authorize>
</body>
</html>