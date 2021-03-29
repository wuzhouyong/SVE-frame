select * from zdatadevdef --数采设备
select * from zdatasoursetting -- 企业表
select * from zcheckpointdef -- 排放口
select * from zelementdef -- 因子表
select * from zlabelhead -- 镇街
select * from zabnortaskhead --任务类型
select * from zabnortaskelement  --异常因子数据 和因子表一起使用
select * from zdatasourelement  --工厂-监控点-因子关联表
select * from zrealdata --实时数据
select * from user_tab_cols where table_name ='ZDATASOURELEMENT'
select * from ent_connectapply  --申请表



--督办任务
create table zSuperviseTask(
zsuptaskid number(6) primary key,  --督办任务ID --SEQ_SUPERVISETASKID
ztownname varchar2(24),             --镇名称
zfactoryname varchar2(128),         --工厂名称
zcheckpointname varchar2(64),       --监控点名称
zelementname varchar2(32),          --因子名称
zmonitortime date,                  --监测时间
zamount number(15,4),               --排放浓度
zstandardval number(15,4),          --排放标准
zovertimes number(5,3),             --超标倍数
zoverdays number(3),                --超标天数
ztaskstatus number(2),              --"", "1草稿", "2分局待处理", "3超时未处理", "4市局待核实", "5市局退回", "6已完成"
zbegintime date,                    --任务起始时间
zendtime date,                      --任务结束时间
zuntownid number(6) default -1,       --镇街ID
zomtownid number(6) default -1,       --镇街ID
zdatasourid number(6) default -1,   --工厂ID
zcheckpointid number(6) default -1, --监控点ID
zelementid number(6) default -1,    --因子ID
zhandleby varchar2(32),             --处理人
zcontactphone varchar2(24),         --联系电话
zhandletime date,                   --处理时间
zistrue number(1),                  --超标是否属实
zreason varchar2(256),              --超标原因
zsituation varchar2(256),           --处理情况
zrecityinfo varchar2(256),          --整改情况
zdatecreated date default sysdate,
zdateupdated date default sysdate,
zcreatedby varchar2(32),
zupdatedby varchar2(32)
)
alter table zsupervisetask add zisdeleted number(1) default 0

--如果状态为1:草稿 保存 不生成结束和开始时间 ,可再次编辑，只能编辑草稿
--如果状态为2:待处理 提交 生成开始结束时间，不可再次编辑，编辑要有id才能编辑，只能编辑状态为1的数据
--也就是新增的时候有草稿，有待处理，编辑的时候只能是草稿的状态才能编辑
--分局处理;235 ->提交之后状态变为4
--4值守核实 -> 驳回5 同意6 用true
--删除只能是本人删除

--附件表我没建，就用以前的附件表，你自己加附件类型枚举好了

-- 各项目因子数据共享的需求可以定一下
-- 各平台因子共享 数据迁移，因子来源关联，主要是金属造纸，源:零星废水，排水户，四大流域
create table pf_bas_element_ref(
src_elemid number(9),       --源项目因子ID
src_tag_head_id number(4),  --源项目tag_head_id
src_dbuser varchar2(16),    --源项目数据库user
dst_elemid number(9),       --目标项目因子ID
dst_tag_head_id number(4),  --目标项目tag_head_id
dst_dbuser varchar2(16),    --目标项目数据库user
is_deleted number(1) default 0,
sync_date date,
date_created date default sysdate,
date_updated date default sysdate,
created_by varchar2(32),
updated_by varchar2(32)
)
-- 数据来源于在线监控的，关联信息存放在basecenter.pf_bas_element_dghbref表中.
-- 各项目关联的，比如造纸的数据来源于四大流域过程的，就必须存放在basecenter.pf_bas_element_ref中

