/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import DAO.ComprasDAO;
import MODEL.RegistroDeCompras;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 0068952
 */
public class ComprasControl {

    private static ComprasDAO Compras = new ComprasDAO();

    public void salvaCompra(String CPF, String DataeHora, DefaultListModel Carrinho, Double precototal) {

        String lista = null;

        for (int i = 0; i < Carrinho.getSize(); i++) {
            if (i == 0) {

                lista = Carrinho.get(i).toString();
            } else {

                lista = lista + " - " + Carrinho.get(i).toString();
            }

        }

        RegistroDeCompras RC = new RegistroDeCompras(lista, DataeHora, precototal, CPF, "pendente");
        Compras.salvaCompra(RC);
    }

    public ArrayList<RegistroDeCompras> pegaPedidos() {

        return Compras.pegaPedidos();
    }

    public void concluirtarefa(String codigoI) {

        if (!codigoI.isBlank()) {
            if (Compras.concluirtarefa(codigoI) == false) {
                JOptionPane.showMessageDialog(null, "Digite um código de compra válido", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Digite o número da compra", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}
