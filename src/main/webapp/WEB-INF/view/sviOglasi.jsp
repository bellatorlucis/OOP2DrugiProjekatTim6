<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>SVII OGLASI</title>
<meta charset="UTF-8">
<body>

	<c:if test="${!empty oglasi }">
		<c:forEach var="oglas" items="${oglasi }">
			      Tekst: ${oglas.tekst }<br>
			      Minimalna ponuda: ${oglas.minPonuda }<br>
		</c:forEach>
	</c:if>
	
	
	<br>
	--------------------------------
	<br>
	<br>
	
		<c:if test="${!empty ponudeZaKorisnika }">
		<c:forEach var="ponuda" items="${ponudeZaKorisnika }">
			      Ponuda: ${ponuda.ponudaPare }<br>
			      Datum: ${ponuda.datum }<br>
			      ID:    ${ponuda.korisnik.idKorisnika }
			      <br>
			      <br>
		</c:forEach>
	</c:if>
	
	
	
</body>
</html>