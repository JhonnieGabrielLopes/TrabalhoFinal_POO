package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {
    public String CadastrarCliente (Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, telefone, endereco) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.executeUpdate();
            return "Cliente cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar cliente";
        }
    }

    public Cliente verificarCPF (String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"), rs.getString("endereco"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Cliente> listarClientes () {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente order by id_cliente ASC";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"), rs.getString("endereco")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public String alterarCliente (int id, String coluna, String valor) {
        String sql = "UPDATE cliente SET " + coluna + " = ? WHERE id_cliente = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, valor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return "\nCliente alterado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao alterar Cliente";
        }
    }

    public String deletarCliente (String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            return "Cliente deletado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar cliente";
        }
    }
}
