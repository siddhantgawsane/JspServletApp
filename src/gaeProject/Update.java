package gaeProject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class Update extends HttpServlet {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String uname = request.getParameter("user");
	    String pass = request.getParameter("password");
	    String fname = request.getParameter("fname");
	    String lname = request.getParameter("lname");
	    String email = request.getParameter("email");
	    
	    Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        BlobKey blobKey = blobs.get("myPic");
        String bks = blobKey.getKeyString();
        PersistenceManager pm = PMF.get().getPersistenceManager();
	    
	    try {
	    	Query q =pm.newQuery(UserDB.class,"username == '"+uname+"'");
	    	List<UserDB> results = (List<UserDB>) q.execute();
	        UserDB u = pm.getObjectById(UserDB.class,results.get(0).getId()); 
	        u.setPassword(pass);
	        u.setFirstName(fname);
	        u.setLastName(lname);
	        u.setEmailId(email);
	        if(bks!=null)
	        {
	        	u.setBlobKey(blobKey.getKeyString());
	        }
	        else
	        {
	        	u.setBlobKey("zYpHSk3LfbXwlI5Y23k_Vw");
	        }
	        response.getWriter().println("Updated successfully");
			response.sendRedirect("/HomePage.jsp");
	    } 
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally {
	        pm.close();
	    }   
	}
}
