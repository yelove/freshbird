package cn.zsy.webim.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import cn.zsy.webim.bean.User;

@Service
public class UserService {
	
	private static ConcurrentHashMap<String,User> userCache =  new ConcurrentHashMap<String,User>();
	
	
	
	public boolean saveUser(User user){
		user.setId(System.currentTimeMillis());//临时ID
		user.setCreatetiem(System.currentTimeMillis());
		userCache.put(user.getName(), user);
		return true;
	}
	
	public User getUserByName(String name){
		return userCache.get(name);
	}

}
