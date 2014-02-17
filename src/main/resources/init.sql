
--1. 商品表 goods

CREATE TABLE goods
(
id varchar(32) NOT NULL PRIMARY KEY ,--商品编号
name varchar(255) NOT NULL , --名称
model varchar(255) NOT NULL , --规格
type varchar(255) NOT NULL --分类
);

--2. 采购表 purchase

CREATE TABLE purchase
(
id varchar(32) NOT NULL PRIMARY KEY ,--采购编号
gid varchar(32) NOT NULL , --商品编号
price varchar(12) NOT NULL , --商品单价
amount varchar(8) NOT NULL , --采购数量
subtotal varchar(16) NOT NULL --采购总价
daytime varchar(14) NOT NULL --采购时间
username varchar(64) --采购者
);

--3. 交易表 trade

CREATE TABLE trade
(
id varchar(32) NOT NULL PRIMARY KEY ,--交易编号
gid varchar(32) NOT NULL , --采购编号
uid varchar(32) NOT NULL , --用户编号
amount varchar(8) NOT NULL, --交易数量
subtotal varchar(16) NOT NULL --交易金额
daytime varchar(14) NOT NULL --交易时间
);

--4. 库存表 capacity

CREATE TABLE capacity
(
gid varchar(32) NOT NULL PRIMARY KEY ,--商品编号
amount varchar(8) NOT NULL , --库存数量
subtotal varchar(16) NOT NULL --库存金额
);

--5. 用户表 member

CREATE TABLE member
(
id varchar(32) NOT NULL PRIMARY KEY ,--用户编号
name varchar(64) NOT NULL , --名称
status varchar(1) NOT NULL --是否有效
);

