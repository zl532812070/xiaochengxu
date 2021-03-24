package zytb.service.inter;

import java.util.List;


import zytb.bean.User;
import zytb.bean.UserPageInfo;

public interface UserServiceInter {

	boolean addUser(User user);
	
	boolean existUser(User user);
	
	UserPageInfo findUserListByPage(int start, int limit);
	
	boolean login(String userName,String passWord);
}
