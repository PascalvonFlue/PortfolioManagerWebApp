-- Welche Produkte muss ich nachbestellen
-- --------------------------------------

SELECT productname, unitprice, unitsinstock, unitsonorder, "Hallo Juhu-Haus"
  FROM products
 WHERE unitsonorder > unitsinstock;
