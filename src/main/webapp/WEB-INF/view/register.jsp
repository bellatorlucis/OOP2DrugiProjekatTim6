<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring"%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Registracija</title>
    <link rel="stylesheet" href="/nakitWeb/css/login.css">
</head>

<body>
    <div class="login">
        <h2 class="login-header">Registracija</h2>
       <spring:form class="login-container" name="f" action="/nakitWeb/saveKorisnik" method="post" enctype="multipart/form-data" modelAttribute="korisnik">
            <div class="registration-info">
                <p>
                    <input type="text" placeholder="Ime" name="ime">
                </p>
                <p>
                    <input type="text" placeholder="Prezime" name="prezime">
                </p>
                <p>
                    <input type="text" placeholder="Korisničko ime" name="korisnickoIme">
                </p>
                <p>
                    <input type="password" placeholder="Lozinka" name="lozinka">
                </p>
                <p>
                    <input type="password" placeholder="Ponovljena lozinka" name="lozinka2">
                </p>
            </div>
            <div class="extended-registration-info">
                <p>
                    <input type="text" placeholder="Opis" name="kratakOpis">
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