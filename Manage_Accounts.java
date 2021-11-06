
package librarysystem;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;


public class Manage_Accounts extends javax.swing.JFrame {
    DefaultTableModel tbModel = new DefaultTableModel();
    Connection con = connectLibrary.connecting(); 
    ResultSet rs;
    PreparedStatement pst;
    Statement st;
    CardLayout cardLayout;
    int idNum1, idNum2;
    
    public Manage_Accounts() {
        initComponents();
        connectLibrary.connecting(); 
        cardLayout = (CardLayout)(CARD.getLayout());
        cardLayout.show(CARD, "addNewAdminPANEL");
        limitDate();
        generateAdminID();
        generateUserID();
        displayUserTable();
        displayAdminTable();
        autoUpdateAdminTable();
        autoUpdateUserTable();
        departmentTXT.setVisible(false);
        errormsg.setVisible(false);
    }
    private void limitDate (){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yyyy", Locale.ENGLISH);
            String fromDate = "01/01/1900";
            String toDate = "01/01/2010";
            Date date1 = sdf.parse(fromDate);
            Date date2 = sdf.parse(toDate);
            bDayAdmin.getCalendar();
            bdayUSER.getCalendar();
            bDayAdmin.setSelectableDateRange(date1, date2);
            bdayUSER.setSelectableDateRange(date1, date2);
            //bDay.setDate(date2);
        }catch (ParseException e){
        }
    }
    private void generateAdminID(){
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String idNums = "SELECT max(idnumber) from adminaccounts";
            st = con.createStatement();
            rs = st.executeQuery(idNums);
            if (rs.next()){
                idNum1 = rs.getInt(1);
                idNum1++;
                newADMINID.setText(Integer.toString(idNum1));  
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void generateUserID(){
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String idNums = "SELECT max(idnumber) from useraccounts";
            st = con.createStatement();
            rs = st.executeQuery(idNums);
            if (rs.next()){
                idNum2 = rs.getInt(1);
                idNum2++;
                newUSERIDFIELD.setText(Integer.toString(idNum2));  
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void displayUserTable(){
       try{
        //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
        String tbl = "SELECT * FROM useraccounts"; 
        pst = con.prepareStatement(tbl);
        rs = pst.executeQuery();
        removeUserTable.setModel(DbUtils.resultSetToTableModel(rs));
    }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void displayAdminTable(){
       try{
        //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
        String tblA = "SELECT * FROM adminaccounts"; 
        pst = con.prepareStatement(tblA);
        rs = pst.executeQuery();
        removeAdminTable.setModel(DbUtils.resultSetToTableModel(rs));
    }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void autoUpdateUserTable() { 
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String table = "SELECT * from useraccounts";
            pst = con.prepareStatement(table);
            rs = pst.executeQuery();
            removeUserTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void autoUpdateAdminTable() { 
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String tablez = "SELECT * from adminaccounts";
            pst = con.prepareStatement(tablez);
            rs = pst.executeQuery();
            removeAdminTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        removeAdminBTN = new javax.swing.JButton();
        addUserBTN = new javax.swing.JButton();
        addAdminBTN = new javax.swing.JButton();
        removeUserBTN = new javax.swing.JButton();
        home = new javax.swing.JLabel();
        CARD = new javax.swing.JPanel();
        addNewAdminPANEL = new javax.swing.JPanel();
        titleINFO1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        newADMINID = new javax.swing.JTextField();
        lastname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        firstname = new javax.swing.JTextField();
        bDayAdmin = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        showpassword = new javax.swing.JCheckBox();
        resetadmin = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        addNewUserPANEL = new javax.swing.JPanel();
        newUSERIDFIELD = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        memberType = new javax.swing.JComboBox<>();
        lastnameUSER = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        firstnameUSER = new javax.swing.JTextField();
        bdayUSER = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        passwordUSER = new javax.swing.JPasswordField();
        showpassword1 = new javax.swing.JCheckBox();
        courseUSER = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        departmentTXT = new javax.swing.JLabel();
        cbCollege = new javax.swing.JComboBox<>();
        sectionUSER = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        collegeTXT = new javax.swing.JLabel();
        titleINFO2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        resetuser = new javax.swing.JButton();
        REGISTERUSER = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        errormsg = new javax.swing.JLabel();
        removeAdminPANEL = new javax.swing.JPanel();
        titleINFO3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        removeAdminTable = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        adminTXT = new javax.swing.JTextField();
        deleteAdmin = new javax.swing.JButton();
        removeUserPANEL = new javax.swing.JPanel();
        titleINFO4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        userTXT = new javax.swing.JTextField();
        deleteUser = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        removeUserTable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        BBBBBBBBBGGGGGGGGGGG = new javax.swing.JLabel();
        INVIid = new javax.swing.JTextField();
        INVIname = new javax.swing.JTextField();
        INVIbday = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MANAGE USERS");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        removeAdminBTN.setBackground(new java.awt.Color(206, 18, 18));
        removeAdminBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        removeAdminBTN.setForeground(new java.awt.Color(255, 255, 255));
        removeAdminBTN.setText("Remove Admin");
        removeAdminBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAdminBTNActionPerformed(evt);
            }
        });
        jPanel1.add(removeAdminBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 310, 40));

        addUserBTN.setBackground(new java.awt.Color(206, 18, 18));
        addUserBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        addUserBTN.setForeground(new java.awt.Color(255, 255, 255));
        addUserBTN.setText("Add New User");
        addUserBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserBTNActionPerformed(evt);
            }
        });
        jPanel1.add(addUserBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 320, 40));

        addAdminBTN.setBackground(new java.awt.Color(206, 18, 18));
        addAdminBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        addAdminBTN.setForeground(new java.awt.Color(255, 255, 255));
        addAdminBTN.setText("Add New Admin");
        addAdminBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAdminBTNActionPerformed(evt);
            }
        });
        jPanel1.add(addAdminBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 330, 40));

        removeUserBTN.setBackground(new java.awt.Color(206, 18, 18));
        removeUserBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        removeUserBTN.setForeground(new java.awt.Color(255, 255, 255));
        removeUserBTN.setText("Remove User");
        removeUserBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserBTNActionPerformed(evt);
            }
        });
        jPanel1.add(removeUserBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 260, 330, 40));

        home.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        home.setForeground(new java.awt.Color(204, 0, 0));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Librarysystem/home (1).png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 150, 80, 90));

        CARD.setLayout(new java.awt.CardLayout());

        addNewAdminPANEL.setBackground(new java.awt.Color(255, 255, 255));
        addNewAdminPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleINFO1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        titleINFO1.setForeground(new java.awt.Color(255, 255, 255));
        titleINFO1.setText("ADD NEW ADMIN");
        addNewAdminPANEL.add(titleINFO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 200, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        addNewAdminPANEL.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 40));

        jLabel4.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel4.setText("ID Number:");
        addNewAdminPANEL.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 180, 30));

        newADMINID.setEditable(false);
        newADMINID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addNewAdminPANEL.add(newADMINID, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 270, 30));

        lastname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addNewAdminPANEL.add(lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 270, 30));

        jLabel7.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel7.setText("Last Name:");
        addNewAdminPANEL.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 160, 30));

        jLabel5.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel5.setText("First Name:");
        addNewAdminPANEL.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, 30));

        firstname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addNewAdminPANEL.add(firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 270, 30));

        bDayAdmin.setDateFormatString("MM/dd/yyyy");
        bDayAdmin.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addNewAdminPANEL.add(bDayAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 270, 30));

        jLabel8.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel8.setText("Birthdate:");
        addNewAdminPANEL.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, 30));

        jLabel2.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel2.setText("Password:");
        addNewAdminPANEL.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 160, 30));

        pass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addNewAdminPANEL.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 270, 30));

        showpassword.setBackground(new java.awt.Color(255, 255, 255));
        showpassword.setText("Show Password");
        showpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpasswordActionPerformed(evt);
            }
        });
        addNewAdminPANEL.add(showpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 120, 30));

        resetadmin.setBackground(new java.awt.Color(255, 255, 255));
        resetadmin.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        resetadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/ress.png"))); // NOI18N
        resetadmin.setText("Reset");
        resetadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetadminActionPerformed(evt);
            }
        });
        addNewAdminPANEL.add(resetadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 140, 40));

        btn_save.setBackground(new java.awt.Color(255, 255, 255));
        btn_save.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/save1.png"))); // NOI18N
        btn_save.setText("Register");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        addNewAdminPANEL.add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 440, 140, 40));

        CARD.add(addNewAdminPANEL, "addNewAdminPANEL");

        addNewUserPANEL.setBackground(new java.awt.Color(255, 255, 255));
        addNewUserPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newUSERIDFIELD.setEditable(false);
        newUSERIDFIELD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        addNewUserPANEL.add(newUSERIDFIELD, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 270, -1));

        jLabel13.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel13.setText("ID Number:");
        addNewUserPANEL.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, 40));

        jLabel11.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel11.setText("Member Type:");
        addNewUserPANEL.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 140, 30));

        memberType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        memberType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Faculty" }));
        memberType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memberTypeItemStateChanged(evt);
            }
        });
        addNewUserPANEL.add(memberType, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 270, 30));

        lastnameUSER.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addNewUserPANEL.add(lastnameUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 270, -1));

        jLabel3.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel3.setText("Email:");
        addNewUserPANEL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 110, 30));

        jLabel6.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel6.setText("First Name:");
        addNewUserPANEL.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        firstnameUSER.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addNewUserPANEL.add(firstnameUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 270, -1));

        bdayUSER.setDateFormatString("MM/dd/yyyy");
        bdayUSER.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addNewUserPANEL.add(bdayUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 270, 30));

        jLabel10.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel10.setText("Birthdate:");
        addNewUserPANEL.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 120, -1));

        jLabel15.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel15.setText("Password:");
        addNewUserPANEL.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, -1, 30));

        passwordUSER.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addNewUserPANEL.add(passwordUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 270, -1));

        showpassword1.setBackground(new java.awt.Color(255, 255, 255));
        showpassword1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        showpassword1.setText("Show Password");
        showpassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassword1ActionPerformed(evt);
            }
        });
        addNewUserPANEL.add(showpassword1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 120, 20));

        courseUSER.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addNewUserPANEL.add(courseUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 270, -1));

        jLabel16.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel16.setText("Course:");
        addNewUserPANEL.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, 80, -1));

        jLabel23.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel23.setText("Last Name:");
        addNewUserPANEL.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        departmentTXT.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        departmentTXT.setText("Department:");
        addNewUserPANEL.add(departmentTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 120, 30));

        cbCollege.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbCollege.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "College of Engineering", "College of Law", "College of Dentistry", "College of Arts and Sciences", "College of Business Administration", "College of Computer Studies and Systems", "College of Education" }));
        addNewUserPANEL.add(cbCollege, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 250, 270, 30));

        sectionUSER.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addNewUserPANEL.add(sectionUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, 270, -1));

        jLabel17.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        jLabel17.setText("Section: ");
        addNewUserPANEL.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 300, -1, -1));

        collegeTXT.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        collegeTXT.setText("College:");
        addNewUserPANEL.add(collegeTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, 80, 30));

        titleINFO2.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        titleINFO2.setForeground(new java.awt.Color(255, 255, 255));
        titleINFO2.setText("ADD NEW USER");
        addNewUserPANEL.add(titleINFO2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 200, 40));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        addNewUserPANEL.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 40));

        resetuser.setBackground(new java.awt.Color(255, 255, 255));
        resetuser.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        resetuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/ress.png"))); // NOI18N
        resetuser.setText("Reset");
        resetuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetuserActionPerformed(evt);
            }
        });
        addNewUserPANEL.add(resetuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 140, 40));

        REGISTERUSER.setBackground(new java.awt.Color(255, 255, 255));
        REGISTERUSER.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        REGISTERUSER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/save1.png"))); // NOI18N
        REGISTERUSER.setText("Register");
        REGISTERUSER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGISTERUSERActionPerformed(evt);
            }
        });
        addNewUserPANEL.add(REGISTERUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 440, 140, 40));

        email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailKeyPressed(evt);
            }
        });
        addNewUserPANEL.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 270, 30));

        errormsg.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        errormsg.setForeground(new java.awt.Color(255, 0, 0));
        errormsg.setText("Please enter a valid email address.");
        addNewUserPANEL.add(errormsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, -1, 20));

        CARD.add(addNewUserPANEL, "addNewUserPANEL");

        removeAdminPANEL.setBackground(new java.awt.Color(255, 255, 255));
        removeAdminPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleINFO3.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        titleINFO3.setForeground(new java.awt.Color(255, 255, 255));
        titleINFO3.setText("REMOVE ADMIN");
        removeAdminPANEL.add(titleINFO3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 200, 40));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        removeAdminPANEL.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1250, 40));

        removeAdminTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        removeAdminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        removeAdminTable.setGridColor(new java.awt.Color(0, 0, 0));
        removeAdminTable.setRowHeight(25);
        removeAdminTable.setSelectionBackground(new java.awt.Color(206, 18, 18));
        removeAdminTable.getTableHeader().setResizingAllowed(false);
        removeAdminTable.getTableHeader().setReorderingAllowed(false);
        removeAdminTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeAdminTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(removeAdminTable);

        removeAdminPANEL.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1250, 400));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(206, 18, 18));
        jLabel20.setText("Enter Admin ID:");
        removeAdminPANEL.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, 170, 40));

        adminTXT.setEditable(false);
        adminTXT.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        removeAdminPANEL.add(adminTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, 330, 40));

        deleteAdmin.setBackground(new java.awt.Color(255, 255, 255));
        deleteAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/tr1.png"))); // NOI18N
        deleteAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAdminActionPerformed(evt);
            }
        });
        removeAdminPANEL.add(deleteAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 480, 50, 40));

        CARD.add(removeAdminPANEL, "removeAdminPANEL");

        removeUserPANEL.setBackground(new java.awt.Color(255, 255, 255));
        removeUserPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleINFO4.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        titleINFO4.setForeground(new java.awt.Color(255, 255, 255));
        titleINFO4.setText("REMOVE USER");
        removeUserPANEL.add(titleINFO4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 200, 40));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        removeUserPANEL.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1250, 40));

        userTXT.setEditable(false);
        userTXT.setFont(new java.awt.Font("Dubai", 1, 20)); // NOI18N
        removeUserPANEL.add(userTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, 330, 40));

        deleteUser.setBackground(new java.awt.Color(255, 255, 255));
        deleteUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/tr1.png"))); // NOI18N
        deleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserActionPerformed(evt);
            }
        });
        removeUserPANEL.add(deleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 480, 50, 40));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(206, 18, 18));
        jLabel22.setText("Enter User ID:");
        removeUserPANEL.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, 170, 40));

        removeUserTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        removeUserTable.setModel(new javax.swing.table.DefaultTableModel(
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
        removeUserTable.setRowHeight(25);
        removeUserTable.setSelectionBackground(new java.awt.Color(206, 18, 18));
        removeUserTable.getTableHeader().setResizingAllowed(false);
        removeUserTable.getTableHeader().setReorderingAllowed(false);
        removeUserTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeUserTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(removeUserTable);

        removeUserPANEL.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1250, 400));

        CARD.add(removeUserPANEL, "removeUserPANEL");

        jPanel1.add(CARD, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 1290, 540));

        jLabel12.setFont(new java.awt.Font("Dubai", 1, 35)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("MANAGE USERS");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 300, 60));

        BBBBBBBBBGGGGGGGGGGG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/BGJLABELUE.png"))); // NOI18N
        jPanel1.add(BBBBBBBBBGGGGGGGGGGG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, -1));

        INVIid.setEditable(false);
        jPanel1.add(INVIid, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 80, -1));

        INVIname.setEditable(false);
        jPanel1.add(INVIname, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, 80, -1));

        INVIbday.setEditable(false);
        jPanel1.add(INVIbday, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 660, 80, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void removeAdminBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAdminBTNActionPerformed
    displayUserTable();
    displayAdminTable();    
    cardLayout.show(CARD, "removeAdminPANEL");
    }//GEN-LAST:event_removeAdminBTNActionPerformed

    private void addUserBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserBTNActionPerformed
    generateAdminID();
    generateUserID();    
    cardLayout.show(CARD, "addNewUserPANEL");
    }//GEN-LAST:event_addUserBTNActionPerformed

    private void addAdminBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdminBTNActionPerformed
    generateAdminID();
    generateUserID();    
    cardLayout.show(CARD, "addNewAdminPANEL");
    }//GEN-LAST:event_addAdminBTNActionPerformed

    private void removeUserBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserBTNActionPerformed
    displayUserTable();
    displayAdminTable();    
    cardLayout.show(CARD, "removeUserPANEL");
    }//GEN-LAST:event_removeUserBTNActionPerformed

    private void showpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpasswordActionPerformed
        if(showpassword.isSelected()){
            pass.setEchoChar((char)0);
        }
        else{
            pass.setEchoChar('*');
        }
    }//GEN-LAST:event_showpasswordActionPerformed

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        Admin_Module AdminFrame = new Admin_Module();
        
        Admin_Module.UserIDTxtField.setText(Manage_Accounts.INVIid.getText());
        Admin_Module.NameTxtField.setText(Manage_Accounts.INVIname.getText());
        Admin_Module.dateNgayonField.setText(Manage_Accounts.INVIbday.getText());
        AdminFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeMouseClicked

    private void memberTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memberTypeItemStateChanged
        if (memberType.getSelectedItem() == "Faculty"){
            courseUSER.setEnabled(false);
            collegeTXT.setVisible(false);
            departmentTXT.setVisible(true);
            sectionUSER.setEnabled(false);
        }else if (memberType.getSelectedItem() == "Student"){
            courseUSER.setEnabled(true);
            sectionUSER.setEnabled(true);
            collegeTXT.setVisible(true);
            departmentTXT.setVisible(false);
        }
    }//GEN-LAST:event_memberTypeItemStateChanged

    private void showpassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassword1ActionPerformed
        if(showpassword1.isSelected())
        {
            passwordUSER.setEchoChar((char)0);
        }
        else
        {
            passwordUSER.setEchoChar('*');
        }
    }//GEN-LAST:event_showpassword1ActionPerformed
        
    private void removeAdminTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeAdminTableMouseClicked
        DefaultTableModel model = (DefaultTableModel)removeAdminTable.getModel();
        int position = removeAdminTable.getSelectedRow();
        adminTXT.setText(model.getValueAt(position, 0).toString());
    }//GEN-LAST:event_removeAdminTableMouseClicked

    private void deleteAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAdminActionPerformed
        if (adminTXT.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please select the Admin ID First!", "Blank Field", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Delete Admin Account", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION){
            try{
                //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
                String delQuery = "DELETE FROM adminaccounts WHERE idnumber = ?";
                pst = con.prepareStatement(delQuery);
                String adNum = adminTXT.getText();
                pst.setString(1, adNum);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Admin Account Successfully Deleted.");
                autoUpdateAdminTable();
                adminTXT.setText(null);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_deleteAdminActionPerformed

    private void deleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserActionPerformed
        if (userTXT.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please select the User ID First!", "Blank Field", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Delete User Account", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            try{
                //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
                String delQuery = "DELETE FROM useraccounts WHERE idnumber = "+ userTXT.getText();
                pst = con.prepareStatement(delQuery);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "User Account Successfully Deleted.");
                autoUpdateUserTable();
                userTXT.setText(null);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_deleteUserActionPerformed

    private void removeUserTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeUserTableMouseClicked
        DefaultTableModel model = (DefaultTableModel)removeUserTable.getModel();
        int position = removeUserTable.getSelectedRow();
        userTXT.setText(model.getValueAt(position, 0).toString());
    }//GEN-LAST:event_removeUserTableMouseClicked

    private void emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyPressed
    if (!email.getText().contains("@")){
        errormsg.setVisible(true);
    }else if(!email.getText().contains(".com")){
        errormsg.setVisible(true);
    }else{
        errormsg.setVisible(false);
        }
    }//GEN-LAST:event_emailKeyPressed

    private void REGISTERUSERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGISTERUSERActionPerformed
        if (memberType.getSelectedItem() == "Student"){
            if (lastnameUSER.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (firstnameUSER.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (memberType.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
                //            }else if (newADMINID.getText().isEmpty()){
                //                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                //                return;
            }else if (passwordUSER.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (courseUSER.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (cbCollege.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (sectionUSER.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (bdayUSER.getDate()== null){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (email.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }if (memberType.getSelectedItem() == "Faculty"){
            if (lastnameUSER.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (firstnameUSER.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (memberType.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
                //            }else if (newADMINID.getText().isEmpty()){
                //                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                //                return;
            }else if (passwordUSER.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (cbCollege.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (bdayUSER.getDate()== null){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (email.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }int userConfirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this new user?", "Add New User", JOptionPane.YES_NO_OPTION);
        if (userConfirm == 0){
            try {
                //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
                String addUser = "INSERT INTO useraccounts values (?,?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(addUser);
                String firstnU = firstnameUSER.getText();
                String lastnU = lastnameUSER.getText();
                String idU = newUSERIDFIELD.getText();
                String pass1U = passwordUSER.getText();
                String courzU = courseUSER.getText();
                String collegezU = (String)cbCollege.getSelectedItem();
                String sectionzU = sectionUSER.getText();
                String roleU = (String)memberType.getSelectedItem();
                String bDateU = ((JTextField)bdayUSER.getDateEditor().getUiComponent()).getText();
                String emailAccountU = email.getText();
                pst.setString(1, idU);
                pst.setString(2, roleU);
                pst.setString(3, lastnU);
                pst.setString(4, firstnU);
                pst.setString(5, bDateU);
                pst.setString(6, pass1U);
                pst.setString(7, courzU);
                pst.setString(8, collegezU);
                pst.setString(9, sectionzU);
                pst.setString(10, emailAccountU);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "New User Account Successfully Added.");
                generateUserID();
                limitDate();
                bdayUSER.validate();
                lastnameUSER.setText(null);
                firstnameUSER.setText(null);
                passwordUSER.setText(null);
                courseUSER.setText(null);
                sectionUSER.setText(null);
                cbCollege.setSelectedIndex(0);
                memberType.setSelectedIndex(0);
                email.setText(null);
                bdayUSER.setDate(null);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_REGISTERUSERActionPerformed

    private void resetuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetuserActionPerformed
        limitDate();
        bdayUSER.validate();
        lastnameUSER.setText(null);
        firstnameUSER.setText(null);
        passwordUSER.setText(null);
        courseUSER.setText(null);
        sectionUSER.setText(null);
        cbCollege.setSelectedIndex(0);
        memberType.setSelectedIndex(0);
        bdayUSER.setDate(null);
    }//GEN-LAST:event_resetuserActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        if (lastname.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (firstname.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (newADMINID.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (pass.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (bDayAdmin.getDate()== null){
            JOptionPane.showMessageDialog(this, "Please complete all the textfields first!", "Cannot Save", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int userConfirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this user as an Admin?", "Add New Admin", JOptionPane.YES_NO_OPTION);
        if (userConfirm == 0){
            try{
                //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
                String addAdmin = "INSERT INTO adminaccounts values (?,?,?,?,?)";
                pst = con.prepareStatement(addAdmin);

                String firstnA = firstname.getText();
                String lastnA  = lastname.getText();
                String idA = newADMINID.getText();
                String passwA = pass.getText();
                String bDateA = ((JTextField)bDayAdmin.getDateEditor().getUiComponent()).getText();
                pst.setString(1, idA);
                pst.setString(2, lastnA);
                pst.setString(3, firstnA);
                pst.setString(4, bDateA);
                pst.setString(5, passwA);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "New Admin Account Successfully Added.");
                generateAdminID();
                lastname.setText(null);
                firstname.setText(null);
                pass.setText(null);
                bDayAdmin.setDate(null);
                bDayAdmin.validate();
                bDayAdmin.setDate(null);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void resetadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetadminActionPerformed
        limitDate();
        lastname.setText(null);
        firstname.setText(null);
        pass.setText(null);
        bDayAdmin.validate();
        bDayAdmin.setDate(null);
    }//GEN-LAST:event_resetadminActionPerformed

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
            java.util.logging.Logger.getLogger(Manage_Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Accounts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BBBBBBBBBGGGGGGGGGGG;
    private javax.swing.JPanel CARD;
    public static javax.swing.JTextField INVIbday;
    public static javax.swing.JTextField INVIid;
    public static javax.swing.JTextField INVIname;
    private javax.swing.JButton REGISTERUSER;
    private javax.swing.JButton addAdminBTN;
    private javax.swing.JPanel addNewAdminPANEL;
    private javax.swing.JPanel addNewUserPANEL;
    private javax.swing.JButton addUserBTN;
    private javax.swing.JTextField adminTXT;
    private com.toedter.calendar.JDateChooser bDayAdmin;
    public static com.toedter.calendar.JDateChooser bdayUSER;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> cbCollege;
    private javax.swing.JLabel collegeTXT;
    private javax.swing.JTextField courseUSER;
    private javax.swing.JButton deleteAdmin;
    private javax.swing.JButton deleteUser;
    private javax.swing.JLabel departmentTXT;
    private javax.swing.JTextField email;
    private javax.swing.JLabel errormsg;
    private javax.swing.JTextField firstname;
    private javax.swing.JTextField firstnameUSER;
    private javax.swing.JLabel home;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lastname;
    private javax.swing.JTextField lastnameUSER;
    private javax.swing.JComboBox<String> memberType;
    private javax.swing.JTextField newADMINID;
    private javax.swing.JTextField newUSERIDFIELD;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPasswordField passwordUSER;
    private javax.swing.JButton removeAdminBTN;
    private javax.swing.JPanel removeAdminPANEL;
    private javax.swing.JTable removeAdminTable;
    private javax.swing.JButton removeUserBTN;
    private javax.swing.JPanel removeUserPANEL;
    private javax.swing.JTable removeUserTable;
    private javax.swing.JButton resetadmin;
    private javax.swing.JButton resetuser;
    private javax.swing.JTextField sectionUSER;
    private javax.swing.JCheckBox showpassword;
    private javax.swing.JCheckBox showpassword1;
    private javax.swing.JLabel titleINFO1;
    private javax.swing.JLabel titleINFO2;
    private javax.swing.JLabel titleINFO3;
    private javax.swing.JLabel titleINFO4;
    private javax.swing.JTextField userTXT;
    // End of variables declaration//GEN-END:variables

}
