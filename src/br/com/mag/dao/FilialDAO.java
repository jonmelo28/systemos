/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.dao;

import br.com.mag.classe.Filial;
import br.com.mag.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonatha
 */
public class FilialDAO {
    private Connection conexao;
        
    public FilialDAO(){
        this.conexao = new ModuloConexao().Conector();
    }
    
    public List<Filial> listFilial(){
       try{
         List<Filial> lista = new ArrayList<>();
         
         String sql = "SELECT * FROM filial";
         
         PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Filial sit = new Filial(); 
               sit.setEmpresa(rs.getString("empresa"));
               sit.setEndereco(rs.getString("endereco"));
               sit.setBairro(rs.getString("bairro"));
               sit.setCnpj(rs.getString("cnpj"));
               sit.setCep(rs.getString("cep"));
               sit.setCidade(rs.getString("cidade"));
               sit.setFone(rs.getString("fone"));
               sit.setSite(rs.getString("site"));
               lista.add(sit);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
    
   public void editarFilial(Filial obj){
        try {
        String Sql = "UPDATE filial SET empresa = ?, endereco = ?, bairro = ?, cnpj = ?, cep = ?, cidade = ?,fone=?, site = ? WHERE idFilial = 1";
            try (PreparedStatement pst = conexao.prepareStatement(Sql)) {
                pst.setString(1, obj.getEmpresa());
                pst.setString(2, obj.getEndereco());
                pst.setString(3, obj.getBairro());
                pst.setString(4, obj.getCnpj());
                pst.setString(5, obj.getCep());
                pst.setString(6, obj.getCidade());
                pst.setString(7, obj.getFone());
                pst.setString(8, obj.getSite());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Filial alterada com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
   
   public Boolean update(Filial obj){
       Boolean retorno = false;
       String sql = "UPDATE filial SET imagem = ? where idFilial = 1";
                
           try {
               PreparedStatement pst = conexao.prepareStatement(sql);
                pst.setBytes(1, obj.getImagem());
                retorno = pst.execute();
                JOptionPane.showMessageDialog(null, "imagem inserida com sucesso");
           } catch (SQLException ex) {
               Logger.getLogger(FilialDAO.class.getName()).log(Level.SEVERE, null, ex);
           }
               return retorno;
            }
        
        
      public Filial buscar(){
          Filial retorno = null;
          String sql = "SELECT imagem FROM filial where idFilial = 1";
          try {
             PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                retorno = new Filial();
                retorno.setImagem(rs.getBytes("imagem"));
            }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
         
          
          return retorno;
      } 
   
    
}
