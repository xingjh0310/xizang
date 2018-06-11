package com.lyht.business.plan.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.Supply;

public class SupplyFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 供应计划bean
	 */
	private Supply mSupply = new Supply();
	
	/**
     * 用于快速模糊匹配关键字
     */
    private String searchName;
    
    /**
     * 用于批量删除多ids，以","分隔 如：1,2,3,4
     */
    private String ids;
    
    /**
     * 物料类型
     */
    private String materielType;
    
    /**
     * 物料名称
     */
    private String materielBase;
    
    /**
     * 供应商名称
     */
    private String supPlyfullName;
    
    public String getSupPlyfullName() {
		return supPlyfullName;
	}

	public void setSupPlyfullName(String supPlyfullName) {
		this.supPlyfullName = supPlyfullName;
	}

	@SuppressWarnings("rawtypes")
	private PageResults pageBean=new PageResults();

	public Supply getmSupply() {
		return mSupply;
	}

	public void setmSupply(Supply mSupply) {
		this.mSupply = mSupply;
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
}
