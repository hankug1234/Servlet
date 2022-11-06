package ajax.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	private Connection con;
	private DataSource dataFactory;
	private Statement stmt;
	
	public MemberDAO() {
		try {
		Context ctx = new InitialContext();
		Context ctxEnv = (Context)ctx.lookup("java:/comp/env");
		this.dataFactory = (DataSource) ctxEnv.lookup("jdbc/mysql");
		}
		catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	public boolean overlappedID(String id) {
		boolean state = false;
		try {
			this.con = this.dataFactory.getConnection();
			String sql = "select if(count(*)>0,true,false) from t_member where id like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();
			result.next();
			state = result.getBoolean(1);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return state;
	}
	
	

}
