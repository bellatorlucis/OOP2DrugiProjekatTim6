<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
<head th:include="layout :: head(title=~{::title},links=~{})">
  <title>Dobrodošli!</title>
  <link rel="stylesheet" href="/nakitWeb/css/login.css">
</head>

<body>
  <img class="login-img" src="/nakitWeb/images/logoFullSize.png" height="200">
  <div class="login">
    <h2 class="login-header">Dobrodošli</h2>
    <c:if test="${param.error}">
      <div>
        <p>Pogresno korisnicko ime i/ili lozinka</p>
      </div>

    </c:if>
    <form class="login-container" name="f" action="<c:url value="/login"/>" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <p>
        <input type="text" id="korisnickoIme" name="korisnickoIme" placeholder="Korisničko ime">
      </p>
      <p>
        <input type="password" id="lozinka" name="lozinka" placeholder="Lozinka">
      </p>
      <p>
        <input type="submit" value="Uloguj se">

      </p>
    </form>
    <div class="RegistracijaLink">
      Nemaš nalog? <a href="/nakitWeb/register">Registruj se!</a>
    </div>
  </div>
</body>

</html>