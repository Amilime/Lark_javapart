# 删除和查看
SHOW TABLES;
DROP TABLE IF EXISTS note;

# 用户表
CREATE TABLE sys_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    role VARCHAR(20) DEFAULT 'USER',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
插入测试用户
INSERT INTO sys_user (username, password, nickname, role)
VALUES ('admin', '123456', 'Admin', 'ADMIN');
# 文件表(note)
0是静态查看文件 1是可同步编辑文件
CREATE TABLE document (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    doc_type TINYINT NOT NULL DEFAULT 0,
    file_key VARCHAR(255),
    owner_id BIGINT NOT NULL,
    version INT DEFAULT 1,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
# 版本表
content_snapshot是快照内容
CREATE TABLE doc_version (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
doc_id BIGINT NOT NULL,
version_num INT NOT NULL,
content_snapshot LONGTEXT,  
editor_id BIGINT,
create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

这里用了三个表