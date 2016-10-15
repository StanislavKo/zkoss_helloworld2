CREATE DATABASE `zkoss01`    CHARACTER SET 'utf8'    COLLATE 'utf8_general_ci';
CREATE USER 'zkoss01'@'localhost' IDENTIFIED BY 'zkoss01';
GRANT ALL PRIVILEGES ON zkoss01.* TO 'zkoss01'@'localhost' IDENTIFIED BY 'zkoss01';
