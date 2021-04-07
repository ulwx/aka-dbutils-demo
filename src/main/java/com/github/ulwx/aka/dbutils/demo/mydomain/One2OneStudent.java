package com.github.ulwx.aka.dbutils.demo.mydomain;

import com.github.ulwx.aka.dbutils.demo.domian.Course;
import com.github.ulwx.aka.dbutils.demo.domian.Student;

public class One2OneStudent extends Student {
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
