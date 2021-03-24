package zytb.rest.inter;

import zytb.bean.User;
import zytb.bean.UserPageInfo;

public interface UserRestInter {

	boolean addUser(User user);
	
	UserPageInfo findUserListByPage(int start, int limit);
	
	String login();
	
	User findUserByToken();
	
	public String exit();
}
