package model;

public class Equipamento implements ExibirDetalhes {
    private int id;
    private String descricao;
    private double vlr_diaria;
    private double vlr_mensal;
    private int qtd_total;
    private int qtd_disponivel;

    public Equipamento(String descricao, double vlr_diaria, double vlr_mensal, int qtd_total) {
        this.descricao = descricao;
        this.vlr_diaria = vlr_diaria;
        this.vlr_mensal = vlr_mensal;
        this.qtd_total = qtd_total;
    }

    public Equipamento(int id, String descricao, double vlr_diaria, double vlr_mensal, int qtd_total, int qtd_disponivel) {
        this.id = id;
        this.descricao = descricao;
        this.vlr_diaria = vlr_diaria;
        this.vlr_mensal = vlr_mensal;
        this.qtd_total = qtd_total;
        this.qtd_disponivel = qtd_disponivel;
    }
    public int getId_equip() {
        return id;
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
    
    @Override
    public String exibirDetalhes() {
        return "ID: " + id + "\nDescrição: " + descricao + "\nValor diário: " + vlr_diaria + "\nValor mensal: " + vlr_mensal + "\nQuantidade total: " + qtd_total + "\nQuantidade disponível: " + qtd_disponivel;
    }
}
