/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.mag.telas;

import br.com.mag.classe.Cliente;
import java.sql.*;

import br.com.mag.dal.ModuloConexao;
import javax.swing.JOptionPane;
import br.com.mag.classe.RemoveLetra;
import br.com.mag.dao.ClienteDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jonatha
 */
public class Tela_Cliente extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet  rs = null;

    /**
     * Creates new form Tela_Cliente
     */
    public Tela_Cliente() throws ClassNotFoundException {
        initComponents();
        this.conexao = ModuloConexao.Conector();
        txtCliFone.setDocument(new RemoveLetra(12));
        
        if(txtCliPesquisar.getText().isEmpty() || txtCodPesquisa.getText().isEmpty()){
            pesquisar_cliente_all();
        }
    }
    
    
    //metodo para adicionar Clientes na tabela tbclientess
    private void adicionar(){
       Cliente obj = new Cliente();
        obj.setNomecli(txtCliNome.getText());
        obj.setEndcli(txtCliEnde.getText());
        obj.setFonecli(txtCliFone.getText());
        obj.setEmailcli(txtCliEmail.getText());
        obj.setCgc(txtCGC.getText());
        obj.setCidadeCli(txtCidadeCli.getText());
        obj.setBairrocli(txtBairroCli.getText());
        
        if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {
                
             JOptionPane.showMessageDialog(null, "Preencha os compos obrigatórios");
            } else {
            
        try {
             
              ClienteDAO dao = new ClienteDAO();   
              dao.cadastrarCliente(obj);
               
                //listarStiuacao();
                 limparCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }
    
    //pesquisar cliente pelo nome 
    public void pesquisar_cliente(){
         try {
            String nomecli;
            nomecli = txtCliPesquisar.getText();
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> lista = dao.listCliente(nomecli + "%");
            
                DefaultTableModel modelo = (DefaultTableModel)tblClientes.getModel();
                modelo.setNumRows(0);
                
                for(Cliente s: lista){
                   modelo.addRow(new Object[]{
                       s.getIdcli(), 
                       s.getNomecli(),
                       s.getEndcli(),
                       s.getFonecli(),
                       s.getEmailcli(),
                       s.getCgc(),
                       s.getSitcli(),
                       s.getCidadeCli(),
                       s.getBairrocli()
                   });
                    
                }
            
        } catch (Exception e) {
        }
       
    }
    
     //pesquisar cliente pelo código 
    public void pesquisar_cliente_cod(){
         try {
            String idcli;
            idcli = txtCodPesquisa.getText();
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> lista = dao.listClienteCod(Integer.parseInt(idcli));
            
                DefaultTableModel modelo = (DefaultTableModel)tblClientes.getModel();
                modelo.setNumRows(0);
                
                for(Cliente s: lista){
                   modelo.addRow(new Object[]{
                       s.getIdcli(), 
                       s.getNomecli(),
                       s.getEndcli(),
                       s.getFonecli(),
                       s.getEmailcli(),
                       s.getCgc(),
                       s.getSitcli(),
                       s.getCidadeCli(),
                       s.getBairrocli()
                   });
                    
                }
            
        } catch (Exception e) {
        }
       
    }
    
        private void pesquisar_cliente_all(){
         try {
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> lista = dao.listClienteAll();
            
                DefaultTableModel modelo = (DefaultTableModel)tblClientes.getModel();
                modelo.setNumRows(0);
                
                for(Cliente s: lista){
                   modelo.addRow(new Object[]{
                       s.getIdcli(), 
                       s.getNomecli(),
                       s.getEndcli(),
                       s.getFonecli(),
                       s.getEmailcli(),
                       s.getCgc(),
                       s.getSitcli(),
                       s.getCidadeCli(),
                       s.getBairrocli()
                   });
                    
                }
            
        } catch (Exception e) {
        }
    
    }
   
    
    // popular o comapos de texto para conseguir editar da tabela
    public void setar_campo(){
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliEnde.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        txtCGC.setText(tblClientes.getModel().getValueAt(setar, 5).toString());
        cbSitCli.setSelectedItem(tblClientes.getModel().getValueAt(setar, 6).toString());
        txtCidadeCli.setText(tblClientes.getModel().getValueAt(setar, 7).toString());
        txtBairroCli.setText(tblClientes.getModel().getValueAt(setar, 8).toString());
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        cbSitCli.setEnabled(true);
    }
    
    private void atualizar(){
        
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja atualizar o cadastro do Cliente","Atenção",JOptionPane.YES_NO_OPTION);
        
        if(confirma == JOptionPane.YES_OPTION){
        Cliente obj = new Cliente();
        obj.setNomecli(txtCliNome.getText());
        obj.setEndcli(txtCliEnde.getText());
        obj.setFonecli(txtCliFone.getText());
        obj.setEmailcli(txtCliEmail.getText());
        obj.setCgc(txtCGC.getText());
        obj.setSitcli(cbSitCli.getSelectedItem().toString());
        obj.setCidadeCli(txtCidadeCli.getText());
        obj.setBairrocli(txtBairroCli.getText());
        obj.setIdcli(Integer.parseInt(txtCliId.getText()));
        
        if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {
                
             JOptionPane.showMessageDialog(null, "Preencha os compos obrigatórios");
            } else {
            
        try {
             
              ClienteDAO dao = new ClienteDAO();   
              dao.EditarCliente(obj);
               
                //listarStiuacao();
                 limparCampos();
                 pesquisar_cliente_all();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
       
    }
   }

    private void deletar(){
         
         int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o Cliente selecionado","Atenção",JOptionPane.YES_NO_OPTION);
        
         if(confirma == JOptionPane.YES_OPTION){
         
        Cliente obj = new Cliente();
        obj.setIdcli(Integer.parseInt(txtCliId.getText()));
        
        try {
             
              ClienteDAO dao = new ClienteDAO();   
              dao.deleteCliente(obj);
               
                //listarStiuacao();
                 limparCampos();
                 pesquisar_cliente_all();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
         
             
         }
    }
    
    public void limparCampos(){
             txtCliPesquisar.setText(null);
             txtCliId.setText(null);
             txtCliNome.setText(null);
             txtCliFone.setText(null);
             txtCliEnde.setText(null);
             txtCliEmail.setText(null);
             txtCGC.setText(null);
             cbSitCli.setSelectedIndex(0);
             txtCidadeCli.setText(null);
             txtBairroCli.setText(null);
             
             btnAdd.setEnabled(true);
             btnDelete.setEnabled(false);
             btnUpdate.setEnabled(false);
             cbSitCli.setEnabled(false);
                     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCliPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtCliNome = new javax.swing.JTextField();
        txtCliEnde = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCGC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCodPesquisa = new javax.swing.JTextField();
        cbSitCli = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCidadeCli = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBairroCli = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(709, 541));

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

        tblClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Endereço", "Telefone", "E-mail", "CGC", "Situação", "Cidade", "Bairro"
            }
        ));
        tblClientes.setFocusable(false);
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel2.setText("* Campos obrigatórios ");

        jLabel3.setText("* Nome");

        jLabel4.setText("Endereço");

        jLabel5.setText("* Telefone");

        jLabel6.setText("E-mail");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/addUser.png"))); // NOI18N
        btnAdd.setMaximumSize(new java.awt.Dimension(48, 48));
        btnAdd.setMinimumSize(new java.awt.Dimension(48, 48));
        btnAdd.setPreferredSize(new java.awt.Dimension(48, 48));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/upUser.png"))); // NOI18N
        btnUpdate.setEnabled(false);
        btnUpdate.setMaximumSize(new java.awt.Dimension(48, 48));
        btnUpdate.setMinimumSize(new java.awt.Dimension(48, 48));
        btnUpdate.setPreferredSize(new java.awt.Dimension(48, 48));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/delUser.png"))); // NOI18N
        btnDelete.setEnabled(false);
        btnDelete.setMaximumSize(new java.awt.Dimension(48, 48));
        btnDelete.setMinimumSize(new java.awt.Dimension(48, 48));
        btnDelete.setPreferredSize(new java.awt.Dimension(48, 48));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtCliEnde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliEndeActionPerformed(evt);
            }
        });

        jLabel7.setText("Código");

        txtCliId.setEnabled(false);
        txtCliId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliIdActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        jLabel8.setText("CPF/CNPJ");

        jLabel9.setText("Código");

        txtCodPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodPesquisaActionPerformed(evt);
            }
        });
        txtCodPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodPesquisaKeyReleased(evt);
            }
        });

        cbSitCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Excluído" }));
        cbSitCli.setEnabled(false);

        jLabel10.setText("Situação Cliente");

        jLabel11.setText("Cidade");

        jLabel12.setText("Bairro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cbSitCli, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCliNome)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCliFone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addComponent(txtCliEnde, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCidadeCli)
                            .addComponent(txtBairroCli, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                    .addComponent(txtCliEmail)
                    .addComponent(txtCGC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(txtCodPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSitCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtCidadeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtCliEnde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtBairroCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCGC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );

        setBounds(0, 0, 709, 541);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // chama metodo alterar cliente
        atualizar();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtCliEndeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliEndeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliEndeActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Chama metodo de adicionar cliente
        adicionar();
        pesquisar_cliente_all();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // O evento faz a atualização em tempo real
        // chama metodo de consultar cliente
        pesquisar_cliente();
        
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // Envento que seleciona usando o click do mouse
        //chama metodo de setar campo
        
        setar_campo();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtCliIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliIdActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtCodPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodPesquisaActionPerformed

    private void txtCodPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodPesquisaKeyReleased
        // TODO add your handling code here:
        
        if(txtCodPesquisa.getText().isEmpty()){
            pesquisar_cliente_all();
        }else{
           pesquisar_cliente_cod(); 
        }
    }//GEN-LAST:event_txtCodPesquisaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbSitCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBairroCli;
    private javax.swing.JTextField txtCGC;
    private javax.swing.JTextField txtCidadeCli;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEnde;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCodPesquisa;
    // End of variables declaration//GEN-END:variables
}
