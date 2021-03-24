package zytb.bean;

import javax.ws.rs.FormParam;

public class User {

	private Integer id;
	@FormParam("userName")
	private String userName;
	@FormParam("tel")
	private String tel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
