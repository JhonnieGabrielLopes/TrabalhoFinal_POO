package control;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public String CadastrarCliente (String nome, String cpf, String telefone, String endereco) {
        Cliente cliente = new Cliente(nome, cpf, telefone, endereco);
        return clienteDAO.CadastrarCliente(cliente);
    }

    public Cliente verificarCPF (String cpf) {
        return clienteDAO.verificarCPF(cpf);
    }

    public String deletarCliente (String cpf) {
        return clienteDAO.deletarCliente(cpf);
    }
}
