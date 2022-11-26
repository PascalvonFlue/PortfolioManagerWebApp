-- Beispiel mit FOREIGN KEY
-- ------------------------

DROP TABLE IF EXISTS Benutzer;
DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
  personid		serial,
  nachname		varchar(64),
  vorname		varchar(32),
  geburtsdatum	date		DEFAULT current_date,
  CONSTRAINT PK_Person		PRIMARY KEY (personid)
);

CREATE TABLE Benutzer (
  benutzerid	serial,
  username		varchar(16),
  password		varchar(128),	-- {plain}geheim  {md5}falsch {sha512}falsch {crypt}$5$salts$hash
  uid			int,
  email			varchar(128),
  personid		int,
  CONSTRAINT PK_Benutzer		PRIMARY KEY (benutzerid),
  CONSTRAINT FK_Benutzer_Person	FOREIGN KEY (personid)	  REFERENCES Person (personid)
--  CONSTRAINT FK_Benutzer_Person	FOREIGN KEY (personid)	  REFERENCES Person (personid) ON DELETE CASCADE
--  CONSTRAINT FK_Benutzer_Person	FOREIGN KEY (personid)	  REFERENCES Person (personid) ON DELETE SET NULL
);

INSERT INTO Person (nachname,vorname)
  VALUES ('Muster','Hans');
INSERT INTO Benutzer (username,personid)
  VALUES ('mh',currval('person_personid_seq'));
INSERT INTO Person (nachname,vorname)
  VALUES ('Kaufmann','Urs');
INSERT INTO Benutzer (username,personid)
  VALUES ('ku',currval('person_personid_seq'));

-- DELETE FROM Person WHERE nachname='Muster';

SELECT * FROM Person;
SELECT * FROM Benutzer;