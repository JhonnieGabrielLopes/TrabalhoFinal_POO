package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Totalizacao;

public class TotalizacaoDAO {
    
    public String Cadastrartotalizacao (int contrato, double valor, double multa, double juros, double total) {
        String sql = "INSERT INTO totalizacao (id_contrato, valor, multa, juros, vlr_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contrato);
            stmt.setDouble(2, valor);
            stmt.setDouble(3, multa);
            stmt.setDouble(4, juros);
            stmt.setDouble(5, total);
            stmt.executeUpdate();   
            return "Totalização realizada com sucesso!\n";
        } catch (SQLException e) {
            return "Erro ao realizar a Totalização";
        }
    }

    public Totalizacao buscarTotalizacao (int contrato) {
        String sql = "SELECT * FROM totalizacao where id_contrato = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contrato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Totalizacao(rs.getInt("id_tot"), rs.getInt("id_contrato"), rs.getDouble("valor"), rs.getDouble("multa"), rs.getDouble("juros"), rs.getDouble("vlr_total"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
