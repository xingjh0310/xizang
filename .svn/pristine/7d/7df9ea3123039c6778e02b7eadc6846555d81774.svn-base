package com.lyht.business.contracMng.formbean;

import java.io.File;
import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContInfo;

/**
 * 作者： 张琦 日期:2018年03月07日 说明: 主合同FormBean
 */
public class ContInfoFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ContInfo contInfoBean = new ContInfo();

	private String searchName; // 模糊查询
	private String ids;
	private String supply;		//供应厂商
	private String engineerNm;	//工程内码
	private String engineerNms;	//工程内码
	private String type;	//主合同列表还是合同变更列表

	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();

	public ContInfo getContInfoBean() {
		return contInfoBean;
	}

	public void setContInfoBean(ContInfo contInfoBean) {
		this.contInfoBean = contInfoBean;
	}

	@SuppressWarnings("rawtypes")
	public PageResults getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getEngineerNm() {
		return engineerNm;
	}

	public void setEngineerNm(String engineerNm) {
		this.engineerNm = engineerNm;
	}
	
	public String getEngineerNms() {
		return engineerNms;
	}

	public void setEngineerNms(String engineerNms) {
		this.engineerNms = engineerNms;
	}
	
	public String getSupply() {
		return supply;
	}
	
	public void setSupply(String supply) {
		this.supply = supply;
	}

}
