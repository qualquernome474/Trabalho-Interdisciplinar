/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class Conexao {

    public static String cpfsalvo;
    public static DefaultListModel dadosListaModel2;
    
    
    public static Cliente clietesalvo;
    private static ClienteDAO cliente=new ClienteDAO();

    public static Connection obterConexao() {
        try {
            String url = "jdbc:postgresql://10.90.24.54:5432/aula";
            String usuario = "aula";
            String senha = "aula";

            Connection con = DriverManager.getConnection(url, usuario, senha);

            return con;
        } catch (java.sql.SQLException ex) {
            System.out.println("Erro ao conectar no banco: " + ex.getMessage());

        }
        return null;
    }

    public void guardaCPF(String cpflogado) {

        cpfsalvo = cpflogado;

    }

    public String informaCPF() {

        return cpfsalvo;

    }

    public void guardaCarriho(DefaultListModel modeloCarrinho) {

        dadosListaModel2 = modeloCarrinho;

    }

    public static DefaultListModel informaCarriho() {

       
        
        return dadosListaModel2;

    }
    
    
    public void guardacliente(String cpfsalvo) {
        clietesalvo = cliente.retornadados(cpfsalvo);

    }

    

}
