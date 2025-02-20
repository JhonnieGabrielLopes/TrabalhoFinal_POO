package model;

public class Contrato implements ExibirDetalhes{
    private int id;
    private int idCliente;
    private int idEquip;
    private String nomeCliente;
    private String DescEquipamento;
    private int tipo;
    private int qtdEquip;
    private String dataInicio;
    private String dataFim;
    private String dataEntrega;
    private String status;

    public Contrato(int tipo, int qtdEquip, String dataInicio, String dataFim, String dataEntrega, String status) {
        this.tipo = tipo;
        this.qtdEquip = qtdEquip;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }
    public Contrato(int id, int idCliente, String nomeCliente, int idEquip, String DescEquipamento, int tipo, int qtdEquip, String dataInicio, String dataFim, String dataEntrega, String status) {
        this.id = id;
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.idEquip = idEquip;
        this.DescEquipamento = DescEquipamento;
        this.tipo = tipo;
        this.qtdEquip = qtdEquip;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    public int getTipo() {
        return tipo;
    }
    public int getQtdEquip() {
        return qtdEquip;
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public String getDataFim() {
        return dataFim;
    }
    public String getDataEntrega() {
        return dataEntrega;
    }
    public String getStatus() {
        return status;
    }
    public int getId() {
        return id;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public int getIdEquip() {
        return idEquip;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public String getDescEquipamento() {
        return DescEquipamento;
    }

    public String exibirDetalhes() {
        return "ID de Contrato: " + id + "\nID Cliente: " + idCliente + "   Nome Cliente: " + nomeCliente + "\nID Equipamento: " + idEquip + "  Descrição Equipamento: " + DescEquipamento + "\nTipo: " + tipo + "\nQuantidade de Equipamentos: " + qtdEquip + "\nData de Início: " + dataInicio + "\nData de Fim: " + dataFim + "\nData de Entrega: " + dataEntrega + "\nStatus do contrato: " + status;
    }
}
