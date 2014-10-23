create table logging (
  id int(11) NOT NULL AUTO_INCREMENT,
  eventDate datetime NOT NULL,
  level varchar(10) DEFAULT NULL,
  logger varchar(100) DEFAULT NULL,
  message text DEFAULT NULL,
  exception text DEFAULT NULL,
  PRIMARY KEY (id)
)