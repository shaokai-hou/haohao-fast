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

 Date: 22/05/2022 15:19:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:正常 1：已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `creator` int DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` int DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 7, '系统管理｜用户管理｜列表', 'P', NULL, NULL, 'system:user:list', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:21', NULL, '2022-05-19 14:16:25');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 7, '系统管理｜用户管理｜分页', 'P', NULL, NULL, 'system:user:page', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:41', NULL, '2022-05-19 14:16:28');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (3, 7, '系统管理｜用户管理｜添加', 'P', NULL, NULL, 'system:user:save', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:48', NULL, '2022-05-19 14:16:29');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (4, 7, '系统管理｜用户管理｜修改', 'P', NULL, NULL, 'system:user:update', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:52', NULL, '2022-05-19 14:16:32');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (5, 7, '系统管理｜用户管理｜删除', 'P', NULL, NULL, 'system:user:delete', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:58', NULL, '2022-05-19 14:16:33');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (6, 0, '系统管理', 'M', '/system', '', NULL, 20, NULL, 0, 0, NULL, '2022-05-19 14:14:33', NULL, '2022-05-22 05:15:09');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (7, 6, '用户管理', 'C', '/user', 'system/user/Index', NULL, NULL, NULL, 0, 0, NULL, '2022-05-19 14:14:41', NULL, '2022-05-22 05:15:42');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (8, 6, '角色管理', 'C', '/role', 'system/role/Index', NULL, NULL, NULL, 0, 0, NULL, '2022-05-19 14:14:46', NULL, '2022-05-22 05:15:40');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (9, 6, '菜单管理', 'C', '/menu', 'system/menu/Index', NULL, NULL, NULL, 0, 0, NULL, '2022-05-19 14:14:51', NULL, '2022-05-22 05:15:38');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (10, 8, '系统管理|角色管理|列表', 'P', NULL, NULL, 'system:role:list', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:21', NULL, '2022-05-19 14:19:07');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (11, 8, '系统管理|角色管理|分页', 'P', NULL, NULL, 'system:role:page', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:41', NULL, '2022-05-19 14:19:18');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (12, 8, '系统管理|角色管理|添加', 'P', NULL, NULL, 'system:role:save', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:48', NULL, '2022-05-19 14:19:21');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (13, 8, '系统管理|角色管理|修改', 'P', NULL, NULL, 'system:role:update', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:52', NULL, '2022-05-19 14:19:27');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (14, 8, '系统管理|角色管理|删除', 'P', NULL, NULL, 'system:role:delete', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:58', NULL, '2022-05-19 14:19:25');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (15, 9, '系统管理|菜单管理|列表', 'P', NULL, NULL, 'system:menu:list', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:21', NULL, '2022-05-19 14:21:04');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (16, 9, '系统管理|菜单管理|分页', 'P', NULL, NULL, 'system:menu:page', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:41', NULL, '2022-05-19 14:21:20');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (17, 9, '系统管理|菜单管理|添加', 'P', NULL, NULL, 'system:menu:save', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:48', NULL, '2022-05-19 14:21:10');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (18, 9, '系统管理|菜单管理|修改', 'P', NULL, NULL, 'system:menu:update', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:52', NULL, '2022-05-19 14:21:14');
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `sort`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (19, 9, '系统管理|菜单管理|删除', 'P', NULL, NULL, 'system:menu:delete', NULL, NULL, 0, 0, NULL, '2022-05-18 15:53:58', NULL, '2022-05-19 14:21:18');
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
  `deleted` int DEFAULT '0' COMMENT '删除:(0:正常 1：已删除)',
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 1, 2, NULL, 0, 0, NULL, '2022-05-18 15:55:47', NULL, '2022-05-18 15:55:47');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 2, 2, NULL, 0, 0, NULL, '2022-05-18 15:55:51', NULL, '2022-05-18 15:55:51');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (3, 3, 2, NULL, 0, 0, NULL, '2022-05-18 15:55:57', NULL, '2022-05-18 15:55:57');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (4, 4, 2, NULL, 0, 0, NULL, '2022-05-18 15:56:06', NULL, '2022-05-18 15:56:06');
INSERT INTO `sys_role_menu` (`id`, `menu_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (5, 5, 2, NULL, 0, 0, NULL, '2022-05-18 15:56:12', NULL, '2022-05-18 15:56:12');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `state` int DEFAULT '0' COMMENT '状态:(0:正常 1:禁用)',
  `phone` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除:(0:正常 1：已删除)',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `creator` int DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` int DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique` (`username`) USING BTREE COMMENT 'Unique index'
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 'admin', '$2a$10$dc1QAkSXMdgZbvPmCU5KOOP/f6lXC9/YoCbjSvpvAVH9qPzlvAGy.', '管理员', 0, '17392652059', NULL, NULL, 0, 0, 1, '2022-05-17 17:09:34', 1, '2022-05-22 06:01:53');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (2, 'zhangsan', '$2a$10$udh0HO6rED6bKowcEIExTuG/2dhN23pQAIvPO5ejn8ihGzAyUCI6G', '张三', 0, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 6, NULL, '2022-05-18 15:51:22', NULL, '2022-05-22 13:55:01');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (4, 'liubei', '$2a$10$cYVnNW4ebRns1GCuP0U1EuvdLGQKp3cSIkrUkALxryx7Gujg8PKj6', '刘备', 0, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 0, NULL, '2022-05-18 16:14:06', NULL, '2022-05-22 13:55:51');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (8, 'liubei1', '$2a$10$vConsIYvygPysEuLmuoH6eqXMf7ghkNqRAfZJhxE4lRkkbJNx28lO', '刘备1', 0, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 1, NULL, '2022-05-20 11:36:46', NULL, '2022-05-22 13:55:54');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `state`, `phone`, `avatar`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (9, 'guanyu', '$2a$10$ufpNeSuVTIVaz//PrYpqv.sAf2ArM.1lSbePcTPvp/YqcJReTESK2', '关羽', 0, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 2, NULL, '2022-05-20 11:38:23', NULL, '2022-05-22 13:55:55');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (1, 2, 2, NULL, 0, 0, NULL, '2022-05-18 15:52:22', NULL, '2022-05-18 15:52:22');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `remark`, `deleted`, `version`, `creator`, `create_time`, `updater`, `update_time`) VALUES (3, 9, 2, NULL, 0, 0, NULL, '2022-05-22 13:40:59', NULL, '2022-05-22 13:40:59');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
