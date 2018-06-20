# OOP2 Drugi projekat Tim6

<h2>Opis aplikacije i podešavanje okruženja</h2>
Aplikacija je radjena pomoću <a href="https://spring.io/projects/spring-boot">Spring Boot</a> framework-a, sa malim izmenama.
Naime, aplikacija ne koristi embeded Tomcat server koji je ponudjen od strane SpringBoot-a već kosristi standalone WildFly server.
Aplikacija se ne pakuje kao <strong>jar</strong>, već kao <strong> war </strong>. Nije potrebno pokretati <strong>main</strong> metod klase
NakitWebTim6Application.java. Potrebno je skinuti <a href="http://wildfly.org/downloads/">JBoss Wildfly</a> server, podesiti jednog korisnika
po želji radi lakšeg podešavanja samog servera. Nakon toga treba dodati WildFly u okruzenje (IDE), ili pokrenuti standalone.sh(.exe/.bat) skriptu
pa ručno dodati web aplikaciju
<br>
<strong>Prvo je potrebno pristupiti stranici ../nakitWeb/fillData da bi se u bazi automatski popunile slike za korisnike i nakite.</strong>

<h3> Dependencies i verzije korišćene u projektu </h3>
<ul>
  <li> <a href="http://download.jboss.org/wildfly/13.0.0.Final/wildfly-13.0.0.Final.zip"> WildFly 13.0 Final </a> </li>
  <li> <a href="https://dev.mysql.com/downloads/mysql/5.6.html"> MySQL Community Server 5.6 </a> (Moguce je koristiti 5.7 ili neke starije verzije) </li>
  <li> <a href=""> JPA Projekat </a> (JPA projekat sa potrenim modelima) </li>
  <li> <a href=""> Spring Security</a> </li>
</ul>
<br>

<h4> Podešavanje  </h4>
Pre pokretanja aplikacije otići na <a href="localhost:8080/nakitWeb/fillData">/nakitWeb/fillData</a> da pi se popunili podaci u bazi. <strong>Context root aplikacije na Jboss server i na Tomcatu je (localhost)nakitWeb/ </strong>
