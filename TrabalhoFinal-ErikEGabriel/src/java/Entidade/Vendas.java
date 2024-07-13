package Entidade;
import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Vendas {
    
    private int id;
    private int quantidade_venda;
    private String data_venda;
    private float valor_venda;
    private int id_cliente;
    private int id_produto;
    private int id_funcionario;
    
    public Vendas() {
        
    }
    
    public Vendas(int id, int quantidade_venda, String data_venda, float valor_venda, int id_cliente, int id_produto, int id_funcionario) {
        this.id = id;
        this.quantidade_venda = quantidade_venda;
        this.data_venda = data_venda;
        this.valor_venda = valor_venda;
        this.id_cliente = id_cliente;
        this.id_produto = id_produto;
        this.id_funcionario = id_funcionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade_venda() {
        return quantidade_venda;
    }

    public void setQuantidade_venda(int quantidade_venda) {
        this.quantidade_venda = quantidade_venda;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }    
}
