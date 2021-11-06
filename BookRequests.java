
package librarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;


public class BookRequests extends javax.swing.JFrame {
    Connection con = connectLibrary.connecting(); 
    ResultSet rs;
    PreparedStatement pst;
    String choosenSTATUS;
    
    public BookRequests() {
        initComponents();
        connectLibrary.connecting(); 
        displayTable ();
        buttonGroup1.add(accept);
        buttonGroup1.add(reject);
        buttonGroup1.add(pending);
        gettingEmailInfo();  
    }
    private void displayTable (){
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String booksAvail = "SELECT * FROM bookrequests"; 
            pst = con.prepareStatement(booksAvail);
            rs = pst.executeQuery();
            requestTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
        }
     }
    private void updateStatus() throws Exception{
        try{
        //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
        String updatingStatus = "UPDATE bookrequests SET status=? WHERE bookid=? AND borrowerid=?";
        pst = con.prepareStatement(updatingStatus);
        //String choosenBOOKID = SELECTEDBOOKID.getText();
        if (accept.isSelected()){
            choosenSTATUS = "ACCEPTED";
            //ifAcceptedDecrementCopy();
            sendAcceptedNotice ();
           
        }else if (reject.isSelected()){
            choosenSTATUS = "REJECTED";
//            ifRejectedIncrementCopy();
           // gettingEmailInfo ();
            sendRejectedNotice();
//            try {
//            con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
//            String getCode = "SELECT * FROM booksavailable where bookid=?";
//            pst = con.prepareStatement(getCode);
//            String updatingCopies  = "UPDATE booksavailable SET COPIES = COPIES + 1   WHERE COPIES > 1 and bookid=" + SELECTEDBOOKID.getText();
//            pst = con.prepareStatement(updatingCopies);
            //String strCODE = INVIDSelectedBookCode.getText();
            //int intCODE = Integer.parseInt(strCODE);
//            pst.setString(1, INVIDSelectedBookCode.getText());
//            pst.executeUpdate();
//            autoUpdateTable();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
        }else if (pending.isSelected()){
            choosenSTATUS = "PENDING";
        }
        pst.setString(1, choosenSTATUS);
        pst.setString(2, SELECTEDBOOKID.getText());
        pst.setString(3, SELECTEDBORROWERID.getText());
        pst.executeUpdate();
        autoUpdateTable();
     }catch (SQLException e){
         e.printStackTrace();
     }
     }
    private void autoUpdateTable(){ 
    try{
        //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
        String updatetable = "SELECT * from bookrequests";
        pst = con.prepareStatement(updatetable);
        rs = pst.executeQuery();
        requestTable.setModel(DbUtils.resultSetToTableModel(rs));
    }catch (SQLException e){
    }
}
    private void gettingEmailInfo (){
        try {
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String gettingEmail = "SELECT * FROM useraccounts where idnumber=?";
            pst = con.prepareStatement(gettingEmail);
            pst.setString(1, SELECTEDBORROWERID.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                EMAIL.setText(rs.getString(10));
            }
        }catch (SQLException e){
        }
    }
