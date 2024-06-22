package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Compras;
import java.util.Date;

public class ComprasDAO implements Dao<Compras> {

    @Override
    public Compras get(int id) {
        Conexao conexao = new Conexao();
        Compras funcionario = new Compras();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Compras WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    funcionario.setId(Integer.parseInt(resultado.getString("ID")));
                    funcionario.setQuantidade_compra(resultado.getInt("QUANTIDADE_COMPRA_COMPRAS"));
                    funcionario.setData_compra(resultado.getDate("DATA_COMPRA_COMPRAS"));
                    funcionario.setValor_compra(resultado.getFloat("VALOR_COMPRA_COMPRAS"));
                    funcionario.setId_fornecedor(resultado.getInt("ID_FORNECEDOR_COMPRAS"));
                    funcionario.setId_produto(resultado.getInt("ID_PRODUTO_COMPRAS"));
                    funcionario.setId_comprador(resultado.getInt("ID_COMPRADOR_COMPRAS"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return funcionario;
    }
    
    @Override
    public void insert(Compras t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Compras (quantidade_compra, data_compra, valor_compra, id_fornecedor,id_produto,id_comprador) VALUES (?,?,?,?,?,?)");
            sql.setInt(1, t.getQuantidade_compra());
            sql.setDate(2, new java.sql.Date(t.getData_compra().getTime()));
            sql.setFloat(3, t.getValor_compra());
            sql.setInt(4, t.getId_fornecedor());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_comprador());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void update(Compras t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Compras SET quantidade_compra = ?, data_compra = ?, valor_compra = ?, id_fornecedor = ?, id_produto = ?, id_comprador = ?  WHERE ID = ? ");
            sql.setInt(1, t.getQuantidade_compra());
            sql.setDate(2, new java.sql.Date(t.getData_compra().getTime()));
            sql.setFloat(3, t.getValor_compra());
            sql.setInt(4, t.getId_fornecedor());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_comprador());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Compras WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public ArrayList<Compras> getAll() {

        ArrayList<Compras> listaCompras = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Compras";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Compras compras = new Compras(
                            resultado.getInt("ID"),
                            resultado.getInt("quantidade_compra"),
                            resultado.getDate("data_compra"),
                            resultado.getFloat("valor_compra"),
                            resultado.getInt("id_fornecedor"),
                            resultado.getInt("id_produto"),
                            resultado.getInt("id_comprador")
                    );
                    listaCompras.add(compras);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - Compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return listaCompras;
    }
}
