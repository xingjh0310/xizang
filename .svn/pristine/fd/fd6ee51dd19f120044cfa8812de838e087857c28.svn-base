package com.lyht.business.refund.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.refund.bean.Refund;
import com.lyht.business.refund.bean.RefundDetailed;

/**
 * 作者： 陈洪强 日期:2018年4月18日  说明: 退料退库FormBean
 */

public class RefundFormBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Refund refund = new Refund();
	private RefundDetailed refundDetailed = new RefundDetailed();
	
	private String searchName; // 模糊查询
	private String ids;
	
	private Integer state;	//状态
	
	 private String year;	//年查询
	 private String month;	//月查询
	 private String materielType; //物料类型
	
	
	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();
	
	public Refund getRefund() {
		return refund;
	}
	public void setRefund(Refund refund) {
		this.refund = refund;
	}
	public RefundDetailed getRefundDetailed() {
		return refundDetailed;
	}
	public void setRefundDetailed(RefundDetailed refundDetailed) {
		this.refundDetailed = refundDetailed;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getMaterielType() {
		return materielType;
	}
	public void setMaterielType(String materielType) {
		this.materielType = materielType;
	}
	
	
	
}
