CREATE SCHEMA IF NOT EXISTS CONTENTS;

CREATE TABLE IF NOT EXISTS CONTENTS.CATEGRORY(
	ID BIGSERIAL PRIMARY KEY,
	NAME varchar(255) NULL,
    CREATED_BY  VARCHAR(50),
    CREATED_DATETIME    TIMESTAMP WITH TIME ZONE,
    UPDATED_BY  VARCHAR(50),
    UPDATED_DATETIME    TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS CONTENTS.POST(
	ID BIGSERIAL PRIMARY KEY,
	NAME varchar(255) NULL,
    CATEGRORY_ID BIGINT,
    PARAGRAPHS TEXT[],
    CREATED_BY  VARCHAR(50),
    CREATED_DATETIME    TIMESTAMP WITH TIME ZONE,
    UPDATED_BY  VARCHAR(50),
    UPDATED_DATETIME    TIMESTAMP WITH TIME ZONE,
	FOREIGN KEY (CATEGRORY_ID) REFERENCES CONTENTS.CATEGRORY(ID)
);

ALTER TABLE USERS.PRIVILEGE ADD COLUMN RESOURCE_ID INT;
CREATE TABLE IF NOT EXISTS USERS.RESOURCE(
    ID BIGSERIAL PRIMARY KEY,
    CODE VARCHAR(150),
    NAME VARCHAR(150),
    CLIENT_ID  VARCHAR(50)
);

ALTER TABLE USERS.PRIVILEGE DROP CONSTRAINT IF EXISTS USERS_PRIVILEGE_ID;
ALTER TABLE USERS.PRIVILEGE ADD CONSTRAINT USERS_PRIVILEGE_ID
FOREIGN KEY (RESOURCE_ID) REFERENCES USERS.RESOURCE (ID);

CREATE TABLE IF NOT EXISTS PRODUCTS.ORDER(
	ID BIGSERIAL PRIMARY KEY,
	NAME varchar(255) NULL,
    PRODUCT_ID INT,
    CREATED_BY  VARCHAR(50),
    CREATED_DATETIME    TIMESTAMP WITH TIME ZONE,
    UPDATED_BY  VARCHAR(50),
    UPDATED_DATETIME    TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS.PRODUCT(ID)
);