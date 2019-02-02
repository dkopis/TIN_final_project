-- Create table
/*create table USER_ACCOUNTS
(
USER_NAME VARCHAR2(30) not null,
GENDER    VARCHAR2(1) not null,
PASSWORD  VARCHAR2(30) not null,
primary key (USER_NAME)
);*/
 
-- Create table
create table BOOKS
(
    TITLE       VARCHAR2(400) not null,
    AUTHOR   VARCHAR2(400) not null,
    ISBN        NUMBER not null,
    primary key (ISBN)
) ;
 
-- Insert data: ---------------------------------------------------------------
 
/*insert into user_accounts (USER_NAME, GENDER, PASSWORD)
values ('tom', 'M', 'tom001');
 
insert into user_accounts (USER_NAME, GENDER, PASSWORD)
values ('jerry', 'M', 'jerry001');*/
 
insert into BOOKS (TITLE, AUTHOR, ISBN)
values ('Tatua¿ysta z Auschwitz', 'Heather Morris', 9788365973313);
 
insert into BOOKS (TITLE, AUTHOR, ISBN)
values ('Rodzanice', 'Katarzyna Puzyñska', 9788381690072);
 
-- Commit
Commit;

-----------------------------------------------------------------------------------------------------------------
--NEW VERSION
-----------------------------------------------------------------------------------------------------------------
-- Create table
create table USER_ACCOUNTS
(
USER_NAME VARCHAR2(30) not null,
GENDER    VARCHAR2(1) not null,
PASSWORD  VARCHAR2(30) not null,
RESP_LVL  VARCHAR2(3)  NOT NULL,
primary key (USER_NAME)
);
 
-- Create table
create table BOOKS
(
    TITLE       VARCHAR2(400) not null,
    BOOK_ID     NUMBER not null,
    ISBN        NUMBER not null,
    primary key (ISBN)
) ;
 
CREATE TABLE AUTHORS(
    AUTHOR_ID NUMBER,
    NAME    VARCHAR2(200) NOT NULL,
    
);
-- Insert data: ---------------------------------------------------------------
 
/*insert into user_accounts (USER_NAME, GENDER, PASSWORD)
values ('tom', 'M', 'tom001');
 
insert into user_accounts (USER_NAME, GENDER, PASSWORD)
values ('jerry', 'M', 'jerry001');*/
 
insert into BOOKS (TITLE, AUTHOR, ISBN)
values ('Tatua¿ysta z Auschwitz', 'Heather Morris', 9788365973313);
 
insert into BOOKS (TITLE, AUTHOR, ISBN)
values ('Rodzanice', 'Katarzyna Puzyñska', 9788381690072);
 
-- Commit
Commit;


