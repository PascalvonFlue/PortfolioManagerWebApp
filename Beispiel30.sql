-- Unsere erste Funktion/Prozedure
-- -------------------------------

CREATE OR REPLACE FUNCTION demo01(a int, b int) RETURNS int
AS $CODE$
DECLARE
  prod int;
BEGIN
  prod := a * b;
  RETURN prod;
END;
$CODE$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION demo01(a int, b int, c int) RETURNS int
AS $CODE$
DECLARE
  prod int;
BEGIN
  prod := a * b + c;
  RETURN prod;
END;
$CODE$
LANGUAGE plpgsql IMMUTABLE;

SELECT demo01(5,3);