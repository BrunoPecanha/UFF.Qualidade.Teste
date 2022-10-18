-- ADMINISTRADORES

insert into administradores(id, nome, cpf, senha) values
(null, 'Thanos', '13676616766', '1234')

insert into administradores(id, nome, cpf, senha) values
(null, 'Kang', '11111111111', '1234')

-- USUARIOS

insert into usuarios(id, nome, cpf, senha) values
(null, 'Homem de Ferro', '12345678910', '1234')

insert into usuarios(id, nome, cpf, senha) values
(null, 'Thor', '00011122233', '1234')

insert into usuarios(id, nome, cpf, senha) values
(null, 'Hulk', '09876543210', '1234')

-- CATEGORIAS

insert into categorias(id, descricao) values
(null, 'Compras')

insert into categorias(id, descricao) values
(null, 'Moradia')

insert into categorias(id, descricao) values
(null, 'Transporte')

insert into categorias(id, descricao) values
(null, 'Lazer')

insert into categorias(id, descricao) values
(null, 'Pagamento')

-- CONTAS

insert into contas (id, id_usuario, nome_conta, banco, agencia, conta_corrente) values
(null, (select id from usuarios where cpf = '12345678910'), 'Bradesco', '011', '1276', '111222')

insert into contas (id, id_usuario, nome_conta, banco, agencia, conta_corrente) values
(null, (select id from usuarios where cpf = '00011122233'), 'Itaú', '562', '6214', '668774')

-- LANÇAMENTOS

insert into lancamentos (id, id_conta, id_categoria, valor, operacao, data, descricao) values
(null, 
(select id from contas
	where id_usuario = (select id from usuarios where cpf = '00011122233')),
5,
150.00,
'D',
STR_TO_DATE('17-10-2022', '%d-%m-%Y'),
'Compras para a semana')

insert into lancamentos (id, id_conta, id_categoria, valor, operacao, data, descricao) values
(null, 
(select id from contas
	where id_usuario = (select id from usuarios where cpf = '00011122233')),
1,
10.000,
'C',
STR_TO_DATE('15-10-2022', '%d-%m-%Y'),
'Pagamento do salário')


insert into lancamentos (id, id_conta, id_categoria, valor, operacao, data, descricao) values
(null, 
(select id from contas
	where id_usuario = (select id from usuarios where cpf = '00011122233')),
3,
50,
'D',
STR_TO_DATE('18-10-2022', '%d-%m-%Y'),
'Ida para faculdade')










