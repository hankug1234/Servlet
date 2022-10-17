package pro11;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/get")
public class GetCookieValue extends HttpServlet {

	public void init() throws ServletException{
		System.out.println("getcooki start");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html;charset=utf-8");
		Cookie c  = new Cookie("cookieTest",URLEncoder.encode("jsp programing","utf-8"));
		c.setMaxAge(-1);
		res.addCookie(c);
		PrintWriter out = res.getWriter();
		Date d = new Date();
		out.println("cookie test : "+d);
		
	}
}