//    private void ifRejectedIncrementCopy(){//SELECTEDBOOKID
//        try {
//            con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
//            String getCode = "SELECT * FROM booksavailable where bookid=?";
//            pst = con.prepareStatement(getCode);
//            String updatingCopies  = "UPDATE booksavailable SET COPIES = COPIES + 1  WHERE bookid=?";
//            pst = con.prepareStatement(updatingCopies);
//            String strCODE = INVIDSelectedBookCode.getText();
//            int intCODE = Integer.parseInt(strCODE);
//            pst.setInt(1, intCODE);
//            pst.executeUpdate();
//            autoUpdateTable();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    private void sendRejectedNotice () throws Exception{
        RejectedMail.SendMail(EMAIL.getText());
    }
    private void sendAcceptedNotice () throws Exception {
        AcceptedMail.SendMail(EMAIL.getText());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        reject = new javax.swing.JRadioButton();
        pending = new javax.swing.JRadioButton();
        accept = new javax.swing.JRadioButton();
        archived = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        updateBTN = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        BGGGGGGGGGGGGGG = new javax.swing.JLabel();
        SELECTEDBOOKID = new javax.swing.JTextField();
        SELECTEDBORROWERID = new javax.swing.JTextField();
        EMAIL = new javax.swing.JTextField();
        INVIid = new javax.swing.JTextField();
        INVIname = new javax.swing.JTextField();
        INVIbday = new javax.swing.JTextField();
        INVIDSelectedBookCode = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BOOK REQUESTS");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        requestTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        requestTable.setModel(new javax.swing.table.DefaultTableModel(
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
        requestTable.setRowHeight(25);
        requestTable.setSelectionBackground(new java.awt.Color(206, 18, 18));
        requestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(requestTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 1270, 510));

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("STATUS:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 100, -1));

        reject.setBackground(new java.awt.Color(255, 255, 255));
        reject.setFont(new java.awt.Font("Dubai", 1, 20)); // NOI18N
        reject.setForeground(new java.awt.Color(255, 255, 255));
        reject.setText("REJECTED");
        reject.setBorder(null);
        reject.setOpaque(false);
        jPanel1.add(reject, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 130, 40));

        pending.setBackground(new java.awt.Color(255, 255, 255));
        pending.setFont(new java.awt.Font("Dubai", 1, 20)); // NOI18N
        pending.setForeground(new java.awt.Color(255, 255, 255));
        pending.setText("PENDING");
        pending.setOpaque(false);
        jPanel1.add(pending, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 140, 40));

        accept.setBackground(new java.awt.Color(255, 255, 255));
        accept.setFont(new java.awt.Font("Dubai", 1, 20)); // NOI18N
        accept.setForeground(new java.awt.Color(255, 255, 255));
        accept.setText("ACCEPTED");
        accept.setBorder(null);
        accept.setOpaque(false);
        jPanel1.add(accept, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, -1, 40));

        archived.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/trash.png"))); // NOI18N
        archived.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archivedMouseClicked(evt);
            }
        });
        jPanel1.add(archived, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 270, 50, 40));

        home.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        home.setForeground(new java.awt.Color(204, 0, 0));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Librarysystem/home (1).png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 150, 80, 90));

        updateBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/check (1).png"))); // NOI18N
        updateBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateBTNMouseClicked(evt);
            }
        });
        jPanel1.add(updateBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 270, 50, 50));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 1270, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/white.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 760, 920, 60));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 1290, 580));

        jLabel18.setFont(new java.awt.Font("Dubai", 1, 35)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("BOOK REQUESTS");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 320, 70));

        BGGGGGGGGGGGGGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/BGJLABELUE.png"))); // NOI18N
        jPanel1.add(BGGGGGGGGGGGGGG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 1500, 910));
        jPanel1.add(SELECTEDBOOKID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 622, 130, 40));
        jPanel1.add(SELECTEDBORROWERID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, 80, -1));
        jPanel1.add(EMAIL, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 670, 120, -1));

        INVIid.setEditable(false);
        jPanel1.add(INVIid, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 80, -1));

        INVIname.setEditable(false);
        jPanel1.add(INVIname, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, 80, -1));

        INVIbday.setEditable(false);
        jPanel1.add(INVIbday, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 660, 80, -1));
        jPanel1.add(INVIDSelectedBookCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void requestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTableMouseClicked
    DefaultTableModel model = (DefaultTableModel)requestTable.getModel();
    int position = requestTable.getSelectedRow();
    String status = (model.getValueAt(position, 0).toString());
        switch (status) {
            case "ACCEPTED":
                accept.setSelected(true);
                gettingEmailInfo ();
                break;
            case "REJECTED":
                reject.setSelected(true);
                gettingEmailInfo ();
                break;
            case "PENDING":
                pending.setSelected(true);
                gettingEmailInfo ();
                break;
            default:
                break;
        }
    gettingEmailInfo();
    SELECTEDBORROWERID.setText(model.getValueAt(position, 1).toString());
    SELECTEDBOOKID.setText(model.getValueAt(position, 5).toString());
    //INVIDSelectedBookCode.setText(model.getValueAt(position, 5).toString());

    }//GEN-LAST:event_requestTableMouseClicked

    private void updateBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBTNMouseClicked
    if (accept.isSelected() || reject.isSelected() || pending.isSelected()){
        if (pending.isSelected() || reject.isSelected()){
            try {
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String updatingCopies  = "UPDATE booksavailable SET COPIES = COPIES + 1 WHERE bookid=? AND COPIES <10";
            pst = con.prepareStatement(updatingCopies);
            String strISBN = SELECTEDBOOKID.getText();
            int intISBN = Integer.parseInt(strISBN);
            pst.setInt(1, intISBN);
            pst.executeUpdate();
            autoUpdateTable();
        }catch (Exception e){
            e.printStackTrace();
        }
        }
        if (accept.isSelected()){
            try {
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String updatingCopies  = "UPDATE booksavailable SET COPIES = COPIES - 1   WHERE bookid=" + SELECTEDBOOKID.getText();
            pst = con.prepareStatement(updatingCopies);
            pst.executeUpdate();
            autoUpdateTable();             
            }catch (Exception e){
            e.printStackTrace();
        }
            }
            try {
        updateStatus();
    }catch (Exception ex) {
            Logger.getLogger(BookRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    autoUpdateTable();
        }else {
            JOptionPane.showMessageDialog(this, "Please select a status first!", "No Status Selected", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_updateBTNMouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
    Admin_Module AdminFrame = new Admin_Module();
    Admin_Module.UserIDTxtField.setText(BookRequests.INVIid.getText());
    Admin_Module.NameTxtField.setText(BookRequests.INVIname.getText());
    Admin_Module.dateNgayonField.setText(BookRequests.INVIbday.getText());
    AdminFrame.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_homeMouseClicked

    private void archivedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivedMouseClicked
    int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the Rejected Requests?","Delete Rejected Book Requests", JOptionPane.YES_NO_OPTION);
    if (confirmation == 0){
        try {
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String deleteREJECTED = "DELETE FROM bookrequests where status=?";
            pst = con.prepareStatement(deleteREJECTED);
            String bookStatus = "REJECTED";
            pst.setString(1, bookStatus);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
            autoUpdateTable();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_archivedMouseClicked

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
            java.util.logging.Logger.getLogger(BookRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookRequests().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BGGGGGGGGGGGGGG;
    private javax.swing.JTextField EMAIL;
    private javax.swing.JTextField INVIDSelectedBookCode;
    public static javax.swing.JTextField INVIbday;
    public static javax.swing.JTextField INVIid;
    public static javax.swing.JTextField INVIname;
    private javax.swing.JTextField SELECTEDBOOKID;
    private javax.swing.JTextField SELECTEDBORROWERID;
    private javax.swing.JRadioButton accept;
    private javax.swing.JLabel archived;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton pending;
    private javax.swing.JRadioButton reject;
    private javax.swing.JTable requestTable;
    private javax.swing.JLabel updateBTN;
    // End of variables declaration//GEN-END:variables
}
