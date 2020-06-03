-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Creato il: Giu 03, 2020 alle 08:38
-- Versione del server: 5.7.26
-- Versione PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `GuidaTv`
--
CREATE DATABASE IF NOT EXISTS `GuidaTv` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `GuidaTv`;

-- --------------------------------------------------------

--
-- Struttura della tabella `Canale`
--

CREATE TABLE IF NOT EXISTS `Canale` (
  `numero` int(7) NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  `gruppo` varchar(25) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Episodio`
--

CREATE TABLE IF NOT EXISTS `Episodio` (
  `id_episodio` int(7) NOT NULL AUTO_INCREMENT,
  `numero` int(7) NOT NULL,
  `descrizione` text NOT NULL,
  `stagione` int(7) NOT NULL,
  PRIMARY KEY (`id_episodio`),
  KEY `stagione` (`stagione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Film`
--

CREATE TABLE IF NOT EXISTS `Film` (
  `id_film` int(7) NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  `foto` text,
  `descrizione` text,
  `link` text,
  `anno` year(4) DEFAULT NULL,
  `durata` time DEFAULT NULL,
  PRIMARY KEY (`id_film`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Genere`
--

CREATE TABLE IF NOT EXISTS `Genere` (
  `id_genere` int(7) NOT NULL AUTO_INCREMENT,
  `nomr` varchar(25) NOT NULL,
  PRIMARY KEY (`id_genere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `ListaGeneri`
--

CREATE TABLE IF NOT EXISTS `ListaGeneri` (
  `id_Ig` int(7) NOT NULL AUTO_INCREMENT,
  `genere` int(7) NOT NULL,
  `spettacolo` int(7) NOT NULL,
  PRIMARY KEY (`id_Ig`),
  KEY `genere` (`genere`),
  KEY `spettacolo` (`spettacolo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Programmazione`
--

CREATE TABLE IF NOT EXISTS `Programmazione` (
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
-- Struttura della tabella `Salvataggio`
--

CREATE TABLE IF NOT EXISTS `Salvataggio` (
  `id_salvataggio` int(7) NOT NULL AUTO_INCREMENT,
  `utente` int(7) NOT NULL,
  `entita` int(7) NOT NULL,
  `tipo` int(7) NOT NULL,
  PRIMARY KEY (`id_salvataggio`),
  KEY `utente` (`utente`),
  KEY `entita` (`entita`),
  KEY `tipo` (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Spettacolo`
--

CREATE TABLE IF NOT EXISTS `Spettacolo` (
  `id_spettacolo` int(7) NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  `tipo` varchar(25) NOT NULL,
  `foto` varchar(25) DEFAULT NULL,
  `descrizione` varchar(100) NOT NULL,
  `link` text NOT NULL,
  `anno` year(4) NOT NULL,
  `durata` time NOT NULL,
  PRIMARY KEY (`id_spettacolo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Stagione`
--

CREATE TABLE IF NOT EXISTS `Stagione` (
  `id_stagione` int(7) NOT NULL AUTO_INCREMENT,
  `numero` int(7) DEFAULT NULL,
  `descrizione` text,
  `serietv` int(7) NOT NULL,
  PRIMARY KEY (`id_stagione`),
  KEY `serietv` (`serietv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Utente`
--

CREATE TABLE IF NOT EXISTS `Utente` (
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
-- Limiti per la tabella `Episodio`
--
ALTER TABLE `Episodio`
  ADD CONSTRAINT `episodio_ibfk_1` FOREIGN KEY (`stagione`) REFERENCES `Stagione` (`id_stagione`);

--
-- Limiti per la tabella `ListaGeneri`
--
ALTER TABLE `ListaGeneri`
  ADD CONSTRAINT `listageneri_ibfk_1` FOREIGN KEY (`genere`) REFERENCES `Genere` (`id_genere`),
  ADD CONSTRAINT `listageneri_ibfk_2` FOREIGN KEY (`spettacolo`) REFERENCES `Spettacolo` (`id_spettacolo`);

--
-- Limiti per la tabella `Programmazione`
--
ALTER TABLE `Programmazione`
  ADD CONSTRAINT `programmazione_ibfk_2` FOREIGN KEY (`film`) REFERENCES `Film` (`id_film`),
  ADD CONSTRAINT `programmazione_ibfk_4` FOREIGN KEY (`spettacolo`) REFERENCES `Episodio` (`id_episodio`),
  ADD CONSTRAINT `programmazione_ibfk_5` FOREIGN KEY (`canale`) REFERENCES `Canale` (`numero`);

--
-- Limiti per la tabella `Salvataggio`
--
ALTER TABLE `Salvataggio`
  ADD CONSTRAINT `salvataggio_ibfk_1` FOREIGN KEY (`entita`) REFERENCES `Canale` (`numero`),
  ADD CONSTRAINT `salvataggio_ibfk_2` FOREIGN KEY (`tipo`) REFERENCES `Spettacolo` (`id_spettacolo`),
  ADD CONSTRAINT `salvataggio_ibfk_3` FOREIGN KEY (`utente`) REFERENCES `Utente` (`id_utente`);

--
-- Limiti per la tabella `Stagione`
--
ALTER TABLE `Stagione`
  ADD CONSTRAINT `stagione_ibfk_1` FOREIGN KEY (`serietv`) REFERENCES `Spettacolo` (`id_spettacolo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
