/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.mag.telas;
import br.com.mag.classe.OS;
import br.com.mag.classe.Situacao;
import java.sql.*;
import br.com.mag.dal.ModuloConexao;
import br.com.mag.dao.OSDAO;
import br.com.mag.dao.SituacaoDAO;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jonatha
 */
public class Tela_OS_Detalhe extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst =null;
    ResultSet rs = null;
    
    private String tipo;

    /**
     * Creates new form Tela_OS
     */
    public Tela_OS_Detalhe() throws ClassNotFoundException {
        initComponents();
        this.conexao = ModuloConexao.Conector();
        
        if(txtCodOS.getText().isEmpty()){
            pesquisar_os();
        }
        
    }
    
    

    
    
   private void limparCampos(){
             cbOsSit.setSelectedIndex(0);
             txtOsEquip.setText(null);
             txtOsDef.setText(null);
             txtOsSer.setText(null);
             txtOsTec.setText(null);
             txtOsVL.setText("0,00");
             txtCliId.setText(null);
             txtOs.setText(null);
             txtData.setText(null);
             txtCliPesquisar.setText(null);
             txtCor.setText(null);
             txtModelo.setText(null);
             txtImei1.setText(null);
             txtImei2.setText(null);
             txtCliNome.setText(null);
             
             ((DefaultTableModel) tblOS.getModel()).setRowCount(0);
             
             
    }
    
    //metodo para pesquisar os
    private void pesquisar_os_cod(){
       try {
            String idos;
            idos = txtCodOS.getText();
            OSDAO dao = new OSDAO();
            List<OS> lista = dao.listOsCod(Integer.parseInt(idos));
            
                DefaultTableModel modelo = (DefaultTableModel)tblOS.getModel();
                modelo.setNumRows(0);
               
                for(OS s: lista){
                   modelo.addRow(new Object[]{
                       s.getOs(),
                       s.getDataos(),
                       s.getTipo(),
                       s.getSituacao(),
                       s.getMarca(),
                       s.getModelo(),
                       s.getCor(),
                       s.getDefeito(),
                       s.getServico(),
                       s.getTecnico(),
                       s.getValor().replace(".", ","),
                       s.getIdcli(),
                       s.getImei1(),
                       s.getImei2(),
                       s.getExcluido(),
                       s.getNomecli(),
                       s.getFonecli()
                   });
                    
                }
            
        } catch (Exception e) {
        }
    }
    //metodo para pesquisar os
    private void pesquisar_os_cli(){
        try {
            String nomecli;
            nomecli = txtCliPesquisar.getText();
            OSDAO dao = new OSDAO();
            List<OS> lista = dao.listOS(nomecli + "%");
            
                DefaultTableModel modelo = (DefaultTableModel)tblOS.getModel();
                modelo.setNumRows(0);
               
                for(OS s: lista){
                   modelo.addRow(new Object[]{
                       s.getOs(),
                       s.getDataos(),
                       s.getTipo(),
                       s.getSituacao(),
                       s.getMarca(),
                       s.getModelo(),
                       s.getCor(),
                       s.getDefeito(),
                       s.getServico(),
                       s.getTecnico(),
                       s.getValor().replace(".", ","),
                       s.getIdcli(),
                       s.getImei1(),
                       s.getImei2(),
                       s.getExcluido(),
                       s.getNomecli(),
                       s.getFonecli()
                   });
                    
                }
            
        } catch (Exception e) {
        }
    }
    
    //metodo para pesquisar os
    private void pesquisar_os(){
        try {
            OSDAO dao = new OSDAO();
            List<OS> lista = dao.listOSAll();
            
                DefaultTableModel modelo = (DefaultTableModel)tblOS.getModel();
                modelo.setNumRows(0);
              
                for(OS s: lista){
                   modelo.addRow(new Object[]{
                       s.getOs(),
                       s.getDataos(),
                       s.getTipo(),
                       s.getSituacao(),
                       s.getMarca(),
                       s.getModelo(),
                       s.getCor(),
                       s.getDefeito(),
                       s.getServico(),
                       s.getTecnico(),
                       s.getValor().replace(".", ","),
                       s.getIdcli(),
                       s.getImei1(),
                       s.getImei2(),
                       s.getExcluido(),
                       s.getNomecli(),
                       s.getFonecli()
                   });
                   
                    
                }
            
        } catch (Exception e) {
        }
    }
    
      public void setar_campo(){
       int setar = tblOS.getSelectedRow();
                txtOs.setText(tblOS.getModel().getValueAt(setar, 0).toString());
                txtData.setText(tblOS.getModel().getValueAt(setar, 1).toString());
                
                String rbtTipo=tblOS.getModel().getValueAt(setar, 2).toString();
                if(rbtTipo.equals("os")){
                    rbtOs.setSelected(true);
                    tipo="os";
                }else{
                    rbtOrc.setSelected(true);
                    tipo="Orçamento";
                }
                cbOsSit.setSelectedItem(tblOS.getModel().getValueAt(setar, 3).toString());
                txtOsEquip.setText(tblOS.getModel().getValueAt(setar, 4).toString());
                txtModelo.setText(tblOS.getModel().getValueAt(setar, 5).toString());
                txtCor.setText(tblOS.getModel().getValueAt(setar, 6).toString());
                txtOsDef.setText(tblOS.getModel().getValueAt(setar, 7).toString());
                txtOsSer.setText(tblOS.getModel().getValueAt(setar, 8).toString());
                txtOsTec.setText(tblOS.getModel().getValueAt(setar, 9).toString());
                txtOsVL.setText(tblOS.getModel().getValueAt(setar, 10).toString().replace(".", ","));
                txtCliId.setText(tblOS.getModel().getValueAt(setar, 11).toString());
                txtImei1.setText(tblOS.getModel().getValueAt(setar, 12).toString());
                txtImei2.setText(tblOS.getModel().getValueAt(setar, 13).toString());
                cbExcluido.setSelectedItem(tblOS.getModel().getValueAt(setar, 14).toString());
                txtCliNome.setText(tblOS.getModel().getValueAt(setar, 15).toString());
                
    }
    
    private void alteraOs(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem ceretza que quer apagar o registro selecionada","Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
         
          OS obj = new OS();
          obj.setTipo(tipo);
          obj.setSit(cbOsSit.getSelectedItem().toString());
          obj.setMarca(txtOsEquip.getText());
          obj.setModelo(txtModelo.getText());
          obj.setCor(txtCor.getText());
          obj.setDefeito(txtOsDef.getText());
          obj.setServico(txtOsSer.getText());
          obj.setTecnico(txtOsTec.getText());
          obj.setValor(txtOsVL.getText().replace(",", "."));
          obj.setImei1(txtImei1.getText());
          obj.setImei2(txtImei2.getText());
          obj.setExcluido(cbExcluido.getSelectedItem().toString());
          obj.setOs(Integer.parseInt(txtOs.getText()));
          if ((txtOsEquip.getText().isEmpty()) || (txtOsDef.getText().isEmpty()) || (txtModelo.getText().isEmpty()) || (txtCor.getText().isEmpty())) {
                
             JOptionPane.showMessageDialog(null, "Preencha os compos obrigatórios");
            } else if(cbOsSit.getSelectedItem().toString().equals("Selecione uma Situação") ) {
              JOptionPane.showMessageDialog(null, "Selecione outra Situação");
                 
            }else{
               OSDAO dao = new OSDAO();
               dao.alterarOS(obj);
               limparCampos();
               habilitaCampos();
               pesquisar_os();

            
            }
          
            
          }else{
            limparCampos();
               habilitaCampos();
               pesquisar_os(); 
        }
        
        
    }
    
    private void excluir(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem ceretza que quer apagar o registro selecionada","Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
             OS obj = new OS();
             obj.setOs(Integer.parseInt(txtOs.getText()));
             OSDAO dao = new OSDAO();
               dao.deletaOS(obj);
               limparCampos();
               habilitaCampos();
               pesquisar_os();
             
        }else{
            limparCampos();
               habilitaCampos();
               pesquisar_os(); 
        }
    }
    
    private void habilitaCampos(){
        
                   txtCliPesquisar.setEnabled(true);
                   tblOS.setVisible(true);
                   
                   btnOsDel.setEnabled(false);
                   btnOsImp.setEnabled(false);
                   btnOsAtu.setEnabled(false);
                   btnOsReturn.setEnabled(true);
    }
    
   private void imprimirOs() throws ClassNotFoundException{
    int confirma = JOptionPane.showConfirmDialog(null,"confirma a impressão deste os?","atenção",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
                this.conexao = ModuloConexao.Conector();
                //classe hashmap para criar um filtro
                HashMap filtro = new HashMap();
                filtro.put("os", Integer.parseInt(txtOs.getText()));
                
                //pega o caminho do relátorio
               String src = "src\\report\\os\\os.jasper"; 
               //Cria a varavel do Jasper
               JasperPrint jasperPrint = null;
            try {     
                //chamado a função do Jasper onde é setado o caminho do relátorio, o parametro e a
               //conexão 
               jasperPrint = JasperFillManager.fillReport(src, filtro, conexao);
                
                //usa a classe jasper para preparar o relatorio
               //JasperPrint print = JasperFillManager.fillReport("src\\report\\cliente\\clientes.jasper",null,conexao);
              
               //exibe o relatorio utilizando a classe jasper
               //JasperViewer.viewReport(print,false);
               
    
                   
    }  catch (JRException e) {
              JOptionPane.showMessageDialog(null, e);
            }
                               //estabcia da classe JAsperViewer que é onde chama o relátorio  
      JasperViewer view = new JasperViewer(jasperPrint,false);
      //Mostra o relátorio
      
      limparCampos();
      habilitaCampos();
      view.setVisible(true);
        }else{
            limparCampos();
            habilitaCampos();
            pesquisar_os(); 
        }
        
   }
   
   private void recuperaOs(){
       String sql = "SELECT max(os) from tbos";
       try {
           pst=conexao.prepareStatement(sql);
           rs=pst.executeQuery();
           if(rs.next()){
               txtOs.setText(rs.getString(1));
           }
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
       }
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbOsSit = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtOsVL = new javax.swing.JTextField();
        txtOsTec = new javax.swing.JTextField();
        txtOsEquip = new javax.swing.JTextField();
        txtOsDef = new javax.swing.JTextField();
        txtOsSer = new javax.swing.JTextField();
        btnOsAtu = new javax.swing.JButton();
        btnOsDel = new javax.swing.JButton();
        btnOsImp = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOS = new javax.swing.JTable();
        rbtOs = new javax.swing.JRadioButton();
        rbtOrc = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        txtCliId = new javax.swing.JTextField();
        txtCliNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCodOS = new javax.swing.JTextField();
        btnOsReturn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbExcluido = new javax.swing.JComboBox<>();
        txtCor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtImei1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtImei2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(709, 541));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel3.setText("Situação");

        cbOsSit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOsSitActionPerformed(evt);
            }
        });

        jLabel5.setText("* Marca");

        jLabel6.setText("* Defeito ");

        jLabel7.setText("Serviço");

        jLabel8.setText("Técnico");

        jLabel9.setText("Valor Total");

        txtOsVL.setToolTipText("0,00");

        txtOsEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsEquipActionPerformed(evt);
            }
        });

        btnOsAtu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/upOs.png"))); // NOI18N
        btnOsAtu.setToolTipText("Atualizar OS");
        btnOsAtu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAtu.setEnabled(false);
        btnOsAtu.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsAtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAtuActionPerformed(evt);
            }
        });

        btnOsDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/delOs.png"))); // NOI18N
        btnOsDel.setToolTipText("Excluir OS");
        btnOsDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsDel.setEnabled(false);
        btnOsDel.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsDelActionPerformed(evt);
            }
        });

        btnOsImp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/printer.png"))); // NOI18N
        btnOsImp.setToolTipText("Imprimir OS");
        btnOsImp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsImp.setEnabled(false);
        btnOsImp.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsImpActionPerformed(evt);
            }
        });

        tblOS = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código OS", "Data OS", "Tipo", "Situação", "Marca", "Modelo", "Cor", "Defeito", "Serviço", "Técnico", "Valor", "Código Cliente", "Imei1", "Imei2", "Situação OS", "Nome Cliente"
            }
        ));
        tblOS.getTableHeader().setReorderingAllowed(false);
        tblOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOS);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buttonGroup1.add(rbtOs);
        rbtOs.setText("Ordem de Serviço");
        rbtOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOsActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOrc);
        rbtOrc.setText("Orçamento");
        rbtOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcActionPerformed(evt);
            }
        });

        jLabel1.setText("Nº OS");

        jLabel2.setText("Data");

        txtOs.setEditable(false);
        txtOs.setEnabled(false);

        txtData.setEditable(false);
        txtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataActionPerformed(evt);
            }
        });

        jLabel4.setText("Cliente");

        txtCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisarActionPerformed(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        txtCliId.setEditable(false);

        txtCliNome.setEditable(false);
        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });

        jLabel10.setText("Código");

        txtCodOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodOSActionPerformed(evt);
            }
        });
        txtCodOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodOSKeyReleased(evt);
            }
        });

        btnOsReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/returnOs.png"))); // NOI18N
        btnOsReturn.setToolTipText("Atualizar OS");
        btnOsReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsReturn.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsReturnActionPerformed(evt);
            }
        });

        jLabel11.setText("* Modelo");

        jLabel12.setText("* Cor");

        cbExcluido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Excluído" }));

        txtCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorActionPerformed(evt);
            }
        });

        jLabel13.setText("Cod. cliente");

        jLabel14.setText("Imei1");

        txtImei1.setSelectionEnd(10);

        jLabel15.setText("Imei2");

        jLabel16.setText("* campos obrigatórios");

        jLabel17.setText("Sit. OS");

        jLabel18.setText("Cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodOS, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtModelo))
                                    .addComponent(txtOsDef)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                                    .addComponent(jLabel14))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtImei1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel15))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel9)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtOsVL, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtCor, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                                            .addComponent(txtImei2)))
                                    .addComponent(txtOsSer)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnOsReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOsAtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOsDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOsImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbtOrc)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(rbtOs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbExcluido, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(txtCliNome))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(txtCodOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbExcluido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOrc)
                    .addComponent(rbtOs)
                    .addComponent(jLabel3)
                    .addComponent(cbOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOsDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtOsSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtOsVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImei1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtImei2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOsAtu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsImp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsReturn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        setBounds(0, 0, 709, 541);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOsAtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAtuActionPerformed
        // TODO add your handling code here:
        alteraOs();
        
    }//GEN-LAST:event_btnOsAtuActionPerformed

    private void tblOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOSMouseClicked
        // TODO add your handling code here:
        setar_campo();
        btnOsDel.setEnabled(true);
        btnOsImp.setEnabled(true);
        btnOsAtu.setEnabled(true);
        btnOsReturn.setEnabled(false);
    }//GEN-LAST:event_tblOSMouseClicked

    private void rbtOrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOrcActionPerformed
        // adiciona um texto se a radio button estiver selecionado
        tipo = "orçamento";
    }//GEN-LAST:event_rbtOrcActionPerformed

    private void rbtOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOsActionPerformed
        // adiciona um texto se a radio button estiver selecionado
        tipo = "os";
    }//GEN-LAST:event_rbtOsActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // ao abrir o form marcar o radio button orçamento
        rbtOrc.setSelected(true);
        tipo = "orçamento";
        pesquisar_os();
        try {
            SituacaoDAO dao = new SituacaoDAO();
            List<Situacao> lista = dao.listsituacao();
            for(Situacao sit : lista){
                cbOsSit.addItem(sit.getSituacao());
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOsDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsDelActionPerformed
        // TODO add your handling code here:
        excluir();
    }//GEN-LAST:event_btnOsDelActionPerformed

    private void btnOsImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsImpActionPerformed
        try {
            // TODO add your handling code here:
            imprimirOs();
            pesquisar_os();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tela_OS_Detalhe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOsImpActionPerformed

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_os_cli();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void txtCodOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodOSActionPerformed

    private void txtCodOSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodOSKeyReleased
        // TODO add your handling code here:
        
         if(txtCodOS.getText().isEmpty()){
            pesquisar_os();
        }else{
            pesquisar_os_cod(); 
         }
    }//GEN-LAST:event_txtCodOSKeyReleased

    private void cbOsSitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOsSitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOsSitActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void btnOsReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsReturnActionPerformed
        // TODO add your handling code here:
        
        
            Tela_OS telaos = new Tela_OS();
            getParent().add(telaos);
            telaos.setVisible(true);
            this.dispose();
        
        
    }//GEN-LAST:event_btnOsReturnActionPerformed

    private void txtOsEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsEquipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsEquipActionPerformed

    private void txtCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsAtu;
    private javax.swing.JButton btnOsDel;
    private javax.swing.JButton btnOsImp;
    private javax.swing.JButton btnOsReturn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbExcluido;
    private javax.swing.JComboBox<String> cbOsSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton rbtOrc;
    private javax.swing.JRadioButton rbtOs;
    private javax.swing.JTable tblOS;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCodOS;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtImei1;
    private javax.swing.JTextField txtImei2;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtOsDef;
    private javax.swing.JTextField txtOsEquip;
    private javax.swing.JTextField txtOsSer;
    private javax.swing.JTextField txtOsTec;
    private javax.swing.JTextField txtOsVL;
    // End of variables declaration//GEN-END:variables
}
