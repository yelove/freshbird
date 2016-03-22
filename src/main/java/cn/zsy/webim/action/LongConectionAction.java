package cn.zsy.webim.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zsy.common.CommonStr;

@Controller
public class LongConectionAction {

	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> ajax(long timed, HttpServletResponse response) {
		Random rand = new Random();
		// 死循环 查询有无数据变化
		Map<String,Object> rm = new HashMap<String,Object>();
		while (true) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} // 休眠300毫秒，模拟处理业务等
			int i = rand.nextInt(100); // 产生一个0-100之间的随机数
			if (i > 20 && i < 56) { // 如果随机数在20-56之间就视为有效数据，模拟数据发生变化
				long responseTime = System.currentTimeMillis();
				// 返回数据信息，请求时间、返回数据时间、耗时
				rm.put(CommonStr.STATUS, 1000);
				rm.put(CommonStr.DESC, "result: " + i + ", response time: " + responseTime + ", request time: " + timed
						+ ", use time: " + (responseTime - timed));
				break; // 跳出循环，返回数据
			} else { // 模拟没有数据变化，将休眠 hold住连接
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return rm;
	}

}
