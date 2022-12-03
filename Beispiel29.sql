-- Beispiel mit Indizies
-- ---------------------

DROP TABLE IF EXISTS Benutzer;
DROP TABLE IF EXISTS Person;

GRANT USAGE ON SCHEMA hm TO PUBLIC;

CREATE TABLE Person (
  personid		serial,
  nachname		varchar(64)	 NOT NULL,
  vorname		varchar(32),
  geburtsdatum	date		DEFAULT current_date,
  CONSTRAINT PK_Person		PRIMARY KEY (personid)
);
CREATE INDEX IX_Person_Nachname_Vorname ON Person (nachname,vorname);
GRANT SELECT ON Person TO Public;

CREATE TABLE Benutzer (
  benutzerid	serial,
  username		varchar(16)	  NOT NULL,
  password		varchar(128)  NOT NULL,	-- {plain}geheim  {md5}falsch {sha512}falsch {crypt}$5$salts$hash
  uid			int			  NOT NULL,
  email			varchar(128),
  personid		int,
  CONSTRAINT PK_Benutzer		  PRIMARY KEY (benutzerid),
  CONSTRAINT FK_Benutzer_Person	  FOREIGN KEY (personid)	  REFERENCES Person (personid),
--  CONSTRAINT FK_Benutzer_Person	FOREIGN KEY (personid)	  REFERENCES Person (personid) ON DELETE CASCADE,
--  CONSTRAINT FK_Benutzer_Person	FOREIGN KEY (personid)	  REFERENCES Person (personid) ON DELETE SET NULL,
  CONSTRAINT UN_Benutzer_Username UNIQUE (username),
  CONSTRAINT UN_Benutzer_UID	  UNIQUE (uid),
  CONSTRAINT CK_Benutzer_UID	  CHECK (uid >= 0),
  CONSTRAINT CK_Benutzer_EMail	  CHECK ( email ~ '.*@.*' )
);

INSERT INTO Person (nachname,vorname)
  VALUES ('Muster','Hans');
INSERT INTO Benutzer (username,password,uid,email,personid)
  VALUES ('mh','geheim',1000,'Ich will keine M@ils',currval('person_personid_seq'));
--INSERT INTO Person (nachname,vorname)
--  VALUES ('Kaufmann','Urs');
--INSERT INTO Benutzer (username,password,uid,personid)
--  VALUES ('ku','keines',-1,currval('person_personid_seq'));

-- DELETE FROM Person WHERE nachname='Muster';

SELECT * FROM Person;
SELECT * FROM Benutzer;