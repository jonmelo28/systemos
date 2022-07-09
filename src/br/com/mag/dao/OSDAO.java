/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.dao;

import br.com.mag.classe.OS;
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
 * @author Jonatha
 */
public class OSDAO {
       private Connection conexao;
        
    public OSDAO() {
        this.conexao = new ModuloConexao().Conector();
    }
    
    public List<OS> listOSAll(){
       try{
         List<OS> lista = new ArrayList<>();
         
         String sql = "SELECT o.os, DATE_FORMAT(o.dataos, '%d/%m/%Y') AS dataos, o.tipo, s.situacao, o.marca, o.modelo, o.cor, o.defeito, o.servico, o.tecnico, o.valor, o.idcli, o.imei1, o.imei2, o.excluido, c.nomecli, c.fonecli FROM tbos o, tbclientes c, situacao s WHERE o.idcli = c.idcli and o.idsit = s.idsit";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
     // o.excluido, c.nomecli, c.fonecli       
            while (rs.next()) {
               OS os = new OS(); 
               os.setOs(rs.getInt(1));
               os.setDataos(rs.getString(2));
               os.setTipo(rs.getString(3));
               os.setSituacao(rs.getString(4));
               os.setMarca(rs.getString(5));
               os.setModelo(rs.getString(6));
               os.setCor(rs.getString(7));
               os.setDefeito(rs.getString(8));
               os.setServico(rs.getString(9));
               os.setTecnico(rs.getString(10));
               os.setValor(rs.getString(11));
               os.setIdcli(rs.getInt(12));
               os.setImei1(rs.getString(13));
               os.setImei2(rs.getString(14));
               os.setExcluido(rs.getString(15));
               os.setNomecli(rs.getString(16));
               os.setFonecli(rs.getString(17));
               
               
               lista.add(os);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
    
     public List<OS> listOS(String nomecli){
       try{
        List<OS> lista = new ArrayList<>();
         
         String sql = "SELECT o.os, DATE_FORMAT(o.dataos, '%d/%m/%Y') AS dataos, o.tipo, s.situacao, o.marca, o.modelo, o.cor, o.defeito, o.servico, o.tecnico, o.valor, o.idcli, o.imei1, o.imei2, o.excluido, c.nomecli, c.fonecli FROM tbos o, tbclientes c, situacao s WHERE o.idcli = c.idcli and o.idsit = s.idsit and nomecli LIKE ? ";
         
               
         
         PreparedStatement pst = conexao.prepareStatement(sql);
           pst.setString(1, nomecli);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               OS os = new OS(); 
               os.setOs(rs.getInt(1));
               os.setDataos(rs.getString(2));
               os.setTipo(rs.getString(3));
               os.setSituacao(rs.getString(4));
               os.setMarca(rs.getString(5));
               os.setModelo(rs.getString(6));
               os.setCor(rs.getString(7));
               os.setDefeito(rs.getString(8));
               os.setServico(rs.getString(9));
               os.setTecnico(rs.getString(10));
               os.setValor(rs.getString(11));
               os.setIdcli(rs.getInt(12));
               os.setImei1(rs.getString(13));
               os.setImei2(rs.getString(14));
               os.setExcluido(rs.getString(15));
               os.setNomecli(rs.getString(16));
               os.setFonecli(rs.getString(17));
               
               
               lista.add(os);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
     
      public List<OS> listOsCod (int idos){
       try{
         List<OS> lista = new ArrayList<>();
         
         String sql = "SELECT o.os, DATE_FORMAT(o.dataos, '%d/%m/%Y') AS dataos, o.tipo, s.situacao, o.marca, o.modelo, o.cor, o.defeito, o.servico, o.tecnico, o.valor, o.idcli, o.imei1, o.imei2, o.excluido, c.nomecli, c.fonecli FROM tbos o, tbclientes c, situacao s WHERE o.idcli = c.idcli and o.idsit = s.idsit and o.os = ? ";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
           pst.setInt(1, idos);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               OS os = new OS(); 
               os.setOs(rs.getInt(1));
               os.setDataos(rs.getString(2));
               os.setTipo(rs.getString(3));
               os.setSituacao(rs.getString(4));
               os.setMarca(rs.getString(5));
               os.setModelo(rs.getString(6));
               os.setCor(rs.getString(7));
               os.setDefeito(rs.getString(8));
               os.setServico(rs.getString(9));
               os.setTecnico(rs.getString(10));
               os.setValor(rs.getString(11));
               os.setIdcli(rs.getInt(12));
               os.setImei1(rs.getString(13));
               os.setImei2(rs.getString(14));
               os.setExcluido(rs.getString(15));
               os.setNomecli(rs.getString(16));
               os.setFonecli(rs.getString(17));
               
               
               lista.add(os);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
   //  public void recuperar(){
   //      OS obj = new OS();
   //      obj.setOs(txtOs.getText());
   //  }
    
     public void cadastrarOS(OS obj){
        try {
           String sql = "INSERT INTO tbos(tipo, idsit, marca, modelo, cor, defeito, servico, tecnico, valor, imei1, imei2, excluido, idcli,formapagamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Ativo', ?, 'Em Aberto')";
             try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getTipo());
                String sql2 = "select * from situacao where situacao = ?";
                 PreparedStatement pst2 = conexao.prepareStatement(sql2);
                 pst2.setString(1, obj.getSit());
                 ResultSet rs = pst2.executeQuery();
                 if(rs.next()){
                     Situacao sit = new Situacao();
                     sit.setIdsit(rs.getInt(1)); 
                 }
                int situ = rs.getInt(1);
                pst.setInt(2, situ);
                pst.setString(3, obj.getMarca());
                pst.setString(4, obj.getModelo());
                pst.setString(5, obj.getCor());
                pst.setString(6, obj.getDefeito());
                pst.setString(7, obj.getServico());
                pst.setString(8, obj.getTecnico());
                pst.setString(9, obj.getValor());
                pst.setString(10, obj.getImei1());
                pst.setString(11, obj.getImei2());
                pst.setInt(12, obj.getIdcli());
                                                                
                pst.execute();
                JOptionPane.showMessageDialog(null, "Ordem de serviço inserida com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
     
    public void alterarOS(OS obj){
        try {
           String sql = "Update tbos set tipo=?, idsit=? , marca=?, modelo = ?, cor = ?, defeito = ?, servico = ?, tecnico = ?, valor = ?, imei1 = ?, imei2 = ?, excluido = ? where os=? ";
           
             try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getTipo());
                 String sql2 = "select * from situacao where situacao = ?";
                 PreparedStatement pst2 = conexao.prepareStatement(sql2);
                 pst2.setString(1, obj.getSit());
                 ResultSet rs = pst2.executeQuery();
                 if(rs.next()){
                     Situacao sit = new Situacao();
                     sit.setIdsit(rs.getInt(1)); 
                 }
                int situ = rs.getInt(1);
                pst.setInt(2, situ);
                pst.setString(3, obj.getMarca());
                pst.setString(4, obj.getModelo());
                pst.setString(5, obj.getCor());
                pst.setString(6, obj.getDefeito());
                pst.setString(7, obj.getServico());
                pst.setString(8, obj.getTecnico());
                pst.setString(9, obj.getValor());
                pst.setString(10, obj.getImei1());
                pst.setString(11, obj.getImei2());
                pst.setString(12, obj.getExcluido());
                pst.setInt(13, obj.getOs());
                                                                
                pst.execute();
               JOptionPane.showMessageDialog(null, "Ordem de serviço alterada com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    } 
    
    public void deletaOS(OS obj){
        try {
           String sql = "Update tbos set excluido = 'Excluído' where os=? ";
           
             try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setInt(1, obj.getOs());
                                                                
                pst.execute();
               JOptionPane.showMessageDialog(null, "Ordem de serviço alterada com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    } 
     
}
