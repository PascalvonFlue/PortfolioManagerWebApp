-- Lieferant Nr. 14 hat Lagerräumung. Was und wie viel brauchen wir von ihm?
--

SELECT productname, unitsinstock, unitsonorder, reorderlevel,
       unitsonorder + reorderlevel - unitsinstock AS "Bestellmenge"
  FROM products
 WHERE unitsinstock-unitsonorder < reorderlevel
   AND supplierid=14;