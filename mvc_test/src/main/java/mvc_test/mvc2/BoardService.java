package mvc_test.mvc2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	
	BoardDAO DAO;
	
	public BoardService() {
		this.DAO = new BoardDAO();
	}
	
	public Map listAritcles(Map pagingMap){
		Map articleMap = new HashMap();
		List<ArticleVO> articleList = this.DAO.selectAllArticles(pagingMap);
		int totArticles = this.DAO.selectTotArticles();
		
		articleMap.put("articleList", articleList);
		articleMap.put("totArticles", totArticles);
		return articleMap;
	}
	
	public List<ArticleVO> listArticles(){
		List<ArticleVO> articleList = DAO.selectAllArticles();
		return articleList;
		
	}
	
	public int addArticle(ArticleVO articleVO) {
		return DAO.insertNewArticle(articleVO);
	}
	
	public ArticleVO viewArticle(int articleNO) {
		return DAO.selectArticle(articleNO);
	}
	
	public void modArticle(ArticleVO articleVO) {
		DAO.updateArticle(articleVO);
		return;
	}
	
	public List<Integer> removeArticle(int articleNO){
		List<Integer> articleNOList = DAO.selectRemoveArticles(articleNO);
		DAO.deleteArticle(articleNO);
		return articleNOList;
	}
	
	public int addReply(ArticleVO articleVO) {
		return DAO.insertNewArticle(articleVO);
	}

}
