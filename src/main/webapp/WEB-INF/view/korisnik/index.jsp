<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pocetna</title>
<link rel="stylesheet" href="/nakitWeb/css/pocetnaStyle.css" type="text/css" />
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
		<img border="2" class="user-image" src="<c:url value="/korisnikSlika/${korisnik.korisnickoIme}"/> " height="150" width="150">
		<p class="user-description">${korisnik.getKratakOpis()}</p>
		<hr>
		<div class="meniii">
			<br />
			<a class="sidebar-active-button">Pocetna</a>
			<a	href="index2.html" class="sidebar-button">Pretraga</a>
			<a class="sidebar-button" href="<c:url value="/oglas/dodajNovi"/> ">Dodaj oglas</a>
            <a class="sidebar-button" href="<c:url value="/oglas/svi"/> ">Svi oglasi</a>
			<a href="<c:url value="/logout"/> " class="sidebar-button">Izloguj se</a>
		</div>
	</div>
	<section id="content">
		<div class="content-header">

			<h1>Naziv oglasa</h1>
			<p>Nesto...</p>
		</div>
		<div class="content" onscroll="customScroll()">
			<c:if test="${!empty oglasi }">
				<c:forEach var="oglas" items="${oglasi }">
			      		${oglas.printOglasToHTML()}
			</c:forEach>
			</c:if>	
		</div>
	</section>
	
	<!--  TO DOOOO -->
	
	<section id="content">
		<div class="content-header">

			<h1>Naziv oglasa</h1>
			<p>Nesto...</p>
		</div>
		<br>
		<div class="content" onscroll="customScroll()">
			<c:if test="${!empty ponude }">
				<c:forEach var="ponuda" items="${ponude }">
			      		Datum postavljanja ponude:${ponuda.datumVreme }<br>
			      		Pare:${ponuda.ponudaPare }<br>
			      		Tekst oglasa:${ponuda.ogla.tekst }<br>
			      		Naslov oglasa:${ponuda.ogla.naslov }<br>
			</c:forEach>
			</c:if>	
		</div>
	
</body>

</html>