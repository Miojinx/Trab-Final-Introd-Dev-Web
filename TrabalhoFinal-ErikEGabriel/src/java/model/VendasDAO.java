package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Vendas;
import java.sql.Date;
import Entidade.VendasTotais;

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
                    vendas.setQuantidade_venda(resultado.getInt("QUANTIDADE_VENDA"));
                    vendas.setData_venda(resultado.getString("DATA_VENDA"));
                    vendas.setValor_venda(resultado.getFloat("VALOR_VENDA"));
                    vendas.setId_cliente(resultado.getInt("ID_CLIENTE"));
                    vendas.setId_produto(resultado.getInt("ID_PRODUTO"));
                    vendas.setId_funcionario(resultado.getInt("id_funcionario"));
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Vendas (quantidade_venda, data_venda, valor_venda, id_cliente,id_produto,id_funcionario) VALUES (?,?,?,?,?,?)");
            sql.setInt(1, t.getQuantidade_venda());
            sql.setDate(2, Date.valueOf(t.getData_venda()));
            sql.setFloat(3, t.getValor_venda());
            sql.setInt(4, t.getId_cliente());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_funcionario());
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Vendas SET quantidade_venda = ?, data_venda = ?, valor_venda = ?, id_cliente = ?, id_produto = ?, id_funcionario = ?  WHERE ID = ? ");
            sql.setInt(1, t.getQuantidade_venda());
            sql.setDate(2, Date.valueOf(t.getData_venda()));
            sql.setFloat(3, t.getValor_venda());
            sql.setInt(4, t.getId_cliente());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_funcionario());
            sql.setInt(7, t.getId());
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
            String selectSQL = "SELECT * FROM vendas";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Vendas venda = new Vendas(
                            resultado.getInt("ID"),
                            resultado.getInt("quantidade_venda"),
                            resultado.getString("data_venda"),
                            resultado.getFloat("valor_venda"),
                            resultado.getInt("id_cliente"),
                            resultado.getInt("id_produto"),
                            resultado.getInt("id_funcionario")
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

    public ArrayList<VendasTotais> getVendasTotais() {
        ArrayList<VendasTotais> listaVendasTotais = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "WITH total AS (SELECT id_produto, SUM(quantidade_venda) AS vendas_totais FROM vendas GROUP BY id_produto) "
                    + "SELECT vendas.id_produto, vendas_totais, data_venda, SUM(quantidade_venda) AS vendas_dia "
                    + "FROM vendas INNER JOIN total ON total.id_produto = vendas.id_produto "
                    + "GROUP BY vendas.id_produto, data_venda;";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    VendasTotais vendasTotais = new VendasTotais(
                            resultado.getInt("id_produto"),
                            resultado.getInt("vendas_totais"),
                            resultado.getString("data_venda"),
                            resultado.getInt("vendas_dia")
                    );
                    listaVendasTotais.add(vendasTotais);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetVendasTotais) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return listaVendasTotais;
    }
}
