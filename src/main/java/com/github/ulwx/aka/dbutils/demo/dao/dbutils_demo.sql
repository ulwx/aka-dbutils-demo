/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.31-log : Database - dbutils_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dbutils_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dbutils_demo`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程id',
                          `name` varchar(20) DEFAULT '' COMMENT '课程名称',
                          `class_hours` int(11) DEFAULT '0' COMMENT '学时',
                          `creatime` datetime DEFAULT NULL COMMENT '建立时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程';

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
                           `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '学生id',
                           `name` varchar(20) DEFAULT '' COMMENT '学生姓名',
                           `age` int(11) DEFAULT '0' COMMENT '年龄',
                           `birth_day` date DEFAULT NULL COMMENT '出生日期',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生';

/*Table structure for table `student_course` */

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course` (
                                  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
                                  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t1` */

DROP TABLE IF EXISTS `t1`;

CREATE TABLE `t1` (
                      `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                      `a` int(11) DEFAULT NULL,
                      `key_b` datetime DEFAULT NULL,
                      `key_c` varchar(30) DEFAULT '',
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `t2` */

DROP TABLE IF EXISTS `t2`;

CREATE TABLE `t2` (
                      `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                      `a` int(11) DEFAULT NULL,
                      `key_a` int(11) DEFAULT '0',
                      `key_b` int(11) DEFAULT '0',
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
