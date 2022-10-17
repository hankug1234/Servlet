package filter_test;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginTest extends HttpServlet{
	
	public void init() throws ServletException{
		System.out.println("login test start");
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html;charset=utf-8");
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("ID : "+user_id);
		out.print("PW : "+user_pw);
		out.print("</body></html>");
		
		
		
	}

}
