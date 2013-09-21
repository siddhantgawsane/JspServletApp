package gaeProject;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersTable extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		List<UserDB> results = null;
		Query q = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			q = pm.newQuery(UserDB.class); //,"username == '*'"
			results = (List<UserDB>) q.execute();
			req.setAttribute("UsersList", results); 
		}catch(Exception e){
			e.printStackTrace(resp.getWriter()); 
		}finally {
			pm.close();
		}
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/ViewTable.jsp");
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(resp.getWriter());
		}
	}	
}
