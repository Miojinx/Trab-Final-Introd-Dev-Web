package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Clientes;

/**
 * Estrutura da tabela: CREATE TABLE `clientes` ( `id` int(11) NOT NULL
 * AUTO_INCREMENT, `nome` varchar(50) NOT NULL, `cpf` varchar(14) NOT NULL,
 * `endereco` varchar(50) NOT NULL, `bairro` varchar(50) NOT NULL, `cidade`
 * varchar(50) NOT NULL, `uf` varchar(2) NOT NULL, `cep` varchar(8) NOT NULL,
 * `telefone` varchar(20) NOT NULL, `email` varchar(50) NOT NULL, PRIMARY KEY
 * (`id`) )
 *
 */
public class ClienteDAO implements Dao<Clientes> {

    @Override
    public Clientes get(int id) {
        Conexao conexao = new Conexao();
        Clientes cliente = new Clientes();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM clientes WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    cliente.setId(Integer.parseInt(resultado.getString("ID")));
                    cliente.setNome(resultado.getString("NOME"));
                    cliente.setCpf(resultado.getString("CPF"));
                    cliente.setEndereco(resultado.getString("ENDERECO"));
                    cliente.setBairro(resultado.getString("BAIRRO"));
                    cliente.setCidade(resultado.getString("CIDADE"));
                    cliente.setUf(resultado.getString("UF"));
                    cliente.setCep(resultado.getString("CEP"));
                    cliente.setTelefone(resultado.getString("TELEFONE"));
                    cliente.setEmail(resultado.getString("EMAIL"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return cliente;
    }

    @Override
    public void insert(Clientes t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Clientes (nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getEndereco());
            sql.setString(4, t.getBairro());
            sql.setString(5, t.getCidade());
            sql.setString(6, t.getUf());
            sql.setString(7, t.getCep());
            sql.setString(8, t.getTelefone());
            sql.setString(9, t.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (clientes) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Clientes t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE clientes SET nome = ?, cpf = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? WHERE ID = ? ");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getEndereco());
            sql.setString(4, t.getBairro());
            sql.setString(5, t.getCidade());
            sql.setString(6, t.getUf());
            sql.setString(7, t.getCep());
            sql.setString(8, t.getTelefone());
            sql.setString(9, t.getEmail());
            sql.setInt(10, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM clientes WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Clientes> getAll() {
        ArrayList<Clientes> listaClientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM clientes order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Clientes cliente = new Clientes(
                            resultado.getInt("ID"),
                            resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("BAIRRO"),
                            resultado.getString("CIDADE"),
                            resultado.getString("UF"),
                            resultado.getString("CEP"),
                            resultado.getString("TELEFONE"),
                            resultado.getString("EMAIL")
                    );
                    listaClientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (listaClientes) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return listaClientes;
    }
}
