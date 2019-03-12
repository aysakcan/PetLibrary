-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                8.0.15 - MySQL Community Server - GPL
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- onlinestoredb için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `onlinestoredb` /*!40100 DEFAULT CHARACTER SET utf32 COLLATE utf32_unicode_ci */;
USE `onlinestoredb`;

-- tablo yapısı dökülüyor onlinestoredb.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Veri çıktısı seçilmemişti
-- tablo yapısı dökülüyor onlinestoredb.pet
CREATE TABLE IF NOT EXISTS `pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tur` varchar(50) CHARACTER SET utf32 COLLATE utf32_unicode_ci DEFAULT NULL,
  `cins` varchar(50) CHARACTER SET utf32 COLLATE utf32_unicode_ci DEFAULT NULL,
  `yas` int(11) DEFAULT NULL,
  `aciklama` varchar(50) CHARACTER SET utf32 COLLATE utf32_unicode_ci DEFAULT NULL,
  `isim` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  `user` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Veri çıktısı seçilmemişti
-- tablo yapısı dökülüyor onlinestoredb.users
CREATE TABLE IF NOT EXISTS `users` (
  `userid` int(11) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `urlid` int(11) DEFAULT NULL,
  `user_address1` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `user_address2` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `user_bdate` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `user_city` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `user_country` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `user_email` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `user_first_name` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `user_last_name` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `user_password` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `user_phone` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `user_state` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `userzip` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Veri çıktısı seçilmemişti
-- tablo yapısı dökülüyor onlinestoredb.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL,
  `roles` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Veri çıktısı seçilmemişti
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
