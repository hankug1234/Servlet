package mvc_test.mvc1;

import java.util.Date;

public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	public MemberVO() {
		System.out.println("MemberVO init");
	}
	
	public MemberVO(String id, String pwd, String name, String email,Date joinDate) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.joinDate = joinDate;
		
	}
	
	public MemberVO(String id, String pwd, String name, String email) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		
	}
	
	
	public String getId() {
		return this.id;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public Date getJoinDate() {
		return this.joinDate;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
