package cn.zsy.webim.bean;

public class OrderCacheRs {
	
	private boolean rs;
	
	private int big;
	
	private int lit;

	public boolean isRs() {
		return rs;
	}

	public void setRs(boolean rs) {
		this.rs = rs;
	}

	public int getBig() {
		return big;
	}

	public void setBig(int big) {
		this.big = big;
	}

	public int getLit() {
		return lit;
	}

	public void setLit(int lit) {
		this.lit = lit;
	}

	public OrderCacheRs(boolean rs, int big, int lit) {
		super();
		this.rs = rs;
		this.big = big;
		this.lit = lit;
	}
}
