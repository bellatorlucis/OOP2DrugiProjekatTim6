<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
			<br /> 
			<a href= "<c:url value="/profil/sviOglasiKorisnika "/>" class="sidebar-button"> Pocetna</a> 
			<a href= "index2.html" class="sidebar-button"> Pretraga </a> 
			<a href= "<c:url value="/oglas/dodajNovi" />"  class="sidebar-active-button"> Dodaj oglas </a>
			<a href="<c:url value="/logout"/>" class="sidebar-button"> Izloguj se </a>
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
				
					<form:form action="/nakitWeb/oglas/dodajNovi" method="post" enctype="multipart/form-data" modelAttribute="oglas">
					
						<form:select path="nakit.tip" items="${tipovi }" itemValue="idTipa" itemLabel="naziv"/> <br><br>
								
						Boja:<form:input type="text" name="boja" path="nakit.boja" /><br>			
						Materijal:<form:input type="text" name="materijal" path="nakit.materijal" /><br>
						Slika:<input type="file" name="file"><br>
				
						Minimalna ponuda:<form:input type="text" name="minPonuda" path="minPonuda" /><br>			
						Tekst:<form:input type="text" name="tekst" path="tekst" /><br>
						Naslov:<form:input type="text" name="naslov" path="naslov" /><br>
		
						<input type="submit" name="prvi" value="DODAJ"/>
					</form:form>

				</div>
			</div>

		</div>
	</section>
</body>

</html>