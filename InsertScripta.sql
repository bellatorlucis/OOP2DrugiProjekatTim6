-- Skripta za OOP2 - projekat 2 
-- PAZNJA: SKRIPTA DROPUJE TABELE IZ oop2Web baze!
CREATE DATABASE IF NOT EXISTS oop2Web;
USE oop2Web;

/*-------------------------------------------------------------- */;
/*------------------------DROP TABLES--------------------------- */;
/*-------------------------------------------------------------- */;
DROP TABLE IF EXISTS `komentar`;
DROP TABLE IF EXISTS `ponuda`;
DROP TABLE IF EXISTS `ogla`;
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
  `id_uloge` int(11) NOT NULL AUTO_INCREMENT,
  `ime_uloge` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_uloge`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tip
CREATE  TABLE IF NOT EXISTS `tip` (
  `id_tipa` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_tipa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Korisnik
CREATE TABLE IF NOT EXISTS `korisnik` (
  `id_korisnika` int(11) NOT NULL AUTO_INCREMENT,
  `Uloga_id_uloge` int(11) NOT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `slika` mediumblob DEFAULT NULL,
  `korisnicko_ime` varchar(100) UNIQUE DEFAULT NULL,
  `lozinka` varchar(100) DEFAULT NULL,
  `kratak_opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_korisnika`),
  KEY `Korisnik_Uloga_FK` (`Uloga_id_uloge`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Nakit
CREATE TABLE IF NOT EXISTS `nakit` (
  `id_nakita` int(11) NOT NULL AUTO_INCREMENT,
  `Tip_id_tipa` int(11) NOT NULL,
  `slika_nakita` mediumblob DEFAULT NULL,
  `materijal` varchar(20) DEFAULT NULL,
  `boja` varchar(100) DEFAULT NULL,
   PRIMARY KEY (`id_nakita`),
   KEY `Nakit_Tip_FK` (`Tip_id_tipa`)        
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Ogla
CREATE TABLE IF NOT EXISTS `ogla` (
  `id_ogla` int(11) NOT NULL AUTO_INCREMENT,
  `Korisnik_id_korisnika` int(11) NOT NULL,
  `Nakit_id_nakita` int(11) NOT NULL,
  `naslov` varchar(100) DEFAULT NULL,
  `tekst` varchar(1000) DEFAULT NULL,
  `min_ponuda` double DEFAULT NULL,
  `aktivan` int(1) DEFAULT NULL,
  `datum_vreme` datetime DEFAULT NULL,
  PRIMARY KEY (`id_ogla`),
  UNIQUE KEY `Ogla__IDX` (`Nakit_id_nakita`),
  KEY `Ogla_Korisnik_FK` (`Korisnik_id_korisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Ponuda
