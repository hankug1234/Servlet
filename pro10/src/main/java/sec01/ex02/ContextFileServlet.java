package sec01.ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet{
	
	public ServletContext sc;
	public void init(ServletConfig config)throws ServletException
	{
		this.sc = config.getServletContext();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		InputStream input = sc.getResourceAsStream("/WEB-INF/bin/hello_word.txt");
		BufferedReader bsr = new BufferedReader(new InputStreamReader(input));
		String content = bsr.readLine();
		out.print("<html><body><h1>"+content+"</h1></body></html>");
		
	}
	

}
