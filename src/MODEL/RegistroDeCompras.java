/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author 0068952
 */
public class RegistroDeCompras {
    private String informacoesprodutosComprados;
    private String dataehoradascompras ;
    private double valordacompra ;
    private String cpfcliente;
    private String status;
    private int codigo;

    public RegistroDeCompras(String lista, String dataehoradascompras, double valordacompra, String cpfcliente, String status) {
        
        this.informacoesprodutosComprados=lista;
        this.dataehoradascompras = dataehoradascompras;
        this.valordacompra = valordacompra;
        this.cpfcliente = cpfcliente;
        this.status=status;
        
    }

    public RegistroDeCompras() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public String getInformacoesprodutosComprados() {
        return informacoesprodutosComprados;
    }

    public String getDataehoradascompras() {
        return dataehoradascompras;
    }

    public double getValordacompra() {
        return valordacompra;
    }

    public String getCpfcliente() {
        return cpfcliente;
    }


    public void setInformacoesprodutosComprados(String informacoesprodutosComprados) {
        this.informacoesprodutosComprados = informacoesprodutosComprados;
    }

    public void setDataehoradascompras(String dataehoradascompras) {
        this.dataehoradascompras = dataehoradascompras;
    }

    public void setValordacompra(double valordacompra) {
        this.valordacompra = valordacompra;
    }

    public void setCpfcliente(String cpfcliente) {
        this.cpfcliente = cpfcliente;
    }

    @Override
    public String toString() {
        return "Produtos: " + informacoesprodutosComprados + "--- data: " + dataehoradascompras + "--- valor da compra: " + valordacompra + "--- cpf do cliente:" + cpfcliente + "--- codigo:" + codigo;
    }
    
    
    
}
