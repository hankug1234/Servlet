package sec01.ex01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServelt extends HttpServlet{
	
	public void init() throws ServletException{
		System.out.println("init method call");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse response) 
	throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		System.out.println(user_id +" : "+user_pw);
	}
	
	public void destroy() {
		System.out.println("destropy method call");
	}

}
