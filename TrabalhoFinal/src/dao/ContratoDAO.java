package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;
import model.Contrato;
import model.Equipamento;

public class ContratoDAO {
    
    public String cadastrarContrato (Contrato contrato) {
        String sql = "INSERT INTO contrato (tipo, id_cliente, id_equip, qtd_equip, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contrato.getTipo());
            stmt.setString(2, ""); //ver como capturar o codigo do cliente
            stmt.setString(3, ""); //ver como capturar o código do equipamento
            stmt.setInt(4, contrato.getQtd_equip());
            stmt.setString(5, contrato.getData_inicio());
            stmt.setString(6, contrato.getData_fim());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return "Contrato realizado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar o Contrato";
        }
    }
}
