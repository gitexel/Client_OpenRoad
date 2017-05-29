create table citizen
(
	n_id varchar(20) default '' not null
		primary key,
	fname varchar(20) not null,
	mname varchar(20) not null,
	lname varchar(20) not null,
	country_name varchar(50) not null,
	gender varchar(1) not null,
	birthday date not null,
	constraint citizen_n_id_uindex
		unique (n_id)
)
;

create index fk_citizen_country_name
	on citizen (country_name)
;

create table country
(
	name varchar(50) not null
		primary key,
	constraint uk_country_name
		unique (name)
)
;

alter table citizen
	add constraint fk_citizen_country_name
		foreign key (country_name) references opr.country (name)
;

create table customer
(
	username varchar(50) not null
		primary key,
	password varchar(100) not null,
	fname varchar(20) not null,
	mname varchar(20) null,
	lname varchar(20) not null,
	gender varchar(1) default 'O' null,
	country_name varchar(50) not null,
	n_id varchar(20) null,
	n_id_photo mediumblob null,
	verified tinyint(1) default '0' null,
	address varchar(1000) null,
	phone varchar(15) null,
	e_mail varchar(50) not null,
	cv mediumblob null,
	health_certificate mediumblob null,
	job varchar(1000) null,
	linkedin varchar(1000) null,
	Education varchar(1000) null,
	filename_appidphoto varchar(100) null,
	filename_appcvfile varchar(100) null,
	filename_apphealthfile varchar(100) null,
	filename_appenglishfile varchar(100) null,
	profile_photo mediumblob null,
	Checkboxenglish varchar(100) null,
	paypal_email varchar(100) null,
	visa_or_mastercard varchar(100) null,
	credit_card_number varchar(100) null,
	credit_card_cvv varchar(100) null,
	bank_account_number varchar(100) null,
	paypal_email_b tinyint(1) null,
	use_credit_card_b tinyint(1) null,
	use_bank_account_b tinyint(1) null,
	english_certificate mediumblob null,
	birthday date not null,
	constraint uk_customer_phone_email
		unique (phone, e_mail),
	constraint fk_customer_country
		foreign key (country_name) references opr.country (name),
	constraint fk_customer_n_id
		foreign key (n_id) references opr.citizen (n_id)
)
;

create index fk_customer_country
	on customer (country_name)
;

create index fk_customer_n_id
	on customer (n_id)
;

create table history
(
	username varchar(50) not null,
	trip_id int not null,
	p_date timestamp default CURRENT_TIMESTAMP not null,
	constraint fk_history_username
		foreign key (username) references opr.customer (username)
)
;

create index fk_history_trip_id
	on history (trip_id)
;

create index fk_history_username
	on history (username)
;

create table trip
(
	id int not null auto_increment
		primary key,
	origin varchar(50) not null,
	destination varchar(50) not null,
	class varchar(20) not null,
	departure date not null,
	dclock time not null,
	arrival date not null,
	aclock time not null,
	price double not null,
	constraint fk_trip_origin
		foreign key (origin) references opr.country (name),
	constraint fk_trip_destination
		foreign key (destination) references opr.country (name)
)
;

create index fk_trip_destination
	on trip (destination)
;

create index fk_trip_origin
	on trip (origin)
;

alter table history
	add constraint fk_history_trip_id
		foreign key (trip_id) references opr.trip (id)
;

create table valid_users
(
	id int not null auto_increment
		primary key,
	username varchar(50) not null,
	valid_start timestamp default CURRENT_TIMESTAMP not null,
	valid_end timestamp default '0000-00-00 00:00:00' not null,
	valid tinyint(1) default '1' null,
	constraint valid_users_customer_username_fk
		foreign key (username) references opr.customer (username)
)
;

create index valid_users_customer_username_fk
	on valid_users (username)
;


