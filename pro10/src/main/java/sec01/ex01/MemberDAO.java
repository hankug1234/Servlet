package sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.util.LinkedList;

public class MemberDAO {
	private Statement stmt;
	private Connection con;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
		Context ctx = new InitialContext();
		Context evCtx = (Context) ctx.lookup("java:/comp/env");
		dataFactory = (DataSource) evCtx.lookup("jdbc/mysql");
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new LinkedList<>();
		try {
			//connDB();
			con = dataFactory.getConnection();
			String sql = "select * from servlet.t_member";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String email = result.getString("email");
				Date joinDate = result.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
				
			}
			result.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			System.out.println("error occure");
			e.printStackTrace();
		}
		return list;
	}
	
	private void connDB() {
		try {
		System.out.println("connection start");	
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver load");
		con = DriverManager.getConnection("jdbc:mysql://localhost/servlet", "root", "1234");
		System.out.println("connection success");
		stmt = con.createStatement();
		System.out.println("create statement");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
