create database handark;
show databases;
use handark;
show tables;

select * from userinfo;
select * from orderinfo;
select * from payinfo;

drop table payinfo;

/* userinfo(�������̵�, ��й�ȣ, �����̸�, ��������, �ּ�, ����ȣ, �ܾ�)
 * �������� -> �Ǹ���/������ ���� */
create table userinfo
	(uid varchar(20) not null primary key,
	pw varchar(100) not null,
	name varchar(20) not null,
	uservalue varchar(3) not null,
	addr varchar(50) not null,
	phone varchar(13) not null,
	cash int not null default 0) ;
	
/* order(�ֹ����̵�, �ֹ��޴�, �������̵�, �Ѱ���, ��������, �ּ�, ����ȣ, ��޻���)
 * �������� 0:���� ��/1:�����Ϸ� 
 * ��޻��´� ������ â���� ����غ�/�����/��޿Ϸ�� ������ ���� ���� �����ϰ� �ϸ� �� ��! */
create table orderinfo
	(oid int not null primary key,
	menu varchar(500) not null,
	uid varchar(20) not null,
	price int not null,
	paycon int not null default 0,
	addr varchar(50) not null,
	phone varchar(13) not null,
	delcon varchar(4) not null default '����غ�') ;

/* payinfo(�ֹ����̵�, �����ݾ�, �������̵�, ��������)
 * ���� �������� ���̵� �ʿ��� �� �����ּ���! */
create table payinfo
	(oid int not null,
	payprice int not null,
	uid varchar(20) not null,
	paycon int not null default 0,
	primary key(oid, uid)) ;


/* sample data */
insert into userinfo
	value('whs123', '123123asd', '���ѽ�', '������', '��⵵ �ǿս� ���ռ�ȯ�� 5 101ȣ', '010-3532-7855', 0);
insert into userinfo
	value('narunaru', 'duck777', '�ѳ���', '������', '����� ���Ǳ� ��', '010-7145-1361', 0);	
insert into userinfo
	value('imwnsdud', 'lovemyself', '���ؿ�', '������', '��⵵ �Ⱦ��', '010-4452-6512', 0);
	
insert into orderinfo
	value(1, '���찣�����(��) 1', 'whs123', 33000, 0, '��⵵ �ǿս� ���ռ�ȯ�� 5 101ȣ', '010-3532-7855', '����غ�');
insert into orderinfo
	value(2, '�����������(��) 1', 'whs123', 24000, 0, '��⵵ �ǿս� ���ռ�ȯ�� 5 101ȣ', '010-3532-7855', '����غ�');

insert into payinfo
	value(1, 11000, 'whs123', 0);
insert into payinfo
	value(1, 11000, 'narunaru', 0);
insert into payinfo
	value(1, 11000, 'imwnsdud', 0);
insert into payinfo
	value(2, 24000, 'whs123', 0);