package Entidade;

public class VendasTotais {
    private int idProduto;
    private int vendasTotais;
    private String dataVenda;
    private int vendasDia;

    // Construtor e getters/setters aqui
    public VendasTotais(int idProduto, int vendasTotais, String dataVenda, int vendasDia) {
        this.idProduto = idProduto;
        this.vendasTotais = vendasTotais;
        this.dataVenda = dataVenda;
        this.vendasDia = vendasDia;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getVendasTotais() {
        return vendasTotais;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public int getVendasDia() {
        return vendasDia;
    }
}