package librarysystem;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class OverdueMail {
    public static void SendMail(String recepient) throws Exception {
        JOptionPane.showMessageDialog(null, "Preparing to send your message...", "Loading Message", JOptionPane.PLAIN_MESSAGE);
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "xxxxxxxxxx@gmail.com";
        String password = "xxxxxxxxxx";
        
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
                    
            }
        });
        Message message = prepareMessage (session, myAccountEmail, recepient);
        Transport.send(message);
        JOptionPane.showMessageDialog(null, "Notified User Sucesfully!");
    }
     private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Important Notice from Library: OVERDUE BOOK");
            message.setText("Good Day. \n \nWe are sending you an email to inform you that the due date for the book that you've borrowed is overdue. \n \nYou have now a pending balance of 50 pesos. \n \nIn order to avoid from being blocked if ever you needed again to borrow a book in the future, please pay the exact amount at the library counter and return the book. \n \nThank you. "
                    + " \n \n \n____________________________________________________ \nIf you have any questions, concerns, or recommendations, dont hesitate to contact us through: \nEmail: group1.librarysystem@gmail.com \nor Call Us: 8123 4567");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(OverdueMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

