-- Alle Produkte (name,price) zwischen 20 unf 50 CHF (inkl. -> [20,50])
--

SELECT productname, unitprice
  FROM products
 WHERE unitprice >= 20 AND unitprice <= 50;

SELECT productname, unitprice
  FROM products
 WHERE unitprice BETWEEN 20 AND 50;