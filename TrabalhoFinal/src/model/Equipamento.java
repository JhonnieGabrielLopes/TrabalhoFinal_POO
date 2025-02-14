package model;

public class Equipamento {
    private String descricao;
    private double vlr_diaria;
    private double vlr_mensal;
    private int qtd_total;
    private int qtd_disponivel;

    public Equipamento(String descricao, double vlr_diaria, double vlr_mensal, int qtd_total, int qtd_disponivel) {
        this.descricao = descricao;
        this.vlr_diaria = vlr_diaria;
        this.vlr_mensal = vlr_mensal;
        this.qtd_total = qtd_total;
        this.qtd_disponivel = qtd_disponivel;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getVlr_diaria() {
        return vlr_diaria;
    }
    public double getVlr_mensal() {
        return vlr_mensal;
    }
    public int getQtd_total() {
        return qtd_total;
    }
    public int getQtd_disponivel() {
        return qtd_disponivel;
    }
}
