package com.github.ulwx.aka.dbutils.demo.dao;

import com.github.ulwx.aka.dbutils.demo.domian.Student;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.MDbUtils;
import com.github.ulwx.aka.dbutils.tool.support.type.TInteger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao2 {
    //用另一个连接池模拟另一个库，其实底层是同一个库
    public static String DbPoolName="dbutils-demo2";
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
    public void testUpdateStudentForVariableSubstitution(){
        Student student=new Student();
        student.setId(123);
        student.setAge(18);
        student.setName("add'a");
        Map<String, Object> map = MD.map(student);
        map.put("ids", new int[]{1,2,3});
        map.put("lname", "add'b");
        MDbUtils.update(DbPoolName, MD.md(),map);
    }
    public void allMdSyntax(){
        Map<String, Object> map = new HashMap<>();
        map.put("ids", new int[]{1,2,3});
        map.put("lname", "add'b");
        map.put("lnameList", Arrays.asList("abc","efg","bed"));
        map.put("birthDay", LocalDate.of(1980, 11, 2));

        TInteger ret=MDbUtils.queryOne(DbPoolName,TInteger.class, MD.md(),map);
    }
    public static void main(String[] args) {
        InitDataTool.prepareData();
        StudentDao2 studentDao2 = new StudentDao2();
        studentDao2.testUpdateStudent();
        studentDao2.testUpdateStudentForVariableSubstitution();
        studentDao2.allMdSyntax();
    }

}
