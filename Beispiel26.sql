-- Beispiel mit NOT NULL
-- ---------------------

DROP TABLE IF EXISTS Benutzer;
DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
  personid		serial,
  nachname		varchar(64)	 NOT NULL,
  vorname		varchar(32),
  geburtsdatum	date		DEFAULT current_date,
  CONSTRAINT PK_Person		PRIMARY KEY (personid)
);

CREATE TABLE Benutzer (
  benutzerid	serial,
  username		varchar(16)	  NOT NULL,
  password		varchar(128)  NOT NULL,	-- {plain}geheim  {md5}falsch {sha512}falsch {crypt}$5$salts$hash
  uid			int			  NOT NULL,
  email			varchar(128),
  personid		int,
  CONSTRAINT PK_Benutzer		PRIMARY KEY (benutzerid),
  CONSTRAINT FK_Benutzer_Person	FOREIGN KEY (personid)	  REFERENCES Person (personid)
--  CONSTRAINT FK_Benutzer_Person	FOREIGN KEY (personid)	  REFERENCES Person (personid) ON DELETE CASCADE
--  CONSTRAINT FK_Benutzer_Person	FOREIGN KEY (personid)	  REFERENCES Person (personid) ON DELETE SET NULL
);

INSERT INTO Person (nachname,vorname)
  VALUES ('Muster','Hans');
INSERT INTO Benutzer (username,password,uid,personid)
  VALUES ('mh','geheim',1000,currval('person_personid_seq'));
INSERT INTO Person (nachname,vorname)
  VALUES ('Kaufmann','Urs');
INSERT INTO Benutzer (username,password,uid,personid)
  VALUES ('ku','keines',1001,currval('person_personid_seq'));

-- DELETE FROM Person WHERE nachname='Muster';

SELECT * FROM Person;
SELECT * FROM Benutzer;