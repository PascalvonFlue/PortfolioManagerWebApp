-- Anzahl der Bestellposiotnen einer Bestellung
--

SELECT orderid, count(*), sum(quantity), sum(quantity)*2, sum(quantity*2),
       min(discount), max(discount),
	   sum(quantity*unitprice*(1-discount))
  FROM orderdetails
 GROUP BY orderid
 ORDER BY orderid;
