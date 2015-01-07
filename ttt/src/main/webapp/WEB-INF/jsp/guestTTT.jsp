<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tic Tac Toe</title>
</head>
<body bgcolor="pink">
	<h2 align="center">Tic Tac Toe</h2>
	<p align="center"><font face="modern" color="green" size="4">
		Player - <strong><span style="color: #0000FF;"><font size="6">X</font></span></strong> /
		Computer - <strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong></font>
	</p>

	<p align="center"><blink><font face="modern" color="red">${print}</font></blink></p>
	<br>
	<table align="center">
	<tr>
	<td>
	<c:if
					test="${print eq '** Congratulations! Player Won ! **'}">
					<div align="center">
						<marquee behavior="alternate">
							<img src="BG/winemo.png" width="200" height="200" alt="smile" /><br>
						
						</marquee>
					</div>
				</c:if> <c:if test="${print eq '-- Computer Won ! --'}">
					<div align="center">
						<marquee behavior="alternate">
							<img src="BG/loseemo.png" width="200" height="200" alt="smile" /><br>
						
						</marquee>
					</div>
				</c:if></td><td>
	<table border='1' align='center' cellpadding="10">
		<tr>
			<td><c:choose>
					<c:when test="${xlist.contains('b1')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b1')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b1">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${xlist.contains('b2')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b2')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b2">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${xlist.contains('b3')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b3')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b3">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td><c:choose>
					<c:when test="${xlist.contains('b4')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b4')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b4">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${xlist.contains('b5')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b5')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b5">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${xlist.contains('b6')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b6')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b6">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td><c:choose>
					<c:when test="${xlist.contains('b7')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b7')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b7">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${xlist.contains('b8')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b8')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b8">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${xlist.contains('b9')}">
						<strong><span style="color: #0000FF;"><font size="6">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b9')}">
								<strong><span style="color: #FFFF00;"><font size="6">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${print eq byDefault }">
										<a href="guestAI.html?click=b9">__</a>
									</c:when>
									<c:otherwise>
										<span style="color: #FFFFFF;">.</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
		</tr>
	</table></td>
	<td><c:if
					test="${print eq '** Congratulations! Player Won ! **'}">
					<div align="center">
						<marquee behavior="alternate">
						
							<img src="BG/winner.gif" width="200" height="200" alt="smile" />
						</marquee>
					</div>
				</c:if> <c:if test="${print eq '-- Computer Won ! --'}">
					<div align="center">
						<marquee behavior="alternate">
						
							<img src="BG/loser.gif" width="200" height="200" alt="smile" />
						</marquee>
					</div>
				</c:if></td>
	</tr>
	</table>
	<br>
<p align="center">	<a href="newgame.html">New Game</a></p>
	<p align="center">
	<a href="http://localhost:8080/ttt/">BACK to Home Page</a></p>
</body>
</html>