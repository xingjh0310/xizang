package com.lyht.base.hibernate.common;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
/**
 * 分页封装类
 * 用于做分页查询的基础类，封装了一些分页的相关属性
 * @author 
 * @version v1.0
 * @param <T>
 */
public class PageResults<T> implements Serializable{
	 
	private static final long serialVersionUID = 1L;
    // 总条数
    private int totalCount;
    //排序字段名称
    private String sort;
    //ASC DESC 
    private String sortOrder;
    //每页记录条数
    private int limit;
    //记录开始数，非页码
    private int offset;
    
    //页码
    private int pageNo;
    //最大页数
    private int totalPage;
    //jsp页面  中心 页码计算
    private int jspPageNo;
    
    public int getLimit() {
		return limit;
	}
    
    
	/**
     * 多字段排序信息
     */
    private List<SortBean> sortBeanList = new ArrayList<SortBean>();
 
    // 记录
    private List<T> results = new ArrayList<T>();

	public void setLimit(int limit) {
		//默认为每页10条记录
		this.limit = limit <= 0 ? 10 : limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset <= 0 ? 0 : offset;		
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

    //查询记录List
    public List<T> getResults() {
        return results;
    }
 
    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }
 
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
 
	public List<SortBean> getSortBeanList() {
		return sortBeanList;
	}

	public void setSortBeanList(List<SortBean> sortBeanList) {
		this.sortBeanList = sortBeanList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getTotalPage() {
		int ms = this.totalCount % this.limit;
		this.totalPage = this.totalCount / this.limit;
		if(ms > 0 ) this.totalPage = this.totalPage+1;
		return this.totalPage;
	}
	
	public int getJspPageNo() {
		int index = pageNo;
		//计算主要是用于显示 5个页面链接  
		//前条件
		if (index - 2 > 0 && index - 2 != 1)
			index = index - 2;
		else
			index = 1;
		if (getTotalPage() > 3) {
			//后
			if (getTotalPage() - index <= 2)
				index = index - 2;
			if (getTotalPage() - index <= 3)
				index = index - 1;
			if (index <= 0)
				index = 1;
		}
		return index;
	} 
}