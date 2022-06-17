-- Alle Produkte (name, supplierid) der Lieferanten 2, 7, 12 und 18!
--

SELECT productname, supplierid
  FROM products
 WHERE supplierid=2
    OR supplierid=7
    OR supplierid=12
    OR supplierid=18;

SELECT productname, supplierid
  FROM products
 WHERE supplierid IN (2,7,12,18);