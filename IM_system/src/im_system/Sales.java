/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package im_system;

import java.awt.print.PrinterException;
import javax.swing.JFrame;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class Sales extends javax.swing.JFrame {

    private Date Date;

    /**
     * Creates new form im_system
     */
    public Sales() {
        initComponents();
        Connect();
        Fetch();
        date();
        Combobox();
        Save();
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model;
    TableRowSorter trs;
    Statement st;
    
    
    int rowQuan;
    int origQuan;
    float tot = 0;
    float totalAmount = 0;
    float orderTotal = 0;
    float value;
    float discount;
    float paidAmount;
    float Total;
    int id;
    int quants;
    
    String ProdID = "";
    String ProductID = "";
    
    
    private void Connect() {
     try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/im1","root","");
        }       catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     private void Fetch(){
        try {
            int q;
            pst = con.prepareStatement("SELECT * FROM products_tbl");
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
             DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            while(rs.next()){
                Vector v2 = new Vector();
                for(int a=1; a<=q; a++){
                    v2.add(rs.getString("pid"));
                    v2.add(rs.getString("cat"));
                    v2.add(rs.getString("pname"));
                    v2.add(rs.getString("description"));
                    v2.add(rs.getString("qty"));
                    v2.add(rs.getString("rprice"));
                    v2.add(rs.getString("date"));
                }
                 df.addRow(v2);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     private void Combobox(){
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/im1","root","");
            String sql = "SELECT * FROM cat_tbl";
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String name = rs.getString("cat_name");
                txtCat.addItem(name);
            }

            
            } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
         public void alert1(String msg)
    {
        JOptionPane.showMessageDialog(rootPane, msg);
    }
         
//         private void filter(String query)
//         {
//             TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
//             jTable1.setRowSorter(trs);
//             
//             if(query != "None")
//             {
//                 trs.setRowFilter(RowFilter.regexFilter(query));
//             }else{
//                 jTable1.setRowSorter(trs);
//             }
//         }
     

     public void search(String str)
     {
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
         TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
         jTable1.setRowSorter(trs);
         
         trs.setRowFilter(RowFilter.regexFilter(str));
     }
     
        private void date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now  = LocalDateTime.now();
        DateTime.setText(dtf.format(now));
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        private void Save(){
        try {
                    String sql = "SELECT max(id) FROM sales_tbl";
                    st = con.createStatement();
                    rs = st.executeQuery(sql);
                if (rs.next())
                {
                     this.id = rs.getInt(1);
                }
                } catch (SQLException ex) {
                    Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
     public void bill_print(){
 
 
            bill.setText("                        KumaTech \n");
            bill.setText(bill.getText() + "\tBogo City, Cebu \n");
            bill.setText(bill.getText() + "\t09123456789 \n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            bill.setText(bill.getText() + " Iteam \tQty \tPrice  \n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            
            DefaultTableModel df = (DefaultTableModel) jTable2.getModel();
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                
                String Pname = df.getValueAt(i, 2).toString();
                String qty = df.getValueAt(i, 4).toString();
                String ptotal = df.getValueAt(i, 6).toString();
                      
                bill.setText(bill.getText() + Pname+"\t"+qty+"\t"+ptotal+ "\n");
                
              }
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
                bill.setText(bill.getText()+"Payment: "+"\t"+txtTotal.getText());
                bill.setText(bill.getText()+"\n\nPayment: "+"\t"+txtPayment.getText());
                bill.setText(bill.getText()+"\nchange: "+"\t"+txtChange.getText());
            
      
 
 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnInventory = new javax.swing.JButton();
        btnCategory = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton8 = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnSales = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        ADD = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtPname = new javax.swing.JTextField();
        txtDesc = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        txtCat = new javax.swing.JComboBox<>();
        Dob = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtQty2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        Payment = new javax.swing.JLabel();
        Change = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtPayment = new javax.swing.JTextField();
        txtChange = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        txtPid = new javax.swing.JTextField();
        DateTime = new javax.swing.JLabel();
        updatebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(175, 89, 89));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(57, 57, 57));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("INVENTORY");

        btnInventory.setBackground(new java.awt.Color(175, 89, 89));
        btnInventory.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInventory.setForeground(new java.awt.Color(255, 255, 255));
        btnInventory.setText("Inventory");
        btnInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventoryActionPerformed(evt);
            }
        });

        btnCategory.setBackground(new java.awt.Color(175, 89, 89));
        btnCategory.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnCategory.setForeground(new java.awt.Color(255, 255, 255));
        btnCategory.setText("Catergory");
        btnCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));

        jButton8.setBackground(new java.awt.Color(175, 89, 89));
        jButton8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("LOG OUT");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btnDashboard.setBackground(new java.awt.Color(175, 89, 89));
        btnDashboard.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        btnUsers.setBackground(new java.awt.Color(175, 89, 89));
        btnUsers.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnUsers.setForeground(new java.awt.Color(255, 255, 255));
        btnUsers.setText("Users");
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });

        btnSales.setBackground(new java.awt.Color(175, 89, 89));
        btnSales.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSales.setForeground(new java.awt.Color(255, 255, 255));
        btnSales.setText("Sales");
        btnSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel3)
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSales, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSales, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, 0, 250, 740));

        jPanel3.setBackground(new java.awt.Color(57, 57, 57));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INVENTORY MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Catergory", "Product Name", "Description", "Quantity", "Retail Price", "Total"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 570, 160));

        jButton7.setText("X");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 50, 30));

        deletebtn.setBackground(new java.awt.Color(255, 102, 102));
        deletebtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/im_system/icons/icons8-delete-25.png"))); // NOI18N
        deletebtn.setText("Remove");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        jPanel1.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, -1, 40));

        ADD.setBackground(new java.awt.Color(102, 255, 102));
        ADD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ADD.setForeground(new java.awt.Color(255, 255, 255));
        ADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/im_system/icons/icons8-add-24.png"))); // NOI18N
        ADD.setText("Add");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });
        jPanel1.add(ADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 118, 40));

        jPanel4.setBackground(new java.awt.Color(175, 89, 89));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txtPname.setBackground(new java.awt.Color(51, 51, 51));
        txtPname.setForeground(new java.awt.Color(255, 255, 255));
        txtPname.setEnabled(false);
        txtPname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPnameActionPerformed(evt);
            }
        });

        txtDesc.setBackground(new java.awt.Color(51, 51, 51));
        txtDesc.setForeground(new java.awt.Color(255, 255, 255));
        txtDesc.setEnabled(false);
        txtDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescActionPerformed(evt);
            }
        });

        txtPrice.setBackground(new java.awt.Color(51, 51, 51));
        txtPrice.setForeground(new java.awt.Color(255, 255, 255));
        txtPrice.setEnabled(false);
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        txtQty.setBackground(new java.awt.Color(51, 51, 51));
        txtQty.setForeground(new java.awt.Color(255, 255, 255));
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        txtCat.setBackground(new java.awt.Color(51, 51, 51));
        txtCat.setForeground(new java.awt.Color(255, 255, 255));
        txtCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        txtCat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtCatItemStateChanged(evt);
            }
        });
        txtCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCatActionPerformed(evt);
            }
        });

        Dob.setBackground(new java.awt.Color(51, 51, 51));
        Dob.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Description");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Product Name");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Category");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Price");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date");

        txtQty2.setBackground(new java.awt.Color(51, 51, 51));
        txtQty2.setForeground(new java.awt.Color(255, 255, 255));
        txtQty2.setEnabled(false);
        txtQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQty2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtCat, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtPname, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel5)))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dob, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtPname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(Dob, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 560, 140));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Catergory", "Product Name", "Description", "Quantity", "Retail Price", "Date of Purchase"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 570, 160));

        jPanel6.setBackground(new java.awt.Color(57, 57, 57));

        bill.setColumns(20);
        bill.setRows(5);
        jScrollPane3.setViewportView(bill);

        jPanel5.setBackground(new java.awt.Color(57, 57, 57));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Total");

        Payment.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Payment.setText("Payment");

        Change.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Change.setText("Balance");

        txtTotal.setBackground(new java.awt.Color(51, 51, 51));
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtTotal.setEnabled(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        txtPayment.setBackground(new java.awt.Color(51, 51, 51));
        txtPayment.setForeground(new java.awt.Color(255, 255, 255));
        txtPayment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaymentActionPerformed(evt);
            }
        });
        txtPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaymentKeyReleased(evt);
            }
        });

        txtChange.setBackground(new java.awt.Color(51, 51, 51));
        txtChange.setForeground(new java.awt.Color(255, 255, 255));
        txtChange.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtChange.setEnabled(false);
        txtChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChangeActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("Discount");

        txtDiscount.setBackground(new java.awt.Color(51, 51, 51));
        txtDiscount.setForeground(new java.awt.Color(255, 255, 255));
        txtDiscount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountActionPerformed(evt);
            }
        });
        txtDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiscountKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Change)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(59, 59, 59)
                        .addComponent(txtTotal))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Payment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Payment)
                    .addComponent(txtPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Change, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtChange, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setText("PAY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("PRINT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setText("Sub Total");

        txtsubtotal.setBackground(new java.awt.Color(51, 51, 51));
        txtsubtotal.setForeground(new java.awt.Color(255, 255, 255));
        txtsubtotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtsubtotal.setEnabled(false);
        txtsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsubtotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 70, 240, 570));

        txtPid.setBackground(new java.awt.Color(51, 51, 51));
        txtPid.setForeground(new java.awt.Color(255, 255, 255));
        txtPid.setEnabled(false);
        txtPid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPidActionPerformed(evt);
            }
        });
        jPanel1.add(txtPid, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 135, 28));

        DateTime.setText("jLabel15");
        jPanel1.add(DateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, -1, -1));

        updatebtn.setBackground(new java.awt.Color(102, 255, 102));
        updatebtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        updatebtn.setForeground(new java.awt.Color(255, 255, 255));
        updatebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/im_system/icons/icons8-add-24.png"))); // NOI18N
        updatebtn.setText("UPDATE");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        jPanel1.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 118, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        Users CAT = new Users();

        CAT.show();

        dispose();
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        dashboard CAT = new dashboard();

        CAT.show();

        dispose();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryActionPerformed
        Cat_system CAT = new Cat_system();
        CAT.show();

        dispose();
    }//GEN-LAST:event_btnCategoryActionPerformed

    
    
    private void btnInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInventoryActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        remove();
