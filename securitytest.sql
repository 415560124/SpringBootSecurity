/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : securitytest

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 29/12/2019 17:34:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES (1, '管理员列表查询', '/admin/welcome');
INSERT INTO `t_function` VALUES (2, '管理员信息', '/admin/details');
INSERT INTO `t_function` VALUES (3, '用户列表查询', '/user/welcome');
INSERT INTO `t_function` VALUES (4, '用户信息', '/user/details');
INSERT INTO `t_function` VALUES (5, '商品列表查询', '/item/welcome');
INSERT INTO `t_function` VALUES (6, '商品展示', '/item/details');
INSERT INTO `t_function` VALUES (7, '商品删除', '/item/delete');
INSERT INTO `t_function` VALUES (8, '商品分类列表查询', '/item/classList');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 'userList');
INSERT INTO `t_menu` VALUES (2, 'userShow');
INSERT INTO `t_menu` VALUES (3, 'adminList');
INSERT INTO `t_menu` VALUES (4, 'adminShow');
INSERT INTO `t_menu` VALUES (5, 'itemList');
INSERT INTO `t_menu` VALUES (6, 'itemShow');
INSERT INTO `t_menu` VALUES (7, 'itemDelete');
INSERT INTO `t_menu` VALUES (8, 'userChange');

-- ----------------------------
-- Table structure for t_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_function`;
CREATE TABLE `t_menu_function`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NULL DEFAULT NULL,
  `function_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu_function
-- ----------------------------
INSERT INTO `t_menu_function` VALUES (1, 1, 3);
INSERT INTO `t_menu_function` VALUES (2, 2, 4);
INSERT INTO `t_menu_function` VALUES (3, 3, 1);
INSERT INTO `t_menu_function` VALUES (4, 4, 2);
INSERT INTO `t_menu_function` VALUES (5, 5, 5);
INSERT INTO `t_menu_function` VALUES (6, 6, 6);
INSERT INTO `t_menu_function` VALUES (7, 7, 7);
INSERT INTO `t_menu_function` VALUES (8, 5, 8);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `note` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ROLE_ADMIN', '管理员');
INSERT INTO `t_role` VALUES (2, 'ROLE_USER', '普通用户');
INSERT INTO `t_role` VALUES (3, 'ROLE_TOURIST', '游客');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1, 1);
INSERT INTO `t_role_menu` VALUES (2, 1, 2);
INSERT INTO `t_role_menu` VALUES (3, 1, 3);
INSERT INTO `t_role_menu` VALUES (4, 1, 4);
INSERT INTO `t_role_menu` VALUES (5, 1, 5);
INSERT INTO `t_role_menu` VALUES (6, 1, 6);
INSERT INTO `t_role_menu` VALUES (7, 1, 7);
INSERT INTO `t_role_menu` VALUES (8, 1, 8);
INSERT INTO `t_role_menu` VALUES (9, 2, 2);
INSERT INTO `t_role_menu` VALUES (10, 2, 5);
INSERT INTO `t_role_menu` VALUES (11, 2, 6);
INSERT INTO `t_role_menu` VALUES (12, 2, 8);
INSERT INTO `t_role_menu` VALUES (13, 3, 5);
INSERT INTO `t_role_menu` VALUES (14, 3, 6);
INSERT INTO `t_role_menu` VALUES (15, 3, 2);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `available` int(1) NULL DEFAULT 1 COMMENT '是否可用， 1表示可用，0表示不可用',
  `note` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, '管理员');
INSERT INTO `t_user` VALUES (2, 'user', 'ee11cbb19052e40b07aac0ca060c23ee', 1, '用户');
INSERT INTO `t_user` VALUES (3, 'tourist', '9c0763361a96027a33f2f28667033686', 1, '游客');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `role_id` int(12) NOT NULL,
  `user_id` int(12) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_id`(`role_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 2, 1);
INSERT INTO `t_user_role` VALUES (3, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