CREATE TABLE IF NOT EXISTS `ponuda` (
  `id_ponude` int(11) NOT NULL AUTO_INCREMENT,
  `Ogla_id_ogla` int(11) NOT NULL,
  `Korisnik_id_korisnika` int(11) NOT NULL,
  `ponuda_pare` double DEFAULT NULL,
  `datum_vreme` datetime DEFAULT NULL,
  PRIMARY KEY (`id_ponude`),
  KEY `Ponuda_Ogla_FK` (`Ogla_id_ogla`),
  KEY `Ponuda_Korisnik_FK` (`Korisnik_id_korisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Komentar
CREATE TABLE IF NOT EXISTS `komentar` (
  `id_komentara` int(11) NOT NULL AUTO_INCREMENT,
   `Korisnik_id_korisnika` int(11) NOT NULL,
  `Ogla_id_ogla` int(11) NOT NULL,
  `sadrzaj` varchar(1000) DEFAULT NULL,
  `datum_vreme` datetime DEFAULT NULL,
  `komentar_roditelj_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_komentara`),
  KEY `Komentar_Ogla_FK` (`Ogla_id_ogla`),
  KEY `Komentar_Korisnik_FK` (`Korisnik_id_korisnika`)               
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
  ADD CONSTRAINT `Korisnik_Uloga_FK` FOREIGN KEY (`Uloga_id_uloge`) REFERENCES `uloga` (`id_uloge`);

--
-- Constraints for table `nakit`
--
ALTER TABLE `nakit`
  ADD CONSTRAINT `Nakit_Tip_FK` FOREIGN KEY (`Tip_id_tipa`) REFERENCES `tip` (`id_tipa`);

--
-- Constraints for table `ogla`
--
ALTER TABLE `ogla`
  ADD CONSTRAINT `Ogla_Korisnik_FK` FOREIGN KEY (`Korisnik_id_korisnika`) REFERENCES `korisnik` (`id_korisnika`),
  ADD CONSTRAINT `Ogla_Nakit_FK` FOREIGN KEY (`Nakit_id_nakita`) REFERENCES `nakit` (`id_nakita`);

--
-- Constraints for table `ponuda`
--

ALTER TABLE `ponuda`
  ADD CONSTRAINT `Ponuda_Ogla_FK` FOREIGN KEY (`Ogla_id_ogla`) REFERENCES `ogla` (`id_ogla`),
  ADD CONSTRAINT `Ponuda_Korisnik_FK` FOREIGN KEY (`Korisnik_id_korisnika`) REFERENCES `korisnik` (`id_korisnika`);


--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `Komentar_Korisnik_FK` FOREIGN KEY (`Korisnik_id_korisnika`) REFERENCES `korisnik` (`id_korisnika`),
  ADD CONSTRAINT `Komentar_Ogla_FK` FOREIGN KEY (`Ogla_id_ogla`) REFERENCES `ogla` (`id_ogla`);

/*-------------------------------------------------------------- */;
/*---------------------ADD CONSTRAINTS END---------------------- */;
/*-------------------------------------------------------------- */;



/*-------------------------------------------------------------- */;
/*------------------------SEED DATA----------------------------- */;
/*-------------------------------------------------------------- */;

-- ULOGA
INSERT INTO `uloga`(`id_uloge`,`ime_uloge`)
VALUES (1,'User');

INSERT INTO `uloga`(`id_uloge`,`ime_uloge`)
VALUES (2,'Admin');
-- ULOGA END

-- TIP
INSERT INTO `tip`(`id_tipa`,`naziv`)
VALUES (1,'Bros');

INSERT INTO `tip`(`id_tipa`,`naziv`)
VALUES (2,'Ogrlica');

INSERT INTO `tip`(`id_tipa`,`naziv`)
VALUES (3,'Prsten');

INSERT INTO `tip`(`id_tipa`,`naziv`)
VALUES (4,'Narukvica');

INSERT INTO `tip`(`id_tipa`,`naziv`)
VALUES (5,'Burma');

INSERT INTO `tip`(`id_tipa`,`naziv`)
VALUES (6,'Mindjusa');

INSERT INTO `tip`(`id_tipa`,`naziv`)
VALUES (7,'Drugo');
-- TIP END

-- KORISNIK
INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(1,1,'Marko','Knez','mknez','$2a$10$myQS6qm2Nl6jYb5/v0yiluXrP0UiIJzMg/d1GEVC/HIkqbLdbG5R2','Brza hrana, teretana');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(2,1,'Nadja','Svircev','nsvircev','$2a$10$Ep7GJjv85HJAyRENgT1DCOsNckKpWiTOhoDrvqS5UchGq.JljYHry','Volim duge setnje po plazi i casopis Svet');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(3,1,'Mile','Savic','msavic','$2a$10$d5Mrl9fbxW5LMdAJKr8sgOCblrU8SGdInNJHYyx.Y4OOofrmtI7U.','Pozitivne misli pozitivna dela!');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(4,1,'Josip','Roncevic','jroncevic','$2a$10$qFSrw8gna0gqBGfcMRkcpu0ch5t5zw2THurO8i0Ay.81RPQuriDL2','Zivote lutalico, drug mi nisi bio.');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(5,1,'Natasa','Novak','nnovak','$2a$10$6UxstqM8fH.x45djIDD6YuzF.hcAaqhAhr4tssv3ZkAiXGOT4pFbq','#PRAYFORPARIS');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(6,1,'Boris','Kovac','bkovac','$2a$10$q3AD7wPTH3Km1futiqzhIe55iNxS9dsk.fAXQNCVgfoLTG3BlsG/6','Nije zivot jedna zena ;)');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(7,1,'Dunja','Juric','djuric','$2a$10$ivah5acCmA4PpW1Ezdv6XuDQULxjBegGmxECiz71ROWHccOPFEqMG','Beogradski Sindikat - Sistem te laze');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(8,1,'Filip','Ovas','fovas','$2a$10$A30RUUebldg1GZRrGTtpreTO0Bib91Y26UN.r3HeDhSopDowyIHsa','Ja volim Niksicko tamno');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(9,1,'Sale','Zagorac','szagorac','$2a$10$/cW97Rg2Is9IUvCIHqqVSuXAzjdQnG6wQfotHqL6AEV/upUPgRehS','Ko to tamo peva zasluzuje oskara');

INSERT INTO `korisnik`(`id_korisnika`,`Uloga_id_uloge`,`ime`,`prezime`,`korisnicko_ime`,`lozinka`,`kratak_opis`)
VALUES(10,1,'Vanja','Vulic','vvulic','$2a$10$x6PISja5jA2jsG7MbWwJ5OUyBGHOALi0w9onpyxa5oHHkmJK/V6N6','D A N K M E M E S A N D W A P O R W A V E');
-- KORISNIK END

-- NAKIT
INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(1,1,'Aluminijum','Smedja');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(2,1,'Srebro','Srebrna');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(3,2,'Zlato','Zlatna');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(4,2,'Aluminijum','Smedja');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(5,3,'Srebro','Srebrna');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(6,3,'Zlato','Zlatna');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(7,4,'Aluminijum','Smedja');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(8,4,'Srebro','Srebrna');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(9,5,'Zlato','Zlatna');

INSERT INTO `nakit`(`id_nakita` ,`Tip_id_tipa`,`materijal`,`boja`)
VALUES(10,7,'Aluminijum','Smedja');
-- NAKIT


-- OGLAS
INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (1,1,1,'Bros jeftino!!!','Bros jeftino!!!',1100,1,'2018-06-09 11:45');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (2,2,2,'Prelep vintage bros must have za sezonu 18/19','Prelep vintage bros must have za sezonu 18/19',2300,1,'2018-06-05 16:20');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (3,3,3,'Zlato nasledjeno od dede!','Zlato nasledjeno od dede!',5200,1,'2018-06-05 06:00');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (4,4,4,'Minimalistic ogrlica fashion yes yes','Minimalistic ogrlica fashion yes yes',3306,1,'2018-06-12 10:45');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (5,5,5,'Verenicki prsten','Verenicki prsten',4000,1,'2018-06-08 17:22');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (6,6,6,'Bakin prsten (sa titanika ga ukrala)','Bakin prsten (sa titanika ga ukrala)',12000,1,'2018-06-09 11:11');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (7,7,7,'Prelepa narukvica','Prelepa narukvica',7000,1,'2018-06-12 12:12');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (8,8,8,'Narukvica magicna daje letenje i +2 na agility','Narukvica magicna daje letenje i +2 na agility',6660,1,'2018-06-13 16:54');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (9,9,9,'Burma, jedna nosena, razvod bio brz','Burma, jedna nosena, razvod bio brz',1000,1,'2018-06-06 14:54');

INSERT INTO `ogla`(`id_ogla`,`Korisnik_id_korisnika`,`Nakit_id_nakita`,`naslov`,`tekst`,`min_ponuda`, `aktivan`,`datum_vreme`)
VALUES (10,10,10 ,'Kruna kralja Henrija V','Kruna kralja Henrija V',42000,1,'2018-06-07 18:54');
-- OGLAS

-- PONUDA
INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(1,1,2, 1250,'2018-06-09 13:45');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(2,1,3, 1251,'2018-06-09 13:50');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(3,1,2, 1351,'2018-06-09 13:55');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(4,2,1,2420,'2018-06-05 16:20');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(5,3,4, 5600,'2018-06-05 10:00');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(6,4,5, 3500,'2018-06-12 18:45');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(7,5,4, 4200, '2018-06-08 17:22');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(8,6,7, 15000,'2018-06-09 11:11');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(9,7,6, 7300, '2018-06-12 12:12');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(10,5,6, 4700,'2018-06-12 18:22');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(11,5,7, 5000, '2018-06-12 18:30');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(12,8,1, 6666,'2018-06-13 16:54');

INSERT INTO `ponuda`(`id_ponude`,`Ogla_id_ogla`,`Korisnik_id_korisnika`,`ponuda_pare`,`datum_vreme`)
VALUES(13,10,9, 60000,'2018-06-07 18:54');
-- PONUDA

-- KOMENTAR
INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(1,2, 1,'1100din nije jeftino, to je prosecna dnevnica.','2018-06-09 13:42:05',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(2,3, 2,'Bas je shik!','2018-06-09 16:42:01',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(3,4, 3,'ZNA DEDA!','2018-06-13 16:48:02',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(4,5, 4,'Danas je sreda, sredom valja kupovati nakit!','2018-06-13 09:45',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(5,6, 5,'Bas mi se dopada!','2018-06-11 14:32',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(6,7, 6,'Ici ce super uz moj dizajner sat!','2018-06-10 5:12',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(7,8, 7,'Mnogo je lepa, opasno je smekam.','2018-06-11 15:32',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(8,9, 8,'Imam neku sto daje +3 intelligence, da li mi je ova boost u dps-u? Ja sam battlemage klasa, human rasa, level 12.','2018-06-12 18:26',0);
 
INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(9,1, 10,'Jeri to onaj sto je osnovao Protestante?','2018-06-08 14:17',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(10,1, 1,'Za ovakav komad jeste!','2018-06-10 12:12',1);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(11,2, 1,'Pa bas i ne znam...','2018-06-10 12:45',10);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(12,4, 2,'Veoma je fensi. MI LAJKI <3','2018-06-11 13:45',0);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(13,3, 3,'Zna zna!','2018-06-12 14:48',3);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(14,6, 3,'Pa ajde kao','2018-06-12 13:22',3);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(15,5, 5,'Perfekt je.','2018-06-11 14:44',5);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(16,6, 6,'Koje marke je sat? Ja imam apple watch u syncu je sa mojom jabukom.','2018-06-12 15:12',6);

INSERT INTO `komentar`(`id_komentara`,`Korisnik_id_korisnika`,`Ogla_id_ogla`,`sadrzaj`,`datum_vreme`,`komentar_roditelj_id`)
VALUES(17,2, 7,'Smekam te, smekam, posle svirke cekam!','2018-06-11 16:00',7);
-- KOMENTAR

/*-------------------------------------------------------------- */;
/*------------------------SEED DATA END------------------------- */;
/*-------------------------------------------------------------- */;