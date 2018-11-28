drop table declaration;

--create
create table declaration(
d_num number not null,
d_title varchar(100) null,
d_content varchar(4000) null,
d_viewcnt number default 0,
d_date date default sysdate,

--u_ID varchar(20) null,
--c_ID varchar(20) null,
a_ID varchar(20) null,

constraint pk_declaration_d_num primary key(d_num),
--constraint fk_declaration_u_ID foreign key(u_ID) references users(u_ID),
--constraint fk_declaration_c_ID foreign key(c_ID) references company(c_ID),
constraint fk_declaration_a_ID foreign key(a_ID) references admin(a_ID)
);

--insert
insert into declaration(d_num, d_title, d_content, d_viewcnt, d_date, u_ID, c_ID, a_ID)
values((SELECT NVL(MAX(d_num), 0)+1 FROM declaration),'신고테스트','신고테스트', 0, sysdate, null, null, 'admin')

--fk용 컬럼추가
alter table declaration add
(u_ID varchar(20) null);

alter table declaration add
(c_ID varchar(20) null);

--fk 추가
alter table declaration add
constraint fk_declaration_u_ID foreign key(u_ID) references users(u_ID);

alter table declaration add
constraint fk_declaration_c_ID foreign key(c_ID) references company(c_ID);

--read
select * from declaration where d_num = ?;

--update
update declaration set
d_title = ?;
d_content = ?;

--delete
delete from declaration where d_num =?;

--list
select d_num, d_title, d_content, d_viewcnt, d_date, r
from
	select d_num, d_title, d_content, d_viewcnt, d_date, as r
	from
		select d_num, d_title, d_content, d_viewcnt, to_char(d_date, yyyy-mm-dd) as d_date
			 from declaration
		order by d_num desc
	
where r>=? and r>=?

--total
 select count(*) from declaration