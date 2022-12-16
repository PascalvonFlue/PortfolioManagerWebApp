-- Beispiel mit Trigger
-- --------------------

DROP VIEW IF EXISTS V_UID;
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

CREATE OR REPLACE FUNCTION TF_Benutzer_Insert()
  RETURNS trigger
AS $code$
BEGIN
  IF (NEW.username IS NULL AND NEW.personid IS NOT NULL) THEN
    NEW.username := F_GenerateUsername(NEW.personid);
  END IF;

  IF (NEW.password IS NULL) THEN
    NEW.password := F_GeneratePassword(8);
  END IF;

  IF (NEW.uid IS NULL) THEN
    SELECT uid INTO NEW.uid FROM V_UID;
  END IF;

  IF (NEW.email IS NULL AND NEW.username IS NOT NULL) THEN
    NEW.email := NEW.username || '@example.org';
  END IF;

  RETURN NEW;
END;
$code$
LANGUAGE plpgsql;

CREATE TRIGGER T_Benutzer_Insert BEFORE INSERT ON Benutzer
  FOR EACH ROW EXECUTE PROCEDURE TF_Benutzer_Insert();

CREATE OR REPLACE VIEW V_UID AS
SELECT 1000 AS uid

UNION

SELECT uid+1
  FROM benutzer
  WHERE uid+1 BETWEEN 1000 AND 65534

EXCEPT

SELECT uid
  FROM benutzer

ORDER BY uid

LIMIT 1;

INSERT INTO Person (nachname,vorname)
   VALUES ('Müllermann','Hans Peter');
INSERT INTO Benutzer (personid)
   VALUES (currval('person_personid_seq'));
--INSERT INTO Person (nachname,vorname)
--  VALUES ('Kaufmann','Urs');
--INSERT INTO Benutzer (username,password,uid,personid)
--  VALUES ('ku','keines',-1,currval('person_personid_seq'));

-- DELETE FROM Person WHERE nachname='Muster';

SELECT * FROM Person;
SELECT * FROM Benutzer;