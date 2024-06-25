package Entidade;

/**
 *
 * @author User
 */
public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private char papel;
    
    public Funcionario(){}
    
    public Funcionario(int id, String nome, String cpf, String senha, char papel) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.papel = papel;
    }
    
     public Funcionario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public char getPapel() {
        return papel;
    }

    public void setPapel(char papel) {
        this.papel = papel;
    }
  
}
