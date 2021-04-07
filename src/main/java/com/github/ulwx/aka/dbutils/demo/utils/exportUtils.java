package com.github.ulwx.aka.dbutils.demo.utils;

import com.github.ulwx.aka.dbutils.database.sql.SqlUtils;

public class exportUtils {
    public static void main(String[] args) {
        SqlUtils.exportTables("dbutils-demo", "dbutils_demo", "c:/dbutils_demo", "com.github.ulwx.aka.dbutils.demo.dao","utf-8",true);

    }
}
