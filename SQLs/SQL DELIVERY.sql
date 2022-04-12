-- PRODUTO MAIS PEDIDO
SELECT p.nome, e.nome, COUNT(*) FROM item_mesa im
INNER JOIN produto AS p
ON im.plantio_id = p.id
INNER JOIN mesa AS m
ON im.mesa_id = m.id
INNER JOIN empresa AS e
ON p.empresa_id = e.id
WHERE m.statusmesa = 'FECHADA'
AND m.finalizado IS TRUE
GROUP BY p.nome, e.nome
ORDER BY COUNT(*) DESC;


-- USUÁRIOS POR MES
SELECT COUNT(*) FROM usuario
WHERE dtcadastro BETWEEN '2020-11-01' AND '2020-11-19';


-- USUÁRIOS POR PERÍODO AGRUPADO POR CIDADE
SELECT c.nome, COUNT(*) FROM usuario u
INNER JOIN cidade AS c
ON u.cidade_id = c.id
WHERE dtcadastro BETWEEN '2020-10-01' AND '2020-10-31'
GROUP BY c.nome
ORDER BY COUNT(*) DESC;


-- USUÁRIOS AGRUPADO POR CIDADE (TODOS)
SELECT c.nome, COUNT(*) FROM usuario u
INNER JOIN cidade AS c
ON u.cidade_id = c.id
GROUP BY c.nome
ORDER BY COUNT(*) DESC;


-- USUÁRIOS COM MAC REGISTRADO
SELECT COUNT(*) FROM usuario u
WHERE u.mac IS NOT NULL;


-- FATURAMENTO POR EMPRESA - GERAL
SELECT e.nome, COUNT(*), SUM(m.total) FROM mesa m
INNER JOIN empresa AS e
ON m.empresa_id = e.id
WHERE m.statusmesa = 'FECHADA'
AND m.finalizado IS TRUE
GROUP BY e.nome
ORDER BY SUM(m.total) DESC;


-- FATURAMENTO POR EMPRESA - PERÍODO
SELECT e.nome, COUNT(*), SUM(m.total) FROM mesa m
INNER JOIN empresa AS e
ON m.empresa_id = e.id
WHERE m.statusmesa = 'FECHADA'
AND m.finalizado IS TRUE
AND date(m.dtmovimento) BETWEEN '2020-11-01' AND '2020-11-19'
GROUP BY e.nome
ORDER BY SUM(m.total) DESC;