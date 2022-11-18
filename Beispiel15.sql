-- Abfragen über mehere Tabellen
--

SELECT COUNT(*)
  FROM products;

SELECT COUNT(*)
  FROM suppliers;

SELECT COUNT(*)
  FROM products, suppliers;

SELECT productname, companyname
  FROM products, suppliers;

SELECT productname, companyname
  FROM products, suppliers
 WHERE products.supplierid = suppliers.supplierid;

SELECT P.productname, S.companyname, S.supplierid
  FROM products P, suppliers S
 WHERE P.supplierid = S.supplierid;

SELECT P.productname, S.companyname, S.supplierid
 FROM products P
INNER JOIN suppliers S ON S.supplierid = P.supplierid;