-- Alle Bestellungen (-position) nach ProductID (aufsteigend) und
-- Menge (absteigend) sortiert
--

SELECT productid, quantity
  FROM orderdetails
 ORDER BY productid ASC, quantity DESC, orderid;
