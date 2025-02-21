package model;

public class Equipamento implements ExibirDetalhes {
    private int id;
    private String descricao;
    private double vlrDiaria;
    private double vlrMensal;
    private int qtdTotal;
    private int qtdDisponivel;

    public Equipamento(String descricao, double vlrDiaria, double vlrMensal, int qtdTotal) {
        this.descricao = descricao;
        this.vlrDiaria = vlrDiaria;
        this.vlrMensal = vlrMensal;
        this.qtdTotal = qtdTotal;
    }

    public Equipamento(int id, String descricao, double vlrDiaria, double vlrMensal, int qtdTotal, int qtdDisponivel) {
        this.id = id;
        this.descricao = descricao;
        this.vlrDiaria = vlrDiaria;
        this.vlrMensal = vlrMensal;
        this.qtdTotal = qtdTotal;
        this.qtdDisponivel = qtdDisponivel;
    }
    public int getIdEquip() {
        return id;
    }   
    public String getDescricao() {
        return descricao;
    }
    public double getVlrDiaria() {
        return vlrDiaria;
    }
    public double getVlrMensal() {
        return vlrMensal;
    }
    public int getQtdTotal() {
        return qtdTotal;
    }
    public int getQtdDisponivel() {
        return qtdDisponivel;
    }
    
    @Override
    public String exibirDetalhes() {
        return "ID: " + id + "\nDescrição: " + descricao + "\nValor diário: R$" + vlrDiaria + "\nValor mensal: R$" + vlrMensal + "\nQuantidade total: " + qtdTotal + "\nQuantidade disponível: " + qtdDisponivel;
    }
}
