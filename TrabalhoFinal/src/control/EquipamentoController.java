package control;

import java.util.ArrayList;
import dao.EquipamentoDAO;
import model.Equipamento;

public class EquipamentoController {
    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

    public String cadastrarEquipamento (String descricao, double vlr_diaria, double vlr_mensal, int qtd_total) {
        Equipamento equipamento = new Equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total);
        return equipamentoDAO.cadastrarEquipamento(equipamento);
    }

    public ArrayList<Equipamento> listarEquipamentos () {
        return equipamentoDAO.listarEquipamentos();
    }

    public String alterarEquipamento (int id, int campo, String novoValor) {
        return equipamentoDAO.alterarEquipamento(id, campo, novoValor);
    }

    public String deletarEquipamento (int id) {
        return equipamentoDAO.deletarEquipamento(id);
    }

}
