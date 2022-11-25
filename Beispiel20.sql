-- EINE Adressliste für Weihnachtskarten aller Kunden, Lieferanten und Mitarbeiter
-- sortiert nach Land und Postleitzahl
--

SELECT companyname AS name, address, postalcode, city, country
  FROM customers
UNION
SELECT companyname, address, postalcode, city, country
  FROM suppliers
UNION
SELECT concat_ws(' ',firstname,lastname), address, postalcode, city, country
  FROM employees
ORDER BY country, postalcode, name;

SELECT 'K' AS typ, companyname AS name, address, postalcode, city, country
  FROM customers
UNION
SELECT 'L', companyname, address, postalcode, city, country
  FROM suppliers
UNION
SELECT 'M', concat_ws(' ',firstname,lastname), address, postalcode, city, country
  FROM employees
ORDER BY country, postalcode, name;