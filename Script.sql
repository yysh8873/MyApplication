create database handark;
show databases;
use handark;
show tables;

select * from userinfo;
select * from orderinfo;
select * from payinfo;

drop table payinfo;

/* userinfo(유저아이디, 비밀번호, 유저이름, 유저성격, 주소, 폰번호, 잔액)
 * 유저성격 -> 판매자/구매자 구분 */
create table userinfo
	(uid varchar(20) not null primary key,
	pw varchar(100) not null,
	name varchar(20) not null,
	uservalue varchar(3) not null,
	addr varchar(50) not null,
	phone varchar(13) not null,
	cash int not null default 0) ;
	
/* order(주문아이디, 주문메뉴, 유저아이디, 총가격, 결제상태, 주소, 폰번호, 배달상태)
 * 결제상태 0:결제 전/1:결제완료 
 * 배달상태는 관리자 창에서 배달준비/배달중/배달완료로 나눠서 선택 수정 가능하게 하면 될 듯! */
create table orderinfo
	(oid int not null primary key,
	menu varchar(500) not null,
	uid varchar(20) not null,
	price int not null,
	paycon int not null default 0,
	addr varchar(50) not null,
	phone varchar(13) not null,
	delcon varchar(4) not null default '배달준비') ;

/* payinfo(주문아이디, 결제금액, 유저아이디, 결제상태)
 * 따로 결제인포 아이디 필요할 지 말씀주세요! */
create table payinfo
	(oid int not null,
	payprice int not null,
	uid varchar(20) not null,
	paycon int not null default 0,
	primary key(oid, uid)) ;


/* sample data */
insert into userinfo
	value('whs123', '123123asd', '왕한신', '구매자', '경기도 의왕시 내손순환로 5 101호', '010-3532-7855', 0);
insert into userinfo
	value('narunaru', 'duck777', '한나루', '구매자', '서울시 관악구 행운동', '010-7145-1361', 0);	
insert into userinfo
	value('imwnsdud', 'lovemyself', '이준영', '구매자', '경기도 안양시', '010-4452-6512', 0);
	
insert into orderinfo
	value(1, '순살간장찜닭(대) 1', 'whs123', 33000, 0, '경기도 의왕시 내손순환로 5 101호', '010-3532-7855', '배달준비');
insert into orderinfo
	value(2, '뼈고추장찜닭(중) 1', 'whs123', 24000, 0, '경기도 의왕시 내손순환로 5 101호', '010-3532-7855', '배달준비');

insert into payinfo
	value(1, 11000, 'whs123', 0);
insert into payinfo
	value(1, 11000, 'narunaru', 0);
insert into payinfo
	value(1, 11000, 'imwnsdud', 0);
insert into payinfo
	value(2, 24000, 'whs123', 0);