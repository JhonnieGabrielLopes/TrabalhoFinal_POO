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

    public String encerrarContrato(int id, String forma, LocalDate dataAtual) {
        return contratoDAO.encerrarContrato(id, forma, dataAtual);
    }

    public ArrayList<Contrato> listarContratoAtivo() {
        return contratoDAO.listarContratoAtivo();
    }

    public ArrayList<Contrato> listarContratoEncerrado() {
        return contratoDAO.listarContratoEncerrado();
    }

    public ArrayList<Contrato> listarContrato() {
        return contratoDAO.listarContrato();
    }

    public Contrato buscarContrato(int id) {
        return contratoDAO.buscarContrato(id);
    }

    public boolean verificarEquipamentoEmContratoAtivo (int idEquip) {
        return contratoDAO.verificarEquipamentoEmContratoAtivo(idEquip);
    }

    public boolean verificarClienteEmContratoAtivo (int id) {
        return contratoDAO.verificarClienteEmContratoAtivo(id);
    }

    public Contrato  ultimoContrato () {
        return contratoDAO.buscarUltimoContrato();
    }

}
