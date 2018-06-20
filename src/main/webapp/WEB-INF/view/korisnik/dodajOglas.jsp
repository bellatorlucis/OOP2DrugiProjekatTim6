<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DODAJ OGLAS</title>
<link rel="stylesheet" href="/nakitWeb/css/dodajOglas.css"
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
			<a class="sidebar-button" href="<c:url value="/testSearch"/> ">Pretraga</a>
			<a class="sidebar-active-button" href="<c:url value="/oglas/dodajNovi"/> ">Dodaj oglas</a>
            <a class="sidebar-button" href="<c:url value="/oglas/svi"/> ">Svi oglasi</a>
			<a href="<c:url value="/logout"/> " class="sidebar-button">Izloguj se</a>
		</div>
	</div>
	<section id="content">
		<div class="content-header">
		
			<h1>Dodaj oglas </h1>
		</div>
		<div class="content">
			<div class="widget-boxsample-widget">
				<div class="widget-header">
					<h2>Dodaj Oglas</h2>
					<i class="fafa-cog"></i>
				</div>
<!-- 

				<div class="dodavanje_oglasa">

					<form action="/nakitWeb/oglas/dodavanjeOglasa" method="post">

						<select>
							<option>Jelena</option>
							<option>Jelena</option>
							<option>Jelena</option>
							<option>Jelena</option>
							<option>Jelena</option>
							<option>Jelena</option>
						</select>

						<p>Boja:</p>
						<input type="text" name="boja" path="nakit.boja" />
						<p>Materijal:</p>
						<input type="text" name="materijal" path="nakit.materijal" />
						<p>Slika:</p>
						<input type="file" name="file"><br>

						<p>Minimalna ponuda:</p>
						<input type="text" name="minPonuda" path="minPonuda" />
						<p>Tekst:</p>
						<input type="text" name="tekst" path="tekst" />
						<p>Naslov:</p>
						<input type="text" name="naslov" path="naslov" /><br /><br/> <input
							type="submit" name="prvi" value="DODAJ" />

					</form>
				</div>


 -->
				<div class="widget-content">
				
					<form:form action="/nakitWeb/oglas/dodajNovi" method="post" enctype="multipart/form-data" modelAttribute="oglas">
						
						Izaberi tip:<form:select path="nakit.tip" items="${tipovi }" itemValue="idTipa" itemLabel="naziv"/> <br><br>
								
						Boja:<form:input type="text" name="boja" path="nakit.boja" /><br>			
						Materijal:<form:input type="text" name="materijal" path="nakit.materijal" /><br>
						Slika:<input type="file" name="file"><br>
				
						Minimalna ponuda:<form:input type="text" name="minPonuda" path="minPonuda" /><br>
						<form:errors path="minPonuda" /><br/>			
						Tekst:<form:input type="text" name="tekst" path="tekst" /><br>
						Naslov:<form:input type="text" name="naslov" path="naslov" /><br>
		
						<br/><input type="submit" name="prvi" value="DODAJ"/>
					</form:form>

				</div>
			</div>

		</div>
	</section>
</body>

</html>