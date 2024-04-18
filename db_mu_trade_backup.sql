/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : db_mu_trade_backup

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 19/04/2024 00:10:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fyp_comment
-- ----------------------------
DROP TABLE IF EXISTS `fyp_comment`;
CREATE TABLE `fyp_comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `reply_to` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `goods_id` bigint NULL DEFAULT NULL,
  `student_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKmu9neaan3erjvqq92k9e8tsmt`(`goods_id` ASC) USING BTREE,
  INDEX `FK6hfa6lislban9lqrv34ptifln`(`student_id` ASC) USING BTREE,
  CONSTRAINT `fyp_comment_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `fyp_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fyp_comment_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `fyp_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_comment
-- ----------------------------
INSERT INTO `fyp_comment` VALUES (1, '2024-03-06 01:57:43', '2024-03-06 01:57:43', 'When did you buy this?', NULL, 8, 2);
INSERT INTO `fyp_comment` VALUES (2, '2024-03-06 01:58:10', '2024-03-06 01:58:10', 'Replys: \"When did you buy this?\"<br>2 months ago', 'Zhuu', 8, 3);
INSERT INTO `fyp_comment` VALUES (4, '2024-03-06 02:18:03', '2024-03-06 02:18:03', 'Replys: “When did you buy this?”<br> today', 'Zhuu', 8, 3);
INSERT INTO `fyp_comment` VALUES (5, '2024-03-06 02:18:14', '2024-03-06 02:18:14', 'Replys: “When did you buy this?”<br>today', 'Zhuu', 8, 3);

-- ----------------------------
-- Table structure for fyp_database_bak
-- ----------------------------
DROP TABLE IF EXISTS `fyp_database_bak`;
CREATE TABLE `fyp_database_bak`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `filename` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `filepath` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_database_bak
-- ----------------------------
INSERT INTO `fyp_database_bak` VALUES (22, '2023-11-13 01:44:10', '2023-11-13 01:44:10', 'db_campus_market_20231112174410.sql', 'D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/');
INSERT INTO `fyp_database_bak` VALUES (23, '2024-03-01 09:00:00', '2024-03-01 09:00:00', 'db_campus_market_20240301010000.sql', 'D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/');
INSERT INTO `fyp_database_bak` VALUES (24, '2024-03-01 23:17:27', '2024-03-01 23:17:27', 'db_campus_market_20240301151727.sql', 'D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/');

-- ----------------------------
-- Table structure for fyp_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `fyp_friend_link`;
CREATE TABLE `fyp_friend_link`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sort` int NOT NULL,
  `url` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_friend_link
-- ----------------------------
INSERT INTO `fyp_friend_link` VALUES (1, '2023-12-16 00:27:18', '2024-03-06 01:43:35', 'Maynooth university', 0, 'https://www.maynoothuniversity.ie/');
INSERT INTO `fyp_friend_link` VALUES (2, '2023-12-16 00:27:54', '2023-12-16 00:27:54', 'Moodle', 0, 'https://moodle.maynoothuniversity.ie/login/index.php');

-- ----------------------------
-- Table structure for fyp_goods
-- ----------------------------
DROP TABLE IF EXISTS `fyp_goods`;
CREATE TABLE `fyp_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `buy_price` float NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `flag` int NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `photo` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `recommend` int NOT NULL,
  `sell_price` float NOT NULL,
  `status` int NOT NULL,
  `view_number` int NOT NULL,
  `goods_category_id` bigint NULL DEFAULT NULL,
  `student_id` bigint NULL DEFAULT NULL,
  `goods_bidding_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKdswoqg26m5t6hh2r5piooslf8`(`goods_category_id` ASC) USING BTREE,
  INDEX `FKl3545va3h3hk6ju68qled17cw`(`student_id` ASC) USING BTREE,
  CONSTRAINT `fyp_goods_ibfk_1` FOREIGN KEY (`goods_category_id`) REFERENCES `fyp_goods_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fyp_goods_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `fyp_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_goods
-- ----------------------------
INSERT INTO `fyp_goods` VALUES (4, '2023-11-20 15:05:32', '2023-12-19 03:30:43', 12888, '4', 0, 'Asus laptop', '20231109/1586610285979.jpg', 0, 3000, 3, 91, 9, 1, NULL);
INSERT INTO `fyp_goods` VALUES (5, '2024-02-22 23:30:37', '2024-03-06 01:57:12', 23, 'Asus laptop, used only few times', 0, 'I5 3600G Asus laptop', '20231109/1586610285979.jpg', 0, 500, 1, 91, 9, 1, NULL);
INSERT INTO `fyp_goods` VALUES (6, '2024-03-04 21:08:14', '2024-03-06 21:39:24', 250, 'alexia &google assistant\r\n43UQ75006LF or 43 up 7500 plf available WITH STAND, warranty 6 months ,genuine\r\nBRAND NEW remote controller included', 1, '43 alexia 4K Smart TV', '20240304/1709557643853.jpg', 1, 250, 3, 11, 24, 2, NULL);
INSERT INTO `fyp_goods` VALUES (7, '2024-03-04 21:09:51', '2024-03-12 04:30:16', 140, 'Condition: New\r\n**Brand New Samsung Pro Buds 2\r\n**Fully Sealed\r\n**No offers below asking', 1, 'Samsung Pro Buds 2', '20240304/1709557763829.jpg', 0, 140, 1, 5, 16, 2, NULL);
INSERT INTO `fyp_goods` VALUES (8, '2024-03-04 21:12:00', '2024-03-12 04:30:04', 480, 'Airpods Max - Sky Blue', 0, 'Airpods Max - Sky Blue', '20240304/1709557918742.jpg', 0, 480, 1, 50, 16, 3, NULL);
INSERT INTO `fyp_goods` VALUES (9, '2024-03-12 23:47:07', '2024-03-12 23:47:31', 100, 'Pure Electric Scooter\r\n\r\nCharger Included\r\n\r\nRange is 25 km\r\nTop speed is 25 km', 0, 'Pure Air Electric Scooter', '20240312/1710258400160.jpg', 0, 80, 1, 2, 48, 2, 4);

