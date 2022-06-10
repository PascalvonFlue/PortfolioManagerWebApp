-- Erste Abfrage einer Tabelle
-- ---------------------------

-- Alle Zeilen, alle Attribute
SELECT *
  FROM products;

-- Nur ProductID, ProductName und UnitPrice
SELECT productid, productname, unitprice
  FROM products;

-- ProductID, ProductName und UnitPrice (mit und ohne MwSt.)
SELECT productid AS "Produktnummer", productname AS "Produktname",
	   unitprice AS "Stückpreis [excl. MwSt.]",
       unitprice * 1.077 AS "Stückpreis [inkl. MwSt.]"
  FROM products;
