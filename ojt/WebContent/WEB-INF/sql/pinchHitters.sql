
DROP TABLE company CASCADE CONSTRAINTS;

CREATE TABLE company (
       c_ID                 VARCHAR(20) NOT NULL,
       cname                VARCHAR(20) NULL,
       passwd               VARCHAR(20) NULL,
       cnum                 VARCHAR(20) NULL,
       boss                 VARCHAR(20) NULL,
       address              VARCHAR(100) NULL,
       mname                VARCHAR(20) NULL,
       mphone               VARCHAR(20) NULL,
       memail               VARCHAR(30) NULL,
       c_date               DATE NULL,
       address2             varchar(100) NULL,
       PRIMARY KEY (c_ID)
);

insert into company(c_ID, cname, passwd, cnum, boss, address, mname, mphone, memail, c_date, address2)
values('회사', '회', '1234', '1', '정일훈', '종로', '정일훈', '123-123-123', '종로@gmail.com', sysdate, '종로뒷골목')

DROP TABLE users CASCADE CONSTRAINTS;

CREATE TABLE users (
       u_ID                 VARCHAR(20) NOT NULL,
       name                 VARCHAR(20) NULL,
       passwd               VARCHAR(20) NULL,
       age                  NUMBER DEFAULT 0 NULL,
       sex                  CHAR(10) NULL,
       phone                VARCHAR(20) NULL,
       address              VARCHAR(100) NULL,
       zipcode              VARCHAR(10) NULL,
       email                VARCHAR(30) NULL,
       u_date               DATE NULL,
       address2             varchar(100) NULL,
       PRIMARY KEY (u_ID)
);


DROP TABLE p_request CASCADE CONSTRAINTS;

