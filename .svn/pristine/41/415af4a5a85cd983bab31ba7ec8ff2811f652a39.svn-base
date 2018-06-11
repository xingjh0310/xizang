package com.lyht.business.contracMng.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.contracMng.bean.ContChange;
import com.lyht.business.contracMng.bean.ContInfo;

/**
 * 作者： 张琦 日期:2018年03月15日 说明: 合同物资变更历史记录FormBean
 */
public class ContChangeFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ContChange contChangeBean = new ContChange();

	private String searchName; // 模糊查询
	private String ids;
	private String engineerNm;		//统计 月
	
	public String getEngineerNm() {
		return engineerNm;
	}

	public void setEngineerNm(String engineerNm) {
		this.engineerNm = engineerNm;
	}

	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();

	public ContChange getContChangeBean() {
		return contChangeBean;
	}

	public void setContChangeBean(ContChange contChangeBean) {
		this.contChangeBean = contChangeBean;
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


	
}
