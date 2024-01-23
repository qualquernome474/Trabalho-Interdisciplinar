package MODEL;

public class Produtos {
    private String nome;
    private double preco;
    private int codigo;
    private int quant;
    private String descricao;

    public Produtos(String nome, double preco, int codigo, int quant, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.codigo=codigo;
        this.quant=quant;
        this.descricao=descricao;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    
    

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - "+ "R$"  + preco ;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    

    
    
    
    
    
}
