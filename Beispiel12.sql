-- Wieiel Kunden habe wir pro Region, sortiert nach Anzahl, die grösste zuerst
--

SELECT region, count(*) as marvin
  FROM customers
 GROUP BY region
 ORDER BY marvin DESC;