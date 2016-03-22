package cn.zsy.webim.bean;

import java.io.Serializable;

public class Menu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6122513389025563838L;
	
	private long id;
	
	private String name;
	
	private long pid;
	
	private String url;
	
	private int isnode;
	
	private long pwoerid;

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

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsnode() {
		return isnode;
	}

	public void setIsnode(int isnode) {
		this.isnode = isnode;
	}

	public long getPwoerid() {
		return pwoerid;
	}

	public void setPwoerid(long pwoerid) {
		this.pwoerid = pwoerid;
	}
	
}
