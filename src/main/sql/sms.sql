create table agent (
	id int(10) auto_increment not null primary key,
	account varchar(45) not null unique,
	accid varchar(45) not null unique,
	password varchar(45) not null,
	name varchar(45),
	key_value varchar(45),
	app_id varchar(45),
	port varchar (5),
	balance int(10) default 0,
	given_balance int(10) default 0,
	status int(2) default 0,
	email varchar(45),
	mobile varchar(13),
	reg_at datetime,
	login_at datetime,
	update_at datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table recv_record (
	id int(10) auto_increment not null primary key,
	agent_id int(10) not null,
	phones varchar(1200),
	content varchar(255),
	smsid varchar(35),
	status int(2) default 0,
	resp_code varchar(10),
	recv_at datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table send_record (
	id int(10) auto_increment not null primary key,
	smsid varchar(35) not null,
	agent_id int(10) not null,
	passageway_id int(10) default 1,
	query_status int(2) default 0,
	phones varchar(1200),
	content varchar(255),
	count int(4) default 0,
	fail_count int(4) default 0,
	resp_status int(4) default -1,
	resp_message varchar(100),
	send_at datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table fail_record (
	id int(11) auto_increment not null primary key,
	agent_id int(10) not null,
	passageway_id int(10) default 1,
	phones varchar(1200),
	content varchar(255),
	resp_code varchar(10),
	count int(10) default 1,
	send_at datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table recharge_log (
	id int(11) auto_increment not null primary key,
	agent_id int(10) not null,
	balance int(10) default 0,
	given_balance int(10) default 0,
	user_id int(10) not null,
	intro varchar(255),
	create_at datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS agent_log;
CREATE TABLE agent_log (
  id int(10) auto_increment NOT NULL,
  agent_id int(10) unsigned DEFAULT NULL,
  ip varchar(255) DEFAULT NULL,
  log_at datetime DEFAULT NULL,
  uri varchar(255) DEFAULT NULL,
  query_ varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id int(10) auto_increment NOT NULL,
  account varchar(45) DEFAULT NULL,
  password varchar(45) DEFAULT NULL,
  name varchar(45) NOT NULL,
  create_at datetime DEFAULT NULL,
  login_at datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS user_session;
CREATE TABLE user_session (
  session_id varchar(255) NOT NULL,
  user_id int(10) unsigned DEFAULT NULL,
  create_at datetime DEFAULT NULL,
  PRIMARY KEY (session_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS agent_session;
CREATE TABLE agent_session (
  session_id varchar(255) NOT NULL,
  agent_id int(10) unsigned DEFAULT NULL,
  create_at datetime DEFAULT NULL,
  PRIMARY KEY (session_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS log;
CREATE TABLE log (
  id int(10) auto_increment NOT NULL,
  user_id int(10) unsigned DEFAULT NULL,
  ip varchar(255) DEFAULT NULL,
  log_at datetime DEFAULT NULL,
  uri varchar(255) DEFAULT NULL,
  query_ varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table counter_daily(
  id int(10) auto_increment not null primary key,
  agent_id int(10) not null,
  rec_date date not null,
  success int(10) not null,
  fail int(10) not null,
  create_at datetime not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#INSERT INTO user VALUES(1, 'zwm', 'aa5a9bcf37a4d7b6be268ada11d9839d', '小钟', NOW(), NOW());
#INSERT INTO user VALUES(2, 'guojianhua', '001b5a4af020885de2abb242ba7a692a', '郭总', NOW(), NOW());

DROP TABLE IF EXISTS passageway;
create table passageway(
	id int(10) auto_increment not null primary key,
	name varchar(255) not null,
	cmcc int(4) not null default 0,
	cucc int(4) not null default 0,
	ctcc int(4) not null default 0,
	status int(4) not null default 0,
	create_at datetime not null,
	create_by int(10),
	update_at datetime not null,
	update_by int(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table counter_monthly(
  id int(10) auto_increment not null primary key,
  agent_id int(10) not null,
  rec_date date not null,
  success int(10) not null,
  fail int(10) not null,
  create_at datetime not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table notice(
  id int(10) auto_increment not null primary key,
  port varchar(10) comment '分配给开发者的上行短信端口（非移动号码此参数为空）。请联系工作人员获取',
  phone varchar(255) comment '上行手机号码',
  content varchar(255) comment '上行短信内容',
  send_at datetime comment '上行短信时间',
  stamp varchar(20) comment '时间戳。当前系统时间（24小时制），格式"yyyyMMddHHmmss"',
  sign varchar(32) comment '签名。MD5(ACCOUNT SID + AUTH TOKEN + timestamp)。共32位（小写）',
  create_at datetime not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table template(
	id int(10) auto_increment not null primary key,
	agent_id int(10) not null comment '商户id'，
	template_id varchar(100) comment '模板ID。创建模板提交后由系统自动分配',
	template_type int(4) default 1 comment '模板类型。0：短信  1：邮件短信',
	signature varchar(100) comment '短信签名',
	content varchar(255) comment '短信内容模板。不包含短信签名',
	sender_mailbox varchar(100) comment '发送者邮箱。已经注册的139邮箱别名，如： sms001@139.com。邮件短信必填。',
	sender_nickname varchar(100) comment '发件人昵称。用于收件人显示的发件人名称，不超过12个字符 ，如：taobao。邮件短信必填',
	email_content text comment '邮件内容模板。支持html格式。邮件短信必填。',
	audit_status int(4) default 0 comment '审核状态。0：待审核 1：审核通过2：审核不通过 3：139邮箱别名待验证',
	enable_status int(4) default 0 comment '启用状态。0：启用  1：关闭',
	create_time datetime comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table template add column title varchar(255) comment '标题';

alter table send_record add column template_id varchar(100) comment '模板id';
alter table send_record add column type int(4) comment '发送类型:1行业邮件短信,2营销邮件短信,3行业短信,4营销短信';

alter table fail_record add column template_id varchar(100) comment '模板id';
alter table fail_record add column type int(4) comment '发送类型:1行业邮件短信,2营销邮件短信,3行业短信,4营销短信';