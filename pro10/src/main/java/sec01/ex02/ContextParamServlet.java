package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/param"},loadOnStartup=1)
public class ContextParamServlet extends HttpServlet {
	
	private ServletContext sc;
	
	public void init(ServletConfig config) throws ServletException{
		this.sc = config.getServletContext();
		System.out.println("contextparam");
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		String menu_member = sc.getInitParameter("menu_member");
		out.print("<html><body><table><tr><td>"+menu_member+"<td/><tr/><table/><body/><html/>");
	}
	

}
