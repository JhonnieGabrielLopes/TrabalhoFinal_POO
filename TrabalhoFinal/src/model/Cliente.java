package model;

public class Cliente extends Pessoa {
    private String endereco;
    private int id;

    public Cliente(String nome, String cpf, String telefone, String endereco) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
    }

    public Cliente(int id, String nome, String cpf, String telefone, String endereco) {
        super(nome, cpf, telefone);
        this.id = id;
        this.endereco = endereco;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public int getId() {
        return id;
    }
}
