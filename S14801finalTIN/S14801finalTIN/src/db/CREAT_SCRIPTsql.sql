-- Create table
/*create table USER_ACCOUNTS
(
USER_NAME VARCHAR2(30) not null,
GENDER    VARCHAR2(1) not null,
PASSWORD  VARCHAR2(30) not null,
primary key (USER_NAME)
);
 
-- Create table
create table BOOKS
(
    TITLE       VARCHAR2(400) not null,
    AUTHOR   VARCHAR2(400) not null,
    ISBN        NUMBER not null,
    primary key (ISBN)
) ;
 
-- Insert data: ---------------------------------------------------------------
 
insert into user_accounts (USER_NAME, GENDER, PASSWORD)
values ('tom', 'M', 'tom001');
 
insert into user_accounts (USER_NAME, GENDER, PASSWORD)
values ('jerry', 'M', 'jerry001');
 
insert into BOOKS (TITLE, AUTHOR, ISBN)
values ('Tatua¿ysta z Auschwitz', 'Heather Morris', 9788365973313);
 
insert into BOOKS (TITLE, AUTHOR, ISBN)
values ('Rodzanice', 'Katarzyna Puzyñska', 9788381690072);
 
-- Commit
Commit;
*/
-----------------------------------------------------------------------------------------------------------------
--NEW VERSION
-----------------------------------------------------------------------------------------------------------------
--Drop tables
/*
drop table USER_ACCOUNTS;
drop table BOOKS;
drop table AUTHORS;
drop table BOOKS_AUTHORS;
drop table USER_RESP;
drop sequence author_seq;
*/
-- Create table
create table USER_ACCOUNTS
(
USER_NAME VARCHAR2(30) not null,
GENDER    VARCHAR2(1) not null,
PASSWORD  VARCHAR2(4000) not null,
RESP_LVL  NUMBER  NOT NULL,
primary key (USER_NAME)
);

create table USER_RESP(
    RESP_NAME VARCHAR2(100) not null,
    RESP_LVL  NUMBER not null
);
 
-- Create table
create table BOOKS
(
    TITLE       VARCHAR2(400) not null,
    ISBN        NUMBER not null,
    primary key (ISBN)
) ;
 
 
CREATE TABLE AUTHORS(
    AUTHOR_ID NUMBER,
    NAME    VARCHAR2(200) NOT NULL,
    PRIMARY KEY(AUTHOR_ID)
);

create table BOOKS_AUTHORS(
    AUTHOR_ID not null,
    ISBN NUMBER not null,
    foreign key(AUTHOR_ID) REFERENCES AUTHORS(AUTHOR_ID) ,
    foreign key(ISBN) REFERENCES BOOKS(ISBN)
 );
 
 
 CREATE SEQUENCE author_seq
  MINVALUE 6
  MAXVALUE 999999999999999999999999999
  START WITH 6
  INCREMENT BY 1
  CACHE 20;
  
-- Insert data: ---------------------------------------------------------------
-- delete from user_accounts;
insert into user_accounts (USER_NAME, GENDER, PASSWORD, RESP_LVL)
values ('ADMIN', 'M', 'a9e7f4848e40deb03cba8edd294d3a17', 1);
insert into user_accounts (USER_NAME, GENDER, PASSWORD, RESP_LVL)
values ('jerryuSER', 'M', 'aa42efed41049ea7f42ac270ac1657b8',2);
insert into USER_RESP (RESP_NAME, RESP_LVL)
values ('Administrator',1);
insert into USER_RESP (RESP_NAME, RESP_LVL)
values ('User',2);

insert into BOOKS (TITLE, ISBN)
values ('Tatua¿ysta z Auschwitz', 9788365973313);
insert into BOOKS (TITLE, ISBN)
values ('Rodzanice', 9788381690072);
insert into BOOKS (TITLE, ISBN)
values ('Pan Tadeusz',  9788388736919);
insert into BOOKS (TITLE, ISBN)
values ('Dziady', 9788373895904);
insert into BOOKS (TITLE, ISBN)
values ('Niezniszczalny', 9788380916937);

insert into AUTHORS (AUTHOR_ID, NAME)
values (1, 'Adam Mickiewicz');
insert into AUTHORS (AUTHOR_ID, NAME)
values (2, 'Katarzyna Puzyñska');
insert into AUTHORS (AUTHOR_ID, NAME)
values (3, 'Heather Morris');
insert into AUTHORS (AUTHOR_ID, NAME)
values (4, 'Cezary Gutowski');
insert into AUTHORS (AUTHOR_ID, NAME)
values (5, 'Aldona Marciniak');

insert into BOOKS_AUTHORS (AUTHOR_ID, ISBN)
values (3, 9788365973313);
insert into BOOKS_AUTHORS (AUTHOR_ID, ISBN)
values (2, 9788381690072);
insert into BOOKS_AUTHORS (AUTHOR_ID, ISBN)
values (1,  9788388736919);
insert into BOOKS_AUTHORS (AUTHOR_ID, ISBN)
values (1, 9788373895904);
insert into BOOKS_AUTHORS (AUTHOR_ID, ISBN)
values (4, 9788380916937);
insert into BOOKS_AUTHORS (AUTHOR_ID, ISBN)
values (5, 9788380916937);
-- Commit
Commit;

SELECT * FROM AUTHORS;
select a.title, b.name, a.ISBN from books a, authors b, books_authors ab where a.isbn=ab.isbn and b.author_id=ab.author_id ;

select b.author_id, b.name from authors b;
select b.* from books_authors b;
select a.title from books a where a.isbn=9788373895904;
    SELECT GET_BOOK_AUTHORS(A.ISBN) FROM BOOKS A;
    
    select author_seq.NEXTVAL from dual;
   select * from  user_accounts;
    select a.title,
            b.name, 
            a.ISBN 
        from books a, 
            authors b, 
            books_authors ab 
        where a.isbn=ab.isbn 
            and b.author_id=ab.author_id 
            and a.ISBN=9788373895904;

Select a.User_Name, a.Password, a.Gender, b.resp_name from User_Accounts a, User_resp b  where a.resp_lvl=b.resp_lvl;
select * from User_Accounts;

select a.title, GET_BOOK_AUTHORS(A.ISBN) name, a.ISBN from books a where a.isbn=(select max(ab.isbn) from books_authors ab where ab.isbn=a.isbn);

Select a.User_Name, a.Password, a.Gender, b.resp_name from User_Accounts a, User_resp b  where a.resp_lvl=b.resp_lvl;