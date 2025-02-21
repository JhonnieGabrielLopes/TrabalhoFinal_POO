package control;

import java.util.ArrayList;
import dao.EquipamentoDAO;
import model.Equipamento;

public class EquipamentoController {
    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

    public String cadastrarEquipamento (String descricao, double vlrDiaria, double vlrMensal, int qtdTotal) {
        Equipamento equipamento = new Equipamento(descricao, vlrDiaria, vlrMensal, qtdTotal);
        return equipamentoDAO.cadastrarEquipamento(equipamento);
    }

    public ArrayList<Equipamento> listarEquipamentos () {
        return equipamentoDAO.listarEquipamentos();
    }

    public String alterarEquipamento (int id, int campo, String coluna, String novoValor) {
        return equipamentoDAO.alterarEquipamento(id, campo, coluna, novoValor);
    }

    public String deletarEquipamento (int id) {
        return equipamentoDAO.deletarEquipamento(id);
    }

    public Equipamento buscarEquipamento (int id) {
        return equipamentoDAO.buscarEquipamento(id);
    }
}
