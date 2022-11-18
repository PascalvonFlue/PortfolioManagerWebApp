-- Liste aller Mitarbeiter (Vorname+Nachname) und deren Region (Beschreibung)
--

SELECT concat_ws(' ',E.firstname,E.lastname) AS employer, R.regiondescription
  FROM employees E
  LEFT JOIN employeeterritories ET ON ET.employeeid=E.employeeid
  JOIN territories T ON T.territoryid=ET.territoryid
  JOIN region R ON R.regionid=T.regionid
 GROUP BY E.employeeid, R.regionid
 ORDER BY E.lastname, E.firstname, E.employeeid, R.regiondescription;

-- und mit den Territorien
SELECT concat_ws(' ',E.firstname,E.lastname) AS employer, R.regiondescription,
	   string_agg(T.territorydescription,', ') AS territories
  FROM employees E
  LEFT JOIN employeeterritories ET ON ET.employeeid=E.employeeid
  JOIN territories T ON T.territoryid=ET.territoryid
  JOIN region R ON R.regionid=T.regionid
 GROUP BY E.employeeid, R.regionid
 ORDER BY E.lastname, E.firstname, E.employeeid, R.regiondescription;


SELECT * FROM employees;
SELECT * FROM employeeterritories;
SELECT * FROM territories;
SELECT * FROM region;