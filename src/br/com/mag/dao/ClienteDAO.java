/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.dao;

import br.com.mag.classe.Cliente;
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
public class ClienteDAO {
     private Connection conexao;
        
    public ClienteDAO() throws ClassNotFoundException{
        this.conexao = new ModuloConexao().Conector();
    }
    
    public List<Cliente> listClienteAll(){
       try{
         List<Cliente> lista = new ArrayList<>();
         
         String sql = "SELECT idcli as 'Código', nomecli as Nome, endcli as Endereço, fonecli as Telefone, emailcli as 'E-mail', cgc as CGC, sitcli as 'situação', cidadecli, bairrocli FROM `tbclientes`  order by nomecli ";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Cliente cli = new Cliente(); 
               cli.setIdcli(rs.getInt(1));
               cli.setNomecli(rs.getString(2));
               cli.setEndcli(rs.getString(3));
               cli.setFonecli(rs.getString(4));
               cli.setEmailcli(rs.getString(5));
               cli.setCgc(rs.getString(6));
               cli.setSitcli(rs.getString(7));
               cli.setCidadeCli(rs.getString(8));
               cli.setBairrocli(rs.getString(9));
               
               lista.add(cli);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
    
     public List<Cliente> listCliente(String nomecli){
       try{
         List<Cliente> lista = new ArrayList<>();
         
         String sql = "SELECT idcli as Código, nomecli as Nome, endcli as Endereço, fonecli as Telefone, emailcli as 'E-mail', cgc as CGC, sitcli as 'situação', cidadecli, bairrocli FROM `tbclientes` WHERE  nomecli LIKE ?  order by nomecli ";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
           pst.setString(1, nomecli);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Cliente cli = new Cliente(); 
               cli.setIdcli(rs.getInt(1));
               cli.setNomecli(rs.getString(2));
               cli.setEndcli(rs.getString(3));
               cli.setFonecli(rs.getString(4));
               cli.setEmailcli(rs.getString(5));
               cli.setCgc(rs.getString(6));
               cli.setSitcli(rs.getString(7));
               cli.setCidadeCli(rs.getString(8));
               cli.setBairrocli(rs.getString(9));
               
               lista.add(cli);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
     
      public List<Cliente> listClienteEx(String nomecli){
       try{
         List<Cliente> lista = new ArrayList<>();
         
         String sql = "SELECT idcli as Código, nomecli as Nome, fonecli as Telefone FROM `tbclientes` WHERE  nomecli LIKE ? and sitcli = 'Ativo' order by nomecli ";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
           pst.setString(1, nomecli);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Cliente cli = new Cliente(); 
               cli.setIdcli(rs.getInt(1));
               cli.setNomecli(rs.getString(2));
               cli.setFonecli(rs.getString(3));
               
               lista.add(cli);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
     
      public List<Cliente> listClienteCod (int idcli){
       try{
         List<Cliente> lista = new ArrayList<>();
         
         String sql = "SELECT idcli as Código, nomecli as Nome, endcli as Endereço, fonecli as Telefone, emailcli as 'E-mail', cgc as CGC, sitcli as 'situação', cidadecli, bairrocli  FROM `tbclientes` WHERE  idcli = ? order by nomecli ";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
           pst.setInt(1, idcli);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Cliente cli = new Cliente(); 
               cli.setIdcli(rs.getInt(1));
               cli.setNomecli(rs.getString(2));
               cli.setEndcli(rs.getString(3));
               cli.setFonecli(rs.getString(4));
               cli.setEmailcli(rs.getString(5));
               cli.setCgc(rs.getString(6));
               cli.setSitcli(rs.getString(7));
               cli.setCidadeCli(rs.getString(8));
               cli.setBairrocli(rs.getString(9));
               
               lista.add(cli);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
    
    public void cadastrarCliente(Cliente obj){
        try {
           String sql = "INSERT INTO tbclientes (nomecli, endcli, fonecli, emailcli, cgc, cidadecli, bairrocli) VALUES (?, ?, ?, ?,?,?,?)";
            try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getNomecli());
                pst.setString(2, obj.getEndcli());
                pst.setString(3, obj.getFonecli());
                pst.setString(4, obj.getEmailcli());
                pst.setString(5, obj.getCgc());
                pst.setString(6, obj.getCidadeCli());
                pst.setString(7, obj.getBairrocli());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
     public void EditarCliente(Cliente obj){
        try {
           String sql = "UPDATE tbclientes set nomecli = ?,  endcli = ?, fonecli = ?, emailcli = ?, cgc = ?, sitcli = ?, cidadecli = ?, bairrocli = ? where idcli = ?";
         try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getNomecli());
                pst.setString(2, obj.getEndcli());
                pst.setString(3, obj.getFonecli());
                pst.setString(4, obj.getEmailcli());
                pst.setString(5, obj.getCgc());
                pst.setString(6, obj.getSitcli());
                pst.setString(7, obj.getCidadeCli());
                pst.setString(8, obj.getBairrocli());
                pst.setInt(9, obj.getIdcli());
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Cliente Atualizado com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
     
     public void deleteCliente(Cliente obj){
        try {
           String sql = "UPDATE tbclientes set sitcli = 'Excluído' where idcli = ?";
           try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setInt(1, obj.getIdcli());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Cliente Excluido com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
}
