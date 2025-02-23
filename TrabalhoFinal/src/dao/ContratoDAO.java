package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Contrato;

public class ContratoDAO {
    
    public String cadastrarContrato (int tipo, int idCliente, int idEquip, int qtdEquip, LocalDate dataInicio, LocalDate dataFim) {
        String sql = "INSERT INTO contrato (tipo, id_cliente, id_equip, qtd_equip, data_inicio, data_fim, data_entrega, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tipo);
            stmt.setInt(2, idCliente);
            stmt.setInt(3, idEquip);
            stmt.setInt(4, qtdEquip);
            stmt.setDate(5, Date.valueOf(dataInicio));
            stmt.setDate(6, Date.valueOf(dataFim));
            stmt.setNull(7, java.sql.Types.DATE);
            stmt.setString(8, "A");
            stmt.executeUpdate();   
            return "Contrato realizado com sucesso!\n";
        } catch (SQLException e) {
            return "Erro ao cadastrar o Contrato\n";
        }
    }

    public Contrato buscarUltimoContrato () {
        String sql = "SELECT * " +
                     "FROM contrato "+
                     "ORDER BY id_contrato DESC " + 
                     "LIMIT 1";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Contrato(rs.getInt("tipo"), rs.getInt("qtd_equip"), rs.getString("data_inicio"), 
                rs.getString("data_fim"), rs.getString("data_entrega"), rs.getString("status"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao obter o Último Contrato Cadastrado");
            return null;
        }
    }

    public Contrato buscarContrato (int id) {
        String sql = "SELECT c.id_contrato, c.tipo, c.id_cliente, cl.nome AS nome_cliente, " +
                "c.id_equip, e.descricao AS descricao_equipamento, " +
                 "c.qtd_equip, c.data_inicio, c.data_fim, c.data_entrega, c.status " +
                 "FROM contrato c " +
                 "JOIN cliente cl ON c.id_cliente = cl.id_cliente " +
                 "JOIN equipamento e ON c.id_equip = e.id_equip " +
                 "WHERE id_contrato = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Contrato(rs.getInt("id_contrato"), rs.getInt("id_cliente"), 
                rs.getString("nome_cliente"), rs.getInt("id_equip"), rs.getString("descricao_equipamento"), 
                rs.getInt("tipo"), rs.getInt("qtd_equip"), rs.getString("data_inicio"), 
                rs.getString("data_fim"), rs.getString("data_entrega"), rs.getString("status"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Contrato> listarContratoAtivo () {
        ArrayList<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT c.id_contrato, c.tipo, c.id_cliente, cl.nome AS nome_cliente, " +
                "c.id_equip, e.descricao AS descricao_equipamento, " +
                 "c.qtd_equip, c.data_inicio, c.data_fim, c.data_entrega, c.status " +
                 "FROM contrato c " +
                 "JOIN cliente cl ON c.id_cliente = cl.id_cliente " +
                 "JOIN equipamento e ON c.id_equip = e.id_equip " +
                 "WHERE c.status = 'A'";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contratos.add(new Contrato(rs.getInt("id_contrato"), rs.getInt("id_cliente"), 
                rs.getString("nome_cliente"), rs.getInt("id_equip"), rs.getString("descricao_equipamento"), 
                rs.getInt("tipo"), rs.getInt("qtd_equip"), rs.getString("data_inicio"), 
                rs.getString("data_fim"), rs.getString("data_entrega"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }

    public ArrayList<Contrato> listarContratoEncerrado () {
        ArrayList<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT c.id_contrato, c.tipo, c.id_cliente, cl.nome AS nome_cliente, " +
                "c.id_equip, e.descricao AS descricao_equipamento, " +
                 "c.qtd_equip, c.data_inicio, c.data_fim, c.data_entrega, c.status " +
                 "FROM contrato c " +
                 "JOIN cliente cl ON c.id_cliente = cl.id_cliente " +
                 "JOIN equipamento e ON c.id_equip = e.id_equip " +
                 "WHERE c.status = 'F' or c.status = 'C'" + 
                 "ORDER BY c.id_contrato ASC";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contratos.add(new Contrato(rs.getInt("id_contrato"), rs.getInt("id_cliente"), 
                rs.getString("nome_cliente"), rs.getInt("id_equip"), rs.getString("descricao_equipamento"), 
                rs.getInt("tipo"), rs.getInt("qtd_equip"), rs.getString("data_inicio"), 
                rs.getString("data_fim"), rs.getString("data_entrega"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }

    public ArrayList<Contrato> listarContrato () {
        ArrayList<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT c.id_contrato, c.tipo, c.id_cliente, cl.nome AS nome_cliente, " +
                "c.id_equip, e.descricao AS descricao_equipamento, " +
                 "c.qtd_equip, c.data_inicio, c.data_fim, c.data_entrega, c.status " +
                 "FROM contrato c " +
                 "JOIN cliente cl ON c.id_cliente = cl.id_cliente " +
                 "JOIN equipamento e ON c.id_equip = e.id_equip ";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contratos.add(new Contrato(rs.getInt("id_contrato"), rs.getInt("id_cliente"), 
                rs.getString("nome_cliente"), rs.getInt("id_equip"), rs.getString("descricao_equipamento"), 
                rs.getInt("tipo"), rs.getInt("qtd_equip"), rs.getString("data_inicio"), 
                rs.getString("data_fim"), rs.getString("data_entrega"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }

    public String encerrarContrato (int id, String forma, LocalDate dataAtual) {
        String sql = "UPDATE contrato SET data_entrega = ?, status = ? WHERE id_contrato = ?";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(dataAtual));
            stmt.setString(2, forma);
            stmt.setInt(3, id);
            stmt.executeUpdate();   
            return "Contrato encerrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao encerrar o Contrato";
        }
    }

    public boolean verificarEquipamentoEmContratoAtivo (int idEquip) {
        String sql = "SELECT COUNT(*) FROM contrato WHERE id_equip = ? AND status = 'A'";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEquip);
            ResultSet rs = stmt.executeQuery();   
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return false;
    }

    public boolean verificarClienteEmContratoAtivo (int id) {
        String sql = "SELECT COUNT(*) FROM contrato WHERE id_cliente = ? AND status = 'A'";
        try (Connection conn = ConexaoDAO.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();   
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return false;
    }
}
