package control;

import java.time.LocalDate;

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
    
    public Double calcularValor (Equipamento equipamento, Contrato contrato, LocalDate dataAtual, LocalDate dataFim) {
        double total = 0;
        if (contrato.getTipo() == 1) {
            int mesesContratados = dataAtual.getMonthValue() - dataFim.getMonthValue();
            total = equipamento.getVlrMensal() * mesesContratados;
        } else {
            int diasContratados = dataAtual.getDayOfMonth() - dataFim.getDayOfMonth();
            total = equipamento.getVlrDiaria() * diasContratados;
        }
        return total;
    }

    public Double calcularMulta (double total, double acrescimo) {
        double multa = 0;
        multa=total*acrescimo;
        return multa;
    }
                
    public Double calcularJuros (long diasAtraso, double acrescimo) {
        double juros = 0;
        juros = diasAtraso * acrescimo;
        return juros;
    }

    public Double calcularTotal (double total, double multa, double juros) {
        double totalFinal = total + multa + juros;
        return totalFinal;
    }
}
