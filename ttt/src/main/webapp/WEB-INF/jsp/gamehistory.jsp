<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAME HISTORY</title>
<style>body
{
background:url("BG/history.jfif");
background-size:100% 100%;
background-repeat:no-repeat;
}</style>
</head>
<body background="BG/history.jfif">
<security:authorize access="hasRole('ROLE_USER')">
	<h1>Game History of ${uname}</h1>
	
	<p>
		Go to <a href="welcomepage.html">HOME PAGE</a> 
	</p>
	<p align="right"><a href="ttt/j_spring_security_logout">LogOut</a>
	<table border=1>
		<tr>
			<td>The number of games completed</td>
			<td>${cmpltgames}</td>
		</tr>
		<tr>
			<td>The number of 1-player games completed</td>
			<td>${oneplayer}</td>
		</tr>
		<tr>
			<td>The number of 2-player games completed</td>
			<td>${twoplayer}</td>
		</tr>
		<tr>
			<td>The win rate against AI.</td>
			<td>${aiwin} %</td>
		</tr>
		<tr>
			<td>The win rate against human players.</td>
			<td>${playerwin} %</td>
		</tr>
	</table>
	<br>
	<h4>This Month Games of ${uname}</h4>
	<table border=1>
		<tr>
			<th>No.</th>
			<th>Start Time</th>
			<th>OPPONENT</th>
			<th>DURATION</th>
			<th>RESULT</th>
		</tr>
		<c:forEach items="${gh}" var="gh" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${gh.starttime}</td>
				<td>${gh.opponent}</td>
				<td>${gh.duration}</td>
				<td>${gh.result}</td>
			</tr>

		</c:forEach>
	</table>
</security:authorize>
</body>
</html>