-- VERIFICA SE EXISTE ALGUMA VENDA A PRAZO SEM CONTAS A RECEBER
SELECT id, totaldescen FROM venda AS v
WHERE v.id NOT IN (SELECT venda_id FROM contasreceber WHERE venda_id IS NOT NULL)
AND tipodoc = 'CREDIÁRIO'
AND cancelada IS FALSE
AND venda IS TRUE
AND vendaeditada IS FALSE
ORDER BY id ASC