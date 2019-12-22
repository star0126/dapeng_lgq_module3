CREATE DATABASE boot_module;
USE boot_module;

#任务表
CREATE TABLE bm_task(
  task_id INT PRIMARY KEY AUTO_INCREMENT,
  task_name VARCHAR(30) ,
  task_type VARCHAR(10) ,
  task_creator VARCHAR(20) ,
  creator_dept INT ,
  creat_time DATETIME DEFAULT '1970-01-01 01:01:01',
  end_time DATETIME DEFAULT '1970-01-01 01:01:01',
  plan_start_time DATETIME DEFAULT '1970-01-01 01:01:01',
  plan_end_time DATETIME DEFAULT '1970-01-01 01:01:01',
  task_executor VARCHAR(20),
  task_status VARCHAR(10),
  task_spec TEXT
);

#部门表
CREATE TABLE bm_dept(
  dept_id INT PRIMARY KEY AUTO_INCREMENT,
  dept_name VARCHAR(15),
  parent_id INT
);

#员工表
CREATE TABLE bm_emp(
  emp_id VARCHAR(20) PRIMARY KEY,
  emp_pwd VARCHAR(15) NOT NULL ,
  emp_name VARCHAR(15) ,
  emp_sex VARCHAR(5) ,
  emp_dept INT ,
  emp_role VARCHAR(10),
  emp_status VARCHAR(10) ,
  emp_email VARCHAR(30),
  emp_phone VARCHAR(20),
  jur_ide INT DEFAULT 0
);

#信息展示表
CREATE TABLE bm_task_info(
  task_id INT PRIMARY KEY AUTO_INCREMENT,
  task_name VARCHAR(30) ,
  task_type VARCHAR(10) ,
  task_creator VARCHAR(20) ,
  creator_name VARCHAR(15) ,
  creator_dept INT ,
  dept_name VARCHAR(15),
  creat_time DATETIME DEFAULT '1970-01-01 01:01:01',
  end_time DATETIME DEFAULT '1970-01-01 01:01:01',
  plan_start_time DATETIME DEFAULT '1970-01-01 01:01:01',
  plan_end_time DATETIME DEFAULT '1970-01-01 01:01:01',
  task_executor VARCHAR(20),
  executor VARCHAR(15) ,
  task_status VARCHAR(10),
  task_spec TEXT
);


 insert into bm_task_info
(
select
task_id,task_name,task_type,task_creator,
(select emp_name from bm_emp e where e.emp_id=bm_task.task_creator) creator_name,
creator_dept,
(select dept_name from bm_dept d where d.dept_id=bm_task.creator_dept) dept_name,
creat_time,end_time,plan_start_time,plan_end_time,task_executor,
(select  emp_name from bm_emp e where e.emp_id=bm_task.task_executor ) executore,
task_status,task_spec from bm_task
);



#创建任务查询表


#表中插入数据
INSERT INTO bm_dept(dept_id,dept_name,parent_id) VALUES(NULL,'总经理',NULL);
INSERT INTO bm_dept(dept_id,dept_name,parent_id) VALUES(NULL,'运营部',1),(NULL,'销售部',1);
INSERT INTO bm_dept(dept_id,dept_name,parent_id) VALUES(NULL,'运营一组',2),(NULL,'运营二组',2);
INSERT INTO bm_dept(dept_id,dept_name,parent_id) VALUES(NULL,'销售一组',3),(NULL,'销售二组',3);

INSERT INTO bm_emp(emp_id,emp_pwd,emp_name,emp_sex,emp_dept,emp_role,emp_status,
emp_email,emp_phone,jur_ide) VALUES('YD20191210121246001','123456','张三','男',2,
'项目经理','正式','12345@123.com','13753889999',8),('XD20191210122046002','123456',
'李四','男',3,'项目经理','正式','12345@123.com','13725256969',8);

