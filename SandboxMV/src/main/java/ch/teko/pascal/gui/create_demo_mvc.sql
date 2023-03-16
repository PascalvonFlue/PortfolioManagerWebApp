DROP TABLE IF EXISTS MVC;
CREATE TABLE MVC (
  id      serial        not null,
  name      varchar(64),
  gross      varchar(4),
  klein      varchar(4),
  test      boolean,
  stempel timestamp    default current_timestamp,
  datum      date        default current_date,
  zeit      time        default current_time,
  zahl  int        default 0,
  wert      double precision,
  farbe      int        default 128,
  CONSTRAINT PK_MVC PRIMARY KEY (id)
);

INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Α', 'α', 'Alpha'  , 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Β', 'β', 'Beta'   , 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Γ', 'γ', 'Gamma'  , 'false',     24.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Δ', 'δ', 'Delta'  , 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ε', 'ε', 'Epsilon', 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ζ', 'ζ', 'Zeta'   , 'false',  23234.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Η', 'η', 'Eta'    , 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Θ', 'θ', 'Theta'  , 'false',   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ι', 'ι', 'Iota'   , 'false',  23334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Κ', 'κ', 'Kappa'  , 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Λ', 'λ', 'Lambda' , 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Μ', 'μ', 'My'     , 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ν', 'ν', 'Ny'     , 'true' ,2343394.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ξ', 'ξ', 'Xi'     , 'false',   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ο', 'ο', 'Omikron', 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Π', 'π', 'Pi'     , 'false',   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ρ', 'ρ', 'Rho'    , 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Σ', 'σ', 'Sigma'  , 'true' ,  23534.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Τ', 'τ', 'Tau'    , 'false',   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Υ', 'υ', 'Ypsilon', 'true' ,   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Φ', 'φ', 'Phi'    , 'true' , 452334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Χ', 'χ', 'Chi'    , 'false',   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ψ', 'ψ', 'Psi'    , 'false',   2334.23);
INSERT INTO MVC (gross,klein,name,test,wert) VALUES('Ω', 'ω', 'Omega'  , 'true' ,   2334.23);