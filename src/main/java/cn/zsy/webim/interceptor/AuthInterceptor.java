package cn.zsy.webim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.zsy.common.CommonStr;
import cn.zsy.webim.bean.User;

/**
 * 登录拦截
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		User user =(User) request.getSession(true).getAttribute(CommonStr.TKUSER);
		if (null!=user||request.getRequestURI().contains("user/login")) {
			if(null!=user){
				request.setAttribute(CommonStr.USERNAME, user.getRealname());
				request.setAttribute("lastlogintime", user.getUpdatetime());
				request.setAttribute("power", user.getDepartmentid());
			}
			return true;
		} else {
			response.sendRedirect(request.getContextPath());
			return false;
		}
	}

}