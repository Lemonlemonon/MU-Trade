/*
MySQL Backup
Database: db_campus_market
Backup Time: 2023-11-06 23:14:59
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `db_campus_market`.`fyp_menu`;
DROP TABLE IF EXISTS `db_campus_market`.`fyp_operater_log`;
DROP TABLE IF EXISTS `db_campus_market`.`fyp_role`;
DROP TABLE IF EXISTS `db_campus_market`.`fyp_role_authorities`;
DROP TABLE IF EXISTS `db_campus_market`.`fyp_site_setting`;
DROP TABLE IF EXISTS `db_campus_market`.`fyp_user`;
CREATE TABLE `fyp_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `url` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `icon` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `sort` int NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `is_bitton` bit(1) NOT NULL,
  `is_show` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKsbtnjocfrq29e8taxdwo21gic` (`parent_id`) USING BTREE,
  CONSTRAINT `FKsbtnjocfrq29e8taxdwo21gic` FOREIGN KEY (`parent_id`) REFERENCES `fyp_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
CREATE TABLE `fyp_operater_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `operator` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=523 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
CREATE TABLE `fyp_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `remark` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
CREATE TABLE `fyp_role_authorities` (
  `role_id` bigint NOT NULL,
  `authorities_id` bigint NOT NULL,
  KEY `FKhj7ap1o1cjrl7enr9arf5f2qp` (`authorities_id`) USING BTREE,
  KEY `FKg3xdaexmr0x1qx8omhvjtk46d` (`role_id`) USING BTREE,
  CONSTRAINT `FKg3xdaexmr0x1qx8omhvjtk46d` FOREIGN KEY (`role_id`) REFERENCES `fyp_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhj7ap1o1cjrl7enr9arf5f2qp` FOREIGN KEY (`authorities_id`) REFERENCES `fyp_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
CREATE TABLE `fyp_site_setting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `all_rights` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `logo_1` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `logo_2` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `qrcode` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `site_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `site_url` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
CREATE TABLE `fyp_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `email` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `head_pic` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `mobile` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `username` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_btsosjytrl4hu7fnm1intcpo8` (`username`) USING BTREE,
  KEY `FKg09b8o67eu61st68rv6nk8npj` (`role_id`) USING BTREE,
  CONSTRAINT `FKg09b8o67eu61st68rv6nk8npj` FOREIGN KEY (`role_id`) REFERENCES `fyp_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
BEGIN;
LOCK TABLES `db_campus_market`.`fyp_menu` WRITE;
DELETE FROM `db_campus_market`.`fyp_menu`;
INSERT INTO `db_campus_market`.`fyp_menu` (`id`,`create_time`,`update_time`,`name`,`url`,`icon`,`sort`,`parent_id`,`is_bitton`,`is_show`) VALUES (2, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'System setting', '', 'mdi-settings', 0, NULL, b'0', b'1'),(3, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Menu management', '/admin/menu/list', 'mdi-view-list', 1, 2, b'0', b'1'),(5, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/menu/add', 'mdi-plus', 2, 3, b'0', b'1'),(7, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Role management', '/admin/role/list', 'mdi-account-settings-variant', 5, 2, b'0', b'1'),(8, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/menu/edit\')', 'mdi-grease-pencil', 3, 3, b'1', b'1'),(9, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/menu/delete\')', 'mdi-close', 4, 3, b'1', b'1'),(10, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/role/add', 'mdi-account-plus', 6, 7, b'0', b'1'),(11, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/role/edit\')', 'mdi-account-edit', 7, 7, b'1', b'1'),(12, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/role/delete\')', 'mdi-account-remove', 8, 7, b'1', b'1'),(13, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'User management', '/admin/user/list', 'mdi-account-multiple', 9, 2, b'0', b'1'),(14, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/user/add', 'mdi-account-plus', 10, 13, b'0', b'1'),(15, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/user/edit\')', 'mdi-account-edit', 11, 13, b'1', b'1'),(16, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/user/delete\')', 'mdi-account-remove', 12, 13, b'1', b'1'),(19, '2023-11-06 21:26:11', '2023-11-07 07:03:26', 'Upload image', '/admin/upload/upload_photo', 'mdi-arrow-up-bold-circle', 0, 13, b'1', b'1'),(20, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Logs management', '/system/operator_log_list', 'mdi-tag-multiple', 13, 2, b'0', b'1'),(21, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/system/delete_operator_log\')', 'mdi-tag-remove', 14, 20, b'1', b'1'),(22, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Erase logs', 'delAll(\'/system/delete_all_operator_log\')', 'mdi-delete-circle', 15, 20, b'1', b'1'),(23, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Database backup', '/admin/database_bak/list', 'mdi-database', 16, 2, b'0', b'1'),(24, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Backup', 'add(\'/admin/database_bak/add\')', 'mdi-database-plus', 17, 23, b'1', b'1'),(25, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/database_bak/delete\')', 'mdi-database-minus', 18, 23, b'1', b'1'),(26, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Restore', 'restore(\'/admin/database_bak/restore\')', 'mdi-database-minus', 19, 23, b'1', b'1'),(27, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Item management', '/admin/goods_category/list', 'mdi-dialpad', 0, NULL, b'0', b'1'),(28, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Categary', '/admin/goods_category/list', 'mdi-apps', 0, 27, b'0', b'1'),(29, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Item management', '/admin/goods/list', 'mdi-cart', 0, 27, b'0', b'1'),(30, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/goods_category/add', 'mdi-battery-positive', 0, 28, b'0', b'1'),(31, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/goods_category/edit\')', 'mdi-border-color', 0, 28, b'1', b'1'),(32, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/goods_category/delete\')', 'mdi-close', 0, 28, b'1', b'1'),(33, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Place item', 'up(\'/admin/goods/up_down\')', 'mdi-arrow-up-bold-box', 0, 29, b'1', b'1'),(34, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Withdraw item', 'down(\'/admin/goods/up_down\')', 'mdi-arrow-down-bold-box', 0, 29, b'1', b'1'),(35, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/goods/delete\')', 'mdi-close-box', 0, 29, b'1', b'1'),(36, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Recommend', 'recommend(\'/admin/goods/recommend\')', 'mdi-thumb-up', 0, 29, b'1', b'1'),(37, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Unrecommend', 'unrecommend(\'/admin/goods/recommend\')', 'mdi-thumb-down', 0, 29, b'1', b'1'),(38, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Request Item', '/admin/wanted_goods/list', 'mdi-ticket', 0, 27, b'0', b'1'),(39, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/wanted_goods/delete\')', 'mdi-close-box', 0, 38, b'1', b'1'),(40, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Student management', '/admin/student/list', 'mdi-account-multiple', 0, NULL, b'0', b'1'),(41, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Student list', '/admin/student/list', 'mdi-account-multiple', 0, 40, b'0', b'1'),(42, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Freeze account', 'freeze(\'/admin/student/update_status\')', 'mdi-account-settings-variant', 0, 41, b'1', b'1'),(43, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Activate account', 'openStudent(\'/admin/student/update_status\')', 'mdi-account-key', 0, 41, b'1', b'1'),(44, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/student/delete\')', 'mdi-account-remove', 0, 41, b'1', b'1'),(45, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Comment management', '/admin/comment/list', 'mdi-comment-account', 0, NULL, b'0', b'1'),(46, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Comment list', '/admin/comment/list', 'mdi-comment-multiple-outline', 0, 45, b'0', b'1'),(47, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/comment/delete\')', 'mdi-message-bulleted-off', 0, 46, b'1', b'1'),(48, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Report management', '/admin/report/list', 'mdi-alert', 0, NULL, b'0', b'1'),(49, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Report list', '/admin/report/list', 'mdi-view-headline', 0, 48, b'0', b'1'),(50, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/report/delete\')', 'mdi-close-box', 0, 49, b'1', b'1'),(51, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'News', '/admin/news/list', 'mdi-onenote', 0, NULL, b'0', b'1'),(52, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'News list', '/admin/news/list', 'mdi-book-open', 0, 51, b'0', b'1'),(53, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/news/add', 'mdi-plus', 0, 52, b'0', b'1'),(54, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/news/edit\')', 'mdi-border-color', 0, 52, b'1', b'1'),(55, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/news/delete\')', 'mdi-close', 0, 52, b'1', b'1'),(56, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Site setting', '', 'mdi-settings', 0, NULL, b'0', b'1'),(57, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Friendly links', '/admin/friend_link/list', 'mdi-vector-line', 0, 56, b'0', b'1'),(58, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/friend_link/add', 'mdi-plus', 0, 57, b'0', b'1'),(59, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/friend_link/edit\')', 'mdi-border-color', 0, 57, b'1', b'1'),(60, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/friend_link/delete\')', 'mdi-close-box', 0, 57, b'1', b'1'),(61, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Site setting', '/admin/site_setting/setting', 'mdi-wrench', 0, 56, b'0', b'1'),(62, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Save changes', '/admin/site_setting/save_setting', 'mdi-marker-check', 0, 61, b'1', b'0');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `db_campus_market`.`fyp_operater_log` WRITE;
DELETE FROM `db_campus_market`.`fyp_operater_log`;
INSERT INTO `db_campus_market`.`fyp_operater_log` (`id`,`create_time`,`update_time`,`content`,`operator`) VALUES (519, '2023-11-07 06:31:21', '2023-11-07 06:31:21', 'User【admin】 at 【2023-11-06 22:31:21】 Logined in to the system！', 'admin'),(520, '2023-11-07 06:53:24', '2023-11-07 06:53:24', 'User【admin】 at 【2023-11-06 22:53:23】 Logined in to the system！', 'admin'),(521, '2023-11-07 06:53:53', '2023-11-07 06:53:53', 'User【admin】 at 【2023-11-06 22:53:52】 Logined in to the system！', 'admin'),(522, '2023-11-07 07:03:26', '2023-11-07 07:03:26', 'edited menu information【Menu [name=Upload image, parent=Menu [name=null, parent=null, url=null, icon=null, sort=0, isButton=false, isShow=true], url=/admin/upload/upload_photo, icon=mdi-arrow-up-bold-circle, sort=0, isButton=true, isShow=true]】', 'admin');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `db_campus_market`.`fyp_role` WRITE;
DELETE FROM `db_campus_market`.`fyp_role`;
INSERT INTO `db_campus_market`.`fyp_role` (`id`,`create_time`,`update_time`,`name`,`remark`,`status`) VALUES (1, '2020-03-15 13:16:38', '2020-04-18 16:28:37', 'Super Admin', 'Super admin holding ALL permission', 1),(2, '2020-03-15 13:18:57', '2020-03-21 22:18:43', 'Admin', 'Admin holding particular permission', 1),(4, '2020-03-21 20:11:00', '2020-03-23 17:49:00', 'Test role', 'A test role', 0);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `db_campus_market`.`fyp_role_authorities` WRITE;
DELETE FROM `db_campus_market`.`fyp_role_authorities`;
INSERT INTO `db_campus_market`.`fyp_role_authorities` (`role_id`,`authorities_id`) VALUES (2, 2),(2, 3),(2, 5),(2, 7),(2, 11),(2, 13),(2, 16),(4, 2),(4, 13),(4, 15),(1, 2),(1, 3),(1, 5),(1, 8),(1, 9),(1, 7),(1, 10),(1, 11),(1, 12),(1, 13),(1, 14),(1, 15),(1, 16),(1, 19),(1, 20),(1, 21),(1, 22),(1, 23),(1, 24),(1, 25),(1, 26),(1, 27),(1, 28),(1, 30),(1, 31),(1, 32),(1, 29),(1, 33),(1, 34),(1, 35),(1, 36),(1, 37),(1, 38),(1, 39),(1, 40),(1, 41),(1, 42),(1, 43),(1, 44),(1, 45),(1, 46),(1, 47),(1, 48),(1, 49),(1, 50),(1, 51),(1, 52),(1, 53),(1, 54),(1, 55),(1, 56),(1, 57),(1, 58),(1, 59),(1, 60),(1, 61),(1, 62);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `db_campus_market`.`fyp_site_setting` WRITE;
DELETE FROM `db_campus_market`.`fyp_site_setting`;
INSERT INTO `db_campus_market`.`fyp_site_setting` (`id`,`create_time`,`update_time`,`all_rights`,`logo_1`,`logo_2`,`qrcode`,`site_name`,`site_url`) VALUES (1, '2023-11-06 17:02:17', '2023-11-06 13:59:04', 'MU-Market', '20210307/1615096736988.png', '20200418/1587201663687.png', '20210223/1614075844201.png', 'MU-Market', 'MUMarket.com');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `db_campus_market`.`fyp_user` WRITE;
DELETE FROM `db_campus_market`.`fyp_user`;
INSERT INTO `db_campus_market`.`fyp_user` (`id`,`create_time`,`update_time`,`email`,`head_pic`,`mobile`,`password`,`sex`,`status`,`username`,`role_id`) VALUES (1, '2023-11-06 22:30:17', '2023-11-06 22:31:10', 'admin@mail.com', NULL, '123456', '123456', 1, 1, 'admin', 1);
UNLOCK TABLES;
COMMIT;
