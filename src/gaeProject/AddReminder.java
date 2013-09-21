package gaeProject;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class AddReminder extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Queue queue = QueueFactory.getQueue("optimize-queue");
		
		
		List<UserDB> results = null;
		//response.getWriter().println(uname+pass);
		Query q = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Date currentDate=new Date();
		
/*		try {
			currentDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20130905" );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
*/		try {
			q = pm.newQuery(UserDB.class,"loginDate < '"+currentDate+"'");
			results = (List<UserDB>) q.execute();

			if (!results.isEmpty()) {
				resp.getWriter().println("Found");
				for(UserDB u : results)
				{	//+"&loginDate="+u.getLoginDate().toString()
					TaskOptions taskOptions = TaskOptions.Builder.withUrl("/reminder?emailId="+u.getEmailId()).method(Method.GET);
					queue.add(taskOptions);
					//sendReminder(u.getEmailId(),u.getLoginDate());
					resp.getWriter().println("\nSuccessfully created a Task in the Queue");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace(resp.getWriter());
		}	
		finally {

			//q.closeAll();
			pm.close();
		}
	}
}
