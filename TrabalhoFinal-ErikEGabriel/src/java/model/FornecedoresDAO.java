package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Fornecedores;

public class FornecedoresDAO implements Dao<Fornecedores> {

    @Override
    public Fornecedores get(int id) {
        Conexao conexao = new Conexao();
        Fornecedores fornecedor = new Fornecedores();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Fornecedores WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    fornecedor.setId(Integer.parseInt(resultado.getString("ID")));
                    fornecedor.setRazao_social(resultado.getString("RAZAO_SOCIAL_FORNECERDORES"));
                    fornecedor.setCnpj(resultado.getString("CNPJ_FORNECERDORES"));
                    fornecedor.setEndereco(resultado.getString("ENDERECO_FORNECERDORES"));
                    fornecedor.setBairro(resultado.getString("BAIRRO_FORNECERDORES"));
                    fornecedor.setCidade(resultado.getString("CIDADE_FORNECERDORES"));
                    fornecedor.setUf(resultado.getString("UF_FORNECERDORES"));
                    fornecedor.setCep(resultado.getString("CEP_FORNECERDORES"));
                    fornecedor.setTelefone(resultado.getString("TELEFONE_FORNECERDORES"));
                    fornecedor.setEmail(resultado.getString("EMAIL_FORNECERDORES"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get Fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return fornecedor;
    }
    
    @Override
    public void insert(Fornecedores t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Fornecedores (razao_social, cnpj, endereco, bairro,cidade,uf,cep,telefone,email) VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, t.getRazao_social());
            sql.setString(2, t.getCnpj());
            sql.setString(3, t.getEndereco());
            sql.setString(4, t.getBairro());
            sql.setString(5, t.getCidade());
            sql.setString(6, t.getUf());
            sql.setString(7, t.getCep());
            sql.setString(8, t.getTelefone());
            sql.setString(9, t.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (Fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void update(Fornecedores t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Fornecedores SET razao_social = ?, cnpj = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ?  WHERE ID = ? ");
            sql.setString(1, t.getRazao_social());
            sql.setString(2, t.getCnpj());
            sql.setString(3, t.getEndereco());
            sql.setString(4, t.getBairro());
            sql.setString(5, t.getCidade());
            sql.setString(6, t.getUf());
            sql.setString(7, t.getCep());
            sql.setString(8, t.getTelefone());
            sql.setString(9, t.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar Fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Fornecedores WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir Fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public ArrayList<Fornecedores> getAll() {

        ArrayList<Fornecedores> listaFornecedores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Fornecedores";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Fornecedores fornecedor = new Fornecedores(
                            resultado.getInt("ID"),
                            resultado.getString("razao_social"),
                            resultado.getString("cnpj"),
                            resultado.getString("endereco"),
                            resultado.getString("bairro"),
                            resultado.getString("cidade"),
                            resultado.getString("uf"),
                            resultado.getString("cep"),
                            resultado.getString("telefone"),
                            resultado.getString("email")
                    );
                    listaFornecedores.add(fornecedor);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - Fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return listaFornecedores;
    }
}
