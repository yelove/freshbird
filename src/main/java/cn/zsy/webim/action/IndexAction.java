package cn.zsy.webim.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexAction {
	
	@RequestMapping(value="/main",method = RequestMethod.GET)
	public String mian(){
		return "main";
	}
	
	@RequestMapping(value="/suborder",method = RequestMethod.GET)
	public String suborder(){
		return "jdkf/suborder";
	}
	
	@RequestMapping(value="/graborder",method = RequestMethod.GET)
	public String graborder(){
		return "jdkf/graborder";
	}

}
