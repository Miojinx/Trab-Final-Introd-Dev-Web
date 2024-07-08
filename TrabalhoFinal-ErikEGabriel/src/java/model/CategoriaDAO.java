package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Categorias;

public class CategoriaDAO implements Dao<Categorias> {

    @Override
    public Categorias get(int id) {
        Conexao conexao = new Conexao();
        Categorias categoria = new Categorias();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM categorias WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    categoria.setId(Integer.parseInt(resultado.getString("ID")));
                    categoria.setNome_categoria(resultado.getString("NOME_CATEGORIA"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return categoria;
    }
    
    @Override
    public void insert(Categorias t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO categorias (nome_categoria) VALUES (?)");
            sql.setString(1, t.getNome_categoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void update(Categorias t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE categorias SET nome_categoria = ?  WHERE ID = ? ");
            sql.setString(1, t.getNome_categoria());
            sql.setInt(2, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Categorias WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public ArrayList<Categorias> getAll() {

        ArrayList<Categorias> listaCategorias = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Categorias";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Categorias Categoria = new Categorias(
                            resultado.getInt("ID"),
                            resultado.getString("nome_categoria")
                    );
                    listaCategorias.add(Categoria);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return listaCategorias;
    }
}
