package cn.zsy.webim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zsy.webim.bean.Department;
import cn.zsy.webim.bean.User;
import cn.zsy.webim.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	private static ConcurrentHashMap<String,User> userCache =  new ConcurrentHashMap<String,User>();
	
	private static List<Department> departList = new ArrayList<Department>();
	
	@PostConstruct
	public void initUser(){
		departList.add(new Department(0, "待分配", "dfp"));
		departList.add(new Department(1, "接单客服", "jdkf"));
		departList.add(new Department(2, "抢单客服", "qdkf"));
		departList.add(new Department(3, "管理部门", "glbm"));
	}
	
	public boolean saveUser(User user){
		user.setId(System.currentTimeMillis());//临时ID
		user.setCreatetime(System.currentTimeMillis());
		userCache.put(user.getName(), user);
		userDao.saveUser(user);
		return true;
	}
	
	public User getUserByName(String name){
		User user = userDao.getUser(name);
		if(null!=user){
			userCache.put(user.getName(), user);
		}
		return user;
	}

	public void logout(User user) {
		user.setUpdatetime(System.currentTimeMillis());
		userCache.put(user.getName(), user);
	}
	
	public void updateUserForLastLog(User user){
		userDao.updateUserForLastLog(user.getId(), System.currentTimeMillis());
	}

}
