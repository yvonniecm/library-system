package librarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connectLibrary {
    public static Connection connecting (){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryManagementSystem", "group1", "librarysystem");
            return con;
        } catch (SQLException ex){
            Logger.getLogger(connectLibrary.class.getName()).log(Level.SEVERE,null,ex);
        }return null;
    }
    
}
