package cn.zsy.webim.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zsy.common.CommonStr;
import cn.zsy.webim.bean.Order;
import cn.zsy.webim.bean.User;
import cn.zsy.webim.service.OrderService;

@Controller
public class IndexAction {
	
	@Autowired
	private OrderService odService;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String mianf(){
		return "main";
	}
	
	@RequestMapping(value="/main",method = RequestMethod.GET)
	public String mian(){
		return "main";
	}
	
	@RequestMapping(value="/suborder",method = RequestMethod.GET)
	public String suborder(){
		return "jdkf/suborder";
	}
	
	@RequestMapping(value="/graborder",method = RequestMethod.GET)
	public String graborder(HttpServletRequest request){
		User user = (User) request.getSession(true).getAttribute(CommonStr.TKUSER);
		List<Order> ol = odService.getGrabedOrder(user.getId());
		if(null!=ol){
			request.setAttribute("orderlist", ol);
		}
		return "jdkf/graborder";
	}

}
