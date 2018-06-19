<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">

<head>
<head th:include="layout :: head(title=~{::title},links=~{})">
<title>Dobrodošli!</title>
<link rel="stylesheet" href="/nakitWeb/css/login.css">
</head>

<body>
	<img class="login-img" src="/nakitWeb/images/logoFullSize.png"
		height="200">
	<div class="login">
		<spring:form class="login-container" name="f" action="/nakitWeb/oglas/rezultatiPretrage" method="post" modelAttribute="oglasSearchDto">
		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<p>
				<input type="text" id="naslov" name="naslov" placeholder="Naslov">
				<select name="izabranTip">
						<c:forEach var="tip" items="${tipovi}">
							<option value="${tip.idTipa}"> ${tip.idTipa} ${tip.naziv}</option>
						</c:forEach>
				</select>
			</p>
			<p>
				<input type="text" id="boja" name="boja" placeholder="Boja" />
					 <input type="text" id="materijal" name="materijal" placeholder="Materijal">
			</p>
			<p>
				<input type="submit" value="Pretrazi">
			</p>
		</spring:form>
	</div>
</body>

</html>