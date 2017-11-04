CREATE TABLE risco_contrato (
  "id_contrato" INT NOT NULL,
  "risco" VARCHAR(20) NOT NULL,
  CONSTRAINT id_contrato_fk FOREIGN KEY ("id_contrato") REFERENCES contrato("id"),
  CONSTRAINT id_contrato_risco_unique UNIQUE ("id_contrato", "risco")
);