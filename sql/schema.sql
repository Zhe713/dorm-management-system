-- 宿舍管理系统建表脚本

CREATE DATABASE IF NOT EXISTS dorm_db DEFAULT CHARSET utf8mb4;
USE dorm_db;

-- 管理员
CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

-- 宿舍
CREATE TABLE dorm (
    id INT PRIMARY KEY AUTO_INCREMENT,
    building VARCHAR(20) NOT NULL,
    room_no VARCHAR(20) NOT NULL,
    capacity INT NOT NULL,
    occupied INT DEFAULT 0
);

-- 学生
CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sno VARCHAR(20) NOT NULL UNIQUE,   -- 学号
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(4),
    dorm_id INT,
    FOREIGN KEY (dorm_id) REFERENCES dorm(id)
);

-- 默认管理员账号 admin / 123456
INSERT INTO admin(username, password) VALUES ('admin', '123456');
