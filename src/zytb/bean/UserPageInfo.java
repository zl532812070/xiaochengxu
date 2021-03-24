package zytb.bean;

import java.util.List;

public class UserPageInfo {

	private List<User> users;
	private int userCount;

	public UserPageInfo(List<User> users, int userCount) {
		super();
		this.users = users;
		this.userCount = userCount;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

}
