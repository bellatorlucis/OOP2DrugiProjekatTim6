<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pretraga</title>
<link rel="stylesheet" href="/nakitWeb/css/search.css"
	type="text/css" />
</head>

<body>
	<header> </header>
	<div class="sidebar">
		<img border="2" class="user-image" src="<c:url value="/korisnikSlika/${korisnik.korisnickoIme}"/>" height="150" width="150">
		<p class="user-description">${korisnik.kratakOpis}</p>
		<hr>
		<div class="meniii">
			<br />
			<a class="sidebar-button" href="<c:url value="/dashboard"/> ">Pocetna</a>
			<a class="sidebar-active-button" href="<c:url value="/testSearch"/> ">Pretraga</a>
			<a class="sidebar-button" href="<c:url value="/oglas/dodajNovi"/> ">Dodaj oglas</a>
            <a class="sidebar-button" href="<c:url value="/oglas/svi"/> ">Svi oglasi</a>
			<a href="<c:url value="/logout"/> " class="sidebar-button">Izloguj se</a>
		</div>
	</div>
	<section id="content">
		<div class="content-header">
		
			<h1>Dodaj oglas</h1>
		</div>
		<div class="content">
			<div class="widget-boxsample-widget">
				<div class="widget-header">
					<h2>Pretrazi oglas</h2>
					<i class="fafa-cog"></i>
				</div>

				<div class="widget-content">
									<form:form class="login-container" name="f" action="/nakitWeb/oglas/rezultatiPretrage" method="post" modelAttribute="oglasSearchDto">
						 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<p>
								<input type="text" id="naslov" name="naslov" placeholder="Naslov">
								<select name="izabranTip">
										<c:forEach var="tip" items="${tipovi}">
											<option value="${tip.idTipa}"> ${tip.idTipa} ${tip.naziv}</option>
										</c:forEach>
								</select>
							</p>
							<p>
								<input type="text" id="boja" name="boja" placeholder="Boja" />
								<input type="text" id="materijal" name="materijal" placeholder="Materijal">
							</p>
							<p>
								<input type="submit" value="Pretrazi">
							</p>
					</form:form>

				</div>
			</div>

		</div>
	</section>
</body>

</html>