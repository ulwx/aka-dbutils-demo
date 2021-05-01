package com.github.ulwx.aka.dbutils.demo.dao;

import com.github.ulwx.aka.dbutils.database.*;
import com.github.ulwx.aka.dbutils.database.MDMethods.One2ManyMapNestOptions;
import com.github.ulwx.aka.dbutils.database.MDMethods.One2OneMapNestOptions;
import com.github.ulwx.aka.dbutils.database.sql.SqlUtils;
import com.github.ulwx.aka.dbutils.demo.domian.Course;
import com.github.ulwx.aka.dbutils.demo.domian.Student;
import com.github.ulwx.aka.dbutils.demo.mydomain.MyStudent;
import com.github.ulwx.aka.dbutils.demo.mydomain.One2ManyStudent;
import com.github.ulwx.aka.dbutils.demo.mydomain.One2OneStudent;
import com.github.ulwx.aka.dbutils.tool.MD;

import com.github.ulwx.aka.dbutils.tool.MDbUtils;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;
import com.github.ulwx.aka.dbutils.tool.support.ResultSetPrinter;
import com.github.ulwx.aka.dbutils.tool.support.type.TInteger;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class CourseDao {
    public static String DbPoolName="dbutils-demo";
    public void delAll(){    //①-1  对应md方法名
        MDbUtils.del(DbPoolName, MD.md(), null);
    }
    public int addAndReturnKey(Course course)  {
        return (int)MDbUtils.insertReturnKeyBy(DbPoolName, course);  //②  直接通过对象反射生成sql语句
    }
    public void update(Course course)throws  DbException{
        MDbUtils.updateBy(DbPoolName, course, MD.of( course::getId));//③ 直接通过对象反射生成sql语句
        //MDbUtils.updateBy(DbPoolName, course, MD.of( "id"));//另一种形式，和上面运行结果等效
    }

    public Course queryOne(String name, int classHours) {
        Course course=new Course();
        course.setName(name);
        course.setClassHours(classHours);
        return MDbUtils.queryOneBy(DbPoolName, course); //④  直接通过对象反射生成sql语句
    }
    public List<Course> queryListBy(String name, int classHours) {
        Course course=new Course();
        course.setName(name);
        course.setClassHours(classHours);
        return MDbUtils.queryListBy(DbPoolName, course);

    }
    public List<Course> queryListByWithSetting(String name, int classHours) {
        Course course=new Course();
        course.setName(name);
        course.setClassHours(classHours);
        course.selectOptions().select("class_hours as classHours,id").limit(2);
        return MDbUtils.queryListBy(DbPoolName, course);
    }
    public  List<Course> queryListFromMdFile(String name, int classHours) {
        Map<String,Object>map=new HashMap<>();
        map.put("myName",name);
        map.put("myClassHours",classHours);
        return MDbUtils.queryList(DbPoolName, Course.class, MD.md(), map);//⑤  用到了CourseDao.md文件
    }

    public List<Course>  queryListForPage(String name,int page,int perPage,PageBean pageBean){
        Course course=new Course();
        course.setName(name);
        return MDbUtils.queryListBy(DbPoolName, course, page, perPage, pageBean);
    }

    public List<Course>  queryListForWhere(String name,int classHours){
        Course course=new Course();
        course.setName(name);
        course.setClassHours(classHours);
        return MDbUtils.queryListBy(DbPoolName, course,MD.of(course::getName,course::getCreatime));
    }

    public  void testQueryListBy(){
        Course course=new Course();
        course.setName("course1");
        course.setClassHours(11);
        List<Course> list = MDbUtils.queryListBy(DbPoolName, course);
        System.out.println("list--"+ObjectUtils.toPrettyJsonString(list));

        course.selectOptions().select("class_hours as classHours , id").
                orderBy("classHours desc").limit(2);
        list = MDbUtils.queryListBy(DbPoolName, course);
        System.out.println("list--"+ObjectUtils.toPrettyJsonString(list));

    }

    public  void testQueryListForPage(){
        PageBean pageBean=new PageBean();
        Course course=new Course();
        course.setName("course_page");
        List<Course> list =
                MDbUtils.queryListBy(DbPoolName, course, 2, 3, pageBean); // ①
        System.out.println("list--"+ObjectUtils.toPrettyJsonString(list));

        course.selectOptions().select("class_hours as classHours , id").
                orderBy("id desc")
                //.limit(11)   //下方的对象分页查询不能调用limit(n)方法设置limit，否则会报错。
                 ;
        list = MDbUtils.queryListBy(DbPoolName, course, 2, 3, pageBean);
        System.out.println("list--"+ObjectUtils.toPrettyJsonString(list));

    }
    public void  testQueryListForWhere(){
        Course course=new Course();
        course.setName("course_page");
        course.setClassHours(11);
        List<Course> list = MDbUtils.queryListBy(DbPoolName,
                course,MD.of(course::getName,course::getCreatime)); //  ①
        System.out.println("list--"+ObjectUtils.toPrettyJsonString(list));

    }
    public void testQueryForResultSet(){
        Map<String,Object> args = new HashMap<>();
        args.put("name","course33");
        DataBaseSet rs=MDbUtils.queryForResultSet(DbPoolName,MD.md(),args );
        while(rs.next()){
            String name=rs.getString("name");
            Integer classHours=rs.getInt("class_hours");
            System.out.println("name--"+name+",classHours--"+classHours);
        }
    }

    public void testQueryForResultSetPage(){
        PageBean pageBean=new PageBean();
        Map<String,Object> args = new HashMap<>();
        args.put("name","course");
        args.put("classHours", new Integer[]{10,11,12,13,14,15,16,17,18,19});
        DataBaseSet rs=MDbUtils.queryForResultSet(DbPoolName, MD.md(), args, 2,
                5, pageBean, "-1");
        // DataBaseSet rs=MDbUtils.queryForResultSet(DbPoolName, MD.md(), args, 2,5, pageBean, "");
        while(rs.next()){
            String name=rs.getString("name");
            Integer classHours=rs.getInt("class_hours");
            System.out.println("name--"+name+",classHours--"+classHours);
        }
    }
    public  void testQueryList(){
        Map<String,Object> args = new HashMap<>();
        args.put("name","course");
        args.put("classHours", new Integer[]{10,11,12,13,14,15,16,17,18,19});
        List<Course> list=MDbUtils.queryList(DbPoolName,Course.class, MD.md(), args);
        for (int i=0; i<list.size();i++){
            Course course = list.get(i);
            System.out.println("course"+i+"="+ ObjectUtils.toString(course));
        }
    }
    public  void testQueryListWithRowMapper(){
        Map<String,Object> args = new HashMap<>();
        args.put("name","course");
        args.put("classHours", new Integer[]{10,11,12,13,14,15,16,17,18,19});
        List<Course> list= MDbUtils.queryList(DbPoolName,MD.md(), args,(rs)->{
                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setName(rs.getString("name"));
                    course.setClassHours(rs.getInt("class_hours"));
                    course.setCreatime(rs.getLocalDateTime("creatime"));
                    return course;
                  });
        for (int i=0; i<list.size();i++){
            Course course = list.get(i);
            System.out.println("course"+i+"="+ ObjectUtils.toString(course));
        }
    }

    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
       return localDateTime;
    }
    public  void testQueryOne(){
        Map<String,Object> args = new HashMap<>();
        args.put("name","course");
        args.put("classHours", new Integer[]{10,11,12,13,14,15,16,17,18,19});
        Course course=MDbUtils.queryOne(DbPoolName,Course.class, MD.md(), args);
        System.out.println("course="+ ObjectUtils.toString(course));
    }
    public static  void testQueryMap(){
        Map<String,Object> args = new HashMap<>();
        args.put("name","course");
        args.put("classHours", new Integer[]{10,11,12});
        List<Map<String, Object>> map=MDbUtils.queryMap(DbPoolName,MD.md(), args);
        System.out.println("course="+ ObjectUtils.toPrettyJsonString(map));
    }

    public  void testQueryMapPage(){
        PageBean pageBean=new PageBean();
        Map<String,Object> args = new HashMap<>();
        args.put("name","course_page");
        args.put("classHours", new Integer[]{10,11,12,13,14,15,16,17,18,19});
        List<Map<String, Object>> list=MDbUtils.queryMap(DbPoolName, MD.md(), args, 2, 3,
                pageBean,
                "-1"); //-1表示总记录行未知，aka-dbutils不会生成计算总数的SQL
        System.out.println("course="+ ObjectUtils.toPrettyJsonString(list));
        System.out.println("pageBean="+ObjectUtils.toPrettyJsonString(pageBean));
    }
    public  void testQueryListOne2One(){
        Map<String,Object> args = new HashMap<>();
        args.put("name",new String[]{"student1","student2","student3"});
        QueryMapNestOne2One queryMapNestOne2One = new QueryMapNestOne2One();
        queryMapNestOne2One.set(null, "course", "c.");
        One2OneMapNestOptions one2OneMapNestOptions=MD.ofOne2One(
                "stu."
                ,queryMapNestOne2One
        );
        List<One2OneStudent> list=MDbUtils.queryListOne2One(DbPoolName, One2OneStudent.class,
                 MD.md(), args, one2OneMapNestOptions);
        System.out.println("list="+ ObjectUtils.toPrettyJsonString(list));

    }
    public  void testQueryListOne2OnePage(){
        PageBean pageBean=new PageBean();
        Map<String,Object> args = new HashMap<>();
        args.put("name",new String[]{"student1","student2","student3"
                ,"student4","student5","student6","student7","student8","student9"});
        QueryMapNestOne2One queryMapNestOne2One = new QueryMapNestOne2One();
        queryMapNestOne2One.set(null, "course", "c.");
        One2OneMapNestOptions one2OneMapNestOptions=MD.ofOne2One("stu.", queryMapNestOne2One);
        List<One2OneStudent> list=MDbUtils.queryListOne2One(DbPoolName, One2OneStudent.class,
                 MD.md(), args,
                one2OneMapNestOptions,2,
                3,pageBean,MD.md(this.getClass(),
                "testQueryListOne2OnePageCount"));
        System.out.println("list="+ ObjectUtils.toPrettyJsonString(list));

    }
    public  void testQueryListOne2Many(){
        Map<String,Object> args = new HashMap<>();
        args.put("name",new String[]{"student1","student2","student3"});
        QueryMapNestOne2Many queryMapNestOne2Many = new QueryMapNestOne2Many();
        queryMapNestOne2Many.set(Course.class,
                "courseList",
                new String[]{"id"},
                "c.",
                null);
        One2ManyMapNestOptions one2ManyMapNestOptions=MD.ofOne2Many("stu."
                , new String[]{"id"}, queryMapNestOne2Many);
        List<One2ManyStudent> list=MDbUtils.queryListOne2Many(DbPoolName,One2ManyStudent.class,
                MD.md(),
                args,
                one2ManyMapNestOptions);
        System.out.println("list="+ ObjectUtils.toPrettyJsonString(list));

    }
    public int[] getPageIdList(Map<String,Object> args,int pageNum,int perPage,PageBean pb ){
        List<TInteger> list=MDbUtils.
                queryList(DbPoolName, TInteger.class,
                        MD.md(), args,pageNum,perPage, pb,
                        null);//让aka-dbutils自动为我们生成计算总数的分页SQL
        int[] ret=list.stream().mapToInt(TInteger::getValue).toArray();
        return ret;
    }
    public  void testQueryListOne2ManyPage(){
        Map<String,Object> args = new HashMap<>();
        args.put("name",new String[]{"student1","student2","student3",
                "student4","student5","student6"});
        //获取某页的所有id
        int pageNum=2;
        int perPage=3;
        PageBean pageBean = new PageBean();
        int[]  ids= this.getPageIdList(args,pageNum,perPage,pageBean);
        args.put("ids",ids);

        QueryMapNestOne2Many queryMapNestOne2Many = new QueryMapNestOne2Many();
        queryMapNestOne2Many.set(Course.class,
                "courseList",
                new String[]{"id"},
                "c.",
                null);
        One2ManyMapNestOptions one2ManyMapNestOptions=MD.ofOne2Many("stu.", new String[]{"id"}, queryMapNestOne2Many);
        List<One2ManyStudent> list=MDbUtils.queryListOne2Many(DbPoolName,One2ManyStudent.class,
                MD.md(),
                args,
                one2ManyMapNestOptions);
        System.out.println("list="+ ObjectUtils.toPrettyJsonString(list));

    }
    public void testAdd()  {
        Course course=new Course();
        course.setName("add");
        course.setCreatime(LocalDateTime.now());

       // int ret= MDbUtils.insertBy(DbPoolName, course);
        // int ret= MDbUtils.insertBy(DbPoolName, course,true);
        // int ret= MDbUtils.insertBy(DbPoolName, course,MD.of("name","id"));
         // int ret= MDbUtils.insertBy(DbPoolName, course,MD.of(Course::getName,Course::getId));
        //int ret= MDbUtils.insertBy(DbPoolName, course,MD.of(course::getName,course::getId));
         int ret= MDbUtils.insertBy(DbPoolName, course,MD.of(Course::getName,Course::getId),false);
        System.out.println("ret="+ret);

    }

    public void testAddManyObjs()  {
        Course course1=new Course();
        course1.setName("add1");
        course1.setCreatime(LocalDateTime.now());
        Course course2=new Course();
        course2.setName("add2");
        course2.setCreatime(LocalDateTime.now());
        Course course3=new Course();
        course3.setName("add3");
        course3.setCreatime(LocalDateTime.now());
        Course[] courses=new Course[]{course1,course2,course3};
        int[] rets=null;
        rets= MDbUtils.insertBy(DbPoolName, courses);
        rets= MDbUtils.insertBy(DbPoolName, courses,true);
        rets= MDbUtils.insertBy(DbPoolName, courses,MD.of("name","id"));
        rets= MDbUtils.insertBy(DbPoolName, courses,MD.of(Course::getName,Course::getId));
        rets= MDbUtils.insertBy(DbPoolName, courses,MD.of(Course::getName,Course::getId),false);
        System.out.println("rets="+ObjectUtils.toPrettyJsonString(rets));

    }
    public void testUpdate()  {
        Course course=new Course();
        course.setName("add");
        course.setCreatime(LocalDateTime.now());
        int ret=0;
        ret= MDbUtils.updateBy(DbPoolName, course,MD.of("name","id"));
        ret= MDbUtils.updateBy(DbPoolName, course,MD.of(Course::getName,Course::getId));
        ret= MDbUtils.updateBy(DbPoolName, course,MD.of("name","id"),true);
        ret= MDbUtils.updateBy(DbPoolName, course,MD.of("name"),MD.of(Course::getCreatime));
        Course course1=new Course();
        course1.setName("add1");
        course1.setCreatime(LocalDateTime.now());
        Course[] courses = new Course[]{course,course1};
        int[] rets=null;
        rets= MDbUtils.updateBy(DbPoolName, courses,MD.of("name"));

        System.out.println("rets="+ObjectUtils.toPrettyJsonString(rets));

    }
    public void testUpdateCourse()  {
        Course course=new Course();
        course.setName("add");
        course.setId(1);
        course.setCreatime(LocalDateTime.now());
        int ret=0;
        ret= MDbUtils.updateBy(DbPoolName, course,MD.of("name","id"));

        System.out.println("rets="+ObjectUtils.toPrettyJsonString(ret));

    }
    public void testDelete()  {
        Course course=new Course();
        course.setName("add");
        int ret=0;
        ret= MDbUtils.delBy(DbPoolName, course,MD.of("name","id"));
        ret= MDbUtils.delBy(DbPoolName, course,MD.of(Course::getName,Course::getId));
        Course course1=new Course();
        course1.setName("add1");
        course1.setCreatime(LocalDateTime.now());
        Course[] courses = new Course[]{course,course1};
        int[] rets=null;
        rets= MDbUtils.delBy(DbPoolName, courses,MD.of("name"));

        System.out.println("rets="+ObjectUtils.toPrettyJsonString(rets));

    }
    public void testInherit(){
        MyStudent myStudent=new MyStudent();
        myStudent.setAddress("abc");
        myStudent.setName("xyz");
        myStudent.setAge(12);
        myStudent.setBirthDay(LocalDate.now());

        int ret=0;
        //下面语句会报错，生成insert into `my_student` (`birth_day`,`address`,`name`,`age`) values('2021-02-14','abc','xyz',12)
        //ret= MDbUtils.insertBy(DbPoolName, myStudent);

        //DbContext.setReflectClass()指定Student，表示生成SQL时使用student表
        DbContext.setReflectClass(Student.class);
        ret= MDbUtils.insertBy(DbPoolName, myStudent);

        //DbContext.setReflectClass()每次只能使用一次，若下次想使用需再次声明
        DbContext.setReflectClass(Student.class);
        MDbUtils.updateBy(DbPoolName, myStudent, MD.of(MyStudent::getName));

        DbContext.setReflectClass(Student.class);
        MyStudent student=MDbUtils.queryOneBy(DbPoolName, myStudent);

        DbContext.setReflectClass(Student.class);
        MDbUtils.delBy(DbPoolName, myStudent,
                MD.of("name")  //必须使用Student类里的属性，不能使用MyStudent类里的属性，不然会报错
        );
    }
    public  void  testInsertWithMd(){

        Map<String, Object> args=new HashMap<>();
        args.put("name", "course_md");
        args.put("classHours",123);
        args.put("creatime",LocalDateTime.now());
        MDbUtils.insert(DbPoolName, MD.md(), args);

        Course course1=new Course();
        course1.setName("course_md01");
        course1.setClassHours(231);
        course1.setCreatime(LocalDateTime.now());
        MDbUtils.insert(DbPoolName, MD.md(), MD.map(course1));

        Course course2=new Course();
        course2.setName("course_md02");
        course2.setClassHours(232);
        course2.setCreatime(LocalDateTime.now());
        MDbUtils.insert(DbPoolName, MD.md(), MD.mapList(course1,course2));


    }

    public  void  testUpdateWithMd(){

        Map<String, Object> args=new HashMap<>();
        args.put("name", "course_md");
        args.put("classHours",123);
        args.put("creatime",LocalDateTime.now());
        MDbUtils.update(DbPoolName, MD.md(), args);

        Course course1=new Course();
        course1.setName("course_md01");
        course1.setClassHours(231);
        course1.setCreatime(LocalDateTime.now());
        MDbUtils.update(DbPoolName, MD.md(), MD.map(course1));

        Course course2=new Course();
        course2.setName("course_md02");
        course2.setClassHours(232);
        course2.setCreatime(LocalDateTime.now());
        MDbUtils.update(DbPoolName, MD.md(), MD.mapList(course1,course2));


    }
    public  void  testDeleteWithMd(){

        Map<String, Object> args=new HashMap<>();
        args.put("name", "course_md");
        MDbUtils.del(DbPoolName, MD.md(), args);

    }
    public void testExeSqlScript() throws Exception{
        Map<String, Object> args=new HashMap<>();
        args.put("name", "course_md");
        args.put("classHours",123);
        args.put("creatime",LocalDateTime.now());
        String str=MDbUtils.exeScript(DbPoolName,MD.md(),";",args);
        System.out.println("str="+str);
        str=MDbUtils.exeScript(DbPoolName,CourseDao.class.getPackage().getName(),
                "test.sql",false);
        System.out.println("str=\n"+str);

    }

    public void testStoredProc() {
        Map<String, Object> args=new HashMap<>();
        args.put("name:in", "course_page");
        args.put("count:out",int.class);//存入的是类型
       // args.put("count:out",0); //和上面一行等效
        Map<String, Object> out=new HashMap<>();
        List<DataBaseSet> list = new ArrayList<>();
        MDbUtils.callStoredPro(DbPoolName, MD.md(),args,out,list);
        System.out.println("out="+ObjectUtils.toString(out));
        if(list.size()>0){
            for(int i=0; i<list.size() ; i++){
                DataBaseSet dataBaseSet = list.get(i);
                ResultSetPrinter.printResultSet(dataBaseSet.getResultSet());
            }
        }

    }
    public void testStoredFunc() throws Exception{
        Map<String, Object> args=new HashMap<>();
        args.put("name:in", "course_page");
        args.put("count:out", int.class);
        Map<String, Object> out=new HashMap<>();
        MDbUtils.callStoredPro(DbPoolName, MD.md(),args,out,null);
        System.out.println("out="+ObjectUtils.toString(out));

    }

    public Course testInsertInTrans(MDataBase mdb){
        Course course=new Course();
        course.setName("addx");
        course.setCreatime(LocalDateTime.now());;
        long key= mdb.insertReturnKeyBy(course);
        course.setId((int)key);
        return course;
    }
    public void testUpdateInTrans(MDataBase mdb,Course course){
        mdb.updateBy(course, MD.of("id"));
    }
    public static void main(String[] args) throws Exception{
        InitDataTool.prepareData();
        CourseDao dao=new CourseDao();
       dao.testQueryForResultSet();
       dao.testQueryForResultSetPage();
        dao.testQueryList();
        dao.testQueryListWithRowMapper();
       dao.testQueryOne();
       dao.testQueryMap();
       dao.testQueryMapPage();
       dao.testQueryListOne2One();
       dao.testQueryListOne2OnePage();
        dao.testQueryListOne2Many();
        dao.testQueryListOne2ManyPage();
        dao.testAdd();
        dao.testAddManyObjs();
       dao.testUpdate();
       dao.testDelete();
       dao.testQueryListBy();
        dao.testQueryListForPage();
       dao.testQueryListForWhere();
       dao.testInherit();
       dao.testInsertWithMd();
       dao.testUpdateWithMd();
        dao.testDeleteWithMd();
        dao.testExeSqlScript();
        dao.testStoredProc();
       dao.testStoredFunc();
       dao.testExeSqlScript();

    }



}
