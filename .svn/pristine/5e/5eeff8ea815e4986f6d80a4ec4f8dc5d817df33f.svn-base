package com.lyht.business.question.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.bean.HandlerDetail;

public class HandlerDetailFormBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HandlerDetail mHandlerDetail = new HandlerDetail();
	
	/**
     * 用于快速模糊匹配关键字
     */
    private String searchName;
    
    /**
     * 用于批量删除多ids，以","分隔 如：1,2,3,4
     */
    private String ids;
    
    @SuppressWarnings("rawtypes")
	private PageResults pageBean=new PageResults();

	public HandlerDetail getmHandlerDetail() {
		return mHandlerDetail;
	}

	public void setmHandlerDetail(HandlerDetail mHandlerDetail) {
		this.mHandlerDetail = mHandlerDetail;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@SuppressWarnings("rawtypes")
	public PageResults getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
    
}