-- ----------------------------
-- Table structure for fyp_goods_bidding
-- ----------------------------
DROP TABLE IF EXISTS `fyp_goods_bidding`;
CREATE TABLE `fyp_goods_bidding`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `good_id` bigint NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL COMMENT 'Bidding status：0-active，1-over，2-end',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fyp_goods_bidding
-- ----------------------------
INSERT INTO `fyp_goods_bidding` VALUES (4, NULL, 0, '2024-03-12 23:47:07', '2024-03-12 23:47:07');

-- ----------------------------
-- Table structure for fyp_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `fyp_goods_category`;
CREATE TABLE `fyp_goods_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `icon` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  `parent_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKbe945ksppqc1hgw520eo31gpv`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `fyp_goods_category_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `fyp_goods_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_goods_category
-- ----------------------------
INSERT INTO `fyp_goods_category` VALUES (1, '2023-11-20 20:20:05', '2024-03-01 23:10:42', '20240301/1709305839721.png', 'Phones', 0, NULL);
INSERT INTO `fyp_goods_category` VALUES (4, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1585834542235.png', 'Smart phones', 1, 1);
INSERT INTO `fyp_goods_category` VALUES (5, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1585834572308.png', 'Phone accessories', 2, 1);
INSERT INTO `fyp_goods_category` VALUES (6, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1585834607924.png', 'Sliding phones', 3, 1);
INSERT INTO `fyp_goods_category` VALUES (7, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1585834633735.png', 'Flip phones', 4, 1);
INSERT INTO `fyp_goods_category` VALUES (8, '2023-11-20 20:20:05', '2024-03-01 23:11:10', '20240301/1709305868430.png', 'Computers', 5, NULL);
INSERT INTO `fyp_goods_category` VALUES (9, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1585834679788.png', 'Laptops', 6, 8);
INSERT INTO `fyp_goods_category` VALUES (10, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1585834694895.png', 'PC', 7, 8);
INSERT INTO `fyp_goods_category` VALUES (11, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1585834717184.png', 'Tablets', 8, 8);
INSERT INTO `fyp_goods_category` VALUES (15, '2023-11-20 20:20:05', '2024-03-01 23:11:28', '20240301/1709305886039.png', 'Multimedia', 9, NULL);
INSERT INTO `fyp_goods_category` VALUES (16, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586084805468.png', 'Headphones', 10, 15);
INSERT INTO `fyp_goods_category` VALUES (17, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586084854001.png', 'MP3/MP4', 11, 15);
INSERT INTO `fyp_goods_category` VALUES (18, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586084878657.png', 'Gaming consoles', 12, 15);
INSERT INTO `fyp_goods_category` VALUES (19, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586084911580.png', 'Keyboards', 13, 15);
INSERT INTO `fyp_goods_category` VALUES (20, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586084936056.png', 'Mouses', 14, 15);
INSERT INTO `fyp_goods_category` VALUES (21, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085005899.png', 'Digital accessories', 15, NULL);
INSERT INTO `fyp_goods_category` VALUES (22, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085024716.png', 'Portable hard drives', 16, 21);
INSERT INTO `fyp_goods_category` VALUES (23, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085052292.png', 'Digital cameras', 17, 21);
INSERT INTO `fyp_goods_category` VALUES (24, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085077518.png', 'Monitors', 18, 21);
INSERT INTO `fyp_goods_category` VALUES (25, '2023-11-20 20:20:05', '2024-03-01 23:22:53', '20240301/1709306571205.png', 'Sports', 19, NULL);
INSERT INTO `fyp_goods_category` VALUES (26, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085124703.png', 'Basketballs', 20, 25);
INSERT INTO `fyp_goods_category` VALUES (27, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085152812.png', 'Badminton', 21, 25);
INSERT INTO `fyp_goods_category` VALUES (28, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085164372.png', 'Football', 22, 25);
INSERT INTO `fyp_goods_category` VALUES (29, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085182130.png', 'Rugby', 23, 25);
INSERT INTO `fyp_goods_category` VALUES (30, '2023-11-20 20:20:05', '2024-03-01 23:23:11', '20240301/1709306588640.png', 'Outfits', 24, NULL);
INSERT INTO `fyp_goods_category` VALUES (31, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085239138.png', 'Tops', 25, 30);
INSERT INTO `fyp_goods_category` VALUES (32, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085262037.png', 'Pants', 26, 30);
INSERT INTO `fyp_goods_category` VALUES (33, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085278533.png', 'Bags', 27, 30);
INSERT INTO `fyp_goods_category` VALUES (34, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085302412.png', 'Umbrellas', 28, 30);
INSERT INTO `fyp_goods_category` VALUES (35, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085319791.png', 'Shoes', 29, 30);
INSERT INTO `fyp_goods_category` VALUES (36, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085349204.png', 'Accessories', 30, 30);
INSERT INTO `fyp_goods_category` VALUES (37, '2023-11-20 20:20:05', '2024-03-01 23:23:37', '20240301/1709306615960.png', 'Entertainments', 31, NULL);
INSERT INTO `fyp_goods_category` VALUES (38, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085407122.png', 'Instruments', 32, 37);
INSERT INTO `fyp_goods_category` VALUES (39, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085433434.png', 'Virtual accounts', 33, 37);
INSERT INTO `fyp_goods_category` VALUES (40, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085449303.png', 'Giftcards', 34, 37);
INSERT INTO `fyp_goods_category` VALUES (41, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085471766.png', 'Makeups', 35, 37);
INSERT INTO `fyp_goods_category` VALUES (42, '2023-11-20 20:20:05', '2024-03-01 23:23:49', '20240301/1709306627038.png', 'Books', 36, NULL);
INSERT INTO `fyp_goods_category` VALUES (43, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085520436.png', 'Textbooks', 37, 42);
INSERT INTO `fyp_goods_category` VALUES (44, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085534371.png', 'Novels', 38, 42);
INSERT INTO `fyp_goods_category` VALUES (45, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085556673.png', 'Manga', 39, 42);
INSERT INTO `fyp_goods_category` VALUES (46, '2023-11-20 20:20:05', '2024-03-01 23:24:05', '20240301/1709306643049.png', 'Transportation', 40, NULL);
INSERT INTO `fyp_goods_category` VALUES (47, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085604095.png', 'Bicycle', 41, 46);
INSERT INTO `fyp_goods_category` VALUES (48, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085624951.png', 'E-bike', 42, 46);
INSERT INTO `fyp_goods_category` VALUES (49, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085652162.png', 'LEAP card', 43, 46);
INSERT INTO `fyp_goods_category` VALUES (50, '2023-11-20 20:20:05', '2024-03-01 23:24:22', '20240301/1709306660566.png', 'Personal skills', 44, NULL);
INSERT INTO `fyp_goods_category` VALUES (51, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085696883.png', 'Photograph', 45, 50);
INSERT INTO `fyp_goods_category` VALUES (52, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586085714915.png', 'Painting', 46, 50);
INSERT INTO `fyp_goods_category` VALUES (53, '2023-11-20 20:20:05', '2024-03-01 23:24:40', '20240301/1709306678318.png', 'Others', 46, NULL);
INSERT INTO `fyp_goods_category` VALUES (54, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586611155253.png', 'Watches', 47, 21);
INSERT INTO `fyp_goods_category` VALUES (55, '2023-11-20 20:20:05', '2023-11-20 20:20:05', '20231201/1586701902327.png', 'Others', 48, 53);

-- ----------------------------
-- Table structure for fyp_menu
-- ----------------------------
DROP TABLE IF EXISTS `fyp_menu`;
CREATE TABLE `fyp_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `url` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `icon` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sort` int NOT NULL,
  `parent_id` bigint NULL DEFAULT NULL,
  `is_bitton` bit(1) NOT NULL,
  `is_show` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKsbtnjocfrq29e8taxdwo21gic`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `fyp_menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `fyp_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of fyp_menu
