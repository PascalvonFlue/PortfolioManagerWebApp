-- Wir können (nicht richtig) rechnen
--

SELECT count(*) FROM customers;
-- Wir haben 91 Kunden

SELECT count(*) FROM customers WHERE region='CA';
-- Wir haben einen Kunden in der Region CA

SELECT count(*) FROM customers WHERE region<>'CA';
-- Wir bekommen nur 30 Kunden

SELECT count(*) FROM customers WHERE region<>'CA' OR region IS NULL;
-- Nun stimmt es