package cn.zsy.webim.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import cn.zsy.webim.bean.Order;

@Service
public class OrderDao {

	@Autowired
	private JdbcTemplate template;

	public long saveOrder(final Order order) {
		KeyHolder kh = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"INSERT into `order` (wwid,amount,`desc`,subuserid,subusername,createtime,state) VALUES (?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, order.getWwid());
				ps.setDouble(2, order.getAmount());
				ps.setString(3, order.getDesc());
				ps.setLong(4, order.getSubuserid());
				ps.setString(5, order.getSubusername());
				ps.setLong(6, order.getCreatetime());
				ps.setLong(7, order.getState());
				return ps;
			}
		}, kh);
		return kh.getKey().longValue();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Order getOrder(Long id) {
		return template.queryForObject("SELECT * from `order` where id = ?;", new Object[] { id },
				new BeanPropertyRowMapper(Order.class));
	}

	public boolean updateOrderForGrad(Order order) {
		int i = template.update("UPDATE `order` set grabuserid=?,grabusername=?,updatetime=?,state=1 where id = ?",
				order.getGrabuserid(), order.getGrabusername(), order.getUpdatetime(), order.getId());
		if (i > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateOrderForGradEnd(Order order) {
		int i = template.update("UPDATE `order` set state=? where id = ?", order.getState(),order.getId());
		if (i > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public int count(String qstr) {
		String sql = "select count(1) from `order` where state=2 and  wwid like ?";
		return template.queryForInt(sql,"%"+qstr+"%");
	}

	@SuppressWarnings("rawtypes")
	public List<Order> getOrder(int pageIndex, int pageSize, String qstr) {
		String sql = "select id,wwid,amount,`desc`,subuserid,subusername,createtime,state,grabuserid,grabusername,updatetime from `order` where state=2 and wwid like ? limit ?,?";
		List<Map<String,Object>> rsm = template.queryForList(sql,
				new Object[] { "%"+qstr+"%", pageSize * (pageIndex - 1), pageSize});
		List<Order> rsod = new ArrayList<Order>();
		for(Map rs : rsm){
			Order od = new Order();
			try {
				BeanUtils.populate(od, rs);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			rsod.add(od);
		}
		return rsod;
	}

	@SuppressWarnings("rawtypes")
	public List<Order> getCacheOrder() {
		String sql = "select * from `order` where state in (0,1)";
		List<Map<String,Object>> rsm = template.queryForList(sql);
		List<Order> rsod = new ArrayList<Order>();
		for(Map rs : rsm){
			Order od = new Order();
			try {
				BeanUtils.populate(od, rs);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			rsod.add(od);
		}
		return rsod;
	}

}
