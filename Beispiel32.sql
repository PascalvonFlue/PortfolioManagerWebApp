-- Funktionen für den Username-Generator
-- -------------------------------------

DROP FUNCTION IF EXISTS F_GenerateUsername(persid int);
DROP FUNCTION IF EXISTS F_Cleanup(string varchar);

CREATE OR REPLACE FUNCTION F_Cleanup(string varchar)
  RETURNS varchar
AS $code$
DECLARE
  clean	varchar;
BEGIN
  clean := lower(trim(from string));
  clean := replace(clean,'ä','ae');
  clean := replace(clean,'ö','oe');
  clean := replace(clean,'ü','ue');
  return clean;
END;
$code$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION F_GenerateUsername(persid int)
  RETURNS varchar
AS $code$
DECLARE
  u			varchar;
  pers		record;
  b			int;
  i			int;
BEGIN
  SELECT nachname, vorname INTO pers FROM Person P WHERE P.personid=persid;
  u := left(F_Cleanup(pers.nachname),7) || left(F_Cleanup(pers.vorname),1);
  i := 1;
  LOOP
	SELECT benutzerid INTO b FROM Benutzer WHERE username=u;
	EXIT WHEN b IS NULL;
	u := left(F_Cleanup(pers.nachname),7) || i;
	i := i + 1;
  END LOOP;
  RETURN u;
END;
$code$
VOLATILE
LANGUAGE plpgsql;

SELECT F_GenerateUsername(1),F_GenerateUsername(2);

SELECT * FROM Person;
INSERT INTO Person (nachname,vorname) VALUES ('Müller','Hans Rudolf');