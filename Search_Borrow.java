package librarysystem;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import javax.swing.RowFilter;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils; 

public class Search_Borrow extends javax.swing.JFrame {
    CardLayout cardLayout;
    Connection con = connectLibrary.connecting(); 
    ResultSet rs;
    PreparedStatement pst;
    
    public Search_Borrow() {
        initComponents();
        connectLibrary.connecting(); 
        displayTable();
        limitDate();
        cardLayout = (CardLayout)(ThreePANELS.getLayout());
        cardLayout.show(ThreePANELS, "isbnPANEL");
        isbnBORROW.setEnabled(true);
        titleBORROW.setEnabled(false);
        authorBORROW.setEnabled(false);
        publisherBorrow.setEnabled(false);
        yearBORROW.setEnabled(false);
        genreBORROW.setEnabled(false);
        bookCopiesBORROW.setEnabled(false);
        borrowBTN.setEnabled(false);
        cancelBTN.setEnabled(false);
    }
    private void displayTable (){
        try{
           // con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String booksAvail = "SELECT * FROM BOOKSAVAILABLE"; 
            pst = con.prepareStatement(booksAvail);
            rs = pst.executeQuery();
            genreTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
        }
    }
    private void filterGenre (String genre){
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) genreTABLE.getModel());
        genreTABLE.setRowSorter (sorter);
        if (!"ALL GENRE".equals(genre)){
            sorter.setRowFilter(RowFilter.regexFilter(genre));
        }else{
            genreTABLE.setRowSorter(sorter);
        }   
    }   
    private void autoUpdateTable(){ 
        try{
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String updatetable = "SELECT * from booksavailable";
            pst = con.prepareStatement(updatetable);
            rs = pst.executeQuery();
            genreTABLE.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e){
        }
    }
    private void limitDate() {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        LocalDate dateToday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate borrowDate = dateToday;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate lastDate = borrowDate.plusDays(7);
        LocalDate oneSem = borrowDate.plusDays(150);
        String dateBorrowed = borrowDate.format(formatter);
        String dateReturn = lastDate.format(formatter);
        String dateOneSem = oneSem.format(formatter);
        
        INVIfirstdate.setText(dateBorrowed);
        INVIseconddate.setText(dateReturn);
        
        INVI150days.setText(dateOneSem);
    }
    private void ifHasOverdueBook (){
        try {
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            String ifhasoverdue = "SELECT * from bookrequests where status=? AND borrowerid=?";
            pst = con.prepareStatement(ifhasoverdue);
            String currentStatus = "ACCEPTED";
            pst.setString(1, currentStatus);
            pst.setString(2, INVIid.getText());
            rs = pst.executeQuery();   
            if (rs.next()){
                checkDATEBORROWED.setText(rs.getString(7));
                Date date = new Date();
                LocalDate dateToday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate todayIs = dateToday;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateTodayIs = todayIs.format(formatter);
                todaysDate.setText(dateTodayIs);
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String d1 = checkDATEBORROWED.getText();
                String d2 = todaysDate.getText();
                try {
                    LocalDate date1 = LocalDate.parse(d1, dtf);
                    LocalDate date2 = LocalDate.parse(d2, dtf);
                    long daysBetween = ChronoUnit.DAYS.between(date1, date2);
                    if (daysBetween < 7){
                        IFFEE.setText("NO");
                    }else if (daysBetween >= 7){
                        IFFEE.setText("50"); 
                    }   
                }catch (Exception e){
                }
                }
            
        }catch (Exception e){
            e.printStackTrace();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        browseGenreBTN = new javax.swing.JButton();
        borrowBookBTN = new javax.swing.JButton();
        searchISBNBTN = new javax.swing.JButton();
        home = new javax.swing.JLabel();
        ThreePANELS = new javax.swing.JPanel();
        browsePANEL = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        selectGENRE = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        genreTABLE = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        isbnPANEL = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        enterISBN = new javax.swing.JTextField();
        isbnBTN = new javax.swing.JButton();
        ISBNTitle1 = new javax.swing.JLabel();
        bookTitle2 = new javax.swing.JLabel();
        authorTitle1 = new javax.swing.JLabel();
        authorField1 = new javax.swing.JTextField();
        bookTitleField1 = new javax.swing.JTextField();
        pubTitle1 = new javax.swing.JLabel();
        genreTitle1 = new javax.swing.JLabel();
        availTitle1 = new javax.swing.JLabel();
        publishedField1 = new javax.swing.JTextField();
        genreField1 = new javax.swing.JTextField();
        bookCopiesField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        publisher = new javax.swing.JTextField();
        borrowPANEL = new javax.swing.JPanel();
        userROLE = new javax.swing.JLabel();
        isbnBORROW = new javax.swing.JTextField();
        inputID = new javax.swing.JLabel();
        titleINFO1 = new javax.swing.JLabel();
        inputID2 = new javax.swing.JLabel();
        titleBORROW = new javax.swing.JTextField();
        inputID3 = new javax.swing.JLabel();
        authorBORROW = new javax.swing.JTextField();
        inputID4 = new javax.swing.JLabel();
        yearBORROW = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inputID5 = new javax.swing.JLabel();
        publisherBorrow = new javax.swing.JTextField();
        genreBORROW = new javax.swing.JTextField();
        inputID6 = new javax.swing.JLabel();
        bookCopiesBORROW = new javax.swing.JTextField();
        isbnBORROWBTN = new javax.swing.JButton();
        inputID7 = new javax.swing.JLabel();
        borrowBTN = new javax.swing.JButton();
        cancelBTN = new javax.swing.JButton();
        BACKGROUND = new javax.swing.JLabel();
        invisibleBORROW = new javax.swing.JLabel();
        invisibleRETURN1 = new javax.swing.JTextField();
        todaysDate = new javax.swing.JTextField();
        invisibleDIFFFaculty = new javax.swing.JTextField();
        bg = new javax.swing.JLabel();
        INVIid = new javax.swing.JTextField();
        checkDATEBORROWED = new javax.swing.JTextField();
        IFFEE = new javax.swing.JTextField();
        HomeBTN = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        INVIname = new javax.swing.JTextField();
        INVIbday = new javax.swing.JTextField();
        INVIcourse = new javax.swing.JTextField();
        INVIfirstdate = new javax.swing.JTextField();
        INVIseconddate = new javax.swing.JTextField();
        INVItype = new javax.swing.JTextField();
        INVI150days = new javax.swing.JTextField();
        ISBNField1 = new javax.swing.JTextField();
        INVIdateTodayIs = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SEARCH AND BORROW");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Dubai", 1, 31)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SEARCH / BORROW");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 320, 70));

        browseGenreBTN.setBackground(new java.awt.Color(206, 18, 18));
        browseGenreBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        browseGenreBTN.setForeground(new java.awt.Color(255, 255, 255));
        browseGenreBTN.setText("Browse by Genre");
        browseGenreBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseGenreBTNActionPerformed(evt);
            }
        });
        jPanel2.add(browseGenreBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 430, 40));

        borrowBookBTN.setBackground(new java.awt.Color(206, 18, 18));
        borrowBookBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        borrowBookBTN.setForeground(new java.awt.Color(255, 255, 255));
        borrowBookBTN.setText("Borrow a Book");
        borrowBookBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowBookBTNActionPerformed(evt);
            }
        });
        jPanel2.add(borrowBookBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 260, 420, 40));

        searchISBNBTN.setBackground(new java.awt.Color(206, 18, 18));
        searchISBNBTN.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        searchISBNBTN.setForeground(new java.awt.Color(255, 255, 255));
        searchISBNBTN.setText("Search By Book ID");
        searchISBNBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchISBNBTNActionPerformed(evt);
            }
        });
        jPanel2.add(searchISBNBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 440, 40));

        home.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        home.setForeground(new java.awt.Color(204, 0, 0));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/home (1).png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel2.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 150, 80, 90));

        ThreePANELS.setLayout(new java.awt.CardLayout());

        browsePANEL.setBackground(new java.awt.Color(255, 255, 255));
        browsePANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SELECT BOOK GENRE");
        browsePANEL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 220, 50));

        selectGENRE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectGENRE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL GENRE", "ACADEMIC TEXTBOOK", "CLASSICS", "COMEDY", "FANTASY", "HISTORY", "HORROR", "LITERARY FICTION", "GRAPHIC NOVEL", "MYSTERY", "ROMANCE", "SCIENCE FICTION", "SHORT STORIES" }));
        selectGENRE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectGENREItemStateChanged(evt);
            }
        });
        browsePANEL.add(selectGENRE, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 240, 30));

        genreTABLE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        genreTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BOOKID", "BOOKTITLE", "AUTHORNAME", "PUBLISHEDYEAR", "GENRE", "COPIES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        genreTABLE.setEnabled(false);
        genreTABLE.setGridColor(new java.awt.Color(255, 153, 153));
        genreTABLE.setRowHeight(25);
        genreTABLE.setSelectionBackground(new java.awt.Color(206, 18, 18));
        genreTABLE.getTableHeader().setResizingAllowed(false);
        genreTABLE.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(genreTABLE);

        browsePANEL.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1270, 410));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        browsePANEL.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 50));

        ThreePANELS.add(browsePANEL, "browsePANEL");

        isbnPANEL.setBackground(new java.awt.Color(255, 255, 255));
        isbnPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ENTER BOOK ID");
        isbnPANEL.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 170, 50));

        enterISBN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        enterISBN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enterISBNKeyPressed(evt);
            }
        });
        isbnPANEL.add(enterISBN, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 330, 40));

        isbnBTN.setBackground(new java.awt.Color(255, 255, 255));
        isbnBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/searchIcon1.png"))); // NOI18N
        isbnBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnBTNActionPerformed(evt);
            }
        });
        isbnPANEL.add(isbnBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 50, 40));

        ISBNTitle1.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        ISBNTitle1.setText("Publisher:");
        isbnPANEL.add(ISBNTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 280, 150, 30));

        bookTitle2.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        bookTitle2.setText("Book Title:");
        isbnPANEL.add(bookTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 140, 30));

        authorTitle1.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        authorTitle1.setText("Author Name:");
        isbnPANEL.add(authorTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 140, 30));

        authorField1.setEditable(false);
        authorField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        isbnPANEL.add(authorField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 320, 30));

        bookTitleField1.setEditable(false);
        bookTitleField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        isbnPANEL.add(bookTitleField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 320, 30));

        pubTitle1.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        pubTitle1.setText("Published Year:");
        isbnPANEL.add(pubTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 140, 30));

        genreTitle1.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        genreTitle1.setText("Genre:");
        isbnPANEL.add(genreTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 140, 30));

        availTitle1.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        availTitle1.setText("Available Copy:");
        isbnPANEL.add(availTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 160, 30));

        publishedField1.setEditable(false);
        publishedField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        isbnPANEL.add(publishedField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, 320, 30));

        genreField1.setEditable(false);
        genreField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        isbnPANEL.add(genreField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 320, 30));

        bookCopiesField1.setEditable(false);
        bookCopiesField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        isbnPANEL.add(bookCopiesField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 360, 320, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        isbnPANEL.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 50));

        publisher.setEditable(false);
        publisher.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        isbnPANEL.add(publisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 280, 320, 30));

        ThreePANELS.add(isbnPANEL, "isbnPANEL");

        borrowPANEL.setBackground(new java.awt.Color(255, 255, 255));
        borrowPANEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userROLE.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        userROLE.setForeground(new java.awt.Color(206, 18, 18));
        borrowPANEL.add(userROLE, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 100, 40));

        isbnBORROW.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        isbnBORROW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                isbnBORROWKeyPressed(evt);
            }
        });
        borrowPANEL.add(isbnBORROW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 310, 40));

        inputID.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID.setText("Book ID:");
        borrowPANEL.add(inputID, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 100, -1));

        titleINFO1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        titleINFO1.setForeground(new java.awt.Color(255, 255, 255));
        titleINFO1.setText("Book's Information");
        borrowPANEL.add(titleINFO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 200, 50));

        inputID2.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID2.setText("Book Title:");
        borrowPANEL.add(inputID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 120, 30));

        titleBORROW.setEditable(false);
        titleBORROW.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        borrowPANEL.add(titleBORROW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 310, 30));

        inputID3.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID3.setText("Author:");
        borrowPANEL.add(inputID3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 100, 30));

        authorBORROW.setEditable(false);
        authorBORROW.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        borrowPANEL.add(authorBORROW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 310, 30));

        inputID4.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID4.setText("Publisher:");
        borrowPANEL.add(inputID4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 110, 30));

        yearBORROW.setEditable(false);
        yearBORROW.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        borrowPANEL.add(yearBORROW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 310, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/solid red.png"))); // NOI18N
        borrowPANEL.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 50));

        inputID5.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID5.setText("Genre:");
        borrowPANEL.add(inputID5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 120, 30));

        publisherBorrow.setEditable(false);
        publisherBorrow.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        borrowPANEL.add(publisherBorrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 310, 30));

        genreBORROW.setEditable(false);
        genreBORROW.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        borrowPANEL.add(genreBORROW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 310, 30));

        inputID6.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID6.setText("Available Copy:");
        borrowPANEL.add(inputID6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 130, 30));

        bookCopiesBORROW.setEditable(false);
        bookCopiesBORROW.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        borrowPANEL.add(bookCopiesBORROW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 310, 30));

        isbnBORROWBTN.setBackground(new java.awt.Color(255, 255, 255));
        isbnBORROWBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/searchIcon1.png"))); // NOI18N
        isbnBORROWBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnBORROWBTNActionPerformed(evt);
            }
        });
        borrowPANEL.add(isbnBORROWBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 60, 40));

        inputID7.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        inputID7.setText("Published Year:");
        borrowPANEL.add(inputID7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 130, 30));

        borrowBTN.setBackground(new java.awt.Color(255, 255, 255));
        borrowBTN.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        borrowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/4br.png"))); // NOI18N
        borrowBTN.setText("Borrow");
        borrowBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowBTNActionPerformed(evt);
            }
        });
        borrowPANEL.add(borrowBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, 160, 40));

        cancelBTN.setBackground(new java.awt.Color(255, 255, 255));
        cancelBTN.setFont(new java.awt.Font("Dubai", 0, 20)); // NOI18N
        cancelBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/es.png"))); // NOI18N
        cancelBTN.setText("Clear");
        cancelBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBTNActionPerformed(evt);
            }
        });
        borrowPANEL.add(cancelBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 440, 160, 40));

        BACKGROUND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/white.jpg"))); // NOI18N
        borrowPANEL.add(BACKGROUND, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1270, 520));

        invisibleBORROW.setBackground(new java.awt.Color(153, 255, 0));
        invisibleBORROW.setForeground(new java.awt.Color(255, 51, 51));
        invisibleBORROW.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        borrowPANEL.add(invisibleBORROW, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 320, 90, 20));

        invisibleRETURN1.setEditable(false);
        invisibleRETURN1.setBackground(new java.awt.Color(255, 255, 0));
        invisibleRETURN1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        borrowPANEL.add(invisibleRETURN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 350, 90, -1));

        todaysDate.setEditable(false);
        todaysDate.setBackground(new java.awt.Color(255, 51, 0));
        todaysDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        borrowPANEL.add(todaysDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 90, -1));

        invisibleDIFFFaculty.setEditable(false);
        invisibleDIFFFaculty.setBackground(new java.awt.Color(51, 51, 255));
        invisibleDIFFFaculty.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        borrowPANEL.add(invisibleDIFFFaculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 90, -1));

        ThreePANELS.add(borrowPANEL, "borrowPANEL");

        jPanel2.add(ThreePANELS, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 1290, 540));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/BGJLABELUE.png"))); // NOI18N
        jPanel2.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, -1));

        INVIid.setEditable(false);
        jPanel2.add(INVIid, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 80, -1));
        jPanel2.add(checkDATEBORROWED, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 670, 60, -1));
        jPanel2.add(IFFEE, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 90, -1));

        HomeBTN.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        HomeBTN.setForeground(new java.awt.Color(204, 0, 0));
        HomeBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeBTNMouseClicked(evt);
            }
        });
        jPanel2.add(HomeBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 80, 70));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 700));

        INVIname.setEditable(false);
        jPanel2.add(INVIname, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 80, -1));

        INVIbday.setEditable(false);
        jPanel2.add(INVIbday, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 80, -1));

        INVIcourse.setEditable(false);
        jPanel2.add(INVIcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 90, -1));
        jPanel2.add(INVIfirstdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 650, 90, -1));
        jPanel2.add(INVIseconddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 620, 80, -1));
        jPanel2.add(INVItype, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 190, -1));
        jPanel2.add(INVI150days, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 150, -1));

        ISBNField1.setEditable(false);
        ISBNField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(ISBNField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 230, 30));
        jPanel2.add(INVIdateTodayIs, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 900));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
    User_Module UserFrame = new User_Module();
    User_Module.UserIDTxtField.setText(Search_Borrow.INVIid.getText());
    User_Module.NameTxtField.setText(Search_Borrow.INVIname.getText());
    User_Module.BirthdateTxtField.setText(Search_Borrow.INVIbday.getText());
    User_Module.CourseTxtField.setText(Search_Borrow.INVIcourse.getText());
    User_Module.memberTypeTxtField.setText(Search_Borrow.INVItype.getText());
    User_Module.dateNgayonFieldUSER.setText(Search_Borrow.INVIdateTodayIs.getText());
    UserFrame.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_homeMouseClicked

    private void browseGenreBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseGenreBTNActionPerformed
    cardLayout.show(ThreePANELS, "browsePANEL");
    }//GEN-LAST:event_browseGenreBTNActionPerformed

    private void borrowBookBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowBookBTNActionPerformed
    cardLayout.show(ThreePANELS, "borrowPANEL");
    }//GEN-LAST:event_borrowBookBTNActionPerformed

    private void searchISBNBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchISBNBTNActionPerformed
    cardLayout.show(ThreePANELS, "isbnPANEL");
    }//GEN-LAST:event_searchISBNBTNActionPerformed

    private void selectGENREItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectGENREItemStateChanged
    String genre = selectGENRE.getSelectedItem().toString();
    filterGenre(genre);
    }//GEN-LAST:event_selectGENREItemStateChanged

    private void enterISBNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enterISBNKeyPressed
    String inputISBN = enterISBN.getText();
    int length = inputISBN.length();
    char c = evt.getKeyChar();
    if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
        if(length<8){
            enterISBN.setEditable(true);
        }else{
            enterISBN.setEditable(false);
        }
    }else{
        if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
            enterISBN.setEditable(true);
        }else{
            enterISBN.setEditable(false);
        }
    }
    }//GEN-LAST:event_enterISBNKeyPressed

    private void isbnBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnBTNActionPerformed
    if(enterISBN.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "Please enter the Book ID first!", "Blank Field", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try{
        //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
        String q;
        q = "Select * from booksavailable where bookid=?";
        pst = con.prepareStatement(q);
        pst.setString(1, enterISBN.getText());
        rs = pst.executeQuery();

        if (rs.next()){
            ISBNField1.setText(rs.getString(1));
            bookTitleField1.setText(rs.getString(2)); 
            authorField1.setText(rs.getString(3));
            publishedField1.setText(rs.getString(4));
            genreField1.setText(rs.getString(5));
            bookCopiesField1.setText(rs.getString(6));
            publisher.setText(rs.getString(7));
        }else{
            JOptionPane.showMessageDialog(null,"Book not found. Please try again.");
            ISBNField1.setText(null);
            bookTitleField1.setText(null);
            authorField1.setText(null);
            publishedField1.setText(null);
            genreField1.setText(null);
            bookCopiesField1.setText(null);
            enterISBN.setText(null);
            publisher.setText(null);
        }
    }
    catch (HeadlessException | SQLException ex){
    }
    }//GEN-LAST:event_isbnBTNActionPerformed

    private void isbnBORROWKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isbnBORROWKeyPressed
    String codeBORROW = isbnBORROW.getText();
    int length = codeBORROW.length();
    char c = evt.getKeyChar();
    if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
        if(length<8){
            isbnBORROW.setEditable(true);
        }else{
            isbnBORROW.setEditable(false);
        }
    }else{
        if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
            isbnBORROW.setEditable(true);
        }else{
            isbnBORROW.setEditable(false);
        }
    }
    }//GEN-LAST:event_isbnBORROWKeyPressed

    private void isbnBORROWBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnBORROWBTNActionPerformed
    if(isbnBORROW.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "Please enter the Book ID first!", "Blank Field", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try{ 
        //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
        String findISBN = "SELECT * FROM booksavailable WHERE bookid=?";
        pst = con.prepareStatement(findISBN);
        pst.setString(1, isbnBORROW.getText());
        rs = pst.executeQuery();
        if (rs.next()){
            titleBORROW.setEnabled(true);
            authorBORROW.setEnabled(true);
            yearBORROW.setEnabled(true);
            genreBORROW.setEnabled(true);
            bookCopiesBORROW.setEnabled(true);
            publisherBorrow.setEnabled(true);
            isbnBORROW.setText(rs.getString(1));
            titleBORROW.setText(rs.getString(2)); 
            authorBORROW.setText(rs.getString(3));
            yearBORROW.setText(rs.getString(4));
            genreBORROW.setText(rs.getString(5));
            bookCopiesBORROW.setText(rs.getString(6));
            publisherBorrow.setText(rs.getString(7));
        }else{
            JOptionPane.showMessageDialog(this, "Book not found. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            isbnBORROW.setText(null);
            titleBORROW.setText(null);
            authorBORROW.setText(null);
            yearBORROW.setText(null);
            genreBORROW.setText(null);
            bookCopiesBORROW.setText(null);
            publisherBorrow.setText(null);
            return;
        }
    }catch (HeadlessException | SQLException ex){
    }
    String strCOPIES = bookCopiesBORROW.getText();
    int intCOPIES = Integer.parseInt(strCOPIES);
    if (intCOPIES != 1){
        borrowBTN.setEnabled(true);
        cancelBTN.setEnabled(true);
    }else{
        JOptionPane.showMessageDialog(this, "Sorry this book can only be borrowed inside the Library.", "Book For Reading Purposes Only.", JOptionPane.INFORMATION_MESSAGE);
        isbnBORROW.setText(null);
        titleBORROW.setText(null);
        authorBORROW.setText(null);
        yearBORROW.setText(null);
        genreBORROW.setText(null);
        publisherBorrow.setText(null);
        bookCopiesBORROW.setText(null);
        titleBORROW.setEnabled(false);
        authorBORROW.setEnabled(false);
        yearBORROW.setEnabled(false);
        genreBORROW.setEnabled(false);
        bookCopiesBORROW.setEnabled(false);
        publisherBorrow.setEnabled(false);
    }
    }//GEN-LAST:event_isbnBORROWBTNActionPerformed

    private void borrowBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowBTNActionPerformed
    ifHasOverdueBook ();
    if (IFFEE.getText().equals("50")){
        JOptionPane.showMessageDialog(this, "You have a Pending Balance! "
                + "\nPlease pay the exact amount of 50 pesos"
                + "\nfirst and return the book so you can borrow again. "
                + "\n \nThank you.", "Pending Transaction", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (INVItype.getText().equals("Student")){
        try { //LIMIT THE NUMBER OF TIMES THE USER IS TRYING TO BORROW A BOOK BY 3
       // con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
        String limit = "SELECT COUNT (*) AS countOccurences FROM bookrequests WHERE borrowerid=?";
        pst = con.prepareStatement(limit);
        pst.setString(1, INVIid.getText());
        rs= pst.executeQuery();
        while (rs.next()){
            int count = rs.getInt("countOccurences");
            if (count == 3){
            JOptionPane.showMessageDialog(this, "You've reached the maximum amount of allowable books to borrow!\nPlease return some of the books that you've borrowed and try again.", "Reached Maximum Allowable Books", JOptionPane.WARNING_MESSAGE);
            return;
                        }
            }
        }catch (Exception e) {
        e.printStackTrace();
}
    }
   
    try{ //==CHECK IF THE USER ALREADY BORROWED THE SAME BOOK==
        //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
        String sameBook = "SELECT * FROM bookrequests WHERE borrowerid=? AND bookid=?";
        pst = con.prepareStatement(sameBook);
        //convert to string first
        String idNumber = INVIid.getText();
        String bookNumber = isbnBORROW.getText();
        pst.setString(1, idNumber);
        pst.setString(2, bookNumber);
        rs = pst.executeQuery();
        if (rs.next()){
            JOptionPane.showMessageDialog(this,"You've already borrowed the same book!", "Same Book Not Allowed", JOptionPane.WARNING_MESSAGE);
            isbnBORROW.setText(null);
            borrowBTN.setEnabled(false);
            cancelBTN.setEnabled(false);
            titleBORROW.setEnabled(false);
            titleBORROW.setText(null);
            authorBORROW.setEnabled(false);
            authorBORROW.setText(null);
            yearBORROW.setEnabled(false);
            yearBORROW.setText(null);
            genreBORROW.setEnabled(false);
            genreBORROW.setText(null);
            bookCopiesBORROW.setEnabled(false);
            bookCopiesBORROW.setText(null);
            publisherBorrow.setText(null);
            publisherBorrow.setEnabled(false);
            return;
        }
    }catch (Exception e){
        e.printStackTrace();
        }
        if (INVItype.getText().equals("Student")){
           JOptionPane.showMessageDialog(this, "Please be advise that if your due date is overdue, \nYou will be charge of 50 pesos fee.", "Important Note", JOptionPane.INFORMATION_MESSAGE);
        int userConfirm = JOptionPane.showConfirmDialog(null, "=================================" + "\nBook ID: " + isbnBORROW.getText()
            + "\nBook Title: " + titleBORROW.getText()
            + "\nAuthor Name: " + authorBORROW.getText()
            + "\nBook Genre: " + genreBORROW.getText()
            + "\nDate Borrowed: " + INVIfirstdate.getText()
            + "\nDate Return: " + INVIseconddate.getText()
            + "\n================================="
            + "\nAre you sure you want to borrow this book?",
            "Book Receipt" ,JOptionPane.YES_NO_OPTION);
        if (userConfirm == 0){
            try{
                //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
                String q = "insert into bookrequests (status, borrowerid, borrowername, membertype, bookname, bookid, dateborrowed, datereturn)values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(q);

                String status = "PENDING";
                pst.setString(1, status);
                pst.setString(2, INVIid.getText());
                pst.setString(3, INVIname.getText());
                pst.setString(4, INVItype.getText());
                pst.setString(5, titleBORROW.getText());
                pst.setString(6, isbnBORROW.getText());
                pst.setString(7, INVIfirstdate.getText());
                pst.setString(8, INVIseconddate.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "We got your Book Receipt.\nWe will notify you if you were given\nthe permission to borrow this book. \n \nThank You.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception e){
                e.printStackTrace();
            }
            } 
        }
        if (INVItype.getText().equals("Faculty")){
           JOptionPane.showMessageDialog(this, "Please be advise that if your due date is overdue, \nYou will be charge of 50 pesos fee.", "Important Note", JOptionPane.INFORMATION_MESSAGE);
        int userConfirm = JOptionPane.showConfirmDialog(null, "=================================" + "\nBook ID: " + isbnBORROW.getText()
            + "\nBook Title: " + titleBORROW.getText()
            + "\nAuthor Name: " + authorBORROW.getText()
            + "\nBook Genre: " + genreBORROW.getText()
            + "\nDate Borrowed: " + INVIfirstdate.getText()
            + "\nDate Return: " + INVI150days.getText()
            + "\n================================="
            + "\nAre you sure you want to borrow this book?",
            "Book Receipt" ,JOptionPane.YES_NO_OPTION);
        if (userConfirm == 0){
            try{
                //con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
                String q = "insert into bookrequests (status, borrowerid, borrowername, membertype, bookname, bookid, dateborrowed, datereturn)values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(q);

                String status = "PENDING";
                pst.setString(1, status);
                pst.setString(2, INVIid.getText());
                pst.setString(3, INVIname.getText());
                pst.setString(4, INVItype.getText());
                pst.setString(5, titleBORROW.getText());
                pst.setString(6, isbnBORROW.getText());
                pst.setString(7, INVIfirstdate.getText());
                pst.setString(8, INVI150days.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "We got your Book Receipt.\nWe will notify you if you were given\nthe permission to borrow this book. \n \nThank You.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception e){
                e.printStackTrace();
            }
            } 
        }
        
    }//GEN-LAST:event_borrowBTNActionPerformed

    private void cancelBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBTNActionPerformed
    titleBORROW.setText(null);
    authorBORROW.setText(null);
    yearBORROW.setText(null);
    genreBORROW.setText(null);
    bookCopiesBORROW.setText(null);
    isbnBORROW.setText(null);
    publisherBorrow.setText(null);
    borrowBTN.setEnabled(false);
    titleBORROW.setEnabled(false);
    authorBORROW.setEnabled(false);
    yearBORROW.setEnabled(false);
    genreBORROW.setEnabled(false);
    bookCopiesBORROW.setEnabled(false);
    publisherBorrow.setEnabled(false);
    IFFEE.setText(null);
    checkDATEBORROWED.setText(null);
    }//GEN-LAST:event_cancelBTNActionPerformed

    private void HomeBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeBTNMouseClicked
    new User_Module().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_HomeBTNMouseClicked

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
            java.util.logging.Logger.getLogger(Search_Borrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search_Borrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search_Borrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search_Borrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Search_Borrow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BACKGROUND;
    private javax.swing.JLabel HomeBTN;
    private javax.swing.JTextField IFFEE;
    private javax.swing.JTextField INVI150days;
    public static javax.swing.JTextField INVIbday;
    public static javax.swing.JTextField INVIcourse;
    public static javax.swing.JTextField INVIdateTodayIs;
    private javax.swing.JTextField INVIfirstdate;
    public static javax.swing.JTextField INVIid;
    public static javax.swing.JTextField INVIname;
    private javax.swing.JTextField INVIseconddate;
    public static javax.swing.JTextField INVItype;
    private javax.swing.JTextField ISBNField1;
    private javax.swing.JLabel ISBNTitle1;
    private javax.swing.JPanel ThreePANELS;
    private javax.swing.JTextField authorBORROW;
    private javax.swing.JTextField authorField1;
    private javax.swing.JLabel authorTitle1;
    private javax.swing.JLabel availTitle1;
    private javax.swing.JLabel bg;
    private javax.swing.JTextField bookCopiesBORROW;
    private javax.swing.JTextField bookCopiesField1;
    private javax.swing.JLabel bookTitle2;
    private javax.swing.JTextField bookTitleField1;
    private javax.swing.JButton borrowBTN;
    private javax.swing.JButton borrowBookBTN;
    private javax.swing.JPanel borrowPANEL;
    private javax.swing.JButton browseGenreBTN;
    private javax.swing.JPanel browsePANEL;
    private javax.swing.JButton cancelBTN;
    private javax.swing.JTextField checkDATEBORROWED;
    private javax.swing.JTextField enterISBN;
    private javax.swing.JTextField genreBORROW;
    private javax.swing.JTextField genreField1;
    private javax.swing.JTable genreTABLE;
    private javax.swing.JLabel genreTitle1;
    private javax.swing.JLabel home;
    private javax.swing.JLabel inputID;
    private javax.swing.JLabel inputID2;
    private javax.swing.JLabel inputID3;
    private javax.swing.JLabel inputID4;
    private javax.swing.JLabel inputID5;
    private javax.swing.JLabel inputID6;
    private javax.swing.JLabel inputID7;
    private javax.swing.JLabel invisibleBORROW;
    private javax.swing.JTextField invisibleDIFFFaculty;
    private javax.swing.JTextField invisibleRETURN1;
    private javax.swing.JTextField isbnBORROW;
    private javax.swing.JButton isbnBORROWBTN;
    private javax.swing.JButton isbnBTN;
    private javax.swing.JPanel isbnPANEL;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel pubTitle1;
    private javax.swing.JTextField publishedField1;
    private javax.swing.JTextField publisher;
    private javax.swing.JTextField publisherBorrow;
    private javax.swing.JButton searchISBNBTN;
    private javax.swing.JComboBox<String> selectGENRE;
    private javax.swing.JTextField titleBORROW;
    private javax.swing.JLabel titleINFO1;
    private javax.swing.JTextField todaysDate;
    private javax.swing.JLabel userROLE;
    private javax.swing.JTextField yearBORROW;
    // End of variables declaration//GEN-END:variables
}
