package gaeProject;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class EmailSender extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Properties props = new Properties();
		Session session1 = Session.getDefaultInstance(props, null);

		String msgBody = request.getParameter("message");
		String mailId = request.getParameter("mailId");
		
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
		
		try {
			Message msg = new MimeMessage(session1);
			msg.setFrom(new InternetAddress(user.getEmail(),
					user.getNickname()));
			
			msg.addRecipient(Message.RecipientType.CC,
					new InternetAddress(mailId,
							"Mr. User"));
			msg.setSubject("Check out this cool new web-app!");
			//msg.setText(msgBody);
			
			String path = "Screenshots/index.jpg";
			
			File img = new File(path);
			byte[] attachmentData = new byte[(int)img.length()];
			DataInputStream data = new DataInputStream(new FileInputStream(path));
			data.readFully(attachmentData);
			
	        Multipart mp = new MimeMultipart();

	        MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(msgBody, "text/html");
	        mp.addBodyPart(htmlPart);

	        
	        MimeBodyPart attachment = new MimeBodyPart();
	        attachment.setFileName("index.jpg");
//	        attachment.setContent(attachmentData, "image/jpeg");
	        DataSource src = new ByteArrayDataSource(attachmentData, "image/jpeg");
	        attachment.setDataHandler(new DataHandler(src)); 

	        mp.addBodyPart(attachment);
	        msg.setContent(mp);
			
	        Transport.send(msg);
			
			response.getWriter().println("Email sent successfully");

		} catch (AddressException e) {
			e.printStackTrace(response.getWriter());
		} catch (MessagingException e) {
			e.printStackTrace(response.getWriter());
		}
	}
}
