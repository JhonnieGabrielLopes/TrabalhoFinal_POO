package model;

public class Totalizacao implements ExibirDetalhes{
    private int id;
    private int contrato;
    private double valor;
    private double multa;
    private double juros;
    private double total;

    public Totalizacao(int contrato, double valor, double multa, double juros, double total) {
        this.contrato = contrato;
        this.valor = valor;
        this.multa = multa;
        this.juros = juros;
        this.total = total;
    }
    public Totalizacao(int id, int contrato, double valor, double multa, double juros, double total) {
        this.id = id;
        this.contrato = contrato;
        this.valor = valor;
        this.multa = multa;
        this.juros = juros;
        this.total = total;
    }
    public int getId() {
        return id;
    }
    public int getContrato() {
        return contrato;
    }
    public double getMulta() {
        return multa;
    }
    public double getJuros() {
        return juros;
    }
    public double getValor() {
        return valor;
    }
    public double getTotal() {
        return total;
    }
    @Override
    public String exibirDetalhes() {
        return "Identificação de Totalização: " + id + "\nContrato: " + contrato + "\nValor: R$" + valor + "\nMulta: R$" + multa + "\nJuros: R$" + juros + "\nTotal: R$" + total;
    }
}
