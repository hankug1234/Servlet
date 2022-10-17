package pro11;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionTest extends HttpServlet{
	
	public void init() throws ServletException{
		System.out.println("session servlet start");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		long createTime = session.getCreationTime();
		long lastAccess = session.getLastAccessedTime();
		String id = session.getId();
		out.println("id : "+id);
		out.println("createTime : "+createTime);
		out.println("lastAccess :"+lastAccess);
		if(session.isNew()) {
			out.println("is new session");
		}
	}

}
