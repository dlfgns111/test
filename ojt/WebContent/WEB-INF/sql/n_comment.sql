--create
create table n_comment(
nc_num number not null,
nc_content varchar(1000) null,
nc_date date default sysdate,
nc_writer varchar(20),
n_num number null,

constraint pk_n_comment_nc_num primary key(nc_num),
constraint fk_n_comment_n_num foreign key(n_num) references notice(n_num)
);

--read
select * from n_comment where nc_num = ?;

--update
update n_comment set
nc_content = ?;
nc_writer = ?;

--delete
delete from n_comment where nc_num =?;

--list
select nc_num, nc_content, nc_date, r
from
	select nc_num, nc_content, nc_date, as r
	from
		select nc_num, nc_content, to_char(nc_date, yyyy-mm-dd) as nc_date
			 from declaration
		order by nc_num desc
	
where r>=? and r>=?

--total
 select count(*) from n_comment
