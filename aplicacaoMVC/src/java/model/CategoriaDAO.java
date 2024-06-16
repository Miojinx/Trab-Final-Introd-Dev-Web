package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Categoria;

/*
--
-- Estrutura da tabela `categorias`
--

CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


 */
public class CategoriaDAO implements Dao<Categoria> {

    @Override
    public Categoria get(int id) {
        Conexao conexao = new Conexao();
        Categoria categoria = new Categoria();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Categorias WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    categoria.setId(Integer.parseInt(resultado.getString("ID")));
                    categoria.setDescricao(resultado.getString("DESCRICAO"));
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
    public void insert(Categoria t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Categorias (descricao) VALUES (?)");
            sql.setString(1, t.getDescricao());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Categoria t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Categorias SET descricao = ?  WHERE ID = ? ");
            sql.setString(1, t.getDescricao());
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
    public ArrayList<Categoria> getAll() {

        ArrayList<Categoria> meusCategorias = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Categorias";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Categoria Categoria = new Categoria(
                            resultado.getInt("ID"),
                            resultado.getString("Descricao")
                    );
                    meusCategorias.add(Categoria);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusCategorias;
    }
}
