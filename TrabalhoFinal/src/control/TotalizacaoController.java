package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import dao.TotalizacaoDAO;
import model.Contrato;
import model.Equipamento;
import model.Totalizacao;

public class TotalizacaoController {
    private TotalizacaoDAO totalizacaoDAO = new TotalizacaoDAO();

    public String realizarTotalizacao (int contrato, double valor, double multa, double juros, double total) {
        return totalizacaoDAO.Cadastrartotalizacao(contrato, valor, multa, juros, total);
    }
    
    public Totalizacao buscarTotalizacao (int contrato) {
        return totalizacaoDAO.buscarTotalizacao(contrato);
    }
    
    //remover dessa classe
    public Double calcularValor (int forma, int tipo, Equipamento equipamento, Contrato contrato, LocalDate dataAtual) {
        double total = 0;
        LocalDate dataFim = LocalDate.parse(contrato.getDataFim().substring(0,10));

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataContratoFim = LocalDate.parse(contrato.getDataFim(), formato);
        LocalDate dataContratoInicio = LocalDate.parse(contrato.getDataInicio(), formato);

        if (contrato.getTipo() == 1) { //Calcular o valor do contrato mensal
            long mesesContratados = ChronoUnit.MONTHS.between(dataContratoInicio, dataContratoFim);
            total= (equipamento.getVlrMensal()*mesesContratados)*contrato.getQtdEquip();
        } else if (contrato.getTipo() == 2) { //Calcular o valor do contrato diario
            long diasContratados = ChronoUnit.DAYS.between(dataContratoInicio, dataContratoFim);
            total= (equipamento.getVlrDiaria()*diasContratados)*contrato.getQtdEquip();
        }

        if (forma == 1) { //Calcular o valor na finalização do contrato
            if (dataAtual.isAfter(dataContratoFim)) {
                calcularMulta(forma, contrato.getTipo(), total);
                calcularJuros(contrato.getTipo(), total, dataAtual, dataFim);
            }
        } else if (forma == 2) { //Calcular o valor no cancelamento do contrato
            calcularMulta(forma, contrato.getTipo(), total);
        }

        return total;
    }

    //remover dessa classe
    public Double calcularMulta (int forma, int tipo, double valor) {
        double total = 0;
        if (forma == 1) {
            if (tipo == 1) { // 20% de multa para finalização mensal e 50% para finalização diario
                total = valor*0.20;
            } else {
                total = valor*0.50;
            }
        } else {
            if (tipo == 1) { // 50% de multa para cancelamento mensal e 70% para cancelamento diario
                total = valor*0.50;
            } else {
                total = valor*0.70;
            }
        }
        return total;
    }
                
    //remover dessa classe
    public Double calcularJuros (int tipo, double valor, LocalDate dataAtual, LocalDate dataFim) {
        long diasAtrasados = ChronoUnit.DAYS.between(dataFim, dataAtual);
        double total = 0;
        if (tipo==1) {
            total = ((diasAtrasados*0.01)*valor);
        } else {
            total = ((diasAtrasados*0.02)*valor);
        }
        return total;
    }

    //remover dessa classe
    public Double calcularTotal (double total, double multa, double juros) {
        double totalFinal = total + multa + juros;
        return totalFinal;
    }
}
