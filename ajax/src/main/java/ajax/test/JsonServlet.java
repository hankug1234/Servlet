package ajax.test;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jsonServlet")
public class JsonServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse  res) 
	throws ServletException, IOException{
		doHandle(req,res);
	}
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException 
	{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		try {
		JSONParser jsonParser = new JSONParser();
		String info = req.getParameter("jsonInfo");
		JSONObject jsonObject = (JSONObject)jsonParser.parse(info);
		System.out.println(jsonObject.get("name"));
		System.out.println(jsonObject.get("age"));
		System.out.println(jsonObject.get("gender"));
		System.out.println(jsonObject.get("nickName"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
