DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
    `USER_ID` BIGINT NOT NULL,
    `FIRSTNAME` VARCHAR(255) NOT NULL,
    `LASTNAME` VARCHAR(255) NOT NULL,
    `EMAIL` VARCHAR(255) NOT NULL,
    `USERNAME` VARCHAR(255) NOT NULL,
    `PASSWORD` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`USER_ID`),
    UNIQUE KEY `UNIQUE_USER_USERNAME` (`USERNAME`)
);