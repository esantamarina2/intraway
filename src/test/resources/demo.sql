--This script is used for unit test cases
DROP TABLE IF EXISTS User;

CREATE TABLE User (UserId LONG PRIMARY KEY AUTO_INCREMENT NOT NULL, UserName VARCHAR(30) NOT NULL, EmailAddress VARCHAR(30) NOT NULL);

INSERT INTO User (UserName, EmailAddress) VALUES ('esantamarina','esteban.santamarina2@gmail.com');
INSERT INTO User (UserName, EmailAddress) VALUES ('intraway','userTesting@intraway.com');

