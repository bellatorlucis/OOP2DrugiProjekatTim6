
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
    document.getElementById ("vText").innerHTML = "Vertically: " + y + "px";
  var knob = document.getElementById("sKnob");
  var att = document.createAttribute("style");
  att.value = "top:" + Math.floor(y/5) + "px";
  knob.setAttributeNode(att);
}
</script>
	<header> </header>
	<div class="sidebar">
		<img border="2" class="user-image" src="dalibor.jpg" height="150"
			width="150">
		<p class="user-description">kao Neki text tu stoji
			aaaaaaaaaaaaaaaaaaaa</p>
		<hr>
		<div>
			<input class="sidebar-active-button" type="submit" value="Pocetna">
			<input class="sidebar-button" type="submit" value="Pretraga">
			<input class="sidebar-button" type="submit" value="Dodaj oglas">
			<input class="sidebar-button" type="submit" value="Izloguj se">
		</div>
	</div>
	<section id="content" >
	<div class="content-header">

				<h1>Naziv oglasa</h1>
				<p>Lep nakit, jos lepse cene, a Jelena najlepsa</p>
			</div>
		<div class="content" onscroll="customScroll()">
			<c:if test="${!empty oglasi }">
				<c:forEach var="oglas" items="${oglasi }">
			      		${oglas.printOglasToHTML()}
			      
			</c:forEach>
			</c:if>		

	</section>
</body>

</html>