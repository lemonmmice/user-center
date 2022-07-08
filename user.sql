create table user
(
    username     varchar(256)                       null comment '用户昵称',
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0                 not null comment '是否删除',
    userRole     int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    planetCode   varchar(512)                       null comment '星球编号'
)
    comment '用户';

INSERT INTO cat.user (username, id, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES ('jake', 1, '577822755', 'lemon', 1, '123456789', '187545478541', '577822755@qq.com', 0, '2022-07-05 13:25:49', '2022-07-05 13:25:56', 0, 1, null);
INSERT INTO cat.user (username, id, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES (null, 2, 'yupi', null, null, '12345678', null, null, 0, '2022-07-05 15:41:43', '2022-07-05 15:42:11', 0, 1, null);
INSERT INTO cat.user (username, id, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES (null, 3, 'dogYupi', null, null, '1b46a17106b85d6b230e9f7f9d90a819', null, null, 0, '2022-07-05 19:50:15', '2022-07-05 19:50:15', 0, 0, '8');