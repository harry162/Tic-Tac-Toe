<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>HOST A GAME</title>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : "join.json",
			success : function(data) {
				$("#list").empty();
				data.joiners.forEach(function(joiner) {

					$("#list").append("<li>" + joiner + "</li>");
				});
			},
		});
		update();
	});
	function update() {
		$.ajax({
			url : "join.deferred.json",
			success : function(data) {
				$("#list").empty();
				data.forEach(function(joiner) {
					window.location
							.replace("http://localhost:8080/ttt/ttt2.html");
					$("#list").append("<li>" + joiner + "</li>");
				});
				update();
			},
		});
	}
	
	
</script>
<style>
body {
	background: url("BG/wait.jpg");
	background-size: 100% 300%;
	background-repeat: no-repeat;
}
</style>

</head>
<body  background="BG/wait.jpg">
<security:authorize access="hasRole('ROLE_USER')">
	<h3 align="center"><font color="white" size="8">Waiting for other player to Join a Game</font></h3>
	<br></br>
	<p align="center">
		<button onclick="window.location.href='cancelhost.html';">Cancel Hosting</button>
	</p>
	</security:authorize>
</body>
</html>
