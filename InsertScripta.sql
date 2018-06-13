-- Skripta za OOP2 - projekat 2 
-- PAZNJA: SKRIPTA DROPUJE TABELE IZ oop2Web baze!

CREATE DATABASE IF NOT EXISTS oop2Web;
USE oop2Web;

/*-------------------------------------------------------------- */;
/*------------------------DROP TABLES--------------------------- */;
/*-------------------------------------------------------------- */;
DROP TABLE IF EXISTS `komentar`;
DROP TABLE IF EXISTS `ponuda`;
DROP TABLE IF EXISTS `oglas`;
DROP TABLE IF EXISTS `nakit`;
DROP TABLE IF EXISTS `korisnik`;
DROP TABLE IF EXISTS `uloga`;
DROP TABLE IF EXISTS `tip`;
DROP TABLE IF EXISTS `tag`;
/*-------------------------------------------------------------- */;
/*------------------------DROP TABLES END----------------------- */;
/*-------------------------------------------------------------- */;

/*-------------------------------------------------------------- */;
/*---------------------RECREATE TABLES-------------------------- */;
/*-------------------------------------------------------------- */;
-- Uloga
CREATE TABLE IF NOT EXISTS `uloga` (
  `idUloge` int(11) NOT NULL AUTO_INCREMENT,
  `imeUloge` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idUloge`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tip
CREATE  TABLE IF NOT EXISTS `tip` (
  `idTipa` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idTipa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Korisnik
CREATE TABLE IF NOT EXISTS `korisnik` (
  `idKorisnika` int(11) NOT NULL AUTO_INCREMENT,
   `Uloga_idUloge` int(11) NOT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `slika` varchar(200) DEFAULT NULL,
  `korisnickoIme` varchar(100) DEFAULT NULL,
  `lozinka` varchar(100) DEFAULT NULL,
  `kratakOpis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idKorisnika`),
  KEY `Korisnik_Uloga_FK` (`Uloga_idUloge`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Nakit
CREATE TABLE IF NOT EXISTS `nakit` (
  `idNakita` int(11) NOT NULL AUTO_INCREMENT,
  `Tip_idTipa` int(11) NOT NULL,
  `slikaNakita` varchar(200) DEFAULT NULL,
  `materijal` varchar(20) DEFAULT NULL,
  `boja` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idNakita`),
   KEY `Nakit_Tip_FK` (`Tip_idTipa`)        
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Oglas
CREATE TABLE IF NOT EXISTS `oglas` (
  `idOglasa` int(11) NOT NULL,
  `Korisnik_idKorisnika` int(11) NOT NULL,
  `Nakit_idNakita` int(11) NOT NULL,
  `tekst` varchar(300) DEFAULT NULL,
  `minPonuda` double DEFAULT NULL,
  `aktivan` int(1) DEFAULT NULL,
  PRIMARY KEY (`idOglasa`),
  KEY `Oglas_Nakit_FK` (`Nakit_idNakita`),
  KEY `Oglas_Korisnik_FK` (`Korisnik_idKorisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Ponuda
CREATE TABLE IF NOT EXISTS `ponuda` (
  `idPonude` int(11) NOT NULL,
  `Oglas_idOglasa` int(11) NOT NULL,
  `Korisnik_idKorisnika` int(11) NOT NULL,
  `ponudaPare` double DEFAULT NULL,
  `datum` varchar(20) DEFAULT NULL,
  `vreme` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idPonude`),
  KEY `Ponuda_Oglas_FK` (`Oglas_idOglasa`),
  KEY `Ponuda_Korisnik_FK` (`Korisnik_idKorisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Komentar
CREATE TABLE IF NOT EXISTS `komentar` (
  `idKomentara` int(11) NOT NULL AUTO_INCREMENT,
   `Korisnik_idKorisnika` int(11) NOT NULL,
  `Oglas_idOglasa` int(11) NOT NULL,
  `sadrzaj` varchar(1000) DEFAULT NULL,
  `datumK` varchar(20) DEFAULT NULL,
  `vremeK` varchar(20) DEFAULT NULL,
  `komentarRoditeljId` int(11) DEFAULT NULL,
  PRIMARY KEY (`idKomentara`),
  KEY `Komentar_Oglas_FK` (`Oglas_idOglasa`),
  KEY `Komentar_Korisnik_FK` (`Korisnik_idKorisnika`)               
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*-------------------------------------------------------------- */;
/*---------------------RECREATE TABLES END---------------------- */;
/*-------------------------------------------------------------- */;


/*-------------------------------------------------------------- */;
/*---------------------ADD CONSTRAINTS-------------------------- */;
/*-------------------------------------------------------------- */;

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `Korisnik_Uloga_FK` FOREIGN KEY (`Uloga_idUloge`) REFERENCES `uloga` (`idUloge`);

--
-- Constraints for table `nakit`
--
ALTER TABLE `nakit`
  ADD CONSTRAINT `Nakit_Tip_FK` FOREIGN KEY (`Tip_idTIpa`) REFERENCES `tip` (`idTipa`);

--
-- Constraints for table `oglas`
--
ALTER TABLE `oglas`
  ADD CONSTRAINT `Oglas_Korisnik_FK` FOREIGN KEY (`Korisnik_idKorisnika`) REFERENCES `korisnik` (`idKorisnika`),
  ADD CONSTRAINT `Oglas_Nakit_FK` FOREIGN KEY (`Nakit_idNakita`) REFERENCES `nakit` (`idNakita`);

--
-- Constraints for table `ponuda`
--

ALTER TABLE `ponuda`
  ADD CONSTRAINT `Ponuda_Oglas_FK` FOREIGN KEY (`Oglas_idOglasa`) REFERENCES `oglas` (`idOglasa`),
  ADD CONSTRAINT `Ponuda_Korisnik_FK` FOREIGN KEY (`Korisnik_idKorisnika`) REFERENCES `korisnik` (`idKorisnika`);


--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `Komentar_Korisnik_FK` FOREIGN KEY (`Korisnik_idKorisnika`) REFERENCES `korisnik` (`idKorisnika`),
  ADD CONSTRAINT `Komentar_Oglas_FK` FOREIGN KEY (`Oglas_idOglasa`) REFERENCES `oglas` (`idOglasa`);

/*-------------------------------------------------------------- */;
/*---------------------ADD CONSTRAINTS END---------------------- */;
/*-------------------------------------------------------------- */;



/*-------------------------------------------------------------- */;
/*------------------------SEED DATA----------------------------- */;
/*-------------------------------------------------------------- */;

-- ULOGA
INSERT INTO `uloga`(`idUloge`,`imeUloge`)
VALUES (1,'User');

INSERT INTO `uloga`(`idUloge`,`imeUloge`)
VALUES (2,'Admin');
-- ULOGA END

-- TIP
INSERT INTO `tip`(`idTipa`,`naziv`)
VALUES (1,'Bros');

INSERT INTO `tip`(`idTipa`,`naziv`)
VALUES (2,'Ogrlica');

INSERT INTO `tip`(`idTipa`,`naziv`)
VALUES (3,'Prsten');

INSERT INTO `tip`(`idTipa`,`naziv`)
VALUES (4,'Narukvica');

INSERT INTO `tip`(`idTipa`,`naziv`)
VALUES (5,'Burma');

INSERT INTO `tip`(`idTipa`,`naziv`)
VALUES (6,'Mindjusa');

INSERT INTO `tip`(`idTipa`,`naziv`)
VALUES (7,'Drugo');
-- TIP END

-- KORISNIK
INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(1,1,'Marko','Knez','putanja/korisnici/slika1','mknez','mknez','Brza hrana, teretana');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(2,1,'Nadja','Svircev','putanja/korisnici/slika2','nsvircev','nsvircev','Volim duge setnje po plazi i casopis Svet');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(3,1,'Mile','Savic','putanja/korisnici/slika3','msavic','msavic','Pozitivne misli pozitivna dela!');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(4,1,'Josip','Roncevic','putanja/korisnici/slika4','jroncevic','jroncevic','Zivote lutalico, drug mi nisi bio.');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(5,1,'Natasa','Novak','putanja/korisnici/slika5','nnovak','nnovak','#PRAYFORPARIS');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(6,1,'Boris','Kovac','putanja/korisnici/slika6','bkovac','bkovac','Nije zivot jedna zena ;)');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(7,1,'Dunja','Juric','putanja/korisnici/slika7','djuric','djuric','Beogradski Sindikat - Sistem te laze');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(8,1,'Filip','Ovas','putanja/korisnici/slika8','fovas','fovas','Ja volim Niksicko tamno');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(9,1,'Saska','Zagorac','putanja/korisnici/slika9','szagorac','szagorac','Ko to tamo peva zasluzuje oskara');

INSERT INTO `korisnik`(`idKorisnika`,`Uloga_idUloge`,`ime`,`prezime`,`slika`,`korisnickoIme`,`lozinka`,`kratakOpis`)
VALUES(10,1,'Vanja','Vulic','putanja/korisnici/slika10','vvulic','vvulic','D A N K M E M E S A N D W A P O R W A V E');
-- KORISNIK END

-- NAKIT
INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(1,1,'putanja/nakit/slika1','Aluminijum','Smedja');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(2,1,'putanja/nakit/slika2','Srebro','Srebrna');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(3,2,'putanja/nakit/slika3','Zlato','Zlatna');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(4,2,'putanja/nakit/slika4','Aluminijum','Smedja');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(5,3,'putanja/nakit/slika5','Srebro','Srebrna');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(6,3,'putanja/nakit/slika6','Zlato','Zlatna');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(7,4,'putanja/nakit/slika7','Aluminijum','Smedja');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(8,4,'putanja/nakit/slika8','Srebro','Srebrna');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(9,5,'putanja/nakit/slika9','Zlato','Zlatna');

INSERT INTO `nakit`(`idNakita` ,`Tip_idTipa`,`slikaNakita`,`materijal`,`boja`)
VALUES(10,7,'putanja/nakit/slika10','Aluminijum','Smedja');
-- NAKIT


-- OGLAS
INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (1,1,1,'Bros jeftino!!!',1100,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (2,2,2,'Prelep vintage bros must have za sezonu 18/19',2300,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (3,3,3,'Zlato nasledjeno od dede!',5200,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (4,4,4,'Minimalistic ogrlica fashion yes yes',3306,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (5,5,5,'Verenicki prsten',4000,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (6,6,6,'Bakin prsten (sa titanika ga ukrala)',12000,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (7,7,7,'Prelepa narukvica',7000,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (8,8,8,'Narukvica magicna daje letenje i +2 na agility',6660,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (9,9,9,'Burma, jedna nosena, razvod bio brz',1000,1);

INSERT INTO `oglas`(`idOglasa`,`Korisnik_idKorisnika`,`Nakit_idNakita`,`tekst`,`minPonuda`, `aktivan`)
VALUES (10,10,10 ,'Kruna kralja Henrija V',42000,1);
-- OGLAS

-- PONUDA
INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(1,1,2, 1250,'9-6-2018','13:45');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(2,1,3, 1251,'9-6-2018','13:50');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(3,1,2, 1351,'9-6-2018','13:55');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(4,2,1,2420,'5-6-2018','16:20');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(5,3,4, 5600,'5-6-2018','10:00');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(6,4,5, 3500,'12-5-2018','18:45');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(7,5,4, 4200, '8-6-2018','7:22');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(8,6,7, 15000,'9-6-2018','11:11');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(9,7,6, 7300, '12-6-2018','12:12');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(10,5,6, 4700,'12-06-2018','18:22');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(11,5,7, 5000, '12-06-2018','18:30');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(12,8,1, 6666,'13-6-2018','6:66');

INSERT INTO `ponuda`(`idPonude`,`Oglas_idOglasa`,`Korisnik_idKorisnika`,`ponudaPare`,`datum`, `vreme`)
VALUES(13,10,9, 60000,'7-6-2018','8:54');
-- PONUDA

-- KOMENTAR
INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(1,2, 1,'1100din nije jeftino, to je prosecna dnevnica.','9-6-2018','13:42',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(2,3, 2,'Bas je shik!','9-6-2018','16:42',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(3,4, 3,'ZNA DEDA!','12-6-2018','4:48',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(4,5, 4,'Danas je sreda, sredom valja kupovati nakit!','13-6-2018','9:45',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(5,6, 5,'Bas mi se dopada!','11-6-2018','14:32',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(6,7, 6,'Ici ce super uz moj dizajner sat!','10-6-2018','5:12',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(7,8, 7,'Mnogo je lepa, opasno je smekam.','11-6-2018','15:32',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(8,9, 8,'Imam neku sto daje +3 intelligence, da li mi je ova boost u dps-u? Ja sam battlemage klasa, human rasa, level 12.','12-6-2018','18:26',0);
 
INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(9,1, 10,'Jeri to onaj sto je osnovao Protestante?','8-6-2018','14:17',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(10,1, 1,'Za ovakav komad jeste!','10-6-2018','12:!2',1);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(11,2, 1,'Pa bas i ne znam...','10-6-2018','12:45',10);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(12,4, 2,'Veoma je fensi. MI LAJKI <3','11-6-2018','13:45',0);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(13,3, 3,'Zna zna!','12-6-2018','14:48',3);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(14,6, 3,'Pa ajde kao','12-6-2018','13:22',3);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(15,5, 5,'Perfekt je.','11-6-2018','14:44',5);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(16,6, 6,'Koje marke je sat? Ja imam apple watch u syncu je sa mojom jabukom.','10-6-2018','5:12',6);

INSERT INTO `komentar`(`idKomentara`,`Korisnik_idKorisnika`,`Oglas_idOglasa`,`sadrzaj`,`datumK`,`vremeK`,`komentarRoditeljId`)
VALUES(17,2, 7,'Smekam te, smekam, posle svirke cekam!','11-6-2018','16:00',7);
-- KOMENTAR

/*-------------------------------------------------------------- */;
/*------------------------SEED DATA END------------------------- */;
/*-------------------------------------------------------------- */;