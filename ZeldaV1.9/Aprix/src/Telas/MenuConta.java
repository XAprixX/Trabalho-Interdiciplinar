package Telas;

import Cliente.Cliente;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaComBanco.Conexao;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class MenuConta extends javax.swing.JPanel {

    private TelaInicial1 frame;

    private String cpf;

    public MenuConta(TelaInicial1 frame, String _cpf) {
        initComponents();
        this.frame = frame;
        this.cpf = _cpf;

        //Passar essa parte para outra classe .java
        try {

            Conexao c = new Conexao();
            Connection conexao = c.obterConexao();

            String SQL = ("SELECT * FROM aprix.cliente"
                    + " where cpf = '" + this.cpf + "'");

            PreparedStatement pstm = conexao.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            //passar a query entre parenteses. stmt.executeQuery(SQL) por exemplo : String SQL = "select * from tabela where cpf = '"+ cpfInformado +"'
            if (rs == null) {
                System.out.println("ResultSet null");
            }

            String nome = "", saldo = "", conta = "";

            while (rs.next()) {

                //165.356.777-44  cpf de login admin
                nome = rs.getString("nome");
                if (saldo.equalsIgnoreCase("")) {
                    saldo = "0";
                }
                saldo = String.format("%.2f", rs.getDouble("valor_conta"));
                conta = rs.getString("conta");

            }

            //Set dos Jlabel no frame
            this.NomeMenu.setText(nome);
            this.SaldoMenu.setText(saldo + " R$");
            this.ContaMenu.setText(conta);

        } catch (SQLException ex) {

            System.out.print("Erro: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    }

    MenuConta(TelaInicial1 frame) {
        initComponents();
        this.frame = frame;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        try {
            //carregando a imagem de fundo
            Image imagens;
            imagens = ImageIO.read(
                    new File(getClass().
                            getResource("/imagens/Tela2.png").
                            getFile()));

            //redimencionamento da imagem
            imagens = imagens.getScaledInstance(310, 611,
                    Image.SCALE_DEFAULT);

            //alocando a imagem no painel
            g.drawImage(imagens, 0, 0, this);

        } catch (IOException ex) {
            System.err.println("o arquivo esta corrompido");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        voltar = new javax.swing.JButton();
        NomeMenu = new javax.swing.JLabel();
        ContaMenu = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        SaldoMenu = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(310, 600));
        setPreferredSize(new java.awt.Dimension(310, 600));

        voltar.setBackground(new java.awt.Color(255, 255, 255));
        voltar.setText("voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        NomeMenu.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        NomeMenu.setText("............................................");

        ContaMenu.setText("............");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Saldo");

        SaldoMenu.setText("............");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Realizar :");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("     Transferencia");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaldoMenu)
                        .addGap(129, 129, 129))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ContaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(NomeMenu)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(NomeMenu)
                .addGap(38, 38, 38)
                .addComponent(ContaMenu)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(SaldoMenu))
                .addGap(56, 56, 56)
                .addComponent(jLabel12)
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        {
            frame.trocarPainel(new TransTela(frame, cpf));

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        frame.trocarPainel(new Inicio(frame));
    }//GEN-LAST:event_voltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ContaMenu;
    private javax.swing.JLabel NomeMenu;
    private javax.swing.JLabel SaldoMenu;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables

}
