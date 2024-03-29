/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEW;

import CONTROL.ClienteControl;
import MODEL.Produtos;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author SYSTEM N.P
 */
public class PConsultaCadastroClientes extends javax.swing.JPanel {

    private DefaultListModel<String> dadosListaModel1;

    /**
     * Creates new form ConsultaCadastro
     */
    public PConsultaCadastroClientes() {
        initComponents();

        this.dadosListaModel1 = new DefaultListModel<>();

        this.listaAlunos.setModel(this.dadosListaModel1);
        
        
       
    
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage imagem = ImageIO.read(getClass().getResource("/RESOURCES/pcadastroclientes.PNG"));//por o nome da tela que vai estar em resouce

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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAlunos = new javax.swing.JList<>();

        setPreferredSize(new java.awt.Dimension(807, 454));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(51, 102, 255));
        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setForeground(new java.awt.Color(51, 102, 255));
        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, -1, -1));

        listaAlunos.setBackground(new java.awt.Color(51, 51, 51));
        listaAlunos.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        listaAlunos.setForeground(new java.awt.Color(255, 51, 102));
        jScrollPane1.setViewportView(listaAlunos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 570, 370));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        dadosListaModel1.clear();
        ClienteControl sa = new ClienteControl();

        dadosListaModel1.addAll(sa.consultarClientesGravados());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Frame.trocaPainel("painelADM", new PAdmin());
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaAlunos;
    // End of variables declaration//GEN-END:variables
}
