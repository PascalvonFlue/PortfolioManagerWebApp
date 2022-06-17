-- Alle Kunden (name), die ein Restaurant sind?
--

SELECT companyname
  FROM customers
 WHERE companyname LIKE '%Restaurant%';

SELECT companyname
  FROM customers
 WHERE companyname ILIKE '%Restaurant%'; -- nicht Standard

SELECT companyname
  FROM customers
 WHERE companyname ~ '.*Restaurant.*'; -- Regulärer Ausdruck