create table user
(
	id int auto_increment,
	account_Id varchar(100),
	name varchar(50),
	token char(36),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	constraint user_pk
		primary key (id)
);