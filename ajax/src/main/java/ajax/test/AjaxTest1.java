package ajax.test;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/server1")
public class AjaxTest1 extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws IOException, ServletException {
		doHandle(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
		doHandle(req,res);
	}
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/plain;charset=utf-8");
		String param = req.getParameter("param");
		PrintWriter out = res.getWriter();
		System.out.println("param: "+param);
		out.print("hello i am server");
	}
	

}
