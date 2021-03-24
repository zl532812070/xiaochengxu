package zytb.util;


import java.util.HashMap;

import zytb.bean.User;

public class CacheTool {

	private static HashMap<String, User> mySession;
	
	public static HashMap getSession(){
		if(mySession==null){
			mySession=new HashMap<String, User>();
		}
		return mySession;
	}
	
}
