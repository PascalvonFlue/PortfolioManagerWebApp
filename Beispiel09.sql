-- Liste aller Produkte (name,price) sortiert nach Preis absteigend
--

SELECT productname, unitprice
  FROM products
 ORDER BY unitprice DESC;

SELECT productname, unitprice
  FROM products
 ORDER BY unitprice DESC, productname, productid;

SELECT *
  FROM employees
 ORDER BY lastname, firstname, birthdate, employeeid;