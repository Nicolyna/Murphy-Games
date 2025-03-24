/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Dao.ClienteDAO;
import Dao.ProdutoDAO;
import Dao.VendaDAO;
import murphygames.Cliente;
import murphygames.Produto;
import murphygames.Venda;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author nicks
 */
public class TelaVendas extends javax.swing.JFrame {
private javax.swing.JFrame telaAnterior; 
private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;
     public TelaVendas(TelaDashboard telaAnterior) {
        this.telaAnterior = telaAnterior;
        initComponents();
        inicializarDAOs();
        configurarAcoes();
    }

    private void inicializarDAOs() {
        clienteDAO = new ClienteDAO();
        produtoDAO = new ProdutoDAO();
        vendaDAO = new VendaDAO();
    }

    private void configurarAcoes() {
        btnFinalizarVenda.addActionListener(e -> finalizarVenda());
        
        bntVoltar.addActionListener(e -> {
            this.dispose();
            telaAnterior.setVisible(true);
        });
    }

   private void finalizarVenda() {
    try {
        String nomeCliente = txtCliente.getText().trim();
        String nomeProduto = txtProduto.getText().trim();
        int quantidade = Integer.parseInt(txtQuantidade.getText());

        // Validação básica
        if (nomeCliente.isEmpty() || nomeProduto.isEmpty() || quantidade <= 0) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!");
            return;
        }

        Cliente cliente = clienteDAO.buscarPorNome(nomeCliente);
        Produto produto = produtoDAO.buscarPorNome(nomeProduto);

        if (cliente == null || produto == null) {
            JOptionPane.showMessageDialog(this, "Cliente ou produto não encontrado!");
            return;
        }

      
        if (produto.getQuantidade() < quantidade) {
            JOptionPane.showMessageDialog(this, 
                "Estoque insuficiente! Disponível: " + produto.getQuantidade());
            return;
        }

       
        Venda venda = new Venda(cliente, produto, quantidade);
        vendaDAO.registrarVenda(venda);

        JOptionPane.showMessageDialog(this, "Venda registrada!");
        limparCampos();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Quantidade deve ser um número!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, 
            "Erro no banco de dados: " + ex.getMessage());
    }
}

    private void limparCampos() {
        txtCliente.setText("");
        txtProduto.setText("");
        txtQuantidade.setText("");
    }

    /**
     * Creates new form TelaVendas
     */
    public TelaVendas() {
        initComponents();
       this.telaAnterior = telaAnterior;
       btnFinalizarVenda.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnFinalizarVenda.setText("FINALIZAR VENDA");
        btnFinalizarVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtQuantidade.setToolTipText("Quantidade do produto");

        bntVoltar.setText("Voltar para Dashboard");
        bntVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        configurarAcoes();
       
    }

   

    // Restante do código gerado pelo NetBeans (alterar JTextField para JComboBox)
    // ... (substituir txtCliente e txtProduto por JComboBox)

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnFinalizarVenda = new javax.swing.JButton();
        txtQuantidade = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtProduto = new javax.swing.JTextField();
        bntVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Registro de Vendas");

        jLabel2.setText("Cliente");

        jLabel3.setText("Produto ");

        jLabel4.setText("Quantidade");

        btnFinalizarVenda.setText("Finalizar Venda");
        btnFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarVendaActionPerformed(evt);
            }
        });

        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });

        txtProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdutoActionPerformed(evt);
            }
        });

        bntVoltar.setText("Voltar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(bntVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFinalizarVenda)
                .addGap(129, 129, 129))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4))
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizarVenda)
                    .addComponent(bntVoltar))
                .addGap(78, 78, 78))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarVendaActionPerformed
        try {
            String nomeCliente = txtCliente.getText().trim();
            String nomeProduto = txtProduto.getText().trim();
            int quantidade = Integer.parseInt(txtQuantidade.getText());

          
            if (nomeCliente.isEmpty() || nomeProduto.isEmpty() || quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!");
                return;
            }

           
            Cliente cliente = clienteDAO.buscarPorNome(nomeCliente);
            Produto produto = produtoDAO.buscarPorNome(nomeProduto);

            if (cliente == null || produto == null) {
                JOptionPane.showMessageDialog(this, "Cliente ou produto não encontrado!");
                return;
            }

           
            if (produto.getQuantidade() < quantidade) {
                JOptionPane.showMessageDialog(this, 
                    "Estoque insuficiente! Disponível: " + produto.getQuantidade());
                return;
            }

           
            Venda venda = new Venda(cliente, produto, quantidade);
            vendaDAO.registrarVenda(venda);

            JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!");
            limparCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida! Use números inteiros.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao registrar venda: " + ex.getMessage());
        }
      // TODO add your handling code here:
    }//GEN-LAST:event_btnFinalizarVendaActionPerformed

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void txtProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdutoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
     try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntVoltar;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
