/*
 *
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.mag.telas;
import br.com.mag.classe.Cliente;
import br.com.mag.classe.OS;
import java.sql.*;
import br.com.mag.dal.ModuloConexao;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.mag.classe.Situacao;
import br.com.mag.dao.ClienteDAO;
import br.com.mag.dao.OSDAO;
import br.com.mag.dao.SituacaoDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jonatha
 */
public class Tela_OS extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst =null;
    ResultSet rs = null;
    
    private String tipo;

    /**
     * Creates new form Tela_OS
     */
    public Tela_OS() {
        initComponents();
        this.conexao = ModuloConexao.Conector();
        
    }
    
    //pesquisar cliente pelo nome 
    public void pesquisar_cliente(){
         try {
            String nomecli;
            nomecli = txtCliPesquisar.getText();
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> lista = dao.listClienteEx(nomecli + "%");
            
                DefaultTableModel modelo = (DefaultTableModel)tblClientes.getModel();
                modelo.setNumRows(0);
                
                for(Cliente s: lista){
                   modelo.addRow(new Object[]{
                       s.getIdcli(), 
                       s.getNomecli(),
                       s.getFonecli()
                   });
                    
                }
            
        } catch (Exception e) {
        }
       
    }

    private void setar_campos(){
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
    }
    
    //metodo para adicionar os na tabela tbos
    private void adicionar(){
        
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
       obj.setIdcli(Integer.parseInt(txtCliId.getText()));
             
             if ((txtCliId.getText().isEmpty()) || (txtOsEquip.getText().isEmpty()) || (txtOsDef.getText().isEmpty()) || (txtModelo.getText().isEmpty()) || (txtCor.getText().isEmpty())) {
                
             JOptionPane.showMessageDialog(null, "Preencha os compos obrigatórios");
            } else if(cbOsSit.getSelectedItem().toString().equals("Selecione uma Situação") ) {
              JOptionPane.showMessageDialog(null, "Selecione outra Situação");
                 
            }else{
               OSDAO dao = new OSDAO();
               dao.cadastrarOS(obj);
               limparCampos();
               recuperaOs();
               btnOsImp.setEnabled(true);

            
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
             
             ((DefaultTableModel) tblClientes.getModel()).setRowCount(0);
             
             
    }
    
    
    
    private void habilitaCampos(){
        
                   txtCliId.setEnabled(true);
                   btnOsAdd.setEnabled(true);
                   txtCliPesquisar.setEnabled(true);
                   tblClientes.setVisible(true);
                   
                   btnOsImp.setEnabled(false);
                   btnOsAtu.setEnabled(true);
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
      view.setVisible(true);
      limparCampos();
      habilitaCampos();
        }
        
   }
   
   
       
   
   private void recuperaOs(){
       try {
           
       
       String sql = "SELECT max(os) from tbos";
        
         
         PreparedStatement pst = conexao.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               txtOs.setText(rs.getString(1));
            }
     } catch (Exception e) {
       
     }
       
       //  public void recuperar(){
   //      OS obj = new OS();
   //      obj.setOs(txtOs.getText());
   //  }
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        rbtOrc = new javax.swing.JRadioButton();
        rbtOs = new javax.swing.JRadioButton();
        txtData = new javax.swing.JTextField();
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
        btnOsAdd = new javax.swing.JButton();
        btnOsAtu = new javax.swing.JButton();
        btnOsImp = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtCliPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtImei1 = new javax.swing.JTextField();
        txtImei2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCor = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº OS");

        jLabel2.setText("Data");

        txtOs.setEnabled(false);

        buttonGroup1.add(rbtOrc);
        rbtOrc.setText("Orçamento");
        rbtOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOs);
        rbtOs.setText("Ordem de Serviço");
        rbtOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOsActionPerformed(evt);
            }
        });

        txtData.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(52, 52, 52)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(rbtOrc)
                        .addGap(18, 18, 18)
                        .addComponent(rbtOs)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOrc)
                    .addComponent(rbtOs))
                .addContainerGap(15, Short.MAX_VALUE))
        );

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

        txtOsVL.setText("0,00");

        btnOsAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/addOs.png"))); // NOI18N
        btnOsAdd.setToolTipText("Incluir OS");
        btnOsAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAdd.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAddActionPerformed(evt);
            }
        });

        btnOsAtu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mag/icone/searOs.png"))); // NOI18N
        btnOsAtu.setToolTipText("Atualizar OS");
        btnOsAtu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAtu.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsAtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAtuActionPerformed(evt);
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

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

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nome", "Telefone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel4.setText("* Código");

        txtCliId.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel10.setText("Imei1");

        jLabel11.setText("Imei2");

        txtImei2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImei2ActionPerformed(evt);
            }
        });

        jLabel12.setText("* Modelo");

        jLabel13.setText("* Cor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnOsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(btnOsAtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtImei2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnOsImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(161, 161, 161))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(12, 12, 12)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtImei1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtOsSer)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtOsVL, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtOsDef))
                                        .addGap(23, 23, 23))))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOsDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtOsSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOsVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtImei1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImei2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOsAtu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOsImp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOsAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        setBounds(0, 0, 709, 541);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void btnOsAtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAtuActionPerformed
        // TODO add your handling code here:
        
        
            
        try {
            Tela_OS_Detalhe teladet = new Tela_OS_Detalhe();
            getParent().add(teladet);
            teladet.setVisible(true);
            this.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tela_OS.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }//GEN-LAST:event_btnOsAtuActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnOsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAddActionPerformed
        // chama o metodo de adicionar os
         if ((txtCliId.getText().isEmpty()) || (txtOsEquip.getText().isEmpty()) || (txtOsDef.getText().isEmpty()) || (txtModelo.getText().isEmpty()) || (txtCor.getText().isEmpty())) {
                
             JOptionPane.showMessageDialog(null, "Preencha os compos obrigatórios");
            }else{
                adicionar();
         }
        
     
    }//GEN-LAST:event_btnOsAddActionPerformed

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
        try {
            SituacaoDAO dao = new SituacaoDAO();
            List<Situacao> lista = dao.listsituacao();
            for(Situacao sit : lista){
                cbOsSit.addItem(sit.getSituacao());
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOsImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsImpActionPerformed
        try {
            // TODO add your handling code here:
            imprimirOs();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tela_OS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOsImpActionPerformed

    private void txtImei2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImei2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImei2ActionPerformed

    private void cbOsSitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOsSitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOsSitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsAdd;
    private javax.swing.JButton btnOsAtu;
    private javax.swing.JButton btnOsImp;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbOsSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtOrc;
    private javax.swing.JRadioButton rbtOs;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliPesquisar;
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
