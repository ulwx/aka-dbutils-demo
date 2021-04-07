getRSet
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}


getRSetPage
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}
@if( $$:classHours ){
and class_hours in(#{classHours})
@}
order by id

getOneCourse
===
SELECT
NAME,
class_hours    /* 和 class_hours as classHours 等效，classHours对应到JavaBean的属性名 */
FROM
course
WHERE 1 = 1
@if( $$:name ){
and name like #{name%}
@}

getCoursesByIds
===
select id,name ,class_hours as classHours,creatime from course where 1=1
@if( $$:name ){
and name like #{name%}
@}
@if($$:ids){
and id in (#{ids})
@}

getCouseList
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}

getCouseListPage
====
select * from course where 1=1
@if( $$:name ){
and name like #{name%}
@}

getCouseListPageCount
====
select count(1) from course where 1=1
@if( $$:name ){
and name like #{name%}
@}


getCouseListOne2One
====
SELECT
stu.name,
stu.id,
age AS `stu.age`,
birth_day AS `stu.birth_day`,  /* 和 birth_day AS `stu.birthDay` 等效 */
c.*
FROM
student stu,
student_course sc,
course c
WHERE stu.id = sc.student_id
AND c.id = sc.course_id
@if( $$:name ){
and stu.name like #{name%}
@}
order by stu.id

getCouseListOne2OnePage
====
select stu.*,c.* from student stu,student_course sc,course c
where stu.id=sc.student_id and  c.id=sc.course_id
@if( $$:name ){
and stu.name like #{name%}
@}
order by stu.id

getCouseListOne2OnePageForCount
====
select count(1)
from student stu,student_course sc,course c
where stu.id=sc.student_id and  c.id=sc.course_id
@if( $$:name ){
and stu.name  like #{name%}
@}

getCouseListOne2Many
====
select
stu.*,
c.*
from student stu,student_course sc,course c
where stu.id=sc.student_id and  c.id=sc.course_id
@if( $$:name ){
and stu.name  like #{name%}
@}
order by stu.id


addCourse
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


addCourseReturnKey
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

updateCourse
====
UPDATE
`course`
SET
`name` = #{name},
`class_hours` = #{classHours},
`creatime` = #{creatime}
WHERE `id` = #{id}

