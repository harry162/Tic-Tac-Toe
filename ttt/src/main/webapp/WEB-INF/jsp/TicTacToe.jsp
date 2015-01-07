<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tic Tac Toe</title>
</head>
<body background="./BG/gamebg.jpg">
<security:authorize access="hasRole('ROLE_USER')">
	<h2>Tic Tac Toe</h2>
	<div align="right">
		Hi ${uname} .. click here to

		<c:choose>
			<c:when test="${print eq checknew }">
				<a href="loORnew.html?click=logout">LogOut</a>
			</c:when>
			<c:otherwise>
				<a href="ttt/j_spring_security_logout">LogOut</a>
			</c:otherwise>
		</c:choose>
	</div>
	<table width="100%">
		<tr>
			<td>
				<p>
					Player - <strong><span style="color: #0000FF;"><font
							size="6">X</font></span></strong> / Computer - <strong><span
						style="color: #FF0000;"><font size="6">O</font></span></strong>
				</p>

				<p>
					<blink>${print}</blink>
				</p>
						

			<table border='1' style="padding: 20px;">
					<tr>
						<td style="padding: 20px;" align="center"><c:choose>
								<c:when test="${xlist.contains('b1')}">
									<strong><span style="color: #0000FF; padding: 5px;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b1')}">
											<strong><span style="color: #FF0000; padding: 5px;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b1">___</a>
												</c:when>
												<c:otherwise>
													<span style="color: #FFFFFF; padding: 5px;">.</span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
						<td style="padding: 20px;" align="center" align="center"><c:choose>
								<c:when test="${xlist.contains('b2')}">
									<strong><span style="color: #0000FF; padding: 5px;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b2')}">
											<strong><span style="color: #FF0000; padding: 5px;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b2">___</a>
												</c:when>
												<c:otherwise>
													<span style="color: #FFFFFF;">.</span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
						<td style="padding: 20px;" align="center"><c:choose>
								<c:when test="${xlist.contains('b3')}">
									<strong><span style="color: #0000FF;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b3')}">
											<strong><span style="color: #FF0000;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b3">___</a>
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
						<td style="padding: 20px;" align="center"><c:choose>
								<c:when test="${xlist.contains('b4')}">
									<strong><span style="color: #0000FF;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b4')}">
											<strong><span style="color: #FF0000;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b4">___</a>
												</c:when>
												<c:otherwise>
													<span style="color: #FFFFFF;">.</span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
						<td style="padding: 20px;" align="center"><c:choose>
								<c:when test="${xlist.contains('b5')}">
									<strong><span style="color: #0000FF;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b5')}">
											<strong><span style="color: #FF0000;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b5">___</a>
												</c:when>
												<c:otherwise>
													<span style="color: #FFFFFF;">.</span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
						<td style="padding: 20px;" align="center"><c:choose>
								<c:when test="${xlist.contains('b6')}">
									<strong><span style="color: #0000FF;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b6')}">
											<strong><span style="color: #FF0000;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b6">___</a>
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
						<td style="padding: 20px;" align="center"><c:choose>
								<c:when test="${xlist.contains('b7')}">
									<strong><span style="color: #0000FF;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b7')}">
											<strong><span style="color: #FF0000;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b7">___</a>
												</c:when>
												<c:otherwise>
													<span style="color: #FFFFFF;">.</span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
						<td style="padding: 20px;" align="center" align="center"><c:choose>
								<c:when test="${xlist.contains('b8')}">
									<strong><span style="color: #0000FF;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b8')}">
											<strong><span style="color: #FF0000;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b8">___</a>
												</c:when>
												<c:otherwise>
													<span style="color: #FFFFFF;">.</span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
						<td style="padding: 20px;" align="center" align="center"><c:choose>
								<c:when test="${xlist.contains('b9')}">
									<strong><span style="color: #0000FF;"><font
											size="6">X</font></span></strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${olist.contains('b9')}">
											<strong><span style="color: #FF0000;"><font
													size="6">O</font></span></strong>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${print eq byDefault }">
													<a href="NextStep.html?click=b9">___</a>
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
				</table>
			</td>
			<td><c:if
					test="${print eq '** Congratulations! Player Won ! **'}">
					<div align="center">
						<marquee behavior="alternate">
							<img src="BG/winemo.png" width="200" height="200" alt="smile" /><br>
							<img src="BG/winner.gif" width="200" height="200" alt="smile" />
						</marquee>
					</div>
				</c:if> <c:if test="${print eq '-- Computer Won ! --'}">
					<div align="center">
						<marquee behavior="alternate">
							<img src="BG/loseemo.png" width="200" height="200" alt="smile" /><br>
							<img src="BG/loser.gif" width="200" height="200" alt="smile" />
						</marquee>
					</div>
				</c:if></td>
		</tr>
	</table>
	<br>
	<c:choose>
		<c:when test="${print eq checknew }">
			<p>
				<a href="loORnew.html?click=welcome">${uname}'s Home Page</a>
			<p>
				<a href="loORnew.html?click=new">New Game</a>
			<p>
				<a href="savegame.html">Save This Game</a>
			</p>

		</c:when>
		<c:otherwise>
			<a href="welcomepage.html">${uname}'s Home Page</a>
		</c:otherwise>
	</c:choose>
</security:authorize>
</body>
</html>