//        try {
//            //Delete Button
//
//            String pid = txtPid.getText();
//
//            pst = con.prepareStatement("DELETE FROM products_tbl WHERE pid=?");
//            pst.setString(1,pid);
//
//            int k=pst.executeUpdate();
//            if (k==1){
//                JOptionPane.showMessageDialog(this,"Successfully Deleted");
//                txtPid.setText("");
//                txtPname.setText("");
//                txtDesc.setText("");
//                txtQty.setText("");
//                txtCat.setSelectedItem("");
//                txtRetailPrice.setText("");
//                Dob.setDate(Date);
//                Fetch();
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_deletebtnActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
            
        
           addToOrder();
           calculate();

//        try {
//            // TODO add your handling code here:
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//            String pname = txtPname.getText();
//            String description = txtDesc.getText();
//            String qty = txtQty.getText();
//            String cat = txtCat.getSelectedItem().toString();
//            String rprice = txtPrice.getText();
//            String date = sdf.format(Dob.getDate());
//            //
//
//            if(txtPname.getText().isEmpty() || txtDesc.getText().isEmpty() || txtQty.getText().isEmpty() || txtPrice.getText().isEmpty())
//            {
//                JOptionPane.showMessageDialog(null,"Fill requred textfield");
//            }else{
//
//                pst = con.prepareStatement("INSERT INTO products_tbl (pname,description,qty,cat,rprice,date)Values(?,?,?,?,?,?)");
//                pst.setString(1,pname);
//                pst.setString(2,description);
//                pst.setString(3,qty);
//                pst.setString(4,cat);
//                pst.setString(5,rprice);
//                pst.setString(6,date);
//
//                int k = pst.executeUpdate();
//
//                if (k==1) {
//                    JOptionPane.showMessageDialog(this,"Record Added Successfully!!");
//                    txtPname.setText("");
//                    txtDesc.setText("");
//                    txtQty.setText("");
//                    txtCat.setSelectedItem("");
//                    txtPrice.setText("");
//                    Dob.setDate(Date);
//                    Fetch();
//
//                } else {
//                    JOptionPane.showMessageDialog(this,"Record Failed To Add!!");
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_ADDActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescActionPerformed

    private void txtPnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPnameActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        ADD.setVisible(true);
        updatebtn.setVisible(false);
        // TODO add your handling code here:
        
        int i = jTable2.getSelectedRow();
        TableModel model = jTable2.getModel();
        
        ProdID = jTable2.getValueAt(i,0).toString();
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalesActionPerformed

    }//GEN-LAST:event_btnSalesActionPerformed

    private void txtCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCatActionPerformed

    }//GEN-LAST:event_txtCatActionPerformed

    private void txtQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQty2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQty2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

