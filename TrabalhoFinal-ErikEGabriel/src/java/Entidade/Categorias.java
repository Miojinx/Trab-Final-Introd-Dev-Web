package Entidade;

public class Categorias {

    private int id;
    private String nome_categoria;
    
    public Categorias(){}
    
    public Categorias(int id, String nome_categoria) {
        this.id = id;
        this.nome_categoria = nome_categoria;
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
     * @return the nome_categoria
     */
    public String getNome_categoria() {
        return nome_categoria;
    }

    /**
     * @param nome_categoria the nome_categoria to set
     */
    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

}
