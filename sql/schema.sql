/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : travel_assistant

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 08/06/2019 22:32:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT 'md5加密后的密码',
  `last_time` timestamp NULL DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- ----------------------------
-- Records of login
-- ----------------------------
BEGIN;
INSERT INTO `login` VALUES ('admin', '1130e637df92a729bc898b794eb7a117', '2019-05-06 11:40:54');
INSERT INTO `login` VALUES ('stdwal', '8a9c9c87023a3ac44fc22eb92deaa335', '2019-05-28 17:41:54');
COMMIT;

-- ----------------------------
-- Table structure for place_model
-- ----------------------------
DROP TABLE IF EXISTS `place_model`;
CREATE TABLE `place_model` (
  `city_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市编码',
  `city` varchar(20) NOT NULL COMMENT '城市',
  `place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '景点',
  `is_hot` int(10) DEFAULT '0' COMMENT '是否热门',
  `is_natural` int(10) DEFAULT '0' COMMENT '是否自然景观',
  `is_human` int(10) DEFAULT '0' COMMENT '是否人文景观',
  `is_business` int(10) DEFAULT '0' COMMENT '是否商业',
  PRIMARY KEY (`city_code`,`place`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of place_model
-- ----------------------------
BEGIN;
INSERT INTO `place_model` VALUES ('010', '北京', '圆明园', 1, 0, 1, 0);
INSERT INTO `place_model` VALUES ('010', '北京', '故宫博物馆', 1, 0, 1, 0);
INSERT INTO `place_model` VALUES ('010', '北京', '清华大学', 1, 0, 1, 0);
INSERT INTO `place_model` VALUES ('010', '北京', '钓鱼台国宾馆', 0, 0, 1, 0);
INSERT INTO `place_model` VALUES ('020', '上海', '上海环贸iapm商场', 1, 0, 0, 1);
INSERT INTO `place_model` VALUES ('020', '上海', '外滩', 1, 0, 1, 1);
INSERT INTO `place_model` VALUES ('020', '上海', '新天地时尚·购物中心', 0, 0, 0, 1);
INSERT INTO `place_model` VALUES ('020', '上海', '静安寺', 0, 0, 1, 0);
INSERT INTO `place_model` VALUES ('025', '南京', '南京雨花台风景区', 0, 1, 1, 0);
INSERT INTO `place_model` VALUES ('025', '南京', '夫子庙', 1, 0, 1, 1);
INSERT INTO `place_model` VALUES ('025', '南京', '总统府', 1, 0, 1, 0);
INSERT INTO `place_model` VALUES ('027', '武汉', '光谷', 1, 0, 0, 1);
INSERT INTO `place_model` VALUES ('027', '武汉', '黄鹤楼公园', 1, 0, 1, 0);
INSERT INTO `place_model` VALUES ('028', '成都', '成都武侯祠博物馆', 0, 0, 1, 0);
INSERT INTO `place_model` VALUES ('028', '成都', '成都远洋太古里', 1, 0, 0, 1);
INSERT INTO `place_model` VALUES ('028', '成都', '春熙路', 1, 0, 0, 1);
INSERT INTO `place_model` VALUES ('0512', '苏州', '拙政园', 1, 0, 1, 0);
INSERT INTO `place_model` VALUES ('0512', '苏州', '金鸡湖景区', 1, 1, 0, 1);
INSERT INTO `place_model` VALUES ('0532', '青岛', '中山公园', 1, 1, 1, 0);
INSERT INTO `place_model` VALUES ('0532', '青岛', '五四广场', 1, 0, 1, 1);
INSERT INTO `place_model` VALUES ('0532', '青岛', '八大关风景区', 1, 0, 1, 0);
INSERT INTO `place_model` VALUES ('0532', '青岛', '崂山风景区', 1, 1, 1, 0);
INSERT INTO `place_model` VALUES ('0571', '杭州', '杭州西湖风景名胜区', 1, 1, 1, 0);
INSERT INTO `place_model` VALUES ('0571', '杭州', '武林广场', 1, 0, 0, 1);
INSERT INTO `place_model` VALUES ('0574', '宁波', '天一广场', 1, 0, 0, 1);
INSERT INTO `place_model` VALUES ('0574', '宁波', '天一阁', 0, 0, 1, 0);
INSERT INTO `place_model` VALUES ('0574', '宁波', '宁波老外滩', 1, 0, 1, 1);
INSERT INTO `place_model` VALUES ('0574', '宁波', '鼓楼步行街', 1, 0, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for travel_plan
-- ----------------------------
DROP TABLE IF EXISTS `travel_plan`;
CREATE TABLE `travel_plan` (
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `city_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市编码',
  `city` varchar(20) NOT NULL COMMENT '城市',
  `place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '景点',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_name`,`city_code`,`place`) USING BTREE,
  CONSTRAINT `travel_plan_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `login` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户旅行计划表';

-- ----------------------------
-- Records of travel_plan
-- ----------------------------
BEGIN;
INSERT INTO `travel_plan` VALUES ('admin', '010', '北京', '圆明园', '2019-05-27 23:20:56');
INSERT INTO `travel_plan` VALUES ('admin', '010', '北京', '故宫博物院', '2019-05-27 23:13:14');
INSERT INTO `travel_plan` VALUES ('admin', '010', '北京', '清华大学', '2019-05-27 23:17:35');
INSERT INTO `travel_plan` VALUES ('admin', '010', '北京', '钓鱼台国宾馆', '2019-05-27 23:19:08');
INSERT INTO `travel_plan` VALUES ('admin', '020', '上海', '上海环贸iapm商场', '2019-05-12 20:50:13');
INSERT INTO `travel_plan` VALUES ('admin', '020', '上海', '外滩', '2019-05-10 13:52:28');
INSERT INTO `travel_plan` VALUES ('admin', '020', '上海', '新天地时尚·购物中心', '2019-05-13 13:11:13');
INSERT INTO `travel_plan` VALUES ('admin', '020', '上海', '静安寺', '2019-05-14 08:31:47');
INSERT INTO `travel_plan` VALUES ('admin', '025', '南京', '南京雨花台风景区', '2019-05-13 17:23:58');
INSERT INTO `travel_plan` VALUES ('admin', '025', '南京', '夫子庙', '2019-05-13 17:23:22');
INSERT INTO `travel_plan` VALUES ('admin', '025', '南京', '总统府', '2019-05-13 17:24:10');
INSERT INTO `travel_plan` VALUES ('admin', '025', '南京', '新街口', '2019-05-23 23:03:13');
INSERT INTO `travel_plan` VALUES ('admin', '025', '南京', '白马公园', '2019-05-23 22:57:27');
INSERT INTO `travel_plan` VALUES ('admin', '027', '武汉', '光谷', '2019-05-27 21:24:05');
INSERT INTO `travel_plan` VALUES ('admin', '027', '武汉', '湖北美术学院(藏龙岛校区)', '2019-05-27 21:24:10');
INSERT INTO `travel_plan` VALUES ('admin', '027', '武汉', '黄鹤楼公园', '2019-05-27 21:24:15');
INSERT INTO `travel_plan` VALUES ('admin', '028', '成都', '成都武侯祠博物馆', '2019-05-27 21:26:15');
INSERT INTO `travel_plan` VALUES ('admin', '028', '成都', '成都远洋太古里', '2019-05-27 21:25:56');
INSERT INTO `travel_plan` VALUES ('admin', '028', '成都', '春熙路', '2019-05-27 21:25:49');
INSERT INTO `travel_plan` VALUES ('admin', '0512', '苏州', '拙政园', '2019-05-28 18:11:21');
INSERT INTO `travel_plan` VALUES ('admin', '0512', '苏州', '金鸡湖景区', '2019-05-28 18:10:13');
INSERT INTO `travel_plan` VALUES ('admin', '0532', '青岛', '中山公园', '2019-05-15 09:35:32');
INSERT INTO `travel_plan` VALUES ('admin', '0532', '青岛', '五四广场', '2019-05-03 15:22:10');
INSERT INTO `travel_plan` VALUES ('admin', '0532', '青岛', '八大关风景区', '2019-05-14 10:17:05');
INSERT INTO `travel_plan` VALUES ('admin', '0532', '青岛', '崂山风景区', '2019-05-28 20:14:00');
INSERT INTO `travel_plan` VALUES ('admin', '0532', '青岛', '石老人海水浴场', '2019-05-28 20:15:26');
INSERT INTO `travel_plan` VALUES ('admin', '0532', '青岛', '青岛科技大学(崂山校区)', '2019-05-28 20:13:54');
INSERT INTO `travel_plan` VALUES ('admin', '0571', '杭州', '杭州西湖风景名胜区', '2019-05-27 23:09:35');
INSERT INTO `travel_plan` VALUES ('admin', '0571', '杭州', '武林广场', '2019-05-27 23:09:30');
INSERT INTO `travel_plan` VALUES ('admin', '0574', '宁波', '天一广场', '2019-05-12 18:15:02');
INSERT INTO `travel_plan` VALUES ('admin', '0574', '宁波', '天一阁', '2019-05-04 13:24:44');
INSERT INTO `travel_plan` VALUES ('admin', '0574', '宁波', '宁波老外滩', '2019-04-29 12:07:23');
INSERT INTO `travel_plan` VALUES ('admin', '0574', '宁波', '鼓楼步行街', '2019-05-13 13:10:21');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
