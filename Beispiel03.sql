-- Welche Produkte muss ich nachbestellen
-- --------------------------------------

SELECT productname, unitprice, unitsinstock, unitsonorder
  FROM products
 WHERE unitsonorder > unitsinstock;
