package model;

public class Cliente extends Pessoa {
    private String endereco;

    public Cliente(String nome, String cpf, String telefone, String endereco) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
    }
    
    public String getEndereco() {
        return endereco;
    }
}
