package pro11;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet{
	
	public void init() {
		System.out.println("init method call");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
	{
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		
	}
	

}
