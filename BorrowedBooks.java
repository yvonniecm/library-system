package librarysystem;

import java.awt.CardLayout;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;

public class BorrowedBooks extends javax.swing.JFrame {
    DefaultTableModel tbModel = new DefaultTableModel();
    Connection con = connectLibrary.connecting();  
    ResultSet rs;
    PreparedStatement pst;
    CardLayout cardLayout;
    int bookFEE = 0;
    
    public BorrowedBooks() {
        initComponents();
        connectLibrary.connecting(); 
        cardLayout = (CardLayout)(CARD.getLayout());
        cardLayout.show(CARD, "myBookStatusPANEL");
        approvedBooksTable();
        myListOfBooks();
    }
    private void approvedBooksTable (){
        try{ 
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String sql = "SELECT * FROM bookrequests WHERE borrowerid=? AND status=?";
            pst = con.prepareStatement(sql);
            String bookStatus = "ACCEPTED";
            String strID = User_Module.UserIDTxtField.getText();
            pst.setString(1, strID);
            pst.setString(2, bookStatus);
            rs = pst.executeQuery();
            myBorrowedBooksTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void myListOfBooks(){
        try{ 
           // con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String sql = "SELECT * FROM bookrequests WHERE borrowerid=? AND status=?";
            pst = con.prepareStatement(sql);
            String bookREJECTED = "REJECTED";
            String bookPENDING = "PENDING";
            String strID = User_Module.UserIDTxtField.getText();
            pst.setString(1, strID);
            pst.setString(2, bookREJECTED);
            pst.setString(2, bookPENDING);
            rs = pst.executeQuery();
            myBookStatusTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void calculatingBookFee() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String d1 = BORROWDATE.getText();
        String d2 = TODAYDATE.getText();
        try {
            LocalDate date1 = LocalDate.parse(d1, dtf);
            LocalDate date2 = LocalDate.parse(d2, dtf);
            long daysBetween = ChronoUnit.DAYS.between(date1, date2);
            if (daysBetween < 7){
                FEE.setText("NO");
            }else if (daysBetween >= 7){
                FEE.setText("50"); //let say na lang na fixed 50 pesos fee for overdue books
            }   
        }catch (Exception e){
            e.printStackTrace();
        }
        }
    private void gettingTodaysDate(){
        Date date = new Date();
        LocalDate dateToday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate todayIs = dateToday;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateTodayIs = todayIs.format(formatter);
        TODAYDATE.setText(dateTodayIs);
    }
    private void autoUpdateTable(){ 
        try{
           // con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String updatetable = "SELECT * from bookrequests";
            pst = con.prepareStatement(updatetable);
            rs = pst.executeQuery();
            myBorrowedBooksTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
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

        jPanel1 = new javax.swing.JPanel();
        CARD = new javax.swing.JPanel();
        myBookStatusPANEL = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myBookStatusTABLE = new javax.swing.JTable();
        titleINFO1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        myBorrowedBooksPANEL = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        myBorrowedBooksTABLE = new javax.swing.JTable();
        inputID = new javax.swing.JLabel();
        inputID2 = new javax.swing.JLabel();
        inputID4 = new javax.swing.JLabel();
        inputID5 = new javax.swing.JLabel();
        inputID6 = new javax.swing.JLabel();
        FEE = new javax.swing.JTextField();
        RETURNDATE = new javax.swing.JTextField();
        TODAYDATE = new javax.swing.JTextField();
        BOOKID = new javax.swing.JTextField();
        BORROWDATE = new javax.swing.JTextField();
        returning = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        titleINFO2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        myBorrowedBooksBTN = new javax.swing.JButton();
        myBookStatusBTN = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        BGGGGG = new javax.swing.JLabel();
        INVIcourse = new javax.swing.JTextField();
        INVIid = new javax.swing.JTextField();
        INVIname = new javax.swing.JTextField();
        INVIbday = new javax.swing.JTextField();
        INVItype = new javax.swing.JTextField();
        INVIdateTodayIs = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BORROWED BOOKS");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CARD.setLayout(new java.awt.CardLayout());

        myBookStatusPANEL.setBackground(new java.awt.Color(255, 255, 255));
        myBookStatusPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        myBookStatusTABLE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        myBookStatusTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        myBookStatusTABLE.setRowHeight(25);
        myBookStatusTABLE.setSelectionBackground(new java.awt.Color(206, 18, 18));
        myBookStatusTABLE.getTableHeader().setResizingAllowed(false);
        myBookStatusTABLE.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(myBookStatusTABLE);

        myBookStatusPANEL.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1270, 460));

        titleINFO1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        titleINFO1.setForeground(new java.awt.Color(255, 255, 255));
        titleINFO1.setText("MY BOOK STATUS");
        myBookStatusPANEL.add(titleINFO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 200, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        myBookStatusPANEL.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 50));

        CARD.add(myBookStatusPANEL, "myBookStatusPANEL");

        myBorrowedBooksPANEL.setBackground(new java.awt.Color(255, 255, 255));
        myBorrowedBooksPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        myBorrowedBooksTABLE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        myBorrowedBooksTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        myBorrowedBooksTABLE.setRowHeight(25);
        myBorrowedBooksTABLE.setSelectionBackground(new java.awt.Color(206, 18, 18));
        myBorrowedBooksTABLE.getTableHeader().setResizingAllowed(false);
        myBorrowedBooksTABLE.getTableHeader().setReorderingAllowed(false);
        myBorrowedBooksTABLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myBorrowedBooksTABLEMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(myBorrowedBooksTABLE);

        myBorrowedBooksPANEL.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 820, 460));

