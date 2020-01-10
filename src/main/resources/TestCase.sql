/*
 Navicat MySQL Data Transfer

 Source Server         : dockerdb
 Source Server Version : 50724
 Source Host           : localhost
 Source Database       : wx_springboot

 Target Server Version : 50724
 File Encoding         : utf-8

 Date: 01/10/2020 14:33:35 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `TestCase`
-- ----------------------------
DROP TABLE IF EXISTS `TestCase`;
CREATE TABLE `TestCase` (
  `id` int(100) NOT NULL,
  `run` tinyint(100) NOT NULL,
  `type` varchar(1000) NOT NULL,
  `url` varchar(1000) NOT NULL,
  `params` varchar(1000) DEFAULT NULL,
  `header` varchar(1000) DEFAULT NULL,
  `check` varchar(1000) DEFAULT NULL,
  `correlation` varchar(1000) DEFAULT NULL,
  `result` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `TestCase`
-- ----------------------------
BEGIN;
INSERT INTO `TestCase` VALUES ('0', '1', 'get', 'https://api.apiopen.top/searchMusic', '', '', '$.code=200;$.message=成功!', 'user=$.message;password=$.code', ''), ('1', '1', 'post', 'https://api.apiopen.top/searchMusic', 'user=${user}&password=${password}', 'user=${user};password=${password};', '', '', '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
