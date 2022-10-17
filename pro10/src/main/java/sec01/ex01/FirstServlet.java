package sec01.ex01;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	
	public void init() throws ServletException{
		System.out.println("first init call");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatch = req.getRequestDispatcher("second?name=lee");
		dispatch.forward(req, res);
	}
	
	
	public void destory() {
		System.out.println("first distory call");
	}
	

}
