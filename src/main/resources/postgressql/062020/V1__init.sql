CREATE SCHEMA IF NOT EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS.ROLE(
	ID						BIGSERIAL PRIMARY KEY,
	name					VARCHAR(100),
    CREATED_BY              VARCHAR(50),
    CREATED_DATETIME        TIMESTAMP WITH TIME ZONE,
    UPDATED_BY              VARCHAR(50),
    UPDATED_DATETIME        TIMESTAMP WITH TIME ZONE
);


CREATE TABLE IF NOT EXISTS USERS.USER(
	ID						BIGSERIAL PRIMARY KEY,
	USERNAME					VARCHAR(100),
	PASSWORD					VARCHAR(100),
    CREATED_BY              VARCHAR(50),
    CREATED_DATETIME        TIMESTAMP WITH TIME ZONE,
    UPDATED_BY              VARCHAR(50),
    UPDATED_DATETIME        TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS USERS.USER_ROLE(
	ID						BIGSERIAL PRIMARY KEY,
	USER_ID INT,
	ROLE_ID INT
);

ALTER TABLE USERS.USER_ROLE
DROP CONSTRAINT IF EXISTS USER_ID_USER_ROLE_USER_ID;

ALTER TABLE USERS.USER_ROLE
DROP CONSTRAINT IF EXISTS ROLE_ROLE_ID_USER_ROLE_ROLE_ID;

ALTER TABLE USERS.USER_ROLE ADD CONSTRAINT USER_ID_USER_ROLE_USER_ID
FOREIGN KEY (USER_ID) REFERENCES USERS.USER (ID);


ALTER TABLE USERS.USER_ROLE ADD CONSTRAINT ROLE_ROLE_ID_USER_ROLE_ROLE_ID
FOREIGN KEY (ROLE_ID) REFERENCES USERS.ROLE (ID);

ALTER TABLE users.role ADD CONSTRAINT role_name_unique UNIQUE (name);

----- INSERT TABLE EMPLOYEE------
CREATE SCHEMA IF NOT EXISTS ENTERPRISE;

CREATE TABLE IF NOT EXISTS ENTERPRISE.COMPANY(
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR(100),
    ADDRESS VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS ENTERPRISE.EMPLOYEE(
    ID BIGSERIAL PRIMARY KEY,
    COMPANY_ID INT, NAME VARCHAR(200)
);

ALTER TABLE ENTERPRISE.COMPANY
DROP CONSTRAINT IF EXISTS COMPANY_ID_COMPANY;

ALTER TABLE ENTERPRISE.EMPLOYEE ADD CONSTRAINT COMPANY_ID_COMPANY
FOREIGN KEY (COMPANY_ID) REFERENCES ENTERPRISE.COMPANY (ID);