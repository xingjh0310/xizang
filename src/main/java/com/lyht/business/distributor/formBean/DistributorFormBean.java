package com.lyht.business.distributor.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.distributor.bean.Distributor;
import com.lyht.business.system.bean.SysStaff;

public class DistributorFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 供应商信息
	 */
	private Distributor mDistributor = new Distributor();
	
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

	public Distributor getmDistributor() {
		return mDistributor;
	}

	public void setmDistributor(Distributor mDistributor) {
		this.mDistributor = mDistributor;
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
