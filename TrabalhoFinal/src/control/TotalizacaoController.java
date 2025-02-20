package control;

import dao.TotalizacaoDAO;
import model.Totalizacao;

public class TotalizacaoController {
    private TotalizacaoDAO totalizacaoDAO = new TotalizacaoDAO();

    public Double calcularTotal (double vlrDiaria, double vlrMensal, double vlrTotal) {

        return vlrTotal;
    }

    public Double calcularMulta () {
        
        return vlrTotal;
    }

    public Double calcularJuros () {
        
        return vlrTotal;
    }

    public String realizarTotalizacao (int contrato, double multa, double juros, double valor) {
        return totalizacaoDAO.Cadastrartotalizacao(contrato, multa, juros, valor);
    }

}
