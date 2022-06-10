-- Beispiel 01 Einführung
-- ----------------------
--
-- Dies ist ein Kommentar
-- // dies auch aber kein Standard
-- /* */ dies auch aber kein Standard
--
-- JAVA: Case-Sensitiv
--     String     "String Literal"
--     Character  'C'
--     Identifier Haus EIN_HAUS Test2022
--
-- SQL: Case-Insensitiv
-- Varchar/Text (String)   'Einfache Anführungszeichen' 
--           $IRGENT-EIN-STRING-ODER-LEER$Ein Text$IRGENT-EIN-STRING-ODER-LEER$
--           $$Dies ist ein Text!$$
--			 $TEXT$ geht über
--                  meherer Zeilen
--           $TEXT$
--
--     Identifier Haus EIN_HAUS Test2022 "Ein Haus" "L-TIN-20-T-a"
--	   Speziell bei PostgreSQL: --> Case-Sensitiv...
--       ... aber
--           EinHaus -> wird zu lowercase einhaus
--       ... "EinHaus" -> bleibt >>EinHaus<<
--         "einhaus" ist EinHaus EINHAUS usw.

SELECT 'Hello World';
SELECT 5+3;

SELECT 'Test', 12 * 3, null, 'Hans' || ' ' || 'Muster';
SELECT 'Test' AS Aufgabe, 12 * 5 AS "Berechnung";
SELECT $Text$Text$Text$ AS "Text";
