<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>SVI Korisniciii</title>
<meta charset="UTF-8">
<body>

<c:forEach var="kor" items="${korisnik }">
  Ime:    ${kor.ime }
  Prezime: ${kor.prezime }
</c:forEach>
</body>
</html>