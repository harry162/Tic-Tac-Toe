<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>TTT Page</title>

<style type="text/css">
table.bottom{position:absolute;top:60%; left:25%}

body {
	background: url("BG/freettt.jpg");
	background-size: 100% 600px;
	background-repeat: no-repeat;
}


.classname {
	-moz-box-shadow:inset 0px 1px 0px 0px #caefab;
	-webkit-box-shadow:inset 0px 1px 0px 0px #caefab;
	box-shadow:inset 0px 1px 0px 0px #caefab;
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #77d42a), color-stop(1, #5cb811) );
	background:-moz-linear-gradient( center top, #77d42a 5%, #5cb811 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#77d42a', endColorstr='#5cb811');
	background-color:#77d42a;
	-webkit-border-top-left-radius:6px;
	-moz-border-radius-topleft:6px;
	border-top-left-radius:6px;
	-webkit-border-top-right-radius:6px;
	-moz-border-radius-topright:6px;
	border-top-right-radius:6px;
	-webkit-border-bottom-right-radius:6px;
	-moz-border-radius-bottomright:6px;
	border-bottom-right-radius:6px;
	-webkit-border-bottom-left-radius:6px;
	-moz-border-radius-bottomleft:6px;
	border-bottom-left-radius:6px;
	text-indent:0;
	border:3px solid #268a16;
	display:inline-block;
	color:#306108;
	font-family:Arial;
	font-size:18px;
	font-weight:bold;
	font-style:normal;
	height:50px;
	line-height:50px;
	width:167px;
	text-decoration:none;
	text-align:center;
	text-shadow:1px 1px 0px #aade7c;
}
.classname:hover {
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #5cb811), color-stop(1, #77d42a) );
	background:-moz-linear-gradient( center top, #5cb811 5%, #77d42a 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#5cb811', endColorstr='#77d42a');
	background-color:#5cb811;
}.classname:active {
	position:relative;
	top:1px;
}






</style>
</head>
<body background="BG/freettt.jpg">
	<br>
	<table style="width: 60%" align="right" class="bottom">
		<tr>
			<td>
				<p align="center">
				<a href="login.html" class="classname">Login</a>
					<!-- <button onclick="window.location.href='register.html';">
						Click here to<br> Register!
					</button> -->
			</td>
			<td><p align="center">
			<a href="register.html" class="classname">Register</a>
				<!-- <button onclick="window.location.href='login.html';">
					Click here to<br> Login! -->
				<!-- </button> -->
			</td>
		</tr>
		<tr>
			<td colspan="2"><p align="center">
			<a href="guestTTT.html" class="classname">Play as Guest</a>
				<!-- <button onclick="window.location.href='guestTTT.html';">
					Play as a<br> Guest ! -->
			<!-- 	</button> -->
			</td>
		</tr>
	</table>
	<!-- 
	<div></div>
	<a href="login.html" class="classname">Login</a>
	<a href="register.html" class="classname">Register</a>
	<a href="guestTTT.html" class="classname">Play as Guest</a>
	 -->	
</body>
</html>