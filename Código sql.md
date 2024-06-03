# Código sql

Revisado: No

```sql
use trabintrodevweb;
create table Categorias (
id int(11) primary key,
nome_categoria varchar(50));

create table Clientes (
id int(11) primary key,
nome varchar(50),
cpf varchar(14),
endereco varchar(50),
bairro varchar(50),
cidade varchar (50),
uf varchar(2),
cep varchar(8),
telefone varchar(20),
email varchar(50)
);

create table Fornecedores (
id int(11) primary key,
razao_social varchar(50),
cnpj varchar(18),
endereco varchar(50),
bairro varchar(50),
cidade varchar (50),
uf varchar(2),
cep varchar(9),
telefone varchar(20),
email varchar(50)
);

create table Funcionarios (
id int(11) primary key,
nome varchar(50),
cpf varchar(14),
senha varchar(10),
email varchar(50),
tipo varchar(1)
);

create table Produtos (
id int(11) primary key,
nome_produto varchar(100),
descricao varchar(255),
preco_compra decimal(10,2),
preco_venda decimal(10,2),
quantidade_disponivel int(11),
liberado_venda varchar(1),
id_categoria int(11),
constraint fk_prod_id_categoria foreign key(id_categoria) references categorias(id)
);

create table Vendas (
id int(11) primary key,
quantidade_venda int(11),
data_venda date,
valor_venda float,
id_cliente int(11),
id_produto int(11),
id_vendedor int(11),
constraint fk_vendas_id_cliente foreign key(id_cliente) references clientes(id),
constraint fk_vendas_id_produto foreign key(id_produto) references produtos(id),
constraint fk_vendas_id_vendedor foreign key(id_vendedor) references funcionarios(id)
);

create table Compras (
id int(11) primary key,
quantidade_compra int(11),
data_compra date,
valor_compra decimal(10,2),
id_fornecedor int(11),
id_produto int(11),
id_comprador int(11),
constraint fk_compras_id_fornecedor foreign key(id_fornecedor) references fornecedores(id),
constraint fk_compras_id_produto foreign key(id_produto) references produtos(id),
constraint fk_compras_id_comprador foreign key(id_comprador) references funcionarios(id)
);
```