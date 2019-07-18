/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-07-18 14:51:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `detail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('1', '笔记本电脑', '5999', '这是一台高性能的笔记本电脑？？？');
INSERT INTO `items` VALUES ('2', '曲面显示屏', '1234', '这是一个曲面显示屏哦~~~');
INSERT INTO `items` VALUES ('3', '无线键鼠', '123', '这是什么？');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('21760161', '123123', '小老虎啦', '690005691@qq.com');
INSERT INTO `user` VALUES ('小爱童鞋', '123123', '小爱', '13588036274@163.com');
INSERT INTO `user` VALUES ('小老虎', '123123', '小老虎啦', 'linhu690005691@outlook.com');