#运营部以YD开头
#销售部以XD开头
INSERT INTO bm_emp(emp_id,emp_pwd,emp_name,emp_sex,emp_dept,emp_role,emp_status,
emp_email,emp_phone,jur_ide) VALUES(
'YD20191210133746003','123456','王五','男',4,'组长','正式','12345@123.com','12535368487',6),(
'YD20191210134100004','123456','赵六','男',5,'组长','正式','12345@123.com','12535364486',6),(
'XD20191210134503005','123456','王芸','女',6,'组长','正式','12345@123.com','12835354239',6),(
'XD20191210134905006','123456','刘美','女',7,'组长','正式','12345@123.com','15835361238',6),(
'YD20191210135223007','123456','魏荷','女',4,'职员','正式','12345@123.com','15825678562',2),(
'YD20191210135520008','123456','杜婉','女',4,'职员','正式','12345@123.com','15826348828',2),(
'YD20191210135656009','123456','赵伊','女',5,'职员','正式','12345@123.com','12588556611',2),(
'YD20191210135800010','123456','吴文','男',5,'职员','正式','12345@123.com','12828369685',2),(
'XD20191210140030011','123456','刘浩','男',6,'职员','正式','12345@123.com','12132353639',2),(
'XD20191210140352012','123456','王琦','女',6,'职员','正式','12345@123.com','12186898482',2),(
'XD20191210140555013','123456','单桐','女',7,'职员','正式','12345@123.com','12186898482',2),(
'XD20191210140744014','123456','姚柯','男',7,'职员','正式','12345@123.com','12186898482',2);

INSERT INTO bm_task(task_id,task_name,task_type,task_creator,creator_dept,creat_time,end_time,
plan_start_time,plan_end_time,task_executor,task_status,task_spec) VALUES(
NULL,'销售OA管理系统','销售类型','XD20191210134503005',6,'2018-1-12 14:00:00','2018-2-12 14:00:00',
'2018-1-15 14:00:00','2018-2-10 14:00:00','XD20191210140352012','已完成',NULL),(
NULL,'销售CRM管理系统','销售类型','XD20191210134503005',6,'2018-5-10 14:00:00','2018-6-12 14:00:00',
'2018-5-15 14:00:00','2018-6-10 14:00:00','XD20191210140030011','已完成',NULL);

INSERT INTO bm_task(task_id,task_name,task_type,task_creator,creator_dept,creat_time,end_time,
plan_start_time,plan_end_time,task_executor,task_status,task_spec) VALUES(
NULL,'销售项目管理系统','销售类型','XD20191210134905006',7,'2018-1-12 14:00:00','2018-2-12 14:00:00',
'2018-1-15 14:00:00','2018-2-10 14:00:00','XD20191210140555013','已完成',NULL),(
NULL,'销售电子订单管理系统','销售类型','XD20191210134905006',7,'2018-5-10 14:00:00','2018-6-12 14:00:00',
'2018-5-15 14:00:00','2018-6-10 14:00:00','XD20191210140744014','已完成',NULL);

INSERT INTO bm_task(task_id,task_name,task_type,task_creator,creator_dept,creat_time,end_time,
plan_start_time,plan_end_time,task_executor,task_status,task_spec) VALUES(
NULL,'运营OA管理系统','运营类型','YD20191210133746003',4,'2018-2-1 09:10:10','2018-2-12 12:10:10',
'2018-2-5 12:10:10','2018-2-12 12:00:10','YD20191210135223007','已完成',NULL),(
NULL,'运营电子订单管理系统','运营类型','YD20191210133746003',4,'2018-7-15 15:30:59','2018-7-28 10:30:59',
'2018-7-16 15:30:59','2018-7-20 15:30:59','YD20191210135520008','已完成',NULL);


INSERT INTO bm_task(task_id,task_name,task_type,task_creator,creator_dept,creat_time,end_time,
plan_start_time,plan_end_time,task_executor,task_status,task_spec) VALUES(
NULL,'运营ABB电力管理系统','运营类型','YD20191210134100004',5,'2018-12-1 09:08:09','2018-12-15 09:19:39',
'2018-12-2 09:08:09','2018-12-16 09:00:00','YD20191210135800010','已逾期','逾期完成'),(
NULL,'运营顺丰物流系统','运营类型','YD20191210134100004',5,'2018-11-25 16:20:59','2018-12-25 12:20:59',
'2018-11-26 09:20:30','2018-12-1 16:20:59','YD20191210135656009','已完成',NULL);


