<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Detalji Oglasa</title>
    <link rel="stylesheet" href="/nakitWeb/css/detalji.css" type="text/css" />
</head>
<body>
<script>
    function customScroll() {
        var sWrap = document.getElementById("sWrapper");
        var y = sWrap.scrollTop;
        document.getElementById("vText").innerHTML = "Vertically: " + y
            + "px";
        var knob = document.getElementById("sKnob");
        var att = document.createAttribute("style");
        att.value = "top:" + Math.floor(y / 5) + "px";
        knob.setAttributeNode(att);
    }
</script>
<header> </header>
<div class="sidebar">
    <img border="2" class="user-image"
         src="<c:url value="/korisnikSlika/${korisnik.korisnickoIme}"/>"
         height="150" width="150">
    <p class="user-description">${korisnik.kratakOpis}</p>
    <hr>
    <div class="meniii">
        <br /> <a class="sidebar-button" href="<c:url value="/dashboard"/> ">Pocetna</a>
        <a class="sidebar-button" href="<c:url value="/testSearch"/> ">Pretraga</a>
        <a class="sidebar-button" href="<c:url value="/oglas/dodajNovi"/> ">Dodaj
            oglas</a> <a class="sidebar-button" href="<c:url value="/oglas/svi"/> ">Svi
        oglasi</a> <a href="<c:url value="/logout"/> " class="sidebar-button">Izloguj
        se</a>
    </div>
</div>
<section id="content" >
    <div class="content-header" style="width:100%; height:13%;">

        <h1>${oglas.naslov }</h1>
        <c:if test="${ !empty uspesnoPrihvacenaPonuda}">
            <h2>${uspesnoPrihvacenaPonuda}</h2>
        </c:if>
    </div>
    <div class="content" style="width:100%; height:70%;">
        <div class="widget-box sample-widget">

            <div class="widget-content">
                <c:if test="${!empty oglas }">
                    <table border="1" style="max-width:100%;  height:100%; text-align:center;">
                        <tr>
                            <td>Tip:</td>
                            <td>${oglas.nakit.tip}</td>
                        </tr>
                        <tr>

                            <td colspan="2"> <img src="<c:url value="/oglas/oglasSlika/${oglas.idOgla}"/>" height="150" width="150"> </td>
                        </tr>
                        <tr>
                            <td>Boja:</td>
                            <td>${oglas.nakit.boja}</td>
                        </tr>
                        <tr>
                            <td>Materijal:</td>
                            <td>${oglas.nakit.materijal}</td>
                        </tr>
                        <tr>
                            <td>Tekst</td>
                            <td>${oglas.tekst}</td>
                        </tr>
                        <tr>
                            <td colspan="2" style="background:#ff6666; color:white;">Ponuda</td>
                        </tr>

                        <c:if test="${!empty ponuda }">
                            <tr>
                                <td>Korisnik</td>
                                <td>${ponuda.korisnik.korisnickoIme}</td>
                            </tr>
                            <tr>
                                <td>Datum i vreme</td>
                                <td>${ponuda.datumVreme}</td>
                            </tr>
                            <tr>
                                <td>Trenutna najveca ponuda</td>
                                <td>${ponuda.ponudaPare}</td>
                            </tr>
                        </c:if>

                        <c:if test="${empty ponuda }">
                            <tr>
                                <td>Trenutno nema ponude za dati oglas!</td>
                            </tr>
                        </c:if>
                        <c:if test="${korisnik.idKorisnika == oglas.korisnik.idKorisnika}">
                            <c:if test="${oglas.aktivan gt 0}">
                                <tr>
                                    <td> <a	href="<c:url value="/oglas/prihvatiPonudu/${oglas.idOgla}"/>"><button>Prihvati ponudu</button></a></td>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${korisnik.idKorisnika != oglas.korisnik.idKorisnika}">
                            <c:if test="${oglas.aktivan gt 0}">
                                <tr>
                                    <form:form action="/nakitWeb/oglas/dodajPonudu" modelAttribute="ponuda" method="post">

                                        <form:input path="korisnik" type="hidden" value="${korisnik.idKorisnika}"/>
                                        <form:input path="ogla" type="hidden" value="${oglas.idOgla} "/>
                                        <td><form:input path="ponudaPare" type="number" step="0.01"/></td>
                                        <td><input type="submit" value="Nova ponuda"></td>


                                    </form:form>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty ponudaDodata && !ponudaDodata}">
                            <tr><td> <p style="color:red"> Morate dodati ponudu vecu od postojece</p></td></tr>

                        </c:if>




                    </table>

                </c:if>
            </div>
        </div>
    </div>

                             



</section>

<section id="content" onscroll="customScroll()">
    <div class="content-header">

        <h1>Komentari</h1>
        <p></p>
    </div>
    <br>
    <c:if test="${!empty komentari }">
        <c:forEach var="komentar" items="${komentari }">
            <div class="komentar" style="background-color: white; border-radius: 15px">

                <p>${komentar.sadrzaj }</p>
                <p>Od : ${komentar.korisnik.korisnickoIme}</p>
                <p> Datum : ${komentar.datumVreme}</p>

            </div>
            <br>
        </c:forEach>
    </c:if>
    <br>
    <form:form action="/nakitWeb/oglas/dodajKomentar/${oglas.idOgla }"
               method="post" modelAttribute="komentar">
        <form:textarea name="sadrzaj" path="sadrzaj" rows="5" cols="30"/>>
        <input type="submit" value="Komentarisi">
    </form:form>
    </div>
</section>

</body>
</html>
