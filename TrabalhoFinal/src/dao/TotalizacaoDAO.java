package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Cliente;
import model.Contrato;
import model.Equipamento;
import model.Totalizacao;

public class TotalizacaoDAO {
    
    public String Cadastrartotalizacao (int contrato, double multa, double juros, double valor) {
        String sql = "INSERT INTO totalizacao (id_contrato, multa, juros, vlr_total) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contrato);
            stmt.setDouble(2, multa);
            stmt.setDouble(3, juros);
            stmt.setDouble(4, valor);
            stmt.executeUpdate();   
            stmt.close();
            conn.close();
            return "Totalização realizada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao realizar a Totalização";
        }
    }

}
