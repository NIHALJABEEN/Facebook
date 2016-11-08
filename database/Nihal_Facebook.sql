-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Nov 08, 2016 at 06:54 AM
-- Server version: 5.5.52-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Nihal_Facebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `CheckIn`
--

CREATE TABLE IF NOT EXISTS `CheckIn` (
  `pk_int_checkid` int(11) NOT NULL AUTO_INCREMENT,
  `Address` varchar(250) NOT NULL,
  `Status` varchar(11) NOT NULL,
  PRIMARY KEY (`pk_int_checkid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `CheckIn`
--

INSERT INTO `CheckIn` (`pk_int_checkid`, `Address`, `Status`) VALUES
(1, 'abc', '0'),
(2, 'abc', 'haai'),
(3, 'Moozhikkal', 'njn'),
(4, 'Vellimadukunnu East, Vellimadukunnu\nKozhikode, Kerala\n', ''),
(5, 'Vellimadukunnu East, Vellimadukunnu\nKozhikode, Kerala\n', 'Hdgsigs'),
(6, 'Kerala 673012\n', ''),
(7, 'Vellimadukunnu East, Vellimadukunnu\nKozhikode, Kerala\n', ''),
(8, 'Moozhikkal\nKozhikode, Kerala\n', 'This my swe'),
(9, 'Moozhikkal\nKozhikode, Kerala\n', 'This is my '),
(10, 'Kozhikode, Kerala\n', ''),
(11, 'Kozhikode, Kerala\n', ''),
(12, 'Kozhikode, Kerala\n', ''),
(13, 'Kozhikode, Kerala\n', ''),
(14, 'Kozhikode, Kerala\n', 'hhgv'),
(15, 'Poovangal\nKozhikode, Kerala 673014\n', ''),
(16, 'Kozhikode, Kerala\n', ''),
(17, 'Kozhikode, Kerala\n', ''),
(18, 'Kozhikode, Kerala\n', ''),
(19, 'Kozhikode, Kerala\n', ''),
(20, 'Kozhikode, Kerala\n', ''),
(21, 'Kozhikode, Kerala\n', ''),
(22, 'Kozhikode, Kerala\n', ''),
(23, 'Kozhikode, Kerala\n', ''),
(24, 'Kozhikode, Kerala\n', ''),
(25, 'Kozhikode, Kerala\n', ''),
(26, 'Kozhikode, Kerala\n', ''),
(27, 'Kozhikode, Kerala\n', ''),
(28, 'Kozhikode, Kerala\n', ''),
(29, 'Kozhikode, Kerala\n', ''),
(30, 'Kozhikode, Kerala\n', 'fvjkk'),
(31, 'Pantheeramkavu, Kerala\n', ''),
(32, 'Kozhikode, Kerala\n', ''),
(33, 'Kozhikode, Kerala\n', '');

-- --------------------------------------------------------

--
-- Table structure for table `Reg`
--

CREATE TABLE IF NOT EXISTS `Reg` (
  `pk_int_reg` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `sur_name` varchar(20) NOT NULL,
  `phn_or_email_addrs` varchar(35) NOT NULL,
  `gender` varchar(35) NOT NULL,
  `Day` int(11) NOT NULL,
  `Month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `password` varchar(35) NOT NULL,
  PRIMARY KEY (`pk_int_reg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Registration`
--

CREATE TABLE IF NOT EXISTS `Registration` (
  `pk_int_reg` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `sur_name` varchar(20) NOT NULL,
  `phn_or_email_addrs` varchar(35) NOT NULL,
  `gender` varchar(35) NOT NULL,
  `Day` int(11) NOT NULL,
  `Month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `password` varchar(35) NOT NULL,
  PRIMARY KEY (`pk_int_reg`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `Registration`
--

INSERT INTO `Registration` (`pk_int_reg`, `first_name`, `sur_name`, `phn_or_email_addrs`, `gender`, `Day`, `Month`, `year`, `password`) VALUES
(4, 'rauf', 'abdul', 'abdul@gmail.com', 'Male', 0, 0, 0, 'abdulrauf'),
(15, 'Nhl', 'jbn', 'nhl@gmail. com', 'Male', 46, 86, 45, 'nhl'),
(21, 'Nihal', 'Jabeen', 'nihaljbn@gmail.com', 'Male', 24, 9, 1994, 'jbn');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
