package MODEL;

import MODEL.Produtos;


public class Carrinho {
    private Produtos produto;
    private int quantCarrinho=0;

    public Carrinho(Produtos produto, int quantCarrinho) {
        this.produto = produto;
        this.quantCarrinho = quantCarrinho;
    }

    public Produtos getProduto() {
        return produto;
    }

    public int getQuantCarrinho() {
        return quantCarrinho;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public void setQuantCarrinho(int quantCarrinho) {
        this.quantCarrinho = quantCarrinho;
    }

    @Override
    public String toString() {
        return produto + "  X" + quantCarrinho;
    }
    
    
    
    
}
