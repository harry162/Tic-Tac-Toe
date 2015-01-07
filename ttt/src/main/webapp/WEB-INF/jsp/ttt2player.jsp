<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tic Tac Toe</title>





<style type="text/css">

BODY {
	background-image: url("BG/2.jpg");
	/* background-repeat: no-repeat;
	background-color: #cccccc;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover; */
}



td {
	width: 50px;
	height: 50px;
}
span{
font-size:20px;
}
</style>











<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : "step.json",
			success : function(data) {
				$("#list").empty();
				data.steps.forEach(function(step) {

					$("#list").append("<li>" + step + "</li>");
				});
			},
		});
		update();
	});
	function update() {
		$
				.ajax({
					url : "step.deferred.json",
					success : function(data) {
						$("#list").empty();
						data
								.forEach(function(step) {
									window.location
											.replace("http://localhost:8080/ttt/ttt2player.html?click=nothing");
									$("#list").append("<li>" + step + "</li>");
								});
						update();
					},
				});
	}
</script>
</head>
<body>
<security:authorize access="hasRole('ROLE_USER')">

	<h1 align="center">Welcome ${uname}</h1>
	<h2 align="center">
		${player1} - <strong><span style="color: #0000FF;"> X
		</span></strong> / ${player2} - <strong><span style="color: #FF0000;">
				O </span></strong>
	</h2>

	<h3 align="center"><span style="color: #00FFFF;">${msg}</span></h3>
	<p align="center">
		<a href="cancelgame.html">Go to Home page</a>
	<table align="center" border='1' bgcolor="FFCCFF" style="padding: 20px;">
		<tr>


			<td style="padding: 20px;" align="center"><c:choose>
					<c:when test="${xlist.contains('b1')}">
						<strong><span style="color: #0000FF; padding: 5px;">
								<font size="8">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b1')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b1">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>




			<td style="padding: 20px;" align="center"><c:choose>
					<c:when test="${xlist.contains('b2')}">
						<strong><span style="color: #0000FF; padding: 5px;">
								<font size="8">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b2')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>

										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b2">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>




			<td style="padding: 20px;" align="center"><c:choose>
					<c:when test="${xlist.contains('b3')}">
						<strong><span style="color: #0000FF; padding: 5px; ">
								<font size="8">X</font> </span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b3')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>

										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b3">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
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
						<strong><span style="color: #0000FF; padding: 5px;">
								<font size="8">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b4')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>


										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b4">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>



			<td style="padding: 20px;" align="center"><c:choose>
					<c:when test="${xlist.contains('b5')}">
						<strong><span style="color: #0000FF; padding: 5px;">
								<font size="8">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b5')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>

								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>

										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b5">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>


			<td style="padding: 20px;" align="center"><c:choose>
					<c:when test="${xlist.contains('b6')}">
						<strong><span style="color: #0000FF; padding: 5px;">
								<font size="8">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b6')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>

								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>

										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b6">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
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
						<strong><span style="color: #0000FF; padding: 5px;">
								<font size="8">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b7')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>

								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>

										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b7">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>


			<td style="padding: 20px;" align="center"><c:choose>
					<c:when test="${xlist.contains('b8')}">
						<strong><span style="color: #0000FF; padding: 5px;">
								<font size="8">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b8')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>

										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b8">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>


			<td style="padding: 20px;" align="center"><c:choose>
					<c:when test="${xlist.contains('b9')}">
						<strong><span style="color: #0000FF; padding: 5px;">
								<font size="8">X</font></span></strong>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${olist.contains('b9')}">
								<strong><span style="color: #FF0000; padding: 5px;">
										<font size="8">O</font></span></strong>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${play eq 1}">
										<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
									</c:when>
									<c:otherwise>

										<c:choose>
											<c:when test="${turn eq 'my'}">
												<a href="ttt2player.html?click=b9">___</a>
											</c:when>
											<c:otherwise>
												<strong><span style="color: #FFCCFF; padding: 5px;">____</span></strong>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose></td>

		</tr>
	</table>
	</security:authorize>
</body>
</html>