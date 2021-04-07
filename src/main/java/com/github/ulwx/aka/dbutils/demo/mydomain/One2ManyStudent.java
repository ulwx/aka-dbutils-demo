package com.github.ulwx.aka.dbutils.demo.mydomain;

import com.github.ulwx.aka.dbutils.demo.domian.Course;
import com.github.ulwx.aka.dbutils.demo.domian.Student;

import java.util.List;

public class One2ManyStudent extends Student {
    private List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}



