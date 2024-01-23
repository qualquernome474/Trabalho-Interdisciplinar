/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import DAO.ClienteDAO;
import DAO.ComprasDAO;
import DAO.Conexao;
import DAO.ProdutosDAO;
import MODEL.Carrinho;
import MODEL.Produtos;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author 0068952
 */
public class CarrinhoControl {

    private static Conexao C = new Conexao();
    private static ClienteDAO SC = new ClienteDAO();
    private static ProdutosDAO produto = new ProdutosDAO();
    private static ComprasDAO compras = new ComprasDAO();

    public ArrayList<Carrinho> atualizacarinho(int codigoproduto, JList<Produtos> jList1, ArrayList<Carrinho> produtocarrinho, JList<Carrinho> jList3) {
        if (produto.verificaestoque(codigoproduto) == true) {
            

                Produtos produtoencontrado = pegaprodutos(codigoproduto, jList1);

                boolean temnocarrinho = consultacarrinho(produtoencontrado, produtocarrinho);

                if (temnocarrinho == false) {

                    Carrinho novoprodutocarrinho = new Carrinho(produtoencontrado, 1);
                    produtocarrinho.add(novoprodutocarrinho);

                    compras.retiradoestoque(codigoproduto, produtoencontrado, produtocarrinho);
                } else {

                    produtocarrinho = aumentaquant(produtoencontrado, produtocarrinho, jList3);

                    compras.retiradoestoque(codigoproduto, produtoencontrado, produtocarrinho);

                }

            
        } else {

            JOptionPane.showMessageDialog(null, "O estoque do produto escolhido acabou", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }

        return produtocarrinho;

    }

    public void retornatudoF() {

        if (C.informaCarriho() != null) {
            produto.retornatudo(C.informaCarriho());
        }
    }

    public boolean consultacarrinho(Produtos produtoencontrado, ArrayList<Carrinho> produtocarrinho) {
        boolean resultado = false;
        for (int i = 0; i < produtocarrinho.size(); i++) {
            if (produtocarrinho.get(i).getProduto().getCodigo() == produtoencontrado.getCodigo()) {

                resultado = true;
            }
        }
        return resultado;
    }

    public ArrayList<Carrinho> aumentaquant(Produtos produtoencontrado, ArrayList<Carrinho> produtocarrinho, JList<Carrinho> jList3) {

        for (int i = 0; i < produtocarrinho.size(); i++) {
            if (produtocarrinho.get(i).getProduto().getCodigo() == (produtoencontrado.getCodigo())) {

                produtocarrinho.get(i).setQuantCarrinho(produtocarrinho.get(i).getQuantCarrinho() + 1);

            }

        }

        return produtocarrinho;

    }

    public Produtos pegaprodutos(int codigoproduto, JList<Produtos> jList1) {

        

        return produto.retornaP(codigoproduto);
    }

    public boolean guardaCarriho(DefaultListModel<Carrinho> dadosListaModel2) {

        if (!dadosListaModel2.isEmpty()) {
            C.guardaCarriho(dadosListaModel2);
            return true;
        }else{
        
            return false;
        }

    }

    public DefaultListModel informaCarriho() {

        return C.informaCarriho();

    }

    public void descartarcompra() {

        DefaultListModel L = null;
        L.clear();
        C.guardaCarriho(L);
    }

    public double totalcarrinho() {
        ListModel<Carrinho> modelocompra = informaCarriho();

        if (modelocompra != null) {
            double total = 0.00;
            
            for (int a = 0; a < modelocompra.getSize(); a++) {
                total = total + (modelocompra.getElementAt(a).getQuantCarrinho() * modelocompra.getElementAt(a).getProduto().getPreco());
            }
            
           java.text.NumberFormat nf=java.text.NumberFormat.getNumberInstance();
           nf.setMinimumFractionDigits(2);
           nf.setMaximumFractionDigits(2);
           String x=nf.format(total);
           
           x=x.replaceAll(",", ".");
           
           total=Double.valueOf(x);

            return total;
        }else{
            return 0.00;
        }
    }

    public DefaultListModel<Carrinho> deletaprodutocarrinho(int x, DefaultListModel<Carrinho> dadosListaModel2, Produtos produtoselecionado, int quantdevolvida) {

        DefaultListModel<Carrinho> dadosListaModel = dadosListaModel2;

        dadosListaModel.remove(x);

        produto.retornavariosprodutos(produtoselecionado, quantdevolvida);

        return dadosListaModel;

    }

    public boolean retornatudo(DefaultListModel<Carrinho> dadosListaModel2) {

        produto.retornatudo(dadosListaModel2);

        return false;

    }

    public DefaultListModel<Carrinho> retiraumprodutocarrinho(int x, DefaultListModel<Carrinho> dadosListaModel2, JList<Carrinho> jList3, Produtos produtoselecionado) {

        for (int i = 0; i < jList3.getModel().getSize(); i++) {
            if (jList3.getModel().getElementAt(i).getProduto().equals(dadosListaModel2.get(x).getProduto())) {

                Carrinho carr = (Carrinho) jList3.getModel().getElementAt(i);

                carr.setQuantCarrinho(carr.getQuantCarrinho() - 1);
                dadosListaModel2.set(i, carr);

                if (carr.getQuantCarrinho() == 0) {
                    dadosListaModel2.remove(i);
                }

                compras.readicionanoestoque(x, produtoselecionado);

            }
        }

        return dadosListaModel2;

    }

}
