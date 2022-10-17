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

@WebServlet("/cset")
public class SetServletContext extends HttpServlet {
	

	private ServletContext sc;
	
	public void init(ServletConfig config) throws ServletException{
		this.sc = config.getServletContext();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter wrt = res.getWriter();
		ServletContext context = this.sc;
		List member = new ArrayList();
		member.add("isungi");
		member.add(30);
		context.setAttribute("member", member);
		wrt.print("<html><body>set parameters<body/><html/>");
	}
}
