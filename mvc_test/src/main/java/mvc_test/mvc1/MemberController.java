package mvc_test.mvc1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet{
	
	MemberDAO dao;
	
	public MemberController() throws ServletException{
		this.dao = new MemberDAO();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException
	{
		doHandle(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException
	{
		doHandle(req,res);
	}
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException
	{
		req.setCharacterEncoding("utf-8");
		
		String path = req.getPathInfo();
		String forward;
		System.out.println("path: "+path);
	
		if( (path == null) || path.equals("/memberlist.do")) {
			forward = "/memberView.jsp";
		}
		else if(path.equals("/addmember.do")) {
			forward = "/memberView.jsp";
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			dao.addMember(new MemberVO(id,pwd,name,email));
			req.setAttribute("msg", "add");
			
		}else if(path.equals("/memberModify.do")) {
			
			forward = "/memberModify.jsp";
			MemberVO info = dao.findMember(req.getParameter("id"));
			req.setAttribute("info", info);
			
		}else if(path.equals("/delMember.do")) {
			
			forward = "/memberView.jsp";
			dao.delMember(req.getParameter("id"));
			req.setAttribute("msg", "del");
			
		}else if(path.equals("/modMember.do")) {
			forward = "/memberView.jsp";
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			dao.modMember(new MemberVO(id,pwd,name,email));
			req.setAttribute("msg", "mod");
			
		}else {
			forward = "/memberLogin.jsp";
		}
		
		if(!forward.equals("/memberModify.do") && !forward.equals("/memberForm.do"))
		{
			ArrayList<MemberVO> memberList = new ArrayList<>();
			dao.listMember(memberList);
			req.setAttribute("memberList", memberList);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, res);
		System.out.println("handling success");
	}

}