        inputID.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        inputID.setText("Book ID:");
        myBorrowedBooksPANEL.add(inputID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 100, 30));

        inputID2.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        inputID2.setText("Borrowed Date:");
        myBorrowedBooksPANEL.add(inputID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 120, 30));

        inputID4.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        inputID4.setText("Return Date:");
        myBorrowedBooksPANEL.add(inputID4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 130, 30));

        inputID5.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        inputID5.setText("Today Date:");
        myBorrowedBooksPANEL.add(inputID5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 120, 30));

        inputID6.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        inputID6.setText("Fee:");
        myBorrowedBooksPANEL.add(inputID6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 40, 30));

        FEE.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        myBorrowedBooksPANEL.add(FEE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 230, 30));

        RETURNDATE.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        myBorrowedBooksPANEL.add(RETURNDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 230, 30));

        TODAYDATE.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        myBorrowedBooksPANEL.add(TODAYDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 230, 30));

        BOOKID.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        myBorrowedBooksPANEL.add(BOOKID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 230, 30));

        BORROWDATE.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        myBorrowedBooksPANEL.add(BORROWDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 230, 30));

        returning.setBackground(new java.awt.Color(255, 255, 255));
        returning.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        returning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/returnb.png"))); // NOI18N
        returning.setText("Return Book");
        returning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returningActionPerformed(evt);
            }
        });
        myBorrowedBooksPANEL.add(returning, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 160, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/3.png"))); // NOI18N
        myBorrowedBooksPANEL.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 440, 460));

        titleINFO2.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        titleINFO2.setForeground(new java.awt.Color(255, 255, 255));
        titleINFO2.setText("MY BORROWED BOOKS");
        myBorrowedBooksPANEL.add(titleINFO2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 260, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        myBorrowedBooksPANEL.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 50));

        CARD.add(myBorrowedBooksPANEL, "myBorrowedBooksPANEL");

        jPanel1.add(CARD, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 1290, 540));

        myBorrowedBooksBTN.setBackground(new java.awt.Color(206, 18, 18));
        myBorrowedBooksBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        myBorrowedBooksBTN.setForeground(new java.awt.Color(255, 255, 255));
        myBorrowedBooksBTN.setText("My Borrowed Books");
        myBorrowedBooksBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myBorrowedBooksBTNActionPerformed(evt);
            }
        });
        jPanel1.add(myBorrowedBooksBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 640, 40));

        myBookStatusBTN.setBackground(new java.awt.Color(206, 18, 18));
        myBookStatusBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        myBookStatusBTN.setForeground(new java.awt.Color(255, 255, 255));
        myBookStatusBTN.setText("My Book Status");
        myBookStatusBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myBookStatusBTNActionPerformed(evt);
            }
        });
        jPanel1.add(myBookStatusBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 650, 40));

        jLabel4.setFont(new java.awt.Font("Dubai", 1, 32)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BORROWED BOOKS");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 70));

        home.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        home.setForeground(new java.awt.Color(204, 0, 0));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/home (1).png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 150, 80, 90));

        BGGGGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/BGJLABELUE.png"))); // NOI18N
        jPanel1.add(BGGGGG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 1500, 910));

        INVIcourse.setEditable(false);
        jPanel1.add(INVIcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 90, -1));

        INVIid.setEditable(false);
        jPanel1.add(INVIid, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 80, -1));

        INVIname.setEditable(false);
        jPanel1.add(INVIname, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 80, -1));

        INVIbday.setEditable(false);
        jPanel1.add(INVIbday, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 80, -1));
        jPanel1.add(INVItype, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 670, 70, -1));
        jPanel1.add(INVIdateTodayIs, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 900));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
    User_Module UserFrame = new User_Module();
    User_Module.UserIDTxtField.setText(BorrowedBooks.INVIid.getText());
    User_Module.NameTxtField.setText(BorrowedBooks.INVIname.getText());
    User_Module.BirthdateTxtField.setText(BorrowedBooks.INVIbday.getText());
    User_Module.memberTypeTxtField.setText(BorrowedBooks.INVItype.getText());
    User_Module.dateNgayonFieldUSER.setText(BorrowedBooks.INVIdateTodayIs.getText());
    UserFrame.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_homeMouseClicked

    private void myBookStatusBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myBookStatusBTNActionPerformed
    cardLayout.show(CARD, "myBookStatusPANEL");
    }//GEN-LAST:event_myBookStatusBTNActionPerformed

    private void myBorrowedBooksBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myBorrowedBooksBTNActionPerformed
    cardLayout.show(CARD, "myBorrowedBooksPANEL");
    }//GEN-LAST:event_myBorrowedBooksBTNActionPerformed

    private void myBorrowedBooksTABLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myBorrowedBooksTABLEMouseClicked
    DefaultTableModel model = (DefaultTableModel)myBorrowedBooksTABLE.getModel();
    String status = myBorrowedBooksTABLE.getValueAt(myBorrowedBooksTABLE.getSelectedRow(),0).toString();
    String borrowerid = myBorrowedBooksTABLE.getValueAt(myBorrowedBooksTABLE.getSelectedRow(),1).toString();
    String borrowername = myBorrowedBooksTABLE.getValueAt(myBorrowedBooksTABLE.getSelectedRow(),2).toString();
    String membertype = myBorrowedBooksTABLE.getValueAt(myBorrowedBooksTABLE.getSelectedRow(),3).toString();
    String bookname = myBorrowedBooksTABLE.getValueAt(myBorrowedBooksTABLE.getSelectedRow(),4).toString();
    String bookid = myBorrowedBooksTABLE.getValueAt(myBorrowedBooksTABLE.getSelectedRow(),5).toString();
    String dateborrowed = myBorrowedBooksTABLE.getValueAt(myBorrowedBooksTABLE.getSelectedRow(),6).toString();
    String datereturn = myBorrowedBooksTABLE.getValueAt(myBorrowedBooksTABLE.getSelectedRow(),7).toString();
    BOOKID.setText(bookid);
    BORROWDATE.setText(dateborrowed);
    RETURNDATE.setText(datereturn);
    gettingTodaysDate();
    calculatingBookFee();
    }//GEN-LAST:event_myBorrowedBooksTABLEMouseClicked

    private void returningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returningActionPerformed
    if (BOOKID.getText().isEmpty() || BORROWDATE.getText().isEmpty() || RETURNDATE.getText().isEmpty() || TODAYDATE.getText().isEmpty() || FEE.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Please select the book you want to return first!", "Blank Fields", JOptionPane.ERROR_MESSAGE);
        return;
    }
        int confirmation = JOptionPane.showConfirmDialog(null,"Please note that if your due date is overdue, you are required to pay 50 pesos fee." , "Important Message", JOptionPane.OK_OPTION);
        if (confirmation == 0){
            try {
                //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
                String returnBook = "insert into returningbooks (status, borrowerid, borrowername, membertype, bookid, borroweddate, returndate, todaydate, fee)values(?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(returnBook);
                
                String bookStatus = "RETURNING";
                pst.setString(1, bookStatus);
                pst.setString(2, INVIid.getText());
                pst.setString(3,INVIname.getText());
                pst.setString(4, INVItype.getText());
                pst.setString(5, BOOKID.getText());
                pst.setString(6, BORROWDATE.getText());
                pst.setString(7, RETURNDATE.getText());
                pst.setString(8, TODAYDATE.getText());
                pst.setString(9, FEE.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book Succesfully Return!");
                BOOKID.setText(null);
                BORROWDATE.setText(null);
                RETURNDATE.setText(null);
                TODAYDATE.setText(null);
                FEE.setText(null);
                String updatingTable = "DELETE from bookrequests where borrowerid=?";
                pst = con.prepareStatement(updatingTable);
                pst.setString(1, INVIid.getText());
                pst.executeUpdate();
                approvedBooksTable();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_returningActionPerformed

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
            java.util.logging.Logger.getLogger(BorrowedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BorrowedBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BGGGGG;
    private javax.swing.JTextField BOOKID;
    private javax.swing.JTextField BORROWDATE;
    private javax.swing.JPanel CARD;
    private javax.swing.JTextField FEE;
    public static javax.swing.JTextField INVIbday;
    protected static javax.swing.JTextField INVIcourse;
    public static javax.swing.JTextField INVIdateTodayIs;
    public static javax.swing.JTextField INVIid;
    public static javax.swing.JTextField INVIname;
    public static javax.swing.JTextField INVItype;
    private javax.swing.JTextField RETURNDATE;
    private javax.swing.JTextField TODAYDATE;
    private javax.swing.JLabel home;
    private javax.swing.JLabel inputID;
    private javax.swing.JLabel inputID2;
    private javax.swing.JLabel inputID4;
    private javax.swing.JLabel inputID5;
    private javax.swing.JLabel inputID6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton myBookStatusBTN;
    private javax.swing.JPanel myBookStatusPANEL;
    private javax.swing.JTable myBookStatusTABLE;
    private javax.swing.JButton myBorrowedBooksBTN;
    private javax.swing.JPanel myBorrowedBooksPANEL;
    private javax.swing.JTable myBorrowedBooksTABLE;
    private javax.swing.JButton returning;
    private javax.swing.JLabel titleINFO1;
    private javax.swing.JLabel titleINFO2;
    // End of variables declaration//GEN-END:variables
}