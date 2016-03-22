package cn.zsy.webim.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.zsy.webim.bean.User;

@Service
public class UserDao {
	
	@Autowired
	private JdbcTemplate template;
	
	public boolean saveUser(User user){
		int i = template.update("insert into user ", user.getName());
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

}
