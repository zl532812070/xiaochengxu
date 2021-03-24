package zytb.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import zytb.bean.User;
import zytb.bean.UserPageInfo;
import zytb.service.inter.UserServiceInter;

@Service("userService")
public class UserServiceImpl implements UserServiceInter {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		if(!existUser(user)){
			if(sqlSessionTemplate.insert("addUser", user) == 1){
				return true;
			}
		}else{
			return true;
		}
		return false;
	}
	public boolean existUser(User user) {
		// TODO Auto-generated method stub
		if((Integer)sqlSessionTemplate.selectOne("existUser", user) == 1){
			return true;
		}
		return false;
	}
	
	public boolean login(String userName,String passWord) {
		// TODO Auto-generated method stub
		if("admin".equals(userName) && "d41d8cd98f00b204e9800998ecf8427e".equals(passWord)){
			return true;
		}
		return false;
	}
	
	public UserPageInfo findUserListByPage(int start, int limit) {
		// TODO Auto-generated method stub
		int userCount=sqlSessionTemplate.selectOne("userCount");
		HashMap<String, Integer> params=new HashMap<String, Integer>();
		params.put("start", start);
		params.put("limit", limit);
		List<User> users= sqlSessionTemplate.selectList("userListByLimit",params);
		return new UserPageInfo(users, userCount);
	}
}
