package cn.zsy.webim.service;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zsy.webim.bean.Order;
import cn.zsy.webim.bean.OrderCacheRs;
import cn.zsy.webim.bean.User;
import cn.zsy.webim.dao.OrderDao;

/**
 * @author zsy 订单业务类
 */
@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;

	/**
	 * 订单缓存
	 */
	private static ConcurrentHashMap<Long, Order> bigorderCache = new ConcurrentHashMap<Long, Order>();

	/**
	 * 订单缓存
	 */
	private static ConcurrentHashMap<Long, Order> litorderCache = new ConcurrentHashMap<Long, Order>();

	/**
//	 * 存入待抢订单队列
//	 */
//	private static ConcurrentLinkedQueue<Order> orderQueue = new ConcurrentLinkedQueue<Order>();

	// private static BlockingQueue<Order> orderQueue = new
	// ArrayBlockingQueue<Order>(200);
	// private ConcurrentHashMap<Long,Order> orderCache = new
	// ConcurrentHashMap<Long,Order>();

	/**
	 * @return 获取待抢订单,出于性能考虑 及 业务 不使用synchronize
	 */
	public OrderCacheRs getOrder() {
		if(bigorderCache.isEmpty()&&litorderCache.isEmpty()){
			return new OrderCacheRs(false,0,0);
		}
		return  new OrderCacheRs(true,bigorderCache.size(),litorderCache.size());
	}
	
	public Order grabOrder(User user,Long orderid){
		Order od = null;
		if(!bigorderCache.isEmpty()){
			Set<Long> ls = bigorderCache.keySet();
			Object[] ids = ls.toArray();
			Arrays.sort(ids);
			od = bigorderCache.get(ids[0]);
			bigorderCache.remove(ids[0]);
		}else{
			if(!litorderCache.isEmpty()){
				Set<Long> ls = litorderCache.keySet();
				Object[] ids = ls.toArray();
				Arrays.sort(ids);
				od = litorderCache.get(ids[0]);
				litorderCache.remove(ids[0]);
			}
		}
		if(null!=od){
			od.setGarbuserid(user.getId());
			od.setGrabusername(user.getRealname());
			od.setUpdatetime(System.currentTimeMillis());
			od.setState(1);
			orderDao.updateOrderForGrad(od);
		}
		return od;
	}

	public boolean putOrder(Order order) {
		Long id = orderDao.saveOrder(order);
		order.setId(id);
		if(order.getAmount()>=30){
			bigorderCache.put(id, order);
		}else{
			litorderCache.put(id, order);
		}
		return true;
	}

}
