package gaeProject;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MyPageServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user != null) {
        	resp.setContentType("text/html");
        	resp.getWriter().println("Hello " + user.getNickname());
        }
        else {
            resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
        resp.getWriter().println("<a href="+userService.createLogoutURL(req.getRequestURI())+">logout</a>");
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("user");
		String pass = request.getParameter("password");
		HttpSession session = request.getSession(true);
		response.setContentType("text/html");
		List<UserDB> results = null;
		//response.getWriter().println(uname+pass);
		Query q = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			q = pm.newQuery(UserDB.class,"username == '"+uname+"' & password == '" +pass+"'");
			results = (List<UserDB>) q.execute();

			if (!results.isEmpty()) {
				response.getWriter().println("Login Successful");
				UserDB u = pm.getObjectById(UserDB.class,results.get(0).getId());

				u.incrementLogins();
				u.setDate(new Date());
				session.setAttribute("fname", u.getFirstName());
				session.setAttribute("lname", u.getLastName());
				session.setAttribute("emailid", u.getEmailId());
				session.setAttribute("logins", u.getLogins());
				session.setAttribute("username",uname);
				session.setAttribute("blob-key", u.getBlobKeyString());
								
				response.sendRedirect("/HomePage.jsp");
			} 
			else {
				System.out.println("FAILED");
				response.getWriter().println("Login Failed");
				response.sendRedirect("/Login.jsp");  
			}
		} 
		catch(Exception e){
			e.printStackTrace();
		}	
		finally {
			pm.close();
		}
	}
}
