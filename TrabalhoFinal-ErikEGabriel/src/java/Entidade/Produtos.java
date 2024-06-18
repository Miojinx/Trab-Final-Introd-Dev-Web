package Entidade;

public class Produtos {

    private int id;
    private String nome_produto;
    private String descricao;
    private float preco_compra;
    private float preco_venda;
    private int quantidade_disponivel;
    private char liberado_venda;
    private int id_categoria;

    public Produtos(){}
    
    public Produtos(int id, String nome_produto, String descricao, float preco_compra, float preco_venda, int quantidade_disponivel, char liberado_venda, int id_categoria) {
        this.id = id;
        this.nome_produto = nome_produto;
        this.descricao = descricao;
        this.preco_compra = preco_compra;
        this.preco_venda = preco_venda;
        this.quantidade_disponivel = quantidade_disponivel;
        this.liberado_venda = liberado_venda;
        this.id_categoria = id_categoria;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome_produto
     */
    public String getNome_produto() {
        return nome_produto;
    }

    /**
     * @param nome_produto the nome_produto to set
     */
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the preco_compra
     */
    public float getPreco_compra() {
        return preco_compra;
    }

    /**
     * @param preco_compra the preco_compra to set
     */
    public void setPreco_compra(float preco_compra) {
        this.preco_compra = preco_compra;
    }

    /**
     * @return the preco_venda
     */
    public float getPreco_venda() {
        return preco_venda;
    }

    /**
     * @param preco_venda the preco_venda to set
     */
    public void setPreco_venda(float preco_venda) {
        this.preco_venda = preco_venda;
    }

    /**
     * @return the quantidade_disponivel
     */
    public int getQuantidade_disponivel() {
        return quantidade_disponivel;
    }

    /**
     * @param quantidade_disponivel the quantidade_disponivel to set
     */
    public void setQuantidade_disponivel(int quantidade_disponivel) {
        this.quantidade_disponivel = quantidade_disponivel;
    }

    /**
     * @return the liberado_venda
     */
    public char getLiberado_venda() {
        return liberado_venda;
    }

    /**
     * @param liberado_venda the liberado_venda to set
     */
    public void setLiberado_venda(char liberado_venda) {
        this.liberado_venda = liberado_venda;
    }

    /**
     * @return the id_categoria
     */
    public int getId_categoria() {
        return id_categoria;
    }

    /**
     * @param id_categoria the id_categoria to set
     */
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

}
