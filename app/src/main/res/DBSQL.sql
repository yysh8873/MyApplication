create database handark;
show databases;
use handark;
show tables;

drop table payinfo;

/* userinfo(유저아이디, 비밀번호, 유저이름, 유저성격, 주소, 폰번호, 잔액)
 * 유저성격 -> 판매자/구매자 구분 */
create table userinfo
	(uid varchar(20) not null primary key,
	pw varchar(100) not null,
	name varchar(20) not null,
	uservalue int not null,
	addr varchar(50) not null,
	phone varchar(13) not null,
	cash int not null default 0) ;

/* order(주문아이디, 주문날짜, 주문메뉴, 유저아이디, 총가격, 결제상태, 주소, 폰번호, 배달상태)
 * 결제상태 0:결제 전/1:결제완료
 * 배달상태는 관리자 창에서 배달준비0/배달중1/배달완료2로 나눠서 선택 수정 가능하게 하면 될 듯! */
create table orderinfo
	(oid int not null primary key,
	tdate date not null,
	menu varchar(500) not null,
	uid varchar(20) not null,
	price int not null,
	paycon int not null default 0,
	addr varchar(50) not null,
	phone varchar(13) not null,
	delcon int not null default 0) ;

/* payinfo(주문아이디, 결제금액, 유저아이디, 결제상태) */
create table payinfo
	(oid int not null,
	payprice int not null,
	uid varchar(20) not null,
	paycon int not null default 0,
	primary key(oid, uid)) ;


/* sample data */
insert into userinfo value('so', 'so1111', '이소현', 1, '경기도 오산시', '010-1111-1111', 20000);
insert into userinfo value('yi', 'yi2222', '권시연', 1, '성남시 분당', '010-2222-2222', 20000);
insert into userinfo value('joo', 'joo3333', '우주영', 1, '서울시', '010-3333-3333', 20000);

insert into orderinfo value(1, '2020-05-01','고추장찜닭', 'so', 18000, 0, '경기도 오산시', '010-1111-1111', 2);
insert into orderinfo value(2, '2020-05-02','치즈순살찜닭', 'yi', 20000, 0, '성남시 분당', '010-2222-2222', 2);
insert into orderinfo value(3, '2020-05-03','안동찜닭', 'woo', 180000, 0, '서울시', '010-3333-3333', 2);

insert into payinfo value(1, 18000, 'so', 1);
insert into payinfo value(2, 10000, 'yi', 1);
insert into payinfo value(2, 10000, 'so', 1);
insert into payinfo value(3, 18000, 'woo', 1);

select * from userinfo;
select * from orderinfo;
select * from payinfo;