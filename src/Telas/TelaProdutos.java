/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Dao.ProdutoDAO;
import murphygames.Produto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 *
 * @author nicks
 */
public class TelaProdutos extends javax.swing.JFrame {
private ProdutoDAO produtoDAO;
    private javax.swing.JFrame telaAnterior;

    public TelaProdutos() {
        initComponents();
        this.telaAnterior = telaAnterior;
        produtoDAO = new ProdutoDAO();
        configurarAcoes();
        configurarTabela();
        carregarProdutos();
    }

    public TelaProdutos(TelaDashboard telaAnterior) {
    this.telaAnterior = telaAnterior;
    initComponents();
    produtoDAO = new ProdutoDAO();
    configurarAcoes();
    configurarTabela();
    carregarProdutos();
}
  

   private void configurarAcoes() {
        // Ação do botão "Voltar"
        bntVoltar.addActionListener(e -> {
            this.dispose(); 
            if (telaAnterior != null) {
                telaAnterior.setVisible(true); 
            }
        });

        bntAdicionar.addActionListener(e -> adicionarProduto());
        botaoRemover.addActionListener(e -> removerProduto());
    }

   


    private void configurarTabela() {
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setDefaultEditor(Object.class, null); // Bloqueia edição
    }

    private void carregarProdutos() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Limpa a tabela
            
            for (Produto p : produtoDAO.listar()) {
                model.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getPreco(),
                    p.getQuantidade()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void adicionarProduto() {
        try {
            // Validação do nome
            String nome = JOptionPane.showInputDialog(this, "Nome do produto:");
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
                return;
            }

            // Validação do preço
            String precoStr = JOptionPane.showInputDialog(this, "Preço (ex: 59.90):");
            if (precoStr == null || precoStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preço inválido!");
                return;
            }

            // Validação do estoque
            String estoqueStr = JOptionPane.showInputDialog(this, "Estoque inicial:");
            if (estoqueStr == null || estoqueStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Estoque inválido!");
                return;
            }

            // Conversão numérica
            double preco = Double.parseDouble(precoStr.replace(",", "."));
            int estoque = Integer.parseInt(estoqueStr);

            // Cria e salva o produto
            Produto produto = new Produto(0, nome, "", preco, estoque);
            produtoDAO.adicionar(produto);
            
            // Atualiza a tabela
            carregarProdutos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato inválido! Use números.\nEx: Preço = 19.90, Estoque = 50");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro no banco de dados:\n" + e.getMessage());
        }
    }

    private void removerProduto() {
        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela!");
            return;
        }

        try {
            int id = (int) jTable1.getValueAt(linhaSelecionada, 0);
            produtoDAO.remover(id);
            carregarProdutos(); // Atualiza a tabela
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir:\n" + e.getMessage());
        }
    }


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bntAdicionar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        bntVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "PREÇO", "ESTOQUE"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Gerenciamento de Produtos");

        bntAdicionar.setText("ADICIONAR");
        bntAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAdicionarActionPerformed(evt);
            }
        });

        botaoRemover.setText("REMOVER");

        bntVoltar.setText("Voltar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(bntAdicionar)
                            .addGap(69, 69, 69)
                            .addComponent(botaoRemover)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntVoltar))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntAdicionar)
                    .addComponent(botaoRemover)
                    .addComponent(bntVoltar))
                .addGap(15, 15, 15))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bntAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bntAdicionarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAdicionar;
    private javax.swing.JButton bntVoltar;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
