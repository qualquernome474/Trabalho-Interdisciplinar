/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEW;

import CONTROL.ClienteControl;
import MODEL.Cliente;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author 0068952
 */
public class PCadastro extends javax.swing.JPanel {

    private static ClienteControl cliente=new ClienteControl();
    /**
     * Creates new form PCadastro
     */
    public PCadastro() {
        initComponents();
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage imagem = ImageIO.read(getClass().getResource("/RESOURCES/pcadrastroclientes.PNG"));//por o nome da tela que vai estar em resouce
            
            
            
            g.drawImage(imagem, 0, 0, this);
            
            

        } catch (IOException ex) {
            System.err.println("arquivo não existe no resource");
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

        CPF = new javax.swing.JFormattedTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPasswordField1 = new javax.swing.JPasswordField();
        endereco = new javax.swing.JTextField();
        nome = new javax.swing.JTextField();
        FazerCadastro = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(768, 432));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CPF.setForeground(new java.awt.Color(255, 51, 51));
        try {
            CPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        CPF.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        add(CPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 187, -1));

        jCheckBox1.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 51, 51));
        jCheckBox1.setText("Mostrar Senha");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        jPasswordField1.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(255, 51, 51));
        add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 182, -1));

        endereco.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        endereco.setForeground(new java.awt.Color(255, 51, 51));
        endereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enderecoActionPerformed(evt);
            }
        });
        add(endereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 187, -1));

        nome.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        nome.setForeground(new java.awt.Color(255, 51, 51));
        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });
        add(nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 187, -1));

        FazerCadastro.setBackground(new java.awt.Color(0, 0, 0));
        FazerCadastro.setForeground(new java.awt.Color(51, 102, 255));
        FazerCadastro.setText("Fazer cadastro");
        FazerCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FazerCadastroActionPerformed(evt);
            }
        });
        add(FazerCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 343, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(51, 102, 255));
        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 343, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {

            jPasswordField1.setEchoChar((char) 0);

        } else {

            jPasswordField1.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void enderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enderecoActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void FazerCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FazerCadastroActionPerformed
        Cliente dados = new Cliente();
        dados.setCPF(CPF.getText());
        dados.setEndereco(endereco.getText());
        dados.setNome(nome.getText());
        dados.setSenha(String.valueOf(jPasswordField1.getPassword()));
        dados.setCargo("ContaComum");

        if (cliente.gravarCliente(dados) == true) {
             JOptionPane.showMessageDialog(null, "O Cadastro foi feito com sucesso", "Cadastro feito com sucesso", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_FazerCadastroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Frame.trocaPainel("painelInicial", new PTelainicial());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField CPF;
    private javax.swing.JButton FazerCadastro;
    private javax.swing.JTextField endereco;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField nome;
    // End of variables declaration//GEN-END:variables
}
