package zytb.rest.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Controller;


import zytb.bean.User;
import zytb.bean.UserPageInfo;
import zytb.rest.inter.UserRestInter;
import zytb.service.inter.UserServiceInter;
import zytb.util.CacheTool;
import zytb.util.TokenUtil;

@Controller
@Path("/user")
public class UserRestImpl implements UserRestInter {

	@Resource
	private UserServiceInter userService;

	@Context
	private HttpServletRequest request;

	@POST
	@Path("/user")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/json;charset=utf-8")
	public boolean addUser(@BeanParam User user) {
		// TODO Auto-generated method stub
		if (userService.addUser(user)) {
			return true;
		}
		return false;
	}

	@Path("/exit")
	@GET
	@Produces("text/json;charset=utf-8")
	public String exit() {
		// TODO Auto-generated method stub
		String token = request.getHeader("Authority");
		CacheTool.getSession().remove(token);
		return "{success:true}";
	}

	@GET
	@Path("login")
	@Produces("text/json;charset=utf-8")
	public String login() {
		// TODO Auto-generated method stub
		String token = "";
		String userName = request.getHeader("userName");
		String passWord = request.getHeader("passWord");
		if (userService.login(userName, passWord)) {
			token = TokenUtil.getToken();
			User user = new User();
			user.setUserName(userName);
			CacheTool.getSession().put(token, user);
		}
		return "{\"token\":\"" + token + "\"}";
	}
	
	@Path("/findUserByToken")
	@GET
	@Produces("text/json;charset=utf-8")
	public User findUserByToken() {
		// TODO Auto-generated method stub
		String token = request.getHeader("Authority");
		User user = (User) CacheTool.getSession().get(token);
		return user;
	}
	
	@Path("/users")
	@GET
	@Produces("text/json;charset=utf-8")
	public UserPageInfo findUserListByPage(@QueryParam("start") int start,
			@QueryParam("limit") int limit) {
		// TODO Auto-generated method stub
		return userService.findUserListByPage(start, limit);
	}

}
