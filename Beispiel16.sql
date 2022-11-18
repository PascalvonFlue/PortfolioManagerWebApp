-- Alle Bestellung mit Kundenname und Bestelldatum
-- sortiert nach Datum
--

SELECT C.companyname, O.orderdate
  FROM orders O
  LEFT OUTER JOIN customers C ON C.customerid = O.customerid
 ORDER BY O.orderdate;

