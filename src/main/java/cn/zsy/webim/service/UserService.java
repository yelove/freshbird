package cn.zsy.webim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import cn.zsy.webim.bean.Department;
import cn.zsy.webim.bean.User;

@Service
public class UserService {
	
	private static ConcurrentHashMap<String,User> userCache =  new ConcurrentHashMap<String,User>();
	
	private static List<Department> departList = new ArrayList<Department>();
	
	@PostConstruct
	public void initUser(){
		departList.add(new Department(1, "接单客服", "jdkf"));
		departList.add(new Department(2, "抢单客服", "qdkf"));
		departList.add(new Department(3, "管理部门", "glbm"));
	}
	
	public boolean saveUser(User user){
		user.setId(System.currentTimeMillis());//临时ID
		user.setCreatetiem(System.currentTimeMillis());
		userCache.put(user.getName(), user);
		return true;
	}
	
	public User getUserByName(String name){
		return userCache.get(name);
	}

	public void logout(User user) {
		user.setUpdatetime(System.currentTimeMillis());
		userCache.put(user.getName(), user);
	}

}
