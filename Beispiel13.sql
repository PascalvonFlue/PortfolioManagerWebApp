-- Wieiel Kunden habe wir pro Region, sortiert nach Anzahl, die grösste zuerst
-- ohne Kunden ohne Region und Regionen mit nur einem Kunden

SELECT region, count(*)
  FROM customers
 WHERE region IS NOT NULL
 GROUP BY region
 HAVING count(*) > 1
 ORDER BY count(*) DESC;