-- Die erste Tabelle, schon brauchbarer
-- ------------------------------------

DROP TABLE IF EXISTS Person;

CREATE SEQUENCE S_Person AS integer;
CREATE TABLE Person (
  personid		int			  DEFAULT nextval('S_Person'),
  vorname		varchar(32),
  nachname		varchar(64),
  geburtstag	date,
  CONSTRAINT PK_Person	PRIMARY KEY (personid)
);
ALTER SEQUENCE S_Person OWNED BY Person.personid;

INSERT INTO Person
  VALUES (nextval('S_Person'),'Hans','Muster','1980-03-25');
INSERT INTO Person (nachname, vorname)
  VALUES ('Kaufmann','Peter');

SELECT * FROM person;