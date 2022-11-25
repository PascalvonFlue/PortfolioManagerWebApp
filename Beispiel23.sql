-- Die erste Tabelle, etwas besser
-- -------------------------------

DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
  personid		int,			  -- PRIMARY KEY,
  vorname		varchar(32),
  nachname		varchar(64),
  geburtstag	date,
  CONSTRAINT PK_Person	PRIMARY KEY (personid)
);

INSERT INTO Person
  VALUES (1,'Hans','Muster','1980-03-25');
INSERT INTO Person (personid,nachname, vorname)
  VALUES (2,'Kaufmann','Peter');

SELECT * FROM person;