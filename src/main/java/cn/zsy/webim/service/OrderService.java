package cn.zsy.webim.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import cn.zsy.webim.bean.Order;

/**
 * @author zsy 订单业务类
 */
@Service
public class OrderService {

	/**
	 * 订单缓存
	 */
	private static ConcurrentHashMap<Long, Order> orderCache = new ConcurrentHashMap<Long, Order>();

	/**
	 * 存入待抢订单队列
	 */
	private static ConcurrentLinkedQueue<Order> orderQueue = new ConcurrentLinkedQueue<Order>();

	// private static BlockingQueue<Order> orderQueue = new
	// ArrayBlockingQueue<Order>(200);
	// private ConcurrentHashMap<Long,Order> orderCache = new
	// ConcurrentHashMap<Long,Order>();

	/**
	 * @return 获取待抢订单,出于性能考虑 及 业务 不使用synchronize
	 */
	public Order getOrder() {
		Order od = null;
		if (!orderQueue.isEmpty()) {
			od = orderQueue.poll();
		}
		return od;
	}

	public boolean putOrder(Order order) {
		return orderQueue.offer(order);
	}

}
