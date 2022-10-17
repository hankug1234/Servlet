package sec01.ex01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet{
	
	@Override
	public void init() throws ServletException{
		System.out.println("second sevlet init method call");
	}
	
	@Override
	public void doGet(HttpServletRequest rep,HttpServletResponse resp)
	throws ServletException, IOException
	{
		System.out.println("second servlet doGet method call");
	}
	
	@Override
	public void destroy() {
		System.out.println("second servelt destroy method call");
	}

}
