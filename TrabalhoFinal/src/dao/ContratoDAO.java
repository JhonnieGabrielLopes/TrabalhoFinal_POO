package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Cliente;
import model.Contrato;
import model.Equipamento;

public class ContratoDAO {
    
    public String cadastrarContrato (int tipo, int idCliente, int idEquip, int qtdEquip, LocalDate dataInicio, LocalDate dataFim) {
        String sql = "INSERT INTO contrato (tipo, id_cliente, id_equip, qtd_equip, data_inicio, data_fim, data_entrega, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tipo);
            stmt.setInt(2, idCliente); //ver como capturar o codigo do cliente
            stmt.setInt(3, idEquip); //ver como capturar o código do equipamento
            stmt.setInt(4, qtdEquip);
            stmt.setDate(5, Date.valueOf(dataInicio));
            stmt.setDate(6, Date.valueOf(dataFim));
            stmt.setNull(7, java.sql.Types.DATE);
            stmt.setString(8, "A");
            stmt.executeUpdate();   
            stmt.close();
            conn.close();
            return "Contrato realizado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar o Contrato";
        }
    }

    public String listarContratoAtivo () {
        String sql = "SELECT * FROM contrato WHERE status = 'A'";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "rs.getInt(\"id_contrato\"), rs.getInt(\"tipo\"), rs.getInt(\"id_cliente\"), rs.getInt(\"id_equip\"), rs.getInt(\"qtd_equip\"), rs.getDate(\"data_inicio\").toLocalDate(), rs.getDate(\"data_fim\").toLocalDate(), rs.getDate(\"data_entrega\").toLocalDate(), rs.getString(\"status\")";
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public String encerrarContrato (int id, String forma, LocalDate dataAtual) {
        String sql = "UPDATE contrato SET data_entrega = ?, status = ? WHERE id_contrato = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(dataAtual));
            stmt.setString(2, forma);
            stmt.setInt(3, id);
            stmt.executeUpdate();   
            stmt.close();
            conn.close();
            return "Contrato encerrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao encerrar o Contrato";
        }
    }
}
