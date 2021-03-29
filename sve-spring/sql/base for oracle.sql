-- ----------------------------
-- 1、存储每一个已配置的 jobDetail 的详细信息
-- ----------------------------
--drop table  QRTZ_JOB_DETAILS;
create table QRTZ_JOB_DETAILS (
    sched_name           varchar(120)    not null,
    job_name             varchar(200)    not null,
    job_group            varchar(200)    not null,
    description          varchar(250)    null,
    job_class_name       varchar(250)    not null,
    is_durable           varchar(1)      not null,
    is_nonconcurrent     varchar(1)      not null,
    is_update_data       varchar(1)      not null,
    requests_recovery    varchar(1)      not null,
    job_data             blob            null,
    constraint qrtz_job_details_pk primary key (sched_name,job_name,job_group)
) ;

-- ----------------------------
-- 2、 存储已配置的 Trigger 的信息
-- ----------------------------
--drop table QRTZ_TRIGGERS;
create table QRTZ_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    job_name             varchar(200)    not null,
    job_group            varchar(200)    not null,
    description          varchar(250)    null,
    next_fire_time       NUMBER(13)      null,
    prev_fire_time       NUMBER(13)      null,
    priority             number(13)         null,
    trigger_state        varchar(16)     not null,
    trigger_type         varchar(8)      not null,
    start_time           NUMBER(13)      not null,
    end_time             NUMBER(13)      null,
    calendar_name        varchar(200)    null,
    misfire_instr        number(2)     null,
    job_data             blob            null,
    constraint qrtz_triggers_pk primary key (sched_name,trigger_name,trigger_group),
    constraint qrtz_trigger_to_jobs_fk foreign key (sched_name,job_name,job_group)
    references qrtz_job_details(sched_name,job_name,job_group)
) ;

-- ----------------------------
-- 3、 存储简单的 Trigger，包括重复次数，间隔，以及已触发的次数
-- ----------------------------
--drop table QRTZ_SIMPLE_TRIGGERS;
create table QRTZ_SIMPLE_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    repeat_count         NUMBER(7)       not null,
    repeat_interval      NUMBER(12)      not null,
    times_triggered      NUMBER(10)      not null,
    constraint qrtz_simple_trig_pk primary key (sched_name,trigger_name,trigger_group),
    constraint qrtz_simple_trig_to_trig_fk foreign key (sched_name,trigger_name,trigger_group)
    references qrtz_triggers(sched_name,trigger_name,trigger_group)
) ;

-- ----------------------------
-- 4、 存储 Cron Trigger，包括 Cron 表达式和时区信息
-- ----------------------------
--drop table QRTZ_CRON_TRIGGERS;
create table QRTZ_CRON_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    cron_expression      varchar(200)    not null,
    time_zone_id         varchar(80),
    constraint qrtz_cron_trig_pk primary key (sched_name,trigger_name,trigger_group),
    constraint qrtz_cron_trig_to_trig_fk foreign key (sched_name,trigger_name,trigger_group)
    references qrtz_triggers(sched_name,trigger_name,trigger_group)
) ;

-- ----------------------------
-- 5、 Trigger 作为 Blob 类型存储(用于 Quartz 用户用 JDBC 创建他们自己定制的 Trigger 类型，JobStore 并不知道如何存储实例的时候)
-- ----------------------------
--drop table QRTZ_BLOB_TRIGGERS;
create table QRTZ_BLOB_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    blob_data            blob            null,
    constraint qrtz_blob_trig_pk primary key (sched_name,trigger_name,trigger_group),
    constraint qrtz_blob_trig_to_trig_fk foreign key (sched_name,trigger_name,trigger_group)
    references qrtz_triggers(sched_name,trigger_name,trigger_group)
) ;

-- ----------------------------
-- 6、 以 Blob 类型存储存放日历信息， quartz可配置一个日历来指定一个时间范围
-- ----------------------------
--drop table QRTZ_CALENDARS;
create table QRTZ_CALENDARS (
    sched_name           varchar(120)    not null,
    calendar_name        varchar(200)    not null,
    calendar             blob            not null,
    constraint qrtz_calendars_pk primary key (sched_name,calendar_name)
) ;

-- ----------------------------
-- 7、 存储已暂停的 Trigger 组的信息
-- ----------------------------
--drop table QRTZ_PAUSED_TRIGGER_GRPS;
create table QRTZ_PAUSED_TRIGGER_GRPS (
    sched_name           varchar(120)    not null,
    trigger_group        varchar(200)    not null,
    constraint qrtz_paused_trig_grps_pk primary key (sched_name,trigger_group)
) ;

-- ----------------------------
-- 8、 存储与已触发的 Trigger 相关的状态信息，以及相联 Job 的执行信息
-- ----------------------------
--drop table QRTZ_FIRED_TRIGGERS;
create table QRTZ_FIRED_TRIGGERS (
    sched_name           varchar(120)    not null,
    entry_id             varchar(95)     not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    instance_name        varchar(200)    not null,
    fired_time           NUMBER(13)      not null,
    sched_time           NUMBER(13)      not null,
    priority             number(13)         not null,
    state                varchar(16)     not null,
    job_name             varchar(200)    null,
    job_group            varchar(200)    null,
    is_nonconcurrent     varchar(1)      null,
    requests_recovery    varchar(1)      null,
    constraint qrtz_fired_trigger_pk primary key (sched_name,entry_id)
) ;

-- ----------------------------
-- 9、 存储少量的有关 Scheduler 的状态信息，假如是用于集群中，可以看到其他的 Scheduler 实例
-- ----------------------------
--drop table QRTZ_SCHEDULER_STATE;
create table QRTZ_SCHEDULER_STATE (
    sched_name           varchar(120)    not null,
    instance_name        varchar(200)    not null,
    last_checkin_time    NUMBER(13)      not null,
    checkin_interval     NUMBER(13)      not null,
    constraint qrtz_scheduler_state_pk primary key (sched_name,instance_name)
) ;

-- ----------------------------
-- 10、 存储程序的悲观锁的信息(假如使用了悲观锁)
-- ----------------------------
--drop table QRTZ_LOCKS;
create table QRTZ_LOCKS (
    sched_name           varchar(120)    not null,
    lock_name            varchar(40)     not null,
    constraint qrtz_locks_pk primary key (sched_name,lock_name)
) ;

--drop table QRTZ_SIMPROP_TRIGGERS;
create table QRTZ_SIMPROP_TRIGGERS (
    sched_name           varchar(120)    not null,
    trigger_name         varchar(200)    not null,
    trigger_group        varchar(200)    not null,
    str_prop_1           varchar(512)    null,
    str_prop_2           varchar(512)    null,
    str_prop_3           varchar(512)    null,
    int_prop_1           number(10)             null,
    int_prop_2           number(10)             null,
    long_prop_1          number(13)          null,
    long_prop_2          number(13)          null,
    dec_prop_1           numeric(13,4)   null,
    dec_prop_2           numeric(13,4)   null,
    bool_prop_1          varchar(1)      null,
    bool_prop_2          varchar(1)      null,
    constraint qrtz_simprop_trig_pk primary key (sched_name,trigger_name,trigger_group),
    constraint qrtz_simprop_trig_to_trig_fk foreign key (sched_name,trigger_name,trigger_group)
    references qrtz_triggers(sched_name,trigger_name,trigger_group)
) ;

commit;