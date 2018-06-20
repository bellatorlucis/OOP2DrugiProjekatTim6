<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Registracija</title>
    <link rel="stylesheet" href="/nakitWeb/css/login.css">
</head>

<body>
    <c:if test="${ usernameExists}">
        <h1>Korisnicko ime vec postoji</h1>
    </c:if>
    <div class="login">
        <h2 class="login-header">Registracija</h2>
       <spring:form class="login-container" name="f"  method="post" enctype="multipart/form-data" modelAttribute="korisnik">
            <div class="registration-info">
                <p>

                    <spring:input type="text" path="ime" placeholder="Ime" />
                    <spring:errors path="ime" />
                </p>
                <p>
                    <spring:input path="prezime" placeholder="Prezime" />
                    <spring:errors path="prezime" />

                </p>
                <p>
                    <spring:input type="text" path="korisnickoIme" placeholder="Korisnicko ime" />
                    <spring:errors path="korisnickoIme" />
                </p>
                <p>
                    <spring:input type="password" path="lozinka" placeholder="Lozinka" />
                    <spring:errors path="lozinka" />
                </p>
                <p>
                    <input type="password" placeholder="Ponovljena lozinka" name="lozinka2">
                </p>
            </div>
            <div class="extended-registration-info">
                <p>
                    <spring:input type="text" path="kratakOpis" placeholder="Kratak opis" />
                    <spring:errors path="kratakOpis" />
                </p>
                <p>
                    <input type="file" placeholder="Opis" value="Izaberi sliku..." name="file">
                </p>            
            </div>
            <input type="submit" value="Registruj se">
            <div class="j">
                Već imaš nalog?
                <a href="/nakitWeb/login">Prijavi se!</a>
            </div>
        </spring:form>
    </div>
</body>

</html>