package com.github.ulwx.aka.dbutils.demo.domian;
import java.util.*;
import java.sql.*;
import java.time.*;
import com.github.ulwx.aka.dbutils.database.MdbOptions;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

/*********************************************
课程
***********************************************/
public class Course extends MdbOptions implements java.io.Serializable {

	private Integer id;/*课程id;len:10*/
	private String name;/*课程名称;len:20*/
	private Integer classHours;/*学时;len:10*/
	private LocalDateTime creatime;/*建立时间;len:19*/

	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setClassHours(Integer classHours){
		this.classHours = classHours;
	}
	public Integer getClassHours(){
		return classHours;
	}
	public void setCreatime(LocalDateTime creatime){
		this.creatime = creatime;
	}
	public LocalDateTime getCreatime(){
		return creatime;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =949138636L;

}