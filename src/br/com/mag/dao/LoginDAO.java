/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.dao;

import br.com.mag.dal.ModuloConexao;
import br.com.mag.telas.Tela_Principal;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class LoginDAO {
    private Connection conexao;
        
    public LoginDAO() throws ClassNotFoundException{
        this.conexao = new ModuloConexao().Conector();
    }
    
    public boolean logar(String usuario, String senha) {
       
        
        try {
            String Sql = "SELECT * FROM tbusuarios WHERE login = ? and senha = aes_encrypt(?,'usuario')";
            PreparedStatement pst = conexao.prepareStatement(Sql);
            pst.setString(1, usuario);
            pst.setString(2, senha);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                //faz a consulta do perfil no banco
                String perfil = rs.getString(6);
                
                //estrutura de decisão para a tela principal 
                if(perfil.equals("Administrador")){
                //abre a tela principal
                Tela_Principal principal = new Tela_Principal();
                principal.setVisible(true);
                //Habilita os campos para o perfil administrador
                Tela_Principal.menRel.setEnabled(true);
                Tela_Principal.menCadUsu.setEnabled(true);
                
                //pega o nome do usuário e mostra na lblUsuario
                Tela_Principal.lblUsuario.setText(rs.getString(2));
                //muda a cor do texto da lblUsuário quando for admisitrador
                Tela_Principal.lblUsuario.setForeground(Color.red);
                
                //ficou na aula 17
                  
                }else{
                //abre a tela principal
                Tela_Principal principal = new Tela_Principal();
                principal.setVisible(true);
                
                //pega o nome do usuário e mostra na lblUsuario
                Tela_Principal.lblUsuario.setText(rs.getString(2));
                }
                
                //fecha a tela de login 
                //this.dispose();
                //fecha a conexão com o banco
                conexao.close();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha não encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
 
      
    
}
