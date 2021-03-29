/*
select* FROM bas_checkpoint_element select * from bas_checkpoint 数采 select * from BAS_ELEMENT
select * from  basecenter.pf_bas_source select * from basecenter.PF_BAS_CONSTRUCTIONINFO
select * from bas_checkpoint_element select * from bas_checkpoint  select * from bas_source
select * from bas_facilityrelation where is_deleted=0 and FACILITY_ID = 230  select * from bas_facility

select f.* from bas_facility f inner join bas_facilityrelation b on f.facility_id=b.facility_pid
where f.is_deleted=0 and b.facility_pid in (298,307,300,301,302,303,297)

voc_tech_type
UV光解 voc_uvgj
活性炭吸附 voc_hxtxf
喷淋  voc_pl
催化燃烧   voc_chrs
*/
--------------零星废水--------------------------------
--废水水表
create table bas_meterinfo(
meterid       varchar2(36) primary key,   --水表ID PK
source_id  varchar2(36),                  --工厂ID
meter_name  VARCHAR2(32),                 --水表名称
meter_type  number(1) default 0,          --水表类型 0:进水表 可能以后会扩展
MEMO        VARCHAR2(128),                --备注
is_deleted number(1) default 0,
created_by  VARCHAR2(32) default 'admin',
updated_by  VARCHAR2(32) default 'admin',
date_created date default sysdate,
date_updated date default sysdate
)

--储水池/桶
create table bas_storageinfo(
storage_id varchar2(36) primary key,       --水池/储罐ID
storage_name VARCHAR2(64)   NOT NULL,      --名称
source_id  varchar2(36),                   --工厂ID
storage_type varchar2(16),                 --水池类型，自建字典 //储存桶/自检池/其他
warn_rate  NUMBER(5,3),                    --预警高度
full_volume NUMBER(10,2),                  --最大容积
max_height  number(4,2),                   --水池高度(m)
section_area  NUMBER(10,2),                --截面积
density NUMBER(4,3),                       --密度
memo VARCHAR2(256),                        --备注
is_deleted number(1) default 0,
created_by  VARCHAR2(32) default 'admin',
updated_by  VARCHAR2(32) default 'admin',
date_created date default sysdate,
date_updated date default sysdate
)

alter table bas_checkpoint_element add facility_id varchar2(36)

/**
bas_checkpoint_element表的字段facility_id用来关联  bas_storageinfo.storage_id
或 bas_meterinfo.meterid,因子类型we11、we00关联水池表，类型w100，w101关联水表
零星废水水池、水表，另外因子表加入了字段facility_id用来关联这两个表，机制类似于VOC

--起始时间 --结束时间  --默认七天   一天返回一条数据
 这个企业下所有的水表,每个水表返回当天的水量,有几天就返回几条数据, 参数:sourceId,beginTime,endTime
 还有一条线是总计,这个企业下所有的水表量按当天的水表量求和

 */

--处置企业运输车辆
create table bas_tankcar(
car_id  number(6) primary key,          --车辆ID
source_id varchar2(36),                     --处置企业ID， reference bas_prodsource.source_id
car_tag varchar2(12),                       --车牌
car_type varchar2(32),                      --车辆类型
transcert varchar2(32),                     --车辆运输许可证
car_size varchar2(128),                     --车辆 长X宽X高
max_loadamt number(10,2),                   --最大载重
is_setup number(1) default 0,      --是否完成信息化改造
full_volume number(10,2),                   --容积
memo varchar2(512),                         --备注
collect_id varchar2(36),                    --关联DTU
is_deleted number(1) default 0,
created_by varchar2(32) default 'admin',
updated_by varchar2(32) default 'admin',
date_created date default sysdate,
date_updated date default sysdate
)

--车辆水箱
create table bas_trucktank(
tank_id number(6) primary key,           --水箱ID
tank_name varchar2(12) ,           --水箱名称
car_id number(6),                        --车辆ID
mn varchar2(32),                            --水箱DTU的MN
collect_id varchar2(36),                    --关联DTU的ID
warn_rate number(5,3),                      --预警桶占比
full_volume number(10,2),                   --最大容积
section_area number(10,2),                  --平均截面积
memo varchar2(512),
is_deleted number(1) default 0,
created_by varchar2(32) default 'admin',
updated_by varchar2(32) default 'admin',
date_created date default sysdate,
date_updated date default sysdate
tank_sn number(2)
)

/*
零星废水增加的 运输车辆与车辆水箱定义表
另外增加视图 bas_prodsource， 因子表bas_checkpoint_element的facility_id字段必须关联 bas_trucktank的PK tank_id
*/

