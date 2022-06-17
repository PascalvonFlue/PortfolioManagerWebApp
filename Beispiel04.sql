-- Welche Produkte muss ich, mit welchen Mengen nachbestellen, so dass
-- nach der Ausliererung der Bestellungen, noch midenstens der
-- Minaimalbestand am Lager ist?
--

SELECT productname, reorderlevel-unitsinstock+unitsonorder AS "Bestellmenge"
  FROM products
-- WHERE reorderlevel-unitsinstock+unitsonorder > 0
 WHERE unitsinstock-unitsonorder < reorderlevel;