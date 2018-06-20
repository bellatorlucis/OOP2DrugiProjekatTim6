<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalji Oglasa</title>
<link rel="stylesheet" href="/nakitWeb/css/detalji.css" type="text/css" />
</head>
<body>
	<script>
		function customScroll() {
			var sWrap = document.getElementById("sWrapper");
			var y = sWrap.scrollTop;
			document.getElementById("vText").innerHTML = "Vertically: " + y
					+ "px";
			var knob = document.getElementById("sKnob");
			var att = document.createAttribute("style");
			att.value = "top:" + Math.floor(y / 5) + "px";
			knob.setAttributeNode(att);
		}
	</script>
	<header> </header>
	<div class="sidebar">
		<img border="2" class="user-image"
			src="<c:url value="/korisnikSlika/${korisnik.korisnickoIme}"/>"
			height="150" width="150">
		<p class="user-description">${korisnik.kratakOpis}</p>
		<hr>
		<div class="meniii">
			<br /> <a class="sidebar-button" href="<c:url value="/dashboard"/> ">Pocetna</a>
			<a class="sidebar-button" href="<c:url value="/testSearch"/> ">Pretraga</a>
			<a class="sidebar-button" href="<c:url value="/oglas/dodajNovi"/> ">Dodaj
				oglas</a> <a class="sidebar-button" href="<c:url value="/oglas/svi"/> ">Svi
				oglasi</a> <a href="<c:url value="/logout"/> " class="sidebar-button">Izloguj
				se</a>
		</div>
	</div>
	<section id="content" >
		<div class="content-header">

			<h1>${oglas.naslov }</h1>
		</div>
		<div class="content" >
			<c:if test="${!empty oglas }">
			     ${oglas.printOglasToHTML() }
			</c:if>
		</div>


		<div class="content" >
			<div class="widget-box sample-widget">
				<div class="widget-header">
					<h2>GSGRS</h2>
					<i class="fa fa-cog"></i>
				</div>
				<p>jjn</p>
				<br>
				<h3>Minimalna Ponuda:</h3>
				<div class="widget-content"></div>
			</div>

		</div>

	</section>

	<section id="content"  onscroll="customScroll()">
		<div class="content-header">

			<h1>Komentari</h1>
			<p></p>
		</div>
		<br>
		<div class="content" onscroll="customScroll()">
			<c:if test="${!empty komentari }">
				<c:forEach var="komentar" items="${komentari }">
					<div class="komentar" style="background-color:white;"><p>${komentar.sadrzaj }</p></div><br>
				</c:forEach>
			</c:if>
			<br>
			<form:form action="/nakitWeb/oglas/dodajKomentar/${oglas.idOgla }" method="post" modelAttribute="komentar">
				<form:textarea name="sadrzaj" path="sadrzaj" rows="5" cols="30"></form:textarea>>
				<input type="submit" value="Komentarisi">
			</form:form>
		</div>
	</section>

</body>
</html>