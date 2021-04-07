testUpdateStudent
====
update student set
@if($$:name){
name=#{name},
@}
age=${age}
where id=#{id}

testSeletStudent
====
select * from student where 1=1
@if($$:lname){
  and name=#{lname}
@}





