package com.lyht.business.contracMng.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContDetail;

/**
 * 作者： 张琦 日期:2018年03月14日 说明: 合同物资明细FormBean
 */
public class ContDetailFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ContDetail contDetailBean = new ContDetail();

	private String searchName; // 模糊查询
	private String ids;
	private String contractNo;
	private String materialCode;
	
	
	

	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();

	public ContDetail getContDetailBean() {
		return contDetailBean;
	}

	public void setContDetailBean(ContDetail contDetailBean) {
		this.contDetailBean = contDetailBean;
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
}
