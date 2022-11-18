-- Liste aller Produkte (Produktanme, Kategorie, Preis, Lieferantenname)
-- welche wir noch führen
--

SELECT P.productname, C.categoryname, P.unitprice, S.companyname
  FROM products P
  LEFT JOIN suppliers S ON S.supplierid=P.supplierid
  LEFT JOIN categories C ON C.categoryid=P.categoryid
 WHERE NOT P.discontinued
 ORDER BY C.categoryname, P.productname, P.productid;