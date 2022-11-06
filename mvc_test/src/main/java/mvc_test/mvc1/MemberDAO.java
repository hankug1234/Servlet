package mvc_test.mvc1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	DataSource dataFactory;
	PreparedStatement select;
	
	public MemberDAO() {
		try {
			Context cxt = new InitialContext();
			Context jdni = (Context) cxt.lookup("java:/comp/env");
			this.dataFactory = (DataSource) jdni.lookup("jdbc/mysql");
			System.out.println("connection pool start");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMember(List<MemberVO> list){
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "select * from t_member";
			this.select = con.prepareStatement(sql);
			ResultSet result = select.executeQuery();
			while(result.next()) {
				String id = result.getString("id");
				String name = result.getString("name");
				String pwd = result.getString("pwd");
				String email = result.getString("email");
				Date joinDate = result.getDate("joinDate");
				list.add(new MemberVO(id,pwd,name,email,joinDate));
			}
			result.close();
			select.close();
			con.close();
			System.out.println("member data load success");
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public boolean addMember(MemberVO member) {
		boolean result = false;
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "insert into t_member (id,pwd,name,email) values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			
			pstmt.execute();
			
			pstmt.close();
			con.close();
			result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public MemberVO findMember(String id) {
		MemberVO result = null;
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "select * from t_member where id like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String ID = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				result = new MemberVO(ID,pwd,name,email);
			}
			
			System.out.println("find member success");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean modMember(MemberVO info) {
		boolean result = false;
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "update t_member set pwd=?,name=?,email=? where id like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,info.getPwd());
			pstmt.setString(2,info.getName());
			pstmt.setString(3,info.getEmail());
			pstmt.setString(4, info.getId());
			pstmt.executeUpdate();
			System.out.println("modMeber success");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean delMember(String id) {
		boolean result = false;
		try {
			Connection con = this.dataFactory.getConnection();
			String sql = "delete from t_member where id like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.execute();
			System.out.println("delMember success: "+result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
