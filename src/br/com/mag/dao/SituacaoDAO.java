/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.dao;

import br.com.mag.classe.Situacao;
import br.com.mag.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class SituacaoDAO {
    
     private Connection conexao;
        
    public SituacaoDAO() throws ClassNotFoundException{
        this.conexao = new ModuloConexao().Conector();
    }
    
    public List<Situacao> listsituacao(){
       try{
         List<Situacao> lista = new ArrayList<>();
         
         String sql = "SELECT * FROM situacao";
         
         PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Situacao sit = new Situacao(); 
               sit.setIdsit(rs.getInt("idsit"));
               sit.setSituacao(rs.getString("situacao"));
               lista.add(sit);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
    
    
    public void cadastrarSituacao(Situacao obj){
        try {
        String Sql = "INSERT INTO situacao (situacao) value (?)";
            try (PreparedStatement pst = conexao.prepareStatement(Sql)) {
                pst.setString(1, obj.getSituacao());
                pst.execute();
                
                JOptionPane.showMessageDialog(null, "Situação cadastrada com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void editarSituacao(Situacao obj){
        try {
        String Sql = "UPDATE situacao SET situacao = ? WHERE idsit = ?";
            try (PreparedStatement pst = conexao.prepareStatement(Sql)) {
                pst.setString(1, obj.getSituacao());
                pst.setInt(2, obj.getIdsit());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Situação alterada com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void deleteSituacao(Situacao obj){
        try {
        String Sql = "DELETE FROM situacao WHERE idsit = ?";
            try (PreparedStatement pst = conexao.prepareStatement(Sql)) {
                pst.setInt(1, obj.getIdsit());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Situação Excluida com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    
}