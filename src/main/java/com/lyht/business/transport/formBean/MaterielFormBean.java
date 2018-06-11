package com.lyht.business.transport.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.transport.bean.Check;
import com.lyht.business.transport.bean.Delivery;
import com.lyht.business.transport.bean.Transfer;

/**
 * 作者： 陈洪强 日期:2018年3月7日  说明: 物资发货信息FormBean
 */
public class MaterielFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Delivery deliveryInfoBean = new Delivery();
	
	private Transfer  transferInfoBean = new Transfer();
	
	private Check checkInfoBean = new Check();

	private String searchName; // 模糊查询
	private String ids;
	private Integer state;		//1 到货列表，2验货列表
	
	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();


	public Delivery getDeliveryInfoBean() {
		return deliveryInfoBean;
	}

	public void setDeliveryInfoBean(Delivery deliveryInfoBean) {
		this.deliveryInfoBean = deliveryInfoBean;
	}

	@SuppressWarnings("rawtypes")
	public PageResults getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
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

	public Transfer getTransferInfoBean() {
		return transferInfoBean;
	}

	public void setTransferInfoBean(Transfer transferInfoBean) {
		this.transferInfoBean = transferInfoBean;
	}

	public Check getCheckInfoBean() {
		return checkInfoBean;
	}

	public void setCheckInfoBean(Check checkInfoBean) {
		this.checkInfoBean = checkInfoBean;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
	
	
}
