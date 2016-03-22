package cn.zsy.webim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.zsy.common.CommonStr;

/**
 * 
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		Object username = request.getSession(true).getAttribute(CommonStr.USERNAME);
		if (!StringUtils.isEmpty(username)||request.getRequestURI().contains("user/login")) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath());
			return false;
		}
	}

}