--车辆GPS坐标历史记录
create table dat_cargps (
car_id number(6),                    --车辆ID
longitude number(10,6),              --经度
latitude number(10,6),               --纬度
gis_time date,                       --GPS时间
speed number(5,2),                   --车速
date_created date default sysdate,
primary key (car_id,gis_time)
)

/*
修改车辆表、车辆水箱表的PK字段car_id,tank_id为number(6), 增加相应的序列
CREATE SEQUENCE  SEQ_CARTANKID  MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  SEQ_CARID  MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

select* FROM bas_checkpoint_element 因子 select * from bas_checkpoint 数采 select * from BAS_ELEMENT 因子类型
select * from  basecenter.pf_bas_source select * from basecenter.PF_BAS_CONSTRUCTIONINFO 施工单位 select * from bas_settletechnology 工艺
select * from bas_checkpoint_element select * from bas_checkpoint  select * from bas_source   select * from bas_facility where FACILITY_ID in null
select * from bas_facilityrelation where is_deleted=0 and FACILITY_ID = 229
*/

--车辆水位、水量表
create table dat_trucktank
(
tank_id NUMBER(6),          --车辆水箱ID
datadate date,              --数据时间
water_hight NUMBER(7,4),   --水箱液位高度
water_amt NUMBER(9,3),     --水箱水量
DATE_UPDATED DATE default sysdate
)

--拉水车司机
create table bas_driverinfo (
  driver_id varchar2(32 byte) not null,  --司机id，默认使用身份证
  source_id varchar2(36 byte),                --司机所属处置企业
  driver_name varchar2(24 byte),           --姓名
  login_account varchar2(32 byte),      --app登陆账号
  login_password varchar2(32 byte),     --app登陆密码
  memo varchar2(128 byte),
  is_deleted number(1,0) default 0,
  created_by varchar2(32 byte) default 'admin',
  updated_by varchar2(32 byte) default 'admin',
  date_created date default sysdate,
  date_updated date default sysdate
)

--处置企业监控点/数采定义
create table bas_checkpoint_proc
(
  collect_id varchar2(36) primary key,      --监控点/数采id
  source_id varchar2(36),                   --产污企业id
  full_name varchar2(128),
  mn varchar2(28),
ip_address varchar2(24),
 is_online number(1, 0) default 0,
 check_date date,
 acquisition_brand varchar2(50),
 is_envcert varchar2(50),
 is_deleted number(1, 0) default 0,
 date_created date default sysdate,
 created_by varchar2(36) default 'admin',
 date_updated date default sysdate,
 updated_by varchar2(36) default 'admin'
)

--处置企业因子
create table bas_checkpoint_elem_proc
(
cp_elem_id number(6) primary key,
collect_id varchar2(36),                  --reference bas_checkpoint_proc.collectid
ele_type_id varchar2(12),                  --因子类型
element_code varchar2(12),                 --编码
element_desc varchar2(64),                 --名称
install_location varchar2(64),             --安装位置
judgementval number(9, 2),                  --启停判断阈值
is_online number(1) default 0,
check_date date,
is_deleted number(1) default 0,
created_by varchar2(36) default 'admin',
date_created date default sysdate,
updated_by varchar2(36) default 'admin',
date_updated date default sysdate
)

--司机联单  工作单
create table job_transjobhead
(
  job_headid varchar2(36) primary key,
  source_id varchar2(36),           --处置企业id
  job_status number(4) default 1,
  car_id number(6),                     --车辆
  driver_id varchar2(36),               --司机
  job_date date default sysdate,        --联单日期
  leave_time date default sysdate,      --司机出发/离开污水厂时间
  return_time date,                     --司机返回污水厂时间
  startflowouttime date,                --放水起始时间
  endflowouttime date,                  --防水结束时间
  flowoutamt number(8, 2),              --企业、司机确认得数量
  factoutamt number(8, 2),              --根据车辆水箱液位计算得数量
  memo varchar2(512),
  is_deleted number(1, 0) default 0,
  created_by varchar2(32),
  updated_by varchar2(32),
  date_created date default sysdate,
  date_updated date default sysdate
)

--司机联单子表 联单
create table job_transjobdetail
(
  job_detailid varchar2(36) primary key,
  job_headid varchar2(36),
  transbillid varchar2(36),
 source_id varchar(36),               --产污企业id
 startflowintime date,       -- 开始收水时间
 endflowintime date,       -- 完成收水时间
 flowinamt number(8, 2),       -- 确认(输入)数量（吨）
 factinamt number(8, 2),      -- 计算数量（吨）
 is_deleted number(1) default 0,
 created_by varchar2(32) default 'admin',
 updated_by varchar2(32) default 'admin',
 date_created date default sysdate,
 date_updated date default sysdate
)

