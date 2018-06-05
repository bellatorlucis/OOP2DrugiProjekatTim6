-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 05, 2018 at 05:10 PM
-- Server version: 5.7.21-log
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stanovi`
--

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

DROP TABLE IF EXISTS `komentar`;
CREATE TABLE IF NOT EXISTS `komentar` (
  `idKomentara` int(11) NOT NULL AUTO_INCREMENT,
  `Oglas_idOglasa` int(11) NOT NULL,
  `sadrzaj` varchar(1000) DEFAULT NULL,
  `datumK` varchar(20) DEFAULT NULL,
  `vremeK` varchar(20) DEFAULT NULL,
  `ref` int(11) DEFAULT NULL,
  PRIMARY KEY (`idKomentara`),
  KEY `Komentar_Oglas_FK` (`Oglas_idOglasa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `idKorisnika` int(11) NOT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `slika` varchar(200) DEFAULT NULL,
  `korisnickoIme` varchar(100) DEFAULT NULL,
  `lozinka` varchar(100) DEFAULT NULL,
  `kratakOpis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idKorisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nakit`
--

DROP TABLE IF EXISTS `nakit`;
CREATE TABLE IF NOT EXISTS `nakit` (
  `idNakita` int(11) NOT NULL,
  `tip` int(11) DEFAULT NULL,
  `slikaNakita` varchar(200) DEFAULT NULL,
  `materijal` varchar(20) DEFAULT NULL,
  `boja` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idNakita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nakittag`
--

DROP TABLE IF EXISTS `nakittag`;
CREATE TABLE IF NOT EXISTS `nakittag` (
  `id` int(11) NOT NULL,
  `Nakit_idNakita` int(11) NOT NULL,
  `Tag_idTaga` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Nakit_idNakita`,`Tag_idTaga`),
  KEY `NakitTag_Nakit_FK` (`Nakit_idNakita`),
  KEY `NakitTag_Tag_FK` (`Tag_idTaga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `oglas`
--

DROP TABLE IF EXISTS `oglas`;
CREATE TABLE IF NOT EXISTS `oglas` (
  `idOglasa` int(11) NOT NULL,
  `Korisnik_idKorisnika` int(11) NOT NULL,
  `Nakit_idNakita` int(11) NOT NULL,
  `idKorisnika` int(11) NOT NULL,
  `tekst` varchar(300) DEFAULT NULL,
  `minPonuda` double DEFAULT NULL,
  PRIMARY KEY (`idOglasa`),
  UNIQUE KEY `Oglas__IDX` (`Nakit_idNakita`),
  KEY `Oglas_Korisnik_FK` (`Korisnik_idKorisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ponuda`
--

DROP TABLE IF EXISTS `ponuda`;
CREATE TABLE IF NOT EXISTS `ponuda` (
  `idPonude` int(11) NOT NULL,
  `Oglas_idOglasa` int(11) NOT NULL,
  `ponudaPare` double DEFAULT NULL,
  `datum` varchar(20) DEFAULT NULL,
  `vreme` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idPonude`),
  KEY `Ponuda_Oglas_FK` (`Oglas_idOglasa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `idTaga` int(11) NOT NULL,
  `naziv` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idTaga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `Komentar_Oglas_FK` FOREIGN KEY (`Oglas_idOglasa`) REFERENCES `oglas` (`idOglasa`);

--
-- Constraints for table `nakittag`
--
ALTER TABLE `nakittag`
  ADD CONSTRAINT `NakitTag_Nakit_FK` FOREIGN KEY (`Nakit_idNakita`) REFERENCES `nakit` (`idNakita`),
  ADD CONSTRAINT `NakitTag_Tag_FK` FOREIGN KEY (`Tag_idTaga`) REFERENCES `tag` (`idTaga`);

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
  ADD CONSTRAINT `Ponuda_Oglas_FK` FOREIGN KEY (`Oglas_idOglasa`) REFERENCES `oglas` (`idOglasa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
