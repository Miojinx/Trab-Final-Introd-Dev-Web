package Entidade;
import java.util.Date;

/**
 *
 * @author User
 */
public class Vendas {
    
    private int id;
    private int quantidade_venda;
    private Date data_venda;
    private float valor_venda;
    private int id_cliente;
    private int id_produto;
    private int id_vendedor;
    
    public Vendas() {
        
    }
    
    public Vendas(int id, int quantidade_venda, Date data_venda, float valor_venda, int id_cliente, int id_produto, int id_vendedor) {
        this.id = id;
        this.quantidade_venda = quantidade_venda;
        this.data_venda = data_venda;
        this.valor_venda = valor_venda;
        this.id_cliente = id_cliente;
        this.id_produto = id_produto;
        this.id_vendedor = id_vendedor;
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

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
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

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
    
    
}