-- 另外数据表后面加上_proc代表处置企业的
--产污企业运水联单
create table job_transbillinfo
(
 bill_id varchar2(36) primary key,         --pk字段
 bill_no varchar2(36),                      --纸质单号
 bill_status number(4) not null,            -- 联单状态: 预联单、进行中、已完成、已作废
 source_id varchar2(36),                    --产污企业id
 source_name varchar2(128),                 --产污企业名
 contact_info varchar2(512),                --产污企业联系信息
 address varchar2(256),                    --产污企业地址
 trans_source_id varchar2(36),              --运输企业id
 trans_source_name varchar2(128),           --运输企业名称
 trans_contect_info varchar2(512),          --运输企业联系信息
 trans_address varchar2(256),               --运输企业地址
 trans_postcode varchar2(128),              --运输企业邮编
 proc_source_id varchar2(36),                --处置企业id
 proc_source_name varchar2(512),         --处置企业名称
 proc_contact_info varchar2(512),     --处置企业联系信息
 proc_address varchar2(256),             --处置企业地址
 proc_postcode varchar2(512),          --处置企业邮编
 wastewater_name varchar2(512),     -- 废水名称
 wastewater_type varchar2(512),       -- 类别
 wastewater_amt number(8, 2),         -- 数量（KG）
 wastewater_nature varchar2(512),    -- 废水特性
 wastewater_shape varchar2(512),     -- 形态
 trans_type varchar2(512),                 -- 运输方式
 trans_purpose number(4), -- 外运目的  1、中转贮存、2、利用、3、集中处理、4、处置，默认集中处理
 main_components varchar2(256),       -- 主要成分
 emergency_measure varchar2(328),    -- 禁忌与应急措施
 shipper_name varchar2(512),               -- 发运人姓名
 ship_persign varchar2(50),                  -- 发运人签名
 trans_destination varchar2(328),         -- 运达地点
 trans_date date,                                 -- 转移时间
 carrier1_name varchar2(50),                -- 第一承运人
 carrier1_date date,                             -- 第一运输日期
 carrier1_type varchar2(32),                 -- 第一车（船）型
 carrier1_platenum varchar2(512),       -- 第一牌号
 carrier1_transcert varchar2(512),        -- 第一道路运输证号
 start_place varchar2(512),                  -- 运输起点
 via_place varchar2(512),                     -- 经由地
 end_place varchar2(512),                   -- 运输终点
 carrie_r1sign varchar2(512),               -- 第一运输人签字
 carrie_r2name varchar2(512),             -- 第二运输姓名
 carrier2_date date,                             -- 第二运输日期
 carrier2_type number(4),                    -- 第二车（船）型
 carrier2_platenum varchar2(512),        -- 第二牌号
 carrier2_transcert varchar2(512),         -- 第二道路运输证号
 carrier2_sign varchar2(512),                -- 第二运输人签字
 receive_sourcename varchar2(512),     -- 废水接收单位名称
 receive_personname varchar2(512),     -- 接收人
 received_date date,                             -- 接收日期
 proc_type number(4),                          -- 废水处置方式
 receive_head varchar2(512),                -- 单位负责人姓名
 receive_headsign varchar2(512),           -- 单位负责人
 receive_headsigndate date,                  -- 签字日期
 invalid_reason varchar2(256),               -- 作废原因
 invalid_date date,                                -- 作废时间
 invalid_user varchar2(32),                     -- 作废用户
 is_deleted number(1) default 0,
 created_by varchar2(32),
 updated_by varchar2(32),
 date_created date default sysdate,
 date_updated date default sysdate,
 upload_date date default sysdate        --触发器
)
------UploadDate--触发器------

CREATE OR REPLACE trigger Job_TransBillInfo_TR
    BEFORE INSERT OR UPDATE ON Job_TransBillInfo FOR EACH ROW
BEGIN
     IF INSERTING THEN
        :NEW.UPLOADDATE := SYSDATE;
    ELSIF UPDATING then
        :NEW.UPLOADDATE := SYSDATE;
    END IF;
END;

/water -- 水重点
/gas -- 气重点
/construction/model
/transfer/procstate
/transfer/chart

公司发布的测试库地址:172.31.0.38
公司发布的数据库地址:172.31.0.45
驻厂的数据库地址:19.106.117.90
