/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Carrinho;
import MODEL.Cliente;
import MODEL.Estoque;
import MODEL.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 0068952
 */
public class ProdutosDAO {

    public int pegaultimocodigoProdutos() {
        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos";
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            int ultimocodigo = 1;
            Produtos atual = null;
            while (resultado.next()) {
                atual = new Produtos(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));
                
                if(ultimocodigo<atual.getCodigo()){
                    ultimocodigo = atual.getCodigo();
                }
                
            }
            
            
            ultimocodigo=ultimocodigo+1;

            c.close();

            
            return ultimocodigo;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return 0;
        }
    }

    public void atualizaestoque(int codigo, int quant) {

        Produtos P = null;
        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos WHERE codigo=" + codigo;
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                P = new Produtos(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));

            }

            c.close();

            if (P != null) {

                P.setQuant(P.getQuant() + quant);

                c = Conexao.obterConexao();
                sql = "UPDATE lucas_interdisciplinar.produtos SET quantidade=" + P.getQuant() + " WHERE codigo=" + codigo;
                PreparedStatement insercao = c.prepareStatement(sql);
                insercao.execute();

                c.close();

            } else {
                JOptionPane.showMessageDialog(null, "Código inválido", "Erro", JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());

        }
    }

    public boolean gravarProduto(Produtos dados) {
        try {
            Connection c = Conexao.obterConexao();
            String sql = "INSERT INTO lucas_interdisciplinar.produtos(codigo,nome,quantidade,descricao,preco) "
                    + " VALUES (?,?,?,?,?) returning codigo;";
            PreparedStatement insercao = c.prepareStatement(sql);
            insercao.setInt(1, dados.getCodigo());
            insercao.setString(2, dados.getNome());
            insercao.setInt(3, dados.getQuant());
            insercao.setString(4, dados.getDescricao());
            insercao.setDouble(5, dados.getPreco());

            insercao.executeQuery();
            c.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível adicionar o(s) produto(s) " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            
            return false;
        }

    }

    public void deletarproduto(int codigo) {

        Produtos P = null;
        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos WHERE codigo=" + codigo;
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                P = new Produtos(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));

            }

            c.close();

            if (P != null) {

                c = Conexao.obterConexao();
                sql = "DELETE  FROM lucas_interdisciplinar.produtos WHERE codigo=" + codigo;
                PreparedStatement insercao = c.prepareStatement(sql);
                insercao.execute();

                c.close();

            } else {
                JOptionPane.showMessageDialog(null, "Código inválido", "Erro", JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());

        }

    }

    
    public Vector<Estoque> pegaEstoque() {

        Vector<Estoque> produtos = new Vector();
        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos order by codigo";
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Estoque atual = new Estoque(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));

                produtos.add(atual);
            }
            c.close();
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    }
    
    
    
    public Vector<Produtos> pegaprodutosSalvos() {

        Vector<Produtos> produtos = new Vector();
        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos order by codigo";
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Produtos atual = new Produtos(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));

                produtos.add(atual);
            }
            c.close();
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    }

    public boolean verificaexistenciaP(int codigo) {

        try {

            List<Produtos> retorno = new ArrayList<Produtos>();
            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos WHERE codigo=" + codigo;
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                Produtos atual = new Produtos(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));
                retorno.add(atual);

            }
            c.close();
            if (retorno.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Código inválido", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                return false;
            } else {

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
    }

    public boolean verificaestoque(int codigoproduto) {
        boolean verificacao = false;
        try {

            List<Produtos> retorno = new ArrayList<Produtos>();
            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos WHERE codigo=" + codigoproduto;
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                Produtos atual = new Produtos(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));
                retorno.add(atual);

            }

            if (retorno.get(0).getQuant() > 0) {
                verificacao = true;

            } else {
                verificacao = false;

            }

            c.close();

            return verificacao;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }

    }

    public Produtos retornaP(int codigoproduto) {

        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos WHERE codigo=" + codigoproduto;
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            Produtos atual = null;
            while (resultado.next()) {
                atual = new Produtos(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));
            }
            c.close();
            return atual;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }

    }

    public void retornatudo(DefaultListModel<Carrinho> dadosListaModel2) {

        for (int x = 0; x < dadosListaModel2.getSize(); x++) {
            try {

                Connection c = Conexao.obterConexao();
                String sql = "UPDATE lucas_interdisciplinar.produtos SET quantidade=quantidade+" + dadosListaModel2.getElementAt(x).getQuantCarrinho() + " WHERE codigo=" + dadosListaModel2.getElementAt(x).getProduto().getCodigo();
                PreparedStatement insercao = c.prepareStatement(sql);
                insercao.execute();

                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

    }

    public void retornavariosprodutos(Produtos produtoselecionado, int quantdevolvida) {
        try {

            Connection c = Conexao.obterConexao();
            String sql = "UPDATE lucas_interdisciplinar.produtos SET quantidade=quantidade+" + quantdevolvida + " WHERE codigo=" + produtoselecionado.getCodigo();
            PreparedStatement insercao = c.prepareStatement(sql);
            insercao.execute();

            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public String mostradesc(int codigo) {
        String desc = null;
        try {

            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.produtos WHERE codigo=" + codigo;
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                Produtos atual = new Produtos(resultado.getString("nome"), resultado.getDouble("preco"), resultado.getInt("codigo"), resultado.getInt("quantidade"), resultado.getString("descricao"));
                desc = "Código:" + atual.getCodigo() + "\n\n" + "Nome:" + atual.getNome() + "\n\n" + "Quantidade:" + atual.getQuant() + "\n\n" + "Descrição:" + atual.getDescricao() + "\n\n" + "Preço:" + atual.getPreco();

            }

            c.close();

            return desc;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());

        }
        return null;
    }

}
