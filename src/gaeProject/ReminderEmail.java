package gaeProject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReminderEmail extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String emailId = req.getParameter("emailId");
//		String loginDate = req.getParameter("loginDate");
		Properties props = new Properties();
		Session session1 = Session.getDefaultInstance(props, null);

		String msgBody = "Dear User, You have not logged in to thedarklord1501@gmail.com since 7 Days!! Please login and enjoy a variety of services";
		try {
			Message msg = new MimeMessage(session1);
			msg.setFrom(new InternetAddress("siddhant.g@a-cti.com","thedarklord1501.appspot.com Admin"));
			msg.addRecipient(Message.RecipientType.CC,new InternetAddress(emailId,"Mr. User"));
			msg.setSubject("Check out this cool new web-app!");
			msg.setText(msgBody);
			Transport.send(msg);
			
			//e.getWriter().println("Email sent successfully");

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
