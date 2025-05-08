package in.bloodsync.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class DonorEmail extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String senderEmail = "harshpatle99999@gmail.com";         
         String senderPassword = "zutn akuk esyf soyw";        
         String receiverEmail = "hpatle@820@gmail.com";  

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail)
            );
            message.setSubject("Hello from Java Servlet!");
            message.setText("Yeh ek test email hai servlet project se.");

            Transport.send(message);

            out.println("<h3 style='color: green'>✅ Email successfully sent!</h3>");
        } catch (MessagingException e) {
            e.printStackTrace();
            out.println("<h3 style='color: red'>❌ Email sending failed: " + e.getMessage() + "</h3>");
        }
    }
}
