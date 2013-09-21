package gaeProject;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.*;


@SuppressWarnings("serial")
public class Register extends HttpServlet {

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String uname = request.getParameter("user");
	    String pass = request.getParameter("password");
	    String fname = request.getParameter("fname");
	    String lname = request.getParameter("lname");
	    String email = request.getParameter("email");
	    response.setContentType("text/html");
	    	    
	    Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        BlobKey blobKey = blobs.get("myPic");
        String bks = blobKey.getKeyString();        
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    if(bks!=null)
        {
	    	UserDB u = new UserDB(uname,pass,fname,lname,email,bks);
	    	pm.makePersistent(u);
        }
        else
        {
        	UserDB u = new UserDB(uname,pass,fname,lname,email,"zYpHSk3LfbXwlI5Y23k_Vw");
        	pm.makePersistent(u);
        }
    	response.getWriter().println("Profile Created Successfully.<\br> Please Login to continue");
    	response.sendRedirect("/Login.jsp");
	}
}
