-- Alle Bestellungen mit Kundenname, Mitarbeiter (mit Vorname+Nachname)
-- und Bestelldatum mit Kalenderwoche
-- sortiert nach Mitarbeiter (Nachname dann Vorname)
--

SELECT C.companyname, E.firstname || ' ' || E.lastname as employer, -- contcat_ws(' ',E.firstname,E.lastname)
       to_char(O.orderdate,'DD. Mon. YYYY') as orderdate,
       to_char(O.orderdate,'IW') as orderweek
  FROM orders O
  LEFT JOIN employees E ON E.employeeid = O.employeeid
  LEFT JOIN customers C ON C.customerid = O.customerid
 ORDER BY E.lastname, E.firstname;