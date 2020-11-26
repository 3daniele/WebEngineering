-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Nov 26, 2020 alle 11:51
-- Versione del server: 10.4.11-MariaDB
-- Versione PHP: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `guidatv`
--
CREATE DATABASE IF NOT EXISTS `guidatv` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `guidatv`;

-- --------------------------------------------------------

--
-- Struttura della tabella `canale`
--

DROP TABLE IF EXISTS `canale`;
CREATE TABLE IF NOT EXISTS `canale` (
  `numero` int(7) NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  `gruppo` varchar(25) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `editore`
--

DROP TABLE IF EXISTS `editore`;
CREATE TABLE IF NOT EXISTS `editore` (
  `id_editore` int(11) NOT NULL AUTO_INCREMENT,
  `email` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id_editore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `episodio`
--

DROP TABLE IF EXISTS `episodio`;
CREATE TABLE IF NOT EXISTS `episodio` (
  `id_episodio` int(7) NOT NULL AUTO_INCREMENT,
  `numero` int(7) NOT NULL,
  `descrizione` text NOT NULL,
  `stagione` int(7) NOT NULL,
  PRIMARY KEY (`id_episodio`),
  KEY `stagione` (`stagione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `film`
--

DROP TABLE IF EXISTS `film`;
CREATE TABLE IF NOT EXISTS `film` (
  `id_film` int(7) NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  `foto` text DEFAULT NULL,
  `descrizione` text DEFAULT NULL,
  `link` text DEFAULT NULL,
  `anno` year(4) DEFAULT NULL,
  `durata` time DEFAULT NULL,
  `editore` int(11) NOT NULL,
  PRIMARY KEY (`id_film`),
  KEY `editore` (`editore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `genere`
--

DROP TABLE IF EXISTS `genere`;
CREATE TABLE IF NOT EXISTS `genere` (
  `id_genere` int(7) NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  PRIMARY KEY (`id_genere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `lgf`
--

DROP TABLE IF EXISTS `lgf`;
CREATE TABLE IF NOT EXISTS `lgf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `film` int(11) NOT NULL,
  `genere` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `film` (`film`),
  KEY `genere` (`genere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `lgs`
--

DROP TABLE IF EXISTS `lgs`;
CREATE TABLE IF NOT EXISTS `lgs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spettacolo` int(11) NOT NULL,
  `genere` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `genere` (`genere`),
  KEY `spettacolo` (`spettacolo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `programmazione`
--

DROP TABLE IF EXISTS `programmazione`;
CREATE TABLE IF NOT EXISTS `programmazione` (
  `id_palinsesto` int(7) NOT NULL AUTO_INCREMENT,
  `canale` int(7) NOT NULL,
  `spettacolo` int(7) NOT NULL,
  `data` date NOT NULL,
  `ora` time NOT NULL,
  `film` int(7) NOT NULL,
  PRIMARY KEY (`id_palinsesto`),
  KEY `canale` (`canale`),
  KEY `spettacolo` (`spettacolo`),
  KEY `film` (`film`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `salvataggiocanali`
--

DROP TABLE IF EXISTS `salvataggiocanali`;
CREATE TABLE IF NOT EXISTS `salvataggiocanali` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `canale` int(11) NOT NULL,
  `utente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `utente` (`utente`),
  KEY `canale` (`canale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `salvataggiogeneri`
--

DROP TABLE IF EXISTS `salvataggiogeneri`;
CREATE TABLE IF NOT EXISTS `salvataggiogeneri` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utente` int(11) NOT NULL,
  `genere` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `genere` (`genere`),
  KEY `utente` (`utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `salvataggioprogrammi`
--

DROP TABLE IF EXISTS `salvataggioprogrammi`;
CREATE TABLE IF NOT EXISTS `salvataggioprogrammi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utente` int(11) NOT NULL,
  `spettacolo` int(11) NOT NULL,
  `film` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `spettacolo` (`spettacolo`),
  KEY `utente` (`utente`),
  KEY `film` (`film`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `spettacolo`
--

DROP TABLE IF EXISTS `spettacolo`;
CREATE TABLE IF NOT EXISTS `spettacolo` (
  `id_spettacolo` int(7) NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  `tipo` varchar(25) NOT NULL,
  `foto` varchar(25) DEFAULT NULL,
  `descrizione` varchar(100) NOT NULL,
  `link` text NOT NULL,
  `anno` year(4) NOT NULL,
  `durata` time NOT NULL,
  `editore` int(11) NOT NULL,
  PRIMARY KEY (`id_spettacolo`),
  KEY `editore` (`editore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `stagione`
--

DROP TABLE IF EXISTS `stagione`;
CREATE TABLE IF NOT EXISTS `stagione` (
  `id_stagione` int(7) NOT NULL AUTO_INCREMENT,
  `numero` int(7) DEFAULT NULL,
  `descrizione` text DEFAULT NULL,
  `serietv` int(7) NOT NULL,
  PRIMARY KEY (`id_stagione`),
  KEY `serietv` (`serietv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

DROP TABLE IF EXISTS `utente`;
CREATE TABLE IF NOT EXISTS `utente` (
  `id_utente` int(7) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `stato` varchar(25) NOT NULL,
  PRIMARY KEY (`id_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `episodio`
--
ALTER TABLE `episodio`
  ADD CONSTRAINT `episodio_ibfk_1` FOREIGN KEY (`stagione`) REFERENCES `stagione` (`id_stagione`);

--
-- Limiti per la tabella `film`
--
ALTER TABLE `film`
  ADD CONSTRAINT `film_ibfk_1` FOREIGN KEY (`editore`) REFERENCES `editore` (`id_editore`);

--
-- Limiti per la tabella `lgf`
--
ALTER TABLE `lgf`
  ADD CONSTRAINT `lgf_ibfk_1` FOREIGN KEY (`film`) REFERENCES `film` (`id_film`),
  ADD CONSTRAINT `lgf_ibfk_2` FOREIGN KEY (`genere`) REFERENCES `genere` (`id_genere`);

--
-- Limiti per la tabella `lgs`
--
ALTER TABLE `lgs`
  ADD CONSTRAINT `lgs_ibfk_1` FOREIGN KEY (`genere`) REFERENCES `genere` (`id_genere`),
  ADD CONSTRAINT `lgs_ibfk_2` FOREIGN KEY (`spettacolo`) REFERENCES `spettacolo` (`id_spettacolo`);

--
-- Limiti per la tabella `programmazione`
--
ALTER TABLE `programmazione`
  ADD CONSTRAINT `programmazione_ibfk_2` FOREIGN KEY (`film`) REFERENCES `film` (`id_film`),
  ADD CONSTRAINT `programmazione_ibfk_4` FOREIGN KEY (`spettacolo`) REFERENCES `episodio` (`id_episodio`),
  ADD CONSTRAINT `programmazione_ibfk_5` FOREIGN KEY (`canale`) REFERENCES `canale` (`numero`);

--
-- Limiti per la tabella `salvataggiocanali`
--
ALTER TABLE `salvataggiocanali`
  ADD CONSTRAINT `salvataggiocanali_ibfk_1` FOREIGN KEY (`utente`) REFERENCES `utente` (`id_utente`),
  ADD CONSTRAINT `salvataggiocanali_ibfk_2` FOREIGN KEY (`canale`) REFERENCES `canale` (`numero`);

--
-- Limiti per la tabella `salvataggiogeneri`
--
ALTER TABLE `salvataggiogeneri`
  ADD CONSTRAINT `salvataggiogeneri_ibfk_1` FOREIGN KEY (`genere`) REFERENCES `genere` (`id_genere`),
  ADD CONSTRAINT `salvataggiogeneri_ibfk_2` FOREIGN KEY (`utente`) REFERENCES `utente` (`id_utente`);

--
-- Limiti per la tabella `salvataggioprogrammi`
--
ALTER TABLE `salvataggioprogrammi`
  ADD CONSTRAINT `salvataggioprogrammi_ibfk_1` FOREIGN KEY (`spettacolo`) REFERENCES `spettacolo` (`id_spettacolo`),
  ADD CONSTRAINT `salvataggioprogrammi_ibfk_2` FOREIGN KEY (`utente`) REFERENCES `utente` (`id_utente`),
  ADD CONSTRAINT `salvataggioprogrammi_ibfk_3` FOREIGN KEY (`film`) REFERENCES `film` (`id_film`);

--
-- Limiti per la tabella `spettacolo`
--
ALTER TABLE `spettacolo`
  ADD CONSTRAINT `spettacolo_ibfk_1` FOREIGN KEY (`editore`) REFERENCES `editore` (`id_editore`);

--
-- Limiti per la tabella `stagione`
--
ALTER TABLE `stagione`
  ADD CONSTRAINT `stagione_ibfk_1` FOREIGN KEY (`serietv`) REFERENCES `spettacolo` (`id_spettacolo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
