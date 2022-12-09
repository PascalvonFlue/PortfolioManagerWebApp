-- Funktionen für den Passwort-Generator
-- -------------------------------------

SELECT substring( 'abcdefghijklmnopqrstuvwxyz' from cast(trunc(random()*26) as int)+1 for 1);

CREATE OR REPLACE FUNCTION F_RandomChar(string varchar)
  RETURNS varchar
AS $code$
BEGIN
  RETURN substring( string from cast(trunc(random()*length(string)) as int)+1 for 1);
END;
$code$
VOLATILE
LANGUAGE plpgsql;

SELECT F_RandomChar('abcdefghijklmnopqrstuvwxyz'),F_RandomChar('ABCEDF');

CREATE OR REPLACE FUNCTION F_GeneratePassword(n int)
  RETURNS varchar
AS $code$
DECLARE
  passwd varchar := '';
BEGIN
  FOR i IN 1..n LOOP
	CASE i
      WHEN 1 THEN
		passwd := passwd || F_RandomChar('abcdefghijklmnopqrstuvwxyz');
	  WHEN 4 THEN
		passwd := passwd || F_RandomChar('!&%_-.');
	  ELSE
		passwd := passwd || F_RandomChar('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789');
    END CASE;
  END LOOP;
  RETURN passwd;
END;
$code$
VOLATILE
LANGUAGE plpgsql;

SELECT F_GeneratePassword(8), F_GeneratePassword(12);