package cn.zsy.webim.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zsy.common.CommonStr;
import cn.zsy.webim.bean.User;
import cn.zsy.webim.service.UserService;

@RequestMapping(value = "user/")
@Controller
public class LogInAction {

	@Autowired
	private UserService uService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(User user, ModelMap mdmap, HttpServletRequest request) {
		Map<String, Object> rm = new HashMap<String, Object>();
		rm.put(CommonStr.STATUS, 1002);
		if (StringUtils.isEmpty(user.getName())) {
			rm.put(CommonStr.STATUS, 1009);
			return rm;
		}
		User aluser = uService.getUserByName(user.getName());
		if (null == aluser) {
			rm.put(CommonStr.STATUS, 1009);
			return rm;
		}
		if (aluser.getPassword() != user.getPassword()) {
			rm.put(CommonStr.STATUS, 1004);
			return rm;
		}
		request.getSession(true).setAttribute(CommonStr.USERNAME, user.getName());
		request.getSession(true).setAttribute(CommonStr.TKUSER, aluser);
		return rm;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logOut(User user, ModelMap mdmap, HttpServletRequest request) {
		request.getSession(true).removeAttribute(CommonStr.USERNAME);
		request.getSession(true).removeAttribute(CommonStr.TKUSER);
		return "redirect:/";
	}
}
