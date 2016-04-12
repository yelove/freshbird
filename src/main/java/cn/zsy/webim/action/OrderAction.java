package cn.zsy.webim.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zsy.common.CommonStr;
import cn.zsy.webim.bean.Order;
import cn.zsy.webim.bean.User;
import cn.zsy.webim.page.Pager;
import cn.zsy.webim.service.OrderService;

@Controller
public class OrderAction {

	@Autowired
	private OrderService odService;

	@RequestMapping(value = "addorder", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody Map<String, Object> subOreder(Order od, HttpServletRequest request) {
		Map<String, Object> rm = new HashMap<String, Object>();
		rm.put(CommonStr.STATUS, 1002);
		User user = (User) request.getSession(true).getAttribute(CommonStr.TKUSER);
		od.setCreatetime(System.currentTimeMillis());
		od.setSubuserid(user.getId());
		od.setSubusername(user.getRealname());
		od.setState(0);
		if (odService.putOrder(od)) {
			rm.put(CommonStr.STATUS, 1000);
		}
		return rm;
	}

	@RequestMapping(value = "graborder/{orderid}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody Map<String, Object> grabOreder(@PathVariable("orderid") long orderid,
			HttpServletRequest request) {
		Map<String, Object> rm = new HashMap<String, Object>();
		rm.put(CommonStr.STATUS, 1002);
		User user = (User) request.getSession(true).getAttribute(CommonStr.TKUSER);
		Order od = odService.grabOrder(user, orderid);
		if (null != od) {
			rm.put(CommonStr.STATUS, 1000);
			rm.put(CommonStr.DESC, od);
		}
		return rm;
	}

	@RequestMapping(value = "queryod", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody Map<String, Object> queryOrder(HttpServletRequest request) {
		String qstr = request.getParameter("qstr");
		String ctpg = request.getParameter("ctpg");
		Pager<Order> odpg = odService.getOrderPage(qstr, StringUtils.isEmpty(ctpg) ? 1 : Integer.parseInt(ctpg));
		Map<String, Object> rm = new HashMap<String, Object>();
		rm.put(CommonStr.STATUS, 1000);
		rm.put("orderlist", odpg.getReList());
		rm.put("ctpg", odpg.getCurrentPage());
		rm.put("maxpg", odpg.getTotalRows());
		rm.put("maxrow", odpg.getTotalSize());
		return rm;
	}

}
