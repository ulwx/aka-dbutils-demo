package com.github.ulwx.aka.dbutils.demo.dao;

import com.github.ulwx.aka.dbutils.database.DataBaseSet;
import com.github.ulwx.aka.dbutils.database.MDMethods.One2ManyMapNestOptions;
import com.github.ulwx.aka.dbutils.database.MDMethods.PageOptions;
import com.github.ulwx.aka.dbutils.database.MDbTransactionManager;
import com.github.ulwx.aka.dbutils.database.QueryMapNestOne2Many;
import com.github.ulwx.aka.dbutils.database.QueryMapNestOne2One;
import com.github.ulwx.aka.dbutils.demo.domian.Course;
import com.github.ulwx.aka.dbutils.demo.mydomain.One2ManyStudent;
import com.github.ulwx.aka.dbutils.demo.mydomain.One2OneStudent;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.MDbUtils;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseMpperTest {
    public static String DbPoolName="dbutils-demo";
    public static void testGetOne(){
        Map<String,Object> arg = new HashMap<>();
        arg.put("xyz","course33");
        Course cs=new Course();
        cs.setCreatime(LocalDateTime.now());
        Course course= MDbUtils.getMapper(DbPoolName, CourseMpper.class).getOneCourse(
                2,"course33",arg,cs);
        System.out.println(ObjectUtils.toString(course));
    }
    public static void testGetOneCourseByIds(){
        Map<String,Object> arg = new HashMap<>();
        arg.put("xyz","course33");
        Course cs=new Course();
        cs.setCreatime(LocalDateTime.now());
        List<Course> courseList=MDbUtils.getMapper(DbPoolName, CourseMpper.class).getCoursesByIds(
                new Integer[]{1,3},"course",arg,cs);
        System.out.println(ObjectUtils.toString(courseList));
    }
    public static void testGetList(){
        List<Course> courseList=MDbUtils.getMapper(DbPoolName, CourseMpper.class).getCouseList("course");
        System.out.println(ObjectUtils.toString(courseList));
    }

    public static void testCouseListPage(){
        List<Course> courseList=MDbUtils.getMapper(DbPoolName, CourseMpper.class).getCouseListPage("course",
                MD.ofPage(2, 3, null));
        System.out.println(ObjectUtils.toString(courseList));
        List<Course> courseList2=MDbUtils.getMapper(DbPoolName, CourseMpper.class).getCouseListPage("course",
                MD.ofPage(2, 3,MD.md(CourseMpper.class,
                        "getCouseListPageCount") ,null));
        System.out.println(ObjectUtils.toString(courseList2));
    }

    public static void  testReturnDataBaseSet(){
        Map<String,Object> arg = new HashMap<>();
        arg.put("name","course33");

        DataBaseSet rs=(DataBaseSet)MDbUtils.getMapper(DbPoolName, CourseMpper.class).getRSet(arg);
        while(rs.next()){
            String name=rs.getString("name");
            Integer classHours=rs.getInt("class_hours");
            System.out.println("name--"+name+",classHours--"+classHours);
        }
    }
    public static void testReturnDataBaseSetAndPage(){
        PageOptions pageOptions=MD.ofPage(2, 3, null);

        Map<String,Object> args = new HashMap<>();
        args.put("name","course");
        args.put("classHours", new Integer[]{10,11,12,13,14,15,16,17,18,19});

        DataBaseSet rs=(DataBaseSet)MDbUtils.
                getMapper(DbPoolName, CourseMpper.class).getRSetPage(args,pageOptions);
        while(rs.next()){
            String name=rs.getString("name");
            Integer classHours=rs.getInt("class_hours");
            System.out.println("name--"+name+",classHours--"+classHours);
        }
    }
    public static void testCouseListOne2One(){
        QueryMapNestOne2One queryMapNestOne2One = new QueryMapNestOne2One();
        queryMapNestOne2One.set(null, "course", "c.");
        List<One2OneStudent> studentList=MDbUtils.getMapper(DbPoolName, CourseMpper.class).
                getCouseListOne2One("student",
                        MD.ofOne2One("stu.",queryMapNestOne2One));
        System.out.println(ObjectUtils.toString(studentList));
    }
    public static void testCouseListOne2OnePage(){
        QueryMapNestOne2One queryMapNestOne2One = new QueryMapNestOne2One();
        queryMapNestOne2One.set(null, "course", "c.");
        List<One2OneStudent> studentList=MDbUtils.getMapper(DbPoolName, CourseMpper.class).
                getCouseListOne2OnePage("student",
                        MD.ofOne2One("stu.",queryMapNestOne2One),
                        MD.ofPage(2, 3,  MD.md(CourseMpper.class,"getCouseListOne2OnePageForCount"),
                                null));
        System.out.println(ObjectUtils.toString(studentList));
    }

    public static void testCouseListOne2Many(){
        QueryMapNestOne2Many queryMapNestOne2Many = new QueryMapNestOne2Many();
        queryMapNestOne2Many.set(Course.class,
                "courseList",
                new String[]{"id"},
                "c.",
                null);
        One2ManyMapNestOptions one2ManyMapNestOptions=MD.ofOne2Many("stu."
                , new String[]{"id"}, queryMapNestOne2Many);

        List<One2ManyStudent> studentList=MDbUtils.getMapper(DbPoolName, CourseMpper.class).
                getCouseListOne2Many("student",
                        one2ManyMapNestOptions);
        System.out.println(ObjectUtils.toString(studentList));
    }

    public static void testAddCourse() {
        Course course=new Course();
        course.setName("abcd");
        course.setCreatime(LocalDateTime.now());
        course.setClassHours(45);
        MDbUtils.getMapper(DbPoolName, CourseMpper.class).addCourse(course);
    }
    public static void testAddCourseAndReturnKey() {
        Course course=new Course();
        course.setName("abcd");
        course.setCreatime(LocalDateTime.now());
        course.setClassHours(45);
        int key=MDbUtils.getMapper(DbPoolName, CourseMpper.class).addCourseReturnKey(course
                , MD.ofInsert(true));
        System.out.println("ret="+key);
    }

    public static void testUpdateCourse() {
        Course course=new Course();
        course.setName("abcd");
        course.setCreatime(LocalDateTime.now());
        course.setClassHours(45);
        course.setId(3);
        CourseMpper courseMpper=MDbUtils.getMapper(DbPoolName, CourseMpper.class);
        //courseMpper 返回的mapper对象不是线程安全的
        courseMpper.updateCourse(course);
        courseMpper.updateCourse(course);
        courseMpper=MDbUtils.getMapper(DbPoolName, CourseMpper.class);
        courseMpper.updateCourse(course);
    }
    public static void testTransaction(){
        MDbTransactionManager.execute(()->{
            testAddCourseAndReturnKey();
            testUpdateCourse();
            testUpdateCourseManual();
            testNestTransaction();
        });
    }
    public static void testNestTransaction(){
        MDbTransactionManager.execute(()->{
            testAddCourseAndReturnKey();
            testUpdateCourse();
        });
    }
    public static void testUpdateCourseManual(){
        MDbUtils.getMapper(DbPoolName, CourseMpper.class).updateCourseManual();
    }

    public static void main(String[] a) {
        InitDataTool.prepareData();
       //  testReturnDataBaseSet();
       //  testReturnDataBaseSetAndPage();
       //  testGetOne();
       //  testGetOneCourseByIds();
       //  testGetList();
       //  testCouseListPage();
       // testCouseListOne2One();
       //  testCouseListOne2OnePage();
       //  testCouseListOne2Many();
       //  testAddCourse();
       //  testAddCourseAndReturnKey();
       //  testUpdateCourse();
       // testTransaction();
         testUpdateCourseManual();


    }
}
