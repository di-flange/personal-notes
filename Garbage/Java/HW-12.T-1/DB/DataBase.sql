-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-0ubuntu6.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema AddressBook
--

CREATE DATABASE IF NOT EXISTS AddressBook;
USE AddressBook;

--
-- Definition of table `AddressBook`.`AddressBook`
--

DROP TABLE IF EXISTS `AddressBook`.`AddressBook`;
CREATE TABLE  `AddressBook`.`AddressBook` (
  `id` int(11) NOT NULL auto_increment,
  `name` text NOT NULL,
  `address` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `AddressBook`.`AddressBook`
--

/*!40000 ALTER TABLE `AddressBook` DISABLE KEYS */;
LOCK TABLES `AddressBook` WRITE;
INSERT INTO `AddressBook`.`AddressBook` VALUES  (19,'Andrej','Narva, Krengolmi 8'),
 (7,'Anna','Tartu, Narva mnt'),
 (22,'Sofja','Johvi, Narva mnt'),
 (21,'Anton','Sillamae, Ranna ');
UNLOCK TABLES;
/*!40000 ALTER TABLE `AddressBook` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
