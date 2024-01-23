/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Carrinho;
import MODEL.Produtos;
import MODEL.RegistroDeCompras;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author 0068952
 */
public class ComprasDAO {

    public void salvaCompra(RegistroDeCompras RC) {

        try {
            Connection c = Conexao.obterConexao();
            String sql = "INSERT INTO lucas_interdisciplinar.cadastrocomprasclientes(informacoesprodutosComprados,dataehoradascompras,valordacompra,cpfcliente,status) "
                    + " VALUES (?,?,?,?,?);";
            PreparedStatement insercao = c.prepareStatement(sql);
            insercao.setString(1, RC.getInformacoesprodutosComprados());
            insercao.setString(2, RC.getDataehoradascompras());
            insercao.setDouble(3, RC.getValordacompra());
            insercao.setString(4, RC.getCpfcliente());
            insercao.setString(5, RC.getStatus());

            insercao.execute();
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void retiradoestoque(int codigoproduto, Produtos produtoencontrado, ArrayList<Carrinho> produtocarrinho) {

        try {

            Connection c = Conexao.obterConexao();
            String sql = "UPDATE lucas_interdisciplinar.produtos SET quantidade=quantidade-1 WHERE codigo=" + codigoproduto;
            PreparedStatement insercao = c.prepareStatement(sql);
            insercao.execute();

            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void readicionanoestoque(int x, Produtos produtoselecionado) {

        try {

            Connection c = Conexao.obterConexao();
            String sql = "UPDATE lucas_interdisciplinar.produtos SET quantidade=quantidade+1 WHERE codigo=" + produtoselecionado.getCodigo();
            PreparedStatement insercao = c.prepareStatement(sql);
            insercao.execute();

            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public ArrayList<RegistroDeCompras> pegaPedidos() {

        ArrayList<RegistroDeCompras> L = new ArrayList<>();

        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.cadastrocomprasclientes WHERE status='pendente'";
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                RegistroDeCompras atual = new RegistroDeCompras();

                atual.setCpfcliente(resultado.getString("cpfcliente"));
                atual.setDataehoradascompras(resultado.getString("dataehoradascompras"));
                atual.setInformacoesprodutosComprados(resultado.getString("informacoesprodutoscomprados"));
                atual.setValordacompra(resultado.getDouble("valordacompra"));
                atual.setCodigo(resultado.getInt("codigodecompra"));

                L.add(atual);

            }

            c.close();
            return L;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public boolean concluirtarefa(String codigoI) {

        String L = "vazio";
        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.cadastrocomprasclientes WHERE codigodecompra=" + codigoI;
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                RegistroDeCompras atual = new RegistroDeCompras();

                atual.setCpfcliente(resultado.getString("cpfcliente"));

                L = atual.getCpfcliente();

            }

            c.close();

            if (!L.equalsIgnoreCase("vazio")) {
                c = Conexao.obterConexao();
                sql = "UPDATE lucas_interdisciplinar.cadastrocomprasclientes SET status='finalizado' WHERE codigodecompra=" + codigoI;
                PreparedStatement insercao = c.prepareStatement(sql);
                insercao.execute();

                c.close();
                return true;

            } else {

                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

}
