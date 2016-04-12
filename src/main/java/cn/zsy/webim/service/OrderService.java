package cn.zsy.webim.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zsy.common.CommonStr;
import cn.zsy.webim.bean.Order;
import cn.zsy.webim.bean.OrderCacheRs;
import cn.zsy.webim.bean.User;
import cn.zsy.webim.dao.OrderDao;
import cn.zsy.webim.page.Pager;

/**
 * @author zsy 订单业务类
 */
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	/**
	 * 订单缓存 金额大于30
	 */
	private static ConcurrentHashMap<Long, Order> bigorderCache = new ConcurrentHashMap<Long, Order>();

	/**
	 * 订单缓存 金额小于30
	 */
	private static ConcurrentHashMap<Long, Order> litorderCache = new ConcurrentHashMap<Long, Order>();
	
	/**
	 * 已抢订单缓存 用户id为key 值中订单号为key
	 */
	private static ConcurrentHashMap<Long, Map<Long,Order>> usergradorder = new ConcurrentHashMap<Long, Map<Long,Order>>();

	
	@PostConstruct
	public void initDbDate(){
		List<Order> odlist = orderDao.getCacheOrder();
		for(Order od : odlist){
			if(od.getState()==0){
				if (od.getAmount() >= 30) {
					bigorderCache.put(od.getId(), od);
				} else {
					litorderCache.put(od.getId(), od);
				}
			}else if(od.getState()==1){
				Map<Long,Order> userGrabod = usergradorder.get(od.getGrabuserid());
				if(null==userGrabod||userGrabod.isEmpty()){
					userGrabod = new HashMap<Long,Order>();
					usergradorder.put(od.getGrabuserid(), userGrabod);
				}
				userGrabod.put(od.getId(), od);
			}
		}
	}

	/**
	 * @return 获取待抢订单,出于性能考虑 及 业务 不使用synchronize
	 */
	public OrderCacheRs getOrder() {
		if (bigorderCache.isEmpty() && litorderCache.isEmpty()) {
			return new OrderCacheRs(false, 0, 0);
		}
		return new OrderCacheRs(true, bigorderCache.size(), litorderCache.size());
	}

	public Order grabOrder(User user, Long orderid) {
		boolean flag = false;
		Order od = null;
		if (orderid > 0) {
			flag = true;
		}
		if (!bigorderCache.isEmpty()) {
			if (flag) {
				od = bigorderCache.get(orderid);
			} else {
				Collection<Order> ordcs = bigorderCache.values();
				List<Order> ol = new ArrayList<Order>(ordcs);
				Collections.sort(ol, new Comparator<Order>() {
					public int compare(Order order0, Order order1) {
						if (order0.getAmount() > order1.getAmount()) {
							return 1;
						} else if (order0.getAmount() == order1.getAmount()) {
							return 0;
						} else {
							return -1;
						}
					}
				});
				od = bigorderCache.get(ol.get(0).getId());
				bigorderCache.remove(ol.get(0).getId());
			}

		} else {
			if (!litorderCache.isEmpty()) {
				if (flag) {
					od = litorderCache.get(orderid);
				} else {
					Set<Long> ls = litorderCache.keySet();
					Object[] ids = ls.toArray();
					Arrays.sort(ids);
					od = litorderCache.get(ids[0]);
					litorderCache.remove(ids[0]);
				}
			}
		}
		if (null != od) {
			od.setGrabuserid(user.getId());
			od.setGrabusername(user.getRealname());
			od.setUpdatetime(System.currentTimeMillis());
			od.setState(1);
			orderDao.updateOrderForGrad(od);
			putGrabedOrder(user.getId(),od);
		}
		return od;
	}
	
	private void putGrabedOrder(Long userid,Order od){
		Map<Long,Order> userGrabod = usergradorder.get(userid);
		if(null==userGrabod||userGrabod.isEmpty()){
			userGrabod = new HashMap<Long,Order>();
			usergradorder.put(userid, userGrabod);
		}
		userGrabod.put(od.getId(), od);
	}

	public boolean putOrder(Order order) {
		Long id = orderDao.saveOrder(order);
		order.setId(id);
		if (order.getAmount() >= 30) {
			bigorderCache.put(id, order);
		} else {
			litorderCache.put(id, order);
		}
		return true;
	}

	public Pager<Order> getOrderPage(String qstr, int currentPage) {
		Pager<Order> orderpager = new Pager<Order>();
		orderpager.setCurrentPage(currentPage);
		orderpager.setTotalSize(orderDao.count(qstr));
		orderpager.setReList(orderDao.getOrder(currentPage, CommonStr.PAGESIZE, qstr));
		return orderpager;
	}

	public List<Order> getGrabedOrder(Long userid) {
		Map<Long,Order> userGrabod = usergradorder.get(userid);
		if(null==userGrabod||userGrabod.isEmpty()){
			return null;
		}
		List<Order> ol = new ArrayList<Order>(userGrabod.values());
		Collections.sort(ol, new Comparator<Order>() {
			public int compare(Order order0, Order order1) {
				if (order0.getId() > order1.getId()) {
					return 1;
				} else if (order0.getAmount() == order1.getAmount()) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		return ol;
	}

}