//        try {
//            
//            int i = jTable1.getSelectedRow();
//            TableModel model = jTable1.getModel();
//
//            txtPid.setText(model.getValueAt(i,0).toString());
//            txtPname.setText(model.getValueAt(i,2).toString());
//            txtDesc.setText(model.getValueAt(i,3).toString());
//            txtQty.setText(model.getValueAt(i,4).toString());
//            txtCat.setSelectedItem(model.getValueAt(i,1).toString());
//            txtPrice.setText(model.getValueAt(i,5).toString());
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,6));
//            Dob.setDate(date);
//        } catch (ParseException ex) {
//            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
                   ADD.setVisible(true);      
                   updatebtn.setVisible(false);
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int SelectedRowIndex = jTable1.getSelectedRow();
        
        rowQuan = SelectedRowIndex;
        
//        txtCat.setSelectedItem(model.getValueAt(SelectedRowIndex, 1).toString());
        txtPname.setText(model.getValueAt(SelectedRowIndex, 2).toString());
        txtPrice.setText(model.getValueAt(SelectedRowIndex, 5).toString());
        txtDesc.setText(model.getValueAt(SelectedRowIndex, 3).toString());
        ProductID = model.getValueAt(SelectedRowIndex, 0).toString();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(SelectedRowIndex,6));
        } catch (ParseException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
            Dob.setDate(date);
    
    }//GEN-LAST:event_jTable1MouseClicked

    
        private void addToOrder()
    {
       String quantity = txtQty.getText().trim();
       String prodname = txtPname.getText().trim();

      
                    DefaultTableModel model3 = (DefaultTableModel) jTable1.getModel();
                    
              
                   int quants = Integer.parseInt(model3.getValueAt(rowQuan, 4).toString());
                   this.origQuan = Integer.parseInt(model3.getValueAt(rowQuan, 4).toString());
                    
        int quan = Integer.parseInt(txtQty.getText());
                    if(quants < quan)
                    {
                        alert1("THE PRODUCT IS OUT OF STOCK");
                    }
                    else
                    {
                        if(Integer.parseInt(txtQty.getText().toString()) == 0)
                        {
                            alert1("EMPTY STOCK");
                        }
                        else
                        {
                            //float price = Float.parseFloat(textProdPrice.getText());
        float quant = Float.parseFloat(txtQty.getText());
        float price = Float.parseFloat(txtPrice.getText());
                            float total = quant * price;
                            
                            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                            Object[] row = new Object[7];
                            
                            row[0] = this.ProductID;
                            row[2] = txtPname.getText();
                            row[1] = txtCat.getSelectedItem();
                            row[4] = txtQty.getText();
                            row[5] = txtPrice.getText();
                            row[3] = txtDesc.getText();
                            row[6] = String.format("%.2f", total);
                            model.addRow(row);
               
                            DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
                            model2.setValueAt(quants - quan, rowQuan, 4);
                        }
    }
        }
    
    private void update(){
        
        int quan = Integer.parseInt(txtQty.getText());
        
        int i = jTable2.getSelectedRow();
        String idprod = jTable2.getValueAt(i,0).toString();
        int currentquant = Integer.parseInt(jTable2.getValueAt(i, 4).toString());
        
        
        try {
            String sql = "SELECT qty from products_tbl WHERE id = '"+idprod+"'";
            pst = (PreparedStatement) con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                this.quants = Integer.parseInt(rs.getString(1));
            }
            
            int row = jTable1.getRowCount();
            for(int k = 0; k < row; k++)
            {
                String ProductID2 = jTable1.getValueAt(k,0).toString();
                if (ProductID.compareTo(ProductID2)==0)
                {
                    if(currentquant > quan)
                    {
                        //String prodQuan1 = jTable1.getValueAt(k, 2).toString();
                        int prodQuan = Integer.parseInt(jTable1.getValueAt(k, 4).toString());
                        int result = currentquant - quan;
                        jTable1.setValueAt(prodQuan + result, k, 4);
                    }
                    else
                    {
                        int prodQuan = Integer.parseInt(jTable1.getValueAt(k, 4).toString());
                        int result = quan - currentquant;
                        jTable1.setValueAt(prodQuan-result, k, 4);
                    }
                    
                }
            }
            jTable2.setValueAt(quan, i, 4);
            calculate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
                
    private void calculate()
    {
        try
        {
           //TOTAL AMOUNT SECTION
            this.totalAmount = 0;
            this.orderTotal = 0;
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            for(int i = 0; i < model.getRowCount(); i++)
            {
                this.value = Float.parseFloat(model.getValueAt(i, 6).toString());
                this.totalAmount +=  value;
            }
            txtsubtotal.setText(String.format("%.2f", this.totalAmount));
            orderTotal += totalAmount;
            txtsubtotal.setText(String.format("%.2f", this.orderTotal));
       
            //DISCOUNT SECTION
            float discountValue;
            //txtDiscount.setText("0");
            discountValue = Float.parseFloat(txtDiscount.getText());
            String discountVal = txtDiscount.getText();
            if(discountValue > 100)
            {
                txtDiscount.setText("100"); // ang nalahi
                this.discount = (totalAmount * 100) / 100;
                //labelDiscount.setText(String.format("%.2f", this.discount));
                this.orderTotal = totalAmount - discount;
                txtTotal.setText(String.format("%.2f", orderTotal));
            }
            else if(discountVal.isEmpty())
            {
                txtDiscount.setText("");
                this.discount = (totalAmount * discountValue) / 100;
                //labelDiscount.setText(String.format("%.2f", this.discount));
                this.orderTotal = totalAmount - discount;
                txtTotal.setText(String.format("%.2f", orderTotal));
            }
            else
            {
                this.discount = (totalAmount * discountValue) / 100;
                //labelDiscount.setText(String.format("%.2f", this.discount));
                this.orderTotal = totalAmount - discount;
                txtTotal.setText(String.format("%.2f", orderTotal));
            }

            //CHANGE SECTION
            //txtChange.setText("");
            float Total = Float.parseFloat(txtTotal.getText());
            float paidAmount = Float.parseFloat(txtPayment.getText());
            float change = paidAmount - Total;
            if(change > 0)
            {
                
                txtChange.setText(String.format("%.2f", change));
            }
            else
            {
                txtChange.setText("INSUFFICIENT MONEY");
            }
            }
        catch(NumberFormatException e)
        {
            //do nothing
        }
    }

//        public void getsum() {
////        int amnt = jTable2.getRowCount();
//
//
//       for (int i = 0; i < jTable2.getRowCount(); i++)
//       {
//            tot = tot + Float.parseFloat(jTable2.getValueAt(i,5).toString());
//        }
//
//        txtsubtotal.setText(Float.toString(tot));
//        
//        
//        txtTotal.setText(Float.toString(tot));
//    }
        
        private void btnSave()
{
        int rowCountOrder = jTable2.getRowCount();
        int rowCountProduct = jTable1.getRowCount();
        int i, k;
       
        if(rowCountOrder <= 0)
        {
            alert1("YOU HAVEN'T ADDED YOUR ORDER!");
        }
        else
        {
            int option = JOptionPane.showConfirmDialog(rootPane, "Is this your final order?", "Add confirmation", JOptionPane.YES_NO_OPTION);
            if(option == 0)
            {
                
                try {
                    String sql = "SELECT max(id) FROM sales_tbl";
                    st = con.createStatement();
                    rs = st.executeQuery(sql);
                if (rs.next())
                {
                     this.id = rs.getInt(1);
                    if (Integer.toString(id).isEmpty())
                    {
                        this.id = 1;
                    }else{
                        this.id++;
                    }
                }
                } catch (SQLException ex) {
                    Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for(i = 0; i<rowCountOrder; i++)
                {
                    String productID = jTable2.getValueAt(i, 0).toString();
                    String quantity = jTable2.getValueAt(i,4).toString();
                    String productname = jTable2.getValueAt(i, 2).toString();
                    String ptotal = jTable2.getValueAt(i, 6).toString();
                    String payment = txtPayment.getText();
                    String discount = txtDiscount.getText();
                    String change = txtChange.getText();
                    String stotal = txtsubtotal.getText();
                    String total = txtTotal.getText();
                    String date = DateTime.getText();
                    saveOrder(quantity,productname,ptotal,payment,discount,change,stotal,total,this.id,date);
               
                    for(k = 0; k<rowCountProduct; k++)
                    {
                        String prodname = jTable1.getValueAt(k, 2).toString();
                        if(productname.compareTo(prodname) == 0)
                        {
                            String updateQuan = jTable1.getValueAt(k, 4).toString();
                            updateProduct(updateQuan,prodname);
                        }
                    }
                }
                alert1("ORDER SUCCESSFULLY ADDED!");
                try {
                    bill.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            
             
        }
}
       private void saveOrder(String quantity, String productname , String ptotal, String payment, String discount, String change, String stotal, String total, int id, String date)
     {
        try {
            
            String sql = "INSERT INTO sales_tbl (pname,qty,ptotal,payment,discount,Pchange,stotal,total,id,date) VALUES ('"+productname+"', '"+quantity+"', '"+ptotal+"', '"+payment+"', '"+discount+"', '"+change+"', '"+stotal+"', '"+total+"', '"+id+"', '"+date+"')";
            st = con.createStatement(); 
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
    
      private void updateProduct(String quantity, String productname)
    {
        try {
            String sql = "UPDATE products_tbl SET qty = '"+quantity+"' WHERE pname = '"+productname+"'";
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      private void restart()
    {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model2.setRowCount(0);
//        search();
//        loadProduct();
//        btnProductSave.setEnabled(false);
        Fetch();
        txtPname.setText("");
        txtDesc.setText("");
        txtQty.setText("");
        txtPrice.setText("0.0");
        txtDiscount.setText("");
        txtPayment.setText("");
        txtChange.setText("0.0");
        txtTotal.setText("0.0");
        //labelDiscount.setText("0.0");
        txtTotal.setText("0.0");
        txtsubtotal.setText("0.0");
        //act = 0;
    }
      
      private void remove()
      {
          DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
          txtTotal.setText("0.0");
        //labelDiscount.setText("0.0");
          txtTotal.setText("0.0");
          txtsubtotal.setText("0.0");
          
          try{
              int SelectedRowIndex = jTable2.getSelectedRow();
              model.removeRow(SelectedRowIndex);
          }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
          
      }
    
    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaymentActionPerformed

    private void txtChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChangeActionPerformed

    private void txtPidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPidActionPerformed

    private void txtsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        btnSave();
        restart();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscountActionPerformed

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        update();
    }//GEN-LAST:event_updatebtnActionPerformed

    private void txtPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaymentKeyReleased
       calculate();
    }//GEN-LAST:event_txtPaymentKeyReleased

    private void txtDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountKeyReleased
        calculate();
    }//GEN-LAST:event_txtDiscountKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       bill_print();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtCatItemStateChanged
        String query=txtCat.getSelectedItem().toString();
        
        search(query);
    }//GEN-LAST:event_txtCatItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JLabel Change;
    private javax.swing.JLabel DateTime;
    private com.toedter.calendar.JDateChooser Dob;
    private javax.swing.JLabel Payment;
    private javax.swing.JTextArea bill;
    private javax.swing.JButton btnCategory;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnInventory;
    private javax.swing.JButton btnSales;
    private javax.swing.JButton btnUsers;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> txtCat;
    private javax.swing.JTextField txtChange;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtPayment;
    private javax.swing.JTextField txtPid;
    private javax.swing.JTextField txtPname;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtQty2;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables

    

}
