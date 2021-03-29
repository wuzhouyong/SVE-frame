/*
 Navicat Premium Data Transfer

 Source Server         : 若依测试库172.31.0.38
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 172.31.0.38:1521
 Source Schema         : SVECENTER

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 12/05/2020 09:42:45
*/


-- ----------------------------
-- Table structure for GEN_TABLE
-- ----------------------------
DROP TABLE "SVECENTER"."GEN_TABLE";
CREATE TABLE "SVECENTER"."GEN_TABLE" (
  "TABLE_ID" NUMBER(20) NOT NULL ,
  "TABLE_NAME" NVARCHAR2(200) ,
  "TABLE_COMMENT" NVARCHAR2(500) ,
  "CLASS_NAME" NVARCHAR2(100) ,
  "TPL_CATEGORY" NVARCHAR2(200) ,
  "PACKAGE_NAME" NVARCHAR2(100) ,
  "MODULE_NAME" NVARCHAR2(30) ,
  "BUSINESS_NAME" NVARCHAR2(30) ,
  "FUNCTION_NAME" NVARCHAR2(50) ,
  "FUNCTION_AUTHOR" NVARCHAR2(50) ,
  "OPTIONS" NVARCHAR2(1000) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."TABLE_ID" IS '编号';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."TABLE_NAME" IS '表名称';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."TABLE_COMMENT" IS '表描述';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."CLASS_NAME" IS '实体类名称';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."TPL_CATEGORY" IS '使用的模板（crud单表操作 tree树表操作）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."PACKAGE_NAME" IS '生成包路径';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."MODULE_NAME" IS '生成模块名';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."BUSINESS_NAME" IS '生成业务名';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."FUNCTION_NAME" IS '生成功能名';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."FUNCTION_AUTHOR" IS '生成功能作者';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."OPTIONS" IS '其它生成选项';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."GEN_TABLE" IS '代码生成业务表';

-- ----------------------------
-- Records of GEN_TABLE
-- ----------------------------
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('28', 'test_ip', '测试IP信息', 'TestIp', 'crud', 'com.ruoyi.project.sample', 'sample', 'ip', '测试IP信息', 'ruoyi', '{}', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('30', 'sys_post', '岗位信息表', 'SysPost', 'crud', 'com.ruoyi.project.system', 'system', 'post', '岗位信息', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('31', 'sys_role', '角色信息表', 'SysRole', 'crud', 'com.ruoyi.project.system', 'system', 'role', '角色信息', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('32', 'sys_role_dept', '角色和部门关联表', 'SysRoleDept', 'crud', 'com.ruoyi.project.system', 'system', 'dept', '角色和部门关联', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('33', 'sys_role_menu', '角色和菜单关联表', 'SysRoleMenu', 'crud', 'com.ruoyi.project.system', 'system', 'menu', '角色和菜单关联', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('34', 'sys_user', '用户信息表', 'SysUser', 'crud', 'com.ruoyi.project.system', 'system', 'user', '用户信息', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('35', 'sys_user_post', '用户与岗位关联表', 'SysUserPost', 'crud', 'com.ruoyi.project.system', 'system', 'post', '用户与岗位关联', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('36', 'sys_user_role', '用户和角色关联表', 'SysUserRole', 'crud', 'com.ruoyi.project.system', 'system', 'role', '用户和角色关联', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('37', 'sys_config', '参数配置表', 'SysConfig', 'crud', 'com.ruoyi.project.system', 'system', 'config', '参数配置', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('38', 'sys_dept', '部门表', 'SysDept', 'crud', 'com.ruoyi.project.system', 'system', 'dept', '部门', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('39', 'sys_dict_data', '字典数据表', 'SysDictData', 'crud', 'com.ruoyi.project.system', 'system', 'data', '字典数据', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('40', 'sys_dict_type', '字典类型表', 'SysDictType', 'crud', 'com.ruoyi.project.system', 'system', 'type', '字典类型', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('41', 'sys_job', '定时任务调度表', 'SysJob', 'crud', 'com.ruoyi.project.system', 'system', 'job', '定时任务调度', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('42', 'sys_job_log', '定时任务调度日志表', 'SysJobLog', 'crud', 'com.ruoyi.project.system', 'system', 'log', '定时任务调度日志', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('43', 'sys_logininfor', '系统访问记录', 'SysLogininfor', 'crud', 'com.ruoyi.project.system', 'system', 'logininfor', '系统访问记录', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('44', 'sys_menu', '菜单权限表', 'SysMenu', 'crud', 'com.ruoyi.project.system', 'system', 'menu', '菜单权限', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('45', 'sys_notice', '通知公告表', 'SysNotice', 'crud', 'com.ruoyi.project.system', 'system', 'notice', '通知公告', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('46', 'sys_oper_log', '操作日志记录', 'SysOperLog', 'crud', 'com.ruoyi.project.system', 'system', 'log', '操作日志记录', 'ruoyi', NULL, 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE" VALUES ('47', 'test_eximport', 'Excel导入导出测试', 'TestEximport', 'crud', 'com.ruoyi.project.sample', 'sample', 'eximport', 'Excel导入导出测试', 'ruoyi', '{}', 'admin', TO_DATE('2020-04-08 15:59:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-04-08 16:01:20', 'SYYYY-MM-DD HH24:MI:SS'), NULL);

-- ----------------------------
-- Table structure for GEN_TABLE_COLUMN
-- ----------------------------
DROP TABLE "SVECENTER"."GEN_TABLE_COLUMN";
CREATE TABLE "SVECENTER"."GEN_TABLE_COLUMN" (
  "COLUMN_ID" NUMBER(20) NOT NULL ,
  "TABLE_ID" NVARCHAR2(64) ,
  "COLUMN_NAME" NVARCHAR2(200) ,
  "COLUMN_COMMENT" NVARCHAR2(500) ,
  "COLUMN_TYPE" NVARCHAR2(100) ,
  "JAVA_TYPE" NVARCHAR2(500) ,
  "JAVA_FIELD" NVARCHAR2(200) ,
  "IS_PK" NCHAR(1) ,
  "IS_INCREMENT" NCHAR(1) ,
  "IS_REQUIRED" NCHAR(1) ,
  "IS_INSERT" NCHAR(1) ,
  "IS_EDIT" NCHAR(1) ,
  "IS_LIST" NCHAR(1) ,
  "IS_QUERY" NCHAR(1) ,
  "QUERY_TYPE" NVARCHAR2(200) ,
  "HTML_TYPE" NVARCHAR2(200) ,
  "DICT_TYPE" NVARCHAR2(200) ,
  "SORT" NUMBER(11) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."COLUMN_ID" IS '编号';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."TABLE_ID" IS '归属表编号';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."COLUMN_NAME" IS '列名称';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."COLUMN_COMMENT" IS '列描述';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."COLUMN_TYPE" IS '列类型';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."JAVA_TYPE" IS 'JAVA类型';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."JAVA_FIELD" IS 'JAVA字段名';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."IS_PK" IS '是否主键（1是）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."IS_INCREMENT" IS '是否自增（1是）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."IS_REQUIRED" IS '是否必填（1是）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."IS_INSERT" IS '是否为插入字段（1是）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."IS_EDIT" IS '是否编辑字段（1是）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."IS_LIST" IS '是否列表字段（1是）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."IS_QUERY" IS '是否查询字段（1是）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."QUERY_TYPE" IS '查询方式（等于、不等于、大于、小于、范围）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."HTML_TYPE" IS '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."DICT_TYPE" IS '字典类型';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."SORT" IS '排序';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."GEN_TABLE_COLUMN"."UPDATE_TIME" IS '更新时间';
COMMENT ON TABLE "SVECENTER"."GEN_TABLE_COLUMN" IS '代码生成业务表字段';

-- ----------------------------
-- Records of GEN_TABLE_COLUMN
-- ----------------------------
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('307', '30', 'post_code', '岗位编码', 'varchar(64)', 'String', 'postCode', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('308', '30', 'post_name', '岗位名称', 'varchar(50)', 'String', 'postName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('309', '30', 'post_sort', '显示顺序', 'int(4)', 'Integer', 'postSort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('310', '30', 'status', '状态（0正常 1停用）', 'char(1)', 'String', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('311', '30', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('312', '30', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('313', '30', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('314', '30', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('315', '30', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('316', '31', 'role_id', '角色ID', 'bigint(20)', 'Long', 'roleId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('317', '31', 'role_name', '角色名称', 'varchar(30)', 'String', 'roleName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('318', '31', 'role_key', '角色权限字符串', 'varchar(100)', 'String', 'roleKey', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('319', '31', 'role_sort', '显示顺序', 'int(4)', 'Integer', 'roleSort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('320', '31', 'data_scope', '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）', 'char(1)', 'String', 'dataScope', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('321', '31', 'status', '角色状态（0正常 1停用）', 'char(1)', 'String', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('322', '31', 'del_flag', '删除标志（0代表存在 2代表删除）', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('323', '31', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('324', '31', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('325', '31', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('326', '31', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '11', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('327', '31', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '12', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('328', '32', 'role_id', '角色ID', 'bigint(20)', 'Long', 'roleId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('329', '32', 'dept_id', '部门ID', 'bigint(20)', 'Long', 'deptId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('330', '33', 'role_id', '角色ID', 'bigint(20)', 'Long', 'roleId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('331', '33', 'menu_id', '菜单ID', 'bigint(20)', 'Long', 'menuId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('332', '34', 'user_id', '用户ID', 'bigint(20)', 'Long', 'userId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('333', '34', 'dept_id', '部门ID', 'bigint(20)', 'Long', 'deptId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('334', '34', 'user_name', '用户账号', 'varchar(30)', 'String', 'userName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('335', '34', 'nick_name', '用户昵称', 'varchar(30)', 'String', 'nickName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('336', '34', 'user_type', '用户类型（00系统用户）', 'varchar(2)', 'String', 'userType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('337', '34', 'email', '用户邮箱', 'varchar(50)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('338', '34', 'phonenumber', '手机号码', 'varchar(11)', 'String', 'phonenumber', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('339', '34', 'sex', '用户性别（0男 1女 2未知）', 'char(1)', 'String', 'sex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('340', '34', 'avatar', '头像地址', 'varchar(100)', 'String', 'avatar', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('341', '34', 'password', '密码', 'varchar(100)', 'String', 'password', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('342', '34', 'status', '帐号状态（0正常 1停用）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '11', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('343', '34', 'del_flag', '删除标志（0代表存在 2代表删除）', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '12', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('344', '34', 'login_ip', '最后登陆IP', 'varchar(50)', 'String', 'loginIp', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '13', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('345', '34', 'login_date', '最后登陆时间', 'datetime', 'Date', 'loginDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', NULL, '14', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('346', '34', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '15', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('347', '34', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '16', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('348', '34', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '17', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('349', '34', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '18', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('350', '34', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '19', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('351', '35', 'user_id', '用户ID', 'bigint(20)', 'Long', 'userId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('352', '35', 'post_id', '岗位ID', 'bigint(20)', 'Long', 'postId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('353', '36', 'user_id', '用户ID', 'bigint(20)', 'Long', 'userId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('354', '36', 'role_id', '角色ID', 'bigint(20)', 'Long', 'roleId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('355', '37', 'config_id', '参数主键', 'int(5)', 'Integer', 'configId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('356', '37', 'config_name', '参数名称', 'varchar(100)', 'String', 'configName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('357', '37', 'config_key', '参数键名', 'varchar(100)', 'String', 'configKey', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('358', '37', 'config_value', '参数键值', 'varchar(500)', 'String', 'configValue', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('359', '37', 'config_type', '系统内置（Y是 N否）', 'char(1)', 'String', 'configType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('360', '37', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('361', '37', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('362', '37', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('363', '37', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('364', '37', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('365', '38', 'dept_id', '部门id', 'bigint(20)', 'Long', 'deptId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('366', '38', 'parent_id', '父部门id', 'bigint(20)', 'Long', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('367', '38', 'ancestors', '祖级列表', 'varchar(50)', 'String', 'ancestors', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('368', '38', 'dept_name', '部门名称', 'varchar(30)', 'String', 'deptName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('369', '38', 'order_num', '显示顺序', 'int(4)', 'Integer', 'orderNum', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('370', '38', 'leader', '负责人', 'varchar(20)', 'String', 'leader', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('371', '38', 'phone', '联系电话', 'varchar(11)', 'String', 'phone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('372', '38', 'email', '邮箱', 'varchar(50)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('373', '38', 'status', '部门状态（0正常 1停用）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('374', '38', 'del_flag', '删除标志（0代表存在 2代表删除）', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('375', '38', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '11', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('376', '38', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '12', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('377', '38', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '13', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('378', '38', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '14', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('379', '39', 'dict_code', '字典编码', 'bigint(20)', 'Long', 'dictCode', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('380', '39', 'dict_sort', '字典排序', 'int(4)', 'Integer', 'dictSort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('381', '39', 'dict_label', '字典标签', 'varchar(100)', 'String', 'dictLabel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('382', '39', 'dict_value', '字典键值', 'varchar(100)', 'String', 'dictValue', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('383', '39', 'dict_type', '字典类型', 'varchar(100)', 'String', 'dictType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('384', '39', 'css_class', '样式属性（其他样式扩展）', 'varchar(100)', 'String', 'cssClass', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('385', '39', 'list_class', '表格回显样式', 'varchar(100)', 'String', 'listClass', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('386', '39', 'is_default', '是否默认（Y是 N否）', 'char(1)', 'String', 'isDefault', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('387', '39', 'status', '状态（0正常 1停用）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('388', '39', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('389', '39', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '11', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('390', '39', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '12', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('391', '39', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '13', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('392', '39', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '14', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('393', '40', 'dict_id', '字典主键', 'bigint(20)', 'Long', 'dictId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('394', '40', 'dict_name', '字典名称', 'varchar(100)', 'String', 'dictName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('395', '40', 'dict_type', '字典类型', 'varchar(100)', 'String', 'dictType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('396', '40', 'status', '状态（0正常 1停用）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('397', '40', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('398', '40', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('399', '40', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('400', '40', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('401', '40', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:57', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('402', '41', 'job_id', '任务ID', 'bigint(20)', 'Long', 'jobId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('403', '41', 'job_name', '任务名称', 'varchar(64)', 'String', 'jobName', '1', '0', NULL, '1', NULL, NULL, NULL, 'LIKE', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('404', '41', 'job_group', '任务组名', 'varchar(64)', 'String', 'jobGroup', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('405', '41', 'invoke_target', '调用目标字符串', 'varchar(500)', 'String', 'invokeTarget', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('406', '41', 'cron_expression', 'cron执行表达式', 'varchar(255)', 'String', 'cronExpression', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('407', '41', 'misfire_policy', '计划执行错误策略（1立即执行 2执行一次 3放弃执行）', 'varchar(20)', 'String', 'misfirePolicy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('408', '41', 'concurrent', '是否并发执行（0允许 1禁止）', 'char(1)', 'String', 'concurrent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('409', '41', 'status', '状态（0正常 1暂停）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('410', '41', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('411', '41', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('412', '41', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '11', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('413', '41', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '12', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('414', '41', 'remark', '备注信息', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '13', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('415', '42', 'job_log_id', '任务日志ID', 'bigint(20)', 'Long', 'jobLogId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('416', '42', 'job_name', '任务名称', 'varchar(64)', 'String', 'jobName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('417', '42', 'job_group', '任务组名', 'varchar(64)', 'String', 'jobGroup', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('418', '42', 'invoke_target', '调用目标字符串', 'varchar(500)', 'String', 'invokeTarget', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('419', '42', 'job_message', '日志信息', 'varchar(500)', 'String', 'jobMessage', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('420', '42', 'status', '执行状态（0正常 1失败）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('421', '42', 'exception_info', '异常信息', 'varchar(2000)', 'String', 'exceptionInfo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('422', '42', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('423', '43', 'info_id', '访问ID', 'bigint(20)', 'Long', 'infoId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('424', '43', 'user_name', '用户账号', 'varchar(50)', 'String', 'userName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('425', '43', 'ipaddr', '登录IP地址', 'varchar(50)', 'String', 'ipaddr', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('426', '43', 'login_location', '登录地点', 'varchar(255)', 'String', 'loginLocation', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('427', '43', 'browser', '浏览器类型', 'varchar(50)', 'String', 'browser', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('428', '43', 'os', '操作系统', 'varchar(50)', 'String', 'os', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('429', '43', 'status', '登录状态（0成功 1失败）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('430', '43', 'msg', '提示消息', 'varchar(255)', 'String', 'msg', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('431', '43', 'login_time', '访问时间', 'datetime', 'Date', 'loginTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('432', '44', 'menu_id', '菜单ID', 'bigint(20)', 'Long', 'menuId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('433', '44', 'menu_name', '菜单名称', 'varchar(50)', 'String', 'menuName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('434', '44', 'parent_id', '父菜单ID', 'bigint(20)', 'Long', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('435', '44', 'order_num', '显示顺序', 'int(4)', 'Integer', 'orderNum', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('436', '44', 'path', '路由地址', 'varchar(200)', 'String', 'path', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('437', '44', 'component', '组件路径', 'varchar(255)', 'String', 'component', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('438', '44', 'is_frame', '是否为外链（0是 1否）', 'int(1)', 'Integer', 'isFrame', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('439', '44', 'menu_type', '菜单类型（M目录 C菜单 F按钮）', 'char(1)', 'String', 'menuType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('440', '44', 'visible', '菜单状态（0显示 1隐藏）', 'char(1)', 'String', 'visible', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('441', '44', 'perms', '权限标识', 'varchar(100)', 'String', 'perms', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('442', '44', 'icon', '菜单图标', 'varchar(100)', 'String', 'icon', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '11', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('443', '44', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '12', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('444', '44', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '13', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('445', '44', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '14', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('446', '44', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '15', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('447', '44', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '16', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('448', '45', 'notice_id', '公告ID', 'int(4)', 'Integer', 'noticeId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('449', '45', 'notice_title', '公告标题', 'varchar(50)', 'String', 'noticeTitle', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('450', '45', 'notice_type', '公告类型（1通知 2公告）', 'char(1)', 'String', 'noticeType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('451', '45', 'notice_content', '公告内容', 'varchar(2000)', 'String', 'noticeContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('452', '45', 'status', '公告状态（0正常 1关闭）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('453', '45', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('454', '45', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('455', '45', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('456', '45', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('457', '45', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('458', '46', 'oper_id', '日志主键', 'bigint(20)', 'Long', 'operId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('459', '46', 'title', '模块标题', 'varchar(50)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('460', '46', 'business_type', '业务类型（0其它 1新增 2修改 3删除）', 'int(2)', 'Integer', 'businessType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('461', '46', 'method', '方法名称', 'varchar(100)', 'String', 'method', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('462', '46', 'request_method', '请求方式', 'varchar(10)', 'String', 'requestMethod', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('463', '46', 'operator_type', '操作类别（0其它 1后台用户 2手机端用户）', 'int(1)', 'Integer', 'operatorType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('464', '46', 'oper_name', '操作人员', 'varchar(50)', 'String', 'operName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('465', '46', 'dept_name', '部门名称', 'varchar(50)', 'String', 'deptName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('466', '46', 'oper_url', '请求URL', 'varchar(255)', 'String', 'operUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '9', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('467', '46', 'oper_ip', '主机地址', 'varchar(50)', 'String', 'operIp', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '10', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('468', '46', 'oper_location', '操作地点', 'varchar(255)', 'String', 'operLocation', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', NULL, '11', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('469', '46', 'oper_param', '请求参数', 'varchar(2000)', 'String', 'operParam', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', NULL, '12', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('470', '46', 'json_result', '返回参数', 'varchar(2000)', 'String', 'jsonResult', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', NULL, '13', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('471', '46', 'status', '操作状态（0正常 1异常）', 'int(1)', 'Integer', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', NULL, '14', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('472', '46', 'error_msg', '错误消息', 'varchar(2000)', 'String', 'errorMsg', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', NULL, '15', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('473', '46', 'oper_time', '操作时间', 'datetime', 'Date', 'operTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', NULL, '16', 'admin', TO_DATE('2020-03-23 21:27:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('474', '47', 'fied1', '字段1', 'varchar(20)', 'String', 'fied1', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-04-08 15:59:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-04-08 16:01:20', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('475', '47', 'fied2', '字段2', 'int(11)', 'Long', 'fied2', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-04-08 15:59:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-04-08 16:01:20', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('476', '47', 'fied3', '字段3', 'varchar(100)', 'String', 'fied3', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-04-08 15:59:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-04-08 16:01:20', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('477', '47', 'create_time', NULL, 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '4', 'admin', TO_DATE('2020-04-08 15:59:58', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-04-08 16:01:20', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('288', '28', 'ip_id', 'ID', 'int(11)', 'Long', 'ipId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('289', '28', 'ip_addr', 'IP地址', 'varchar(64)', 'String', 'ipAddr', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '2', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('290', '28', 'ip_passwrod', 'IP密码', 'varchar(64)', 'String', 'ipPasswrod', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', NULL, '3', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('291', '28', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '4', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('292', '28', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, '5', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('293', '28', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', NULL, '6', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('294', '28', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', NULL, '7', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('295', '28', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', NULL, '8', 'admin', TO_DATE('2020-03-23 21:27:24', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 21:34:02', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."GEN_TABLE_COLUMN" VALUES ('306', '30', 'post_id', '岗位ID', 'bigint(20)', 'Long', 'postId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, '1', 'admin', TO_DATE('2020-03-23 21:27:53', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL);

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS";
CREATE TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "TRIGGER_NAME" NVARCHAR2(200) NOT NULL ,
  "TRIGGER_GROUP" NVARCHAR2(200) NOT NULL ,
  "BLOB_DATA" BLOB 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_CALENDARS";
CREATE TABLE "SVECENTER"."QRTZ_CALENDARS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "CALENDAR_NAME" NVARCHAR2(200) NOT NULL ,
  "CALENDAR" BLOB NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS";
CREATE TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "TRIGGER_NAME" NVARCHAR2(200) NOT NULL ,
  "TRIGGER_GROUP" NVARCHAR2(200) NOT NULL ,
  "CRON_EXPRESSION" NVARCHAR2(200) NOT NULL ,
  "TIME_ZONE_ID" NVARCHAR2(80) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO "SVECENTER"."QRTZ_CRON_TRIGGERS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');
INSERT INTO "SVECENTER"."QRTZ_CRON_TRIGGERS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO "SVECENTER"."QRTZ_CRON_TRIGGERS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS";
CREATE TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "ENTRY_ID" NVARCHAR2(95) NOT NULL ,
  "TRIGGER_NAME" NVARCHAR2(200) NOT NULL ,
  "TRIGGER_GROUP" NVARCHAR2(200) NOT NULL ,
  "INSTANCE_NAME" NVARCHAR2(200) NOT NULL ,
  "FIRED_TIME" NUMBER(20) NOT NULL ,
  "SCHED_TIME" NUMBER(20) NOT NULL ,
  "PRIORITY" NUMBER(11) NOT NULL ,
  "STATE" NVARCHAR2(16) NOT NULL ,
  "JOB_NAME" NVARCHAR2(200) ,
  "JOB_GROUP" NVARCHAR2(200) ,
  "IS_NONCONCURRENT" NVARCHAR2(1) ,
  "REQUESTS_RECOVERY" NVARCHAR2(1) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_JOB_DETAILS";
CREATE TABLE "SVECENTER"."QRTZ_JOB_DETAILS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "JOB_NAME" NVARCHAR2(200) NOT NULL ,
  "JOB_GROUP" NVARCHAR2(200) NOT NULL ,
  "DESCRIPTION" NVARCHAR2(250) ,
  "JOB_CLASS_NAME" NVARCHAR2(250) NOT NULL ,
  "IS_DURABLE" NVARCHAR2(1) NOT NULL ,
  "IS_NONCONCURRENT" NVARCHAR2(1) NOT NULL ,
  "IS_UPDATE_DATA" NVARCHAR2(1) NOT NULL ,
  "REQUESTS_RECOVERY" NVARCHAR2(1) NOT NULL ,
  "JOB_DATA" BLOB 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO "SVECENTER"."QRTZ_JOB_DETAILS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 'com.ruoyi.common.utils.job.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', HEXTORAW('ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720027636F6D2E72756F79692E70726F6A6563742E6D6F6E69746F722E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720029636F6D2E72756F79692E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E74697479000000000000000102000A4C0009626567696E54696D6571007E00094C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C00096461746153636F706571007E00094C0007656E6454696D6571007E00094C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707070707070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800'));
INSERT INTO "SVECENTER"."QRTZ_JOB_DETAILS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'com.ruoyi.common.utils.job.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', HEXTORAW('ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720027636F6D2E72756F79692E70726F6A6563742E6D6F6E69746F722E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720029636F6D2E72756F79692E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E74697479000000000000000102000A4C0009626567696E54696D6571007E00094C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C00096461746153636F706571007E00094C0007656E6454696D6571007E00094C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707070707070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800'));
INSERT INTO "SVECENTER"."QRTZ_JOB_DETAILS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 'com.ruoyi.common.utils.job.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', HEXTORAW('ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720027636F6D2E72756F79692E70726F6A6563742E6D6F6E69746F722E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720029636F6D2E72756F79692E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E74697479000000000000000102000A4C0009626567696E54696D6571007E00094C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C00096461746153636F706571007E00094C0007656E6454696D6571007E00094C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707070707070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800'));

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_LOCKS";
CREATE TABLE "SVECENTER"."QRTZ_LOCKS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "LOCK_NAME" NVARCHAR2(40) NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO "SVECENTER"."QRTZ_LOCKS" VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO "SVECENTER"."QRTZ_LOCKS" VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_PAUSED_TRIGGER_GRPS";
CREATE TABLE "SVECENTER"."QRTZ_PAUSED_TRIGGER_GRPS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "TRIGGER_GROUP" NVARCHAR2(200) NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE";
CREATE TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "INSTANCE_NAME" NVARCHAR2(200) NOT NULL ,
  "LAST_CHECKIN_TIME" NUMBER(20) NOT NULL ,
  "CHECKIN_INTERVAL" NUMBER(20) NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
INSERT INTO "SVECENTER"."QRTZ_SCHEDULER_STATE" VALUES ('RuoyiScheduler', 'DESKTOP-V2HPO7R1588950539491', '1589247790930', '15000');
INSERT INTO "SVECENTER"."QRTZ_SCHEDULER_STATE" VALUES ('RuoyiScheduler', 'DESKTOP-V2HPO7R1588834272392', '1589247792518', '15000');

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS";
CREATE TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "TRIGGER_NAME" NVARCHAR2(200) NOT NULL ,
  "TRIGGER_GROUP" NVARCHAR2(200) NOT NULL ,
  "REPEAT_COUNT" NUMBER(20) NOT NULL ,
  "REPEAT_INTERVAL" NUMBER(20) NOT NULL ,
  "TIMES_TRIGGERED" NUMBER(20) NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS";
CREATE TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "TRIGGER_NAME" NVARCHAR2(200) NOT NULL ,
  "TRIGGER_GROUP" NVARCHAR2(200) NOT NULL ,
  "STR_PROP_1" NVARCHAR2(512) ,
  "STR_PROP_2" NVARCHAR2(512) ,
  "STR_PROP_3" NVARCHAR2(512) ,
  "INT_PROP_1" NUMBER(11) ,
  "INT_PROP_2" NUMBER(11) ,
  "LONG_PROP_1" NUMBER(20) ,
  "LONG_PROP_2" NUMBER(20) ,
  "DEC_PROP_1" NUMBER ,
  "DEC_PROP_2" NUMBER ,
  "BOOL_PROP_1" NVARCHAR2(1) ,
  "BOOL_PROP_2" NVARCHAR2(1) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE "SVECENTER"."QRTZ_TRIGGERS";
CREATE TABLE "SVECENTER"."QRTZ_TRIGGERS" (
  "SCHED_NAME" NVARCHAR2(120) NOT NULL ,
  "TRIGGER_NAME" NVARCHAR2(200) NOT NULL ,
  "TRIGGER_GROUP" NVARCHAR2(200) NOT NULL ,
  "JOB_NAME" NVARCHAR2(200) NOT NULL ,
  "JOB_GROUP" NVARCHAR2(200) NOT NULL ,
  "DESCRIPTION" NVARCHAR2(250) ,
  "NEXT_FIRE_TIME" NUMBER(20) ,
  "PREV_FIRE_TIME" NUMBER(20) ,
  "PRIORITY" NUMBER(11) ,
  "TRIGGER_STATE" NVARCHAR2(16) NOT NULL ,
  "TRIGGER_TYPE" NVARCHAR2(8) NOT NULL ,
  "START_TIME" NUMBER(20) NOT NULL ,
  "END_TIME" NUMBER(20) ,
  "CALENDAR_NAME" NVARCHAR2(200) ,
  "MISFIRE_INSTR" NUMBER(6) ,
  "JOB_DATA" BLOB 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO "SVECENTER"."QRTZ_TRIGGERS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, '1588950540000', '-1', '5', 'PAUSED', 'CRON', '1588950539000', '0', NULL, '2', NULL);
INSERT INTO "SVECENTER"."QRTZ_TRIGGERS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, '1588950540000', '-1', '5', 'PAUSED', 'CRON', '1588950539000', '0', NULL, '2', NULL);
INSERT INTO "SVECENTER"."QRTZ_TRIGGERS" VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, '1588950540000', '-1', '5', 'PAUSED', 'CRON', '1588950539000', '0', NULL, '2', NULL);

-- ----------------------------
-- Table structure for SYS_CONFIG
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_CONFIG";
CREATE TABLE "SVECENTER"."SYS_CONFIG" (
  "CONFIG_ID" NUMBER(11) NOT NULL ,
  "CONFIG_NAME" NVARCHAR2(100) ,
  "CONFIG_KEY" NVARCHAR2(100) ,
  "CONFIG_VALUE" NVARCHAR2(500) ,
  "CONFIG_TYPE" NCHAR(1) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."CONFIG_ID" IS '参数主键';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."CONFIG_NAME" IS '参数名称';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."CONFIG_KEY" IS '参数键名';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."CONFIG_VALUE" IS '参数键值';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."CONFIG_TYPE" IS '系统内置（Y是 N否）';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_CONFIG"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."SYS_CONFIG" IS '参数配置表';

-- ----------------------------
-- Records of SYS_CONFIG
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_CONFIG" VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO "SVECENTER"."SYS_CONFIG" VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '初始化密码 123456');
INSERT INTO "SVECENTER"."SYS_CONFIG" VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for SYS_DEPT
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_DEPT";
CREATE TABLE "SVECENTER"."SYS_DEPT" (
  "DEPT_ID" NUMBER(20) NOT NULL ,
  "PARENT_ID" NUMBER(20) ,
  "ANCESTORS" NVARCHAR2(50) ,
  "DEPT_NAME" NVARCHAR2(30) ,
  "ORDER_NUM" NUMBER(11) ,
  "LEADER" NVARCHAR2(20) ,
  "PHONE" NVARCHAR2(11) ,
  "EMAIL" NVARCHAR2(50) ,
  "STATUS" NCHAR(1) ,
  "DEL_FLAG" NCHAR(1) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."DEPT_ID" IS '部门id';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."PARENT_ID" IS '父部门id';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."ANCESTORS" IS '祖级列表';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."DEPT_NAME" IS '部门名称';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."ORDER_NUM" IS '显示顺序';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."LEADER" IS '负责人';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."PHONE" IS '联系电话';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."EMAIL" IS '邮箱';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."STATUS" IS '部门状态（0正常 1停用）';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."DEL_FLAG" IS '删除标志（0代表存在 2代表删除）';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_DEPT"."UPDATE_TIME" IS '更新时间';
COMMENT ON TABLE "SVECENTER"."SYS_DEPT" IS '部门表';

-- ----------------------------
-- Records of SYS_DEPT
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('100', '0', '0', '华为科技', '0', '华为', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-03-19 21:22:57', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('101', '100', '0,100', '深圳总公司', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('102', '100', '0,100', '长沙分公司', '2', '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('103', '101', '0,100,101', '研发部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('104', '101', '0,100,101', '市场部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('105', '101', '0,100,101', '测试部门', '3', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('106', '101', '0,100,101', '财务部门', '4', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('107', '101', '0,100,101', '运维部门', '5', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('108', '102', '0,100,102', '市场部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_DEPT" VALUES ('109', '102', '0,100,102', '财务部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for SYS_DICT_DATA
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_DICT_DATA";
CREATE TABLE "SVECENTER"."SYS_DICT_DATA" (
  "DICT_CODE" NUMBER(20) NOT NULL ,
  "DICT_SORT" NUMBER(11) ,
  "DICT_LABEL" NVARCHAR2(100) ,
  "DICT_VALUE" NVARCHAR2(100) ,
  "DICT_TYPE" NVARCHAR2(100) ,
  "CSS_CLASS" NVARCHAR2(100) ,
  "LIST_CLASS" NVARCHAR2(100) ,
  "IS_DEFAULT" NCHAR(1) ,
  "STATUS" NCHAR(1) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."DICT_CODE" IS '字典编码';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."DICT_SORT" IS '字典排序';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."DICT_LABEL" IS '字典标签';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."DICT_VALUE" IS '字典键值';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."DICT_TYPE" IS '字典类型';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."CSS_CLASS" IS '样式属性（其他样式扩展）';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."LIST_CLASS" IS '表格回显样式';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."IS_DEFAULT" IS '是否默认（Y是 N否）';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."STATUS" IS '状态（0正常 1停用）';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_DATA"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."SYS_DICT_DATA" IS '字典数据表';

-- ----------------------------
-- Records of SYS_DICT_DATA
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('1', '1', '男', '0', 'sys_user_sex', NULL, NULL, 'Y', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '性别男');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('2', '2', '女', '1', 'sys_user_sex', NULL, NULL, 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '性别女');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('3', '3', '未知', '2', 'sys_user_sex', NULL, NULL, 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '性别未知');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('4', '1', '显示', '0', 'sys_show_hide', NULL, 'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '显示菜单');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '隐藏菜单');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('6', '1', '正常', '0', 'sys_normal_disable', NULL, 'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '正常状态');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('7', '2', '停用', '1', 'sys_normal_disable', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '停用状态');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('8', '1', '正常', '0', 'sys_job_status', NULL, 'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '正常状态');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('9', '2', '暂停', '1', 'sys_job_status', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '停用状态');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('10', '1', '默认', 'DEFAULT', 'sys_job_group', NULL, NULL, 'Y', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '默认分组');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('11', '2', '系统', 'SYSTEM', 'sys_job_group', NULL, NULL, 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统分组');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('12', '1', '是', 'Y', 'sys_yes_no', NULL, 'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统默认是');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('13', '2', '否', 'N', 'sys_yes_no', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统默认否');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('14', '1', '通知', '1', 'sys_notice_type', NULL, 'warning', 'Y', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '通知');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('15', '2', '公告', '2', 'sys_notice_type', NULL, 'success', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '公告');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('16', '1', '正常', '0', 'sys_notice_status', NULL, 'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '正常状态');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('17', '2', '关闭', '1', 'sys_notice_status', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '关闭状态');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('18', '1', '新增', '1', 'sys_oper_type', NULL, 'info', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '新增操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('19', '2', '修改', '2', 'sys_oper_type', NULL, 'info', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '修改操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('20', '3', '删除', '3', 'sys_oper_type', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '删除操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('21', '4', '授权', '4', 'sys_oper_type', NULL, 'primary', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '授权操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('22', '5', '导出', '5', 'sys_oper_type', NULL, 'warning', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '导出操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('23', '6', '导入', '6', 'sys_oper_type', NULL, 'warning', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '导入操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('24', '7', '强退', '7', 'sys_oper_type', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '强退操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('25', '8', '生成代码', '8', 'sys_oper_type', NULL, 'warning', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '生成操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('26', '9', '清空数据', '9', 'sys_oper_type', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '清空操作');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('27', '1', '成功', '0', 'sys_common_status', NULL, 'primary', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '正常状态');
INSERT INTO "SVECENTER"."SYS_DICT_DATA" VALUES ('28', '2', '失败', '1', 'sys_common_status', NULL, 'danger', 'N', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '停用状态');

-- ----------------------------
-- Table structure for SYS_DICT_TYPE
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_DICT_TYPE";
CREATE TABLE "SVECENTER"."SYS_DICT_TYPE" (
  "DICT_ID" NUMBER(20) NOT NULL ,
  "DICT_NAME" NVARCHAR2(100) ,
  "DICT_TYPE" NVARCHAR2(100) ,
  "STATUS" NCHAR(1) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."DICT_ID" IS '字典主键';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."DICT_NAME" IS '字典名称';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."DICT_TYPE" IS '字典类型';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."STATUS" IS '状态（0正常 1停用）';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_DICT_TYPE"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."SYS_DICT_TYPE" IS '字典类型表';

-- ----------------------------
-- Records of SYS_DICT_TYPE
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '用户性别列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('2', '菜单状态', 'sys_show_hide', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '菜单状态列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('3', '系统开关', 'sys_normal_disable', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统开关列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('4', '任务状态', 'sys_job_status', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '任务状态列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('5', '任务分组', 'sys_job_group', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '任务分组列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('6', '系统是否', 'sys_yes_no', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统是否列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('7', '通知类型', 'sys_notice_type', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '通知类型列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('8', '通知状态', 'sys_notice_status', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '通知状态列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('9', '操作类型', 'sys_oper_type', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '操作类型列表');
INSERT INTO "SVECENTER"."SYS_DICT_TYPE" VALUES ('10', '系统状态', 'sys_common_status', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '登录状态列表');

-- ----------------------------
-- Table structure for SYS_JOB
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_JOB";
CREATE TABLE "SVECENTER"."SYS_JOB" (
  "JOB_ID" NUMBER(20) NOT NULL ,
  "JOB_NAME" NVARCHAR2(64) NOT NULL ,
  "JOB_GROUP" NVARCHAR2(64) NOT NULL ,
  "INVOKE_TARGET" NVARCHAR2(500) NOT NULL ,
  "CRON_EXPRESSION" NVARCHAR2(255) ,
  "MISFIRE_POLICY" NVARCHAR2(20) ,
  "CONCURRENT" NCHAR(1) ,
  "STATUS" NCHAR(1) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."JOB_ID" IS '任务ID';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."JOB_NAME" IS '任务名称';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."JOB_GROUP" IS '任务组名';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."INVOKE_TARGET" IS '调用目标字符串';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."CRON_EXPRESSION" IS 'cron执行表达式';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."MISFIRE_POLICY" IS '计划执行错误策略（1立即执行 2执行一次 3放弃执行）';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."CONCURRENT" IS '是否并发执行（0允许 1禁止）';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."STATUS" IS '状态（0正常 1暂停）';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB"."REMARK" IS '备注信息';
COMMENT ON TABLE "SVECENTER"."SYS_JOB" IS '定时任务调度表';

-- ----------------------------
-- Records of SYS_JOB
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_JOB" VALUES ('1', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2020-04-14 17:32:35', 'SYYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO "SVECENTER"."SYS_JOB" VALUES ('2', '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(''ry'')', '0/15 * * * * ?', '3', '1', '1', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2020-04-14 17:22:01', 'SYYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO "SVECENTER"."SYS_JOB" VALUES ('3', '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(''ry'', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2020-04-14 17:24:28', 'SYYYY-MM-DD HH24:MI:SS'), NULL);

-- ----------------------------
-- Table structure for SYS_JOB_LOG
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_JOB_LOG";
CREATE TABLE "SVECENTER"."SYS_JOB_LOG" (
  "JOB_LOG_ID" NUMBER(20) NOT NULL ,
  "JOB_NAME" NVARCHAR2(64) NOT NULL ,
  "JOB_GROUP" NVARCHAR2(64) NOT NULL ,
  "INVOKE_TARGET" NVARCHAR2(500) NOT NULL ,
  "JOB_MESSAGE" NVARCHAR2(500) ,
  "STATUS" NCHAR(1) ,
  "EXCEPTION_INFO" NCLOB ,
  "CREATE_TIME" DATE 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_JOB_LOG"."JOB_LOG_ID" IS '任务日志ID';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB_LOG"."JOB_NAME" IS '任务名称';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB_LOG"."JOB_GROUP" IS '任务组名';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB_LOG"."INVOKE_TARGET" IS '调用目标字符串';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB_LOG"."JOB_MESSAGE" IS '日志信息';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB_LOG"."STATUS" IS '执行状态（0正常 1失败）';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB_LOG"."EXCEPTION_INFO" IS '异常信息';
COMMENT ON COLUMN "SVECENTER"."SYS_JOB_LOG"."CREATE_TIME" IS '创建时间';
COMMENT ON TABLE "SVECENTER"."SYS_JOB_LOG" IS '定时任务调度日志表';

-- ----------------------------
-- Table structure for SYS_LOGININFOR
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_LOGININFOR";
CREATE TABLE "SVECENTER"."SYS_LOGININFOR" (
  "INFO_ID" NUMBER(20) NOT NULL ,
  "USER_NAME" NVARCHAR2(50) ,
  "IPADDR" NVARCHAR2(50) ,
  "LOGIN_LOCATION" NVARCHAR2(255) ,
  "BROWSER" NVARCHAR2(50) ,
  "OS" NVARCHAR2(50) ,
  "STATUS" NCHAR(1) ,
  "MSG" NVARCHAR2(255) ,
  "LOGIN_TIME" DATE 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."INFO_ID" IS '访问ID';
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."USER_NAME" IS '用户账号';
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."IPADDR" IS '登录IP地址';
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."LOGIN_LOCATION" IS '登录地点';
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."BROWSER" IS '浏览器类型';
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."OS" IS '操作系统';
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."STATUS" IS '登录状态（0成功 1失败）';
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."MSG" IS '提示消息';
COMMENT ON COLUMN "SVECENTER"."SYS_LOGININFOR"."LOGIN_TIME" IS '访问时间';
COMMENT ON TABLE "SVECENTER"."SYS_LOGININFOR" IS '系统访问记录';

-- ----------------------------
-- Records of SYS_LOGININFOR
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('1', 'admin', '14.222.40.112', '广东 东莞', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-08 14:28:23', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('2', 'admin', '14.222.40.112', 'XX XX', 'Chrome 8', 'Windows 10', '0', '退出成功', TO_DATE('2020-04-08 15:39:45', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('3', 'admin', '14.222.40.112', 'XX XX', 'Chrome 8', 'Windows 10', '1', '验证码错误', TO_DATE('2020-04-08 15:41:26', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('4', 'admin', '14.222.40.112', 'XX XX', 'Chrome 8', 'Windows 10', '1', '验证码错误', TO_DATE('2020-04-08 15:41:32', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('5', 'admin', '14.222.40.112', 'XX XX', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-08 15:41:37', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('6', 'admin', '14.222.40.112', '广东 东莞', 'Chrome 8', 'Windows 10', '0', '退出成功', TO_DATE('2020-04-08 16:04:53', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('7', 'admin', '14.222.40.112', '广东 东莞', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-08 16:04:58', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('8', 'admin', '14.222.40.112', 'XX XX', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-08 18:58:11', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('9', 'admin', '14.222.40.112', '广东 东莞', 'Firefox 7', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-08 21:05:13', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('10', 'admin', '14.222.40.112', '广东 东莞', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-08 21:06:25', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('11', 'admin', '14.222.40.112', 'XX XX', 'Microsoft Edge', 'Windows 10', '1', '用户不存在/密码错误', TO_DATE('2020-04-08 21:08:13', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('12', 'admin', '14.222.40.112', 'XX XX', 'Microsoft Edge', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-08 21:08:28', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('13', 'admin', '14.222.40.112', 'XX XX', 'Microsoft Edge', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-08 21:08:32', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('14', 'admin', '14.222.41.90', 'XX XX', 'Chrome 8', 'Windows 10', '1', '验证码错误', TO_DATE('2020-04-09 09:30:54', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('15', 'admin', '14.222.41.90', 'XX XX', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-09 09:31:00', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('16', 'admin', '14.222.41.48', 'XX XX', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-11 13:42:55', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('17', 'admin', '14.222.41.48', 'XX XX', 'Chrome 8', 'Windows 10', '0', '退出成功', TO_DATE('2020-04-11 13:43:27', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('18', 'admin', '14.222.41.48', 'XX XX', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-11 13:43:32', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('113', 'admin', '192.168.19.36', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-14 22:55:16', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('111', 'admin', '192.168.19.36', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', TO_DATE('2020-04-14 16:59:53', 'SYYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SVECENTER"."SYS_LOGININFOR" VALUES ('112', 'admin', '192.168.19.36', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', TO_DATE('2020-04-14 17:00:13', 'SYYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for SYS_MENU
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_MENU";
CREATE TABLE "SVECENTER"."SYS_MENU" (
  "MENU_ID" NUMBER(20) NOT NULL ,
  "MENU_NAME" NVARCHAR2(50) NOT NULL ,
  "PARENT_ID" NUMBER(20) ,
  "ORDER_NUM" NUMBER(11) ,
  "PATH" NVARCHAR2(200) ,
  "COMPONENT" NVARCHAR2(255) ,
  "IS_FRAME" NUMBER(11) ,
  "MENU_TYPE" NCHAR(1) ,
  "VISIBLE" NCHAR(1) ,
  "PERMS" NVARCHAR2(100) ,
  "ICON" NVARCHAR2(100) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) ,
  "STATUS" VARCHAR2(1 BYTE) DEFAULT '0' 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."MENU_ID" IS '菜单ID';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."MENU_NAME" IS '菜单名称';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."PARENT_ID" IS '父菜单ID';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."ORDER_NUM" IS '显示顺序';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."PATH" IS '路由地址';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."COMPONENT" IS '组件路径';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."IS_FRAME" IS '是否为外链（0是 1否）';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."MENU_TYPE" IS '菜单类型（M目录 C菜单 F按钮）';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."VISIBLE" IS '菜单状态（0显示 1隐藏）';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."PERMS" IS '权限标识';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."ICON" IS '菜单图标';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."REMARK" IS '备注';
COMMENT ON COLUMN "SVECENTER"."SYS_MENU"."STATUS" IS '菜单状态';
COMMENT ON TABLE "SVECENTER"."SYS_MENU" IS '菜单权限表';

-- ----------------------------
-- Records of SYS_MENU
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2093', '移动执法', '-1', '2', 'http://192.168.19.40:82', NULL, '1', 'S', '0', NULL, 'edit', 'admin', TO_DATE('2020-05-08 22:44:05', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1037', '公告新增', '107', '2', '#', NULL, '1', 'F', '0', 'system:notice:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1038', '公告修改', '107', '3', '#', NULL, '1', 'F', '0', 'system:notice:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1039', '公告删除', '107', '4', '#', NULL, '1', 'F', '0', 'system:notice:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1040', '操作查询', '500', '1', '#', NULL, '1', 'F', '0', 'monitor:operlog:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1041', '操作删除', '500', '2', '#', NULL, '1', 'F', '0', 'monitor:operlog:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1042', '日志导出', '500', '4', '#', NULL, '1', 'F', '0', 'monitor:operlog:export', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1043', '登录查询', '501', '1', '#', NULL, '1', 'F', '0', 'monitor:logininfor:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1044', '登录删除', '501', '2', '#', NULL, '1', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1045', '日志导出', '501', '3', '#', NULL, '1', 'F', '0', 'monitor:logininfor:export', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1046', '在线查询', '109', '1', '#', NULL, '1', 'F', '0', 'monitor:online:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1047', '批量强退', '109', '2', '#', NULL, '1', 'F', '0', 'monitor:online:batchLogout', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1048', '单条强退', '109', '3', '#', NULL, '1', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1049', '任务查询', '110', '1', '#', NULL, '1', 'F', '0', 'monitor:job:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1050', '任务新增', '110', '2', '#', NULL, '1', 'F', '0', 'monitor:job:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1051', '任务修改', '110', '3', '#', NULL, '1', 'F', '0', 'monitor:job:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1052', '任务删除', '110', '4', '#', NULL, '1', 'F', '0', 'monitor:job:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1053', '状态修改', '110', '5', '#', NULL, '1', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1054', '任务导出', '110', '7', '#', NULL, '1', 'F', '0', 'monitor:job:export', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1055', '生成查询', '114', '1', '#', NULL, '1', 'F', '0', 'tool:gen:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1056', '生成修改', '114', '2', '#', NULL, '1', 'F', '0', 'tool:gen:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1057', '生成删除', '114', '3', '#', NULL, '1', 'F', '0', 'tool:gen:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1058', '导入代码', '114', '2', '#', NULL, '1', 'F', '0', 'tool:gen:import', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1059', '预览代码', '114', '4', '#', NULL, '1', 'F', '0', 'tool:gen:preview', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1060', '生成代码', '114', '5', '#', NULL, '1', 'F', '0', 'tool:gen:code', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2000', '案例', '0', '4', 'sample', NULL, '1', 'M', '0', NULL, 'form', 'admin', TO_DATE('2020-03-23 21:10:38', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-03-23 21:14:43', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2002', '测试IP信息', '2000', '1', 'ip', 'sample/ip/index', '1', 'C', '0', 'sample:ip:list', 'example', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-03-24 09:12:29', 'SYYYY-MM-DD HH24:MI:SS'), '测试IP信息菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2003', '测试IP信息查询', '2002', '1', '#', NULL, '1', 'F', '0', 'sample:ip:query', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2004', '测试IP信息新增', '2002', '2', '#', NULL, '1', 'F', '0', 'sample:ip:add', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2005', '测试IP信息修改', '2002', '3', '#', NULL, '1', 'F', '0', 'sample:ip:edit', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2006', '测试IP信息删除', '2002', '4', '#', NULL, '1', 'F', '0', 'sample:ip:remove', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2007', '测试IP信息导出', '2002', '5', '#', NULL, '1', 'F', '0', 'sample:ip:export', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2008', 'Excel导入导出测试', '2000', '1', 'eximport', 'sample/eximport/index', '1', 'C', '0', 'sample:eximport:list', 'excel', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-04-11 13:43:20', 'SYYYY-MM-DD HH24:MI:SS'), 'Excel导入导出测试菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2009', 'Excel导入导出测试查询', '2008', '1', '#', NULL, '1', 'F', '0', 'sample:eximport:query', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2010', 'Excel导入导出测试新增', '2008', '2', '#', NULL, '1', 'F', '0', 'sample:eximport:add', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2011', 'Excel导入导出测试修改', '2008', '3', '#', NULL, '1', 'F', '0', 'sample:eximport:edit', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2012', 'Excel导入导出测试删除', '2008', '4', '#', NULL, '1', 'F', '0', 'sample:eximport:remove', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2013', 'Excel导入导出测试导出', '2008', '5', '#', NULL, '1', 'F', '0', 'sample:eximport:export', '#', 'admin', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1', '系统管理', '0', '1', 'system', NULL, '1', 'M', '0', NULL, 'system', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统管理目录', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2', '系统监控', '0', '2', 'monitor', NULL, '1', 'M', '0', NULL, 'monitor', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统监控目录', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('3', '系统工具', '0', '3', 'tool', NULL, '1', 'M', '0', NULL, 'tool', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统工具目录', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('100', '用户管理', '1', '1', 'user', 'system/user/index', '1', 'C', '0', 'system:user:list', 'user', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '用户管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('101', '角色管理', '1', '2', 'role', 'system/role/index', '1', 'C', '0', 'system:role:list', 'peoples', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '角色管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '1', 'C', '0', 'system:menu:list', 'tree-table', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '菜单管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('103', '部门管理', '1', '4', 'dept', 'system/dept/index', '1', 'C', '0', 'system:dept:list', 'tree', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '部门管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('104', '岗位管理', '1', '5', 'post', 'system/post/index', '1', 'C', '0', 'system:post:list', 'post', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '岗位管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('105', '字典管理', '1', '6', 'dict', 'system/dict/index', '1', 'C', '0', 'system:dict:list', 'dict', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '字典管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('106', '参数设置', '1', '7', 'config', 'system/config/index', '1', 'C', '0', 'system:config:list', 'edit', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '参数设置菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('107', '通知公告', '1', '8', 'notice', 'system/notice/index', '1', 'C', '0', 'system:notice:list', 'message', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '通知公告菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('108', '日志管理', '1', '9', 'log', 'system/log/index', '1', 'M', '0', NULL, 'log', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '日志管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('109', '在线用户', '2', '1', 'online', 'monitor/online/index', '1', 'C', '0', 'monitor:online:list', 'online', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '在线用户菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('110', '定时任务', '2', '2', 'job', 'monitor/job/index', '1', 'C', '0', 'monitor:job:list', 'job', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '定时任务菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('111', '数据监控', '2', '3', 'druid', 'monitor/druid/index', '1', 'C', '0', 'monitor:druid:list', 'druid', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '数据监控菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('112', '服务监控', '2', '4', 'server', 'monitor/server/index', '1', 'C', '0', 'monitor:server:list', 'server', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '服务监控菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('113', '表单构建', '3', '1', 'build', 'tool/build/index', '1', 'C', '0', 'tool:build:list', 'build', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '表单构建菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('114', '代码生成', '3', '2', 'gen', 'tool/gen/index', '1', 'C', '0', 'tool:gen:list', 'code', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '代码生成菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('115', '系统接口', '3', '3', 'swagger', 'tool/swagger/index', '1', 'C', '0', 'tool:swagger:list', 'swagger', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统接口菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('500', '操作日志', '108', '1', 'operlog', 'monitor/operlog/index', '1', 'C', '0', 'monitor:operlog:list', 'form', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '操作日志菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('501', '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '1', 'C', '0', 'monitor:logininfor:list', 'logininfor', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '登录日志菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1001', '用户查询', '100', '1', NULL, NULL, '1', 'F', '0', 'system:user:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1002', '用户新增', '100', '2', NULL, NULL, '1', 'F', '0', 'system:user:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1003', '用户修改', '100', '3', NULL, NULL, '1', 'F', '0', 'system:user:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1004', '用户删除', '100', '4', NULL, NULL, '1', 'F', '0', 'system:user:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1005', '用户导出', '100', '5', NULL, NULL, '1', 'F', '0', 'system:user:export', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1006', '用户导入', '100', '6', NULL, NULL, '1', 'F', '0', 'system:user:import', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1007', '重置密码', '100', '7', NULL, NULL, '1', 'F', '0', 'system:user:resetPwd', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1008', '角色查询', '101', '1', NULL, NULL, '1', 'F', '0', 'system:role:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1009', '角色新增', '101', '2', NULL, NULL, '1', 'F', '0', 'system:role:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1010', '角色修改', '101', '3', NULL, NULL, '1', 'F', '0', 'system:role:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1011', '角色删除', '101', '4', NULL, NULL, '1', 'F', '0', 'system:role:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1012', '角色导出', '101', '5', NULL, NULL, '1', 'F', '0', 'system:role:export', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1013', '菜单查询', '102', '1', NULL, NULL, '1', 'F', '0', 'system:menu:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1014', '菜单新增', '102', '2', NULL, NULL, '1', 'F', '0', 'system:menu:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1015', '菜单修改', '102', '3', NULL, NULL, '1', 'F', '0', 'system:menu:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1016', '菜单删除', '102', '4', NULL, NULL, '1', 'F', '0', 'system:menu:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1017', '部门查询', '103', '1', NULL, NULL, '1', 'F', '0', 'system:dept:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1018', '部门新增', '103', '2', NULL, NULL, '1', 'F', '0', 'system:dept:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1019', '部门修改', '103', '3', NULL, NULL, '1', 'F', '0', 'system:dept:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1020', '部门删除', '103', '4', NULL, NULL, '1', 'F', '0', 'system:dept:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1021', '岗位查询', '104', '1', NULL, NULL, '1', 'F', '0', 'system:post:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1022', '岗位新增', '104', '2', NULL, NULL, '1', 'F', '0', 'system:post:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1023', '岗位修改', '104', '3', NULL, NULL, '1', 'F', '0', 'system:post:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1024', '岗位删除', '104', '4', NULL, NULL, '1', 'F', '0', 'system:post:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1025', '岗位导出', '104', '5', NULL, NULL, '1', 'F', '0', 'system:post:export', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1026', '字典查询', '105', '1', '#', NULL, '1', 'F', '0', 'system:dict:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1027', '字典新增', '105', '2', '#', NULL, '1', 'F', '0', 'system:dict:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1028', '字典修改', '105', '3', '#', NULL, '1', 'F', '0', 'system:dict:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1029', '字典删除', '105', '4', '#', NULL, '1', 'F', '0', 'system:dict:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1030', '字典导出', '105', '5', '#', NULL, '1', 'F', '0', 'system:dict:export', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1031', '参数查询', '106', '1', '#', NULL, '1', 'F', '0', 'system:config:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1032', '参数新增', '106', '2', '#', NULL, '1', 'F', '0', 'system:config:add', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1033', '参数修改', '106', '3', '#', NULL, '1', 'F', '0', 'system:config:edit', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1034', '参数删除', '106', '4', '#', NULL, '1', 'F', '0', 'system:config:remove', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1035', '参数导出', '106', '5', '#', NULL, '1', 'F', '0', 'system:config:export', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('1036', '公告查询', '107', '1', '#', NULL, '1', 'F', '0', 'system:notice:query', '#', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL, '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('0', '管理系统', '-1', '1', 'http://192.168.19.40:81', NULL, '1', 'S', '0', NULL, 'bug', 'admin', TO_DATE('2020-04-29 14:14:22', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-04-29 14:14:28', 'SYYYY-MM-DD HH24:MI:SS'), '管理系统', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2088', '移法管理', '2093', '1', 'system', NULL, '1', 'M', '0', NULL, 'system', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '系统管理目录', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2055', '用户管理', '2088', '1', 'user', 'system/user/index', '1', 'C', '0', 'system:user:list', 'user', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '用户管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2056', '角色管理', '2088', '2', 'role', 'system/role/index', '1', 'C', '0', 'system:role:list', 'peoples', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '角色管理菜单', '0');
INSERT INTO "SVECENTER"."SYS_MENU" VALUES ('2057', '部门管理', '2088', '4', 'dept', 'system/dept/index', '1', 'C', '0', 'system:dept:list', 'tree', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '部门管理菜单', '0');

-- ----------------------------
-- Table structure for SYS_NOTICE
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_NOTICE";
CREATE TABLE "SVECENTER"."SYS_NOTICE" (
  "NOTICE_ID" NUMBER(11) NOT NULL ,
  "NOTICE_TITLE" NVARCHAR2(50) NOT NULL ,
  "NOTICE_TYPE" NCHAR(1) NOT NULL ,
  "NOTICE_CONTENT" NCLOB ,
  "STATUS" NCHAR(1) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(255) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."NOTICE_ID" IS '公告ID';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."NOTICE_TITLE" IS '公告标题';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."NOTICE_TYPE" IS '公告类型（1通知 2公告）';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."NOTICE_CONTENT" IS '公告内容';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."STATUS" IS '公告状态（0正常 1关闭）';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_NOTICE"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."SYS_NOTICE" IS '通知公告表';

-- ----------------------------
-- Records of SYS_NOTICE
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_NOTICE" VALUES ('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '管理员');
INSERT INTO "SVECENTER"."SYS_NOTICE" VALUES ('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), '管理员');
INSERT INTO "SVECENTER"."SYS_NOTICE" VALUES ('10', 'xxx', '1', '<p>xxx</p>', '0', 'admin', TO_DATE('2020-03-23 20:37:51', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-03-23 20:38:18', 'SYYYY-MM-DD HH24:MI:SS'), NULL);

-- ----------------------------
-- Table structure for SYS_OPER_LOG
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_OPER_LOG";
CREATE TABLE "SVECENTER"."SYS_OPER_LOG" (
  "OPER_ID" NUMBER(20) NOT NULL ,
  "TITLE" NVARCHAR2(50) ,
  "BUSINESS_TYPE" NUMBER(11) ,
  "METHOD" NVARCHAR2(100) ,
  "REQUEST_METHOD" NVARCHAR2(10) ,
  "OPERATOR_TYPE" NUMBER(11) ,
  "OPER_NAME" NVARCHAR2(50) ,
  "DEPT_NAME" NVARCHAR2(50) ,
  "OPER_URL" NVARCHAR2(255) ,
  "OPER_IP" NVARCHAR2(50) ,
  "OPER_LOCATION" NVARCHAR2(255) ,
  "OPER_PARAM" NCLOB ,
  "JSON_RESULT" NCLOB ,
  "STATUS" NUMBER(11) ,
  "ERROR_MSG" NCLOB ,
  "OPER_TIME" DATE 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."OPER_ID" IS '日志主键';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."TITLE" IS '模块标题';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."BUSINESS_TYPE" IS '业务类型（0其它 1新增 2修改 3删除）';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."METHOD" IS '方法名称';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."REQUEST_METHOD" IS '请求方式';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."OPERATOR_TYPE" IS '操作类别（0其它 1后台用户 2手机端用户）';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."OPER_NAME" IS '操作人员';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."DEPT_NAME" IS '部门名称';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."OPER_URL" IS '请求URL';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."OPER_IP" IS '主机地址';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."OPER_LOCATION" IS '操作地点';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."OPER_PARAM" IS '请求参数';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."JSON_RESULT" IS '返回参数';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."STATUS" IS '操作状态（0正常 1异常）';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."ERROR_MSG" IS '错误消息';
COMMENT ON COLUMN "SVECENTER"."SYS_OPER_LOG"."OPER_TIME" IS '操作时间';
COMMENT ON TABLE "SVECENTER"."SYS_OPER_LOG" IS '操作日志记录';

-- ----------------------------
-- Records of SYS_OPER_LOG
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_OPER_LOG" VALUES ('106', '操作日志', '9', 'com.ruoyi.project.monitor.controller.SysOperlogController.clean()', 'DELETE', '1', 'admin', NULL, '/monitor/operlog/clean', '192.168.19.36', '内网IP', '{}', '{"msg":"操作成功","code":200}', '0', NULL, TO_DATE('2020-04-14 17:36:02', 'SYYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for SYS_POST
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_POST";
CREATE TABLE "SVECENTER"."SYS_POST" (
  "POST_ID" NUMBER(20) NOT NULL ,
  "POST_CODE" NVARCHAR2(64) NOT NULL ,
  "POST_NAME" NVARCHAR2(50) NOT NULL ,
  "POST_SORT" NUMBER(11) NOT NULL ,
  "STATUS" NCHAR(1) NOT NULL ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."POST_ID" IS '岗位ID';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."POST_CODE" IS '岗位编码';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."POST_NAME" IS '岗位名称';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."POST_SORT" IS '显示顺序';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."STATUS" IS '状态（0正常 1停用）';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_POST"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."SYS_POST" IS '岗位信息表';

-- ----------------------------
-- Records of SYS_POST
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_POST" VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO "SVECENTER"."SYS_POST" VALUES ('2', 'se', '项目经理', '2', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO "SVECENTER"."SYS_POST" VALUES ('3', 'hr', '人力资源', '3', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO "SVECENTER"."SYS_POST" VALUES ('4', 'user', '普通员工', '4', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), NULL);

-- ----------------------------
-- Table structure for SYS_RECYCLE_BIN
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_RECYCLE_BIN";
CREATE TABLE "SVECENTER"."SYS_RECYCLE_BIN" (
  "ID" NUMBER(11) NOT NULL ,
  "TABLE_NAME" NVARCHAR2(255) NOT NULL ,
  "DATA" NCLOB NOT NULL ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for SYS_ROLE
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_ROLE";
CREATE TABLE "SVECENTER"."SYS_ROLE" (
  "ROLE_ID" NUMBER(20) NOT NULL ,
  "ROLE_NAME" NVARCHAR2(30) NOT NULL ,
  "ROLE_KEY" NVARCHAR2(100) NOT NULL ,
  "ROLE_SORT" NUMBER(11) NOT NULL ,
  "DATA_SCOPE" NCHAR(1) ,
  "STATUS" NCHAR(1) NOT NULL ,
  "DEL_FLAG" NCHAR(1) ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."ROLE_ID" IS '角色ID';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."ROLE_NAME" IS '角色名称';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."ROLE_KEY" IS '角色权限字符串';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."ROLE_SORT" IS '显示顺序';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."DATA_SCOPE" IS '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."STATUS" IS '角色状态（0正常 1停用）';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."DEL_FLAG" IS '删除标志（0代表存在 2代表删除）';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."SYS_ROLE" IS '角色信息表';

-- ----------------------------
-- Records of SYS_ROLE
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_ROLE" VALUES ('1', '管理员', 'admin', '1', '1', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-04-08 16:04:45', 'SYYYY-MM-DD HH24:MI:SS'), '管理员');
INSERT INTO "SVECENTER"."SYS_ROLE" VALUES ('2', '普通角色', 'common', '2', '2', '0', '0', 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-04-23 17:26:59', 'SYYYY-MM-DD HH24:MI:SS'), '普通角色');

-- ----------------------------
-- Table structure for SYS_ROLE_DEPT
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_ROLE_DEPT";
CREATE TABLE "SVECENTER"."SYS_ROLE_DEPT" (
  "ROLE_ID" NUMBER(20) NOT NULL ,
  "DEPT_ID" NUMBER(20) NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE_DEPT"."ROLE_ID" IS '角色ID';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE_DEPT"."DEPT_ID" IS '部门ID';
COMMENT ON TABLE "SVECENTER"."SYS_ROLE_DEPT" IS '角色和部门关联表';

-- ----------------------------
-- Records of SYS_ROLE_DEPT
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_ROLE_DEPT" VALUES ('2', '100');
INSERT INTO "SVECENTER"."SYS_ROLE_DEPT" VALUES ('2', '101');
INSERT INTO "SVECENTER"."SYS_ROLE_DEPT" VALUES ('2', '105');

-- ----------------------------
-- Table structure for SYS_ROLE_MENU
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_ROLE_MENU";
CREATE TABLE "SVECENTER"."SYS_ROLE_MENU" (
  "ROLE_ID" NUMBER(20) NOT NULL ,
  "MENU_ID" NUMBER(20) NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE_MENU"."ROLE_ID" IS '角色ID';
COMMENT ON COLUMN "SVECENTER"."SYS_ROLE_MENU"."MENU_ID" IS '菜单ID';
COMMENT ON TABLE "SVECENTER"."SYS_ROLE_MENU" IS '角色和菜单关联表';

-- ----------------------------
-- Records of SYS_ROLE_MENU
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '3');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '100');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '101');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '102');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '103');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '104');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '105');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '106');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '107');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '108');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '109');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '110');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '111');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '112');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '113');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '114');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '115');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '500');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '501');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1001');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1002');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1003');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1004');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1005');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1006');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1007');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1008');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1009');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1010');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1011');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1012');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1013');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1014');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1015');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1016');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1017');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1018');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1019');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1020');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1021');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1022');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1023');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1024');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1025');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1026');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1027');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1028');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1029');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1030');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1031');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1032');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1033');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1034');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1035');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1036');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1037');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1038');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1039');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1040');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1041');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1042');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1043');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1044');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1045');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1046');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1047');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1048');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1049');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1050');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1051');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1052');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1053');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1054');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1055');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1056');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1057');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1058');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1059');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '1060');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2000');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2002');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2003');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2004');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2005');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2006');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2007');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2008');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2009');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2010');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2011');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2012');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('1', '2013');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '2');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '3');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '100');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '101');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '102');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '103');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '104');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '105');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '106');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '107');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '108');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '109');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '110');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '111');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '112');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '113');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '114');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '115');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '500');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '501');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1001');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1002');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1003');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1004');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1005');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1006');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1007');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1008');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1009');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1010');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1011');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1012');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1013');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1014');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1015');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1016');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1017');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1018');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1019');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1020');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1021');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1022');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1023');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1024');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1025');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1026');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1027');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1028');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1029');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1030');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1031');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1032');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1033');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1034');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1035');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1036');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1037');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1038');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1039');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1040');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1041');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1042');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1043');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1044');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1045');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1046');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1047');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1048');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1049');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1050');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1051');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1052');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1053');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1054');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1055');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1056');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1057');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1058');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1059');
INSERT INTO "SVECENTER"."SYS_ROLE_MENU" VALUES ('2', '1060');

-- ----------------------------
-- Table structure for SYS_USER
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_USER";
CREATE TABLE "SVECENTER"."SYS_USER" (
  "USER_ID" NUMBER(20) NOT NULL ,
  "DEPT_ID" NUMBER(20) ,
  "USER_NAME" NVARCHAR2(30) NOT NULL ,
  "NICK_NAME" NVARCHAR2(30) NOT NULL ,
  "USER_TYPE" NVARCHAR2(2) ,
  "EMAIL" NVARCHAR2(50) ,
  "PHONENUMBER" NVARCHAR2(11) ,
  "SEX" NCHAR(1) ,
  "AVATAR" NVARCHAR2(100) ,
  "PASSWORD" NVARCHAR2(100) ,
  "STATUS" NCHAR(1) ,
  "DEL_FLAG" NCHAR(1) ,
  "LOGIN_IP" NVARCHAR2(50) ,
  "LOGIN_DATE" DATE ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."USER_ID" IS '用户ID';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."DEPT_ID" IS '部门ID';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."USER_NAME" IS '用户账号';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."NICK_NAME" IS '用户昵称';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."USER_TYPE" IS '用户类型（00系统用户）';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."EMAIL" IS '用户邮箱';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."PHONENUMBER" IS '手机号码';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."SEX" IS '用户性别（0男 1女 2未知）';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."AVATAR" IS '头像地址';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."PASSWORD" IS '密码';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."STATUS" IS '帐号状态（0正常 1停用）';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."DEL_FLAG" IS '删除标志（0代表存在 2代表删除）';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."LOGIN_IP" IS '最后登陆IP';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."LOGIN_DATE" IS '最后登陆时间';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."SYS_USER"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."SYS_USER" IS '用户信息表';

-- ----------------------------
-- Records of SYS_USER
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_USER" VALUES ('1', '103', 'admin', '管理员', '00', '13266272935@163.com', '13266272935', '0', '/profile/avatar/2020/04/23/82f80f3cc6a9e1e96aea8c471990a1d3.jpeg', '$2a$10$GexDB6qwdVQP9xBbQ1JQb.4cGGSN.cOa0yyEnD/8yrYoXvCAu1o7G', '0', '0', '127.0.0.1', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2018-03-16 11:33:00', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2020-04-23 17:24:45', 'SYYYY-MM-DD HH24:MI:SS'), '管理员');
INSERT INTO "SVECENTER"."SYS_USER" VALUES ('18', '101', '李先生', 'lee', '00', 'mm@111.com', '13827222222', '0', NULL, '$2a$10$Yr5ZVbOTZvf13ZSpsNCJEuKKJXovQTxjch5sKnMtBefMP8co0gkP2', '0', '2', NULL, NULL, 'admin', TO_DATE('2020-04-23 16:29:30', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, '新用户');

-- ----------------------------
-- Table structure for SYS_USER_POST
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_USER_POST";
CREATE TABLE "SVECENTER"."SYS_USER_POST" (
  "USER_ID" NUMBER(20) NOT NULL ,
  "POST_ID" NUMBER(20) NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_USER_POST"."USER_ID" IS '用户ID';
COMMENT ON COLUMN "SVECENTER"."SYS_USER_POST"."POST_ID" IS '岗位ID';
COMMENT ON TABLE "SVECENTER"."SYS_USER_POST" IS '用户与岗位关联表';

-- ----------------------------
-- Records of SYS_USER_POST
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_USER_POST" VALUES ('1', '1');
INSERT INTO "SVECENTER"."SYS_USER_POST" VALUES ('2', '2');
INSERT INTO "SVECENTER"."SYS_USER_POST" VALUES ('18', '2');
INSERT INTO "SVECENTER"."SYS_USER_POST" VALUES ('100', '1');

-- ----------------------------
-- Table structure for SYS_USER_ROLE
-- ----------------------------
DROP TABLE "SVECENTER"."SYS_USER_ROLE";
CREATE TABLE "SVECENTER"."SYS_USER_ROLE" (
  "USER_ID" NUMBER(20) NOT NULL ,
  "ROLE_ID" NUMBER(20) NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."SYS_USER_ROLE"."USER_ID" IS '用户ID';
COMMENT ON COLUMN "SVECENTER"."SYS_USER_ROLE"."ROLE_ID" IS '角色ID';
COMMENT ON TABLE "SVECENTER"."SYS_USER_ROLE" IS '用户和角色关联表';

-- ----------------------------
-- Records of SYS_USER_ROLE
-- ----------------------------
INSERT INTO "SVECENTER"."SYS_USER_ROLE" VALUES ('1', '1');
INSERT INTO "SVECENTER"."SYS_USER_ROLE" VALUES ('2', '2');
INSERT INTO "SVECENTER"."SYS_USER_ROLE" VALUES ('18', '1');
INSERT INTO "SVECENTER"."SYS_USER_ROLE" VALUES ('100', '1');

-- ----------------------------
-- Table structure for TEST_EXIMPORT
-- ----------------------------
DROP TABLE "SVECENTER"."TEST_EXIMPORT";
CREATE TABLE "SVECENTER"."TEST_EXIMPORT" (
  "FIED1" NVARCHAR2(20) NOT NULL ,
  "FIED2" NUMBER(11) NOT NULL ,
  "FIED3" NVARCHAR2(100) NOT NULL ,
  "CREATE_TIME" DATE NOT NULL 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."TEST_EXIMPORT"."FIED1" IS '字段1';
COMMENT ON COLUMN "SVECENTER"."TEST_EXIMPORT"."FIED2" IS '字段2';
COMMENT ON COLUMN "SVECENTER"."TEST_EXIMPORT"."FIED3" IS '字段3';
COMMENT ON TABLE "SVECENTER"."TEST_EXIMPORT" IS 'Excel导入导出测试';

-- ----------------------------
-- Table structure for TEST_IP
-- ----------------------------
DROP TABLE "SVECENTER"."TEST_IP";
CREATE TABLE "SVECENTER"."TEST_IP" (
  "IP_ID" NUMBER(11) NOT NULL ,
  "IP_ADDR" NVARCHAR2(64) NOT NULL ,
  "IP_PASSWROD" NVARCHAR2(64) NOT NULL ,
  "CREATE_BY" NVARCHAR2(64) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" NVARCHAR2(64) ,
  "UPDATE_TIME" DATE ,
  "REMARK" NVARCHAR2(500) 
)
TABLESPACE "SVECENTER_DATA"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "SVECENTER"."TEST_IP"."IP_ID" IS 'ID';
COMMENT ON COLUMN "SVECENTER"."TEST_IP"."IP_ADDR" IS 'IP地址';
COMMENT ON COLUMN "SVECENTER"."TEST_IP"."IP_PASSWROD" IS 'IP密码';
COMMENT ON COLUMN "SVECENTER"."TEST_IP"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "SVECENTER"."TEST_IP"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SVECENTER"."TEST_IP"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "SVECENTER"."TEST_IP"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SVECENTER"."TEST_IP"."REMARK" IS '备注';
COMMENT ON TABLE "SVECENTER"."TEST_IP" IS '测试IP信息';

-- ----------------------------
-- Records of TEST_IP
-- ----------------------------
INSERT INTO "SVECENTER"."TEST_IP" VALUES ('1', '10.146.5.50', 'gf@TW.10', NULL, TO_DATE('2020-03-23 22:18:29', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 22:23:08', 'SYYYY-MM-DD HH24:MI:SS'), '固废服务器IP地址');
INSERT INTO "SVECENTER"."TEST_IP" VALUES ('2', '19.104.43.41', 'fsy@TW.19', NULL, TO_DATE('2020-03-23 22:18:47', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-03-23 22:23:41', 'SYYYY-MM-DD HH24:MI:SS'), '辐射源服务器IP地址');
INSERT INTO "SVECENTER"."TEST_IP" VALUES ('3', '192.168.10.213', 'DBsync2017', NULL, TO_DATE('2020-03-23 22:18:59', 'SYYYY-MM-DD HH24:MI:SS'), NULL, TO_DATE('2020-04-01 10:14:18', 'SYYYY-MM-DD HH24:MI:SS'), 'xxx');
INSERT INTO "SVECENTER"."TEST_IP" VALUES ('4', '192.168.19.10', 'abc123...', NULL, TO_DATE('2020-03-23 22:19:23', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."TEST_IP" VALUES ('5', '192.168.19.210', 'g8TZ6Ybi', NULL, TO_DATE('2020-03-23 22:19:34', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SVECENTER"."TEST_IP" VALUES ('6', '192.168.19.238', 'qaz123wsx', NULL, TO_DATE('2020-03-23 22:19:45', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);

-- ----------------------------
-- Function structure for FIND_IN_SET
-- ----------------------------
CREATE OR REPLACE
FUNCTION "SVECENTER"."FIND_IN_SET" AS
begin
select instr(','||arg2||',' , ','||arg1||',') into Result from dual;
return(Result);
end find_in_set;
/

-- ----------------------------
-- Sequence structure for SEQ_GEN_TABLE
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_GEN_TABLE";
CREATE SEQUENCE "SVECENTER"."SEQ_GEN_TABLE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_GEN_TABLE_COLUMN
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_GEN_TABLE_COLUMN";
CREATE SEQUENCE "SVECENTER"."SEQ_GEN_TABLE_COLUMN" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_CONFIG
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_CONFIG";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_CONFIG" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_DEPT
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_DEPT";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_DEPT" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_DICT_DATA
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_DICT_DATA";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_DICT_DATA" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_DICT_TYPE
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_DICT_TYPE";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_DICT_TYPE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_JOB
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_JOB";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_JOB" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_JOB_LOG
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_JOB_LOG";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_JOB_LOG" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_LOGININFOR
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_LOGININFOR";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_LOGININFOR" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_MENU
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_MENU";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_MENU" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_NOTICE
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_NOTICE";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_NOTICE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_OPER_LOG
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_OPER_LOG";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_OPER_LOG" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_POST
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_POST";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_POST" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_ROLE
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_ROLE";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_ROLE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_USER
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_SYS_USER";
CREATE SEQUENCE "SVECENTER"."SEQ_SYS_USER" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_TEST_IP
-- ----------------------------
DROP SEQUENCE "SVECENTER"."SEQ_TEST_IP";
CREATE SEQUENCE "SVECENTER"."SEQ_TEST_IP" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Primary Key structure for table GEN_TABLE
-- ----------------------------
ALTER TABLE "SVECENTER"."GEN_TABLE" ADD CONSTRAINT "SYS_C0011531" PRIMARY KEY ("TABLE_ID");

-- ----------------------------
-- Checks structure for table GEN_TABLE
-- ----------------------------
ALTER TABLE "SVECENTER"."GEN_TABLE" ADD CONSTRAINT "SYS_C0011432" CHECK ("TABLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."GEN_TABLE" ADD CONSTRAINT "SYS_C0016997" CHECK ("TABLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table GEN_TABLE_COLUMN
-- ----------------------------
ALTER TABLE "SVECENTER"."GEN_TABLE_COLUMN" ADD CONSTRAINT "SYS_C0011537" PRIMARY KEY ("COLUMN_ID");

-- ----------------------------
-- Checks structure for table GEN_TABLE_COLUMN
-- ----------------------------
ALTER TABLE "SVECENTER"."GEN_TABLE_COLUMN" ADD CONSTRAINT "SYS_C0011425" CHECK ("COLUMN_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."GEN_TABLE_COLUMN" ADD CONSTRAINT "SYS_C0016998" CHECK ("COLUMN_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_BLOB_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" ADD CONSTRAINT "SYS_C0011530" PRIMARY KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP");

-- ----------------------------
-- Checks structure for table QRTZ_BLOB_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" ADD CONSTRAINT "SYS_C0011427" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" ADD CONSTRAINT "SYS_C0011429" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" ADD CONSTRAINT "SYS_C0011431" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" ADD CONSTRAINT "SYS_C0016999" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" ADD CONSTRAINT "SYS_C0017000" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" ADD CONSTRAINT "SYS_C0017001" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_CALENDARS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_CALENDARS" ADD CONSTRAINT "SYS_C0011529" PRIMARY KEY ("SCHED_NAME", "CALENDAR_NAME");

-- ----------------------------
-- Checks structure for table QRTZ_CALENDARS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_CALENDARS" ADD CONSTRAINT "SYS_C0011426" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CALENDARS" ADD CONSTRAINT "SYS_C0011428" CHECK ("CALENDAR_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CALENDARS" ADD CONSTRAINT "SYS_C0011430" CHECK ("CALENDAR" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CALENDARS" ADD CONSTRAINT "SYS_C0017002" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CALENDARS" ADD CONSTRAINT "SYS_C0017003" CHECK ("CALENDAR_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CALENDARS" ADD CONSTRAINT "SYS_C0017004" CHECK ("CALENDAR" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_CRON_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0011532" PRIMARY KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP");

-- ----------------------------
-- Checks structure for table QRTZ_CRON_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0011433" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0011434" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0011435" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0011436" CHECK ("CRON_EXPRESSION" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0017005" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0017006" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0017007" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "SYS_C0017008" CHECK ("CRON_EXPRESSION" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_FIRED_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011533" PRIMARY KEY ("SCHED_NAME", "ENTRY_ID");

-- ----------------------------
-- Checks structure for table QRTZ_FIRED_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011437" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011438" CHECK ("ENTRY_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011439" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011440" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011441" CHECK ("INSTANCE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011442" CHECK ("FIRED_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011443" CHECK ("SCHED_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011444" CHECK ("PRIORITY" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0011445" CHECK ("STATE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017009" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017010" CHECK ("ENTRY_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017011" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017012" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017013" CHECK ("INSTANCE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017014" CHECK ("FIRED_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017015" CHECK ("SCHED_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017016" CHECK ("PRIORITY" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_FIRED_TRIGGERS" ADD CONSTRAINT "SYS_C0017017" CHECK ("STATE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_JOB_DETAILS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011534" PRIMARY KEY ("SCHED_NAME", "JOB_NAME", "JOB_GROUP");

-- ----------------------------
-- Checks structure for table QRTZ_JOB_DETAILS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011446" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011447" CHECK ("JOB_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011448" CHECK ("JOB_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011449" CHECK ("JOB_CLASS_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011450" CHECK ("IS_DURABLE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011451" CHECK ("IS_NONCONCURRENT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011452" CHECK ("IS_UPDATE_DATA" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0011453" CHECK ("REQUESTS_RECOVERY" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0017018" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0017019" CHECK ("JOB_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0017020" CHECK ("JOB_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0017021" CHECK ("JOB_CLASS_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0017022" CHECK ("IS_DURABLE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0017023" CHECK ("IS_NONCONCURRENT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0017024" CHECK ("IS_UPDATE_DATA" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_JOB_DETAILS" ADD CONSTRAINT "SYS_C0017025" CHECK ("REQUESTS_RECOVERY" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_LOCKS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_LOCKS" ADD CONSTRAINT "SYS_C0011535" PRIMARY KEY ("SCHED_NAME", "LOCK_NAME");

-- ----------------------------
-- Checks structure for table QRTZ_LOCKS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_LOCKS" ADD CONSTRAINT "SYS_C0011454" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_LOCKS" ADD CONSTRAINT "SYS_C0011455" CHECK ("LOCK_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_LOCKS" ADD CONSTRAINT "SYS_C0017026" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_LOCKS" ADD CONSTRAINT "SYS_C0017027" CHECK ("LOCK_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_PAUSED_TRIGGER_GRPS" ADD CONSTRAINT "SYS_C0011536" PRIMARY KEY ("SCHED_NAME", "TRIGGER_GROUP");

-- ----------------------------
-- Checks structure for table QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_PAUSED_TRIGGER_GRPS" ADD CONSTRAINT "SYS_C0011456" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_PAUSED_TRIGGER_GRPS" ADD CONSTRAINT "SYS_C0011457" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_PAUSED_TRIGGER_GRPS" ADD CONSTRAINT "SYS_C0017028" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_PAUSED_TRIGGER_GRPS" ADD CONSTRAINT "SYS_C0017029" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_SCHEDULER_STATE
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0011538" PRIMARY KEY ("SCHED_NAME", "INSTANCE_NAME");

-- ----------------------------
-- Checks structure for table QRTZ_SCHEDULER_STATE
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0011458" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0011459" CHECK ("INSTANCE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0011460" CHECK ("LAST_CHECKIN_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0011461" CHECK ("CHECKIN_INTERVAL" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0017030" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0017031" CHECK ("INSTANCE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0017032" CHECK ("LAST_CHECKIN_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SCHEDULER_STATE" ADD CONSTRAINT "SYS_C0017033" CHECK ("CHECKIN_INTERVAL" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0011539" PRIMARY KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP");

-- ----------------------------
-- Checks structure for table QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0011462" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0011463" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0011464" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0011465" CHECK ("REPEAT_COUNT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0011466" CHECK ("REPEAT_INTERVAL" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0011467" CHECK ("TIMES_TRIGGERED" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0017034" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0017035" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0017036" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0017037" CHECK ("REPEAT_COUNT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0017038" CHECK ("REPEAT_INTERVAL" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "SYS_C0017039" CHECK ("TIMES_TRIGGERED" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" ADD CONSTRAINT "SYS_C0011540" PRIMARY KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP");

-- ----------------------------
-- Checks structure for table QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" ADD CONSTRAINT "SYS_C0011468" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" ADD CONSTRAINT "SYS_C0011469" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" ADD CONSTRAINT "SYS_C0011470" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" ADD CONSTRAINT "SYS_C0017040" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" ADD CONSTRAINT "SYS_C0017041" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" ADD CONSTRAINT "SYS_C0017042" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table QRTZ_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011541" PRIMARY KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP");

-- ----------------------------
-- Checks structure for table QRTZ_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011471" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011472" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011473" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011474" CHECK ("JOB_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011475" CHECK ("JOB_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011476" CHECK ("TRIGGER_STATE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011477" CHECK ("TRIGGER_TYPE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0011478" CHECK ("START_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0017043" CHECK ("SCHED_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0017044" CHECK ("TRIGGER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0017045" CHECK ("TRIGGER_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0017046" CHECK ("JOB_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0017047" CHECK ("JOB_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0017048" CHECK ("TRIGGER_STATE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0017049" CHECK ("TRIGGER_TYPE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "SYS_C0017050" CHECK ("START_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table QRTZ_TRIGGERS
-- ----------------------------
CREATE INDEX "SVECENTER"."SCHED_NAME"
  ON "SVECENTER"."QRTZ_TRIGGERS" ("SCHED_NAME" ASC, "JOB_NAME" ASC, "JOB_GROUP" ASC)
  LOGGING
  TABLESPACE "SVECENTER_DATA"
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Primary Key structure for table SYS_CONFIG
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_CONFIG" ADD CONSTRAINT "SYS_C0011542" PRIMARY KEY ("CONFIG_ID");

-- ----------------------------
-- Checks structure for table SYS_CONFIG
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_CONFIG" ADD CONSTRAINT "SYS_C0011479" CHECK ("CONFIG_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_CONFIG" ADD CONSTRAINT "SYS_C0017051" CHECK ("CONFIG_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_DEPT
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_DEPT" ADD CONSTRAINT "SYS_C0011543" PRIMARY KEY ("DEPT_ID");

-- ----------------------------
-- Checks structure for table SYS_DEPT
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_DEPT" ADD CONSTRAINT "SYS_C0011480" CHECK ("DEPT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_DEPT" ADD CONSTRAINT "SYS_C0017052" CHECK ("DEPT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_DICT_DATA
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_DICT_DATA" ADD CONSTRAINT "SYS_C0011544" PRIMARY KEY ("DICT_CODE");

-- ----------------------------
-- Checks structure for table SYS_DICT_DATA
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_DICT_DATA" ADD CONSTRAINT "SYS_C0011481" CHECK ("DICT_CODE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_DICT_DATA" ADD CONSTRAINT "SYS_C0017053" CHECK ("DICT_CODE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_DICT_TYPE
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_DICT_TYPE" ADD CONSTRAINT "SYS_C0011545" PRIMARY KEY ("DICT_ID");

-- ----------------------------
-- Checks structure for table SYS_DICT_TYPE
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_DICT_TYPE" ADD CONSTRAINT "SYS_C0011482" CHECK ("DICT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_DICT_TYPE" ADD CONSTRAINT "SYS_C0017054" CHECK ("DICT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table SYS_DICT_TYPE
-- ----------------------------
CREATE UNIQUE INDEX "SVECENTER"."DICT_TYPE"
  ON "SVECENTER"."SYS_DICT_TYPE" ("DICT_TYPE" ASC)
  LOGGING
  TABLESPACE "SVECENTER_DATA"
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Primary Key structure for table SYS_JOB
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0011546" PRIMARY KEY ("JOB_ID", "JOB_NAME", "JOB_GROUP");

-- ----------------------------
-- Checks structure for table SYS_JOB
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0011483" CHECK ("JOB_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0011484" CHECK ("JOB_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0011485" CHECK ("JOB_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0011486" CHECK ("INVOKE_TARGET" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0017055" CHECK ("JOB_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0017056" CHECK ("JOB_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0017057" CHECK ("JOB_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB" ADD CONSTRAINT "SYS_C0017058" CHECK ("INVOKE_TARGET" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_JOB_LOG
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0011560" PRIMARY KEY ("JOB_LOG_ID");

-- ----------------------------
-- Checks structure for table SYS_JOB_LOG
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0011487" CHECK ("JOB_LOG_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0011488" CHECK ("JOB_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0011489" CHECK ("JOB_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0011490" CHECK ("INVOKE_TARGET" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0017059" CHECK ("JOB_LOG_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0017060" CHECK ("JOB_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0017061" CHECK ("JOB_GROUP" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_JOB_LOG" ADD CONSTRAINT "SYS_C0017062" CHECK ("INVOKE_TARGET" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_LOGININFOR
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_LOGININFOR" ADD CONSTRAINT "SYS_C0011547" PRIMARY KEY ("INFO_ID");

-- ----------------------------
-- Checks structure for table SYS_LOGININFOR
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_LOGININFOR" ADD CONSTRAINT "SYS_C0011491" CHECK ("INFO_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_LOGININFOR" ADD CONSTRAINT "SYS_C0017063" CHECK ("INFO_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_MENU
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_MENU" ADD CONSTRAINT "SYS_C0011551" PRIMARY KEY ("MENU_ID");

-- ----------------------------
-- Checks structure for table SYS_MENU
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_MENU" ADD CONSTRAINT "SYS_C0011492" CHECK ("MENU_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_MENU" ADD CONSTRAINT "SYS_C0011493" CHECK ("MENU_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_MENU" ADD CONSTRAINT "SYS_C0017064" CHECK ("MENU_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_MENU" ADD CONSTRAINT "SYS_C0017065" CHECK ("MENU_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_NOTICE
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_NOTICE" ADD CONSTRAINT "SYS_C0011548" PRIMARY KEY ("NOTICE_ID");

-- ----------------------------
-- Checks structure for table SYS_NOTICE
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_NOTICE" ADD CONSTRAINT "SYS_C0011494" CHECK ("NOTICE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_NOTICE" ADD CONSTRAINT "SYS_C0011495" CHECK ("NOTICE_TITLE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_NOTICE" ADD CONSTRAINT "SYS_C0011496" CHECK ("NOTICE_TYPE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_NOTICE" ADD CONSTRAINT "SYS_C0017066" CHECK ("NOTICE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_NOTICE" ADD CONSTRAINT "SYS_C0017067" CHECK ("NOTICE_TITLE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_NOTICE" ADD CONSTRAINT "SYS_C0017068" CHECK ("NOTICE_TYPE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_OPER_LOG
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_OPER_LOG" ADD CONSTRAINT "SYS_C0011554" PRIMARY KEY ("OPER_ID");

-- ----------------------------
-- Checks structure for table SYS_OPER_LOG
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_OPER_LOG" ADD CONSTRAINT "SYS_C0011497" CHECK ("OPER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_OPER_LOG" ADD CONSTRAINT "SYS_C0017069" CHECK ("OPER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_POST
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0011549" PRIMARY KEY ("POST_ID");

-- ----------------------------
-- Checks structure for table SYS_POST
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0011498" CHECK ("POST_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0011499" CHECK ("POST_CODE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0011500" CHECK ("POST_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0011501" CHECK ("POST_SORT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0011502" CHECK ("STATUS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0017070" CHECK ("POST_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0017071" CHECK ("POST_CODE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0017072" CHECK ("POST_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0017073" CHECK ("POST_SORT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_POST" ADD CONSTRAINT "SYS_C0017074" CHECK ("STATUS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_RECYCLE_BIN
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_RECYCLE_BIN" ADD CONSTRAINT "SYS_C0011550" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table SYS_RECYCLE_BIN
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_RECYCLE_BIN" ADD CONSTRAINT "SYS_C0011503" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_RECYCLE_BIN" ADD CONSTRAINT "SYS_C0011504" CHECK ("TABLE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_RECYCLE_BIN" ADD CONSTRAINT "SYS_C0011505" CHECK ("DATA" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_RECYCLE_BIN" ADD CONSTRAINT "SYS_C0017075" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_RECYCLE_BIN" ADD CONSTRAINT "SYS_C0017076" CHECK ("TABLE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_RECYCLE_BIN" ADD CONSTRAINT "SYS_C0017077" CHECK ("DATA" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_ROLE
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0011552" PRIMARY KEY ("ROLE_ID");

-- ----------------------------
-- Checks structure for table SYS_ROLE
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0011508" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0011509" CHECK ("ROLE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0011510" CHECK ("ROLE_KEY" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0011511" CHECK ("ROLE_SORT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0011512" CHECK ("STATUS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0017078" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0017079" CHECK ("ROLE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0017080" CHECK ("ROLE_KEY" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0017081" CHECK ("ROLE_SORT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE" ADD CONSTRAINT "SYS_C0017082" CHECK ("STATUS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_ROLE_DEPT
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_ROLE_DEPT" ADD CONSTRAINT "SYS_C0011553" PRIMARY KEY ("ROLE_ID", "DEPT_ID");

-- ----------------------------
-- Checks structure for table SYS_ROLE_DEPT
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_ROLE_DEPT" ADD CONSTRAINT "SYS_C0011506" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE_DEPT" ADD CONSTRAINT "SYS_C0011507" CHECK ("DEPT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE_DEPT" ADD CONSTRAINT "SYS_C0017083" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE_DEPT" ADD CONSTRAINT "SYS_C0017084" CHECK ("DEPT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_ROLE_MENU
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_ROLE_MENU" ADD CONSTRAINT "SYS_C0011557" PRIMARY KEY ("ROLE_ID", "MENU_ID");

-- ----------------------------
-- Checks structure for table SYS_ROLE_MENU
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_ROLE_MENU" ADD CONSTRAINT "SYS_C0011515" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE_MENU" ADD CONSTRAINT "SYS_C0011516" CHECK ("MENU_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE_MENU" ADD CONSTRAINT "SYS_C0017085" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_ROLE_MENU" ADD CONSTRAINT "SYS_C0017086" CHECK ("MENU_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_USER
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_USER" ADD CONSTRAINT "SYS_C0011559" PRIMARY KEY ("USER_ID");

-- ----------------------------
-- Checks structure for table SYS_USER
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_USER" ADD CONSTRAINT "SYS_C0011526" CHECK ("USER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER" ADD CONSTRAINT "SYS_C0011527" CHECK ("USER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER" ADD CONSTRAINT "SYS_C0011528" CHECK ("NICK_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER" ADD CONSTRAINT "SYS_C0017087" CHECK ("USER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER" ADD CONSTRAINT "SYS_C0017088" CHECK ("USER_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER" ADD CONSTRAINT "SYS_C0017089" CHECK ("NICK_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_USER_POST
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_USER_POST" ADD CONSTRAINT "SYS_C0011555" PRIMARY KEY ("USER_ID", "POST_ID");

-- ----------------------------
-- Checks structure for table SYS_USER_POST
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_USER_POST" ADD CONSTRAINT "SYS_C0011513" CHECK ("USER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER_POST" ADD CONSTRAINT "SYS_C0011514" CHECK ("POST_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER_POST" ADD CONSTRAINT "SYS_C0017090" CHECK ("USER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER_POST" ADD CONSTRAINT "SYS_C0017091" CHECK ("POST_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table SYS_USER_ROLE
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_USER_ROLE" ADD CONSTRAINT "SYS_C0011556" PRIMARY KEY ("USER_ID", "ROLE_ID");

-- ----------------------------
-- Checks structure for table SYS_USER_ROLE
-- ----------------------------
ALTER TABLE "SVECENTER"."SYS_USER_ROLE" ADD CONSTRAINT "SYS_C0011517" CHECK ("USER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER_ROLE" ADD CONSTRAINT "SYS_C0011518" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER_ROLE" ADD CONSTRAINT "SYS_C0017092" CHECK ("USER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."SYS_USER_ROLE" ADD CONSTRAINT "SYS_C0017093" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Checks structure for table TEST_EXIMPORT
-- ----------------------------
ALTER TABLE "SVECENTER"."TEST_EXIMPORT" ADD CONSTRAINT "SYS_C0011519" CHECK ("FIED1" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_EXIMPORT" ADD CONSTRAINT "SYS_C0011520" CHECK ("FIED2" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_EXIMPORT" ADD CONSTRAINT "SYS_C0011521" CHECK ("FIED3" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_EXIMPORT" ADD CONSTRAINT "SYS_C0011522" CHECK ("CREATE_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_EXIMPORT" ADD CONSTRAINT "SYS_C0017094" CHECK ("FIED1" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_EXIMPORT" ADD CONSTRAINT "SYS_C0017095" CHECK ("FIED2" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_EXIMPORT" ADD CONSTRAINT "SYS_C0017096" CHECK ("FIED3" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_EXIMPORT" ADD CONSTRAINT "SYS_C0017097" CHECK ("CREATE_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table TEST_IP
-- ----------------------------
ALTER TABLE "SVECENTER"."TEST_IP" ADD CONSTRAINT "SYS_C0011558" PRIMARY KEY ("IP_ID");

-- ----------------------------
-- Checks structure for table TEST_IP
-- ----------------------------
ALTER TABLE "SVECENTER"."TEST_IP" ADD CONSTRAINT "SYS_C0011523" CHECK ("IP_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_IP" ADD CONSTRAINT "SYS_C0011524" CHECK ("IP_ADDR" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_IP" ADD CONSTRAINT "SYS_C0011525" CHECK ("IP_PASSWROD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_IP" ADD CONSTRAINT "SYS_C0017098" CHECK ("IP_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_IP" ADD CONSTRAINT "SYS_C0017099" CHECK ("IP_ADDR" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "SVECENTER"."TEST_IP" ADD CONSTRAINT "SYS_C0017100" CHECK ("IP_PASSWROD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table QRTZ_BLOB_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_BLOB_TRIGGERS" ADD CONSTRAINT "QRTZ_BLOB_TRIGGERS_IBFK_1" FOREIGN KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") REFERENCES "SVECENTER"."QRTZ_TRIGGERS" ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table QRTZ_CRON_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_CRON_TRIGGERS" ADD CONSTRAINT "QRTZ_CRON_TRIGGERS_IBFK_1" FOREIGN KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") REFERENCES "SVECENTER"."QRTZ_TRIGGERS" ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_SIMPLE_TRIGGERS" ADD CONSTRAINT "QRTZ_SIMPLE_TRIGGERS_IBFK_1" FOREIGN KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") REFERENCES "SVECENTER"."QRTZ_TRIGGERS" ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_SIMPROP_TRIGGERS" ADD CONSTRAINT "QRTZ_SIMPROP_TRIGGERS_IBFK_1" FOREIGN KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") REFERENCES "SVECENTER"."QRTZ_TRIGGERS" ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table QRTZ_TRIGGERS
-- ----------------------------
ALTER TABLE "SVECENTER"."QRTZ_TRIGGERS" ADD CONSTRAINT "QRTZ_TRIGGERS_IBFK_1" FOREIGN KEY ("SCHED_NAME", "JOB_NAME", "JOB_GROUP") REFERENCES "SVECENTER"."QRTZ_JOB_DETAILS" ("SCHED_NAME", "JOB_NAME", "JOB_GROUP") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
