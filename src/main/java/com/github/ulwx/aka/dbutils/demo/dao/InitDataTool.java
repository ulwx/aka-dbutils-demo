package com.github.ulwx.aka.dbutils.demo.dao;

import com.github.ulwx.aka.dbutils.demo.domian.Course;
import com.github.ulwx.aka.dbutils.tool.MDbUtils;

import java.time.LocalDateTime;

public class InitDataTool {
    public static void prepareData() {

        MDbUtils.exeScript(CourseDao.DbPoolName
                , CourseDao.class.getPackage().getName(),
                "dbutils_demo.sql"
                , false);

        CourseDao courseDao=new CourseDao();
        //删除course表所有记录,
        courseDao.delAll();
        //添加course1的记录
        Course course1=new Course();
        course1.setId(1);
        course1.setName("course1");
        course1.setClassHours(11);
        course1.setCreatime(LocalDateTime.now());
        courseDao.addAndReturnKey(course1);

        //添加course2的记录
        Course course2=new Course();
        course2.setId(2);
        course2.setName("course2");
        course2.setClassHours(12);
        course2.setCreatime(LocalDateTime.now());
        courseDao.addAndReturnKey(course2);
        //添加course3的记录
        Course course3=new Course();
        course3.setId(3);
        course3.setName("course3");
        course3.setClassHours(13);
        course3.setCreatime(LocalDateTime.now());
        int course3Id=courseDao.addAndReturnKey(course3);
        //更新course3的记录为名称course33
        Course courseForUpdate=new Course();
        courseForUpdate.setId(course3Id);
        courseForUpdate.setName("course33");
        courseDao.update(courseForUpdate);
        Course course33=courseDao.queryOne("course33", 13);

        for(int i=0; i<30; i++) {
            Course course = new Course();
            course.setId(i+4);
            course.setName("course_page");
            course.setClassHours(10+i);
            course.setCreatime(LocalDateTime.now());
            courseDao.addAndReturnKey(course);
        }
        StudentDao studentDao=new StudentDao();
        studentDao.delAll();
        studentDao.insertData();

        StudentCourseDao studentCourseDao=new StudentCourseDao();
        studentCourseDao.delAll();
        studentCourseDao.insertData();


    }
}
