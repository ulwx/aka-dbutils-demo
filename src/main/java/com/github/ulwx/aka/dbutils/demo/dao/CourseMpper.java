package com.github.ulwx.aka.dbutils.demo.dao;

import com.github.ulwx.aka.dbutils.database.AkaMapper;
import com.github.ulwx.aka.dbutils.database.DataBaseSet;
import com.github.ulwx.aka.dbutils.database.MDMethods.One2ManyMapNestOptions;
import com.github.ulwx.aka.dbutils.database.MDMethods.One2OneMapNestOptions;
import com.github.ulwx.aka.dbutils.database.MDMethods.PageOptions;
import com.github.ulwx.aka.dbutils.database.MDMethods.InsertOptions;
import com.github.ulwx.aka.dbutils.database.MDataBase;
import com.github.ulwx.aka.dbutils.demo.domian.Course;
import com.github.ulwx.aka.dbutils.demo.mydomain.One2ManyStudent;
import com.github.ulwx.aka.dbutils.demo.mydomain.One2OneStudent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public abstract class CourseMpper extends AkaMapper {

    public abstract  DataBaseSet getRSet(Map<String,Object> ars);
    public abstract DataBaseSet getRSetPage(Map<String,Object> ars, PageOptions pageOptions);
    public abstract Course getOneCourse(Integer id, String name, Map<String,Object> ars,Course cs);
    public abstract  List<Course> getCoursesByIds(Integer[] ids, String name, Map<String,Object> ars,Course cs);
    public abstract List<Course> getCouseList(String name);
    public abstract List<Course> getCouseListPage(String name,PageOptions pageOptions);
    public abstract List<One2OneStudent> getCouseListOne2One(String name, One2OneMapNestOptions nestOptions);
    public abstract List<One2OneStudent> getCouseListOne2OnePage(String name, One2OneMapNestOptions nestOptions,
                                                        PageOptions pageOptions);
    public abstract List<One2ManyStudent> getCouseListOne2Many(String name, One2ManyMapNestOptions nestOptions);
    public abstract void addCourse(Course course);
    public abstract int addCourseReturnKey(Course course, InsertOptions insertOptions);
    public abstract int updateCourse(Course course);

    public void updateCourseManual(){
        Course course=new Course();
        course.setName("abcd");
        course.setCreatime(LocalDateTime.now());
        course.setClassHours(45);
        course.setId(3);
        MDataBase mDataBase=this.getMdDataBase();
        this.updateCourse(course);
    }


}
