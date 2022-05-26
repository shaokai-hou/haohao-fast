/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : haohao-fast

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 26/05/2022 18:30:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
  `dict_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `state` int DEFAULT '0' COMMENT '字典状态:(0:正常 1:禁用)',
  `sort` int DEFAULT NULL COMMENT '字典排序',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:未删除 1:已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁字段',
  `creator` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE KEY `unique index` (`dict_code`) USING BTREE COMMENT 'unique index'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`dict_id`, `dict_code`, `dict_name`, `state`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 'user_sex', '用户性别', 0, 20, NULL, 0, 0, NULL, '2022-05-26 18:08:37', NULL, '2022-05-26 18:09:32');
INSERT INTO `sys_dict` (`dict_id`, `dict_code`, `dict_name`, `state`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 'pay_way', '支付方式', 0, 10, NULL, 0, 0, NULL, '2022-05-26 18:09:14', NULL, '2022-05-26 18:09:30');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_data_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典数据ID',
  `dict_code` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典编码',
  `dict_label` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典项名称',
  `dict_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典项值',
  `sort` int DEFAULT NULL COMMENT '排序',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:未删除 1:已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁字段',
  `creator` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`dict_data_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` (`dict_data_id`, `dict_code`, `dict_label`, `dict_value`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 'user_sex', '未知', '0', 10, NULL, 0, 0, NULL, '2022-05-26 18:19:41', NULL, '2022-05-26 18:28:03');
