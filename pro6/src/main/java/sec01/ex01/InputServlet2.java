package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/input2")
public class InputServlet2 extends HttpServlet{
	
	public void init() throws ServletException
	{
		System.out.println("input2 init method call");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		Enumeration enu = req.getParameterNames();
		while(enu.hasMoreElements()) {
			String name =(String) enu.nextElement();
			String[] values = req.getParameterValues(name);
			for(String value: values) {
				System.out.println("parameter: "+name+"->"+value);
			}
		}
	}
	
	public void destroy()
	{
		System.out.println("input2 destroy method call");
	}

}
