<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DODAJ OGLAS</title>
<link rel="stylesheet" href="/nakitWeb/css/pocetnaStyle.css"
	type="text/css" />
</head>

<body>
	<header> </header>
	<div class="sidebar">
		<img border="2" class="user-image" src="dalibor.jpg" height="150"
			width="150">
		<p class="user-description">kao Neki text tu stoji aaaaa aaaas
			adsasdsa asd as da s sdaaaaaaaaaaa</p>
		<hr>
		<div class="meniii">
			<br /> <a class="sidebar-button">Pocetna</a> <a href="index2.html"
				class="sidebar-button">Pretraga</a> <a class="sidebar-active-button">Dodaj
				oglas</a> <a class="sidebar-button">Izloguj se</a>
		</div>
	</div>
	<section id="content">
		<div class="content-header">

			<h1>r oglasa</h1>
			<p>Lep nakit, jos lepse cene, a Jelena najlepsa</p>
		</div>
		<div class="content">
			<div class="widget-boxsample-widget">
				<div class="widget-header">
					<h2>Dodaj Oglas</h2>
					<i class="fafa-cog"></i>
				</div>

				<div class="widget-content">
					<form action="" method="get">
						TIP: <select>
							<c:if test="${!empty tipovi }">
								<c:forEach var="tip" items="${tipovi }">
			      					<option value="${ tip.getNaziv()}">${ tip.getNaziv()}</option>
							</c:forEach>
							</c:if>
						</select> <br>
						Boja: <input type="text" name="boja"> <br>
						Materijal: <input type="text" name="materijal"><br>
						Ponuda: <input type="text" name="punuda"><br>
						Naziv Oglasa: <input type="text" name="nazivOglasa"><br>
						Kratak opis: <input type="text" name="kratakOpis"><br>
						<img src="/nakitWeb/images/NoImage.png" height="200" width="200"> <br>
						<input type="submit" value="Dodaj">
					</form>

				</div>
			</div>


		</div>
	</section>
</body>

</html>