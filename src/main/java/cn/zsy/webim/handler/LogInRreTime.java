package cn.zsy.webim.handler;

import cn.zsy.webim.bean.User;
import cn.zsy.webim.service.UserService;

public class LogInRreTime implements Runnable {

	private UserService userService;

	private User user;

	public LogInRreTime(UserService userService, User user) {
		super();
		this.userService = userService;
		this.user = user;
	}

	@Override
	public void run() {
		userService.updateUserForLastLog(user);
	}

}
