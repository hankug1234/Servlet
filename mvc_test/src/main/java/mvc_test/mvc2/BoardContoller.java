package mvc_test.mvc2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;



@WebServlet("/board/*")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
maxFileSize=1024*1024*50,      	// 50 MB
maxRequestSize=1024*1024*100,
location="D:/eclips_workspace/work-space/mvc_test/src/main/webapp/pre_img") 
public class BoardContoller extends HttpServlet{
	
	private static String ARTICLE_IMAGE_REPO = "D:\\eclips_workspace\\work-space\\mvc_test\\src\\main\\webapp\\img";
	private ArticleVO articleVO;
	private BoardService boardService;
	
	
	public void init() throws ServletException{
		System.out.println("board controller start");
		this.boardService = new BoardService();
		this.articleVO = new ArticleVO();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException
	{
		doHandle(req,res);	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException
	{
		doHandle(req,res);	
	}
	
	public void doHandle(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException
	{
		try {
			req.setCharacterEncoding("utf-8");
			List<ArticleVO> articleList = new ArrayList<>();
			String nextPage = "";
			HttpSession session;
			String action = req.getPathInfo();
			System.out.println("action : "+action);
			if((action == null) || action.equals("/listArticle.do")) {
				String _section = req.getParameter("section");
				String _pageNum = req.getParameter("pageNum");
				int section = Integer.parseInt(((_section == null) ? "1":_section));
				int pageNum = Integer.parseInt(((_pageNum == null) ? "1":_section));
				Map<String,Integer> pagingMap = new HashMap<>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				
				Map articlesMap = this.boardService.listAritcles(pagingMap);
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				
				req.setAttribute("articlesMap", articlesMap);
				nextPage = "/listArticle.jsp";
				
			}else if(action.equals("/articleForm.do")) {
				nextPage = "/articleForm.jsp";
			}
			else if(action.equals("/addArticle.do")) {
				Map<String,String> articleMap = upload(req,res);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				articleVO.setParentNO(0);
				articleVO.setId("kim");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				int articleNO = boardService.addArticle(articleVO);
				System.out.println(articleNO);
				nextPage = "/board/listArticles.do";
				if(imageFileName != null && imageFileName.length() > 0) {
					System.out.println("file move");
					File srcFile = new File(this.ARTICLE_IMAGE_REPO+File.separator+"temp"+File.separator+imageFileName);
					File dstDir = new File(this.ARTICLE_IMAGE_REPO+File.separator+articleNO+File.separator+imageFileName);
					dstDir.mkdirs();
					Files.move(srcFile.toPath(), dstDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
					
				}
				req.setAttribute("messaage","<script> alert(new article); </script>" );
				
			}
			else if(action.equals("/ViewArticle.do")) {
				int articleNO = Integer.parseInt(req.getParameter("articleNO"));
				this.articleVO = this.boardService.viewArticle(articleNO);
				req.setAttribute("articleVO", articleVO);
				nextPage = "/ViewArticle.jsp";
			}
			else if(action.equals("/ModifyArticle.do")) {
				Map<String,String> articleMap = upload(req, res);
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				this.articleVO.setArticleNO(articleNO);
				this.articleVO.setContent(content);
				this.articleVO.setImageFileName(imageFileName);
				this.articleVO.setTitle(title);
				this.articleVO.setParentNO(0);
				this.articleVO.setId("kim");
				
				this.boardService.modArticle(this.articleVO);
				
				if(imageFileName != null && imageFileName.length() >0 ) {
					String originalFile = articleMap.get("originalFileName");
					File srcImg = new File(this.ARTICLE_IMAGE_REPO+File.separator+"temp"+File.separator+imageFileName);
					File dstImg = new File(this.ARTICLE_IMAGE_REPO+File.separator+articleNO+File.separator+imageFileName);
					File oldImg = new File(this.ARTICLE_IMAGE_REPO+File.separator+articleNO+File.separator+originalFile);
					dstImg.mkdirs();
					
					oldImg.delete();
					
					Files.move(srcImg.toPath(), dstImg.toPath(), StandardCopyOption.REPLACE_EXISTING);
					
					req.setAttribute("messaage","<script> alert(modify article); </script>" );
				}
				nextPage="/board/listArticle.do";
				
				
			}else if(action.equals("/RemoveArticle.do")) {
				int articleNO = Integer.parseInt(req.getParameter("articleNO"));
				List<Integer> articleNOList = this.boardService.removeArticle(articleNO);
				for(int i : articleNOList) {
					File deleteFile = new File(this.ARTICLE_IMAGE_REPO+File.separator+i);
					if(deleteFile.exists()) {
						File[] deleteList = deleteFile.listFiles();
						for(File file : deleteList) {
							file.delete();
						}
						deleteFile.delete();
					}
				}
				req.setAttribute("messaage","<script> alert(remove article); </script>" );
				nextPage = "/board/listArticle.do";
			}else if(action.equals("/ReplyForm.do")){
				int parentNO = Integer.parseInt(req.getParameter("parentNO"));
				session = req.getSession();
				session.setAttribute("parentNO", parentNO);
				nextPage = "/ReplyForm.jsp";
			}else if(action.equals("/AddReply.do")) {
				session = req.getSession();
				int parentNO = (int) session.getAttribute("parentNO");
				session.removeAttribute("parentNO");
				Map<String,String> articleMap = upload(req,res);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				this.articleVO.setTitle(title);
				this.articleVO.setContent(content);
				this.articleVO.setImageFileName(imageFileName);
				this.articleVO.setParentNO(parentNO);
				this.articleVO.setId("kim");
				int articleNO = this.boardService.addReply(articleVO);
				if(imageFileName != null && imageFileName.length() > 0) {
					File srcImg = new File(this.ARTICLE_IMAGE_REPO+File.separator+"temp"+File.separator+imageFileName);
					File dstImg = new File(this.ARTICLE_IMAGE_REPO+File.separator+articleNO+File.separator+imageFileName);
					dstImg.mkdirs();
					Files.move(srcImg.toPath(), dstImg.toPath(), StandardCopyOption.REPLACE_EXISTING);
					
				}
				req.setAttribute("message", "<script>alert(add reply)</script>");
				nextPage="/board/listArticle.do";
			}
			else {
				nextPage = "/listArticle.jsp";
				articleList = this.boardService.listArticles();
				req.setAttribute("articleList", articleList);
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher(nextPage);
			dispatcher.forward(req, res);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Map<String,String> upload(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		Map<String,String> articleMap = new HashMap<String,String>();
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		String contentType = request.getContentType();
		
		if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			Collection<Part> parts = request.getParts();
			
			for(Part part : parts) {
				System.out.printf("parameter :%s contentType: %s size: %s",
						part.getName(),part.getContentType(),part.getSize());
				if(part.getHeader("Content-Disposition").contains("filename=")){
					String filename = extractFilename(part.getHeader("Content-Disposition"));
					if(part.getSize()>0) {
						System.out.printf("\n upload filename: %s", filename);
						System.out.println(this.ARTICLE_IMAGE_REPO+File.separator+filename);
						part.write(this.ARTICLE_IMAGE_REPO+File.separator+"temp"+File.separator+filename);
						
					}
					articleMap.put("imageFileName", filename);
					
				}else {
					
					String formValue = request.getParameter(part.getName());
					System.out.printf("field: %s value: %s",part.getName(),formValue);
					articleMap.put(part.getName(), formValue);
				}
			}
			
		}
	
		
		return articleMap;
	}
	
	private String extractFilename(String header) {
		for(String cd : header.split(";")) {
			if(cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf("=")+1).trim().replace("\"","");
				int idx = filename.lastIndexOf(File.separator);
				return filename.substring(idx+1);
			}
		}
		return null;
	}
	

}
