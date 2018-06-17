<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring"%>
<html lang="en">

<head>
<head th:include="layout :: head(title=~{::title},links=~{})">
  <title>Dobrodošli!</title>
  <link rel="stylesheet" href="/nakitWeb/css/login.css">
</head>

<body>
	<input type="text" name="_csrf" width="0">
  <img class="login-img" src="/nakitWeb/images/logoFullSize.png" height="200">
  <div class="login">
    <h2 class="login-header">Dobrodošli ${k.ime}  ${k.prezime}</h2>
     <spring:form class="login-container" name="f" action="/nakitWeb/login" method="post">
      <p>
        <input type="text" id="username" placeholder="Korisničko ime">
      </p>
      <p>
        <input type="password" id="password" placeholder="Lozinka">
      </p>
      <p>
        <input type="submit" value="Uloguj se">
      </p>
      <div class="RegistracijaLink">
          Nemaš nalog? <a href="https://www.w3schools.com">Registruj se!</a>
      </div>
    </spring:form>
  </div>
</body>

</html>