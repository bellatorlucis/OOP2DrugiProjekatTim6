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
		<img border="2" class="user-image" src="<c:url value="/korisnikSlika/${korisnik.korisnickoIme}"/>" height="150" width="150">
		<p class="user-description">${korisnik.kratakOpis}</p>
		<hr>
		<div class="meniii">
			<br />
			<a class="sidebar-active-button" href="<c:url value="/dashboard"/> ">Pocetna</a>
			<a class="sidebar-button" href="<c:url value="/testSearch"/> ">Pretraga</a>
			<a class="sidebar-button" href="<c:url value="/oglas/dodajNovi"/> ">Dodaj oglas</a>
            <a class="sidebar-button" href="<c:url value="/oglas/svi"/> ">Svi oglasi</a>
			<a href="<c:url value="/logout"/> " class="sidebar-button">Izloguj se</a>
		</div>
	</div>
	<section id="content">
		<div class="content-header">

			<h1>Oglasna tabla</h1>
			<h2>Oglasi</h2>
		</div>
		<div class="content" onscroll="customScroll()">
			<c:if test="${!empty oglasi }">
				<c:forEach var="oglas" items="${oglasi }">
			      	${oglas.printOglasToHTML()}
			</c:forEach>
			</c:if>	
		</div>
		<div class="content-header">

			<h1>Ponude</h1>
		</div>
		<br>
		<div class="content" onscroll="customScroll()">
			<c:if test="${!empty ponude }">
				<c:forEach var="ponuda" items="${ponude }">
			      	${ponuda.printPonudaToHTML() }
			</c:forEach>
			</c:if>	
		</div>
	</section>
	
</body>

</html>