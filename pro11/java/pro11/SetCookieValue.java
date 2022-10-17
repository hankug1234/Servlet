package pro11;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/set")
public class SetCookieValue extends HttpServlet {
	
	public void init() throws ServletException{
		System.out.println("getcookie start");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html;charset=utf-8");
	    Cookie[] cookies = req.getCookies();
	    PrintWriter out = res.getWriter();
	    for(Cookie cookie : cookies) {
	    	if(cookie.getName().equals("cookieTest")) {
	    		out.print("cookieTest value :"+URLDecoder.decode(cookie.getValue(),"utf-8"));
	    	}
	    }
	    
		
	}

}
