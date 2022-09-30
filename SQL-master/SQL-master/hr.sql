DROP TABLE EMPLOYEES;

CREATE TABLE EMPLOYEES(
 ID NUMBER(19),
 FIRST_NAME VARCHAR(255),
 LAST_NAME VARCHAR(255),
 ADDRESS VARCHAR(255),
 Department_id NUMBER(10),
 SALARY NUMBER(10),
 Hired_Date DATE NOT NULL,
 PRIMARY KEY (ID)
 
);

INSERT INTO EMPLOYEES VALUES ('105','Rose','Kantata', '2736 Kooter Lane','80','1000',DATE '2020-10-05');
INSERT INTO EMPLOYEES VALUES ('1000','Mike','Togglie', '111 Cool Dr','50','2500',DATE '2010-05-05');
INSERT INTO EMPLOYEES VALUES ('1999','Dana', 'Whitley', '464 Gorsuch Drive','60','25000',DATE '2010-05-05');
INSERT INTO EMPLOYEES VALUES ('1','Robin', 'Cash', '64 Zella Park','80','35000',DATE '2011-05-05');
INSERT INTO EMPLOYEES VALUES ('10','Chary', 'Mess', '112 Yellow Hill','50','100000',DATE '2008-05-05');

SELECT * FROM employees;

SELECT first_name FROM employees where ID < 10 AND(department_id=50 OR department_id=80);

SELECT * FROM employees where first_name LIKE 'R%';

SELECT FIRST_NAME,SALARY,Department_id FROM employees where (salary between 1000 and 25000) AND (Department_id BETWEEN 50 AND 60);

SELECT * FROM employees WHERE department_id not in (50,80);

SELECT * FROM employees WHERE department_id in (50,80);

SELECT * FROM employees where Hired_Date < to_date ('01-01-2010','DD-MM-YYYY');

INSERT INTO EMPLOYEES VALUES ('2','Mathew', 'Mess', '112 Yellow Hill','50','100000',DATE '2008-05-05');

select * from employees where last_name ='Mess' and FIRST_NAME ='Mathew';

SELECT distinct last_name,department_id FROM employees ;

select department_id,department_id*1000 from employees;

select FIRST_NAME as"EmployeeName",Salary as REGULAR_SALARY, salary+1000 as "SALARY + BONUS" from employees;

SELECT FIRST_NAME,Salary,Department_id FROM employees ORDER BY Salary,FIRST_NAME desc;

/*    FUNCTIONS */

CREATE TABLE JOBS{
job_name number(10),
job_desc VARCHAR2 ,
min_salary number(10),
max_salary number(10),
 PRIMARY KEY (job_name)
}

insert into jobs values ('DEVloper','Develops the application','45000','65000');  
insert into jobs values ('Tester','Test the application','145000','265000');  
insert into jobs values ('SM',' Manages application','145000','855000');  
insert into jobs values ('PO','OWNS the application','335000','6695000');  

select * from jobs;

select substr(job_desc,1,6) from jobs;

select job_desc,length(job_desc) from jobs;

/*Concat*/
select concat(job_name,min_salary) from jobs;

select concat('Designation in Team : ',job_name), CONCAT('minimum salary : ',min_salary) from jobs;

select 'Designation'||'-->'||job_name||'    Their Salary are'||min_salary from jobs;

select upper(job_name),lower(job_desc) from jobs;

/*It wontn't fetch the results since value at the column was Tester */
select job_name from jobs where job_name='tester';

/*It will fetch the results */
select job_name from jobs where lower(job_name)='tester';

select job_name,instr(job_name,'e') from jobs;

select job_name,instr(job_name,'e') from jobs where instr(job_name,'e')='7';

select rpad(job_name,10,'/'),lpad(job_name,10,'/') from jobs;

select TO_CHAR(max_salary,'999.99') FROM jobs;

select *from employees;

select first_name, last_name,to_char(HIRED_DATE,'DD-MON-YYYY') from employees;

select first_name, last_name,to_char(HIRED_DATE,'DD-MON-YYYY') from employees where to_char(HIRED_DATE,'MON') = 'MAY';

select * from dual;

select ROUND(15.193,1) "Round" FROM DUAL;

select TRUNC(15.79,0) "Truncate" FROM DUAL;

SELECT to_char(SYSDATE,'DD/MON/YYYY')  FROM DUAL;

select SYSDATE,
       LAST_DAY(SYSDATE) "Last",
       LAST_DAY(SYSDATE) - SYSDATE "Days Left"
  FROM DUAL;
  
SELECT SYSTIMESTAMP AT TIME ZONE 'UTC' FROM DUAL;

SELECT to_char(SYSTIMESTAMP,'DD/MON/YYYY') FROM DUAL;
  
  
select sysdate,Hired_Date, round(months_between(sysdate,Hired_Date),1) from employees ;

SELECT sysdate,add_months(sysdate,1) FROM DUAL;
/*                         Joints Concepts                               */
DROP TABLE Departments;
CREATE TABLE Departments(
Department_id NUMBER(10),
Department_name VARCHAR(50),
Department_Location VARCHAR(50),
Primary key(Department_id)

);
select *from employees;
Insert  into Departments values('50','Retail','sirusero');
Insert  into Departments values('60','Banking','Chennaione');
Insert  into Departments values('80','Insurance','DLF');
Insert  into Departments values('100','HealthScience','sirusero');

select * from Departments;
select *from employees;

INSERT INTO EMPLOYEES VALUES ('2000','Mathew', 'Whitley', '464 Gorsuch Drive','40','25000',DATE '2010-05-05');

/*Left join*/
select EMPLOYEES.FIRST_NAME,EMPLOYEES.LAST_NAME,departments.department_name from EMPLOYEES inner join Departments on employees.department_id = departments.department_id;

select EMPLOYEES.FIRST_NAME,EMPLOYEES.LAST_NAME,departments.department_name from EMPLOYEES left join Departments on employees.department_id = departments.department_id;

select EMPLOYEES.FIRST_NAME,EMPLOYEES.LAST_NAME,departments.department_name from EMPLOYEES right join Departments on employees.department_id = departments.department_id;

select EMPLOYEES.FIRST_NAME,EMPLOYEES.LAST_NAME,departments.department_name from EMPLOYEES full join Departments on employees.department_id = departments.department_id;

/*Aliases*/

select first_name, last_name ,department_name from employees emp full join departments dept on emp.department_id = dept.department_id;

/* ANSI Standart query for joins for left join*/
select first_name, last_name ,department_name
from employees,departments
where employees.department_id=departments.department_id(+);

DELETE from employees where first_name='Rose' and last_name='Kantata';
rollback; /*It will undo the previous transaction*/

DELETE from employees where first_name='Rose' and last_name='Kantata';
commit; /*you can't undo the previous transaction*/

update employees set  first_name='Maranamass' where last_name ='Cash';
commit;

/*How to add or remove columns from tables || Alter is DDL|| Can't rollback*/
alter table employees add(Permanent VARCHAR2(20)); 

alter table employees drop column Permanent ;  





