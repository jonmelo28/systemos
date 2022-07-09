/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.mag.telas;

import br.com.mag.classe.Filial;
import br.com.mag.dao.FilialDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.com.mag.classe.ManipularImagem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Jonatha
 */
public class Tela_Filial extends javax.swing.JInternalFrame {

    /**
     * Creates new form Tela_Filial
     */
     BufferedImage imagem;
    public Tela_Filial() {
        initComponents();
    }
    
    public void listarFilial(){
         try {
            FilialDAO dao = new FilialDAO();
            List<Filial> lista = dao.listFilial();
            
                DefaultTableModel modelo = (DefaultTableModel)tbFilial.getModel();
                modelo.setNumRows(0);
                
                for(Filial s: lista){
                   modelo.addRow(new Object[]{
                       s.getEmpresa(), 
                       s.getEndereco(),
                       s.getBairro(),
                       s.getCnpj(),
                       s.getCep(),
                       s.getCidade(),
                       s.getFone(),
                       s.getSite()
                   });
                    
                }
            
        } catch (Exception e) {
        }
    }
    
    public void img(){
        FilialDAO dao = new FilialDAO();
        Filial exe = dao.buscar();
        ManipularImagem.exibiImagemLabel(exe.getImagem(), lblImagem);
        
    }
    
    public void limpaCampos(){
        txtEmpresa.setText(null);
        txtEndereco.setText(null);
        txtBairro.setText(null);
        txtCnpj.setText(null);
        txtCep.setText(null);
        txtCidade.setText(null);
        txtTelefone.setText(null);
        txtSite.setText(null);
        
        btnUpdate.setEnabled(false);
        btnSalvarImagem.setEnabled(false);
    }
    
     public void setar_campo(){
        int setar = tbFilial.getSelectedRow();
        txtEmpresa.setText(tbFilial.getModel().getValueAt(setar, 0).toString());
        txtEndereco.setText(tbFilial.getModel().getValueAt(setar, 1).toString());
        txtBairro.setText(tbFilial.getModel().getValueAt(setar, 2).toString());
        txtCnpj.setText(tbFilial.getModel().getValueAt(setar, 3).toString());
        txtCep.setText(tbFilial.getModel().getValueAt(setar, 4).toString());
        txtCidade.setText(tbFilial.getModel().getValueAt(setar, 5).toString());
        txtTelefone.setText(tbFilial.getModel().getValueAt(setar, 6).toString());
        txtSite.setText(tbFilial.getModel().getValueAt(setar, 7).toString());
        
       btnUpdate.setEnabled(true);
       //ImageIcon figura = new ImageIcon("C:\\Users\\Jonatha\\Documents\\NetBeansProjects\\Teste\\build\\classes\\br\\com\\mag\\icone\\image.jpg");
       //String caminho = getClass().getResource("/br/com/mag/icone/").toString().substring(5);
       //File outputfile = new File(caminho+"image.jpg");
       //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/me/myimageapp/newpackage/image.png")));
       lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/image.png")));
    }
     
    public void updateFilial(){ 
    int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja atualizar a situação","Atenção",JOptionPane.YES_NO_OPTION);
        
         if(confirma == JOptionPane.YES_OPTION){
         Filial obj = new Filial();
         obj.setEmpresa(txtEmpresa.getText());
         obj.setEndereco(txtEndereco.getText());
         obj.setBairro(txtBairro.getText());
         obj.setCnpj(txtCnpj.getText());
         obj.setCep(txtCep.getText());
         obj.setCidade(txtCidade.getText());
         obj.setFone(txtTelefone.getText());
         obj.setSite(txtSite.getText());
         try {
              FilialDAO dao = new FilialDAO();   
              dao.editarFilial(obj);
              
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
            listarFilial();
            limpaCampos();
         }else{
             limpaCampos();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        txtEndereco = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtSite = new javax.swing.JTextField();
        txtCnpj = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFilial = new javax.swing.JTable();
        txtCidade = new javax.swing.JTextField();
        lblImagem = new javax.swing.JLabel();
        btnSalvarImagem = new javax.swing.JButton();
        btnSelectImagem1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Empresa");
        setPreferredSize(new java.awt.Dimension(709, 541));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
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
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("BAIRRO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, 20));

        jLabel2.setText("EMPRESA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 20));

        jLabel3.setText("TELEFONE");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, 20));

        jLabel4.setText("SITE");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, 20));

        jLabel5.setText("ENDEREÇO");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, 20));

        jLabel6.setText("CNPJ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 46, 20));

        jLabel7.setText("CEP");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 38, 20));

        jLabel8.setText("CIDADE");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, 20));

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/upOs.png"))); // NOI18N
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(txtEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 320, -1));

        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 320, -1));
        getContentPane().add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 320, -1));

        txtSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSiteActionPerformed(evt);
            }
        });
        getContentPane().add(txtSite, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 320, -1));

        txtCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnpjActionPerformed(evt);
            }
        });
        getContentPane().add(txtCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 139, 320, -1));
        getContentPane().add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 320, -1));
        getContentPane().add(txtEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 320, -1));

        tbFilial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "EMPRESA", "ENDEREÇO", "BAIRRO", "CNPJ", "CEP", "CIDADE", "TELEFONE", "SITE"
            }
        ));
        tbFilial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFilialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbFilial);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 622, 45));
        getContentPane().add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 320, -1));
        getContentPane().add(lblImagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 150, 170));

        btnSalvarImagem.setText("SALVAR FOTO");
        btnSalvarImagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvarImagem.setEnabled(false);
        btnSalvarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarImagemActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvarImagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 130, -1));

        btnSelectImagem1.setText("SELECIONAR FOTO");
        btnSelectImagem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelectImagem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectImagem1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSelectImagem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, -1, -1));

        setBounds(0, 0, 709, 541);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void txtCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjActionPerformed

    private void btnSalvarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarImagemActionPerformed
        // TODO add your handling code here:
      try {
            
            Filial obj = new Filial();
            obj.setImagem(ManipularImagem.getImgBytes(imagem));
            FilialDAO dao = new FilialDAO();
            dao.update(obj);
                       
            
         } catch (Exception ex) {
             Logger.getLogger(Tela_Filial.class.getName()).log(Level.SEVERE, null, ex);
         }
         btnSalvarImagem.setEnabled(false);
       
    }//GEN-LAST:event_btnSalvarImagemActionPerformed

    private void txtSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSiteActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        listarFilial();
        img();
        //lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/image.png")));
        
    }//GEN-LAST:event_formInternalFrameActivated

    private void tbFilialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFilialMouseClicked
        // TODO add your handling code here:
        setar_campo();
    }//GEN-LAST:event_tbFilialMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updateFilial();
         
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSelectImagem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectImagem1ActionPerformed
        // TODO add your handling code here:
        btnSalvarImagem.setEnabled(true);
         JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();

            try {
                imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 160, 160);

                lblImagem.setIcon(new ImageIcon(imagem));
                
            } catch (Exception ex) {
               // System.out.println(ex.printStackTrace().toString());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
        }
    }//GEN-LAST:event_btnSelectImagem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvarImagem;
    private javax.swing.JButton btnSelectImagem1;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JTable tbFilial;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCnpj;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtSite;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