-- ----------------------------
INSERT INTO `fyp_menu` VALUES (2, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'System setting', '', 'mdi-settings', 0, NULL, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (3, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Menu management', '/admin/menu/list', 'mdi-view-list', 1, 2, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (5, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/menu/add', 'mdi-plus', 2, 3, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (7, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Role management', '/admin/role/list', 'mdi-account-settings-variant', 5, 2, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (8, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/menu/edit\')', 'mdi-grease-pencil', 3, 3, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (9, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/menu/delete\')', 'mdi-close', 4, 3, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (10, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/role/add', 'mdi-account-plus', 6, 7, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (11, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/role/edit\')', 'mdi-account-edit', 7, 7, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (12, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/role/delete\')', 'mdi-account-remove', 8, 7, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (13, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'User management', '/admin/user/list', 'mdi-account-multiple', 9, 2, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (14, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/user/add', 'mdi-account-plus', 10, 13, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (15, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/user/edit\')', 'mdi-account-edit', 11, 13, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (16, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/user/delete\')', 'mdi-account-remove', 12, 13, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (19, '2023-11-06 21:26:11', '2023-11-07 07:03:26', 'Upload image', '/admin/upload/upload_photo', 'mdi-arrow-up-bold-circle', 0, 13, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (20, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Logs management', '/system/operator_log_list', 'mdi-tag-multiple', 13, 2, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (21, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/system/delete_operator_log\')', 'mdi-tag-remove', 14, 20, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (22, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Erase logs', 'delAll(\'/system/delete_all_operator_log\')', 'mdi-delete-circle', 15, 20, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (23, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Database backup', '/admin/database_bak/list', 'mdi-database', 16, 2, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (24, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Backup', 'add(\'/admin/database_bak/add\')', 'mdi-database-plus', 17, 23, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (25, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/database_bak/delete\')', 'mdi-database-minus', 18, 23, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (26, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Restore', 'restore(\'/admin/database_bak/restore\')', 'mdi-database-minus', 19, 23, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (27, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Item management', '/admin/goods_category/list', 'mdi-dialpad', 0, NULL, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (28, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Categary', '/admin/goods_category/list', 'mdi-apps', 0, 27, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (29, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Item management', '/admin/goods/list', 'mdi-cart', 0, 27, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (30, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/goods_category/add', 'mdi-battery-positive', 0, 28, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (31, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/goods_category/edit\')', 'mdi-border-color', 0, 28, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (32, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/goods_category/delete\')', 'mdi-close', 0, 28, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (33, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Place item', 'up(\'/admin/goods/up_down\')', 'mdi-arrow-up-bold-box', 0, 29, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (34, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Withdraw item', 'down(\'/admin/goods/up_down\')', 'mdi-arrow-down-bold-box', 0, 29, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (35, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/goods/delete\')', 'mdi-close-box', 0, 29, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (36, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Recommend', 'recommend(\'/admin/goods/recommend\')', 'mdi-thumb-up', 0, 29, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (37, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Unrecommend', 'unrecommend(\'/admin/goods/recommend\')', 'mdi-thumb-down', 0, 29, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (38, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Request Item', '/admin/wanted_goods/list', 'mdi-ticket', 0, 27, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (39, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/wanted_goods/delete\')', 'mdi-close-box', 0, 38, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (40, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Student management', '/admin/student/list', 'mdi-account-multiple', 0, NULL, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (41, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Student list', '/admin/student/list', 'mdi-account-multiple', 0, 40, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (42, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Freeze account', 'freeze(\'/admin/student/update_status\')', 'mdi-account-settings-variant', 0, 41, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (43, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Activate account', 'openStudent(\'/admin/student/update_status\')', 'mdi-account-key', 0, 41, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (44, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/student/delete\')', 'mdi-account-remove', 0, 41, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (45, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Comment management', '/admin/comment/list', 'mdi-comment-account', 0, NULL, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (46, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Comment list', '/admin/comment/list', 'mdi-comment-multiple-outline', 0, 45, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (47, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/comment/delete\')', 'mdi-message-bulleted-off', 0, 46, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (48, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Report management', '/admin/report/list', 'mdi-alert', 0, NULL, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (49, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Report list', '/admin/report/list', 'mdi-view-headline', 0, 48, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (50, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/report/delete\')', 'mdi-close-box', 0, 49, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (51, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'News', '/admin/news/list', 'mdi-onenote', 0, NULL, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (52, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'News list', '/admin/news/list', 'mdi-book-open', 0, 51, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (53, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/news/add', 'mdi-plus', 0, 52, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (54, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/news/edit\')', 'mdi-border-color', 0, 52, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (55, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/news/delete\')', 'mdi-close', 0, 52, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (56, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Site setting', '', 'mdi-settings', 0, NULL, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (57, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Friendly links', '/admin/friend_link/list', 'mdi-vector-line', 0, 56, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (58, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Create', '/admin/friend_link/add', 'mdi-plus', 0, 57, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (59, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Edit', 'edit(\'/admin/friend_link/edit\')', 'mdi-border-color', 0, 57, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (60, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Delete', 'del(\'/admin/friend_link/delete\')', 'mdi-close-box', 0, 57, b'1', b'1');
INSERT INTO `fyp_menu` VALUES (61, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Site setting', '/admin/site_setting/setting', 'mdi-wrench', 0, 56, b'0', b'1');
INSERT INTO `fyp_menu` VALUES (62, '2023-11-06 21:26:11', '2023-11-06 21:26:34', 'Save changes', '/admin/site_setting/save_setting', 'mdi-marker-check', 0, 61, b'1', b'0');

-- ----------------------------
-- Table structure for fyp_news
-- ----------------------------
DROP TABLE IF EXISTS `fyp_news`;
CREATE TABLE `fyp_news`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `content` varchar(10024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sort` int NOT NULL,
  `title` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `view_number` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_news
-- ----------------------------
INSERT INTO `fyp_news` VALUES (2, '2022-03-20 21:29:49', '2024-03-11 01:49:13', 'something', 0, 'MU-Martet trading policy', 19);
INSERT INTO `fyp_news` VALUES (3, '2022-03-20 21:30:22', '2024-03-11 01:49:20', 'something', 1, 'MU-Market user policy', 7);

-- ----------------------------
-- Table structure for fyp_notice
-- ----------------------------
DROP TABLE IF EXISTS `fyp_notice`;
CREATE TABLE `fyp_notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `receiver_id` bigint NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_read` int NULL DEFAULT NULL COMMENT '已读状态：0-未读，1-已读',
  `send_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fyp_notice
-- ----------------------------
INSERT INTO `fyp_notice` VALUES (3, 1, '12323', '2024-03-12 20:26:31', '2024-03-12 20:26:31', 0, NULL);

-- ----------------------------
-- Table structure for fyp_operater_log
-- ----------------------------
DROP TABLE IF EXISTS `fyp_operater_log`;
CREATE TABLE `fyp_operater_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `operator` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 616 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of fyp_operater_log
-- ----------------------------
INSERT INTO `fyp_operater_log` VALUES (519, '2023-11-07 06:31:21', '2023-11-07 06:31:21', 'User【admin】 at 【2023-11-06 22:31:21】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (520, '2023-11-07 06:53:24', '2023-11-07 06:53:24', 'User【admin】 at 【2023-11-06 22:53:23】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (521, '2023-11-07 06:53:53', '2023-11-07 06:53:53', 'User【admin】 at 【2023-11-06 22:53:52】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (522, '2023-11-07 07:03:26', '2023-11-07 07:03:26', 'edited menu information【Menu [name=Upload image, parent=Menu [name=null, parent=null, url=null, icon=null, sort=0, isButton=false, isShow=true], url=/admin/upload/upload_photo, icon=mdi-arrow-up-bold-circle, sort=0, isButton=true, isShow=true]】', 'admin');
INSERT INTO `fyp_operater_log` VALUES (523, '2023-11-07 07:35:50', '2023-11-07 07:35:50', 'User【admin】 at 【2023-11-06 23:35:50】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (524, '2023-11-10 06:05:54', '2023-11-10 06:05:54', 'User【admin】 at 【2023-11-09 22:05:53】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (526, '2023-11-10 06:25:33', '2023-11-10 06:25:33', 'User【admin】 at 【2023-11-09 22:25:32】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (527, '2023-11-10 06:26:05', '2023-11-10 06:26:05', 'User【admin】 at 【2023-11-09 22:26:04】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (528, '2023-11-10 06:38:33', '2023-11-10 06:38:33', 'User【admin】 at 【2023-11-09 22:38:33】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (529, '2023-11-13 00:51:27', '2023-11-13 00:51:27', 'User【admin】 at 【2023-11-12 16:51:27】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (530, '2023-11-13 00:51:51', '2023-11-13 00:51:51', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112165150.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (531, '2023-11-13 00:52:29', '2023-11-13 00:52:29', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112165229.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (532, '2023-11-13 00:52:55', '2023-11-13 00:52:55', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112165254.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (533, '2023-11-13 01:01:15', '2023-11-13 01:01:15', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112170115.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (534, '2023-11-13 01:03:04', '2023-11-13 01:03:04', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112170303.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (535, '2023-11-13 01:08:37', '2023-11-13 01:08:37', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112170837.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (536, '2023-11-13 01:09:25', '2023-11-13 01:09:25', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112170924.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (537, '2023-11-13 01:11:15', '2023-11-13 01:11:15', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112171115.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (538, '2023-11-13 01:11:37', '2023-11-13 01:11:37', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112171136.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (539, '2023-11-13 01:11:47', '2023-11-13 01:11:47', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112171147.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (540, '2023-11-13 01:12:16', '2023-11-13 01:12:16', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112171216.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (541, '2023-11-13 01:21:09', '2023-11-13 01:21:09', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112172108.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (542, '2023-11-13 01:21:58', '2023-11-13 01:21:58', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112172158.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (543, '2023-11-13 01:22:05', '2023-11-13 01:22:05', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112172204.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (544, '2023-11-13 01:22:06', '2023-11-13 01:22:06', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112172206.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (545, '2023-11-13 01:22:09', '2023-11-13 01:22:09', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112172208.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (546, '2023-11-13 01:22:10', '2023-11-13 01:22:10', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112172210.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (547, '2023-11-13 01:31:50', '2023-11-13 01:31:50', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112173150.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (548, '2023-11-13 01:31:57', '2023-11-13 01:31:57', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_base_20231112173156.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (549, '2023-11-13 01:35:29', '2023-11-13 01:35:29', 'Database backup successfully, backup file：DatabaseBak [filename=db_boot_bak_20231112173528.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (550, '2023-11-13 01:39:03', '2023-11-13 01:39:03', 'Database backup successfully, backup file：DatabaseBak [filename=db_campus_market_20231112173903.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (551, '2023-11-13 01:44:10', '2023-11-13 01:44:10', 'Database backup successfully, backup file：DatabaseBak [filename=db_campus_market_20231112174410.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (552, '2023-11-20 19:24:08', '2023-11-20 19:24:08', 'User【admin】 at 【2023-11-20 11:24:08】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (553, '2023-11-20 19:38:37', '2023-11-20 19:38:37', 'User【admin】 at 【2023-11-20 11:38:37】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (554, '2023-11-20 19:51:48', '2023-11-20 19:51:48', 'User【admin】 at 【2023-11-20 11:51:48】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (555, '2023-11-21 05:37:35', '2023-11-21 05:37:35', 'User【admin】 at 【2023-11-20 21:37:34】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (556, '2023-11-21 06:34:30', '2023-11-21 06:34:30', 'User【admin】 at 【2023-11-20 22:34:30】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (557, '2023-11-21 07:42:18', '2023-11-21 07:42:18', 'User【admin】 at 【2023-11-20 23:42:18】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (558, '2023-11-28 08:15:55', '2023-11-28 08:15:55', 'User【admin】 at 【2023-11-28 00:15:54】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (559, '2023-11-28 08:15:55', '2023-11-28 08:15:55', 'User【admin】 at 【2023-11-28 00:15:54】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (560, '2023-12-04 02:33:08', '2023-12-04 02:33:08', 'User【admin】 at 【2023-12-03 18:33:07】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (561, '2023-12-04 02:33:57', '2023-12-04 02:33:57', 'Edit role【Super Admin】', 'admin');
INSERT INTO `fyp_operater_log` VALUES (562, '2023-12-04 02:42:51', '2023-12-04 02:42:51', 'User【admin】 at 【2023-12-03 18:42:50】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (565, '2023-12-04 02:57:07', '2023-12-04 02:57:07', 'User【admin】 at 【2023-12-03 18:57:07】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (566, '2023-12-04 02:59:51', '2023-12-04 02:59:51', 'User【admin】 at 【2023-12-03 18:59:50】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (567, '2023-12-04 03:01:06', '2023-12-04 03:01:06', 'User【admin】 at 【2023-12-03 19:01:05】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (568, '2023-12-04 03:02:28', '2023-12-04 03:02:28', 'User【admin】 at 【2023-12-03 19:02:28】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (569, '2023-12-04 03:48:31', '2023-12-04 03:48:31', 'User【admin】 at 【2023-12-03 19:48:31】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (570, '2023-12-09 03:24:28', '2023-12-09 03:24:28', 'User【admin】 at 【2023-12-08 19:24:27】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (571, '2023-12-09 03:25:15', '2023-12-09 03:25:15', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20210223/1614075844201.png, allRights=MU-Market, logo1=20210307/1615096736988.png, logo2=20200418/1587201663687.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (572, '2023-12-09 03:25:23', '2023-12-09 03:25:23', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20210223/1614075844201.png, allRights=MU-Market, logo1=20210307/1615096736988.png, logo2=20200418/1587201663687.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (573, '2023-12-09 03:25:41', '2023-12-09 03:25:41', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20210223/1614075844201.png, allRights=MU-Market, logo1=20210307/1615096736988.png, logo2=20200418/1587201663687.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (574, '2023-12-09 03:44:04', '2023-12-09 03:44:04', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20210223/1614075844201.png, allRights=MU-Market, logo1=20231208/1702064640864.png, logo2=20200418/1587201663687.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (575, '2023-12-09 03:44:33', '2023-12-09 03:44:33', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20210223/1614075844201.png, allRights=MU-Market, logo1=20231208/1702064640864.png, logo2=20200418/1587201663687.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (576, '2023-12-09 03:45:06', '2023-12-09 03:45:06', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20210223/1614075844201.png, allRights=MU-Market, logo1=20231208/1702064640864.png, logo2=20231208/1702064703244.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (577, '2023-12-09 03:45:23', '2023-12-09 03:45:23', 'User【admin】 at 【2023-12-08 19:45:23】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (578, '2023-12-16 00:26:44', '2023-12-16 00:26:44', 'User【admin】 at 【2023-12-15 16:26:44】 Logined in to the system！', 'admin');
INSERT INTO `fyp_operater_log` VALUES (579, '2023-12-16 00:27:18', '2023-12-16 00:27:18', 'Added site:FriendLink [name=Maynooth universityy, url=https://www.maynoothuniversity.ie/, sort=0]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (580, '2023-12-16 00:27:27', '2023-12-16 00:27:27', 'Edited site URL：FriendLink [name=Maynooth universityy, url=https://www.maynoothuniversity.ie/, sort=0]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (581, '2023-12-16 00:27:54', '2023-12-16 00:27:54', 'Added site:FriendLink [name=Moodle, url=https://moodle.maynoothuniversity.ie/login/index.php, sort=0]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (582, '2023-12-19 03:26:08', '2023-12-19 03:26:08', 'User [admin] at  [2023-12-18 19:26:07] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (583, '2023-12-19 03:27:04', '2023-12-19 03:27:04', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20231218/1702927621494.png, allRights=MU-Market, logo1=20231218/1702927585492.png, logo2=20231218/1702927615687.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (584, '2024-02-21 04:25:06', '2024-02-21 04:25:06', 'User [admin] at  [2024-02-20 20:25:06] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (585, '2024-02-21 04:26:05', '2024-02-21 04:26:05', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20240220/1708460761925.png, allRights=MU-Market, logo1=20240220/1708460754419.png, logo2=20240220/1708460758617.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (586, '2024-02-21 04:26:31', '2024-02-21 04:26:31', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20240220/1708460761925.png, allRights=MU-Market, logo1=20240220/1708460754419.png, logo2=20240220/1708460758617.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (587, '2024-02-22 07:29:10', '2024-02-22 07:29:10', 'User [admin] at  [2024-02-21 23:29:10] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (588, '2024-02-22 07:37:41', '2024-02-22 07:37:41', 'User [admin] at  [2024-02-21 23:37:41] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (589, '2024-02-27 01:52:58', '2024-02-27 01:52:58', 'User [admin] at  [2024-02-26 17:52:57] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (590, '2024-02-27 05:34:36', '2024-02-27 05:34:36', 'User [admin] at  [2024-02-26 21:34:36] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (591, '2024-02-29 01:58:25', '2024-02-29 01:58:25', 'User [admin] at  [2024-02-28 17:58:24] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (592, '2024-02-29 02:03:25', '2024-02-29 02:03:25', 'User [admin] at  [2024-02-28 18:03:24] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (593, '2024-02-29 02:32:26', '2024-02-29 02:32:26', 'User [admin] at  [2024-02-28 18:32:25] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (594, '2024-03-01 09:04:09', '2024-03-01 09:04:09', 'User [admin] at  [2024-03-01 01:04:08] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (595, '2024-03-01 09:05:13', '2024-03-01 09:05:13', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20240220/1708460761925.png, allRights=MU-Market, logo1=20240220/1708460754419.png, logo2=20240301/1709255105124.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (596, '2024-03-01 09:05:37', '2024-03-01 09:05:37', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20240220/1708460761925.png, allRights=MU-Market, logo1=20240301/1709255131654.png, logo2=20240301/1709255128377.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (597, '2024-03-01 09:08:16', '2024-03-01 09:08:16', 'Edited site settingSiteSetting [siteName=MU-Market, siteUrl=MUMarket.com, qrcode=20240220/1708460761925.png, allRights=MU-Market, logo1=20240301/1709255293816.png, logo2=20240301/1709255128377.png]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (598, '2024-03-01 23:09:05', '2024-03-01 23:09:05', 'User [admin] at  [2024-03-01 15:09:04] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (599, '2024-03-01 23:17:27', '2024-03-01 23:17:27', 'Database backup successfully, backup file：DatabaseBak [filename=db_campus_market_20240301151727.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (600, '2024-03-01 23:20:15', '2024-03-01 23:20:15', 'Database restore successfully, restore file：DatabaseBak [filename=db_campus_market_20240301151727.sql, filepath=D:\\dev\\FYP\\mumarket\\Mu-Market\\src\\main\\resources\\backup/]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (601, '2024-03-01 23:21:37', '2024-03-01 23:21:37', 'User [admin] at  [2024-03-01 15:21:37] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (602, '2024-03-02 00:01:39', '2024-03-02 00:01:39', 'User [admin] at  [2024-03-01 16:01:38] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (603, '2024-03-03 05:02:52', '2024-03-03 05:02:52', 'User [admin] at  [2024-03-02 21:02:51] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (604, '2024-03-04 21:14:06', '2024-03-04 21:14:06', 'User [admin] at  [2024-03-04 13:14:05] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (605, '2024-03-04 21:51:58', '2024-03-04 21:51:58', 'User [admin] at  [2024-03-04 13:51:58] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (606, '2024-03-06 01:43:26', '2024-03-06 01:43:26', 'User [admin] at  [2024-03-05 17:43:25] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (607, '2024-03-06 01:43:35', '2024-03-06 01:43:35', 'Edited site URL：FriendLink [name=Maynooth university, url=https://www.maynoothuniversity.ie/, sort=0]', 'admin');
INSERT INTO `fyp_operater_log` VALUES (608, '2024-03-06 01:46:34', '2024-03-06 01:46:34', 'User [admin] at  [2024-03-05 17:46:33] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (609, '2024-03-06 01:48:21', '2024-03-06 01:48:21', 'User [admin] at  [2024-03-05 17:48:21] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (610, '2024-03-06 02:20:28', '2024-03-06 02:20:28', 'User [admin] at  [2024-03-05 18:20:28] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (611, '2024-03-06 02:21:47', '2024-03-06 02:21:47', 'User [admin] at  [2024-03-05 18:21:47] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (612, '2024-03-06 21:37:05', '2024-03-06 21:37:05', 'User [admin] at  [2024-03-06 13:37:04] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (613, '2024-03-08 02:43:12', '2024-03-08 02:43:12', 'User [admin] at  [2024-03-07 18:43:12] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (614, '2024-03-08 02:46:31', '2024-03-08 02:46:31', 'User [admin] at  [2024-03-07 18:46:31] Logined in to the system!', 'admin');
INSERT INTO `fyp_operater_log` VALUES (615, '2024-03-11 01:49:36', '2024-03-11 01:49:36', 'User [admin] at  [2024-03-10 17:49:36] Logined in to the system!', 'admin');

-- ----------------------------
-- Table structure for fyp_report_goods
-- ----------------------------
DROP TABLE IF EXISTS `fyp_report_goods`;
CREATE TABLE `fyp_report_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `goods_id` bigint NULL DEFAULT NULL,
  `student_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK1eve16ebpuhvym71thhnx8qv`(`goods_id` ASC) USING BTREE,
  INDEX `FKsvhhes59oocag8r9khs753lp4`(`student_id` ASC) USING BTREE,
  CONSTRAINT `fyp_report_goods_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `fyp_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fyp_report_goods_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `fyp_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_report_goods
-- ----------------------------
INSERT INTO `fyp_report_goods` VALUES (2, '2024-03-06 21:36:47', '2024-03-06 21:36:47', 'bad', 8, 2);

-- ----------------------------
-- Table structure for fyp_role
-- ----------------------------
DROP TABLE IF EXISTS `fyp_role`;
CREATE TABLE `fyp_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `remark` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of fyp_role
-- ----------------------------
INSERT INTO `fyp_role` VALUES (1, '2020-03-15 13:16:38', '2023-12-04 02:33:57', 'Super Admin', 'Super admin holding ALL permission', 1);
INSERT INTO `fyp_role` VALUES (2, '2020-03-15 13:18:57', '2020-03-21 22:18:43', 'Admin', 'Admin holding particular permission', 1);
INSERT INTO `fyp_role` VALUES (4, '2020-03-21 20:11:00', '2020-03-23 17:49:00', 'Test role', 'A test role', 0);

-- ----------------------------
-- Table structure for fyp_role_authorities
-- ----------------------------
DROP TABLE IF EXISTS `fyp_role_authorities`;
CREATE TABLE `fyp_role_authorities`  (
  `role_id` bigint NOT NULL,
  `authorities_id` bigint NOT NULL,
  INDEX `FKhj7ap1o1cjrl7enr9arf5f2qp`(`authorities_id` ASC) USING BTREE,
  INDEX `FKg3xdaexmr0x1qx8omhvjtk46d`(`role_id` ASC) USING BTREE,
  CONSTRAINT `fyp_role_authorities_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `fyp_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fyp_role_authorities_ibfk_2` FOREIGN KEY (`authorities_id`) REFERENCES `fyp_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of fyp_role_authorities
-- ----------------------------
INSERT INTO `fyp_role_authorities` VALUES (2, 2);
INSERT INTO `fyp_role_authorities` VALUES (2, 3);
INSERT INTO `fyp_role_authorities` VALUES (2, 5);
INSERT INTO `fyp_role_authorities` VALUES (2, 7);
INSERT INTO `fyp_role_authorities` VALUES (2, 11);
INSERT INTO `fyp_role_authorities` VALUES (2, 13);
INSERT INTO `fyp_role_authorities` VALUES (2, 16);
INSERT INTO `fyp_role_authorities` VALUES (4, 2);
INSERT INTO `fyp_role_authorities` VALUES (4, 13);
INSERT INTO `fyp_role_authorities` VALUES (4, 15);
INSERT INTO `fyp_role_authorities` VALUES (1, 2);
INSERT INTO `fyp_role_authorities` VALUES (1, 3);
INSERT INTO `fyp_role_authorities` VALUES (1, 5);
INSERT INTO `fyp_role_authorities` VALUES (1, 8);
INSERT INTO `fyp_role_authorities` VALUES (1, 9);
INSERT INTO `fyp_role_authorities` VALUES (1, 7);
INSERT INTO `fyp_role_authorities` VALUES (1, 10);
INSERT INTO `fyp_role_authorities` VALUES (1, 11);
INSERT INTO `fyp_role_authorities` VALUES (1, 12);
INSERT INTO `fyp_role_authorities` VALUES (1, 13);
INSERT INTO `fyp_role_authorities` VALUES (1, 14);
INSERT INTO `fyp_role_authorities` VALUES (1, 15);
INSERT INTO `fyp_role_authorities` VALUES (1, 16);
INSERT INTO `fyp_role_authorities` VALUES (1, 19);
INSERT INTO `fyp_role_authorities` VALUES (1, 20);
INSERT INTO `fyp_role_authorities` VALUES (1, 21);
INSERT INTO `fyp_role_authorities` VALUES (1, 22);
INSERT INTO `fyp_role_authorities` VALUES (1, 23);
INSERT INTO `fyp_role_authorities` VALUES (1, 24);
INSERT INTO `fyp_role_authorities` VALUES (1, 25);
INSERT INTO `fyp_role_authorities` VALUES (1, 26);
INSERT INTO `fyp_role_authorities` VALUES (1, 27);
INSERT INTO `fyp_role_authorities` VALUES (1, 28);
INSERT INTO `fyp_role_authorities` VALUES (1, 30);
INSERT INTO `fyp_role_authorities` VALUES (1, 31);
INSERT INTO `fyp_role_authorities` VALUES (1, 32);
INSERT INTO `fyp_role_authorities` VALUES (1, 29);
INSERT INTO `fyp_role_authorities` VALUES (1, 33);
INSERT INTO `fyp_role_authorities` VALUES (1, 34);
INSERT INTO `fyp_role_authorities` VALUES (1, 35);
INSERT INTO `fyp_role_authorities` VALUES (1, 36);
INSERT INTO `fyp_role_authorities` VALUES (1, 37);
INSERT INTO `fyp_role_authorities` VALUES (1, 38);
INSERT INTO `fyp_role_authorities` VALUES (1, 39);
INSERT INTO `fyp_role_authorities` VALUES (1, 40);
INSERT INTO `fyp_role_authorities` VALUES (1, 41);
INSERT INTO `fyp_role_authorities` VALUES (1, 42);
INSERT INTO `fyp_role_authorities` VALUES (1, 43);
INSERT INTO `fyp_role_authorities` VALUES (1, 44);
INSERT INTO `fyp_role_authorities` VALUES (1, 45);
INSERT INTO `fyp_role_authorities` VALUES (1, 46);
INSERT INTO `fyp_role_authorities` VALUES (1, 47);
INSERT INTO `fyp_role_authorities` VALUES (1, 48);
INSERT INTO `fyp_role_authorities` VALUES (1, 49);
INSERT INTO `fyp_role_authorities` VALUES (1, 50);
INSERT INTO `fyp_role_authorities` VALUES (1, 51);
INSERT INTO `fyp_role_authorities` VALUES (1, 52);
INSERT INTO `fyp_role_authorities` VALUES (1, 53);
INSERT INTO `fyp_role_authorities` VALUES (1, 54);
INSERT INTO `fyp_role_authorities` VALUES (1, 55);
INSERT INTO `fyp_role_authorities` VALUES (1, 56);
INSERT INTO `fyp_role_authorities` VALUES (1, 57);
INSERT INTO `fyp_role_authorities` VALUES (1, 58);
INSERT INTO `fyp_role_authorities` VALUES (1, 59);
INSERT INTO `fyp_role_authorities` VALUES (1, 60);
INSERT INTO `fyp_role_authorities` VALUES (1, 61);
INSERT INTO `fyp_role_authorities` VALUES (1, 62);

-- ----------------------------
-- Table structure for fyp_site_setting
-- ----------------------------
DROP TABLE IF EXISTS `fyp_site_setting`;
CREATE TABLE `fyp_site_setting`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of fyp_site_setting
-- ----------------------------
INSERT INTO `fyp_site_setting` VALUES (1, '2023-11-06 17:02:17', '2024-03-01 09:08:16', 'MU-Market', '20240301/1709255293816.png', '20240301/1709255128377.png', '20240220/1708460761925.png', 'MU-Market', 'MUMarket.com');

-- ----------------------------
-- Table structure for fyp_student
-- ----------------------------
DROP TABLE IF EXISTS `fyp_student`;
CREATE TABLE `fyp_student`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `academy` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `grade` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `head_pic` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `mobile` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `nickname` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `school` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sn` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` int NULL DEFAULT NULL,
  `qq` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_e63ucar64v05lc4uni6hagqci`(`sn` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_student
-- ----------------------------
INSERT INTO `fyp_student` VALUES (1, '2023-11-20 22:05:29', '2023-11-21 07:51:38', 'Maynooth University', 'Year 4', '20231109/1699569178075.png', '123456', 'Zhu', '123456', 'CSSE', '20322243', 1, NULL);
INSERT INTO `fyp_student` VALUES (2, '2024-02-23 07:45:09', '2024-03-06 01:30:34', 'Maynoothh', '44', '20240305/1709659833550.jpg', '08332023022', 'Zhuu', '88888888', 'CSSEE', '20322253', 1, NULL);
INSERT INTO `fyp_student` VALUES (3, '2024-03-04 21:10:55', '2024-03-04 21:50:11', 'Maynooth', '4', NULL, '0833202302', 'Huang', '88888888', 'CSSE', '20322254', 1, NULL);

-- ----------------------------
-- Table structure for fyp_user
-- ----------------------------
DROP TABLE IF EXISTS `fyp_user`;
CREATE TABLE `fyp_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `email` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `head_pic` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `mobile` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `username` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `role_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_btsosjytrl4hu7fnm1intcpo8`(`username` ASC) USING BTREE,
  INDEX `FKg09b8o67eu61st68rv6nk8npj`(`role_id` ASC) USING BTREE,
  CONSTRAINT `fyp_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `fyp_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of fyp_user
-- ----------------------------
INSERT INTO `fyp_user` VALUES (1, '2023-11-06 22:30:17', '2023-11-10 06:32:44', 'admin@mail.com', '20231109/1699569162551.png', '123456', '123456', 1, 1, 'admin', 1);

-- ----------------------------
-- Table structure for fyp_users_bidding
-- ----------------------------
DROP TABLE IF EXISTS `fyp_users_bidding`;
CREATE TABLE `fyp_users_bidding`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `bidding_id` bigint NULL DEFAULT NULL,
  `bidding_price` int NULL DEFAULT NULL COMMENT 'Bidding price',
  `create_time` datetime NULL DEFAULT NULL,
  `uppdate_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fyp_users_bidding
-- ----------------------------
INSERT INTO `fyp_users_bidding` VALUES (2, 3, 4, 10000, '2024-03-12 23:47:39', NULL, '2024-03-12 23:47:39', 'Huang');

-- ----------------------------
-- Table structure for fyp_wanted_goods
-- ----------------------------
DROP TABLE IF EXISTS `fyp_wanted_goods`;
CREATE TABLE `fyp_wanted_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sell_price` float NOT NULL,
  `trade_place` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `view_number` int NOT NULL,
  `student_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKl7jrltb5cvwvfk46lstodq4sb`(`student_id` ASC) USING BTREE,
  CONSTRAINT `fyp_wanted_goods_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `fyp_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fyp_wanted_goods
-- ----------------------------
INSERT INTO `fyp_wanted_goods` VALUES (1, '2024-03-04 20:26:09', '2024-03-04 20:26:09', 'I want a gaming keyboard with RGB and in good condiction', 'RGB Gaming key board', 50, 'South Campus', 0, 2);

SET FOREIGN_KEY_CHECKS = 1;
