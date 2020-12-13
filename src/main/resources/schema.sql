CREATE TABLE USERS (
                      USERNAME VARCHAR(50) NOT NULL,
                      PASSWORD VARCHAR(50) NOT NULL,
                      ENABLED BOOLEAN NOT NULL DEFAULT true,
                      PRIMARY KEY(USERNAME)
);

CREATE TABLE AUTHORITIES (
                            USERNAME VARCHAR(50) NOT NULL,
                            AUTHORITY VARCHAR(50) NOT NULL,
                            FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME)
);

CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES(USERNAME);

INSERT INTO USERS (USERNAME, PASSWORD) VALUES ( 'user', '{noop}pass' );
INSERT INTO USERS (USERNAME, PASSWORD) VALUES ( 'admin', '{noop}pass' );

INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ( 'user', 'USER' );
-- INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ( 'admin', 'USER' );
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ( 'admin', 'ADMIN' );

CREATE TABLE SESSIONS (
    SESSION_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DESCRIPTION VARCHAR(200) NOT NULL
);

CREATE TABLE SPEAKERS (
    SPEAKER_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FIRST_NAME VARCHAR(200) NOT NULL,
    LAST_NAME VARCHAR(200) NOT NULL
);