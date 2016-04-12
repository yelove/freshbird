package cn.zsy.webim.bean;

import java.io.Serializable;
import java.util.Date;

import cn.zsy.util.TimeUtils;

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
	
	private String ctstr = "";
	
	private int state;
	
	private long grabuserid;
	
	private String grabusername = "";
	
	private long updatetime;
	
	private String upstr = "";

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

	public long getGrabuserid() {
		return grabuserid;
	}

	public void setGrabuserid(long grabuserid) {
		this.grabuserid = grabuserid;
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

	public String getCtstr() {
		if(createtime>0){
			ctstr = TimeUtils.date2str(new Date(createtime));
		}
		return ctstr;
	}

	public void setCtstr(String ctstr) {
		this.ctstr = ctstr;
	}

	public String getUpstr() {
		if(updatetime>0){
			upstr = TimeUtils.date2str(new Date(updatetime));
		}
		return upstr;
	}

	public void setUpstr(String upstr) {
		this.upstr = upstr;
	}
	

}
