-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Dic 10, 2020 alle 15:51
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
-- Struttura della tabella `episodio`
--

DROP TABLE IF EXISTS `episodio`;
CREATE TABLE IF NOT EXISTS `episodio` (
  `id_episodio` int(7) NOT NULL AUTO_INCREMENT,
  `numero` int(7) NOT NULL,
  `descrizione` text NOT NULL,
  `stagione` int(7) NOT NULL,
  `durata` int(11) NOT NULL,
  PRIMARY KEY (`id_episodio`),
  KEY `stagione` (`stagione`)
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
-- Struttura della tabella `palinsesto`
--

DROP TABLE IF EXISTS `palinsesto`;
CREATE TABLE IF NOT EXISTS `palinsesto` (
  `id_palinsesto` int(7) NOT NULL AUTO_INCREMENT,
  `canale` int(7) NOT NULL,
  `data` date NOT NULL,
  `ora` time NOT NULL,
  `programma` int(11) NOT NULL,
  PRIMARY KEY (`id_palinsesto`),
  KEY `canale` (`canale`),
  KEY `spettacolo` (`programma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `programma`
--

DROP TABLE IF EXISTS `programma`;
CREATE TABLE IF NOT EXISTS `programma` (
  `id_programma` int(11) NOT NULL AUTO_INCREMENT,
  `anno` int(11) DEFAULT NULL,
  `durata` int(11) DEFAULT NULL,
  `descrizione` text DEFAULT NULL,
  `tipo` varchar(25) NOT NULL,
  `editore` int(11) NOT NULL,
  PRIMARY KEY (`id_programma`),
  KEY `editore` (`editore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `salvataggio`
--

DROP TABLE IF EXISTS `salvataggio`;
CREATE TABLE IF NOT EXISTS `salvataggio` (
  `id_salvataggio` int(11) NOT NULL AUTO_INCREMENT,
  `canale` int(11) DEFAULT NULL,
  `programma` int(11) DEFAULT NULL,
  `genere` int(11) DEFAULT NULL,
  `utente` int(11) NOT NULL,
  PRIMARY KEY (`id_salvataggio`),
  KEY `utente` (`utente`),
  KEY `genere` (`genere`),
  KEY `spettacolo` (`programma`),
  KEY `canale` (`canale`)
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
  `status` varchar(25) NOT NULL,
  `ruolo` varchar(25) NOT NULL DEFAULT 'user',
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
-- Limiti per la tabella `palinsesto`
--
ALTER TABLE `palinsesto`
  ADD CONSTRAINT `palinsesto_ibfk_5` FOREIGN KEY (`canale`) REFERENCES `canale` (`numero`),
  ADD CONSTRAINT `palinsesto_ibfk_6` FOREIGN KEY (`programma`) REFERENCES `programma` (`id_programma`);

--
-- Limiti per la tabella `programma`
--
ALTER TABLE `programma`
  ADD CONSTRAINT `programma_ibfk_1` FOREIGN KEY (`editore`) REFERENCES `utente` (`id_utente`);

--
-- Limiti per la tabella `salvataggio`
--
ALTER TABLE `salvataggio`
  ADD CONSTRAINT `salvataggio_ibfk_1` FOREIGN KEY (`utente`) REFERENCES `utente` (`id_utente`),
  ADD CONSTRAINT `salvataggio_ibfk_2` FOREIGN KEY (`genere`) REFERENCES `genere` (`id_genere`),
  ADD CONSTRAINT `salvataggio_ibfk_3` FOREIGN KEY (`programma`) REFERENCES `programma` (`id_programma`),
  ADD CONSTRAINT `salvataggio_ibfk_4` FOREIGN KEY (`canale`) REFERENCES `canale` (`numero`);

--
-- Limiti per la tabella `stagione`
--
ALTER TABLE `stagione`
  ADD CONSTRAINT `stagione_ibfk_1` FOREIGN KEY (`serietv`) REFERENCES `programma` (`id_programma`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
