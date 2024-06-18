package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Clientes;
/**
Estrutura da tabela:
* CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
)
* 
 */
public class ClienteDAO implements Dao<Clientes>{
    @Override
    public Clientes get(int id){
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
                }
            }
        }
    }
    
}
