package com.github.ulwx.aka.dbutils.demo.dao;

import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.MDbUtils;

import java.util.Map;

public class StudentCourseDao {
    public static String DbPoolName="dbutils-demo";
    public void delAll(){    //①-1  对应md方法名
        MDbUtils.del(DbPoolName, MD.md(), null);
    }
    public void  insertData() {
        MDbUtils.insert(DbPoolName, MD.md(), (Map<String, Object>) null);
    }
}
