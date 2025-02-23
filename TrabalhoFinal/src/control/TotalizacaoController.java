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
    
    public Double calcularValor (Equipamento equipamento, Contrato contrato, LocalDate dataAtual) {
        double total = 0;
        LocalDate dataFim = LocalDate.parse(contrato.getDataFim().substring(0,10));

        if (dataAtual.isAfter(dataFim)) {
            if (contrato.getTipo() == 1) {
                long mesesContratados = ChronoUnit.MONTHS.between(dataFim, dataAtual);
                total = equipamento.getVlrMensal() * mesesContratados;
                total *= contrato.getQtdEquip();
            } else {
                long diasContratados = ChronoUnit.DAYS.between(dataFim, dataAtual);
                total = equipamento.getVlrDiaria() * diasContratados;
                total *= contrato.getQtdEquip();
            } 
        } else if (dataAtual.isBefore(dataFim)) {
            if (contrato.getTipo() == 1) {
                long mesesContratados = ChronoUnit.MONTHS.between(dataAtual, dataFim);
                total = equipamento.getVlrMensal() * mesesContratados;
                total *= contrato.getQtdEquip();
            } else {
                long diasContratados = ChronoUnit.DAYS.between(dataAtual, dataFim);
                total = equipamento.getVlrDiaria() * diasContratados;
                total *= contrato.getQtdEquip();
            }
        } else {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataContrato = LocalDate.parse(contrato.getDataInicio(), formato);
            if (contrato.getTipo() == 1) {
                long diasUtilizados = ChronoUnit.DAYS.between(dataContrato, dataAtual);
                double vlrDiariaMes = equipamento.getVlrMensal()/30;
                total = vlrDiariaMes * diasUtilizados;
            } else {
                long diasContratados = ChronoUnit.DAYS.between(dataContrato, dataAtual);
                total = equipamento.getVlrDiaria() * diasContratados;
            }
        }

        return total;
    }

    public Double calcularMulta (int forma, double valor) {
        double total = 0;
        if (forma == 1) {
            total = valor*0.20; 
        } else {
            total = valor*0.50;
        }
        return total;
    }
                
    public Double calcularJuros (int forma, double valor, LocalDate dataAtual, LocalDate dataFim) {
        long diasAtrasados = ChronoUnit.DAYS.between(dataFim, dataAtual);
        double total = 0;
        if (forma==1) {
            total = ((diasAtrasados*0.10)*valor);
        } else {
            total = ((diasAtrasados*0.20)*valor);
        }
        return total;
    }

    public Double calcularTotal (double total, double multa, double juros) {
        double totalFinal = total + multa + juros;
        return totalFinal;
    }
}
