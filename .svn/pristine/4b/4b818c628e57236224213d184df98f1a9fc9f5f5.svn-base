package com.lyht.business.evaluate.formBean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.evaluate.bean.ContEvaluate;

/**
 * 作者： 张琦 日期:2018年03月12日 说明: 履约评价FormBean
 */
public class ContEvaluateFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ContEvaluate contEvaluateBean = new ContEvaluate();

	private String searchName; // 模糊查询
	private String ids;
	private String year;		//统计 年
	private String month;		//统计 月
	
	@SuppressWarnings("rawtypes")
	private PageResults pageBean = new PageResults();

	public ContEvaluate getContEvaluateBean() {
		return contEvaluateBean;
	}

	public void setContEvaluateBean(ContEvaluate contEvaluateBean) {
		this.contEvaluateBean = contEvaluateBean;
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
