package com.lyht.business.question.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.question.bean.Handler;

public class HandlerFormBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Handler mHandler=new Handler();
	
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

	public Handler getmHandler() {
		return mHandler;
	}

	public void setmHandler(Handler mHandler) {
		this.mHandler = mHandler;
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
