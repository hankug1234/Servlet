package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cget")
public class GetServletContext extends HttpServlet {
	
	private ServletContext sc;
	
	public void init(ServletConfig config) throws ServletException{
		this.sc = config.getServletContext();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		List member = (ArrayList)sc.getAttribute("member");
		String name = (String)member.get(0);
		int age = (int)member.get(1);
		out.print("<html><body>"+name+" : "+age+"<body/><html/>");
		
	}
	
}
