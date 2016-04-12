package cn.zsy.webim.page;

import java.util.List;

public interface QueryHandler<T> {
	
	/**
     * 获取数据总行数
     * @return
     */
    int getTotalElements();
    
    /**
     * 获取当前页的数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<T> getCurrData(int pageIndex, int pageSize);

}
