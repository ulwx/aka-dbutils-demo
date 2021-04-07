delAll
====
delete from course

queryListFromMdFile
====
select * from course where 1=1  
@if( $$:myName ){
 and name like #{myName%}
@}
@if( (Integer)$$.myClassHours > 0 ){
 and class_hours > #{myClassHours}
@}

testQueryForResultSet
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}

testQueryForResultSetPage
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}
@if( $$:classHours ){
and class_hours in(#{classHours})
@}
order by id

testQueryList
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}
@if( $$:classHours ){
and class_hours in(#{classHours})
@}
order by id

testQueryListWithRowMapper
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}
@if( $$:classHours ){
and class_hours in(#{classHours})
@}
order by id

testQueryOne
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}
@if( $$:classHours ){
and class_hours in(#{classHours})
@}
order by id

testQueryMap
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}
@if( $$:classHours ){
and class_hours in(#{classHours})
@}
order by id

testQueryMapPage
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}
@if( $$:classHours ){
and class_hours in(#{classHours})
@}
order by id


testQueryListOne2One
====
select stu.*,c.* from student stu,student_course sc,course c 
where stu.id=sc.student_id and  c.id=sc.course_id 
@if( $$:name ){
and stu.name in (#{name})
@}
order by stu.id

testQueryListOne2OnePage
====
select stu.*,c.* from student stu,student_course sc,course c
where stu.id=sc.student_id and  c.id=sc.course_id
@if( $$:name ){
and stu.name in (#{name})
@}
order by stu.id

testQueryListOne2OnePageCount
====
select count(1) from student stu,student_course sc,course c
where stu.id=sc.student_id and  c.id=sc.course_id
@if( $$:name ){
and stu.name in (#{name})
@}
order by stu.id


testQueryListOne2Many
====
select 
stu.*,
c.* 
from student stu,student_course sc,course c
where stu.id=sc.student_id and  c.id=sc.course_id
@if( $$:name ){
and stu.name in (#{name})
@}
order by stu.id


testQueryListOne2ManyPage
====
select
stu.*,
c.*
from student stu,student_course sc,course c
where stu.id=sc.student_id and c.id=sc.course_id
@if( $$:name ){
and stu.name in (#{name})
@}
@if( $$:ids ){
and stu.id in (#{ids})
@}
order by stu.id

getPageIdList
====
select
stu.id as `value`
from student stu,student_course sc,course c
where stu.id=sc.student_id and  c.id=sc.course_id
@if( $$:name ){
and stu.name in (#{name})
@}
order by stu.id

testInsertWithMd
====
INSERT INTO `course` (
`name`,
`class_hours`,
`creatime`
)
VALUES
(
#{name},
#{classHours},
#{creatime}
)


testUpdateWithMd
====
UPDATE
`course`
SET
`class_hours` = #{classHours},
`creatime` = #{creatime}
WHERE `name` = #{name}

testDeleteWithMd
====
DELETE
FROM
`course`
WHERE `name` = #{name}


testExeSqlScript
====
INSERT INTO `course` (
`name`,
`class_hours`,
`creatime`
)
VALUES
(
#{name},
#{classHours},
#{creatime}
);
select * from course;

select * 
from 
course 
where name=#{name};

UPDATE
`course`
SET
`class_hours` = #{classHours},
`creatime` = #{creatime}
WHERE `name` = #{name};

DELETE
FROM
`course`
WHERE `name` = #{name};


testStoredProc
====
{call query_course_proc(#{name},#{count})}

testStoredFunc
====
{#{count}= call query_course_cnt_func(#{name})}


