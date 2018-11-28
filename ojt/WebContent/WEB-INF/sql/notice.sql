drop table notice cascade constraint;

alter table notice
drop constraint fk_admin_a_ID;
--존재테이블 전체검색
select *
from all_all_tables;

select * from notice;

create table notice(
n_num number not null,
n_title varchar(100) null,
n_content varchar(4000) null,
n_viewcnt number default 0,
n_date date default sysdate,
a_ID varchar(20) null,

constraint pk_notice_n_num primary key(n_num),
constraint fk_admin_a_ID foreign key(a_ID) references admin(a_ID)
);

--read
select * from notice where n_num = ?;

--update
update notice set
n_content = ?;
n_writer = ?;
where n_num = ?;

--delete
delete from notice where n_num =?;

--list
select n_num, n_title, n_content, nc_date, r
from
	select n_num, n_title, n_content, n_date, as r
	from
		select n_num, n_title, n_content, to_char(n_date, yyyy-mm-dd) as n_date
			 from notice
		order by n_num desc
	
where r>=? and r>=?

--total
 select count(*) from notice