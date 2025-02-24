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
    
}
