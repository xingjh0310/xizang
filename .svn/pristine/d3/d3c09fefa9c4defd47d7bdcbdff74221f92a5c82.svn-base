package com.lyht.business.plan.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.plan.bean.Arrival;

public class ArrivalFormBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Arrival mArrival=new Arrival();
	
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
     * 审核记录查询flag
     */
    private String auditFlag;
    
    
    private String year; //统计年
    private String month; //统计月
    
    /**
     * 物料名称
     */
    private String materielBase;
    
    @SuppressWarnings("rawtypes")
	private PageResults pageBean=new PageResults();

	public Arrival getmArrival() {
		return mArrival;
	}

	public void setmArrival(Arrival mArrival) {
		this.mArrival = mArrival;
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

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}
}
