/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.mag.telas;
import br.com.mag.classe.Pagamento;
import java.sql.*;
import br.com.mag.dal.ModuloConexao;
import br.com.mag.dao.PagamentoDAO;
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
public class Tela_Pagamento extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst =null;
    ResultSet rs = null;
    
    /**
     * Creates new form Tela_OS
     */
    public Tela_Pagamento() throws ClassNotFoundException {
        initComponents();
        this.conexao = ModuloConexao.Conector();
        
               
    }
    
    //metodo para pesquisar os
    private void pesquisar_pagamento_all(){
       try {
            PagamentoDAO dao = new PagamentoDAO();
            List<Pagamento> lista = dao.listOSAll();
            
                DefaultTableModel modelo = (DefaultTableModel)tblOS.getModel();
                modelo.setNumRows(0);
               
                for(Pagamento s: lista){
                   modelo.addRow(new Object[]{
                       s.getCodOs(),
                       s.getDataOs(),
                       s.getCodCliente(),
                       s.getCliente(),
                       s.getValor(),
                       s.getDataPagamento(),
                       s.getFormaPagamento()
                   });
                    
                }
            
        } catch (Exception e) {
        }
    }
    
     private void pesquisar_pagamento(){
       try {
          
           String codOs, codCliente;
           String cliente, dataOs1, dataOs2, dataPagamento1, dataPagamento2, formaPagamento;
           codOs = txtOsVL.getText();
           codCliente = txtCodOS.getText();
           cliente = txtCliPesquisar.getText();
           dataOs1 = txtDtOs1.getText();
           dataOs2 = txtDtOs2.getText();
           dataPagamento1 = txtPag1.getText();
           dataPagamento2 = txtPag2.getText();
           formaPagamento = cbPagPes.getSelectedItem().toString();
           PagamentoDAO dao = new PagamentoDAO();
            List<Pagamento> lista = dao.listOS(Integer.parseInt(codOs), Integer.parseInt(codCliente) , cliente + "%", dataOs1, dataOs2, dataPagamento1, dataPagamento2, formaPagamento);
            
                DefaultTableModel modelo = (DefaultTableModel)tblOS.getModel();
                modelo.setNumRows(0);
               
                for(Pagamento s: lista){
                   modelo.addRow(new Object[]{
                       s.getCodOs(),
                       s.getDataOs(),
                       s.getCodCliente(),
                       s.getCliente(),
                       s.getValor(),
                       s.getDataPagamento(),
                       s.getFormaPagamento()
                   });
                    
                }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

      public void setar_campo(){
       int setar = tblOS.getSelectedRow();
                
          
                txtOs.setText(tblOS.getModel().getValueAt(setar, 0).toString());
                txtData.setText(tblOS.getModel().getValueAt(setar, 1).toString());
                txtCliId.setText(tblOS.getModel().getValueAt(setar, 2).toString());
                txtCliNome.setText(tblOS.getModel().getValueAt(setar, 3).toString());
               
                   txtDataPag.setText(tblOS.getModel().getValueAt(setar, 5).toString()); 
                
                 btnOsAtu.setEnabled(true);  
                 btnOsExc.setEnabled(true);                
                 btnOsImp.setEnabled(true);
                
                cbForPag.setSelectedItem(tblOS.getModel().getValueAt(setar,6).toString());
                

                
    }
      
      public void limpaCampos(){
           txtOs.setText(null);
                txtData.setText(null);
                txtCliId.setText(null);
                txtCliNome.setText(null);
                txtDataPag.setText(null);
                cbForPag.setSelectedIndex(0);
                
                btnOsAtu.setEnabled(false);
                btnOsExc.setEnabled(false);
                btnOsImp.setEnabled(false);
      }
    
    private void alteraPagamento(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que quer fazer esse pagamento","Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
         
          Pagamento obj = new Pagamento();
          obj.setFormaPagamento(cbForPag.getSelectedItem().toString());
          obj.setDataOs(txtDataPag.getText());
          obj.setCodOs(Integer.parseInt(txtOs.getText()));
          if ((txtDataPag.getText().equals(" "))) {
             if(cbForPag.getSelectedItem().toString().equals("Em Aberto")){
                 JOptionPane.showMessageDialog(null, "escolha outra forma de pagamento");
             } else{
               PagamentoDAO dao = new PagamentoDAO();
               dao.alterarPagamento(obj);
               limpaCampos();
               pesquisar_pagamento_all();
               
             }
              
              
            } else{
               if(cbForPag.getSelectedItem().toString().equals("Em Aberto")){
                 JOptionPane.showMessageDialog(null, "escolha outra forma de pagamento");
             } else{
                 PagamentoDAO dao = new PagamentoDAO();
               dao.alterarPagamentoA(obj);
               limpaCampos();
               pesquisar_pagamento_all();
              
             }
             
              
            }
          
            
          }
        
        
    }
    
   private void excluirPagamento(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que quer excluir esse pagamento","Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
         
          Pagamento obj = new Pagamento();
          obj.setCodOs(Integer.parseInt(txtOs.getText()));
          
               PagamentoDAO dao = new PagamentoDAO();
               dao.excluirPagamento(obj);
               limpaCampos();
               pesquisar_pagamento_all();
  
                  
            
          }
        
        
    } 
    
   
    private void imprimirOs() throws ClassNotFoundException{
    int confirma = JOptionPane.showConfirmDialog(null,"confirma a impressão deste pagamento?","atenção",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
                this.conexao = ModuloConexao.Conector();
                //classe hashmap para criar um filtro
                HashMap filtro = new HashMap();
                filtro.put("os", Integer.parseInt(txtOs.getText()));
                
                //pega o caminho do relátorio
               String src = "src\\report\\pagamento\\pagamento.jasper"; 
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
      
      limpaCampos();
      view.setVisible(true);
        }else{
            limpaCampos();
            pesquisar_pagamento_all(); 
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
        cbForPag = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDataPag = new javax.swing.JTextField();
        btnOsExc = new javax.swing.JButton();
        btnOsImp = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        txtCliId = new javax.swing.JTextField();
        txtCliNome = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOS = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cbPagPes = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtCodOS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDtOs1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtOsVL = new javax.swing.JTextField();
        txtDtOs2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPag1 = new javax.swing.JTextField();
        txtPag2 = new javax.swing.JTextField();
        txtOs = new javax.swing.JTextField();
        btnOsAtu = new javax.swing.JButton();
        btnOsPes = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Pagamento");
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

        jLabel3.setText("Forma de Pagamento");

        cbForPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Aberto", "A Vista", "Pix", "Debito", "Cartão 1x", "Cartão 2x", "Cartão 3x", "Cartão 4x", "Cartão 5x", "Cartão 6x", "Cartão 7x", "Cartão 8x", "Cartão 9x", "Cartão 10x", "Cartão 11x", "Cartão 12x" }));
        cbForPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbForPagActionPerformed(evt);
            }
        });

        jLabel6.setText("Data de Pagamento ");

        txtDataPag.setEditable(false);
        txtDataPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataPagActionPerformed(evt);
            }
        });

        btnOsExc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/delOs.png"))); // NOI18N
        btnOsExc.setToolTipText("Excluir Pagamento");
        btnOsExc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsExc.setEnabled(false);
        btnOsExc.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsExc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsExcActionPerformed(evt);
            }
        });

        btnOsImp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/printer.png"))); // NOI18N
        btnOsImp.setToolTipText("Imprimir Pagamento");
        btnOsImp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsImp.setEnabled(false);
        btnOsImp.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsImpActionPerformed(evt);
            }
        });

        jLabel1.setText(" OS");

        jLabel2.setText("Data");

        txtData.setEditable(false);
        txtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataActionPerformed(evt);
            }
        });

        txtCliId.setEditable(false);
        txtCliId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliIdActionPerformed(evt);
            }
        });

        txtCliNome.setEditable(false);
        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });

        jLabel13.setText("Cod. cliente");

        jLabel18.setText("Cliente");

        tblOS = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. OS", "Data OS", "Cod. Cliente", "Cliente", "Valor", "Data de Pagamento", "Forma de Pagamento"
            }
        ));
        tblOS.getTableHeader().setReorderingAllowed(false);
        tblOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOS);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar"));

        jLabel17.setText("Forma de Pagamento");

        cbPagPes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Aberto", "A Vista", "Pix", "Debito", "Cartão 1x", "Cartão 2x", "Cartão 3x", "Cartão 4x", "Cartão 5x", "Cartão 6x", "Cartão 7x", "Cartão 8x", "Cartão 9x", "Cartão 10x", "Cartão 11x", "Cartão 12x" }));

        jLabel10.setText("Cod. Cliente ");

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

        jLabel5.setText("Data OS");

        txtDtOs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDtOs1ActionPerformed(evt);
            }
        });
        txtDtOs1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDtOs1KeyReleased(evt);
            }
        });

        jLabel9.setText("OS");

        txtOsVL.setToolTipText("0,00");

        txtDtOs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDtOs2ActionPerformed(evt);
            }
        });
        txtDtOs2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDtOs2KeyReleased(evt);
            }
        });

        jLabel7.setText("Data de Pagamento");

        txtPag1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPag1ActionPerformed(evt);
            }
        });

        txtPag2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPag2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(txtOsVL, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel10))
                            .addComponent(txtCodOS, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtPag1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPag2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbPagPes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(74, 74, 74))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDtOs1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDtOs2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(113, 113, 113))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDtOs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDtOs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPag1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPag2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOsVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPagPes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        txtOs.setEditable(false);
        txtOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsActionPerformed(evt);
            }
        });

        btnOsAtu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/upOs.png"))); // NOI18N
        btnOsAtu.setToolTipText("Atualizar Pagamento");
        btnOsAtu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAtu.setEnabled(false);
        btnOsAtu.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsAtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAtuActionPerformed(evt);
            }
        });

        btnOsPes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/searOs.png"))); // NOI18N
        btnOsPes.setToolTipText("Pesquisar Pagamento");
        btnOsPes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsPes.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsPes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsPesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel1))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOsAtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnOsExc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbForPag, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(btnOsImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDataPag, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(btnOsPes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(8, 8, 8))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbForPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDataPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOsExc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsAtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsPes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 709, 541);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOsExcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsExcActionPerformed
        // TODO add your handling code here:
        excluirPagamento();
    }//GEN-LAST:event_btnOsExcActionPerformed

    private void tblOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOSMouseClicked
        // TODO add your handling code here:
        setar_campo();
    }//GEN-LAST:event_tblOSMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // ao abrir o form marcar o radio button orçamento
        pesquisar_pagamento_all();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOsImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsImpActionPerformed
        
         try {
            // TODO add your handling code here:
            imprimirOs();
            pesquisar_pagamento_all();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tela_OS_Detalhe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOsImpActionPerformed

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_pagamento();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void txtCodOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodOSActionPerformed

    private void txtCodOSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodOSKeyReleased
        // TODO add your handling code here:
        
         
    }//GEN-LAST:event_txtCodOSKeyReleased

    private void cbForPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbForPagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbForPagActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void txtDtOs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDtOs1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDtOs1ActionPerformed

    private void txtDtOs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDtOs2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDtOs2ActionPerformed

    private void txtPag1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPag1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPag1ActionPerformed

    private void txtPag2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPag2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPag2ActionPerformed

    private void txtCliIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliIdActionPerformed

    private void txtOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsActionPerformed

    private void txtDataPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataPagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataPagActionPerformed

    private void btnOsAtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAtuActionPerformed
        // TODO add your handling code here:
        alteraPagamento();
    }//GEN-LAST:event_btnOsAtuActionPerformed

    private void txtDtOs1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDtOs1KeyReleased
        // TODO add your handling code here:
        pesquisar_pagamento();
    }//GEN-LAST:event_txtDtOs1KeyReleased

    private void txtDtOs2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDtOs2KeyReleased
        // TODO add your handling code here:
         pesquisar_pagamento();
    }//GEN-LAST:event_txtDtOs2KeyReleased

    private void btnOsPesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsPesActionPerformed
        // TODO add your handling code here:
        pesquisar_pagamento();
    }//GEN-LAST:event_btnOsPesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsAtu;
    private javax.swing.JButton btnOsExc;
    private javax.swing.JButton btnOsImp;
    private javax.swing.JButton btnOsPes;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbForPag;
    private javax.swing.JComboBox<String> cbPagPes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblOS;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCodOS;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDataPag;
    private javax.swing.JTextField txtDtOs1;
    private javax.swing.JTextField txtDtOs2;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtOsVL;
    private javax.swing.JTextField txtPag1;
    private javax.swing.JTextField txtPag2;
    // End of variables declaration//GEN-END:variables
}
