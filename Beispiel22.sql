-- Unsere erste Tabelle
-- --------------------

DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
  vorname		varchar(32),
  nachname		varchar(64),
  geburtstag	date
);

INSERT INTO Person
  VALUES ('Hans','Muster','1980-03-25');
INSERT INTO Person (nachname, vorname)
  VALUES ('Kaufmann','Peter');

SELECT * FROM person;