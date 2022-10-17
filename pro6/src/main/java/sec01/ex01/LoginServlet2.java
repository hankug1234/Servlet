package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	
	public void init() throws ServletException{
		System.out.println("login2 init method call");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter wrt = res.getWriter();
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		
		String data = "<html>";
		data+="<body>";
		data+="id : "+user_id+"<br>";
		data+="password : "+user_pw+"<br>";
		data+="<body/>";
		data+="<html/>";
		wrt.print(data);
	}
	
	public void destroy() {
		System.out.println("login2 destroy method call");
	}

}
