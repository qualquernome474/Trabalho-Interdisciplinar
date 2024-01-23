/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import DAO.Conexao;
import DAO.ClienteDAO;
import MODEL.Cliente;
import MODEL.RegistroDeCompras;
import VIEW.PPedidos;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author 0068952
 */
public class ClienteControl {

    private static ClienteDAO SC = new ClienteDAO();
    private static Conexao C = new Conexao();
    public static Vector<String> dadosListaModel1;

    public boolean fazlogin(String cpfescrito, String senha) {

        if (SC.LogIn(cpfescrito, senha) != null) {
            return true;
        }
        return false;

    }

    public Cliente retornadados() {
        return SC.retornadados(C.informaCPF());
    }
    
    
    public String pegaHoraAtual() {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        return dataHoraAtual.toString();
    }

    public boolean alterarSenha(Cliente dados) {

        return SC.alterarSenha(dados);

    }

    public boolean alterarCargo(String CPF) {

        String cargo = informaCargo(CPF);

        if (cargo!=null) {
            if (cargo.equalsIgnoreCase("Admin")) {
                cargo = "ContaComum";
            } else {
                cargo = "Admin";
            }
        

        return SC.alterarCargo(CPF, cargo);
        }
        return false;
    }

    public boolean gravarCliente(Cliente dados) {

        if (!dados.getCPF().isBlank() && !dados.getCargo().isBlank() && !dados.getEndereco().isBlank() && !dados.getNome().isBlank() && !dados.getSenha().isBlank()) {

            return SC.gravarCliente(dados);

        } else {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos para que o cliente seja cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    public void guardaCPF(String cpfescrito) {
        C.guardaCPF(cpfescrito);
    }

    public String informaCPF() {
        return C.informaCPF();
    }

    public String informaCargo(String cpfescrito) {
        return SC.informaCargo(cpfescrito);
    }

    public Vector<String> consultarClientesGravados() {

        dadosListaModel1 = SC.consultarClientesGravados();

        return dadosListaModel1;
    }

    public boolean atualizaperfil(String CPF, String nome, String endereco, String senha, String Cargo) {

        return SC.atualizaperfil(CPF, nome, endereco, senha, Cargo);
    }

    public void guardacliente(String cpfescrito) {
        C.guardacliente(cpfescrito);
    }
}
