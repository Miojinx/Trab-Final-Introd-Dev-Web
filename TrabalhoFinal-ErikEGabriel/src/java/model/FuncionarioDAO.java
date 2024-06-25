package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Funcionario;

public class FuncionarioDAO implements Dao<Funcionario> {

    @Override
    public Funcionario get(int id) {
        Conexao conexao = new Conexao();
        Funcionario funcionario = new Funcionario();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM funcionarios WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    funcionario.setId(Integer.parseInt(resultado.getString("ID")));
                    funcionario.setNome(resultado.getString("NOME_FUNCIONARIO"));
                    funcionario.setCpf(resultado.getString("CPF_FUNCIONARIO"));
                    funcionario.setSenha(resultado.getString("SENHA_FUNCIONARIO"));
                    funcionario.setPapel(resultado.getString("PAPEL_FUNCIONARIO").charAt(0));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return funcionario;
    }
    
    @Override
    public void insert(Funcionario t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO funcionario (nome, cpf, senha, papel) VALUES (?,?,?,?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getSenha());
            sql.setString(4, String.valueOf(t.getPapel()));
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void update(Funcionario t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE funcionario SET nome = ?, cpf = ?, senha = ?, papel = ?  WHERE ID = ? ");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getSenha());
            sql.setString(4, String.valueOf(t.getPapel()));
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Funcionario WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public ArrayList<Funcionario> getAll() {

        ArrayList<Funcionario> listaFuncionarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Funcionario";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Funcionario funcionario = new Funcionario(
                            resultado.getInt("ID"),
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("senha"),
                            resultado.getString("papel").charAt(0)
                    );
                    listaFuncionarios.add(funcionario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - Funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return listaFuncionarios;
    }
    
    public Funcionario Logar(Funcionario funcionario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM funcionarios WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, funcionario.getCpf());
            sql.setString(2, funcionario.getSenha());
            ResultSet resultado = sql.executeQuery();
            Funcionario funcionarioObtido = new Funcionario();
            if (resultado != null) {
                while (resultado.next()) {
                    funcionarioObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    funcionarioObtido.setNome(resultado.getString("NOME"));
                    funcionarioObtido.setCpf(resultado.getString("CPF"));
                    funcionarioObtido.setSenha(resultado.getString("SENHA"));
                    funcionarioObtido.setPapel(resultado.getString("PAPEL").charAt(0));
                }
            }
            return funcionarioObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta",e);
        } finally {
            conexao.closeConexao();
        }
    }
}
