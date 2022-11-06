package mvc_test.mvc2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.Buffer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet{
	private static String ARTICLE_IMAGE_REPO = "D:\\eclips_workspace\\work-space\\mvc_test\\src\\main\\webapp\\img";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		doHandle(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		doHandle(req,res);
	}
	
	public void doHandle(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		String file_name = req.getParameter("imageFileName");
		String dir = req.getParameter("articleNO");
		byte[] buffer = new byte[1024*1024*10];
		File img_file = new File(this.ARTICLE_IMAGE_REPO+File.separator+dir+File.separator+file_name);
		try {
			FileInputStream in = new FileInputStream(img_file);
			OutputStream out = res.getOutputStream();
			res.setHeader("Cache-Control","no-cache");
			res.addHeader("Content-disposition", "attachment;fileName="+file_name);
			while(true){
				int count = in.read(buffer);
				if(count == -1) {
					break;
				}
				out.write(buffer, 0, count);		
			}
			in.close();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
