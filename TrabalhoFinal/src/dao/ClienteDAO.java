package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            stmt.close();
            conn.close();
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
                return new Cliente(rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"), rs.getString("endereco"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public String deletarCliente (String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return "Cliente deletado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao deletar cliente";
        }
    } 
}
