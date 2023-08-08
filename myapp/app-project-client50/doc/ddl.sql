create table myapp_styling(
  styling_no int not null,
  style varchar(255) not null,
  brand text null,
  fit varchar(20) not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now()
);

alter table myapp_styling
  add constraint primary key (styling_no),
  modify column styling_no int not null auto_increment;

create table myapp_acc(
  acc_no int not null,
  style varchar(255) not null,
  choose text null,
  size varchar(20) not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now()
);

alter table myapp_acc
  add constraint primary key (acc_no),
  modify column acc_no int not null auto_increment;
  
create table myapp_member(
  member_no int not null,
  name varchar(20) not null,
  email varchar(50) not null,
  password varchar(100) not null,
  age varchar(100) not null,
  gender char(1) not null,
  top varchar(10) not null,
  pants varchar(10) not null,
  shoes varchar(10) not null
);

alter table myapp_member
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;
  
  
-- 게시판에 카테고리 컬럼 추가
alter table myapp_acc
  add column category int not null;
  alter table myapp_styling
  add column category int not null;