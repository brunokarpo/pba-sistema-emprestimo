INSERT INTO contrato ("codigo", "titulo", "perc_sal_emprestimo", "perc_sal_comprometido", "juros_mes") VALUES ('c018', 'Crédito para férias', 0.5, 0.07, 0.1);
INSERT INTO contrato ("codigo", "titulo", "perc_sal_emprestimo", "perc_sal_comprometido", "juros_mes") VALUES ('h453', 'Crédito para emergências', 1, 0.1, 0.05);
INSERT INTO contrato ("codigo", "titulo", "perc_sal_emprestimo", "perc_sal_comprometido", "juros_mes") VALUES ('y88y', 'Crédito para quitação de débitos', 5, 0.1, 0.15);
INSERT INTO contrato ("codigo", "titulo", "perc_sal_emprestimo", "perc_sal_comprometido", "juros_mes") VALUES ('ad56', 'Crédito para investimentos', 10, 0.15, 0.08);
INSERT INTO contrato ("codigo", "titulo", "perc_sal_emprestimo", "perc_sal_comprometido", "juros_mes") VALUES ('piy4', 'Crédito para financiamento de automóveis', 15, 0.2, 0.12);
INSERT INTO contrato ("codigo", "titulo", "perc_sal_emprestimo", "perc_sal_comprometido", "juros_mes") VALUES ('t3r4', 'Crédito para compra de terra', 50, 0.2, 0.18);
INSERT INTO contrato ("codigo", "titulo", "perc_sal_emprestimo", "perc_sal_comprometido", "juros_mes") VALUES ('1m0v', 'Crédito para financiamento de imóveis', 100, 0.3, 0.12);

INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'c018'), 'BAIXO');
INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'c018'), 'MEDIO');
INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'c018'), 'ALTO');

INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'h453'), 'BAIXO');
INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'h453'), 'MEDIO');
INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'h453'), 'ALTO');

INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'y88y'), 'BAIXO');
INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'y88y'), 'MEDIO');

INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'ad56'), 'BAIXO');
INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'ad56'), 'MEDIO');

INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 'piy4'), 'BAIXO');

INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = 't3r4'), 'BAIXO');

INSERT INTO risco_contrato("id_contrato", "risco") VALUES ((SELECT "id" FROM contrato WHERE "codigo" = '1m0v'), 'BAIXO');