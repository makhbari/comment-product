DROP TABLE IF EXISTS COMMENT;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE USER
(
    ID            INT AUTO_INCREMENT PRIMARY KEY,
    NAME          VARCHAR(250) NOT NULL,
    USERNAME      VARCHAR(250),
    PASSWORD      VARCHAR(250),
    MOBILE_NUMBER VARCHAR(250),
    EMAIL         VARCHAR(250),
    ROLE          VARCHAR(250) NOT NULL,
    CREATED_DATE  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_DATE  TIMESTAMP

);

CREATE TABLE PRODUCT
(
    ID               INT AUTO_INCREMENT PRIMARY KEY,
    CODE             VARCHAR(250) NOT NULL UNIQUE,
    TITLE            VARCHAR(250) NOT NULL,
    TYPE             VARCHAR(250) NOT NULL,
    DESCRIPTION      VARCHAR(250),
    ENABLE           TINYINT(1),
    VISITABLE        TINYINT(1),
    REGISTER_COMMENT TINYINT(1),
    REGISTER_VOTE    TINYINT(1),
    CREATED_DATE     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_DATE     TIMESTAMP
);

CREATE TABLE COMMENT
(
    ID           INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID      INT          NOT NULL,
    PRODUCT_ID   INT          NOT NULL,
    COMMENT      VARCHAR(250),
    VOTE         VARCHAR(250),
    STATUS       VARCHAR(250) NOT NULL,
    CREATED_DATE TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_DATE TIMESTAMP,
    CONSTRAINT FK_COMMENT_USER FOREIGN KEY (USER_ID) REFERENCES USER (ID),
    CONSTRAINT FK_COMMENT_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID)
);

CREATE UNIQUE INDEX UK_COMMENT_1 ON COMMENT (USER_ID, PRODUCT_ID);