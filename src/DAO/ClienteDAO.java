/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Cliente;
import DAO.Conexao;
import MODEL.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ClienteDAO {

    public Vector<String> consultarClientesGravados() {
        try {
            Vector<String> retorno = new Vector<String>();
            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.CadastroDeClientes";
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Cliente atual = new Cliente();

                atual.setNome(resultado.getString("nome"));

                retorno.add(atual.getNome());
            }
            c.close();
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    }

    public boolean alterarCargo(String CPF, String cargo) {

        try {
            Connection c = Conexao.obterConexao();
            String sql = "UPDATE lucas_interdisciplinar.cadastrodeclientes "
                    + " SET cargo=?"
                    + " WHERE cpf='" + CPF + "' ";
            PreparedStatement atualizacao = c.prepareStatement(sql);

            atualizacao.setString(1, cargo);

            atualizacao.executeUpdate();
            c.close();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o cargo", "Erro", JOptionPane.ERROR_MESSAGE);

        }
        return false;

    }

    public boolean gravarCliente(Cliente dados) {
        try {

            Vector<String> retorno = new Vector<String>();
            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.CadastroDeClientes WHERE cpf='" + dados.getCPF() + "'";
            PreparedStatement consulta = c.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Cliente atual = new Cliente();

                atual.setCPF(resultado.getString("cpf"));

                retorno.add(atual.getNome());
            }
            c.close();

            if (retorno.isEmpty()) {
                c = Conexao.obterConexao();
                sql = "INSERT INTO lucas_interdisciplinar.CadastroDeClientes(nome,CPF,endereco,senha,cargo) "
                        + " VALUES (?,?,?,?,?);";
                PreparedStatement insercao = c.prepareStatement(sql);
                insercao.setString(1, dados.getNome());
                insercao.setString(2, dados.getCPF());
                insercao.setString(3, dados.getEndereco());
                insercao.setString(4, dados.getSenha());
                insercao.setString(5, dados.getCargo());

                insercao.execute();
                c.close();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Já existe um cliente com esse CPF cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;

    }

    public Cliente LogIn(String cpfescrito, String senha) {
        try {
            Connection c = Conexao.obterConexao();

            String sql = "SELECT * FROM lucas_interdisciplinar.CadastroDeClientes WHERE cpf='" + cpfescrito + "' and senha='" + senha + "'";
            Statement consulta = c.createStatement();

            ResultSet resultado = consulta.executeQuery(sql);

            if (resultado.next() == true) {
                Cliente clienteLogIn = new Cliente();
                clienteLogIn.setCPF(resultado.getString("cpf"));
                clienteLogIn.setNome(resultado.getString("nome"));
                clienteLogIn.setEndereco(resultado.getString("endereco"));
                clienteLogIn.setSenha(resultado.getString("senha"));

                return clienteLogIn;

            } else {
                JOptionPane.showMessageDialog(null, "CPF ou senha incorretos", "Erro no LogIn", JOptionPane.ERROR_MESSAGE);
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }

        return null;
    }

    public boolean alterarSenha(Cliente dados) {

        Cliente clienteLogIn = new Cliente();
        try {
            Connection c = Conexao.obterConexao();
            String sql = "SELECT * FROM lucas_interdisciplinar.cadastrodeclientes WHERE cpf='" + dados.getCPF() + "' ";
            Statement consulta = c.createStatement();

            ResultSet resultado = consulta.executeQuery(sql);

            if (resultado.next()) {

                clienteLogIn.setCPF(resultado.getString("cpf"));
                clienteLogIn.setNome(resultado.getString("nome"));
                clienteLogIn.setEndereco(resultado.getString("endereco"));
                clienteLogIn.setSenha(resultado.getString("senha"));

            } else {
                JOptionPane.showMessageDialog(null, "CPF ou senha incorretos", "Erro no LogIn", JOptionPane.ERROR_MESSAGE);
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());

        }

        if (clienteLogIn.getCPF() == null) {
            return false;
        } else {
            try {
                Connection c = Conexao.obterConexao();
                String sql = "UPDATE lucas_interdisciplinar.cadastrodeclientes "
                        + " SET nome=?, cpf=?, endereco=?, senha=?"
                        + " WHERE cpf='" + clienteLogIn.getCPF() + "' ";
                PreparedStatement atualizacao = c.prepareStatement(sql);

                atualizacao.setString(1, clienteLogIn.getNome());
                atualizacao.setString(2, clienteLogIn.getCPF());
                atualizacao.setString(3, clienteLogIn.getEndereco());
                atualizacao.setString(4, dados.getSenha());

                atualizacao.executeUpdate();
                c.close();

                return true;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível alterar a senha", "Erro", JOptionPane.ERROR_MESSAGE);

            }

            return false;
        }

    }

    public String informaCargo(String cpfescrito) {

        try {
            Connection c = Conexao.obterConexao();

            String sql = "SELECT * FROM lucas_interdisciplinar.CadastroDeClientes WHERE cpf='" + cpfescrito + "'";
            Statement consulta = c.createStatement();

            ResultSet resultado = consulta.executeQuery(sql);

            if (resultado.next() == true) {

                return resultado.getString("cargo");

            } else {
                JOptionPane.showMessageDialog(null, "CPF inválido informado", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }

        return null;
    }

    public boolean atualizaperfil(String CPF, String nome, String endereco, String senha, String Cargo) {
        try {
            Connection c = Conexao.obterConexao();
            String sql = "UPDATE lucas_interdisciplinar.cadastrodeclientes "
                    + " SET nome=?, cpf=?, endereco=?, senha=?, cargo=?"
                    + " WHERE cpf='" + CPF + "' ";
            PreparedStatement atualizacao = c.prepareStatement(sql);

            atualizacao.setString(1, nome);
            atualizacao.setString(2, CPF);
            atualizacao.setString(3, endereco);
            atualizacao.setString(4, senha);
            atualizacao.setString(5, Cargo);

            atualizacao.executeUpdate();
            c.close();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar a senha", "Erro", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public Cliente retornadados(String cpfescrito) {
        try {
            Connection c = Conexao.obterConexao();

            String sql = "SELECT * FROM lucas_interdisciplinar.CadastroDeClientes WHERE cpf='" + cpfescrito + "'";
            Statement consulta = c.createStatement();

            ResultSet resultado = consulta.executeQuery(sql);

            if (resultado.next() == true) {
                Cliente clienteLogIn = new Cliente();
                clienteLogIn.setCPF(resultado.getString("cpf"));
                clienteLogIn.setNome(resultado.getString("nome"));
                clienteLogIn.setEndereco(resultado.getString("endereco"));
                clienteLogIn.setSenha(resultado.getString("senha"));
                clienteLogIn.setCargo(resultado.getString("cargo"));

                return clienteLogIn;

            } else {
                JOptionPane.showMessageDialog(null, "CPF ou senha incorretos", "Erro no LogIn", JOptionPane.ERROR_MESSAGE);
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }

        return null;
    }

}