INSERT INTO `sys_dict_data` (`dict_data_id`, `dict_code`, `dict_label`, `dict_value`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 'user_sex', '男', '1', 20, NULL, 0, 0, NULL, '2022-05-26 18:20:01', NULL, '2022-05-26 18:28:05');
INSERT INTO `sys_dict_data` (`dict_data_id`, `dict_code`, `dict_label`, `dict_value`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (3, 'user_sex', '女', '2', 30, NULL, 0, 0, NULL, '2022-05-26 18:20:06', NULL, '2022-05-26 18:28:07');
INSERT INTO `sys_dict_data` (`dict_data_id`, `dict_code`, `dict_label`, `dict_value`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (4, 'pay_way', '微信支付', '0', 10, NULL, 0, 0, NULL, '2022-05-26 18:21:22', NULL, '2022-05-26 18:28:28');
INSERT INTO `sys_dict_data` (`dict_data_id`, `dict_code`, `dict_label`, `dict_value`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (5, 'pay_way', '支付宝支付', '1', 20, NULL, 0, 0, NULL, '2022-05-26 18:21:35', NULL, '2022-05-26 18:28:30');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名称',
  `menu_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单类型（M：目录｜C：菜单｜P：权限）',
  `path` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `component` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件名字',
  `permission` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `sort` int DEFAULT NULL COMMENT '排序',
  `icon` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:正常 1：已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `creator` int DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` int DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 7, '系统管理｜用户管理｜列表', 'P', NULL, NULL, 'system:user:list', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:21', NULL, '2022-05-19 14:16:25');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 7, '系统管理｜用户管理｜分页', 'P', NULL, NULL, 'system:user:page', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:41', NULL, '2022-05-19 14:16:28');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (3, 7, '系统管理｜用户管理｜添加', 'P', NULL, NULL, 'system:user:save', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:48', NULL, '2022-05-19 14:16:29');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (4, 7, '系统管理｜用户管理｜修改', 'P', NULL, NULL, 'system:user:update', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:52', NULL, '2022-05-19 14:16:32');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (5, 7, '系统管理｜用户管理｜删除', 'P', NULL, NULL, 'system:user:delete', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:58', NULL, '2022-05-19 14:16:33');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (6, 0, '系统管理', 'M', '/system', '', NULL, 20, 'github', NULL, 0, 0, NULL, '2022-05-19 14:14:33', NULL, '2022-05-25 16:47:44');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (7, 6, '用户管理', 'C', 'user', 'system/user/Index', NULL, 10, 'github', NULL, 0, 0, NULL, '2022-05-19 14:14:41', NULL, '2022-05-25 16:47:45');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (8, 6, '角色管理', 'C', 'role', 'system/role/Index', NULL, 20, 'github', NULL, 0, 0, NULL, '2022-05-19 14:14:46', NULL, '2022-05-25 16:47:47');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (9, 6, '菜单管理', 'C', 'menu', 'system/menu/Index', NULL, 30, 'github', NULL, 0, 0, NULL, '2022-05-19 14:14:51', NULL, '2022-05-25 16:47:48');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (10, 8, '系统管理|角色管理|列表', 'P', NULL, NULL, 'system:role:list', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:21', NULL, '2022-05-19 14:19:07');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (11, 8, '系统管理|角色管理|分页', 'P', NULL, NULL, 'system:role:page', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:41', NULL, '2022-05-19 14:19:18');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (12, 8, '系统管理|角色管理|添加', 'P', NULL, NULL, 'system:role:save', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:48', NULL, '2022-05-19 14:19:21');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (13, 8, '系统管理|角色管理|修改', 'P', NULL, NULL, 'system:role:update', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:52', NULL, '2022-05-19 14:19:27');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (14, 8, '系统管理|角色管理|删除', 'P', NULL, NULL, 'system:role:delete', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:58', NULL, '2022-05-19 14:19:25');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (15, 9, '系统管理|菜单管理|列表', 'P', NULL, NULL, 'system:menu:list', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:21', NULL, '2022-05-19 14:21:04');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (16, 9, '系统管理|菜单管理|分页', 'P', NULL, NULL, 'system:menu:page', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:41', NULL, '2022-05-19 14:21:20');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (17, 9, '系统管理|菜单管理|添加', 'P', NULL, NULL, 'system:menu:save', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:48', NULL, '2022-05-19 14:21:10');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (18, 9, '系统管理|菜单管理|修改', 'P', NULL, NULL, 'system:menu:update', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:52', NULL, '2022-05-19 14:21:14');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (19, 9, '系统管理|菜单管理|删除', 'P', NULL, NULL, 'system:menu:delete', NULL, NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:58', NULL, '2022-05-19 14:21:18');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (20, 0, '日志管理', 'M', '/log', NULL, NULL, 30, 'github', NULL, 0, 0, NULL, '2022-05-25 14:57:41', NULL, '2022-05-25 16:47:49');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (21, 20, '登录日志', 'C', 'login', 'log/login/Index', NULL, 10, 'github', NULL, 0, 0, NULL, '2022-05-25 14:58:09', NULL, '2022-05-25 16:47:50');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (22, 20, '操作日志', 'C', 'operation', 'log/operation/Index', NULL, 20, 'github', NULL, 0, 0, NULL, '2022-05-25 15:05:53', NULL, '2022-05-25 16:47:51');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (23, 6, '通知管理', 'C', 'notices', 'system/notices/Index', NULL, 50, 'github', NULL, 0, 0, NULL, '2022-05-25 15:36:48', NULL, '2022-05-25 16:47:52');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `icon`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (24, 6, '字典管理', 'C', 'dict', 'system/dict/Index', NULL, 40, 'github', NULL, 0, 0, NULL, '2022-05-25 16:32:58', NULL, '2022-05-25 16:47:53');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:未删除 1:已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `creator` int DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` int DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique` (`name`) USING BTREE COMMENT 'Unique index'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `description`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 'admin', '管理员', NULL, 0, 0, NULL, '2022-05-17 17:11:42', NULL, '2022-05-17 17:11:42');
INSERT INTO `sys_role` (`id`, `name`, `description`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 'test', '测试', NULL, 0, 0, NULL, '2022-05-18 15:10:47', NULL, '2022-05-18 15:10:47');
INSERT INTO `sys_role` (`id`, `name`, `description`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (3, 'dev', '开发', NULL, 0, 0, NULL, '2022-05-18 15:52:36', NULL, '2022-05-18 15:52:36');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:正常 1：已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `creator` int DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` int DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 1, 2, NULL, 0, 0, NULL, '2022-05-18 15:55:47', NULL, '2022-05-18 15:55:47');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 2, 2, NULL, 0, 0, NULL, '2022-05-18 15:55:51', NULL, '2022-05-18 15:55:51');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (3, 3, 2, NULL, 0, 0, NULL, '2022-05-18 15:55:57', NULL, '2022-05-18 15:55:57');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (4, 4, 2, NULL, 0, 0, NULL, '2022-05-18 15:56:06', NULL, '2022-05-18 15:56:06');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (5, 5, 2, NULL, 0, 0, NULL, '2022-05-18 15:56:12', NULL, '2022-05-18 15:56:12');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (6, 6, 2, NULL, 0, 0, NULL, '2022-05-22 20:07:13', NULL, '2022-05-22 20:07:13');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (7, 7, 2, NULL, 0, 0, NULL, '2022-05-22 20:07:17', NULL, '2022-05-22 20:07:30');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (8, 8, 2, NULL, 0, 0, NULL, '2022-05-22 20:07:20', NULL, '2022-05-22 20:07:31');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (9, 9, 2, NULL, 0, 0, NULL, '2022-05-22 20:07:25', NULL, '2022-05-22 20:07:32');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (10, 20, 2, NULL, 0, 0, NULL, '2022-05-25 15:00:58', NULL, '2022-05-25 15:00:58');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (11, 21, 2, NULL, 0, 0, NULL, '2022-05-25 15:01:04', NULL, '2022-05-25 15:01:04');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (12, 22, 2, NULL, 0, 0, NULL, '2022-05-25 15:05:37', NULL, '2022-05-25 15:05:37');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (13, 23, 2, NULL, 0, 0, NULL, '2022-05-25 15:37:00', NULL, '2022-05-25 15:37:00');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (14, 24, 2, NULL, 0, 0, NULL, '2022-05-25 16:32:24', NULL, '2022-05-25 16:32:24');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `state` int DEFAULT '0' COMMENT '状态:(0:正常 1:禁用)',
  `phone` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:未删除 1:已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `creator` int DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` int DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique` (`username`) USING BTREE COMMENT 'Unique index'
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 'admin', '$2a$10$dc1QAkSXMdgZbvPmCU5KOOP/f6lXC9/YoCbjSvpvAVH9qPzlvAGy.', '管理员', 0, '17392652059', '17392652059@163.com', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 5, 1, '2022-05-17 17:09:34', 1, '2022-05-25 15:24:31');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 'zhangsan', '$2a$10$xcIDR2DWbmf93fjFaw87/us.TTZa7veaczlomdyf4X5ZChUsUym0K', '张三', 1, '', NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 30, NULL, '2022-05-18 15:51:22', NULL, '2022-05-26 15:45:17');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (4, 'liubei', '$2a$10$cYVnNW4ebRns1GCuP0U1EuvdLGQKp3cSIkrUkALxryx7Gujg8PKj6', '刘备333', 0, '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 8, NULL, '2022-05-18 16:14:06', NULL, '2022-05-26 15:45:18');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (45, 'guanyu', '$2a$10$qQpNMBNp4/Dl4ZvC5t3chuE8mqm4TNSelUZ/3Az0NKwHlkFf.D/lu', '关羽', 0, '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 2, NULL, '2022-05-22 22:48:14', NULL, '2022-05-26 15:45:20');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (46, 'xiaomi', '$2a$10$Mr4ocySwDSL1JD2Mn3FjBO17hQFuq88kL0A4AXx2BzWc6FaD2tLyu', '小米', 0, '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 3, NULL, '2022-05-22 22:55:01', NULL, '2022-05-26 15:45:21');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (47, 'huawei', '$2a$10$FP7V6wk4bcM7WEODTyiYKO2GJ5r4Vo5sty5QDvZOJShwzU0uQa02i', '华为', 0, '', '', NULL, NULL, 0, 2, NULL, '2022-05-26 15:14:51', NULL, '2022-05-26 15:45:24');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (48, 'rongyao', '$2a$10$onSRncx9O0otq180Cr653.X08Sssjpv82EXimI8MQ.Lrek6XTVKOW', '荣耀', 0, NULL, NULL, NULL, NULL, 0, 0, NULL, '2022-05-26 15:15:28', NULL, '2022-05-26 15:15:28');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (49, '1', '$2a$10$96Gf0C2.DtMpdnTCUuj24emskDxMUszPK3ULWB0jvRFkJbXY9c5Hu', '1', 0, '', '', NULL, NULL, 1, 0, NULL, '2022-05-26 15:17:34', NULL, '2022-05-26 16:13:41');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (50, '1231', '$2a$10$AIX.Bu7yaQF6Ld8/PfC.J./SjEJ3N2Uwnf35AJ0s2qG7SBYrhc6Rm', '大撒撒撒', 0, '', NULL, NULL, NULL, 1, 0, NULL, '2022-05-26 15:21:12', NULL, '2022-05-26 16:19:45');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (51, '1我2', '$2a$10$BThNk4AcRojfNSjcApb9PObUKwrbWKgac0MljUSKoyf0AMwJy33oy', '确实', 0, '', '', NULL, NULL, 1, 0, NULL, '2022-05-26 15:22:57', NULL, '2022-05-26 16:20:31');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `email`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (52, '是v次', '$2a$10$ksdPei1KgFz2SOQQIO1JTendHZGypGJqT.tIoSxzjEBX8gk0I6AHa', '1', 0, NULL, NULL, NULL, NULL, 1, 0, NULL, '2022-05-26 15:23:19', NULL, '2022-05-26 16:14:07');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:正常 1：已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `creator` int DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` int DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 2, 2, NULL, 0, 0, NULL, '2022-05-18 15:52:22', NULL, '2022-05-18 15:52:22');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (3, 9, 2, NULL, 0, 0, NULL, '2022-05-22 13:40:59', NULL, '2022-05-22 13:40:59');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (4, 45, 2, NULL, 1, 0, NULL, '2022-05-22 22:48:14', NULL, '2022-05-26 15:21:30');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (5, 45, 3, NULL, 1, 0, NULL, '2022-05-22 22:48:14', NULL, '2022-05-26 15:21:30');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (6, 46, 2, NULL, 1, 0, NULL, '2022-05-22 22:55:01', NULL, '2022-05-26 15:21:35');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (7, 1, 1, NULL, 1, 0, NULL, '2022-05-22 22:59:35', NULL, '2022-05-26 15:46:01');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (8, 1, 2, NULL, 1, 0, NULL, '2022-05-22 22:59:39', NULL, '2022-05-26 15:46:01');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (9, 1, 3, NULL, 1, 0, NULL, '2022-05-22 22:59:44', NULL, '2022-05-26 15:46:01');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (10, 4, 2, NULL, 1, 0, NULL, '2022-05-23 11:24:51', NULL, '2022-05-26 15:21:24');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (11, 47, 1, NULL, 1, 0, NULL, '2022-05-26 15:14:51', NULL, '2022-05-26 15:15:00');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (12, 47, 2, NULL, 1, 0, NULL, '2022-05-26 15:14:51', NULL, '2022-05-26 15:15:00');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (13, 47, 3, NULL, 1, 0, NULL, '2022-05-26 15:14:51', NULL, '2022-05-26 15:15:00');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (14, 47, 1, NULL, 1, 0, NULL, '2022-05-26 15:15:00', NULL, '2022-05-26 15:21:42');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (15, 47, 2, NULL, 1, 0, NULL, '2022-05-26 15:15:00', NULL, '2022-05-26 15:21:42');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (16, 47, 3, NULL, 1, 0, NULL, '2022-05-26 15:15:00', NULL, '2022-05-26 15:21:42');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (17, 48, 2, NULL, 0, 0, NULL, '2022-05-26 15:15:28', NULL, '2022-05-26 15:15:28');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (18, 49, 1, NULL, 0, 0, NULL, '2022-05-26 15:17:34', NULL, '2022-05-26 15:17:34');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (19, 50, 2, NULL, 0, 0, NULL, '2022-05-26 15:21:12', NULL, '2022-05-26 15:21:12');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (20, 4, 2, NULL, 0, 0, NULL, '2022-05-26 15:21:24', NULL, '2022-05-26 15:21:24');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (21, 45, 2, NULL, 0, 0, NULL, '2022-05-26 15:21:30', NULL, '2022-05-26 15:21:30');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (22, 45, 3, NULL, 0, 0, NULL, '2022-05-26 15:21:30', NULL, '2022-05-26 15:21:30');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (23, 46, 2, NULL, 0, 0, NULL, '2022-05-26 15:21:35', NULL, '2022-05-26 15:21:35');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (24, 47, 1, NULL, 0, 0, NULL, '2022-05-26 15:21:42', NULL, '2022-05-26 15:21:42');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (25, 47, 2, NULL, 0, 0, NULL, '2022-05-26 15:21:42', NULL, '2022-05-26 15:21:42');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (26, 47, 3, NULL, 0, 0, NULL, '2022-05-26 15:21:42', NULL, '2022-05-26 15:21:42');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (27, 51, 1, NULL, 0, 0, NULL, '2022-05-26 15:22:57', NULL, '2022-05-26 15:22:57');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (28, 51, 2, NULL, 0, 0, NULL, '2022-05-26 15:22:57', NULL, '2022-05-26 15:22:57');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (29, 52, 2, NULL, 0, 0, NULL, '2022-05-26 15:23:19', NULL, '2022-05-26 15:23:19');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (30, 1, 1, NULL, 0, 0, NULL, '2022-05-26 15:46:01', NULL, '2022-05-26 15:46:01');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (31, 1, 2, NULL, 0, 0, NULL, '2022-05-26 15:46:01', NULL, '2022-05-26 15:46:01');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
