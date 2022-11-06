package mvc_test.mvc2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.xdevapi.Statement;

public class BoardDAO {
	
	DataSource dataFactory;
	
	public BoardDAO(){
		try {
		Context ctx = new InitialContext();
		Context jdni = (Context) ctx.lookup("java:/comp/env");
		this.dataFactory = (DataSource) jdni.lookup("jdbc/mysql");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ArticleVO> selectAllArticles(){
		List<ArticleVO> result = new ArrayList<>();
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "select t1.level as level, t2.articleNO as articleNO, t2.parentNO as parentNO, LPAD(t2.title,15+level*2,'->') as title, t2.content as content "
					+ ",t2.writedate as writedate, t2.id as id "
					+ "from t_board as t2 right join "
					+ "(select hierarchi() as articleNO, @level as level from (select @start_with:=0, @articleNO:=@start_with, @level:=0) as var join t_board) as t1 "
					+ "on t2.articleNO = t1.articleNO;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("writedate");
				String id = rs.getString("id");
				
				result.add(new ArticleVO(level,articleNO,parentNO,title,id,writeDate,content));
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	private int getNewArticleNO() {
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "select max(articleNO) from t_board";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertNewArticle(ArticleVO articleVO) {
		int articleNO = getNewArticleNO();
		try {
			Connection con = this.dataFactory.getConnection();
			int parentNO = articleVO.getParentNO();
			String title = articleVO.getTitle();
			String content = articleVO.getContent();
			String id = articleVO.getId();
			String imageFileName = articleVO.getImageFileName();
			
			String sql = "insert into t_board (articleNO, parentNo, title, content, id, imageFileName)";
			sql = sql+"values(?,?,?,?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, id);
			pstmt.setString(6, imageFileName);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}
	
	public ArticleVO selectArticle(int articleNO) {
		ArticleVO articleVO = null;
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "select * from t_board where articleNO = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int _articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String imageFileName = rs.getString("imageFileName");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				articleVO = new ArticleVO();
				articleVO.setArticleNO(_articleNO);
				articleVO.setParentNO(parentNO);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleVO.setId(id);
				articleVO.setWriteDate(writeDate);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleVO;
	}
	
	public void updateArticle(ArticleVO articleVO) {
		int articleNO = articleVO.getArticleNO();
		String title = articleVO.getTitle();
		String imageFileName = articleVO.getImageFileName();
		String content = articleVO.getContent();
		
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "update t_board set title=?,content=?";
			if(imageFileName != null && imageFileName.length()> 0) {
				sql+=",imageFileName=?";
			}
			sql+="where articleNO = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if(imageFileName != null && imageFileName.length()> 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			}
			else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArticle(int articleNO) {
		try {
			Connection con = this.dataFactory.getConnection();
			String sql2 = "delete from t_board where articleNO = ?;";
			String sql = "delete from t_board where articleNO in (select t.articleNO as articleNO from (select hierarchi() as articleNO from (select @start_with:=?, @articleNO:=@start_with, @level:=0) as var join t_board) as t where not t.articleNO is null);";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			pstmt.execute();
			
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, articleNO);
			pstmt.execute();
			
			
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> selectRemoveArticles(int articleNO){
		List<Integer> articleList = new ArrayList<Integer>();
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "select t.articleNO as articleNO from (select hierarchi() as articleNO "
					+ "from (select @start_with:=?, @articleNO:=@start_with, @level:=0) as var join t_board)"
					+ " as t where not t.articleNO is null;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			articleList.add(articleNO);
			while(rs.next()) {
		
				articleList.add(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
	public List<ArticleVO> selectAllArticles(Map<String,Integer> pagingMap){
		List<ArticleVO> articleList = new ArrayList<ArticleVO>();
		int section = (Integer) pagingMap.get("section");
		int pageNum = (Integer) pagingMap.get("pageNum");
		try 
		{
			Connection con = this.dataFactory.getConnection();
			String sql = "select t1.level as level, t2.articleNO as articleNO, t2.parentNO as parentNO, LPAD(t2.title,15+level*2,'->') as title, t2.content as content "
					+ ",t2.writedate as writedate, t2.id as id "
					+ "from t_board as t2 right join "
					+ "(select hierarchi() as articleNO, @level as level from (select @start_with:=0, @articleNO:=@start_with, @level:=0) as var join t_board) as t1 "
					+ "on t2.articleNO = t1.articleNO limit ?,?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (section-1)*100 + (pageNum-1)*10);
			pstmt.setInt(2, (section-1)*100 + (pageNum)*10);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("writedate");
				String id = rs.getString("id");
				
				articleList.add(new ArticleVO(level,articleNO,parentNO,title,id,writeDate,content));
			}
			rs.close();
			pstmt.close();
			con.close();
			
			
		}catch(Exception e) {
			
		}
		return articleList;
	}
	
	public int selectTotArticles() {
		int num =0;
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "select count(*) form t_board";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return num;
	}

}
