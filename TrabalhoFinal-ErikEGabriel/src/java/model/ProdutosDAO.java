package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Produtos;

/*
CREATE TABLE `produtos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(100) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `preco_compra` decimal(10,2) NOT NULL,
  `preco_venda` decimal(10,2) NOT NULL,
  `quantidade_disponível` int(11) NOT NULL,
  `liberado_venda` varchar(1) NOT NULL DEFAULT 'S',
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_fk_categoria` (`id_categoria`) USING BTREE,
  CONSTRAINT `produtos_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`)
)
 */
public class ProdutosDAO implements Dao<Produtos> {

    @Override
    public Produtos get(int id) {
        Conexao conexao = new Conexao();
        Produtos produto = new Produtos();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM produtos WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    produto.setId(Integer.parseInt(resultado.getString("ID")));
                    produto.setNome_produto(resultado.getString("NOME_PRODUTO"));
                    produto.setDescricao(resultado.getString("DESCRICAO"));
                    produto.setPreco_compra(Float.parseFloat(resultado.getString("PRECO_COMPRA")));
                    produto.setPreco_venda(Float.parseFloat(resultado.getString("PRECO_VENDA")));
                    produto.setQuantidade_disponivel(Integer.parseInt(resultado.getString("QUANTIDADE_DISPONÍVEL")));
                    produto.setLiberado_venda(resultado.getString("LIBERADO_VENDA").charAt(0));
                    produto.setId_categoria(Integer.parseInt(resultado.getString("ID_CATEGORIA")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return produto;
    }

    @Override
    public void insert(Produtos t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO produtos (nome_produto, descricao, preco_compra, preco_venda, quantidade_disponível, liberado_venda, id_categoria) VALUES (?,?,?,?,?,?,?)");
            sql.setString(1, t.getNome_produto());
            sql.setString(2, t.getDescricao());
            sql.setFloat(3, t.getPreco_compra());
            sql.setFloat(4, t.getPreco_venda());
            sql.setInt(5, t.getQuantidade_disponivel());
            sql.setString(6, String.valueOf(t.getLiberado_venda()));
            sql.setInt(7, t.getId_categoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Produtos t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE produtos SET nome_produto = ?, descricao = ?, preco_compra = ?, preco_venda = ?, quantidade_disponível = ?, liberado_venda = ?, id_categoria = ? WHERE ID = ? ");
            sql.setString(1, t.getNome_produto());
            sql.setString(2, t.getDescricao());
            sql.setFloat(3, t.getPreco_compra());
            sql.setFloat(4, t.getPreco_venda());
            sql.setInt(5, t.getQuantidade_disponivel());
            sql.setString(6, String.valueOf(t.getLiberado_venda()));
            sql.setInt(7, t.getId_categoria());
            sql.setInt(8, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM produtos WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public ArrayList<Produtos> getAll() {
        ArrayList<Produtos> listaProdutos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * from estoque.produtos";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Produtos produto= new Produtos(
                            resultado.getInt("ID"),
                            resultado.getString("NOME_PRODUTO"),
                            resultado.getString("DESCRICAO"),
                            resultado.getFloat("PRECO_COMPRA"),
                            resultado.getFloat("PRECO_VENDA"),
                            resultado.getInt("QUANTIDADE_DISPONÍVEL"),
                            resultado.getString("LIBERADO_VENDA").charAt(0),
                            resultado.getInt("ID_CATEGORIA")
                    );
                    listaProdutos.add(produto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (listaProdutos) incorreta", e);
        } finally {
            conexao.closeConexao();
        }
        return listaProdutos;
    }
    
}
