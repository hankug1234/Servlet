package ajax.test;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkId")
public class MemberServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doHandle(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doHandle(req,res);
	}
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/plain;charset=utf-8");
		String id = req.getParameter("id");
		PrintWriter out = res.getWriter();
		
		MemberDAO dao = new MemberDAO();
		boolean state = dao.overlappedID(id);
		System.out.println(state);
		if(state) {
			out.print("already exist current id");
		}else {
			out.print("can use this id");
		}
	}
	
	

}
