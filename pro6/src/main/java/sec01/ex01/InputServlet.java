package sec01.ex01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/input")
public class InputServlet extends HttpServlet{
	
	public void init() throws ServletException{
		System.out.println("input init method call");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException
	{
		req.setCharacterEncoding("utf-8");;
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		System.out.println(user_id +" : "+user_pw);
		String[] values = req.getParameterValues("subject");
		for(String value : values) {
			System.out.println("checked subject: "+value);
		}
		
	}
	
	public void destroy() {
		System.out.println("input destroy method call");
	}

}
