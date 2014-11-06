SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

CREATE
TABLE dict(
	id int(11) NOT NULL auto_increment,
	target int(11) NOT NULL,
	resp int(11) NOT NULL,
	PRIMARY KEY  (id),
	KEY target (target)
)

ENGINE=MyISAM 
DEFAULT CHARSET=utf8;

CREATE TABLE words(
  id int(11) NOT NULL auto_increment,
  word text NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY PurumPumPum (word(20))
)

ENGINE=MyISAM
DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;