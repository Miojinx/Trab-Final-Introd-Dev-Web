package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Vendas;
import java.util.Date;

public class VendasDAO implements Dao<Vendas> {

    @Override
    public Vendas get(int id) {
        Conexao conexao = new Conexao();
        Vendas vendas = new Vendas();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Vendas WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    vendas.setId(Integer.parseInt(resultado.getString("ID")));
                    vendas.setQuantidade_venda(resultado.getInt("QUANTIDADE_VENDA_VENDAS"));
                    vendas.setData_venda(resultado.getDate("DATA_VENDA_VENDAS"));
                    vendas.setValor_venda(resultado.getFloat("VALOR_VENDA_VENDAS"));
                    vendas.setId_cliente(resultado.getInt("ID_CLIENTE_VENDAS"));
                    vendas.setId_produto(resultado.getInt("ID_PRODUTO_VENDAS"));
                    vendas.setId_vendedor(resultado.getInt("ID_VENDEDOR_VENDAS"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get Vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return vendas;
    }
    
    @Override
    public void insert(Vendas t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Vendas (quantidade_venda, data_venda, valor_venda, id_cliente,id_produto,id_vendedor) VALUES (?,?,?,?,?,?)");
            sql.setInt(1, t.getQuantidade_venda());
            sql.setDate(2, new java.sql.Date(t.getData_venda().getTime()));
            sql.setFloat(3, t.getValor_venda());
            sql.setInt(4, t.getId_cliente());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_vendedor());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (Vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void update(Vendas t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Vendas SET quantidade_venda = ?, data_venda = ?, valor_venda = ?, id_cliente = ?, id_produto = ?, id_vendedor = ?  WHERE ID = ? ");
            sql.setInt(1, t.getQuantidade_venda());
            sql.setDate(2, new java.sql.Date(t.getData_venda().getTime()));
            sql.setFloat(3, t.getValor_venda());
            sql.setInt(4, t.getId_cliente());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_vendedor());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar Vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Vendas WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir Vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public ArrayList<Vendas> getAll() {

        ArrayList<Vendas> listaVendas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Vendas";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Vendas venda = new Vendas(
                            resultado.getInt("ID"),
                            resultado.getInt("quantidade_venda"),
                            resultado.getDate("data_venda"),
                            resultado.getFloat("valor_venda"),
                            resultado.getInt("id_cliente"),
                            resultado.getInt("id_produto"),
                            resultado.getInt("id_vendedor")
                    );
                    listaVendas.add(venda);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - Vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return listaVendas;
    }
}
