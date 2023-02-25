/*
 Navicat MySQL Data Transfer

 Source Server         : root1
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : mapper

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 26/02/2023 01:07:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL COMMENT '主键',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'aa', '12345', 'bb', 12, 'man', 'china location', '2023-02-21');
INSERT INTO `user` VALUES (2, 'bb', '12345', 'cc', 13, 'man', 'china location', '2023-02-21');
INSERT INTO `user` VALUES (3, 'dd', '12345', 'ee', 14, 'man', 'china location', '2023-02-21');
INSERT INTO `user` VALUES (4, 'ff', '12345', 'gg', 15, 'man', 'china location', '2023-02-21');
INSERT INTO `user` VALUES (5, 'hh', '12345', 'ii', 16, 'man', 'china location', '2023-02-21');
INSERT INTO `user` VALUES (6, 'jj', '12345', 'kk', 17, 'man', 'china location', '2023-02-21');

SET FOREIGN_KEY_CHECKS = 1;
