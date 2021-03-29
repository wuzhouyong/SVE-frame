--油烟排放烟囱
CREATE TABLE BAS_OUTLET
(
  OUTLET_ID VARCHAR2(36 BYTE) primary key,
  SOURCE_ID VARCHAR2(36 BYTE) ,
  IS_DELETED NUMBER(1, 0) DEFAULT 0
  DATE_UPDATED DATE DEFAULT sysdate ,
  UPDATED_BY VARCHAR2(32 BYTE) ,
  OUTLET_NAME VARCHAR2(32 BYTE) ,
  MEMO VARCHAR2(64 BYTE) ,
)

--中心表 施工驳回信息表
create table pf_bas_construction_return
(
	return_id	number(9) primary key,
	pf_source_id varchar2(50) not null,
	tag_head_id varchar2(50) not null,
	construction_status number(1),
	return_reason varchar2(512),
	contact_name varchar2(24),
	contact_phone varchar2(24),
	memo varchar(512),
	created_by varchar2(32),
	date_created date default sysdate
);

