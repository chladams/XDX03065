CREATE database Game2048;
use Game2048;
CREATE TABLE rank
(
  secore int,
  playername varchar(20),
  PRIMARY KEY (secore)
)ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE PROCEDURE find_all()  SELECT * FROM rank;
call find_all()

