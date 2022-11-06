package mvc_test.mvc2;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ArticleVO {
	private int level;
	private int articleNO;
	private int parentNO;
	private String title;
	private String imageFileName;
	private String id;
	private String content;
	private Date writeDate;
	
	public ArticleVO() {}
	public ArticleVO(int level, int articleNO, int parentNO, String title, String imageFileName
			,String id, Date writeDate, String content) {
		this.level = level;
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.imageFileName = imageFileName;
		this.id = id;
		this.writeDate = writeDate;
		this.content = content;
	}
	
	public ArticleVO(int level, int articleNO, int parentNO, String title
			,String id, Date writeDate,String content) {
		this.level = level;
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.id = id;
		this.writeDate = writeDate;
		this.content = content;
	}
	
	

}
