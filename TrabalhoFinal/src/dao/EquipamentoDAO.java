package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Equipamento;

public class EquipamentoDAO {
    public String cadastrarEquipamento (Equipamento equipamento) {
        String sql = "INSERT INTO equipamento (descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, equipamento.getDescricao());
            stmt.setDouble(2, equipamento.getVlrDiaria());
            stmt.setDouble(3, equipamento.getVlrMensal());
            stmt.setInt(4, equipamento.getQtdTotal());
            stmt.executeUpdate();
            return "\nEquipamento cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar equipamento";
        }
    }

    public String alterarEquipamento (int id, int campo, String coluna, String novoValor) {
        String sql = "UPDATE equipamento SET " + coluna + " = ? WHERE id_equip = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (campo == 1) {
                stmt.setString(1, novoValor);
            }else if (campo == 2 || campo == 3) {
                stmt.setDouble(1, Double.parseDouble(novoValor));
            }else if (campo == 4) {
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return "\nEquipamento alterado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao alterar equipamento";
        }
    }

    public ArrayList<Equipamento> listarEquipamentos () {
        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM equipamento order by id_equip ASC";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                equipamentos.add(new Equipamento(rs.getInt("id_equip"), rs.getString("descricao"), rs.getDouble("vlr_diaria"), rs.getDouble("vlr_mensal"), rs.getInt("qtd_total"), rs.getInt("qtd_disponivel")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }
    
    public String deletarEquipamento (int id) {
        String sql = "DELETE FROM equipamento WHERE id_equip = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "\nEquipamento deletado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar equipamento";
        }
    }

    public Equipamento buscarEquipamento (int idEquip) {
        String sql = "SELECT * FROM equipamento WHERE id_equip = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEquip);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Equipamento(rs.getString("descricao"), rs.getDouble("vlr_diaria"), rs.getDouble("vlr_mensal"), rs.getInt("qtd_total"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
