/*
 Navicat Premium Data Transfer

 Source Server         : local1
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : PersonalManagerSystem

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 25/05/2019 18:53:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_honor
-- ----------------------------
DROP TABLE IF EXISTS `t_honor`;
CREATE TABLE `t_honor` (
  `number` varchar(11) DEFAULT NULL COMMENT '教职工编号',
  `company` varchar(255) DEFAULT NULL COMMENT '单位',
  `awardname` varchar(255) DEFAULT NULL COMMENT '获奖名称',
  `awardlevel` varchar(255) DEFAULT NULL COMMENT '获奖级别',
  `awardcpy` varchar(255) DEFAULT NULL COMMENT '奖励单位',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `grade` varchar(255) DEFAULT NULL COMMENT '获奖等级',
  `honorid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '荣誉id',
  `checked` int(2) DEFAULT NULL COMMENT '审核字段',
  PRIMARY KEY (`honorid`) USING BTREE,
  KEY `number1` (`number`),
  CONSTRAINT `number1` FOREIGN KEY (`number`) REFERENCES `t_people` (`number`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_honor
-- ----------------------------
BEGIN;
INSERT INTO `t_honor` VALUES ('100002', '计算机学院', 'C语言课程优秀讲师', '院级', 'xx大学', '2017-2018年', '提名', 1, 2);
INSERT INTO `t_honor` VALUES ('100003', '体育学院', '4*100米接力第一名', '校级', 'xx大学', '2017-2018年', '第一名', 4, 2);
INSERT INTO `t_honor` VALUES ('100003', '计算机学院', 'C语言优秀讲师', '院级', 'xx大学', '2017-2018年度', '一等', 6, 2);
INSERT INTO `t_honor` VALUES ('100003', '计算机学院', '数据结构优秀讲师', '院级', 'xx大学', '2017-2018年度', '一等', 7, 2);
INSERT INTO `t_honor` VALUES ('100003', 'xx学院', '优秀创业导师', '院级', 'xx大学', NULL, NULL, 8, 2);
INSERT INTO `t_honor` VALUES ('100003', 'xx学院', '优秀辅导员', '校级', 'xx大学', NULL, NULL, 9, 2);
INSERT INTO `t_honor` VALUES ('100003', 'xx学院', '计算机网络优秀讲师', '校级', 'xx大学', '2017-2018年度', '一等', 10, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_people
-- ----------------------------
DROP TABLE IF EXISTS `t_people`;
CREATE TABLE `t_people` (
  `number` varchar(11) NOT NULL COMMENT '教职工编号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `age` varchar(10) DEFAULT NULL COMMENT '年龄',
  `department` varchar(255) DEFAULT NULL COMMENT '所属系、部',
  `position` varchar(255) DEFAULT NULL COMMENT '职务',
  `birthplace` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `identityNo` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `politicalstatus` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '政治面貌',
  `phoneNumber` varchar(12) DEFAULT NULL COMMENT '电话',
  `checked` int(2) DEFAULT NULL COMMENT '审核字段',
  PRIMARY KEY (`number`),
  KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_people
-- ----------------------------
BEGIN;
INSERT INTO `t_people` VALUES ('100001', 'admin', '男', '30', '人事部', '主管', '北京', '汉族', '340122188511115544', '党员', '17777777777', 1);
INSERT INTO `t_people` VALUES ('100002', 'Jams', '男', '25', '经管学院', '博士', '上海', '汉族', '341133199401011144', '团员', '16666666666', 1);
INSERT INTO `t_people` VALUES ('100003', 'Thomas', '男', '24', '信息学院', '讲师', '安徽合肥', '汉族', '344122199006216633', '团员', '18888888888', 1);
INSERT INTO `t_people` VALUES ('100004', 'Jack', '男', '49', '计算机科学与技术系', '系主任', '陕西西安', '汉族', '622122197008081234', '党员', '16666664444', 1);
INSERT INTO `t_people` VALUES ('100005', 'Tony', '男', '49', '公路系', '系主任', '陕西西安', '汉族', '622122197008081234', '党员', '16666663333', 1);
INSERT INTO `t_people` VALUES ('100006', 'Andy', '女', '25', '人事部', '财务员', 'nul', 'null', NULL, 'null', 'null', 1);
INSERT INTO `t_people` VALUES ('100007', 'Baey', '女', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2);
INSERT INTO `t_people` VALUES ('100008', 'Bob', '男', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2);
INSERT INTO `t_people` VALUES ('100009', 'Jone', '男', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2);
INSERT INTO `t_people` VALUES ('100010', 'Jackson', '男', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `number` varchar(11) NOT NULL COMMENT '用户序号',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`number`) USING BTREE,
  CONSTRAINT `pnumber2` FOREIGN KEY (`number`) REFERENCES `t_people` (`number`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('100001', 1);
INSERT INTO `t_role` VALUES ('100002', 2);
INSERT INTO `t_role` VALUES ('100003', 2);
INSERT INTO `t_role` VALUES ('100004', 2);
INSERT INTO `t_role` VALUES ('100005', 2);
COMMIT;

-- ----------------------------
-- Table structure for t_thesis
-- ----------------------------
DROP TABLE IF EXISTS `t_thesis`;
CREATE TABLE `t_thesis` (
  `number` varchar(11) DEFAULT NULL COMMENT '教职工编号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `company` varchar(255) DEFAULT NULL COMMENT '单位',
  `title` varchar(255) DEFAULT NULL COMMENT '文题',
  `classify` varchar(255) DEFAULT NULL COMMENT '所属分类',
  `magazine` varchar(255) DEFAULT NULL COMMENT '发表期刊',
  `thesisid` int(11) NOT NULL AUTO_INCREMENT COMMENT '论文ID',
  `checked` int(2) DEFAULT NULL COMMENT '审核字段',
  PRIMARY KEY (`thesisid`) USING BTREE,
  KEY `number3` (`number`),
  CONSTRAINT `number3` FOREIGN KEY (`number`) REFERENCES `t_people` (`number`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_thesis
-- ----------------------------
BEGIN;
INSERT INTO `t_thesis` VALUES ('100002', 'Jams', '计算机学院', '基于大数据分析的网上论坛关键词检测系统', '大数据', 'cc期刊', 19, 2);
INSERT INTO `t_thesis` VALUES ('100003', 'Thomas', '软件学院', '地图定点信息发布和搜索平台', 'web系统', 'cc期刊', 20, 2);
INSERT INTO `t_thesis` VALUES ('100003', 'Thomas', '计算机学院', '基于区块链的供应链溯源系统', 'web系统', 'cc期刊', 21, 2);
INSERT INTO `t_thesis` VALUES ('100003', 'Thomas', NULL, '视频图像中的景深信息获取', NULL, NULL, 22, 2);
INSERT INTO `t_thesis` VALUES ('100003', 'Thomas', NULL, '基于卷积神经网络的视频中的运动的人获取', NULL, NULL, 23, 2);
INSERT INTO `t_thesis` VALUES ('100003', 'Thomas', 'null', '校园投票系统设计和实现', 'null', 'null', 24, 2);
INSERT INTO `t_thesis` VALUES ('100003', 'Thomas', NULL, '校园人事管理系统设计和实现', NULL, NULL, 25, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `number` varchar(11) NOT NULL COMMENT '教职工编号',
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE,
  CONSTRAINT `pnumber4` FOREIGN KEY (`number`) REFERENCES `t_people` (`number`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('100001', '123456');
INSERT INTO `t_user` VALUES ('100002', '111111');
INSERT INTO `t_user` VALUES ('100003', '123456');
INSERT INTO `t_user` VALUES ('100004', '081234');
INSERT INTO `t_user` VALUES ('100005', '123456');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
