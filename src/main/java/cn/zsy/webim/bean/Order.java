package cn.zsy.webim.bean;

import java.io.Serializable;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5549213566240191149L;
	
	private long id;
	
	private String wwid;
	
	private double amount;
	
	private String desc;
	
	private long subuserid;
	
	private String subusername;
	
	private long createtime;
	
	private int state;
	
	private long garbuserid;
	
	private String grabusername;
	
	private long updatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWwid() {
		return wwid;
	}

	public void setWwid(String wwid) {
		this.wwid = wwid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getSubuserid() {
		return subuserid;
	}

	public void setSubuserid(long subuserid) {
		this.subuserid = subuserid;
	}

	public String getSubusername() {
		return subusername;
	}

	public void setSubusername(String subusername) {
		this.subusername = subusername;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getGarbuserid() {
		return garbuserid;
	}

	public void setGarbuserid(long garbuserid) {
		this.garbuserid = garbuserid;
	}

	public String getGrabusername() {
		return grabusername;
	}

	public void setGrabusername(String grabusername) {
		this.grabusername = grabusername;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

}
