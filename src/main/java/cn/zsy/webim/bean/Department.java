package cn.zsy.webim.bean;

import java.io.Serializable;

public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5314150449923927245L;
	
	private long id;
	
	private String name;
	
	private String bname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Department(long id, String name, String bname) {
		super();
		this.id = id;
		this.name = name;
		this.bname = bname;
	}
	

}
