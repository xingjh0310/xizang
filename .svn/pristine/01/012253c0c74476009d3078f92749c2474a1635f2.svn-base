package com.lyht.business.plan.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.Demand;

public class DemandFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 需求清单bean
	 */
	private Demand mDemand=new Demand();
	
	/**
     * 用于快速模糊匹配关键字
     */
    private String searchName;
    
    /**
     * 物料类型
     */
    private String materielType;
    
    /**
     * 物料名称
     */
    private String materielBase;
    
    /**
     * 用于批量删除多ids，以","分隔 如：1,2,3,4
     */
    private String ids;
    
    private String year;	//年查询
    private String month;	//月查询
    
    @SuppressWarnings("rawtypes")
	private PageResults pageBean=new PageResults();

    
    /**
	 * 需求清单bean
	 */
	public Demand getmDemand() {
		return mDemand;
	}

	public void setmDemand(Demand mDemand) {
		this.mDemand = mDemand;
	}

	/**
     * 用于快速模糊匹配关键字
     */
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
     * 用于批量删除多ids，以","分隔 如：1,2,3,4
     */
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

	public String getMaterielType() {
		return materielType;
	}

	public void setMaterielType(String materielType) {
		this.materielType = materielType;
	}

	public String getMaterielBase() {
		return materielBase;
	}

	public void setMaterielBase(String materielBase) {
		this.materielBase = materielBase;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	
}
