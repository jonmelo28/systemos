/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.mag.telas;

import br.com.mag.classe.Usuario;
import br.com.mag.dal.ModuloConexao;
import br.com.mag.dao.UsuarioDAO;
import java.sql.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jonatha
 */
public class Tela_Usuario extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;    
    ResultSet rs = null;

    /**
     * Creates new form Tela_Usuarrio
     */
    public Tela_Usuario() throws ClassNotFoundException {
        initComponents();
        
         this.conexao = ModuloConexao.Conector();
         
         if(txtUsuPesquisar.getText().isEmpty()){
            consultar();
        }
    }
    
    private void consultar(){
        
        
        try {
        UsuarioDAO dao = new UsuarioDAO();  
        List<Usuario> lista = dao.listUsuarioAll();
         DefaultTableModel modelo = (DefaultTableModel)tblUsuario.getModel();
                modelo.setNumRows(0);
           for(Usuario s: lista){
                   modelo.addRow(new Object[]{
                       s.getIdUser(),
                       s.getUsuario(),
                       s.getFone(),
                       s.getLogin(),
                       s.getPerfil(),
                       s.getExcluido()
                   });
                   
                    
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void consultar_os_cod(){
        try {
            String idUser;
            idUser = txtUsuPesquisar.getText();
        UsuarioDAO dao = new UsuarioDAO();  
        List<Usuario> lista = dao.listUsuarioCod(Integer.parseInt(idUser));
         DefaultTableModel modelo = (DefaultTableModel)tblUsuario.getModel();
                modelo.setNumRows(0);
           for(Usuario s: lista){
                   modelo.addRow(new Object[]{
                       s.getIdUser(),
                       s.getUsuario(),
                       s.getFone(),
                       s.getLogin(),
                       s.getPerfil(),
                       s.getExcluido()
                   });
                   
                    
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void consultar_os_nome(){
         try {
            String usuario;
            usuario = txtUsuCliPesquisar.getText();
        UsuarioDAO dao = new UsuarioDAO();  
        List<Usuario> lista = dao.listUsuario(usuario + "%");
         DefaultTableModel modelo = (DefaultTableModel)tblUsuario.getModel();
                modelo.setNumRows(0);
           for(Usuario s: lista){
                   modelo.addRow(new Object[]{
                       s.getIdUser(),
                       s.getUsuario(),
                       s.getFone(),
                       s.getLogin(),
                       s.getPerfil(),
                       s.getExcluido()
                   });
                   
                    
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    //metodo para adicionar usuários na tabela tbusuarios
    private void adicionar(){
        Usuario usur = new Usuario();
        usur.setUsuario(txtUsuNome.getText());
        usur.setFone(txtUsuFone.getText());
        usur.setLogin(txtUsuLogin.getText());
        usur.setSenha(txtUsuSenha.getText());
        usur.setPerfil(cbUsuPerfil.getSelectedItem().toString());
        usur.setExcluido(cbUsuEx.getSelectedItem().toString());
        
    
            
             if ((txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty() || (txtUsuSenha.getText().isEmpty()))) {
                
             JOptionPane.showMessageDialog(null, "Preencha os compos obrigatórios");
            } else if(cbUsuPerfil.getSelectedItem().toString().equals("Escolha uma opção")) {
            
               JOptionPane.showMessageDialog(null, "O campo de perifl deve ser seleciona: Administrador ou Usuario"); 
            
             }else{
            try {
             
              UsuarioDAO dao = new UsuarioDAO();   
              dao.cadastrarUsuario(usur);
              
              limpacampos();
            }catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
               
            
             }   
            
        
    }
    
    private void atualizar(){
             
        if(txtUsuSenha.getText().isEmpty()){
        Usuario usur = new Usuario();
        usur.setUsuario(txtUsuNome.getText());
        usur.setFone(txtUsuFone.getText());
        usur.setLogin(txtUsuLogin.getText());
        usur.setPerfil(cbUsuPerfil.getSelectedItem().toString());
        usur.setExcluido(cbUsuEx.getSelectedItem().toString());
        usur.setIdUser(Integer.parseInt(txtUsuCodigo.getText()));
        
         if ((txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty())) {
                
             JOptionPane.showMessageDialog(null, "Preencha os compos obrigatórios");
            } else if(cbUsuPerfil.getSelectedItem().toString().equals("Escolha uma opção")) {
            
               JOptionPane.showMessageDialog(null, "O campo de perifl deve ser seleciona: Administrador ou Usuario"); 
            
             }else{
               try {
             
              UsuarioDAO dao = new UsuarioDAO();   
              dao.EditarUsuario(usur);
              
              limpacampos();
            }catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
            } 
          }
        
        
        }else{
        
        Usuario usur = new Usuario();
        usur.setUsuario(txtUsuNome.getText());
        usur.setFone(txtUsuFone.getText());
        usur.setLogin(txtUsuLogin.getText());
        usur.setSenha(txtUsuSenha.getText());
        usur.setPerfil(cbUsuPerfil.getSelectedItem().toString());
        usur.setExcluido(cbUsuEx.getSelectedItem().toString());
        usur.setIdUser(Integer.parseInt(txtUsuCodigo.getText()));
        
         if ((txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty())) {
                
             JOptionPane.showMessageDialog(null, "Preencha os compos obrigatórios");
            } else if(cbUsuPerfil.getSelectedItem().toString().equals("Escolha uma opção")) {
            
               JOptionPane.showMessageDialog(null, "O campo de perifl deve ser seleciona: Administrador ou Usuario"); 
            
             }else{
               try {
             
              UsuarioDAO dao = new UsuarioDAO();   
              dao.EditarUsuarioCS(usur);
              
              limpacampos();
            }catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
            } 
          }
        }
    }
    
     private void deletar(){
         
         int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o usuário","Atenção",JOptionPane.YES_NO_OPTION);
        
         if(confirma == JOptionPane.YES_OPTION){
         Usuario usur = new Usuario();
         usur.setIdUser(Integer.parseInt(txtUsuCodigo.getText()));
          try {
             
              UsuarioDAO dao = new UsuarioDAO();   
              dao.deleteUsuario(usur);
              
              limpacampos();
            }catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
            } 
      
         }  
    }
     
      public void setar_campo(){
       int setar = tblUsuario.getSelectedRow();
                txtUsuCodigo.setText(tblUsuario.getModel().getValueAt(setar, 0).toString());
                txtUsuNome.setText(tblUsuario.getModel().getValueAt(setar, 1).toString());
                txtUsuFone.setText(tblUsuario.getModel().getValueAt(setar, 2).toString());
                txtUsuLogin.setText(tblUsuario.getModel().getValueAt(setar, 3).toString());
                cbUsuPerfil.setSelectedItem(tblUsuario.getModel().getValueAt(setar, 4).toString());
                cbUsuEx.setSelectedItem(tblUsuario.getModel().getValueAt(setar, 5).toString());
                
                BtnUsuAdd.setEnabled(false);
                btnUsuDelete.setEnabled(true);
                btnUsuUpdate.setEnabled(true);
                cbUsuEx.setEnabled(true);
                
      }
      
      public void limpacampos(){
          //limpa os campos
             txtUsuNome.setText(null);
             txtUsuCodigo.setText(null);
             txtUsuFone.setText(null);
             txtUsuLogin.setText(null);
             txtUsuSenha.setText(null);
             cbUsuPerfil.setSelectedItem("Escolha uma opção");
             cbUsuEx.setSelectedItem("Não");
             
             
                BtnUsuAdd.setEnabled(true);
                btnUsuDelete.setEnabled(false);
                btnUsuUpdate.setEnabled(false);
                cbUsuEx.setEnabled(false);
      }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbUsuPerfil = new javax.swing.JComboBox<>();
        txtUsuSenha = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuFone = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuCodigo = new javax.swing.JTextField();
        BtnUsuAdd = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtUsuPesquisar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUsuCliPesquisar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbUsuEx = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");

        jLabel1.setText("Código");

        jLabel2.setText("* Nome");

        jLabel3.setText("Fone");

        jLabel4.setText("* Senha");

        jLabel5.setText("* Login");

        jLabel6.setText("* Perfil");

        cbUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha uma opção", "Administrador", "Usuario" }));

        txtUsuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuSenhaActionPerformed(evt);
            }
        });

        txtUsuFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuFoneActionPerformed(evt);
            }
        });

        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });

        txtUsuCodigo.setEnabled(false);

        BtnUsuAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/addUser.png"))); // NOI18N
        BtnUsuAdd.setToolTipText("adicionar");
        BtnUsuAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnUsuAdd.setPreferredSize(new java.awt.Dimension(80, 80));
        BtnUsuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUsuAddActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/upUser.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setEnabled(false);
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/delUser.png"))); // NOI18N
        btnUsuDelete.setToolTipText("deletar");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setEnabled(false);
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("* campos obrigatórios");

        tblUsuario = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Telefone", "Usuário", "Perfil", "Excluído"
            }
        ));
        tblUsuario.setFocusable(false);
        tblUsuario.getTableHeader().setReorderingAllowed(false);
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuario);

        jLabel8.setText("Código");

        txtUsuPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuPesquisarKeyReleased(evt);
            }
        });

        jLabel9.setText("Usuário");

        txtUsuCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuCliPesquisarKeyReleased(evt);
            }
        });

        jLabel10.setText("Excluido");

        cbUsuEx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não", "Sim" }));
        cbUsuEx.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsuPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsuCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(cbUsuEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel7))
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(BtnUsuAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbUsuPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtUsuPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtUsuCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUsuCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(cbUsuEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUsuAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        setBounds(0, 0, 709, 541);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuSenhaActionPerformed

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuNomeActionPerformed

    private void txtUsuFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuFoneActionPerformed

    private void BtnUsuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUsuAddActionPerformed
        // chama o metodo para adicionar o usuário no banco
        adicionar();
        consultar();
        
    }//GEN-LAST:event_BtnUsuAddActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // chama o metodo para atualizar o usuário no banco
        atualizar();
        consultar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // chama o metodo para deletar um registro da tabela de usuário n o banco
        deletar();
        consultar();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void txtUsuPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuPesquisarKeyReleased
        // TODO add your handling code here:
       if(txtUsuPesquisar.getText().isEmpty()){
             consultar();
        }else{
             consultar_os_cod(); 
         }
       
    }//GEN-LAST:event_txtUsuPesquisarKeyReleased

    private void txtUsuCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuCliPesquisarKeyReleased
        // TODO add your handling code here:
        consultar_os_nome();
    }//GEN-LAST:event_txtUsuCliPesquisarKeyReleased

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        // TODO add your handling code here:
        setar_campo();
    }//GEN-LAST:event_tblUsuarioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnUsuAdd;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cbUsuEx;
    private javax.swing.JComboBox<String> cbUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtUsuCliPesquisar;
    private javax.swing.JTextField txtUsuCodigo;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuPesquisar;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
