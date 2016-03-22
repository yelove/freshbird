package cn.zsy.webim.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zsy.webim.bean.Order;

@Controller
public class OrderAction {
	
	@RequestMapping(value="addorder",method = RequestMethod.POST)
	public String subOreder(Order od,HttpServletRequest request){
		od.setCreatetime(System.currentTimeMillis());
//		od.setSubuserid(subuserid);
		return "";
	}

}
