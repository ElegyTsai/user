/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 28/04/2021 10:46:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL COMMENT '唯一不重复',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL COMMENT '电话号码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for public_material
-- ----------------------------
DROP TABLE IF EXISTS `public_material`;
CREATE TABLE `public_material`  (
  `pid` varchar(100) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL COMMENT '唯一不重复',
  `thumbnail_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '缩略图地址',
  `picture_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL COMMENT '图片地址',
  `category` varchar(20) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '类别',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_estonian_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
