package com.github.ulwx.aka.dbutils.demo.service;

import com.github.ulwx.aka.dbutils.database.DbException;
import com.github.ulwx.aka.dbutils.database.MDataBase;
import com.github.ulwx.aka.dbutils.database.MDbManager;
import com.github.ulwx.aka.dbutils.database.MDbTransactionManager;
import com.github.ulwx.aka.dbutils.database.MDbTransactionManager.PROPAGATION;
import com.github.ulwx.aka.dbutils.demo.dao.StudentDao2;
import com.github.ulwx.aka.dbutils.demo.domian.Course;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;
import com.github.ulwx.aka.dbutils.demo.dao.CourseDao;

import java.time.LocalDateTime;
import java.util.List;

public class CourseService {
    private CourseDao courseDao=new CourseDao();
    private StudentDao2 studentDao2=new StudentDao2();
    public static String DbPoolName="dbutils-demo";

    public void testBasicUsage(){
        //删除course表所有记录,
        courseDao.delAll();
        //添加course1的记录
        Course course1=new Course();
        course1.setId(1);
        course1.setName("course1");
        course1.setClassHours(11);
        course1.setCreatime(LocalDateTime.now());
        courseDao.addAndReturnKey(course1);

        List<Course> lista=courseDao.queryListBy("course1",11);
        lista=courseDao.queryListByWithSetting("course1",11);

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
        System.out.println(ObjectUtils.toString(course33));
        List<Course> list= courseDao.queryListFromMdFile("course", 10);
        System.out.println(ObjectUtils.toString(list));
        //测试分页查询
        for(int i=0; i<30; i++) {
            Course course = new Course();
            course.setId(i+4);
            course.setName("course_page");
            course.setClassHours(10+i);
            course.setCreatime(LocalDateTime.now());
            courseDao.addAndReturnKey(course);
        }
        PageBean pageBean=new PageBean();
        List<Course> onePageList=courseDao.queryListForPage("course_page",2,10,pageBean);
        System.out.println(ObjectUtils.toString(pageBean));
        System.out.println(ObjectUtils.toString(onePageList));

        List<Course>  list1 = courseDao.queryListForWhere("course_page",11);
        System.out.println(ObjectUtils.toString(list1));

        courseDao.testQueryForResultSet();
        courseDao.testQueryForResultSetPage();
    }
    public void testTransaction(){
        MDataBase mdb = null;
        try {
            mdb = MDbManager.getDataBase(DbPoolName);
            mdb.setAutoCommit(false);
            Course course=courseDao.testInsertInTrans(mdb);
            courseDao.testUpdateInTrans(mdb,course);
            mdb.commit();
        } catch (Exception e){
            mdb.rollback();
            throw new DbException(e);
        }finally {
            if (mdb != null) {
                mdb.close();
            }
        }
    }
    public void testTransactionWithSavePoint(){
        MDataBase mdb = null;
        try {
            mdb = MDbManager.getDataBase(DbPoolName);
            mdb.setAutoCommit(false);
            mdb.setSavepoint("abc");
            Course course=courseDao.testInsertInTrans(mdb);

            course.setCreatime(LocalDateTime.now().plusDays(3));
            courseDao.testUpdateInTrans(mdb,course);
            mdb.setSavepoint("123");
            course.setCreatime(LocalDateTime.now().plusDays(6));
            courseDao.testUpdateInTrans(mdb,course);

            mdb.rollbackToSavepoint("123");
            mdb.rollbackToSavepoint("abc");
            mdb.commit();
        } catch (Exception e){
            mdb.rollback();
            throw new DbException(e);
        }finally {
            if (mdb != null) {
                mdb.close();
            }
        }
    }
    public void testTransactionManager(){
        MDbTransactionManager.execute(()->{
            courseDao.testInsertWithMd();
            courseDao.testUpdate();
        });
    }
    public void testTransactionManagerOuter(){
        MDbTransactionManager.execute(()->{
            this.testTransactionManager();
            courseDao.testUpdate();
        });
    }

    public void testTransactionManagerForDiffDB(){
        MDbTransactionManager.execute(()->{
            courseDao.testUpdateCourse();
            studentDao2.testInsertStudent();
        });
    }

    public void testTransactionManagerForDiffDBOuter(){
        MDbTransactionManager.execute(()->{
            courseDao.testUpdateCourse();
            studentDao2.testInsertStudent();
            testTransactionManagerForDiffDB();
        });
    }
    public void testRollBackTransactionManagerForDiffDB(){
        MDbTransactionManager.execute(()->{
            try {
                testTransactionManagerException();
            }catch (Exception e){
                e.printStackTrace();
            }
            //courseDao.testUpdateCourse();
            studentDao2.testInsertStudent();

        });
    }
    public void testTransactionManagerException(){
        MDbTransactionManager.execute(PROPAGATION.NESTED,()->{
            //courseDao.testUpdateCourse();
            testTransactionManagerExceptionMore();
        });
    }
    public void testTransactionManagerExceptionMore(){
        MDbTransactionManager.execute(PROPAGATION.REQUIRED,()->{
            courseDao.testUpdateCourse();
             int f=1/0;
        });
    }
    public static void main(String[] args) throws Exception{
        CourseService courseService=new CourseService();
        courseService.testBasicUsage();
        courseService.testTransaction();
        courseService.testTransactionManager();
        courseService.testTransactionManagerForDiffDBOuter();
        courseService.testRollBackTransactionManagerForDiffDB();
        courseService.testTransactionWithSavePoint();


    }
}