CREATE TABLE p_request (
       pr_num               NUMBER DEFAULT 0 NOT NULL,
       u_ID                 VARCHAR(20) NULL,
       c_ID                 VARCHAR(20) NULL,
       pr_job               CHAR(5) NULL,
       pr_salary            NUMBER DEFAULT 0 NULL,
       pr_date              DATE NULL,
       pr_time              number(2) NULL,
       pr_endtime           number(2) NULL,
       pr_bonus             CHAR(5) NULL,
       PRIMARY KEY (pr_num), 
       FOREIGN KEY (c_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);


DROP TABLE u_review CASCADE CONSTRAINTS;

CREATE TABLE u_review (
       ur_num               NUMBER DEFAULT 0 NOT NULL,
       ur_title             VARCHAR(100) NULL,
       ur_content           VARCHAR(4000) NULL,
       ur_viewcnt           NUMBER DEFAULT 0 NULL,
       u_ID                 VARCHAR(20) NOT NULL,
       c_ID                 VARCHAR(20) NOT NULL,
       PRIMARY KEY (ur_num), 
       FOREIGN KEY (C_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);


DROP TABLE ur_comment CASCADE CONSTRAINTS;

CREATE TABLE ur_comment (
       urc_num              NUMBER DEFAULT 0 NOT NULL,
       urc_content          VARCHAR(1000) NULL,
       urc_date             DATE NULL,
       urc_writer           VARCHAR(20) NULL,
       ur_num               NUMBER DEFAULT 0 NOT NULL,
       PRIMARY KEY (urc_num), 
       FOREIGN KEY (ur_num)
                             REFERENCES u_review
);


DROP TABLE c_rate CASCADE CONSTRAINTS;

CREATE TABLE c_rate (
       cr_num               NUMBER DEFAULT 0 NOT NULL,
       u_ID                 VARCHAR(20) NOT NULL,
       cr_content           VARCHAR(1000) NULL,
       cr_star              NUMBER DEFAULT 0 NULL,
       cr_ratecode          char(3) NULL,
       c_ID                 VARCHAR(20) NOT NULL,
       PRIMARY KEY (cr_num), 
       FOREIGN KEY (c_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);


DROP TABLE message CASCADE CONSTRAINTS;

CREATE TABLE message (
       m_num                NUMBER DEFAULT 0 NOT NULL,
       receiver             VARCHAR(20) NULL,
       m_title              VARCHAR(100) NULL,
       m_content            VARCHAR(4000) NULL,
       m_date               DATE NULL,
       m_check              CHAR(1) NULL,
       u_ID                 VARCHAR(20) NULL,
       c_ID                 VARCHAR(20) NULL,
       PRIMARY KEY (m_num), 
       FOREIGN KEY (c_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);


DROP TABLE freeboard CASCADE CONSTRAINTS;

CREATE TABLE freeboard (
       f_num                NUMBER DEFAULT 0 NOT NULL,
       f_title              VARCHAR(100) not NULL,
       f_filename           VARCHAR(20) NULL,
       f_filesize           NUMBER(7) NULL,
       f_content            VARCHAR(4000) not NULL,
       f_viewcnt            NUMBER DEFAULT 0 not NULL,
       f_writer             varchar(20) not null,
       f_date               DATE NULL,
       
       PRIMARY KEY (f_num)
);


DROP TABLE a_history CASCADE CONSTRAINTS;

CREATE TABLE a_history (
       ah_num               NUMBER DEFAULT 0 NOT NULL,
       ah_name              VARCHAR(20) NULL,
       ah_date              DATE NULL,
       ah_time              number(2) NULL,
       ah_endtime           number(2) NULL,
       ah_salary            NUMBER DEFAULT 0 NULL,
       u_ID                 VARCHAR(20) NOT NULL,
       c_ID                 VARCHAR(20) NOT NULL,
       PRIMARY KEY (ah_num), 
       FOREIGN KEY (c_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);


DROP TABLE signal CASCADE CONSTRAINTS;

CREATE TABLE signal (
       s_num                NUMBER DEFAULT 0 NOT NULL,
       receiver             VARCHAR(20) NULL,
       s_content            VARCHAR(100) NULL,
       s_date               DATE NULL,
       s_check              CHAR(1) NULL,
       c_ID                 VARCHAR(20) NULL,
       u_ID                 VARCHAR(20) NULL,
       PRIMARY KEY (s_num), 
       FOREIGN KEY (c_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);


DROP TABLE admin CASCADE CONSTRAINTS;

CREATE TABLE admin (
       a_ID                 VARCHAR(20) NOT NULL,
       passwd               VARCHAR(20) NULL,
       PRIMARY KEY (a_ID)
);


DROP TABLE declaration CASCADE CONSTRAINTS;

CREATE TABLE declaration (
       d_num                NUMBER DEFAULT 0 NOT NULL,
       d_title              VARCHAR(100) NULL,
       d_content            VARCHAR(4000) NULL,
       d_viewcnt            NUMBER DEFAULT 0 NULL,
       d_date               DATE NULL,
       u_ID                 VARCHAR(20) NULL,
       c_ID                 VARCHAR(20) NULL,
       a_ID                 VARCHAR(20) NULL,
       PRIMARY KEY (d_num), 
       FOREIGN KEY (a_ID)
                             REFERENCES admin, 
       FOREIGN KEY (c_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);


DROP TABLE f_comment CASCADE CONSTRAINTS;

CREATE TABLE f_comment (
       fc_num               NUMBER NOT NULL,
       fc_content           VARCHAR(1000) not NULL,
       f_num                NUMBER DEFAULT 0 NOT NULL,
       fc_date              DATE not NULL,
       fc_writer            CHAR(20) not NULL,
       PRIMARY KEY (fc_num), 
       FOREIGN KEY (f_num)
                             REFERENCES freeboard
);


DROP TABLE a_request CASCADE CONSTRAINTS;

CREATE TABLE a_request (
       ar_num               NUMBER DEFAULT 0 NOT NULL,
       u_ID                 VARCHAR(20) NOT NULL,
       c_ID                 VARCHAR(20) NOT NULL,
       ar_name              VARCHAR(20) NULL,
       ar_job               CHAR(5) NULL,
       ar_salary            NUMBER DEFAULT 0 NULL,
       ar_date              DATE NULL,
       ar_time              number(2) NULL,
       ar_endtime           number(2) NULL,
       ar_bonus             CHAR(5) NULL,
       PRIMARY KEY (ar_num), 
       FOREIGN KEY (c_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);


DROP TABLE notice CASCADE CONSTRAINTS;

CREATE TABLE notice (
       n_num                NUMBER DEFAULT 0 NOT NULL,
       n_title              VARCHAR(100) NULL,
       n_content            VARCHAR(4000) NULL,
       n_viewcnt            NUMBER DEFAULT 0 NULL,
       n_date               DATE NULL,
       a_ID                 VARCHAR(20) NOT NULL,
       PRIMARY KEY (n_num), 
       FOREIGN KEY (a_ID)
                             REFERENCES admin
);


DROP TABLE n_comment CASCADE CONSTRAINTS;

CREATE TABLE n_comment (
       nc_num               NUMBER DEFAULT 0 NOT NULL,
       nc_content           VARCHAR(1000) NULL,
       nc_writer            VARCHAR(20) NULL,
       nc_date              DATE NULL,
       n_num                NUMBER DEFAULT 0 NULL,
       PRIMARY KEY (nc_num), 
       FOREIGN KEY (n_num)
                             REFERENCES notice
);


DROP TABLE qa CASCADE CONSTRAINTS;

CREATE TABLE qa (
       q_num                NUMBER DEFAULT 0 NOT NULL,
       q_category           CHAR(20) not NULL,
       q_title              VARCHAR(100) not NULL,
       q_content            VARCHAR(4000) not NULL,
       q_filename           VARCHAR(20) NULL,
       q_date               date not null,
       q_grpno              NUMBER DEFAULT 0 not NULL,
       q_indent             NUMBER DEFAULT 0 not NULL,
       q_ansnum             NUMBER DEFAULT 0 not NULL,
       q_refnum             NUMBER DEFAULT 0 not NULL,
       q_writer             varchar(20) not null,
       primary key(q_num)
);


DROP TABLE faq_company CASCADE CONSTRAINTS;

CREATE TABLE faq_company (
       fqc_num              NUMBER DEFAULT 0 NOT NULL,
       fqc_title            VARCHAR(1000) not NULL,
       fqc_content          VARCHAR(1000) not NULL,
       fqc_category         CHAR(20) not NULL,
       PRIMARY KEY (fqc_num)
);


DROP TABLE faq_users CASCADE CONSTRAINTS;

CREATE TABLE faq_users (
       fqu_num              NUMBER DEFAULT 0 NOT NULL,
       fqu_title            VARCHAR(1000) not NULL,
       fqu_content          VARCHAR(1000) not NULL,
       fqu_category         CHAR(20) not NULL,
       PRIMARY KEY (fqu_num)
);


DROP TABLE u_rate CASCADE CONSTRAINTS;

CREATE TABLE u_rate (
       u_num                NUMBER DEFAULT 0 NOT NULL,
       u_ID                 VARCHAR(20) NOT NULL,
       u_content            VARCHAR(1000) NULL,
       u_star               NUMBER DEFAULT 0 NULL,
       c_ID                 VARCHAR(20) NOT NULL,
       u_ratecode           char(3) NULL,
       PRIMARY KEY (u_num), 
       FOREIGN KEY (c_ID)
                             REFERENCES company, 
       FOREIGN KEY (u_ID)
                             REFERENCES users
);



