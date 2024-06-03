/*
Navicat MySQL Data Transfer

Source Server         : a
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : dream

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2024-05-12 12:37:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) DEFAULT NULL,
                              `number` int(11) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'School of Computer Science', '101');
INSERT INTO `department` VALUES ('2', 'Business School', '102');
INSERT INTO `department` VALUES ('3', 'School of Music', '103');
INSERT INTO `department` VALUES ('4', 'Medical School', '104');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `number` int(255) DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            `gender` varchar(255) DEFAULT NULL,
                            `age` int(11) DEFAULT NULL,
                            `dep_id` int(11) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('2', '10002', 'Zhang Wei', 'Male', '32', '2');
INSERT INTO `employee` VALUES ('3', '10003', 'Wang Tao', 'Male', '25', '1');
INSERT INTO `employee` VALUES ('5', '10005', 'Zhang Qiang', 'Male', '24', '3');
INSERT INTO `employee` VALUES ('7', '10007', 'Meng Yu', 'Male', '25', null);
INSERT INTO `employee` VALUES ('13', '10022', 'Zhang Jing', 'Female', '26', null);
INSERT INTO `employee` VALUES ('32', '10031', 'Wu Yuan', 'Female', '345345', '2');
INSERT INTO `employee` VALUES ('43', '12', '12', 'Male', '12', '2');

-- ----------------------------
-- Table structure for `m_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `m_role_permission`;
CREATE TABLE `m_role_permission` (
                                     `r_id` int(11) NOT NULL,
                                     `p_id` int(11) NOT NULL,
                                     UNIQUE KEY `UKauisyd1wev6xoufu43jp8nnc8` (`r_id`,`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of m_role_permission
-- ----------------------------
INSERT INTO `m_role_permission` VALUES ('1', '1');
INSERT INTO `m_role_permission` VALUES ('1', '2');
INSERT INTO `m_role_permission` VALUES ('1', '3');
INSERT INTO `m_role_permission` VALUES ('1', '4');
INSERT INTO `m_role_permission` VALUES ('1', '5');
INSERT INTO `m_role_permission` VALUES ('1', '6');
INSERT INTO `m_role_permission` VALUES ('2', '1');
INSERT INTO `m_role_permission` VALUES ('2', '2');
INSERT INTO `m_role_permission` VALUES ('2', '6');
INSERT INTO `m_role_permission` VALUES ('3', '6');

-- ----------------------------
-- Table structure for `m_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
                               `u_id` int(11) NOT NULL,
                               `r_id` int(11) NOT NULL,
                               UNIQUE KEY `UKckkgm0u5uccg6q7do1xj6bipf` (`u_id`,`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of m_user_role
-- ----------------------------
INSERT INTO `m_user_role` VALUES ('1', '1');
INSERT INTO `m_user_role` VALUES ('2', '2');
INSERT INTO `m_user_role` VALUES ('3', '3');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `code` varchar(255) DEFAULT NULL,
                              `name` varchar(255) DEFAULT NULL,
                              `resource` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'employee', 'User Management', '/emp/**');
INSERT INTO `permission` VALUES ('2', 'department', 'Department Management', '/department/**');
INSERT INTO `permission` VALUES ('3', 'sysUser', 'System User Management', '/sysUser/**');
INSERT INTO `permission` VALUES ('4', 'sysRole', 'System Role Management', '/sysRole/**');
INSERT INTO `permission` VALUES ('5', 'sysPermission', 'System Permission Management', '/sysPermission/**');
INSERT INTO `permission` VALUES ('6', 'common', 'General Management', '/index/**');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `code` varchar(255) DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', 'Super Administrator');
INSERT INTO `role` VALUES ('2', 'ROLE_MANAGER', 'System Administrator');
INSERT INTO `role` VALUES ('3', 'ROLE_EMPLOYEE', 'General Administrator');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `password` varchar(255) DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '$2a$10$YawFZD0Av3aGg5XDYYPiBeCuIFar3PpftRI/DK6Zby6UaU1rvT5Be', 'admin');
INSERT INTO `user` VALUES ('2', '$2a$10$ZdZmj4OgE8.EuVL17VcxiuiEtBBCFr0fccNOk1PrsVuqG8ir6Ktfu', 'abc');
INSERT INTO `user` VALUES ('3', '$2a$10$aOUZBkE/Gfc8s7qW3evJEePq4lFP7xcfadcpBQc3b0A9GyLw4xQ4m', 'tom');
