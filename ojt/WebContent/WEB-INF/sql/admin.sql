drop table admin;

insert into company(c_ID, cname, passwd, cnum, boss, address, mname, mphone, memail, c_date, address2)
values('회사', '회', '1234', '1', '정일훈', '종로', '정일훈', '123-123-123', '종로@gmail.com', sysdate, '종로뒷골목')

create table admin(
a_ID varchar(20) not null,
passwd varchar(20),

constraint pk_admin_a_ID primary key(a_ID)
);

--read
select * from admin where a_ID = 'admin';

--update
update admin set
a_ID = ?;
passwd = ?;

--delete
delete from admin where a_ID =?;

--list
select a_ID, passwd, r
from
	select a_ID, passwd, as r
	from
		select a_ID, passwd
			 from admin
		order by a_ID desc
	
where r>=? and r>=?

--total
 select count(*) from admin
 
 
 
 select a_ID, passwd, r
		from(
			select a_ID, passwd, rownum as r
			from(
				select a_ID, passwd
                from admin
				order by a_ID desc
				)
            )
        where r>= 1 and r<= 5;
