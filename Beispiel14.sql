-- Die einzelnen Mengen der bestellten Produkte und deren Preis,
-- aber nur diejenigen der Preis grösser oder gleich 20'000 ist,
-- sortiert nach Preis absteigend.
--

SELECT productid, COUNT(productid), SUM(quantity), SUM(quantity*unitprice*(1-discount))
  FROM orderdetails
 GROUP BY productid
HAVING SUM(quantity*unitprice*(1-discount)) >= 20000
 ORDER BY SUM(quantity*unitprice*(1-discount)) DESC;

--ACHTUNG: nur Produkte, die mehr als 10 kosten
SELECT productid, SUM(quantity), COUNT(*), SUM(quantity*unitprice*(1-discount)) AS Preis
  FROM orderdetails
 WHERE unitprice > 10
 GROUP BY productid
HAVING SUM(quantity*unitprice*(1-discount)) >= 20000
 ORDER BY Preis DESC;
