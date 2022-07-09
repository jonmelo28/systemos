/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.dao;

import br.com.mag.classe.Pagamento;
import br.com.mag.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonatha
 */
public class PagamentoDAO {
    private Connection conexao;
    public Date dt = new Date();
        
    public PagamentoDAO() {
        this.conexao = new ModuloConexao().Conector();
    }
    
    public List<Pagamento> listOSAll(){
       try{
         List<Pagamento> lista = new ArrayList<>();
         
         String sql = "SELECT o.os, DATE_FORMAT(o.dataos, '%d/%m/%Y') AS dataos, o.idcli, c.nomecli, o.valor, CASE WHEN o.datapagamento is null THEN ' ' else DATE_FORMAT(o.datapagamento, '%d/%m/%Y') end datapagamento, o.formapagamento FROM `tbos` o INNER JOIN tbclientes c ON (o.idcli = c.idcli)  and o.excluido = 'Ativo'  and o.tipo = 'os'  order by o.os";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Pagamento pag = new Pagamento(); 
               pag.setCodOs(rs.getInt(1));
               pag.setDataOs(rs.getString(2));
               pag.setCodCliente(rs.getInt(3));
               pag.setCliente(rs.getString(4));
               pag.setValor(rs.getString(5).replace(".", ","));
               pag.setDataPagamento(rs.getString(6));
               pag.setFormaPagamento(rs.getString(7));
               
               lista.add(pag);
               
            }
             return lista;
       }catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
    
   public List<Pagamento> listOS(int codOs, int codCliente, String cliente, String dataOs1, String dataOs2, String dataPagamento1, String dataPagamento2, String formaPagamento){
       try{
         List<Pagamento> lista = new ArrayList<>();
         
         String sql = "SELECT o.os, DATE_FORMAT(o.dataos, '%d/%m/%Y') AS dataos, o.idcli, c.nomecli, o.valor, CASE WHEN o.datapagamento is null THEN ' ' else DATE_FORMAT(o.datapagamento, '%d/%m/%Y') end datapagamento, o.formapagamento FROM `tbos` o INNER JOIN tbclientes c ON (o.idcli = c.idcli)  where o.excluido = 'Ativo'  and o.tipo = 'os' and o.os = ? and o.idcli = ? and c.cliente = ? and o.dataos between = ? and ? and o.datapagamento between = ? and ? and o.formapagamento = ? order by o.os";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
          pst.setInt(1, codOs);
          pst.setInt(2, codCliente);
          pst.setString(3, cliente);
          pst.setString(4, dataOs1);
          pst.setString(5, dataOs2);
          pst.setString(6, dataPagamento1);          
          pst.setString(7, dataPagamento2);
          pst.setString(8, formaPagamento);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               Pagamento pag = new Pagamento(); 
               pag.setCodOs(rs.getInt(1));
               pag.setDataOs(rs.getString(2));
               pag.setCodCliente(rs.getInt(3));
               pag.setCliente(rs.getString(4));
               pag.setValor(rs.getString(5).replace(".", ","));
               pag.setDataPagamento(rs.getString(6));
               pag.setFormaPagamento(rs.getString(7));
               
               lista.add(pag);
               
            }
             return lista;
       }catch (java.lang.NumberFormatException ex){
    
       JOptionPane.showMessageDialog(null, ex);
       return null;   
      } catch (Exception e){
    
       JOptionPane.showMessageDialog(null, e);
       return null;   
      } 
      
   }
    
   public void alterarPagamento(Pagamento obj){
        try {
            
           String sql = "Update tbos set formapagamento = ? , datapagamento = now() where os = ? ";
           
             try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getFormaPagamento());
                pst.setInt(2, obj.getCodOs());
                                                                
                pst.execute();
               JOptionPane.showMessageDialog(null, "Pagamento incluido com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    } 
   
    public void alterarPagamentoA(Pagamento obj){
        try {
            
           String sql = "Update tbos set formapagamento = ? where os = ? ";
           
             try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setString(1, obj.getFormaPagamento());
                pst.setInt(2, obj.getCodOs());
                                                                
                pst.execute();
               JOptionPane.showMessageDialog(null, "Pagamento alterada com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    } 
    
     public void excluirPagamento(Pagamento obj){
        try {
            
           String sql = "Update tbos set formapagamento = 'Em aberto', datapagamento = null  where os = ? ";
           
             try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                pst.setInt(1, obj.getCodOs());
                                                                
                pst.execute();
               JOptionPane.showMessageDialog(null, "Pagamento excluido com sucesso");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    } 

}