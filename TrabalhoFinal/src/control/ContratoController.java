package control;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.ContratoDAO;
import model.Contrato;

public class ContratoController {
    private ContratoDAO contratoDAO = new ContratoDAO();

    public String cadastrarContrato(int tipo, int idCliente, int idEquip, int qtdEquip, LocalDate dataInicio, LocalDate dataFim) {
        return contratoDAO.cadastrarContrato(tipo, idCliente, idEquip, qtdEquip, dataInicio, dataFim);
    }
}
