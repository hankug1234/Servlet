package pro11;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login4")
public class SessionTest4 extends HttpServlet {
	public void init()throws ServletException{
		System.out.println("login4 start");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		doHandle(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		doHandle(req,res);
	}
	
	public void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		HttpSession session = req.getSession();
		if(session.isNew()) {
			if(user_id != null) {
				session.setAttribute("user_id", user_id);
				out.print("<a href='login.html'> login state confirm</a>");
			}
			
		}else {
			if((user_id != null) && (user_id.length() != 0)) {
				out.println("hello "+user_id);
			}else {
				out.print("<a href='login.html'>login again</a>");
				session.invalidate();
			}
		}
	}

}
