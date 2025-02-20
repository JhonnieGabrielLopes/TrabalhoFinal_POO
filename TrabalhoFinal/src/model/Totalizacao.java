package model;

public class Totalizacao {
    private int contrato;
    private double multa;
    private double juros;
    private double valor;

    public Totalizacao(int contrato, double multa, double juros, double valor) {
        this.contrato = contrato;
        this.multa = multa;
        this.juros = juros;
        this.valor = valor;
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

}
