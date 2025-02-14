package model;

public class Contrato {
    private int tipo;
    private int qtd_equip;
    private String data_inicio;
    private String data_fim;
    private String data_entrega;
    private String status;

    public Contrato(int tipo, int qtd_equip, String data_inicio, String data_fim, String data_entrega, String status) {
        this.tipo = tipo;
        this.qtd_equip = qtd_equip;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.data_entrega = data_entrega;
        this.status = status;
    }
    public int getTipo() {
        return tipo;
    }
    public int getQtd_equip() {
        return qtd_equip;
    }
    public String getData_inicio() {
        return data_inicio;
    }
    public String getData_fim() {
        return data_fim;
    }
    public String getData_entrega() {
        return data_entrega;
    }
    public String getStatus() {
        return status;
    }
}
