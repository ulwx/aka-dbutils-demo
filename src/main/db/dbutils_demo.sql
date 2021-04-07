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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='课程';

/*Data for the table `course` */

insert  into `course`(`id`,`name`,`class_hours`,`creatime`) values 
(1,'course1',11,'2021-03-29 13:06:34'),
(2,'course2',12,'2021-03-29 13:06:34'),
(3,'course33',13,'2021-03-29 13:06:34'),
(4,'course_page',10,'2021-03-29 13:06:34'),
(5,'course_page',11,'2021-03-29 13:06:34'),
(6,'course_page',12,'2021-03-29 13:06:34'),
(7,'course_page',13,'2021-03-29 13:06:34'),
(8,'course_page',14,'2021-03-29 13:06:34'),
(9,'course_page',15,'2021-03-29 13:06:34'),
(10,'course_page',16,'2021-03-29 13:06:34'),
(11,'course_page',17,'2021-03-29 13:06:34'),
(12,'course_page',18,'2021-03-29 13:06:34'),
(13,'course_page',19,'2021-03-29 13:06:34'),
(14,'course_page',20,'2021-03-29 13:06:34'),
(15,'course_page',21,'2021-03-29 13:06:34'),
(16,'course_page',22,'2021-03-29 13:06:34'),
(17,'course_page',23,'2021-03-29 13:06:34'),
(18,'course_page',24,'2021-03-29 13:06:34'),
(19,'course_page',25,'2021-03-29 13:06:34'),
(20,'course_page',26,'2021-03-29 13:06:34'),
(21,'course_page',27,'2021-03-29 13:06:34'),
(22,'course_page',28,'2021-03-29 13:06:34'),
(23,'course_page',29,'2021-03-29 13:06:34'),
(24,'course_page',30,'2021-03-29 13:06:34'),
(25,'course_page',31,'2021-03-29 13:06:34'),
(26,'course_page',32,'2021-03-29 13:06:34'),
(27,'course_page',33,'2021-03-29 13:06:34'),
(28,'course_page',34,'2021-03-29 13:06:34'),
(29,'course_page',35,'2021-03-29 13:06:34'),
(30,'course_page',36,'2021-03-29 13:06:34'),
(31,'course_page',37,'2021-03-29 13:06:34'),
(32,'course_page',38,'2021-03-29 13:06:34'),
(33,'course_page',39,'2021-03-29 13:06:34'),
(36,'add2',0,'2021-03-29 13:06:35'),
(37,'add3',0,'2021-03-29 13:06:35'),
(39,'add2',NULL,'2021-03-29 13:06:35'),
(40,'add3',NULL,'2021-03-29 13:06:35'),
(42,'add2',0,NULL),
(43,'add3',0,NULL),
(45,'add2',0,NULL),
(46,'add3',0,NULL),
(48,'add2',0,NULL),
(49,'add3',0,NULL),
(51,'course_md01',231,'2021-03-29 13:06:35'),
(52,'course_md01',231,'2021-03-29 13:06:35'),
(53,'course_md02',232,'2021-03-29 13:06:35');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `name` varchar(20) DEFAULT '' COMMENT '学生姓名',
  `age` int(11) DEFAULT '0' COMMENT '年龄',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='学生';

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`age`,`birth_day`) values 
(1,'student1',40,'1980-10-08'),
(2,'student2',39,'1981-11-01'),
(3,'student3',38,'1982-10-08'),
(4,'student4',38,'1982-05-08'),
(5,'student5',38,'1982-06-08'),
(6,'student6',38,'1982-07-08'),
(7,'student7',38,'1982-03-08'),
(8,'student8',38,'1982-04-08'),
(9,'student9',38,'1982-06-08'),
(10,'student10',38,'1982-04-08'),
(11,'student11',38,'1982-06-08'),
(12,'student12',38,'1982-07-08'),
(13,'student13',38,'1982-01-08');

/*Table structure for table `student_course` */

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `student_course` */

insert  into `student_course`(`id`,`student_id`,`course_id`) values 
(1,1,10),
(2,2,13),
(3,3,14),
(4,4,15),
(5,5,12),
(6,6,16),
(7,7,15),
(8,8,12),
(9,9,14),
(10,10,16),
(11,11,20);

/*Table structure for table `t1` */

DROP TABLE IF EXISTS `t1`;

CREATE TABLE `t1` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `a` int(11) DEFAULT NULL,
  `key_b` datetime DEFAULT NULL,
  `key_c` varchar(30) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t1` */

insert  into `t1`(`id`,`a`,`key_b`,`key_c`) values 
(1,3,'2019-11-01 17:29:36','bbb'),
(2,2,'2019-11-21 17:29:38','xxxx');

/*Table structure for table `t2` */

DROP TABLE IF EXISTS `t2`;

CREATE TABLE `t2` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `a` int(11) DEFAULT NULL,
  `key_a` int(11) DEFAULT '0',
  `key_b` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t2` */

insert  into `t2`(`id`,`a`,`key_a`,`key_b`) values 
(1,1,1,0),
(2,1,1,6),
(3,1,2,0),
(4,1,3,1),
(5,1,4,1),
(6,2,1,2),
(7,2,2,4),
(8,3,12,44);

/* Function  structure for function  `query_course_cnt_func` */

/*!50003 DROP FUNCTION IF EXISTS `query_course_cnt_func` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `query_course_cnt_func`( v_name varchar(40)) RETURNS int(11)
BEGIN
	declare v_ret int;
	SELECT count(1) INTO v_ret FROM `course` WHERE `name` = v_name;
	RETURN v_ret;
END */$$
DELIMITER ;

/* Procedure structure for procedure `query_course_proc` */

/*!50003 DROP PROCEDURE IF EXISTS  `query_course_proc` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `query_course_proc`(IN v_cname VARCHAR(45) , OUT v_cnt INT)
BEGIN
    SELECT count(1) into v_cnt  FROM `course` ;    
    SELECT * FROM `course` WHERE `name` = v_cname;
END */$$
DELIMITER ;

/* Procedure structure for procedure `testproc` */

/*!50003 DROP PROCEDURE IF EXISTS  `testproc` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `testproc`()
BEGIN
	   set @i=1;
	   select 1 into @i from t1;
	END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
