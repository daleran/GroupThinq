CREATE TABLE USERS (
USERNAME VARCHAR PRIMARY KEY NOT NULL,
LASTNAME VARCHAR NOT NULL,
FIRSTNAME VARCHAR NOT NULL,
EMAIL VARCHAR NOT NULL );

INSERT INTO USERS
VALUES
('jsmith', 'John', 'Smith', 'jsmith@foo.com');
