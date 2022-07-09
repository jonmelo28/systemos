/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.dao;

import br.com.mag.classe.Usuario;
import br.com.mag.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonatha
 */
public class UsuarioDAO {
     private Connection conexao;
        
    public UsuarioDAO(){
        this.conexao = ModuloConexao.Conector();
    } 
    
    public List<Usuario> listUsuarioAll(){
       try{
         List<Usuario> lista = new ArrayList<>();
         
         String sql = "SELECT iduser, usuario, fone, login, perfil, excluido FROM tbusuarios order by usuario";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Usuario usur = new Usuario();  
               usur.setIdUser(rs.getInt(1));
               usur.setUsuario(rs.getString(2));
               usur.setFone(rs.getString(3));
               usur.setLogin(rs.getString(4));
               usur.setPerfil(rs.getString(5));
               usur.setExcluido(rs.getString(6));
               
               lista.add(usur);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
    
     public List<Usuario> listUsuario(String usuario){
       try{
         List<Usuario> lista = new ArrayList<>();
         
         String sql = "SELECT iduser, usuario, fone, login, perfil, excluido FROM tbusuarios WHERE usuario like ? order by usuario";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
           pst.setString(1, usuario);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Usuario usur = new Usuario();   
               usur.setIdUser(rs.getInt(1));
               usur.setUsuario(rs.getString(2));
               usur.setFone(rs.getString(3));
               usur.setLogin(rs.getString(4));
               usur.setPerfil(rs.getString(5));
               usur.setExcluido(rs.getString(6));
               
               lista.add(usur);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
     
      public List<Usuario> listUsuarioCod (int idUser){
       try{
         List<Usuario> lista = new ArrayList<>();
         
         String sql = "SELECT iduser, usuario, fone, login, perfil, excluido FROM tbusuarios WHERE iduser = ? order by usuario";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
           pst.setInt(1, idUser);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Usuario usur = new Usuario(); 
               usur.setIdUser(rs.getInt(1));
               usur.setUsuario(rs.getString(2));
               usur.setFone(rs.getString(3));
               usur.setLogin(rs.getString(4));
               usur.setPerfil(rs.getString(5));
               usur.setExcluido(rs.getString(6));
               
               lista.add(usur);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
      
      
   public void cadastrarUsuario(Usuario obj){
        try {
           String sql = "INSERT INTO tbusuarios (usuario,fone,login,senha,perfil) VALUES (?, ?, ?, aes_encrypt(?,'usuario'), ?)";
         try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getUsuario());
                pst.setString(2, obj.getFone());
                pst.setString(3, obj.getLogin());
                pst.setString(4, obj.getSenha());
                pst.setString(5, obj.getPerfil());
                pst.execute();
                
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
            }
        
        }catch(SQLIntegrityConstraintViolationException ex) {
             JOptionPane.showMessageDialog(null, "utilizar outro Login esse já existe");
            
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
     public void EditarUsuario(Usuario obj){
        try {
           String sql = "UPDATE tbusuarios set usuario = ?, fone = ?, login = ?, perfil = ?, excluido = ? where iduser = ?";
          try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getUsuario());
                pst.setString(2, obj.getFone());
                pst.setString(3, obj.getLogin());
                pst.setString(4, obj.getPerfil());
                pst.setString(5, obj.getExcluido());
                pst.setInt(6, obj.getIdUser());
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Usuário Atualizado com sucesso");
            }
        
        }catch(SQLIntegrityConstraintViolationException ex) {
             JOptionPane.showMessageDialog(null, "utilizar outro Login esse já existe");
            
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
     
     public void EditarUsuarioCS(Usuario obj){
        try {
           String sql = "UPDATE tbusuarios set usuario = ?, fone = ?, login = ?, senha = AES_ENCRYPT(?,'usuario'), perfil = ?, excluido = ? where iduser = ?";
          try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getUsuario());
                pst.setString(2, obj.getFone());
                pst.setString(3, obj.getLogin());
                pst.setString(4, obj.getSenha());
                pst.setString(5, obj.getPerfil());
                pst.setString(6, obj.getExcluido());
                pst.setInt(7, obj.getIdUser());
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Usuário Atualizado com sucesso");
            }
        
        }catch(SQLIntegrityConstraintViolationException ex) {
             JOptionPane.showMessageDialog(null, "utilizar outro Login esse já existe");
            
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
     
     public void deleteUsuario(Usuario obj){
        try {
          String sql = "UPDATE tbusuarios set excluido = 'Sim' where iduser = ?";
           try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setInt(1, obj.getIdUser());
                pst.execute();
                
                JOptionPane.showMessageDialog(null, "Usuário Deletado com sucesso");
            }
        
        }catch(SQLIntegrityConstraintViolationException ex) {
             JOptionPane.showMessageDialog(null, "utilizar outro Login esse já existe");
            
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
}
