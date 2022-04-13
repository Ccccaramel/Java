#创建一个数据库
#create database muke;

show tables;

#用户个人信息表
CREATE TABLE user_basic_information(
                                       userName varchar(20),
                                       userId int NOT NULL AUTO_INCREMENT,
                                       userEmail char(20) UNIQUE,
                                       userTel char(20) UNIQUE,
                                       userOldPassword char(100),
                                       userNewPassword char(100),
                                       userSex char(4) CHARACTER SET "utf8",
                                       userBirth char(10),
                                       userAccountStatus int default 1,
                                       PRIMARY KEY(userId)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
#alter table user AUTO_INCREMENT=100;#修改自增列起始值
#意见箱
CREATE TABLE suggestions(
                            ideaId int NOT NULL AUTO_INCREMENT,
                            userId int,
                            ideaContent varchar(100),
                            sendingDate timestamp NULL default CURRENT_TIMESTAMP,
                            PRIMARY KEY(ideaId)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

#课程笔记
CREATE TABLE note(
                     courseId int,
                     userId int,
                     userType int,
                     note varchar(100),
                     sendingDate timestamp NULL default CURRENT_TIMESTAMP
)ENGINE=InnoDB CHARSET=utf8;

#教师个人信息
CREATE TABLE teacher_basic_information(
                                          teacherName varchar(20),
                                          teacherId int NOT NULL AUTO_INCREMENT,
                                          teachersSchool varchar(20),
                                          teacherIDcard char(30) UNIQUE,
                                          teacherTel char(30) UNIQUE,
                                          teacherEmail char(20) UNIQUE,
                                          teacherQualification int UNIQUE,
                                          teacherOldPassword char(100),
                                          teacherNewPassword char(100),
                                          teacherAccountStatus int default 0,
                                          teachersSchoolEmail char(20),
                                          PRIMARY KEY(teacherId)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

#课程
CREATE TABLE course(
                       courseName char(50),
                       courseId int NOT NULL AUTO_INCREMENT,
                       courseImgName char(100) UNIQUE,
                       courseClass int,
                       teacherId int,
                       courseStatus int default 1,
                       heat int default 0,
                       PRIMARY KEY(courseId),
                       foreign key(teacherId) references teacher_basic_information(teacherId)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
#删除唯一约束---表已修改不用添加
#ALTER TABLE course DROP INDEX courseName;
#添加热度属性---表已修改不用添加
#alter table course add heat int default 0;

#课程简介
CREATE TABLE course_abstract(
                                courseId int NOT NULL,
                                courseSection int,
                                courseIntroduce varchar(400),
                                foreign key(courseId) references course(courseId)
)ENGINE=InnoDB CHARSET=utf8;
#添加联合唯一索引，否则 更新+插入 无法执行。执行sql语句时，可能会报错：Incorrect index name 'courseId'，改变列名顺序再执行一次
ALTER TABLE `course_abstract` ADD UNIQUE(courseId,courseSection);

#课程结构
CREATE TABLE course_structure(
                                 courseId int,
                                 chapterId int,
                                 chapterName varchar(30),
                                 sectionId int,
                                 sectionName varchar(30),
                                 mvAdd varchar(100),
                                 foreign key(courseId) references course(courseId)
)ENGINE=InnoDB CHARSET=utf8;
#添加联合唯一约束，每个课程的每章的每小节都是唯一的。若sql语句执行不成功改变列名顺序再执行一次
ALTER TABLE course_structure ADD UNIQUE KEY(courseId, chapterId,sectionId);
#修改列名---表已修改，此句不用执行
#alter table course_structure change column sectionName sectionName varchar(30);

#测试卷
CREATE TABLE item_back(
                          testId int NOT NULL AUTO_INCREMENT,
                          courseId int,
                          testName varchar(100),
                          testStatus int default 1,
                          totalScore int,
                          passLine int,
                          teacherId int,
                          PRIMARY KEY(testId),
                          foreign key(courseId) references course(courseId),
                          foreign key(teacherId) references teacher_basic_information(teacherId)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

#试卷题库
CREATE TABLE test_question(
                              testId int,
                              questionId int NOT NULL AUTO_INCREMENT,
                              questionNumber int,
                              questionContent varchar(400),
                              rightKey char(4),
                              analysis varchar(400),
                              score int,
                              PRIMARY KEY(questionId),
                              foreign key(testId) references item_back(testId)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
ALTER TABLE test_question ADD UNIQUE KEY(testId, questionNumber);#若sql语句执行不成功改变列名顺序再执行一次

#测试卷选项
CREATE TABLE test_options(
                             questionId int,
                             questionOption int,
                             optionContent varchar(200),
                             foreign key(questionId) references test_question(questionId)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
ALTER TABLE test_options ADD UNIQUE KEY(questionId,questionOption);#若sql语句执行不成功改变列名顺序再执行一次
ALTER TABLE test_options ADD UNIQUE index agdkey(questionOption,questionId);
alter table test_options add constraint FK_ID foreign key(questionId) REFERENCES test_question(questionId);

#用户答卷
CREATE TABLE user_results(
                             answerSheetId varchar(50),
                             userId int,
                             questionId int,
                             userKey char(4),
                             userScore int,
                             foreign key(userId) references user_basic_information(userId),
                             foreign key(questionId) references test_question(questionId)
)ENGINE=InnoDB CHARSET=utf8;
#alter table user_results drop primary key;

#用户成绩
CREATE TABLE user_achievement(
                                 userId int,
                                 answerSheetId varchar(50),
                                 courseId int,
                                 testId int,
                                 userAchievement int,
                                 submitTestDate timestamp NULL default CURRENT_TIMESTAMP,
                                 effectiveness int,
                                 totalScore int,
                                 pass int,
                                 foreign key(userId) references user_basic_information(userId),
                                 foreign key(courseId) references course(courseId),
                                 foreign key(testId) references item_back(testId)
)ENGINE=InnoDB CHARSET=utf8;

#用户收藏课程
CREATE TABLE favorite(
                         userId int,
                         courseId int,
                         foreign key(userId) references user_basic_information(userId),
                         foreign key(courseId) references course(courseId)
)ENGINE=InnoDB CHARSET=utf8;
ALTER TABLE favorite ADD UNIQUE KEY(courseId, userId);#不可全为外键，在xampp中有效但在phpstudy中不可以，改用下面sql语句即可
ALTER TABLE favorite ADD CONSTRAINT uc_PersonID UNIQUE(courseId, userId);

#管理员个人信息表
CREATE TABLE administrator(
                              gmId int NOT NULL AUTO_INCREMENT,
                              gmEmail char(20) UNIQUE,
                              gmTel char(20) UNIQUE,
                              gmPassword char(100),
                              gmPower int  default 0,
                              gmAccountStatus int default 1,
                              PRIMARY KEY(gmId)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
#删除唯一属性---不用删除，作为登陆方式之一需要唯一性
#ALTER TABLE administrator DROP INDEX gmEmail;
#ALTER TABLE administrator DROP INDEX gmTel;

#管理员操作记录---此表已弃用
CREATE TABLE operation_note(
                               gmId int,
                               operationObjectivesId int,
                               operationObjectivesName varchar(50),
                               processingMode varchar(10),
                               operationDate char(10),
                               foreign key(gmId) references administrator(gmId)
)ENGINE=InnoDB CHARSET=utf8;

#账号状态字典表
CREATE TABLE account_status(
                               accountNumber int,
                               accountClass varchar(20)
)ENGINE=InnoDB CHARSET=utf8;

#管理员权限字典表
CREATE TABLE gm_power(
                         powerNumber int,
                         powerClass varchar(20)
)ENGINE=InnoDB CHARSET=utf8;

#课程资源 teacherId>userId
CREATE TABLE curriculum_resource(
                                    resourceId int NOT NULL AUTO_INCREMENT,
                                    resourceName varchar(100),
                                    courseId int,
                                    linkAdd varchar(100),
                                    resourceAccount int default 1,
                                    teacherId int,
                                    userType int default 1,
                                    PRIMARY KEY(resourceId)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
#alter table curriculum_resource add userType int default 1;    已加入表中

#资源状态字典表
CREATE TABLE resource_account(
                                 accountNumber int,
                                 accountClass varchar(20)
)ENGINE=InnoDB CHARSET=utf8;

#课程类型字典表
CREATE TABLE course_type(
                            typeValue int NOT NULL AUTO_INCREMENT,
                            typeName varchar(20),
                            PRIMARY KEY(typeValue)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

#测试评价字典表
CREATE TABLE evaluate(
                         typeValue int NOT NULL AUTO_INCREMENT,
                         typeName varchar(20),
                         PRIMARY KEY(typeValue)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

#有效性检查字典表
CREATE TABLE effectivenss_check(
                                   typeValue int NOT NULL AUTO_INCREMENT,
                                   typeName varchar(20),
                                   PRIMARY KEY(typeValue)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

#用户类型字典表
CREATE TABLE user_type(
                          typeValue int,
                          typeName varchar(20),
                          PRIMARY KEY(typeValue)
)ENGINE=InnoDB CHARSET=utf8;

#课程推广字典表
CREATE TABLE course_promotion(
    courseId int UNIQUE
)ENGINE=InnoDB CHARSET=utf8;
##################################################3
#设置数据库编码格式
#mysqli_query($conn,"set names utf8");

#完善账号状态字典表
INSERT INTO `account_status`(`accountNumber`, `accountClass`) VALUES ('0','待审核');
INSERT INTO `account_status`(`accountNumber`, `accountClass`) VALUES ('1','正常');
INSERT INTO `account_status`(`accountNumber`, `accountClass`) VALUES ('2','已冻结');
INSERT INTO `account_status`(`accountNumber`, `accountClass`) VALUES ('3','已注销');
INSERT INTO `account_status`(`accountNumber`, `accountClass`) VALUES ('4','待激活');

#完善管理员权限字典表
INSERT INTO `gm_power`(`powerNumber`, `powerClass`) VALUES ('0','基层管理员');
INSERT INTO `gm_power`(`powerNumber`, `powerClass`) VALUES ('1','中层管理员');
INSERT INTO `gm_power`(`powerNumber`, `powerClass`) VALUES ('2','高层管理员');

#完善资源状态字典表
INSERT INTO `resource_account`(`accountNumber`, `accountClass`) VALUES ('1','正常');
INSERT INTO `resource_account`(`accountNumber`, `accountClass`) VALUES ('2','已冻结');
INSERT INTO `resource_account`(`accountNumber`, `accountClass`) VALUES ('3','已删除');

#完善用户类型字典表
INSERT INTO `user_type`(`typeValue`, `typeName`) VALUES ('0','普通用户');
INSERT INTO `user_type`(`typeValue`, `typeName`) VALUES ('1','教师');

#完善测试评价字典表
INSERT INTO `evaluate`(`typeName`) VALUES ('不及格');
INSERT INTO `evaluate`(`typeName`) VALUES ('及格');

#完善有效性检查字典表
INSERT INTO `effectivenss_check`(`typeName`) VALUES ('无效');
INSERT INTO `effectivenss_check`(`typeName`) VALUES ('有效');

#添加管理员
INSERT INTO `administrator`(`gmEmail`, `gmTel`, `gmPassword`, `gmPower`) VALUES ('3064070081@qq.com','17720796145',md5('123123'),'2');
INSERT INTO `administrator`(`gmEmail`, `gmTel`, `gmPassword`, `gmPower`) VALUES ('19960508@qq.com','7640560',md5('123123'),'1');
INSERT INTO `administrator`(`gmEmail`, `gmTel`, `gmPassword`, `gmPower`) VALUES ('11223344@qq.com','13478798080',md5('123123'),'0');

#联合约束
ALTER TABLE course_structure ADD UNIQUE KEY(courseId, chapterId,sectionId);
# alter table course_structure drop index courseId_2;
# alter table course_structure drop index courseId_3;


-- 修改表名， TO 或AS都可以，也以省略掉
-- ALTER TABLE 表名 RENAME [TO|AS] 新表名
#ALTER TABLE user10 RENAME TO user11;

#多表连接查询---查询的列要加上重新命名的表名!
#$result = mysqli_query($GMconn, "SELECT t1.testId,t1.courseId,t1.testName,t1.testStatus,t1.teacherId,t2.teacherName from item_back t1 LEFT JOIN teacher_basic_information t2 ON t1.teacherId=t2.teacherId");







