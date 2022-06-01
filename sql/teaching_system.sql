/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : teaching_system

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 30/04/2022 17:48:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for examination
-- ----------------------------
DROP TABLE IF EXISTS `examination`;
CREATE TABLE `examination`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '考试id',
  `teaching_outline_id` int NOT NULL COMMENT '考试大纲id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试描述',
  `difficulty_level` enum('简单','一般','偏难') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '简单' COMMENT '考试难度',
  `suggest_finish_time` int NOT NULL COMMENT '建议完成时长',
  `proportion` decimal(2, 1) NOT NULL COMMENT '统计成绩时，考试占比(0-1)',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `deadline_time` datetime NOT NULL COMMENT '截止时间',
  `status` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '考试状态。0：未发布；1：已发布；2：已截止',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teaching_outline_id`(`teaching_outline_id` ASC) USING BTREE,
  CONSTRAINT `examination_ibfk_1` FOREIGN KEY (`teaching_outline_id`) REFERENCES `teaching_outline` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exercise
-- ----------------------------
DROP TABLE IF EXISTS `exercise`;
CREATE TABLE `exercise`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `question_stem` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题干',
  `option_a` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项A内容',
  `option_b` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项B内容',
  `option_c` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项C内容',
  `option_d` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项D内容',
  `correct_answer` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正确答案',
  `score` int NOT NULL COMMENT '分值',
  `knowledge_point` int NOT NULL COMMENT '知识点id',
  `category` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '0：属于习题下的题目；1：属于考试下的题目。根据这个去关联习题表还是考试表',
  `work_id` int NOT NULL COMMENT '分组作业或者考试id',
  `type` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目类型。0：单选；1：多选',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `knowledge_point`(`knowledge_point` ASC) USING BTREE,
  CONSTRAINT `exercise_ibfk_1` FOREIGN KEY (`knowledge_point`) REFERENCES `knowledge_point` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exercise_complete
-- ----------------------------
DROP TABLE IF EXISTS `exercise_complete`;
CREATE TABLE `exercise_complete`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '习题完成情况id',
  `exercise_id` int NOT NULL COMMENT '习题id',
  `user_id` int NOT NULL COMMENT '用户id',
  `answer` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户填写的答案',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `exercise_id`(`exercise_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `exercise_complete_ibfk_1` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `exercise_complete_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_work
-- ----------------------------
DROP TABLE IF EXISTS `group_work`;
CREATE TABLE `group_work`  (
  `id` int NOT NULL COMMENT '分组作业id',
  `teaching_outline_id` int NOT NULL COMMENT '教学大纲Id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组作业标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组作业描述',
  `difficulty_level` enum('简单','一般','偏难') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '简单' COMMENT '难易程度',
  `suggest_finish_time` int NOT NULL COMMENT '建议完成时长',
  `proportion` decimal(2, 1) NOT NULL COMMENT '作业在统计成绩时所占比重(0-1)',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '作业发布时间',
  `deadline_time` datetime NULL DEFAULT NULL COMMENT '作业截止时间',
  `status` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '作业状态。0-未发布；1-进行中；2-已截止',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teaching_outline_id`(`teaching_outline_id` ASC) USING BTREE,
  CONSTRAINT `group_work_ibfk_1` FOREIGN KEY (`teaching_outline_id`) REFERENCES `teaching_outline` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for knowledge_point
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_point`;
CREATE TABLE `knowledge_point`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '知识点id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '知识点名称',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '知识点内容',
  `support_strength` enum('low','middle','high') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'low' COMMENT '支撑强度。取值为：low、middle、high',
  `teaching_goal_id` int NOT NULL COMMENT '教学目标id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teaching_goal_id`(`teaching_goal_id` ASC) USING BTREE,
  CONSTRAINT `knowledge_point_ibfk_1` FOREIGN KEY (`teaching_goal_id`) REFERENCES `teaching_goal` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teaching_goal
-- ----------------------------
DROP TABLE IF EXISTS `teaching_goal`;
CREATE TABLE `teaching_goal`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '教学目标id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教学目标标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教学目标内容',
  `teaching_outline_id` int NOT NULL COMMENT '教学大纲id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teaching_outline_id`(`teaching_outline_id` ASC) USING BTREE,
  CONSTRAINT `teaching_goal_ibfk_1` FOREIGN KEY (`teaching_outline_id`) REFERENCES `teaching_outline` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teaching_outline
-- ----------------------------
DROP TABLE IF EXISTS `teaching_outline`;
CREATE TABLE `teaching_outline`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '大纲编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '大纲标题',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '大纲版本',
  `uploading_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '大纲上传时间',
  `principal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '大纲负责人',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传的原始文件名',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传后的文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teaching_schedule
-- ----------------------------
DROP TABLE IF EXISTS `teaching_schedule`;
CREATE TABLE `teaching_schedule`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '教学进度编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教学进度标题',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教学进度版本',
  `uploading_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上传教学进度时间',
  `principal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '负责人',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原始文件名称',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传之后文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登陆密码',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份。0：教师；1：学生',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work_statistics
-- ----------------------------
DROP TABLE IF EXISTS `work_statistics`;
CREATE TABLE `work_statistics`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '习题完成情况id',
  `work_id` int NOT NULL COMMENT '习题组或考试id',
  `user_id` int NOT NULL COMMENT '用户id',
  `submit_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '习题提交状态。0：未提交；1：已提交',
  `submit_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '提交时间',
  `correct_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '批改状态。0：未批改；1：已批改',
  `score` int NOT NULL DEFAULT 0 COMMENT '习题分数',
  `category` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '0：习题完成情况：1：考试完成情况',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `work_statistics_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
