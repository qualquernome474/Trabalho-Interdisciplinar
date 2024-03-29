/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import CONTROL.CarrinhoControl;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author 0068952
 */
public class Frame extends javax.swing.JFrame {

    private static JScrollPane painelRolagem;
    private static JPanel painelTroca;

    private static CardLayout baralhoPaineis;
    private static Frame INSTANCE;
    private static String nomeAtual;

    private static Hashtable<String, JPanel> historicoPaineis;
    private static CarrinhoControl carrinho = new CarrinhoControl();

    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents();
        configuraTrocaPaineis();

        INSTANCE = this;

        setResizable(false);

        trocaPainel("Tela Inicial", new PTelainicial());

    }

    private void configuraTrocaPaineis() {

        this.setLayout(new BorderLayout());
        baralhoPaineis = new CardLayout();
        painelTroca = new JPanel(baralhoPaineis);
        painelRolagem = new JScrollPane();

        this.add(painelRolagem);
        painelRolagem.setViewportView(this.painelTroca);
        historicoPaineis = new Hashtable<>();

    }

    public static void trocaPainel(String nome, JPanel novoPainel) {

        if (!historicoPaineis.containsKey(nome)) {
            painelTroca.add(novoPainel, nome);

            baralhoPaineis.show(painelTroca, nome);

            painelTroca.setPreferredSize(novoPainel.getPreferredSize());
            painelTroca.setSize(novoPainel.getSize());

            historicoPaineis.put(nome, novoPainel);
        } else {

            baralhoPaineis.show(painelTroca, nome);
            painelTroca.setPreferredSize(historicoPaineis.get(nome).getPreferredSize());
            painelTroca.setSize(novoPainel.getSize());
            
            

        }

        
        nomeAtual = nome;
        
        

        INSTANCE.setSize(new Dimension(painelTroca.getPreferredSize().width + 23, painelTroca.getPreferredSize().height + 48));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (nomeAtual.equalsIgnoreCase("painelCompras") || nomeAtual.equalsIgnoreCase("painelFinal")) {

            carrinho.retornatudoF();
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
