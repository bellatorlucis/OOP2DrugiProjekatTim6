<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalji Oglasa</title>
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
		<img border="2" class="user-image" src="<c:url value="/korisnikSlika/${korisnik.korisnickoIme}"/>" height="150" width="150">
		<p class="user-description">${korisnik.kratakOpis}</p>
		<hr>
		<div class="meniii">
			<br />
			<a class="sidebar-button" href="<c:url value="/dashboard"/> ">Pocetna</a>
			<a class="sidebar-button" href="<c:url value="/testSearch"/> ">Pretraga</a>
			<a class="sidebar-button" href="<c:url value="/oglas/dodajNovi"/> ">Dodaj oglas</a>
            <a class="sidebar-button" href="<c:url value="/oglas/svi"/> ">Svi oglasi</a>
			<a href="<c:url value="/logout"/> " class="sidebar-button">Izloguj se</a>
		</div>
	</div>
	<section id="content">
		<div class="content-header">

			<h1>${oglas.naslov }</h1>
			<c:if test="${ !empty uspesnoPrihvacenaPonuda}">
				<h2>${uspesnoPrihvacenaPonuda}</h2>
			</c:if>
		</div>
		<div class="content" onscroll="customScroll()">
			<c:if test="${!empty oglas }">
			     ${oglas.printOglasToHTML()}
			</c:if>	
		</div>
	</section>
	
	<section id="content">
		<div class="content-header">

			<h1>Ponude</h1>
			<p>Nesto...</p>
		</div>
		<br>
		<div class="content" onscroll="customScroll()">
			<c:if test="${! empty nemaPonuda}">
				${nemaPonuda}
			</c:if>
			<c:if test="${!empty ponuda }">
			      	${ponuda.printPonudaToHTML() }
				<c:if test="${korisnik.idKorisnika == oglas.korisnik.idKorisnika}">
					<c:if test="${oglas.aktivan gt 0}">
						<a href="<c:url value="/oglas/prihvatiPonudu/${oglas.idOgla}"/>"><button>Prihvati ponudu</button></a>
					</c:if>
				</c:if>
			</c:if>	
		</div>
	</section>
	
</body>
</html>