package cn.zsy.webim.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zsy.common.CommonStr;
import cn.zsy.webim.bean.OrderCacheRs;
import cn.zsy.webim.service.OrderService;

@Controller
public class LongConectionAction {
	
	@Autowired
	private OrderService odService;
	
	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> ajax() {
		Map<String,Object> rm = new HashMap<String,Object>();
		rm.put(CommonStr.STATUS, 1002);
		long stime = System.currentTimeMillis();
		while (true) {
			OrderCacheRs odrs = odService.getOrder();
			if (odrs.isRs()) {
				rm.put(CommonStr.STATUS, 1000);
				rm.put(CommonStr.BIG, odrs.getBig());
				rm.put(CommonStr.LIT, odrs.getLit());
				break; 
			} else { 
				if((System.currentTimeMillis()-stime)>14001){
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return rm;
	}
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