INSERT INTO bm_task(task_id,task_name,task_type,task_creator,creator_dept,creat_time,end_time,
plan_start_time,plan_end_time,task_executor,task_status,task_spec) VALUES(
NULL,'销售OA管理系统','销售类型','XD20191210134503005',6,'2018-1-12 14:00:00','2018-2-12 14:00:00',
'2018-1-15 14:00:00','2018-2-10 14:00:00','XD20191210140352012','已完成',NULL)






#项目过程
SELECT task_id,task_name,task_type,task_creator,(SELECT emp_name FROM bm_emp 
WHERE bm_emp.`emp_id`=bm_task.`task_creator`) AS creatorName,creator_dept,
creat_time,end_time,plan_start_time,plan_end_time,
task_executor,(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_executor`) AS executor,
task_status,task_spec FROM `bm_task` 

SELECT task_id,task_name,task_type,task_creator,(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_creator`)
 AS creatorName,creator_dept,(SELECT d.dept_name FROM bm_dept d WHERE d.dept_id=`bm_task`.`creator_dept` ) AS deptName,
 creat_time,end_time,plan_start_time,plan_end_time, task_executor,
(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_executor`) AS executor, task_status,task_spec FROM `bm_task` LIMIT 0,3;


SELECT emp_id,emp_pwd,emp_name,emp_sex,emp_dept,
(SELECT d.dept_name FROM bm_dept d WHERE d.dept_id=`bm_emp`.`emp_dept` ) AS deptName,
emp_role,emp_status,emp_email,emp_phone,jur_ide FROM bm_emp WHERE emp_role IN ('组长');


SELECT task_id,task_name,task_type,task_creator,(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_creator`) 
AS creatorName,creator_dept, (SELECT d.dept_name FROM bm_dept d WHERE d.dept_id=`bm_task`.`creator_dept` ) AS deptName, 
creat_time,end_time,plan_start_time,plan_end_time, task_executor,
(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_executor`) AS executor, 
task_status,task_spec FROM `bm_task` WHERE /*类型*/ AND task_type=? /*状态*/ AND task_status =? LIMIT ?,? ;




SELECT task_id,task_name,task_type,task_creator,(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_creator`) 
AS creatorName,creator_dept, (SELECT d.dept_name FROM bm_dept d WHERE d.dept_id=`bm_task`.`creator_dept` ) AS deptName, 
creat_time,end_time,plan_start_time,plan_end_time, task_executor,(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_executor`) 
AS executor, task_status,task_spec FROM `bm_task` WHERE UNIX_TIMESTAMP(creat_time) >= 1514764800000 
AND UNIX_TIMESTAMP(creat_time) <= 1519862400000 LIMIT 0,3 ; 

SELECT creat_time FROM bm_task WHERE creat_time<=FROM_UNIXTIME(1514764800000)

SELECT FROM_UNIXTIME(1514764800000);

SELECT FROM_UNIXTIME(1514764800, '%Y-%m-%d %H:%i:%S')

SELECT UNIX_TIMESTAMP(creat_time)  FROM `bm_task`  WHERE  UNIX_TIMESTAMP(creat_time)>=1514764800000 LIMIT 0,3
SELECT * FROM bm_task WHERE creat_time >="2018-1-1";
1514764800000(LONG), 1519862400000(LONG)
2018-1-1             2018-3-1


SELECT task_id,task_name,task_type,task_creator,(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_creator`) 
AS creatorName,creator_dept, (SELECT d.dept_name FROM bm_dept d WHERE d.dept_id=`bm_task`.`creator_dept` ) AS deptName, 
creat_time,end_time,plan_start_time,plan_end_time, task_executor,(SELECT emp_name FROM bm_emp WHERE bm_emp.`emp_id`=bm_task.`task_executor`) 
AS executor, task_status,task_spec FROM `bm_task` ORDER BY 'creat_time' DESC LIMIT 0,3

DELETE FROM bm_task WHERE task_id IN (12,13);


SELECT task_id,task_creator,task_type,plan_start_time,creat_time,task_executor,plan_end_time,task_name,end_time,creator_dept,task_spec,task_status 
FROM bm_task WHERE (creat_time >= '2018-01-01 00:00:00' AND creat_time <= '2018-03-01 00:00:00') LIMIT 0,3 