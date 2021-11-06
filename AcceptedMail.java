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
import static librarysystem.loadingScreen.closeBTN;

public class AcceptedMail {
    
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
            message.setSubject("Important Notice from Library: ACCEPTED BOOK REQUEST");
            message.setText("Good Day. \n \nThe book that you've requested was given the permission to borrow. \n \nYou may now go to the Library and claim your book between 9 am to 4 pm, Monday to Friday only. "
                    + "\n \nThank you. \n \n \n____________________________________________________ \nIf you have any questions, concerns, or recommendations, dont hesitate to contact us through: \nEmail: group1.librarysystem@gmail.com \nor Call Us: 8123 4567");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AcceptedMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
