/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import DAO.ClienteDAO;
import DAO.ProdutosDAO;
import MODEL.Estoque;
import MODEL.Produtos;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author 0068952
 */
public class ProdutosControl {

    public static Vector<Produtos> dadosListaModel1;

    private static ProdutosDAO PD = new ProdutosDAO();

    public Vector<Produtos> pegaProdutos() {

        return PD.pegaprodutosSalvos();

    }

    public Vector<Estoque> pegaEstoque() {

        return PD.pegaEstoque();

    }

    public void deletarproduto(int codigo) {

        PD.deletarproduto(codigo);

    }

    public int pegaultimocodigoProdutos() {

        return (PD.pegaultimocodigoProdutos());

    }

    public boolean verificacodigo(int codigo) {

        return PD.verificaexistenciaP(codigo);
    }

    public void cadastraProdutos(String nome, double preco, int codigo, int quant, String descricao) {

        if (!nome.isBlank() && !descricao.isBlank()) {

            Produtos P = new Produtos(nome, preco, codigo, quant, descricao);

            if (PD.gravarProduto(P) == true) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            }

        } else {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos para que o produto seja cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);

        }
    }

    public Vector<Produtos> consultarProdutosGravados() {

        dadosListaModel1 = PD.pegaprodutosSalvos();

        return dadosListaModel1;
    }

    public String mostradesc(int codigo) {

        return PD.mostradesc(codigo);
    }

    public void atualizaestoque(int codigo, int quant) {

        PD.atualizaestoque(codigo, quant);
    }

}
