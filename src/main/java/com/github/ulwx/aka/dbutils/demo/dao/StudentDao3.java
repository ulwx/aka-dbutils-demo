package com.github.ulwx.aka.dbutils.demo.dao;

import com.github.ulwx.aka.dbutils.database.DataBase.MainSlaveModeConnectMode;
import com.github.ulwx.aka.dbutils.database.DbContext;
import com.github.ulwx.aka.dbutils.database.MDbTransactionManager;
import com.github.ulwx.aka.dbutils.demo.domian.Student;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.MDbUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao3 {
    public static String DbPoolName="dbutils-demo3";
    public void testInsertStudent(){
        Student student=new Student();
        student.setAge(18);
        student.setName("add");
        MDbUtils.insertBy(DbPoolName, student);
    }
    public void testUpdateStudent(){
        Student student=new Student();
        student.setId(123);
        student.setAge(18);
        student.setName("add");
        MD.map(student);
        MDbUtils.update(DbPoolName, MD.md(),MD.map(student));
    }
    public List<Student> testSeletStudent(){
        Map<String, Object> args=new HashMap<>();
        args.put("lname","abc");
        List<Student> list = MDbUtils.queryList(DbPoolName,Student.class,
                MD.md(),args);
        return list;
    }
    public static void main(String[] args) {
        InitDataTool.prepareData();
        StudentDao3 studentDao3 = new StudentDao3();
        DbContext.setMainSlaveModeConnectMode(MainSlaveModeConnectMode.Connect_Auto);
        studentDao3.testSeletStudent();
        studentDao3.testUpdateStudent();
        DbContext.setMainSlaveModeConnectMode(MainSlaveModeConnectMode.Connect_MainServer);
        studentDao3.testSeletStudent();

        DbContext.setMainSlaveModeConnectMode(MainSlaveModeConnectMode.Connect_Auto);
        //下面一行跟上面一行效果相同
        //DbContext.setMainSlaveModeConnectMode(MainSlaveModeConnectMode.Connect_MainServer);
        //在事务里必须必定使用主库连接，这是为了保持数据一致性，如果指定Connect_SlaveServer会报错，下面一行会报错
        //DbContext.setMainSlaveModeConnectMode(MainSlaveModeConnectMode.Connect_SlaveServer);
        MDbTransactionManager.execute(()->{
            studentDao3.testSeletStudent();
            studentDao3.testUpdateStudent();
        });


    }

}
