CREATE TABLE `BANNER` (
  `ID` BIGINT(20) NOT NULL,
  `UUID` VARCHAR(255) NOT NULL,
  `POSITION` VARCHAR(255) NOT NULL,
  `CONTENT` LONGTEXT NOT NULL,
  PRIMARY KEY (`BANNER_ID`),
  UNIQUE INDEX `UUID` (`UUID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;