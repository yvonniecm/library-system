package librarysystem;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class bookReports extends javax.swing.JFrame {
    CardLayout cardLayout;
    Connection con = connectLibrary.connecting(); 
    ResultSet rs;
    PreparedStatement pst;

    public bookReports() {
        initComponents();
        connectLibrary.connecting(); 
        displayBorrowedTable();
        displayOverdueBooksTable();
        displayReturningBooksTable();
        cardLayout = (CardLayout)(CARD.getLayout());
        cardLayout.show(CARD, "borrowedBooksPANEL");
        buttonGroup1.add(received);
    }
    private void displayBorrowedTable (){
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String booksAvail = "SELECT * FROM bookrequests where status=?"; 
            pst = con.prepareStatement(booksAvail);
            String bookStatus = "ACCEPTED";
            pst.setString(1, bookStatus);
            rs = pst.executeQuery();
            borrowedBooks.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
        }
    }
    private void displayOverdueBooksTable (){
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String booksAvail = "SELECT * FROM bookrequests where status=?"; 
            pst = con.prepareStatement(booksAvail);
            String bookStatus = "ACCEPTED";
            pst.setString(1, bookStatus);
            rs = pst.executeQuery();
            overdueBooksTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
        }
    }
    private void displayReturningBooksTable (){
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String booksAvail = "SELECT * FROM returningbooks"; 
            pst = con.prepareStatement(booksAvail);
            rs = pst.executeQuery();
            returningBooks.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
        }
    }
    private void returnToInventory (){
        try {
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String updateInventory = "UPDATE booksavailable SET copies = copies+1 where bookid=?";
            pst = con.prepareStatement(updateInventory);
            String strReturningBook = INVIreturningBookID.getText();
            int intReturningBook = Integer.parseInt(strReturningBook);
            pst.setInt(1, intReturningBook);
            pst.executeUpdate();  
            JOptionPane.showMessageDialog(null, "Book Successfully Return to the Inventory.");
            
            String deleteHistory = "DELETE FROM returningbooks where borrowerid=? and bookid=?";
            pst = con.prepareStatement(deleteHistory);
            pst.setString(1, INVIreturningBorrowerID.getText());
            pst.setString(2, INVIreturningBookID.getText());
            pst.executeUpdate();
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
        todaydate.setText(dateTodayIs);
    }
    private void calculatingBookFee() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String d1 = borrowdate.getText();
        String d2 = todaydate.getText();
        try {
            LocalDate date1 = LocalDate.parse(d1, dtf);
            LocalDate date2 = LocalDate.parse(d2, dtf);
            long daysBetween = ChronoUnit.DAYS.between(date1, date2);
            if (daysBetween < 7){
                fee.setText("NO");
            }else if (daysBetween >= 7){
                fee.setText("50"); 
            }   
        }catch (Exception e){
        }
        }
    private void gettingEmailInfo (){
        try {
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String gettingEmail = "SELECT * FROM useraccounts where idnumber=?";
            pst = con.prepareStatement(gettingEmail);
            pst.setString(1, INVIBORROWERID.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                email.setText(rs.getString(10));
            }
                
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    private void updateBookCopies(){
//        try {
//            con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
//            String updatingCopies = "UPDATE booksavailable SET COPIES = COPIES - 1  WHERE bookid=? and COPIES > 1";
//            pst = con.prepareStatement(updatingCopies);
//            String strID = INVIreturningBookID.getText();//get yung isbn book na hihiramin and convert to int
//            int intID = Integer.parseInt(strID);
//            pst.setInt(1, intID);
//            pst.executeUpdate();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        
//    }
    //for sending emails overdue mails
    private void sendOverdueNotice () throws Exception{
        OverdueMail.SendMail(email.getText());
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
        CARD = new javax.swing.JPanel();
        borrowedBooksPANEL = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        borrowedBooks = new javax.swing.JTable();
        overdueBooksPANEL = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        overdueBooksTABLE = new javax.swing.JTable();
        inputID3 = new javax.swing.JLabel();
        inputID2 = new javax.swing.JLabel();
        inputID4 = new javax.swing.JLabel();
        inputID5 = new javax.swing.JLabel();
        inputID6 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        returndate = new javax.swing.JTextField();
        fee = new javax.swing.JTextField();
        todaydate = new javax.swing.JTextField();
        inputID7 = new javax.swing.JLabel();
        Clear = new javax.swing.JButton();
        notfy = new javax.swing.JButton();
        borrowdate = new javax.swing.JTextField();
        bookid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        returningBooksPANEL = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        received = new javax.swing.JRadioButton();
        receivedBTN = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        returningBooks = new javax.swing.JTable();
        overduebooksBTN = new javax.swing.JButton();
        returningbooksBTN = new javax.swing.JButton();
        borrowedbooksBTN = new javax.swing.JButton();
        home = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        BGGGGGGGGGGGGGG = new javax.swing.JLabel();
        INVIid = new javax.swing.JTextField();
        INVIname = new javax.swing.JTextField();
        INVIreturningBookID = new javax.swing.JTextField();
        INVIbookFee = new javax.swing.JTextField();
        INVIreturningBorrowerID = new javax.swing.JTextField();
        INVIbday = new javax.swing.JTextField();
        INVIBORROWERID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BOOK REPORTS");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CARD.setLayout(new java.awt.CardLayout());

        borrowedBooksPANEL.setBackground(new java.awt.Color(255, 255, 255));
        borrowedBooksPANEL.setName("borrowedBooksPANEL"); // NOI18N
        borrowedBooksPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("BORROWED BOOKS");
        borrowedBooksPANEL.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 260, 40));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        borrowedBooksPANEL.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 40));

        borrowedBooks.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        borrowedBooks.setModel(new javax.swing.table.DefaultTableModel(
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
        borrowedBooks.setEnabled(false);
        borrowedBooks.setRowHeight(25);
        borrowedBooks.setSelectionBackground(new java.awt.Color(206, 18, 18));
        jScrollPane1.setViewportView(borrowedBooks);

        borrowedBooksPANEL.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1270, 460));

        CARD.add(borrowedBooksPANEL, "borrowedBooksPANEL");

        overdueBooksPANEL.setBackground(new java.awt.Color(255, 255, 255));
        overdueBooksPANEL.setName("overdueBooksPANEL"); // NOI18N
        overdueBooksPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        overdueBooksTABLE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        overdueBooksTABLE.setModel(new javax.swing.table.DefaultTableModel(
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
        overdueBooksTABLE.setRowHeight(25);
        overdueBooksTABLE.setSelectionBackground(new java.awt.Color(206, 18, 18));
        overdueBooksTABLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                overdueBooksTABLEMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(overdueBooksTABLE);

        overdueBooksPANEL.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 58, 840, 460));

        inputID3.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID3.setText("Book ID:");
        overdueBooksPANEL.add(inputID3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 80, 30));

        inputID2.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID2.setText("Borrowed Date:");
        overdueBooksPANEL.add(inputID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 130, 30));

        inputID4.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID4.setText("Return Date:");
        overdueBooksPANEL.add(inputID4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 120, 30));

        inputID5.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID5.setText("Today Date:");
        overdueBooksPANEL.add(inputID5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 110, 30));

        inputID6.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID6.setText("Fee:");
        overdueBooksPANEL.add(inputID6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 60, 30));

        email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        overdueBooksPANEL.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 200, -1));

        returndate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        overdueBooksPANEL.add(returndate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 200, -1));

        fee.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        overdueBooksPANEL.add(fee, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 200, -1));

        todaydate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        overdueBooksPANEL.add(todaydate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 200, -1));

        inputID7.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID7.setText("Email:");
        overdueBooksPANEL.add(inputID7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 60, 30));

        Clear.setBackground(new java.awt.Color(255, 255, 255));
        Clear.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/es.png"))); // NOI18N
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        overdueBooksPANEL.add(Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 120, 40));

        notfy.setBackground(new java.awt.Color(255, 255, 255));
        notfy.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        notfy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/ema.png"))); // NOI18N
        notfy.setText("Notify");
        notfy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notfyActionPerformed(evt);
            }
        });
        overdueBooksPANEL.add(notfy, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 120, -1));

        borrowdate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        overdueBooksPANEL.add(borrowdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 200, -1));

        bookid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        overdueBooksPANEL.add(bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 200, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/3.png"))); // NOI18N
        overdueBooksPANEL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 410, 460));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("OVERDUE BOOKS");
        overdueBooksPANEL.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 210, 40));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        overdueBooksPANEL.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 40));

        CARD.add(overdueBooksPANEL, "overdueBooksPANEL");

        returningBooksPANEL.setBackground(new java.awt.Color(255, 255, 255));
        returningBooksPANEL.setName("returningBooksPANEL"); // NOI18N
        returningBooksPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("STATUS:");
        returningBooksPANEL.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 100, 40));

        received.setBackground(new java.awt.Color(255, 255, 255));
        received.setFont(new java.awt.Font("Dubai", 1, 20)); // NOI18N
        received.setForeground(new java.awt.Color(255, 255, 255));
        received.setText("RECEIVED");
        received.setOpaque(false);
        returningBooksPANEL.add(received, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 120, 40));

        receivedBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/check (1).png"))); // NOI18N
        receivedBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                receivedBTNMouseClicked(evt);
            }
        });
        returningBooksPANEL.add(receivedBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 50, 50));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        returningBooksPANEL.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 40));

        returningBooks.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        returningBooks.setModel(new javax.swing.table.DefaultTableModel(
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
        returningBooks.setRowHeight(25);
        returningBooks.setSelectionBackground(new java.awt.Color(206, 18, 18));
        returningBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returningBooksMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(returningBooks);

        returningBooksPANEL.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1270, 460));

        CARD.add(returningBooksPANEL, "returningBooksPANEL");

        jPanel1.add(CARD, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 1290, 530));

        overduebooksBTN.setBackground(new java.awt.Color(206, 18, 18));
        overduebooksBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        overduebooksBTN.setForeground(new java.awt.Color(255, 255, 255));
        overduebooksBTN.setText("Overdue Books");
        overduebooksBTN.setName(""); // NOI18N
        overduebooksBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overduebooksBTNActionPerformed(evt);
            }
        });
        jPanel1.add(overduebooksBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 430, 40));

        returningbooksBTN.setBackground(new java.awt.Color(206, 18, 18));
        returningbooksBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        returningbooksBTN.setForeground(new java.awt.Color(255, 255, 255));
        returningbooksBTN.setText("Returning Books");
        returningbooksBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returningbooksBTNActionPerformed(evt);
            }
        });
        jPanel1.add(returningbooksBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 270, 430, 40));

        borrowedbooksBTN.setBackground(new java.awt.Color(206, 18, 18));
        borrowedbooksBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        borrowedbooksBTN.setForeground(new java.awt.Color(255, 255, 255));
        borrowedbooksBTN.setText("Borrowed Books");
        borrowedbooksBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowedbooksBTNActionPerformed(evt);
            }
        });
        jPanel1.add(borrowedbooksBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 430, 40));

        home.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        home.setForeground(new java.awt.Color(204, 0, 0));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Librarysystem/home (1).png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 150, 80, 90));

        jLabel12.setFont(new java.awt.Font("Dubai", 1, 35)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("BOOK REPORTS");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 320, 60));

        BGGGGGGGGGGGGGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/BGJLABELUE.png"))); // NOI18N
        jPanel1.add(BGGGGGGGGGGGGGG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 900));

        INVIid.setEditable(false);
        jPanel1.add(INVIid, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 80, -1));

        INVIname.setEditable(false);
        jPanel1.add(INVIname, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, 80, -1));
        jPanel1.add(INVIreturningBookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 640, 90, -1));
        jPanel1.add(INVIbookFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 60, -1));
        jPanel1.add(INVIreturningBorrowerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 60, -1));

        INVIbday.setEditable(false);
        jPanel1.add(INVIbday, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 660, 80, -1));
        jPanel1.add(INVIBORROWERID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void borrowedbooksBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowedbooksBTNActionPerformed
    cardLayout.show(CARD, "borrowedBooksPANEL");
    }//GEN-LAST:event_borrowedbooksBTNActionPerformed

    private void overduebooksBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overduebooksBTNActionPerformed
    cardLayout.show(CARD, "overdueBooksPANEL");
    }//GEN-LAST:event_overduebooksBTNActionPerformed

    private void returningbooksBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returningbooksBTNActionPerformed
    cardLayout.show(CARD, "returningBooksPANEL");
    }//GEN-LAST:event_returningbooksBTNActionPerformed

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
    Admin_Module AdminFrame = new Admin_Module();
    Admin_Module.UserIDTxtField.setText(bookReports.INVIid.getText());
    Admin_Module.NameTxtField.setText(bookReports.INVIname.getText());
    Admin_Module.dateNgayonField.setText(bookReports.INVIbday.getText());
    AdminFrame.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_homeMouseClicked

    private void returningBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returningBooksMouseClicked
    DefaultTableModel model = (DefaultTableModel)returningBooks.getModel();
    int position = returningBooks.getSelectedRow();
    //String bookFEE= (model.getValueAt(position, 8).toString());
    INVIbookFee.setText(model.getValueAt(position, 8).toString());
    INVIreturningBorrowerID.setText(model.getValueAt(position, 1).toString());
    INVIreturningBookID.setText(model.getValueAt(position, 4).toString());
   
    }//GEN-LAST:event_returningBooksMouseClicked

    private void receivedBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_receivedBTNMouseClicked
    if(received.isSelected()){
        if (INVIbookFee.getText().equals("50")){
            int confi = JOptionPane.showConfirmDialog(this, "This User has a pending charge of 50 pesos fee. \nIf they already paid, ignore this.", "Pending Amount", JOptionPane.OK_OPTION);
                if (confi == 0){
                returnToInventory();
                displayReturningBooksTable();
                //updateBookCopies();
            }
        }    
    }else {
        JOptionPane.showMessageDialog(this, "Click the Received Button First!", "No Status", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    if(received.isSelected()){
       if (INVIbookFee.getText().equals("NO")){
            returnToInventory();
            displayReturningBooksTable(); 
       }
    }else {
        JOptionPane.showMessageDialog(this, "Click the Received Button First!", "No Status", JOptionPane.ERROR_MESSAGE);
        return;
    }

    
    }//GEN-LAST:event_receivedBTNMouseClicked

    private void overdueBooksTABLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_overdueBooksTABLEMouseClicked
    DefaultTableModel model = new DefaultTableModel();
//    int position = overdueBooksTABLE.getSelectedRow();
    
//    bookid.setText(model.getValueAt(position, 5).toString());
//    //email.setText(t);
//    borrowdate.setText(model.getValueAt(position, 6).toString());
//    returndate.setText(model.getValueAt(position, 7).toString());
    gettingTodaysDate();
    calculatingBookFee(); 

    String status = overdueBooksTABLE.getValueAt(overdueBooksTABLE.getSelectedRow(),0).toString();
    String borrowerid = overdueBooksTABLE.getValueAt(overdueBooksTABLE.getSelectedRow(),1).toString();
    String borrowername = overdueBooksTABLE.getValueAt(overdueBooksTABLE.getSelectedRow(),2).toString();
    String membertype = overdueBooksTABLE.getValueAt(overdueBooksTABLE.getSelectedRow(),3).toString();
    String bookname = overdueBooksTABLE.getValueAt(overdueBooksTABLE.getSelectedRow(),4).toString();
    String bookidd = overdueBooksTABLE.getValueAt(overdueBooksTABLE.getSelectedRow(),5).toString();
    String dateborrowed = overdueBooksTABLE.getValueAt(overdueBooksTABLE.getSelectedRow(),6).toString();
    String datereturn = overdueBooksTABLE.getValueAt(overdueBooksTABLE.getSelectedRow(),7).toString();
    
    bookid.setText(bookidd);
    INVIBORROWERID.setText(borrowerid);
    gettingEmailInfo();
    borrowdate.setText(dateborrowed);
    returndate.setText(datereturn);
    gettingTodaysDate();
    calculatingBookFee(); 
    }//GEN-LAST:event_overdueBooksTABLEMouseClicked

    private void notfyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notfyActionPerformed
    if (bookid.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "Please Select From the Table First!", "No Selection", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (fee.getText().equals("NO")){
        JOptionPane.showMessageDialog(null, "This User doesnt have a pending balance.");
        return;
    }
    if (fee.getText().equals("50")){
        try {
            sendOverdueNotice ();
        } catch (Exception ex) {
            Logger.getLogger(bookReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_notfyActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
    bookid.setText(null);
    email.setText(null);
    borrowdate.setText(null);
    returndate.setText(null);
    todaydate.setText(null);
    fee.setText(null);
    }//GEN-LAST:event_ClearActionPerformed

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
            java.util.logging.Logger.getLogger(bookReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bookReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bookReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bookReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bookReports().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BGGGGGGGGGGGGGG;
    private javax.swing.JPanel CARD;
    private javax.swing.JButton Clear;
    private javax.swing.JTextField INVIBORROWERID;
    public static javax.swing.JTextField INVIbday;
    private javax.swing.JTextField INVIbookFee;
    public static javax.swing.JTextField INVIid;
    public static javax.swing.JTextField INVIname;
    private javax.swing.JTextField INVIreturningBookID;
    private javax.swing.JTextField INVIreturningBorrowerID;
    private javax.swing.JTextField bookid;
    private javax.swing.JTextField borrowdate;
    private javax.swing.JTable borrowedBooks;
    private javax.swing.JPanel borrowedBooksPANEL;
    private javax.swing.JButton borrowedbooksBTN;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fee;
    private javax.swing.JLabel home;
    private javax.swing.JLabel inputID2;
    private javax.swing.JLabel inputID3;
    private javax.swing.JLabel inputID4;
    private javax.swing.JLabel inputID5;
    private javax.swing.JLabel inputID6;
    private javax.swing.JLabel inputID7;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton notfy;
    private javax.swing.JPanel overdueBooksPANEL;
    private javax.swing.JTable overdueBooksTABLE;
    private javax.swing.JButton overduebooksBTN;
    private javax.swing.JRadioButton received;
    private javax.swing.JLabel receivedBTN;
    private javax.swing.JTextField returndate;
    private javax.swing.JTable returningBooks;
    private javax.swing.JPanel returningBooksPANEL;
    private javax.swing.JButton returningbooksBTN;
    private javax.swing.JTextField todaydate;
    // End of variables declaration//GEN-END:variables
}
