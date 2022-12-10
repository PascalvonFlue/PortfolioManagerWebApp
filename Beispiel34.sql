-- Finde die nächste freie UID ab 1000
-- -----------------------------------

CREATE OR REPLACE VIEW V_UID AS
SELECT 1000 AS uid

UNION

SELECT uid+1
  FROM benutzer
  WHERE uid+1 BETWEEN 1000 AND 65534

EXCEPT

SELECT uid
  FROM benutzer

ORDER BY uid

LIMIT 1;

SELECT * FROM V_uid;