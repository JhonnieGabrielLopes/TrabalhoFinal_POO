package control;

import java.util.ArrayList;

import dao.ContratoDAO;
import model.Contrato;

public class ContratoController {
    private ContratoDAO contratoDAO = new ContratoDAO();

    public String cadastrarContrato(int tipo, int idCliente, int idEquip, int qtdEquip, String dataInicio, String dataFim) {
        Contrato contrato = new Contrato(tipo, qtdEquip, dataInicio, dataFim, null, "A");
        return contratoDAO.cadastrarContrato(contrato);
    }
}
