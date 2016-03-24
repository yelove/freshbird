package cn.zsy.webim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
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
						"INSERT into `order` (wwid,amount,`desc`,subuserid,subusername,createtime,state) VALUES (?,?,?,?,?,?,?);");
				ps.setString(1, order.getWwid());
				ps.setDouble(2, order.getAmount());
				ps.setString(3, order.getDesc());
				ps.setLong(4, order.getSubuserid());
				ps.setString(5, order.getSubusername());
				ps.setLong(6, order.getCreatetime());
				return ps;
			}
		}, kh);
		return kh.getKey().longValue();
	}

	public Order getOrder(Long id) {
		return template.queryForObject("SELECT * from `order` where id = ?;", new Object[] { id }, Order.class);
	}

	public boolean updateOrderForGrad(Order order) {
		int i = template.update("UPDATE `order` set garbuserid=?,grabusername=?,updatetime=?,state=1 where id = ?",
				order.getGarbuserid(), order.getGrabusername(), order.getUpdatetime(), order.getId());
		if(i>0){
			return true;
		}
		return false;
	}

}
