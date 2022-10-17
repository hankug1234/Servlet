package sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class MemberDAO {
	private Statement stmt;
	private Connection con;
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new LinkedList<>();
		try {
			connDB();
			String sql = "select * from t_member";
			ResultSet result = stmt.executeQuery(sql);
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
			stmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void connDB() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver load");
		con = DriverManager.getConnection("localhost", "root", "1234");
		System.out.println("connection success");
		stmt = con.createStatement();
		System.out.println("create statement");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
