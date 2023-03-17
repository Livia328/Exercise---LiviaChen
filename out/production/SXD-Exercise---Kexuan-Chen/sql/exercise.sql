/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : exercise

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 17/03/2023 15:22:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for constraint
-- ----------------------------
DROP TABLE IF EXISTS `constraint`;
CREATE TABLE `constraint` (
  `id` int NOT NULL AUTO_INCREMENT,
  `x1Coefficient` double(255,3) DEFAULT NULL,
  `x2Coefficient` double(255,3) DEFAULT NULL,
  `constantValue` double(255,3) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for LPObject
-- ----------------------------
DROP TABLE IF EXISTS `LPObject`;
CREATE TABLE `LPObject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `x1Coefficient` varchar(255) DEFAULT NULL,
  `x2Coefficient` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `id` int NOT NULL AUTO_INCREMENT,
  `constraintCompose` varchar(255) DEFAULT NULL,
  `x` double(255,3) DEFAULT NULL,
  `y` double(255,3) DEFAULT NULL,
  `op` double(255,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
