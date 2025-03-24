/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import murphygames.Venda;
import java.sql.*;

public class VendaDAO {
    public void registrarVenda(Venda venda) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexaoDAO.getConnection();
            conn.setAutoCommit(false);

           
            int vendaId = inserirVenda(conn, venda);
            
            
            inserirItemVenda(conn, vendaId, venda);
            
           
            atualizarEstoque(conn, venda);
            
            conn.commit();
        } catch (SQLException ex) {
            if (conn != null) conn.rollback();
            throw ex;
        } finally {
            fecharConexao(conn);
        }
    }

    private int inserirVenda(Connection conn, Venda venda) throws SQLException {
        String sql = "INSERT INTO Venda (cliente_id, data) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, venda.getCliente().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(venda.getData()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
    }

    private void inserirItemVenda(Connection conn, int vendaId, Venda venda) throws SQLException {
        String sql = "INSERT INTO Venda_Produto (venda_id, produto_id, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendaId);
            stmt.setInt(2, venda.getProduto().getId());
            stmt.setInt(3, venda.getQuantidade());
            stmt.executeUpdate();
        }
    }

    private void atualizarEstoque(Connection conn, Venda venda) throws SQLException {
        String sql = "UPDATE Produto SET quantidade = quantidade - ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getQuantidade());
            stmt.setInt(2, venda.getProduto().getId());
            stmt.executeUpdate();
        }
    }

    private void fecharConexao(Connection conn) {
        try {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao fechar conexão: " + ex.getMessage());
        }
    }
}