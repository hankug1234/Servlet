package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/second")
public class SecondServlet extends HttpServlet{
	
	public void init() throws ServletException{
		System.out.println("first init call");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pwt = res.getWriter();
		pwt.print("<html><body>");
		pwt.print("dispatch prectice "+name);
		pwt.print("<body/><html>");
	}
	
	
	public void destory() {
		System.out.println("first distory call");
	}

}
